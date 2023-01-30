<template>
  <div style="margin-top: 10px" class="app-contain">
    <el-row :gutter="50">
      <el-col :span="7">
        <el-card>
          <div slot="header" class="clearfix">
            <span>个人信息</span>
          </div>
          <el-row style="text-align: center">
            <el-upload action="/api/student/upload/image"  accept=".jpg,.png" :show-file-list="false"  :on-success="uploadSuccess">
              <el-avatar class="el-dropdown-avatar" :size="100" :src="form.imagePath === null ? require('@/assets/avatar.png') : form.imagePath"></el-avatar>
            </el-upload>
          </el-row>
          <el-row class="user-info-userName">
            <label>{{form.username}}</label>
          </el-row>
          <el-divider/>
          <el-row class="user-info-fullInfo">
            <label>姓名：{{form.realName}}</label><br/>
            <label>班级：{{form.className}}</label><br/>
            <label>注册时间：{{form.createTime}}</label><br/>
          </el-row>
        </el-card>
      </el-col>
      <el-col :span="17">
        <el-card shadow="hover">
          <el-tabs active-name="event" type="card">
            <el-tab-pane label="用户动态" name="event">
              <div class="block">
                <el-timeline>
                  <el-timeline-item  :timestamp="item.createTime" placement="top" :key="item.id" v-for="item in event">
                    <el-card>
                      <p>{{item.content}}</p>
                    </el-card>
                  </el-timeline-item>
                </el-timeline>
              </div>
            </el-tab-pane>
            <el-tab-pane label="个人资料修改" name="update">
              <el-form :model="form" ref="form" label-width="100px" v-loading="formLoading" :rules="rules">
                <el-form-item label="真实姓名：" prop="realName" required>
                  <el-input v-model="form.realName"></el-input>
                </el-form-item>
                <el-form-item label="年龄：">
                  <el-input v-model="form.age"></el-input>
                </el-form-item>
                <el-form-item label="性别：">
                  <el-select v-model="form.gender" placeholder="性别" clearable>
                    <el-option v-for="item in sexEnum" :key="item.key" :value="item.key"
                               :label="item.value"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="出生日期：">
                  <el-date-picker v-model="form.birthday" value-format="yyyy-MM-dd" type="date" placeholder="选择日期"/>
                </el-form-item>
                <el-form-item label="手机：" prop="phone">
                  <el-input v-model="form.phone"></el-input>
                </el-form-item>
                <el-form-item label="学院：" prop="college">
                  <el-input v-model="form.college"></el-input>
                </el-form-item>
                <el-form-item label="班级：" prop="className">
                  <el-input v-model="form.className"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="submitForm">更新</el-button>
                </el-form-item>
              </el-form>
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import userApi from '@/api/user'
import { mapGetters, mapState } from 'vuex'

export default {
  data () {
    return {
      event: [],
      form: {
        username: '',
        realName: '',
        age: 0,
        gender: 0,
        birthday: '',
        phone: '',
        college: '',
        className: '',
        imagePath: ''
      },
      formLoading: false,
      rules: {
        realName: [
          { required: true, message: '请输入真实姓名', trigger: 'blur' }
        ],
        userLevel: [
          { required: true, message: '请选择年级', trigger: 'change' }
        ]
      }
    }
  },
  created () {
    console.log(this.studentUserId)
    let _this = this
    userApi.getCurrentUser(this.studentUserId).then(re => {
      _this.form = re.data
    })
    // _this.form = this.userInfo
  },
  methods: {
    uploadSuccess (re, file) {
      if (re.code === 1) {
        window.location.reload()
      } else {
        this.$message.error(re.message)
      }
    },
    submitForm () {
      let _this = this
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.formLoading = true
          userApi.update(this.form).then(data => {
            if (data.code === 0) {
              _this.$message.success(data.msg)
            } else {
              _this.$message.error(data.msg)
            }
            _this.formLoading = false
          }).catch(e => {
            _this.formLoading = false
          })
        } else {
          return false
        }
      })
    },
    levelFormatter (level) {
      return this.enumFormat(this.levelEnum, level)
    }
  },
  computed: {
    ...mapGetters('enumItem', [
      'enumFormat'
    ]),
    ...mapState('user', ['userInfo', 'studentUserId']),
    ...mapState('enumItem', {
      sexEnum: state => state.user.sexEnum,
      levelEnum: state => state.user.levelEnum
    })
  }
}
</script>

<style lang="scss" scoped>

</style>
