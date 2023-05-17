<template>
  <div class="app-container">
    <el-form :model="queryParam" ref="queryForm" :inline="true">
      <el-form-item label="学科：" >
        <el-select v-model="queryParam.courseId"  clearable>
          <el-option v-for="item in subjects" :key="item.id" :value="item.id" :label="item.name"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">查询</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="listLoading" :data="tableData" border fit highlight-current-row style="width: 100%">
      <el-table-column prop="id" label="Id"  width="100" />
      <el-table-column prop="examName" label="试卷名称"/>
      <el-table-column prop="courseId" label="课程" :formatter="subjectFormatter"/>
      <el-table-column prop="score" label="试卷总分"/>
      <el-table-column prop="minScore" label="最低分"/>
      <el-table-column prop="maxScore" label="最高分"/>
      <el-table-column prop="averageScore" label="平均分"/>
      <el-table-column prop="averageDoTime" label="平均做题时长(秒)"/>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="queryParam.pageIndex" :limit.sync="queryParam.pageSize"
                @pagination="search"/>
  </div>
</template>

<script>

import { mapGetters, mapState, mapActions } from 'vuex'
import Pagination from '@/components/Pagination'
import examPaperAnswerApi from '@/api/examPaperAnwser'

export default {
  components: { Pagination },
  data () {
    return {
      queryParam: {
        courseId: null,
        teacherId: 0,
        pageIndex: 1,
        pageSize: 10
      },
      courseParm: {
        teacherId: 1
      },
      listLoading: false,
      tableData: [],
      total: 0
    }
  },
  created () {
    this.queryParam.teacherId = this.userId
    this.courseParm.teacherId = this.userId
    this.initSubject(this.courseParm)
    this.search()
  },
  methods: {
    search () {
      this.listLoading = true
      examPaperAnswerApi.page(this.queryParam).then(re => {
        this.tableData = re.data.list
        this.total = re.data.total
        this.queryParam.pageIndex = re.pageNum
        this.listLoading = false
      })
    },
    submitForm () {
      this.queryParam.pageIndex = 1
      this.search()
    },
    subjectFormatter (row, column, cellValue, index) {
      return this.subjectEnumFormat(cellValue)
    },
    ...mapActions('exam', { initSubject: 'initSubject' })
  },
  computed: {
    ...mapGetters([
      'userId'
    ]),
    ...mapGetters('enumItem', ['enumFormat']),
    ...mapGetters('exam', ['subjectEnumFormat']),
    ...mapState('exam', { subjects: state => state.subjects })
  }
}
</script>
