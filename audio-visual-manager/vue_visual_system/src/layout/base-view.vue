<template>
  <div>
    <el-container>
      <el-header id="admin-header-box">
          <topHeader></topHeader>
      </el-header>
      <el-container>
        <el-aside width="220px" id="left-menu-list-box">
          <div class="nav">
            <leftMenu></leftMenu>
          </div>
        </el-aside>
        <el-container>
          <el-main>
            <div class="content">
              <router-view></router-view>
            </div>
          </el-main>
          
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>

<style>
    #left-menu-list-box .el-menu {
       border-right: none;
    }

    .el-header {
      background-color: #223351;
      line-height: 46px;
      height: 46px !important;
    }

    .el-aside {
      border-right: solid 1px #150b50;
      line-height: 200px;
      background-color: #262d47;
    }

    .el-main {
      padding: 0;
       background-color: #171c30;
    }
</style>

<script>
  export default {
      mounted() {
        let leftMenuBox = document.getElementById('left-menu-list-box')
        let adminHeaderBox = document.getElementById('admin-header-box')
        let dy = window.innerHeight - adminHeaderBox.offsetHeight
        if (leftMenuBox && adminHeaderBox) {
            leftMenuBox.style.height = dy + 'px'
        }

      }
  }
</script>
