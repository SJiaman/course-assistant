<template>
  <div class="app-container">
    <el-form :model="queryParam" ref="queryForm" :inline="true">
      <el-form-item label="课程：">
        <el-select v-model="queryParam.courseId" clearable>
          <el-option v-for="item in subjects" :key="item.id" :value="item.id"
                     :label="item.name"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="题型：">
        <el-select v-model="queryParam.type" clearable>
          <el-option v-for="item in questionType" :key="item.key" :value="item.key" :label="item.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">查询</el-button>
        <el-popover placement="bottom" trigger="click">
          <el-button type="warning" size="mini" v-for="item in editUrlEnum" :key="item.key"
                     @click="$router.push({path:item.value})">{{item.name}}
          </el-button>
          <el-button slot="reference" type="primary" class="link-left">添加</el-button>
        </el-popover>
      </el-form-item>
    </el-form>
    <el-table v-loading="listLoading" :data="tableData" border fit highlight-current-row style="width: 100%">
      <el-table-column type="index" width="50" label="ID"></el-table-column>
      <el-table-column prop="courseId" label="学科" :formatter="subjectFormatter" width="120px"/>
      <el-table-column prop="type" label="题型" :formatter="questionTypeFormatter" width="70px"/>
      <el-table-column prop="title" label="题干" show-overflow-tooltip/>
      <el-table-column prop="score" label="分数" width="60px"/>
      <el-table-column prop="difficult" label="难度" width="60px"/>
      <el-table-column prop="createTime" label="创建时间" width="160px"/>
      <el-table-column label="操作" align="center" width="220px">
        <template slot-scope="{row}">
          <el-button size="mini"   @click="showQuestion(row)">预览</el-button>
          <el-button size="mini"  @click="editQuestion(row)">编辑</el-button>
          <el-button size="mini"  type="danger" @click="deleteQuestion(row)" class="link-left">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="queryParam.pageIndex" :limit.sync="queryParam.pageSize"
                @pagination="search"/>
    <el-dialog :visible.sync="questionShow.dialog" style="width: 100%;height: 100%">
      <QuestionShow :qType="questionShow.qType" :question="questionShow.question" :qLoading="questionShow.loading"/>
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters, mapState, mapActions } from 'vuex'
import Pagination from '@/components/Pagination'
import QuestionShow from './components/Show'
import questionApi from '@/api/question'

export default {
  components: { Pagination, QuestionShow },
  data () {
    return {
      queryParam: {
        id: null,
        type: null,
        courseId: null,
        pageIndex: 1,
        pageSize: 10
      },
      subjectList: [],
      listLoading: true,
      tableData: [],
      total: 0,
      questionShow: {
        qType: 0,
        dialog: false,
        question: null,
        loading: false
      }
    }
  },
  created () {
    this.initSubject()
    this.subjectList = this.subjects
    this.search()
  },
  methods: {
    submitForm () {
      this.queryParam.pageIndex = 1
      this.search()
    },
    search () {
      this.listLoading = true
      questionApi.questionPageList(this.queryParam).then(data => {
        this.tableData = data.data.list
        this.total = data.data.total
        // this.queryParam.pageIndex = re.pageNum
        this.listLoading = false
      })
    },
    levelChange () {
      this.queryParam.subjectId = null
      this.subjectList = this.subjects
    },
    addQuestion () {
      this.$router.push('/exam/question/edit/singleChoice')
    },
    showQuestion (row) {
      let _this = this
      this.questionShow.dialog = true
      this.questionShow.loading = true
      questionApi.select(row.id).then(re => {
        _this.questionShow.qType = re.data.type
        _this.questionShow.question = re.data
        _this.questionShow.loading = false
      })
    },
    editQuestion (row) {
      let url = this.enumFormat(this.editUrlEnum, row.type)
      this.$router.push({ path: url, query: { id: row.id } })
    },
    deleteQuestion (row) {
      let _this = this
      questionApi.deleteQuestion(row.id).then(re => {
        if (re.code === 0) {
          _this.search()
          _this.$message.success(re.msg)
        } else {
          _this.$message.error(re.msg)
        }
      })
    },
    questionTypeFormatter (row, column, cellValue, index) {
      return this.enumFormat(this.questionType, cellValue)
    },
    subjectFormatter (row, column, cellValue, index) {
      return this.subjectEnumFormat(cellValue)
    },
    ...mapActions('exam', { initSubject: 'initSubject' })
  },
  computed: {
    ...mapGetters('enumItem', ['enumFormat']),
    ...mapState('enumItem', {
      questionType: state => state.exam.question.typeEnum,
      editUrlEnum: state => state.exam.question.editUrlEnum,
      levelEnum: state => state.user.levelEnum
    }),
    ...mapGetters('exam', ['subjectEnumFormat']),
    ...mapState('exam', { subjects: state => state.subjects })
  }
}
</script>
