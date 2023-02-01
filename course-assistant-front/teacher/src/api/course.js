import { post, get } from '@/utils/request'

export default {
  getCoursePageList: query => get('/api/course/page', query),
  getCourseStudentList: query => get('/api/course/student/student',  query),
  getCourseStudentByName: courseName => get('/api/course/student/byCourseId',  courseName),
  createCourse: query => post('/api/course/save', query),
  selectCourse: id => get('/api/course/' + id),
  updateCourse: query => post('/api/course/update', query),
  changeStatus: id => post('/api/admin/user/changeStatus/' + id),
  deleteCourse: id => post('/api/course/delete/', [id]),
  selectByUserName: query => post('/api/admin/user/selectByUserName', query),
  deleCourseStudent: id => post('/api/course/student/delete', [id]),
  saveResource: query => post('/api/course/resource/save', query),
  resourceList: query => get('/api/course/resource/page', query),
  selectResource: query => get('/api/course/resource/' + query),
  selectStudent: query => get('/api/course/student/random', query),
  saveScore: query => post('/api/demo/coursestudentscore/save', query),
}
