import { get } from '@/utils/request'

export default {
  index: query => get('/api/index/teacher', query),

  recordCount: query => get('/api/index/teacher')
}
