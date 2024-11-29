<template>
  <RouterLink :to="{name:'matchList'}">
    <button>主页</button>
  </RouterLink>
  <div>
    <h1>登录</h1>
    邮箱：<input type="text" placeholder="请输入邮箱" v-model="email"><br>
    密码：<input type="text" placeholder="请输入密码" v-model="password"><br>
    <input type="checkbox" v-model="isRemember">记住我<br>
    <button @click="login">登录</button>
  </div>
</template>

<script setup lang="ts">
import {RouterLink, RouterView, useRoute} from 'vue-router'
import axios from "axios";
import {onMounted, onUnmounted, ref, watch} from 'vue'
import router from "@/router";
import http from "@/api";
import {useUserStore} from '@/store/User'
import {ElMessage} from 'element-plus'

const route = useRoute()
let userStore = useUserStore()

let email = ref();
let password = ref();
let isRemember = ref(false);


const login = async () => {
  try {
    let data = {
      email: email.value,
      password: password.value
    }
    const response = await http.post('/post/login', data);
    if (response.data.code === 1){
      ElMessage({
        message: '登录成功',
        type: 'success',
      })
    }else{
      ElMessage({
        message: '登录失败，请检查邮箱和密码',
        type: 'error',
      })
    }
    //将jwt存入浏览器本地
    const {user, jwt} = response.data.data;
    //将user赋值给pinia管理
    userStore.setUser(user)
    localStorage.setItem('isremember',isRemember.value ? 'true' : 'false');
    if (isRemember.value){ //
      localStorage.setItem('user', JSON.stringify(user));
      localStorage.setItem('jwt', jwt);
    } else {
      sessionStorage.setItem('user', JSON.stringify(user))
      sessionStorage.setItem('jwt', jwt);
    }

    router.replace(`/index/matchlist`)
  } catch (error) {
    console.log(error)
  }
}
</script>


<style scoped>

</style>