import { get,post } from '@/utils/request'

export default {
  list:  query => get('/api/course/list', query),
  pageList: query => post('/api/admin/education/subject/page', query),
  edit: query => post('/api/admin/education/subject/edit', query),
  select: id => post('/api/admin/education/subject/select/' + id),
  deleteSubject: id => post('/api/admin/education/subject/delete/' + id)
}
