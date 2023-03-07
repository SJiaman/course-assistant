import { get,post } from '@/utils/request'

export default {
  pageList: query => get('/api/message/page', query),
  send: query => post('/api/message/save', query),
  delete: query => post('/api/message/delete', query),
  edit: query => post('/api/message/update', query),
  select: query => get('/api/message/'+ query)
}
