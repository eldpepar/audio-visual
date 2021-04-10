import Vue from 'vue'
import App from './App.vue'
import router from './router'

// 饿了么ui
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

//网络相关
import axios from 'axios';
Vue.prototype.$axios = axios;

//cookie相关
import cookie from 'vue-cookie'
Vue.prototype.$cookie = cookie;

import leftMenu from './layout/left-menu'

import topHeader from './layout/top-header'

Vue.config.productionTip = false
axios.defaults.withCredentials = true


//注册组件
Vue.component('leftMenu', leftMenu)
Vue.component('topHeader', topHeader)
Vue.use(ElementUI)
Vue.use(cookie)


//登录的判断
router.beforeEach((to, from, next) => {

  if (to.path === '/login' || to.path === '/forbidden') {
    next()
  } else {
    const default_uuid = cookie.get('cookie-token-key')
    if (default_uuid === null) {
               next({
             path: '/login'
           })
    } else {
       axios.get("/user/check-token").then(
      (response) => {
        let res = response.data
        /*  console.log(res) */
        if (res.code === 20000) {
          if (res.data.roles === 'role_admin' || res.data.roles === 'role_root') {
            next()
          } else {
            next({
              path: '/forbidden'
            })
          }
        } else {
          next({
            path: '/login'
          })
        }
      },
      (response) => {
        console.log("error");
      }
    )
        console.log(default_uuid)
    }
  }
})

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
