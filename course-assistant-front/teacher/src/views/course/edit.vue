<template>
  <div class="app-container">

    <el-form :model="form" ref="form" label-width="100px" v-loading="formLoading">
      <el-form-item label="课程名："  prop="name" required>
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="课程口令：" prop="command" required>
        <el-input v-model="form.command"></el-input>
      </el-form-item>
      <el-form-item label="人数：" prop="count" required>
        <el-input v-model="form.count"></el-input>
      </el-form-item>
      <el-form-item label="状态：" required>
        <el-select v-model="form.status" placeholder="状态">
          <el-option v-for="item in statusEnum" :key="item.key" :value="item.key" :label="item.value"></el-option>
        </el-select>
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
        name: '',
        command: '',
        count: '',
        teacherId: '',
        status: 0
      },
      formLoading: false
    }
  },
  created () {
    let id = this.$route.query.id
    let _this = this
    if (id && parseInt(id) !== 0) {
      _this.formLoading = true
      courseApi.selectUser(id).then(re => {
        _this.form = re.response
        _this.formLoading = false
      })
    }
  },
  methods: {
    submitForm () {
      let _this = this
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.formLoading = true
          courseApi.createCourse(this.form).then(data => {
            if (data.code === 0) {
              _this.$message.success(data.message)
              _this.delCurrentView(_this).then(() => {
                _this.$router.push('/course/list')
              })
            } else {
              _this.$message.error(data.message)
              _this.formLoading = false
            }
          }).catch(e => {
            _this.formLoading = false
          })
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
        name: '',
        command: '',
        count: '',
        teacherId: 1,
        status: 1
      }
      this.form.id = lastId
    },
    ...mapActions('tagsView', { delCurrentView: 'delCurrentView' })
  },
  computed: {
    ...mapGetters('enumItem', [
      'enumFormat'
    ]),
    ...mapState('enumItem', {
      statusEnum: state => state.course.statusEnum
    })
  }
}
</script>
