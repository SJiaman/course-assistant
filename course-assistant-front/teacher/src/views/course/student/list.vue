<template>
  <div class="app-container">
    <el-form :model="queryParam" ref="queryForm" :inline="true">
      <el-form-item label="课程名：">
        <el-input v-model="queryParam.courseName"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">查询</el-button>
        <router-link :to="{path:'/user/student/edit'}" class="link-left">
          <el-button type="primary">添加</el-button>
        </router-link>
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
  </div>
</template>

<script>
import { mapGetters, mapState } from 'vuex'
import Pagination from '@/components/Pagination'
import courseApi from '@/api/course'

export default {
  components: { Pagination },
  data () {
    return {
      queryParam: {
        courseName: '',
        teacherId: null,
        pageIndex: 1,
        pageSize: 10
      },
      teacherId: null,
      listLoading: true,
      tableData: [],
      total: 0
    }
  },
  created () {
    this.teacherId = this.userId
    this.search()
  },
  methods: {
    search () {
      this.listLoading = true
      courseApi.getCourseStudentList(this.teacherId).then(data => {
        this.tableData = data.data
        this.total = data.total
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
       this.listLoading = true
        let param ={courseName: this.queryParam.courseName}
        courseApi.getCourseStudentByName(param).then(data => {
        this.tableData = data.data
        this.total = data.total
        this.queryParam.pageIndex = data.pageNum
        this.listLoading = false
      })
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
    }
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
    })
  }
}
</script>
