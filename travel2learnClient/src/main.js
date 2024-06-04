import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import VueAMap from 'vue-amap'; // 引入vue-amap
import Vuex from 'vuex';
import store from './store'

import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);
Vue.use(VueAMap); // 使用VueAMap
Vue.use(Vuex);

// 初始化高德地图
VueAMap.initAMapApiLoader({
  key: '42884872723269d3d6d2e0faaf62afb4', // 替换为你的API密钥
  plugin: ['AMap.Autocomplete', 'AMap.PlaceSearch', 'AMap.Scale', 'AMap.OverView', 'AMap.ToolBar', 'AMap.MapType', 'AMap.PolyEditor', 'AMap.CircleEditor'],
  v: '1.4.4' // 高德地图版本
});

Vue.config.productionTip = false

new Vue({
  router,
  store,  
  render: h => h(App)
}).$mount('#app')
