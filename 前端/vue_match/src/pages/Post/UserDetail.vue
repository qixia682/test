<template>
  <h1>个人主页</h1>
  <RouterLink to="index/matchlist">
    <button>主页</button>
  </RouterLink>
  <ul>
    <li v-for="(value,key) in user" :key="key"> <!--    v-show="key.toString()!=='id'&& key.toString()!=='captcha'-->
      {{ key }} -- {{ value ? value : "null" }}
    </li>
  </ul>
  头像：<img :src="img" alt="" width="50px"><br>
  <!--  弹出框-->
  <el-dialog
      v-model="dialogVisible"
      title="修改信息"
      width="500"
      :before-close="handleClose"
  >
    <p>用户名 -- <input type="text" :value="username"></p>
    <p>密码 -- <input type="text" :value="password"></p>
    <p>性别 --
      <label>
        <input type="radio" value="男" v-model="gender"> 男
      </label>
      <label>
        <input type="radio" value="女" v-model="gender"> 女
      </label>
      <label>
        <input type="radio" value="其他" v-model="gender"> 其他
      </label>
    </p>
    <p>年级 --
      <select name="" id="" v-model="grade">
        <option value="大一">大一</option>
        <option value="大二">大二</option>
        <option value="大三">大三</option>
        <option value="大四">大四</option>
      </select>
    </p>
    <p>生日 --
      <el-date-picker
          v-model="birthday"
          type="date"
          placeholder="Pick a Date"
          format="YYYY-MM-DD"
          value-format="YYYY-MM-DD"
      />
    </p>
    <p>头像 --
      <img :src="formImg" width="50px">
      <input type="file" @change="onFileChange">
    </p>
    <template #footer>
      <div>
        <el-button type="primary" @click="postForm">
          提交
        </el-button>
      </div>
    </template>
  </el-dialog>

  <button @click="dialogVisible = true">修改信息</button>
</template>

<script setup lang="ts">
import {RouterView, RouterLink} from 'vue-router'
import {useUserStore} from '@/store/User'
import {ref} from "vue";
import type {User} from "@/types/user";
import {ElMessage, ElMessageBox} from "element-plus";
import http from "@/api";

let user = ref<User>(JSON.parse(localStorage.getItem('isremember') == 'true'?
    localStorage.getItem('user')!:
    sessionStorage.getItem('user')!))

const id = ref(user.value.id)
const username = ref(user.value.username)
const password = ref(user.value.password)
const gender = ref(user.value.gender)
const grade = ref(user.value.grade)
const birthday = ref(user.value.birthday)
let img = ref(user.value.img)
let formImg = ref(img.value)
const dialogVisible = ref(false)


const onFileChange = async (event: Event) => {
  const file = (event.target as HTMLInputElement).files?.[0] as Blob;
  const data = new FormData()
  data.append("image", file);
  try {
    const response = await http.post('/upload',data)
    console.log(response.data.code)
    formImg.value = response.data.data
  } catch (error){
    console.log(error)
  }
};


const update = async ()=>{
  try {
    let data = {
      id:id.value,
      username:username.value ? username.value : '',
      password:password.value ? password.value : '',
      gender:gender.value ? gender.value : '',
      grade:grade.value ? grade.value : '',
      birthday:birthday.value ? birthday.value : '',
      img:formImg.value ? formImg.value : ''
    }
    const response = await http.post('/post/update', data)
    if (localStorage.getItem('isremember') === 'true'){
      localStorage.setItem('user',JSON.stringify(response.data.data))
    } else {
      sessionStorage.setItem('user',JSON.stringify(response.data.data))
    }
    ElMessage({
      message: '修改成功',
      type: 'success',
    })
    location.reload()
  } catch (error) {
    console.log(error)
  }
  dialogVisible.value = false
}

const handleClose = (done: () => void) => {
  ElMessageBox.confirm('你确定取消吗')
      .then(() => {
        done()
      })
      .catch(() => {
        // catch error
      })
}

const postForm = ()=>{
  ElMessageBox.confirm('你确定提交吗')
      .then(() => {
        update()
      })
      .catch(() => {
        // catch error
      })
}
</script>

<style scoped>

</style>