import { post, postWithLoadTip } from '@/utils/request'

export default {
  login: query => postWithLoadTip(`/api/login`, query),
  logout: query => post(`/api/logout`, query)
}
