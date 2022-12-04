import Cookies from 'js-cookie'
import userApi from '@/api/user'
// initial state
const state = {
  userName: Cookies.get('adminUserName'),
  userInfo: Cookies.get('adminUserInfo'),
  userId: Cookies.get('adminUserId')
}

// actions
const actions = {
  initUserInfo ({ commit }) {
    userApi.getCurrentUser().then(re => {
      commit('setUserInfo', re.response)
    })
  }
}

// mutations
const mutations = {
  setUserName (state, userName) {
    state.userName = userName
    Cookies.set('adminUserName', userName, { expires: 60 * 24 })
  },
  setUserId (state, userId) {
    state.userId = userId
    Cookies.set('adminUserId', userId, { expires: 60 * 24 })
  },
  setUserInfo: (state, userInfo) => {
    state.userInfo = userInfo
    Cookies.set('adminUserInfo', userInfo, { expires: 60 * 24 })
  },
  clearLogin (state) {
    Cookies.remove('adminUserName')
    Cookies.remove('adminUserInfo')
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
