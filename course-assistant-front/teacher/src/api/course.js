import { post, get } from '@/utils/request'

export default {
  getCoursePageList: query => get('/api/course/page', query),
  getCourseStudentList: id => get('/api/course/student/student/' + id),
  getCourseStudentByName: courseName => get('/api/course/student/byCourseId',  courseName),
  createCourse: query => post('/api/course/save', query),
  selectCourse: id => get('/api/course/' + id),
  updateCourse: query => post('/api/course/update', query),
  changeStatus: id => post('/api/admin/user/changeStatus/' + id),
  deleteCourse: id => post('/api/course/delete/', [id]),
  selectByUserName: query => post('/api/admin/user/selectByUserName', query),
  deleCourseStudent: id => post('/api/course/student/delete', [id])
}
