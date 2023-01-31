import { post, get } from '@/utils/request'

export default {
  getCoursePageList: query => get('/api/course/page', query),
  getCourseByUserId: query => get('/api/course/student/user', query),
  getUserEventPageList: query => post('/api/admin/user/event/page/list', query),
  joinCourse: query => post('/api/course/student/joinCourse', query),
  selectCourse: id => get('/api/course/' + id),
  getCurrentUser: () => post('/api/admin/user/current'),
  updateCourse: query => post('/api/course/update', query),
  changeStatus: id => post('/api/admin/user/changeStatus/' + id),
  deleteCourse: id => post('/api/course/delete/', [id]),
  selectByUserName: query => post('/api/admin/user/selectByUserName', query),
  resourceList: query => get('/api/course/resource/page', query),
}
