<template>
  <el-dialog :visible.sync="visible" :title="!dataForm.id ? $t('add') : $t('update')" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmitHandle()" :label-width="$i18n.locale === 'en-US' ? '120px' : '80px'">
          <el-form-item label="学生id" prop="studentId">
          <el-input v-model="dataForm.studentId" placeholder="学生id"></el-input>
      </el-form-item>
          <el-form-item label="课程id" prop="courseId">
          <el-input v-model="dataForm.courseId" placeholder="课程id"></el-input>
      </el-form-item>
          <el-form-item label="创建时间" prop="createTime">
          <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
      </el-form-item>
          <el-form-item label="修改时间" prop="modifyTime">
          <el-input v-model="dataForm.modifyTime" placeholder="修改时间"></el-input>
      </el-form-item>
          <el-form-item label="是否删除0:未删除1:删除" prop="deleted">
          <el-input v-model="dataForm.deleted" placeholder="是否删除0:未删除1:删除"></el-input>
      </el-form-item>
      </el-form>
    <template slot="footer">
      <el-button @click="visible = false">{{ $t('cancel') }}</el-button>
      <el-button type="primary" @click="dataFormSubmitHandle()">{{ $t('confirm') }}</el-button>
    </template>
  </el-dialog>
</template>

<script>
import debounce from 'lodash/debounce'
export default {
  data () {
    return {
      visible: false,
      dataForm: {
        id: '',
        studentId: '',
        courseId: '',
        createTime: '',
        modifyTime: '',
        deleted: ''
      }
    }
  },
  computed: {
    dataRule () {
      return {
        studentId: [
          { required: true, message: this.$t('validate.required'), trigger: 'blur' }
        ],
        courseId: [
          { required: true, message: this.$t('validate.required'), trigger: 'blur' }
        ],
        createTime: [
          { required: true, message: this.$t('validate.required'), trigger: 'blur' }
        ],
        modifyTime: [
          { required: true, message: this.$t('validate.required'), trigger: 'blur' }
        ],
        deleted: [
          { required: true, message: this.$t('validate.required'), trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    init () {
      this.visible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
        if (this.dataForm.id) {
          this.getInfo()
        }
      })
    },
    // 获取信息
    getInfo () {
      this.$http.get(`/demo/coursestudent/${this.dataForm.id}`).then(({ data: res }) => {
        if (res.code !== 0) {
          return this.$message.error(res.msg)
        }
        this.dataForm = {
          ...this.dataForm,
          ...res.data
        }
      }).catch(() => {})
    },
    // 表单提交
    dataFormSubmitHandle: debounce(function () {
      this.$refs['dataForm'].validate((valid) => {
        if (!valid) {
          return false
        }
        this.$http[!this.dataForm.id ? 'post' : 'put']('/demo/coursestudent/', this.dataForm).then(({ data: res }) => {
          if (res.code !== 0) {
            return this.$message.error(res.msg)
          }
          this.$message({
            message: this.$t('prompt.success'),
            type: 'success',
            duration: 500,
            onClose: () => {
              this.visible = false
              this.$emit('refreshDataList')
            }
          })
        }).catch(() => {})
      })
    }, 1000, { 'leading': true, 'trailing': false })
  }
}
</script>
