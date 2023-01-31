<template>
  <div class="app-container">
    <el-form :model="queryParam" ref="queryForm" :inline="true">
       <el-form-item label="课程：">
        <el-select v-model="queryParam.courseId" clearable>
          <el-option v-for="item in subjects" :key="item.id" :value="item.id"
                     :label="item.name"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">查询</el-button>
         <el-button type="primary" @click="selectStudent">随机抽人</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="listLoading" :data="tableData" border fit highlight-current-row style="width: 100%">
      <el-table-column type="index" width="50" label="ID"></el-table-column>
      <el-table-column prop="username" label="用户名"/>
      <el-table-column prop="realName" label="真实姓名" />
      <el-table-column prop="courseName" label="课程名" />
      <el-table-column prop="college" label="学院"/>
      <el-table-column prop="className" label="班级名" width="160px"/>
      <el-table-column width="270px" label="操作" align="center">
        <template slot-scope="{row}">
          <router-link :to="{path:'/user/student/edit', query:{id:row.id}}" class="link-left">
            <el-button size="mini" >编辑</el-button>
          </router-link>
          <el-button  size="mini" type="danger" @click="deleteUser(row)" class="link-left">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="queryParam.pageIndex" :limit.sync="queryParam.pageSize"
                @pagination="search"/>
    <el-dialog title="随机点名" :visible.sync="dialogFormVisible">
    <el-form :model="form">
      <el-form-item label="姓名: " :label-width="formLabelWidth">
        <!-- <el-input v-model="form.name" autocomplete="off"></el-input> -->
         <span style="font-size: 25px">{{name}}</span>
      </el-form-item>
      <el-form-item label="打分: " :label-width="formLabelWidth">
        <!-- <el-select v-model="form.region" placeholder="请选择活动区域">
          <el-option label="区域一" value="shanghai"></el-option>
          <el-option label="区域二" value="beijing"></el-option>
        </el-select> -->
        <el-rate v-model="form.score" class="question-item-rate"></el-rate>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="submitScoreForm">确 定</el-button>
    </div>
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters, mapState, mapActions  } from 'vuex'
import Pagination from '@/components/Pagination'
import courseApi from '@/api/course'

export default {
  components: { Pagination },
  data () {
    return {
      queryParam: {
        courseId: null,
        pageIndex: 1,
        pageSize: 10
      },
      name: '张三',
      teacherId: null,
      listLoading: true,
      tableData: [],
      total: 0,
      subjectFilter: null,
      dialogFormVisible: false,
      form: {
        studentId: 0,
        courseId: 0,
        score: 0,
      },
      formLabelWidth: '120px'
    }
  },
  created () {
    this.search()
    this.initSubject()
  },
  methods: {
    submitScoreForm () {
      let _this = this
      courseApi.saveScore(this.form).then(re => {
        if (re.code === 0) {
          this.dialogFormVisible = false
          _this.$message.success(re.msg)
        } else {
          _this.$message.error(re.msg)
        }
      })
    },
    selectStudent () {
      this.dialogFormVisible = true
      let param ={courseId: this.queryParam.courseId}
      this.form.courseId = this.queryParam.courseId
      courseApi.selectStudent(param).then(data => {
        this.name = data.data.username
        this.form.studentId = data.data.id
      })
    },
    search () {
      this.listLoading = true
      courseApi.getCourseStudentList(this.queryParam).then(data => {
        this.tableData = data.data.list
        this.total = data.data.total
        this.queryParam.pageIndex = data.pageNum
        this.listLoading = false
      })
    },
    changeStatus (row) {
      let _this = this
      userApi.changeStatus(row.id).then(re => {
        if (re.code === 1) {
          row.status = re.response
          _this.$message.success(re.message)
        } else {
          _this.$message.error(re.message)
        }
      })
    },
    deleteUser (row) {
      let _this = this
      console.log(row.id)
      courseApi.deleCourseStudent(row.id).then(re => {
        if (re.code === 0) {
          _this.search()
          _this.$message.success(re.msg)
        } else {
          _this.$message.error(re.msg)
        }
      })
    },
    submitForm () {
      this.queryParam.pageIndex = 1
      this.search()
    },
    levelFormatter  (row, column, cellValue, index) {
      return this.enumFormat(this.levelEnum, cellValue)
    },
    sexFormatter  (row, column, cellValue, index) {
      return this.enumFormat(this.sexEnum, cellValue)
    },
    statusFormatter (status) {
      return this.enumFormat(this.statusEnum, status)
    },
    statusTagFormatter (status) {
      return this.enumFormat(this.statusTag, status)
    },
    statusBtnFormatter (status) {
      return this.enumFormat(this.statusBtn, status)
    },
    subjectFormatter  (row, column, cellValue, index) {
      return this.subjectEnumFormat(cellValue)
    },
    ...mapActions('exam', { initSubject: 'initSubject' })
  },
  computed: {
    ...mapGetters('enumItem', [
      'enumFormat'
    ]),
     ...mapGetters([
      'userId'
    ]),
    ...mapState('enumItem', {
      sexEnum: state => state.user.sexEnum,
      statusEnum: state => state.user.statusEnum,
      statusTag: state => state.user.statusTag,
      statusBtn: state => state.user.statusBtn,
      levelEnum: state => state.user.levelEnum
    }),
    ...mapGetters('exam', ['subjectEnumFormat']),
    ...mapState('exam', { subjects: state => state.subjects })
  }
}
</script>
