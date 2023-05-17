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
      </el-form-item>
    </el-form>

    <el-table v-loading="listLoading" :data="tableData" border fit highlight-current-row style="width: 100%">
      <el-table-column type="index" width="50" label="ID"></el-table-column>
      <el-table-column prop="title" label="标题" show-overflow-tooltip/>
      <el-table-column prop="content" label="内容" show-overflow-tooltip />
      <el-table-column prop="courseName" label="接收课程"  show-overflow-tooltip />
      <el-table-column prop="receiveCount" label="接收人数" width="100" />
      <el-table-column prop="readCount" label="已读数" width="70" />
      <el-table-column prop="createTime" label="创建时间" width="160px"/>
      <el-table-column  label="操作" align="center"  width="160px">
        <template slot-scope="{row}">
          <el-button size="mini" @click="$router.push({path:'/message/send',query:{id:row.id}})" >编辑</el-button>
          <el-button size="mini" type="danger"  @click="deletePaper(row)" class="link-left">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="queryParam.pageIndex" :limit.sync="queryParam.pageSize"
                @pagination="search"/>
  </div>
</template>

<script>
import { mapGetters, mapState, mapActions } from 'vuex'
import Pagination from '@/components/Pagination'
import messageApi from '@/api/message'

export default {
  components: { Pagination },
  data () {
    return {
      queryParam: {
        teacherId: 0,
        sendUserName: null,
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
      messageApi.pageList(this.queryParam).then(data => {
        this.tableData = data.data.list
        this.total = data.data.total
        this.queryParam.pageIndex = data.data.pageNum
        this.listLoading = false
      })
    },
    deletePaper (row) {
      let _this = this
      messageApi.delete(row.id).then(re => {
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
    ...mapActions('exam', { initSubject: 'initSubject' })
  },
   computed: {
    ...mapGetters([
      'userId'
    ]),
    ...mapGetters('enumItem', ['enumFormat']),
    ...mapState('enumItem', {
      levelEnum: state => state.user.levelEnum
    }),
    ...mapGetters('exam', ['subjectEnumFormat']),
    ...mapState('exam', { subjects: state => state.subjects })
  }
}
</script>
