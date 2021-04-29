import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import Vue from "vue"
import {BootstrapVue, IconsPlugin} from  'bootstrap-vue'

import App from 'pages/App.vue'
import VueResource from 'vue-resource'
import router from 'router/router'

Vue.use (BootstrapVue)
Vue.use (IconsPlugin)
Vue.use(VueResource)

new Vue({
el: '#app',
router,
render: a => a(App)
}).$mount('#app')

