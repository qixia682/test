import {defineStore} from 'pinia'
import type {User} from "@/types/user";
// 定义并暴露一个store
export const useUserStore = defineStore('userStore',{
  // 动作
  actions: {
    setUser(userData:User) {
      this.user = userData; // 更新 user 对象
    }
  },
  // 状态
  state(){
    return {
      user:{} as User
    }
  },
  // 计算
  getters:{}
})