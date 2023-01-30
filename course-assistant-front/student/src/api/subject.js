import { get,post } from '@/utils/request'

export default {
  list: query => get('/api/course/list'),
  select: id => post('/api/student/education/subject/select/' + id)
}
