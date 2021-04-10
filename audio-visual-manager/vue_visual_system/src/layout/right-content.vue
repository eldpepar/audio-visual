<template>
  <div id>
    <!-- 显示我们自己的内容 -->
    <div class="content-title-part" v-text="$route.name"></div>

    <div class="content-container-box">
      <router-view></router-view>
    </div>
  </div>
</template>

<style>
.content-title-part {
  padding: 10px;
  border-bottom: solid #e6e6e6 1px;
   color: #e6e6e6;
   font-weight: bold;
}

.content-container-box {
  color: #e6e6e6;
  padding: 10px;
}
</style>

<script>
</script>