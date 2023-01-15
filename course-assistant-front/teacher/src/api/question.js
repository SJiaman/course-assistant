import { get,post } from '@/utils/request'

export default {
  questionPageList: query => get('/api/question/page', query),
  edit: query => post('/api/question/save', query),
  select: id => post('/api/admin/question/select/' + id),
  deleteQuestion: id => post('/api/admin/question/delete/' + id)
}
