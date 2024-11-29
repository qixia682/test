<template>
  <div v-if="!isLogin">
    <RouterLink to="/post/login">登录</RouterLink>
    <RouterLink to="/post/register">注册</RouterLink>
  </div>
  <div v-else>
    <span>已登录</span>
    <img :src="user.img" alt="" height="40px">
    <span>{{ user.username }}</span>
    <RouterLink to="/user"><button>个人主页</button></RouterLink>
    <button @click="logout">退出登录</button>
  </div>
</template>

<script setup lang="ts">
import {RouterLink, RouterView} from 'vue-router'
import {ref, reactive, type Reactive, toRefs} from "vue";
import router from "@/router";
import {useUserStore} from "@/store/User";
const userStore = useUserStore()
let user = localStorage.getItem('isremember')=='true'?
    ref(JSON.parse(localStorage.getItem('user')!)) :
    ref(JSON.parse(sessionStorage.getItem('user')!))
let jwt = localStorage.getItem('isremember')=='true' ?
    localStorage.getItem('jwt') :
    sessionStorage.getItem('jwt')
console.log(jwt)

let isLogin = ref();
isLogin.value = !!jwt; //jwt ? true : false的简化

const logout = ()=>{
  if(localStorage.getItem('isremember')==='true'){
  } else {
    sessionStorage.clear()
  }
  localStorage.clear()
  router.replace('/index/matchList')
  location.reload();
}
</script>

<style scoped>

</style>