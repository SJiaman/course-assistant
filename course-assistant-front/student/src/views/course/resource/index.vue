<template>
  <div style="margin-top: 10px" class="app-contain">
    <el-tabs tab-position="left"  v-model="tabId"  @tab-click="subjectChange" >
      <el-tab-pane :label="item.name"  :key="item.id" :name="item.id" v-for="item in subjectList" style="margin-left: 20px;" >
        <!-- <el-row  style="float: right">
          <el-radio-group v-model="queryParam.type" size="mini" @change="paperTypeChange" >
            <el-radio v-for="item in paperTypeEnum" size="mini" :key="item.key" :label="item.key">{{item.value}}</el-radio>
          </el-radio-group>
        </el-row> -->
        <el-table v-loading="listLoading" :data="tableData" fit highlight-current-row style="width: 100%">
          <el-table-column type="index" width="50" label="ID"></el-table-column>
          <el-table-column prop="fileName" label="名称"  />
          <el-table-column align="right">
            <template slot-scope="{row}">
              <!-- <router-link target="_blank" :to="{path:'/do',query:{id:row.id}}">
                <el-button  type="text" size="small">开始答题</el-button>
              </router-link> -->
              <a :href="row.url">打开</a>
            </template>
          </el-table-column>
        </el-table>
        <pagination v-show="total>0" :total="total" :background="false" :page.sync="queryParam.pageIndex" :limit.sync="queryParam.pageSize"
                    @pagination="search" style="margin-top: 20px"/>
      </el-tab-pane>
    </el-tabs>

  </div>
</template>

<script>
import { mapState } from 'vuex'
import Pagination from '@/components/Pagination'
import courseApi from '@/api/course'
import subjectApi from '@/api/subject'

export default {
  components: { Pagination },
  data () {
    return {
      queryParam: {
        type: 1,
        courseId: 0,
        pageIndex: 1,
        pageSize: 10
      },
      tabId: '',
      listLoading: true,
      subjectList: [],
      tableData: [],
      total: 0
    }
  },
  created () {
    this.initSubject()
  },
  methods: {
    initSubject () {
      let _this = this
      subjectApi.list().then(re => {
        _this.subjectList = re.data
        let courseId = _this.subjectList[0].id
        _this.queryParam.courseId = courseId
        _this.tabId = courseId.toString()
        _this.search()
      })
    },
    search () {
      this.listLoading = true
      courseApi.resourceList(this.queryParam).then(re => {
        this.tableData = re.data.list
        this.total = re.data.total
        this.listLoading = false
      })
    },
    paperTypeChange (val) {
      this.search()
    },
    subjectChange (tab, event) {
      this.queryParam.courseId = Number(this.tabId)
      this.search()
    }
  },
  computed: {
    ...mapState('enumItem', {
      paperTypeEnum: state => state.exam.examPaper.paperTypeEnum,
    })
  }
}
</script>
