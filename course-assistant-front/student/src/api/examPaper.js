import { get,post } from '@/utils/request'

export default {
  select: id => get('/api/exam/' + id),
  pageList: query => get('/api/exam/page', query)
}
