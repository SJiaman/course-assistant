<template>
  <div style="margin-top: 10px">
    <!-- <el-row>
      <el-carousel :interval="5000" arrow="always" type="card">
        <el-carousel-item >
          <img src="@/assets/carousel/1.png" class="carousel-img">
        </el-carousel-item>
        <el-carousel-item >
          <img src="@/assets/carousel/2.png" class="carousel-img">
        </el-carousel-item>
        <el-carousel-item >
          <img src="@/assets/carousel/3.png" class="carousel-img">
        </el-carousel-item>
        <el-carousel-item >
          <img src="@/assets/carousel/4.png" class="carousel-img">
        </el-carousel-item>
      </el-carousel>
    </el-row> -->
    <!-- <el-row class="app-item-contain">
      <h3 class="index-title-h3" style="border-left: solid 10px #3651d4;">课程中心</h3>
      <div style="padding-left: 15px">
        <el-collapse  v-loading="taskLoading"  accordion v-if="taskList.length!==0">
          <el-collapse-item :title="taskItem.name" :name="taskItem.id" :key="taskItem.id" v-for="taskItem in taskList">
            <table class="index-task-table">
              <tr v-for="paperItem in taskItem.paperItems" :key="paperItem.examPaperId">
                <td class="index-task-table-paper">
                  {{paperItem.examPaperName}}
                </td>
                <td width="70px">
                  <el-tag :type="statusTagFormatter(paperItem.status)" v-if="paperItem.status !== null" size="mini">
                    {{ statusTextFormatter(paperItem.status) }}
                  </el-tag>
                </td>
                <td width="80px">
                  <router-link target="_blank" :to="{path:'/do',query:{id:paperItem.examPaperId}}" v-if="paperItem.status === null">
                    <el-button  type="text" size="small">开始答题</el-button>
                  </router-link>
                  <router-link target="_blank" :to="{path:'/edit',query:{id:paperItem.examPaperAnswerId}}" v-else-if="paperItem.status === 1">
                    <el-button  type="text" size="small">批改试卷</el-button>
                  </router-link>
                  <router-link target="_blank" :to="{path:'/read',query:{id:paperItem.examPaperAnswerId}}" v-else-if="paperItem.status === 2">
                    <el-button  type="text" size="small">查看试卷</el-button>
                  </router-link>
                </td>
              </tr>
            </table>
          </el-collapse-item>
        </el-collapse>
      </div>
    </el-row> -->
    <el-row class="app-item-contain">
      <h3 class="index-title-h3">课程中心</h3>
      <div style="padding-left: 15px">
        <el-col :span="4" v-for="(item, index) in taskList" :key="index" :offset="index > 0 ? 1 : 0">
          <el-card :body-style="{ padding: '0px' }" v-loading="loading">
            <img src="@/assets/exam-paper/show1.png" class="image">
            <div style="padding: 14px;">
              <span>{{item.name}}</span>
              <div class="bottom clearfix">
                <!-- <router-link target="_blank" :to="{path:'/do',query:{id:item.id}}"> -->
                  <el-button type="text" class="button" @click="open(item.id)">加入课程</el-button>
                <!-- </router-link> -->
              </div>
            </div>
          </el-card>
        </el-col>
      </div>
    </el-row>
    <!-- <el-row class="app-item-contain">
      <h3 class="index-title-h3" style="border-left: solid 10px rgb(220, 208, 65);">时段试卷</h3>
      <div style="padding-left: 15px">
        <el-col :span="4" v-for="(item, index) in timeLimitPaper" :key="index" :offset="index > 0 ? 1 : 0">
          <el-card :body-style="{ padding: '0px' }" v-loading="loading">
            <img src="@/assets/exam-paper/show2.png" class="image">
            <div style="padding: 14px;">
              <span>{{item.name}}</span>
              <p class="index-limit-paper-time">
                <span>{{item.startTime}}</span>
                <br/>
                <span>{{item.endTime}}</span>
              </p>
              <div class="bottom clearfix">
                <router-link target="_blank" :to="{path:'/do',query:{id:item.id}}">
                  <el-button type="text" class="button">开始做题</el-button>
                </router-link>
              </div>
            </div>
          </el-card>
        </el-col>
      </div>
    </el-row> -->
  </div>
</template>

<script>
import { mapState, mapGetters } from 'vuex'
import indexApi from '@/api/dashboard'
import courseApi from '@/api/course'

export default {
  data () {
    return {
      joinCourse: {
        studentId: '',
        courseId: '',
        command: ''
      },
      fixedPaper: [],
      timeLimitPaper: [],
      pushPaper: [],
      loading: false,
      taskLoading: false,
      taskList: []
    }
  },
  created () {
    let _this = this
    this.loading = true
    // indexApi.index().then(re => {
    //   _this.fixedPaper = re.response.fixedPaper
    //   _this.timeLimitPaper = re.response.timeLimitPaper
    //   _this.pushPaper = re.response.pushPaper
    //   _this.loading = false
    // })
    this.taskLoading = true
    courseApi.getCoursePageList().then(re => {
      _this.taskList = re.data.list
      _this.loading = false
      _this.taskLoading = false
    })
  },
  methods: {
    open(id) {
      let _this = this
      this.$prompt('请输入班级口令', '加入课程', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        // inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
        // inputErrorMessage: '邮箱格式不正确'
      }).then(({ value }) => {
        _this.joinCourse.courseId = id
        _this.joinCourse.studentId = this.userInfo.id
        _this.joinCourse.command = value
        console.log(this.joinCourse)
        courseApi.joinCourse(this.joinCourse).then(function (result) {
          if (result && result.code === 0) {
            _this.$message.success(result.msg)
          } else {
            _this.$message.error(result.msg)
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        });       
      });
    },
    statusTagFormatter (status) {
      return this.enumFormat(this.statusTag, status)
    },
    statusTextFormatter (status) {
      return this.enumFormat(this.statusEnum, status)
    }   
  },
  computed: {
    ...mapGetters('enumItem', [
      'enumFormat'
    ]),
    ...mapState('user', ['userInfo']),
    ...mapState('enumItem', {
      statusEnum: state => state.exam.examPaperAnswer.statusEnum,
      statusTag: state => state.exam.examPaperAnswer.statusTag
    })
  }
}
</script>

<style lang="scss" scoped>
  .index-title-h3 {
    font-size: 22px;
    font-weight: 400;
    color: #1f2f3d;
    border-left: solid 10px #2ce8b4;
    padding-left: 10px;
  }

  .el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 50%;
    height: 80%;
    display: block;
    margin: 20px auto 10px auto;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
</style>
