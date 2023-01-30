import { post, get } from '@/utils/request'

export default {
  createUser: query => post('/api/student/user/edit', query),
  getCurrentUser: query => get('/api/user/' + query),
  getUserEvent: () => post('/api/student/user/log'),
  update: query => post('/api/user/update', query),
  messagePageList: query => get('/api/message/user', query),
  read: id => post('/api/message/readed/' + id),
  getMessageCount: () => post('/api/student/user/message/unreadCount')
}
