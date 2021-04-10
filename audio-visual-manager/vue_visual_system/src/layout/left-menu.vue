<template>
  <div>
    <el-menu default-active="0" class="el-menu-vertical" :unique-opened="true" background-color="#262d47">
      <!-- 遍历内容菜单 -->

      <!-- 分两种，有子菜单和没有子菜单 -->
      <template v-for="(item, index) in menuList">
        <router-link v-if="!item.children && !item.hidden"  :key="index" :to="item.path">
          <el-menu-item :index="index + ''">
            <i :class="item.icon"></i>
            <span slot="title" style="color:#ffffff">{{ item.name }}</span>
          </el-menu-item>
        </router-link>

        <el-submenu
          :index="index + ''"
          v-if="item.children && !item.hidden"
          :key="index">
          <template slot="title">
            <i :class="item.icon"></i>
            <span style="color:#ffffff">{{ item.name }}</span>
          </template>

          <router-link
            v-for="(subItem, subIndex) in item.children"
            :key="subIndex"
            :to="item.path + '/' + subItem.path">
            <el-menu-item
              :index="index + '-' + subIndex + ''"
              v-if="!subItem.hidden">
              <i :class="subItem.icon"></i>
              <span slot="title" v-text="subItem.name" style="color:#ffffff"> </span>
            </el-menu-item>
          </router-link>
        </el-submenu>
      </template>
    </el-menu>
  </div>
</template>

<script>
import { routes } from "../router";
import { async } from "../router";
export default {
  data() {
    return {
      menuList: [],
    };
  },
  mounted() {
    this.$axios.get("/user/check-token").then((response) => {
      let res = response.data;
      if (res.code === 20000) {
        if (res.data.roles === "role_root") {
          console.log("是超级管理员");
          let menuList = async[0];
          this.menuList = menuList.children;
        } else {
          console.log("是普通管理员");
          let menuList = routes[0];
          this.menuList = menuList.children;

        }
      }
    }), (response) => {console.log("error");};
  },
};
</script>

<style>
.el-menu-vertical a {
  text-decoration: none;
}
 
</style>