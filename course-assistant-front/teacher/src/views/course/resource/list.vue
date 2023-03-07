<template>
  <div class="app-container">
    <el-form :model="queryParam" ref="queryForm" :inline="true">
      <el-form-item>
        <!-- <el-button type="primary" @click="submitForm">查询</el-button> -->
        <router-link :to="{path:'/course/resource/edit'}" class="link-left">
          <el-button type="primary">添加</el-button>
        </router-link>
      </el-form-item>
    </el-form>

    <el-table v-loading="listLoading" :data="tableData" border fit highlight-current-row style="width: 100%">
      <el-table-column type="index" width="50" label="ID"></el-table-column>
      <el-table-column prop="courseId" label="学科" :formatter="subjectFormatter" width="120px"/>
      <el-table-column prop="fileName" label="资源名" />
      <!-- <el-table-column prop="type" label="类型"  /> -->
      <el-table-column width="220px" label="操作" align="center">
        <template slot-scope="{row}">
          <el-button size="mini"><a :href="row.url">打开</a></el-button>
          <router-link :to="{path:'/course/resource/edit', query:{id:row.id}}" class="link-left">
            <el-button size="mini">编辑</el-button>
          </router-link>
          <el-button   size="mini" type="danger" @click="delSubject(row)" class="link-left">删除</el-button>
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
      listLoading: true,
      tableData: [],
      total: 0,
      subjectList: [],
    }
  },
  created () {
    this.search()
    this.initSubject()
  },
  methods: {
    search () {
      this.listLoading = true
      courseApi.resourceList(this.queryParam).then(re => {
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
    delSubject (row) {
      let _this = this
      courseApi.deleteResource(row.id).then(re => {
        if (re.code === 0) {
          _this.search()
          _this.$message.success(re.message)
        } else {
          _this.$message.error(re.message)
        }
      })
    },
    resourceFormatter (row, column, cellValue, index) {
      return this.resourceEnumFormat(cellValue)
    },
     subjectFormatter (row, column, cellValue, index) {
      return this.subjectEnumFormat(cellValue)
    },
    ...mapActions('exam', { initSubject: 'initSubject' }),
  },
  computed: {
    ...mapGetters('enumItem', [
      'enumFormat'
    ]),
    ...mapState('enumItem', {
      resourceEnum: state => state.course.resourceEnum
    }),
    ...mapGetters('exam', ['subjectEnumFormat']),
    ...mapState('exam', { subjects: state => state.subjects })
  }
}
</script>
