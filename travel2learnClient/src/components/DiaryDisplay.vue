<template>
    <el-row gutter="20">
      <el-col :span="24" v-for="item in lists" :key="item.name">
        <el-card class="el-card-custom">
          <div class="user-info">
            <el-avatar :src="item.avatarUrl" class="avatar"></el-avatar>
            <div class="name-and-time">
              <div>{{ item.username }}</div>
              <div>{{ item.time }}</div>
            </div>
          </div>
          <div v-if="item.imgUrls.length > 0">
            <div class="title">{{ item.title }}</div>
            <el-carousel height="300px">
              <el-carousel-item v-for="url in item.imgUrls" :key="url">
                <img :src="url" class="carousel-image" />
              </el-carousel-item>
            </el-carousel>
          </div>
          <div class="card-content">
            <span>{{ item.content }}</span>
            <div class="card-footer">
              <div class="icon-with-text">
                <i class="el-icon-view"></i>
                <span>{{ item.clicks }}</span>
              </div>
              <el-button type="text" class="button right-content">探索</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </template>
  
  <script>
  export default {
    props: {
      lists: Array
    }
  }
  </script>
  
  <style scoped>
.el-card-custom {
  width: 600px;
  margin: 20px auto;
  background-color: #ffffff60;
  border: 0px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
}

.el-card-custom:hover {
  transform: translateY(-10px) scale(1.05);
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.3);
}

.avatar {
  border-radius: 50%;
  /* 圆形头像 */
}

.user-info {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
  /* 在头像和用户名时间信息与走马灯之间添加空间 */
}

.name-and-time {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  margin-left: 10px;
  /* 头像和文字之间的间距 */
}

.title {
  text-align: center;
  /* 标题居中 */
  margin-bottom: 10px;
  /* 在标题和走马灯之间添加一些空间 */
  font-size: 20px;
  /* 设置字体大小 */
  font-weight: bold;
  /* 字体加粗 */
}

.card-content {
  text-align: left;
  /* 内容居中显示 */
}

.card-footer {
  display: flex;
  justify-content: space-between;
}

.icon-with-text {
  display: flex;
  align-items: center;
  /* 图标和文字垂直居中 */
}

.button.right-content {
  align-self: flex-end;
  /* 按钮靠右显示 */
}

.carousel-image {
  width: 100%;
  /* 保证图片宽度填满走马灯 */
  height: auto;
  /* 图片高度自适应，保持图片比例不变 */
  object-fit: cover;
  /* 覆盖容器，可能会裁剪图片，也可选择contain保持完整 */
}
</style>


  