<template>
  <div>
    <h1 style="display: inline">
      抱歉，这个页面无法访问，请检查是否登录，或者检查权限
    </h1>
    <h1 style="display: inline"><font color="red"><a @click="toLogin">跳转到登录页面</a></font></h1>
  </div>
</template>

<script>
export default {
  methods: {
    //跳转页面

    toLogin() {
      this.$router.push("/login/login");
    },
  },
};
</script>

<style >
html,
body {
  padding: 0;
  margin: 0;
  height: 100%;
}
.box {
  width: 100%;
  height: 100%;
  background-color: wheat;
  text-align: center; /*文本水平居中*/
  line-height: 600px; /*文本垂直居中*/
}
</style>