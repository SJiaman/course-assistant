import { get } from '@/utils/request'

export default {
  page: query => get('/api/exam/record/analysis', query)
}
