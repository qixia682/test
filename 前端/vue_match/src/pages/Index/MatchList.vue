<template>
  <div class="matchList">
    <!-- 分页控件 -->
    <el-row>
      <el-col :span="8">
        <select v-model="pageSize">
          <option value="5">5</option>
          <option value="10">10</option>
          <option value="15">15</option>
        </select>
        <button @click="previousPage" :disabled="currentPage <= 1">上一页</button>
        <span>第 {{ currentPage }} 页 / 共 {{ totalPages }} 页</span>
        <button @click="nextPage" :disabled="currentPage >= totalPages">下一页</button>
        <span>总共{{ totalMatch }}条数据</span>
      </el-col>
      <el-col :span="16">
        <input type="text" v-model="matchKeyValue" style="display: inline-block">

        <div style="display: inline-block">
          <el-date-picker
              v-model="begin"
              type="date"
              placeholder="选择开始时间"
              format="YYYY-MM-DD"
              value-format="YYYY-MM-DD"
          />
        </div>

        <el-date-picker
            v-model="end"
            type="date"
            placeholder="选择结束时间"
            format="YYYY-MM-DD"
            value-format="YYYY-MM-DD"
        />
        <button @click="matchKeyValue='';begin='';end='';getMatchList()">清空</button>
        <button @click="getMatchList" style="display: inline-block">搜索</button>
      </el-col>
    </el-row>
  </div>
  <!--  比赛列表  -->
  <div v-if="matchList.length" v-for="match in matchList" :key="match.id" class="matchDetail">
    <el-row :gutter="20">
      <el-col :span="4">
        <div style="background-color: aqua"><img src="" alt=""></div>
      </el-col>
      <el-col :span="16">
        {{ match.name }}
      </el-col>
      <el-col :span="4">
        <RouterLink :to="{name:'matchDetail',params:{id:match.id}}">
          <button @click="isLogin">详情</button>
        </RouterLink>
      </el-col>
    </el-row>
  </div>
  <div v-else>
    没有相关比赛
  </div>

</template>

<script lang="ts" setup>
import {RouterLink, RouterView} from 'vue-router'
import {onMounted, reactive, ref, watch} from 'vue'
import axios from "axios"
import {type MatchList} from '@/types/match'
import router from "@/router";
import http from "@/api";
import {ElMessage} from "element-plus";
//数据w
let matchList = reactive<MatchList>([])
// let matchList = ref([])
const totalMatch = ref(); //总共数据数
const currentPage = ref(1); // 当前页码
const pageSize = ref(10); // 每页显示的条数
const totalPages = ref(0); // 总页数
const matchKeyValue = ref(); //查询关键字
const begin = ref();
const end = ref();

const isLogin = ()=>{
  if(!localStorage.getItem('jwt')){
    ElMessage({
      message: '请先登录',
      type: 'error',
    })
  }
}

//方法

//请求数据
const getMatchList = async () => {
  try {
    const response = await http('/match', {
      params: {
        page: currentPage.value,
        pageSize: pageSize.value,
        matchKeyValue: matchKeyValue.value,
        begin: begin.value ? begin.value : '',
        end: end.value ? end.value : ''
      }
    });
    totalPages.value = Math.ceil(response.data.data.total / pageSize.value);
    totalMatch.value = response.data.data.total
    matchList.length = 0
    matchList.push(...response.data.data.rows)
  } catch (err) {
    console.log(err)
  }
};
//根据每页数量变化
watch(pageSize, () => {
  getMatchList()
}, {immediate: true})

// 上一页
const previousPage = () => {
  if (currentPage.value > 1) {
    currentPage.value--;
    getMatchList();
  }
};

// 下一页
const nextPage = () => {
  if (currentPage.value < totalPages.value) {
    currentPage.value++;
    getMatchList();
  }
};
// let {begin, end} = useSiftMatch()
</script>

<style scoped>

</style>