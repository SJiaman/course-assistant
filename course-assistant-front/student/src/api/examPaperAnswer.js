import { get,post } from '@/utils/request'

export default {
  pageList: query => get('/api/exam/record/list', query),
  answerSubmit: form => post('/api/exam/record/do', form),
  read: id => get('/api/exam/record/' + id),
  edit: form => post('/api/student/exampaper/answer/edit', form)
}
