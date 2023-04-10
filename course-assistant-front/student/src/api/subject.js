import { get,post } from '@/utils/request'

export default {
  list: query => get('/api/ccourse/student/user', + query),
  select: id => post('/api/course/student/user' + id)
}
