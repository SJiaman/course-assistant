import { post, get } from '@/utils/request'

export default {
  getCoursePageList: query => get('/api/course/page', query),
  getUserEventPageList: query => post('/api/admin/user/event/page/list', query),
  createCourse: query => post('/api/course/save', query),
  selectCourse: id => get('/api/course/' + id),
  getCurrentUser: () => post('/api/admin/user/current'),
  updateCourse: query => post('/api/course/update', query),
  changeStatus: id => post('/api/admin/user/changeStatus/' + id),
  deleteCourse: id => post('/api/course/delete/', [id]),
  selectByUserName: query => post('/api/admin/user/selectByUserName', query)
}
