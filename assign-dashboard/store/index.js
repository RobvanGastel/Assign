import axios from 'axios'

export const state = () => ({
  sidebar: false,
  authUser: null
})

export const mutations = {
  toggleSidebar (state) {
    state.sidebar = !state.sidebar
  },
  SET_USER: function (state, user) {
    state.authUser = user
  }
}

export const actions = {
  nuxtServerInit ({ commit }, { req }) {
    if (req.session && req.session.authUser) {
      commit('SET_USER', req.session.authUser)
    }
  },
  login ({ commit, redirect }, { username, password }) {
    return axios.post('https://api.assignapp.nl/v0.1/auth?email=' + username + '&password=' + password)
    .then((res) => {
      commit('SET_USER', res.data)
    })
    .catch((error) => {
      if (error.response.status === 401) {
        throw new Error('Bad credentials')
      }
    })
  },

  logout ({ commit }) {
    return axios.post('/api/logout')
    .then(() => {
      commit('SET_USER', null)
    })
  }
}