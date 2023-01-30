import { post, get } from '@/utils/request'

export default {
  createUser: query => post('/api/student/user/edit', query),
  getCurrentUser: query => get('/api/user/' + query),
  getUserEvent: () => post('/api/student/user/log'),
  update: query => post('/api/user/update', query),
  messagePageList: query => post('/api/student/user/message/page', query),
  read: id => post('/api/student/user/message/read/' + id),
  getMessageCount: () => post('/api/student/user/message/unreadCount')
}
