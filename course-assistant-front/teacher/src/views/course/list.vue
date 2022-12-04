<template>
  <div class="app-container">
    <el-form :model="queryParam" ref="queryForm" :inline="true">
      <el-form-item label="用户名：">
        <el-input v-model="queryParam.userName"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">查询</el-button>
        <router-link :to="{path:'/course/edit'}" class="link-left">
          <el-button type="primary">添加</el-button>
        </router-link>
      </el-form-item>
    </el-form>

    <el-table v-loading="listLoading" :data="tableData" border fit highlight-current-row style="width: 100%">
      <el-table-column prop="id" label="Id" />
      <el-table-column prop="name" label="课程名称"/>
      <el-table-column prop="command" label="班级口令" />
      <el-table-column prop="count" label="人数"  />
      <el-table-column prop="teacher" label="教师" width="60px;">{{userName}} </el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="160px"/>
      <el-table-column label="状态" prop="status" width="70px">
        <template slot-scope="{row}">
          <el-tag :type="statusTagFormatter(row.status)">
            {{ statusFormatter(row.status) }}
          </el-tag>
        </template>
      </el-table-column>
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
        name: '',
        role: 1,
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
          _this.$message.success(re.message)
        } else {
          _this.$message.error(re.message)
        }
      })
    },
    deleteUser (row) {
      let _this = this
      courseApi.deleteUser(row.id).then(re => {
        if (re.code === 1) {
          _this.search()
          _this.$message.success(re.message)
        } else {
          _this.$message.error(re.message)
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
    }
  },
  computed: {
    ...mapGetters('enumItem', [
      'enumFormat'
    ]),
    ...mapGetters(['userName']),
    ...mapState('enumItem', {
      // sexEnum: state => state.user.sexEnum,
      statusEnum: state => state.course.statusEnum,
      statusTag: state => state.user.statusTag
      // statusBtn: state => state.user.statusBtn,
      // levelEnum: state => state.user.levelEnum,

    })
  }
}
</script>
