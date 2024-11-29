<template>
  <RouterLink :to="{name:'matchList'}">
    <button>主页</button>
  </RouterLink>
  <div>
    <h1>注册</h1>
    用户名：<input type="text" placeholder="请输入用户名" v-model="username">
    <br>
    邮箱：<input type="text" placeholder="请输入QQ邮箱" v-model="email">
    <br>
    密码：<input type="text" placeholder="请输入密码" v-model="password">
    <br>
    验证码：<input type="text" placeholder="请输入验证码" v-model="captcha">
    <input type="button" @click="sendCaptcha" v-model="btnValue" :disabled="!isSend">
    <br>
    <button @click="register">注册</button>
  </div>
</template>

<script setup lang="ts">
import {ref} from 'vue'
import axios from "axios";
import {RouterLink} from "vue-router";
import router from "@/router";
import http from "@/api";
import {ElMessage} from "element-plus";
//数据
let username = ref();
let email = ref();
let password = ref();
let captcha = ref();
let btnValue = ref("发送验证码")
let isSend = ref(true)
//方法
const sendCaptcha = async () => {
  isSend.value = !isSend.value
  let time = 30
  btnValue.value = `${time}s`
  const timer = setInterval(()=>{
    time--
    btnValue.value = `${time}s`
    if(time === 0){
      clearInterval(timer)
      btnValue.value = '发送验证码'
      isSend.value = !isSend.value
    }
  },1000)
  console.log(email.value);
  try {
    const response = await http.get('/email', {
      params: {
        email: email.value
      }
    });
    console.log(response.data)
    if (response.data.code === 1) {
      ElMessage({
        message: '验证码已发送',
        type: 'success',
      })
    } else {
      clearInterval(timer)
      btnValue.value = '发送验证码'
      isSend.value = !isSend.value
      ElMessage({
        message: '请检查邮箱',
        type: 'error',
      })
    }
  } catch (error) {
    console.log(error)
  }
}

const register = async () => {
  try {
    let data = {
      username: username.value,
      email: email.value,
      password: password.value,
      captcha: captcha.value
    }
    const response = await http.post('/post/register', data);
    if (response.data.code === 1) {
      router.replace("/post/login")
      ElMessage({
        message: '注册成功，请登录',
        type: 'success',
      })
    } else {
      ElMessage({
        message: '登录失败，请检查邮箱和验证码',
        type: 'error',
      })
    }
  } catch (error) {
    console.log(error)
  }
}
</script>

<style scoped>

</style>