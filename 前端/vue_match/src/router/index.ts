import {createRouter, createWebHistory} from 'vue-router'
import MatchList from "@/pages/Index/MatchList.vue";
import MatchDetail from "@/pages/Index/MatchDetail.vue";
import Index from "@/pages/Index/Index.vue"
import Post from "@/pages/Post/Post.vue";
import Login from "@/pages/Post/Login.vue";
import Register from "@/pages/Post/Register.vue";
import UserDetail from "@/pages/Post/UserDetail.vue";
import Qita1 from "@/pages/Index/Qita1.vue";
import Qita2 from "@/pages/Index/Qita2.vue";

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/index',
      component: Index,
      children: [
        {
          name: 'matchList',
          path: 'matchlist',
          component: MatchList
        },
        {
          name: 'matchDetail',
          path: 'matchdetail/:id',
          component: MatchDetail,
          meta: { requiresAuth: true },
        },
        {
          name:'qiTa1',
          path: 'qita1',
          component: Qita1
        },
        {
          name:'qiTa2',
          path: 'qita2',
          component: Qita2
        }
      ]
    },
    {
      name: 'post',
      path: '/post',
      component: Post,
      children: [
        {
          name: 'login',
          path: 'login',
          component: Login
        },
        {
          name: 'register',
          path: 'register',
          component: Register
        }
      ]
    },
    {
      name:'userDetail',
      path:'/user',
      component:UserDetail,
      meta: { requiresAuth: true },
    },
    {
      path: '/',
      redirect: '/index/matchlist'
    }
  ]
})
router.beforeEach((to, from, next) => {
  const jwt = localStorage.getItem('isremember') == 'true'?
      localStorage.getItem('jwt') :
      sessionStorage.getItem('jwt')
  if (to.matched.some(record => record.meta.requiresAuth)) {
    // 如果路由需要认证
    if (!jwt) {
      // 如果没有JWT，则重定向到登录页面
      next({path: '/post/login'});
    } else {
      // 如果有JWT，则允许访问
      next();
    }
  } else if (to.matched.some(record => record.meta.isPublic)) {
    // 如果路由是公开的，直接放行
    next();
  } else {
    // 如果路由既不需要认证也不是公开的，直接放行或根据需要添加其他逻辑
    next();
  }
});
export default router