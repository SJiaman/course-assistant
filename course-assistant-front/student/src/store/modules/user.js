import Cookies from 'js-cookie'
import userApi from '@/api/user'
// initial state
const state = {
  userName: Cookies.get('studentUserName'),
  userInfo: Cookies.get('studentUserInfo'),
  studentUserId: Cookies.get('studentUserId'),
  imagePath: Cookies.get('studentImagePath'),
  messageCount: 0
}

// actions
const actions = {
  initUserInfo ({ commit }, id) {
    userApi.getCurrentUser(id).then(re => {
      commit('setUserInfo', re.data)
    })
  },
  getUserMessageInfo ({ commit }) {
    userApi.getMessageCount().then(re => {
      commit('setMessageCount', re.response)
    })
  }
}

// mutations
const mutations = {
  setUserName (state, userName) {
    state.userName = userName
    Cookies.set('studentUserName', userName, { expires: 30 })
  },
  setUserId (state, userId) {
    state.studentUserId = userId
    Cookies.set('studentUserId', userId, { expires: 60 * 24 })
  },
  setUserInfo: (state, userInfo) => {
    state.userInfo = userInfo
    Cookies.set('studentUserInfo', userInfo, { expires: 30 })
  },
  setImagePath: (state, imagePath) => {
    state.imagePath = imagePath
    Cookies.set('studentImagePath', imagePath, { expires: 30 })
  },
  setMessageCount: (state, messageCount) => {
    state.messageCount = messageCount
  },
  messageCountSubtract: (state, num) => {
    state.messageCount = state.messageCount - num
  },
  clearLogin (state) {
    Cookies.remove('studentUserName')
    Cookies.remove('studentUserInfo')
    Cookies.remove('studentImagePath')
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
