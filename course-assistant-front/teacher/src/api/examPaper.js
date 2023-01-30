import { get,post } from '@/utils/request'

export default {
  pageList: query => get('/api/exam/page', query),
  taskExamPage: query => post('/api/admin/exam/paper/taskExamPage', query),
  edit: query => post('/api/exam/save', query),
  select: id => get('/api/exam/' + id),
  deletePaper: id => post('/api/exam/delete', id)
}
