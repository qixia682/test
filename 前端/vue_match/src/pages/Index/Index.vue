<template>
  <div>
    <el-container>
      <el-header style="background-color: bisque">
        <Header/>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <Menu/>
        </el-aside>
        <el-main>
          <RouterView></RouterView>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import Menu from "@/components/Menu.vue";
import Header from "@/components/Header.vue";
import {RouterView} from "vue-router";

</script>

<style scoped>

</style>