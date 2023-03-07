import { get,post } from '@/utils/request'

export default {
  questionPageList: query => get('/api/question/page', query),
  edit: query => post('/api/question/save', query),
  update: query => post('/api/question/update', query),
  select: id => get('/api/question/' + id),
  deleteQuestion: id => post('/api/question/delete',  [id])
}
