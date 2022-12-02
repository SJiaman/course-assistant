import { post, get } from '@/utils/request'

export default {
  getCoursePageList: query => get('/api/course/page', query),
  getUserEventPageList: query => post('/api/admin/user/event/page/list', query),
  createCourse: query => post('/api/course/save', query),
  selectUser: id => post('/api/admin/user/select/' + id),
  getCurrentUser: () => post('/api/admin/user/current'),
  updateUser: query => post('/api/admin/user/update', query),
  changeStatus: id => post('/api/admin/user/changeStatus/' + id),
  deleteUser: id => post('/api/admin/user/delete/' + id),
  selectByUserName: query => post('/api/admin/user/selectByUserName', query)
}
