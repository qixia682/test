<script setup lang="ts">
import {RouterLink, RouterView} from 'vue-router'
import Header from "@/components/Header.vue";
import Menu from "@/components/Menu.vue";

</script>

<template>
  <div>
    <el-container>
      <el-header style="background-color: bisque">
        <Header/>
      </el-header>
      <el-container>
        <RouterView/>
      </el-container>
    </el-container>
  </div>
</template>

<style scoped>

</style>