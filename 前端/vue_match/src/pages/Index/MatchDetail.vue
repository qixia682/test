<script setup lang="ts">
import {useRoute} from 'vue-router'
import axios from "axios";
import {onBeforeMount, reactive, ref, toRefs} from 'vue'
import router from "@/router";
import http from "@/api";
const route = useRoute()

let matchDetail = reactive({})
const getMatchDetail = async ()=>{
  try {
    const response = await http.get(`/matchdetail/${route.params.id}`);
    Object.assign(matchDetail, response.data.data)
  } catch (err) {
    console.log(err)
  }
}
getMatchDetail()

const lastPage = ()=>{
  router.replace("/index/matchlist")
}
</script>

<template>
  <button @click="lastPage">上一页</button>
  <ul>
    <li v-for="(value, name) in matchDetail">
      {{ name }}: {{ value }}
    </li>
  </ul>
</template>

<style scoped>

</style>