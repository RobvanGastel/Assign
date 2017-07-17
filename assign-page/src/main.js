// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Meta from 'vue-meta'

Vue.use(Meta)

import App from './App'
import router from './router'

import './assets/css/bootstrap.min.css'
import './assets/css/style.css'

Vue.config.productionTip = false

// API URL
Vue.prototype.$apiurl = process.env.API_URL

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})
