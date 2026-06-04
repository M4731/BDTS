export default {
  namespaced: true,

  state() {
    const savedUser = localStorage.getItem("currentUser");
    return {
      currentUser: savedUser ? JSON.parse(savedUser) : null
    }
  },

  getters: {
    currentUser(state) {
      return state.currentUser
    },

    isAuthenticated(state) {
      return state.currentUser !== null
    }
  },

  mutations: {
    SET_USER(state, user) {
      state.currentUser = user
      if (user) {
        localStorage.setItem("currentUser", JSON.stringify(user))
      }
    },

    LOGOUT(state) {
      state.currentUser = null
      localStorage.removeItem("currentUser")
    }
  }
}