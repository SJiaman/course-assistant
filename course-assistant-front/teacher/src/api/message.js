import { get,post } from '@/utils/request'

export default {
  pageList: query => get('/api/message/page', query),
  send: query => post('/api/message/save', query)
}
