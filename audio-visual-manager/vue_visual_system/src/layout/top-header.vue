<template>
  <div>
    <div class="header-left-box">
      <div class="header-logo">光子影音后台管理系统</div>
    </div>

    <div class="header-right-box" @click="doLogout" style="cursor:pointer">
      <i class="el-icon-switch-button"  ></i>
      <span>退出</span>
    </div>
  </div>
</template>

<script>

export default {
  data() {
    return {};
  },
  methods: {
    doLogout() {
      this.$axios.get("/user/logout").then(
        (response) => {
          let res = response.data;
          /*  console.log(res) */
          if (res.code === 20000) {
            this.$message({
              message: "退出登录成功",
              center: true,
              type: "success"
            });
             this.$router.push("/login");
          }
        },
        (response) => {
          console.log("error");
        }
      );
    },
  },
};
</script>

<style >
.header-left-box {
  color: #fff;
  font-size: 20px;
  font-weight: 600;
  float: left;
}

.header-right-box {
   float: right;
   color: #fff;
  font-size: 14px;
}
</style>