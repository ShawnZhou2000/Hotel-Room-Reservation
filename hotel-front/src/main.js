import Vue from 'vue'
import App from './App.vue'
import store from './store'
import axios from 'axios'
import VueAxios from 'vue-axios'
import './plugins/element.js'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import VueRouter from 'vue-router';
import VueWechatTitle from 'vue-wechat-title';
import router from './router';
import './assets/css/custom.css';
import feather from 'feather-icons';

axios.defaults.withCredentials = true;

Vue.config.productionTip = false;
Vue.use(ElementUI, { size: 'small', zIndex: 3000 });
Vue.use(VueRouter);
Vue.use(VueWechatTitle);
Vue.use(feather);
Vue.use(VueAxios, axios);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
