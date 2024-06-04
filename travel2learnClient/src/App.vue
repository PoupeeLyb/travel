<template>
  <div id="app">
    <router-view name="navbar" /> 
    <router-view></router-view> <!-- 根据路由动态显示其他组件 -->
  </div>
</template>
<script>
// 这里不需要单独引入 NavBar，因为它通过路由来处理
export default {
  name: 'App'
}
</script>

<style>
/* 全局样式 */
html, body {
  height: 100%; /* 确保背景覆盖整个页面 */
  margin: 0; /* 去除默认边距 */
  background-image: url('@/assets/background1.jpg'); /* 替换为你的图片路径 */
  background-size: cover; /* 确保背景图片完全覆盖背景，不留空白 */
  background-position: center; /* 图片居中显示 */
  background-repeat: no-repeat; /* 避免背景图片重复 */
  background-attachment: fixed; /* 背景图片不随页面滚动而滚动 */
}
</style>