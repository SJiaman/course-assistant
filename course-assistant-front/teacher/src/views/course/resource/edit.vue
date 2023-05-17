<template>
  <div class="app-container">
    <el-form :model="form" ref="form" label-width="100px" v-loading="formLoading">
     <el-form-item label="课程：">
        <el-select v-model="form.courseId" clearable>
          <el-option v-for="item in subjects" :key="item.id" :value="item.id"
                     :label="item.name"></el-option>
        </el-select>
      </el-form-item>
       <el-form-item label="类型：" required>
        <el-select v-model="form.type" placeholder="资源类型">
          <el-option v-for="item in resourceEnum" :key="item.key" :value="item.key" :label="item.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="名称：" prop="fileName" >
        <el-input v-model="form.fileName"/>
      </el-form-item>
       <el-form-item label="资源：" required>
        <el-upload
          :limit="num"
          class="upload-demo"
          drag
          action="/api/sys/oss/upload"
          multiple
          :on-success="uploadSuccess">
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { mapGetters, mapState, mapActions } from 'vuex'
import courseApi from '@/api/course'

export default {
  data () {
    return {
      form: {
        id: null,
        fileName: '',
        courseId:'',
        type: 0,
        url: ''
      },
      courseParm: {
        teacherId: 1
      },
      formLoading: false,
      subjectList: [],
      num: 1
    }
  },
  created () {
    let id = this.$route.query.id
    let _this = this
    if (id && parseInt(id) !== 0) {
      _this.formLoading = true
      courseApi.selectResource(id).then(re => {
        _this.form = re.data
        _this.formLoading = false
      })
    }
    this.courseParm.teacherId = this.userId
    this.initSubject(this.courseParm)
    this.subjectList = this.subjects
  },
  methods: {
    uploadSuccess (re, file) {
      if (re.code === 0) {
        this.form.url = re.data
      } else {
        this.$message.error(re.msg)
      }
    },
    submitForm () {
      let _this = this
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.formLoading = true
          if (this.form.id == null) {
            console.log(this.form)
            courseApi.saveResource(this.form).then(data => {
              if (data.code === 0) {
                _this.$message.success(data.msg)
                _this.delCurrentView(_this).then(() => {
                  _this.$router.push('/course/resource/list')
                })
              } else {
                _this.$message.error(data.msg)
                _this.formLoading = false
              }
            }).catch(e => {
              _this.formLoading = false
            })
          } else {
            courseApi.updateResource(this.form).then(data => {
              if (data.code === 0) {
                _this.$message.success(data.msg)
                _this.delCurrentView(_this).then(() => {
                  _this.$router.push('/course/resource/list')
                })
              } else {
                _this.$message.error(data.msg)
                _this.formLoading = false
              }
            }).catch(e => {
              _this.formLoading = false
            })
          }
        } else {
          return false
        }
      })
    },
    resetForm () {
      let lastId = this.form.id
      this.$refs['form'].resetFields()
      this.form = {
        id: null,
        fileName: '',
        courseId:'',
        type: 0,
        url: ''
      }
      this.form.id = lastId
    },
    subjectFormatter (row, column, cellValue, index) {
      return this.subjectEnumFormat(cellValue)
    },
    ...mapActions('exam', { initSubject: 'initSubject' }),
    ...mapActions('tagsView', { delCurrentView: 'delCurrentView' })
  },
  computed: {
    ...mapGetters([
      'userId'
    ]),
    ...mapGetters('enumItem', [
      'enumFormat'
    ]),
    ...mapState('enumItem', {
      levelEnum: state => state.user.levelEnum,
      resourceEnum: state => state.course.resourceEnum
    }),
    ...mapState('exam', { subjects: state => state.subjects })
  }
}
</script>
