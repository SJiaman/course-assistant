<template>
  <div class="app-container">
    <el-form :model="queryParam" ref="queryForm" :inline="true">
      <el-form-item label="课程名：">
        <el-input v-model="queryParam.name"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">查询</el-button>
        <router-link :to="{path:'/course/edit'}" class="link-left">
          <el-button type="primary">添加</el-button>
        </router-link>
      </el-form-item>
    </el-form>

    <el-table v-loading="listLoading" :data="tableData" border fit highlight-current-row style="width: 100%">
       <el-table-column type="index" width="50" label="ID"></el-table-column>
      <el-table-column prop="name" label="课程名称"  width="150"/>
      <el-table-column prop="command" label="班级口令"  width="150" />
      <el-table-column prop="count" label="人数"  width="150" />
      <el-table-column prop="teacher" label="教师" width="150px;">{{userName}} </el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="200px"/>
      <el-table-column label="状态" prop="status" width="100px">
        <template slot-scope="{row}">
          <el-tag :type="statusTagFormatter(row.status)">
            {{ statusFormatter(row.status) }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column width="270px" label="操作" align="center">
        <template slot-scope="{row}">
          <router-link :to="{path:'/course/edit', query:{id:row.id}}" class="link-left">
            <el-button size="mini" >编辑</el-button>
          </router-link>
          <el-button  size="mini" type="danger" @click="deleteCourse(row)" class="link-left">删除</el-button>
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
        name: null,
        pageIndex: 1,
        pageSize: 10
      },
      listLoading: true,
      tableData: [],
      total: 0
    }
  },
  created () {
    this.search()
  },
  methods: {
    search () {
      this.listLoading = true
      courseApi.getCoursePageList(this.queryParam).then(result => {
        this.tableData = result.data.list
        this.total = result.data.total
        // this.queryParam.pageIndex = re.pageNum
        this.listLoading = false
      })
    },
    changeStatus (row) {
      let _this = this
      courseApi.changeStatus(row.id).then(re => {
        if (re.code === 1) {
          row.status = re.response
          _this.$message.success(re.msg)
        } else {
          _this.$message.error(re.msg)
        }
      })
    },
    deleteCourse (row) {
      let _this = this
      courseApi.deleteCourse(row.id).then(re => {
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
    statusFormatter (status) {
      return this.enumFormat(this.statusEnum, status)
    },
    statusTagFormatter (status) {
      return this.enumFormat(this.statusTag, status)
    }
  },
  computed: {
    ...mapGetters('enumItem', [
      'enumFormat'
    ]),
    ...mapGetters(['userName']),
    ...mapState('enumItem', {
      statusEnum: state => state.course.statusEnum,
      statusTag: state => state.user.statusTag
    })
  }
}
</script>
