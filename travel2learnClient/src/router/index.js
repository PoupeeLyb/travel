import Vue from 'vue';
import VueRouter from 'vue-router';
import FindKey from '@/views/FindKey.vue';
import Home from '@/views/HomeView.vue'; // 导入Home组件
import Diary from '@/views/DiaryView.vue'; // 导入Blog组件
import AI from '@/views/AIView.vue'; // 导入AI组件
import NavBar from '@/components/NavBar.vue';
import Login from '@/views/LoginView.vue';
import Register from '@/views/RegisterView.vue';
import Map from '@/views/MapView.vue';
import Scene from '@/views/SceneView.vue'; // 确保已正确导入SceneView组件

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'login',
    component: Login
  },
  {
    path: '/register',
    name: 'register',
    component: Register
  },
  {
    path: '/findkey',
    name: 'findkey',
    component: FindKey
  },
  {
    path: '/home',
    name: 'home',
    components: {
      default: Home,
      navbar: NavBar
    }
  },
  {
    path: '/diary',
    name: 'diary',
    components: {
      default: Diary,
      navbar: NavBar
    }
  },
  {
    path: '/ai',
    name: 'ai',
    components: {
      default: AI,
      navbar: NavBar
    }
  },
  {
    path: '/map',
    name: 'map',
    components: {
      default: Map,
      navbar: NavBar
    }
  },
  {
    path: '/scene/:id', // 添加新的路由，用于景点详情页，并接收一个id参数
    name: 'scene',
    components: {
      default: Scene,
      navbar: NavBar
    },
    props: {
      default: true // 启用props，使得组件可以通过props接收路由参数
    }
  }
];

const router = new VueRouter({
  mode: 'history', // 使用HTML5 History模式
  routes
});

export default router;
