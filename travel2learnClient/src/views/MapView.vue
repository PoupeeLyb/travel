<template>
  <div class="center-content">
    <!-- 走马灯组件显示前七个项目，高度调整为原有的1.5倍 -->
    <el-carousel :interval="4000" type="card" height="550px" class="full-width-carousel">
      <el-carousel-item v-for="item in lists.slice(0, 7)" :key="item.name">
        <router-link to="/map" tag="div">
          <el-card class="el-card-custom el-card-carousel">
            <img :src="item.imgUrl" class="image" />
            <div class="card-content">
              <span class="item-name">{{ item.name }}</span>
            </div>
          </el-card>
        </router-link>
      </el-carousel-item>
    </el-carousel>

    <div class="spacer"></div>

    <!-- 剩余项目按原样式展示 -->
    <el-row gutter="20">
      <el-col :span="24" v-for="item in lists.slice(7)" :key="item.name">
        <router-link to="/map" tag="div">
          <el-card class="el-card-custom">
            <img :src="item.imgUrl" class="image" />
            <div class="card-content">
              <span class="item-name">{{ item.name }}</span>
              <div class="card-footer">
                <div class="icon-with-text">
                  <i class="el-icon-view"></i>
                  <span>{{ item.clicks }}</span>
                </div>
              </div>  
            </div>
          </el-card>
        </router-link>
      </el-col>
    </el-row>
  </div>
</template>


<script >
import axios from 'axios';

export default {
  data() {
    return {
      loading: true,
      lists: [],
    }
  },
  methods: {
    fetchImages() {
      axios.get('http://localhost:9090/scen')
        .then(response => {
          this.lists = response.data;
          this.loading = false;
        })
        .catch(error => {
          console.error('Error fetching images:', error);
          this.loading = false;
          alert("error");
        });
    }
  },
  mounted() {
    this.fetchImages(); // 调用 fetchImages 方法来加载数据
  }
}
</script>

<style scoped>
  .full-width-carousel {
    width: 95%; /* 调整为占据界面的95%宽度 */
    margin: 0 auto 30px auto; /* 水平居中显示，并增加底部间隙 */
  }

  .el-card-carousel {
    width: 100%; /* 走马灯中卡片的宽度调整为100% */
    height: 100%; /* 走马灯中卡片的高度调整为100% */
  }

  .spacer {
    height: 50px; /* 增加走马灯与下方内容的间隙 */
  }

.el-container {
  height: 700px;
  border: 2px solid #eee;
}

.center-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
}

.image {
  width: 100%;
  max-width: 100%;
  height: auto;
  margin: auto;
}

.card-content {
  padding: 20px;
}

.item-name {
  font-family: 'Roboto Slab', serif; /* 使用Roboto Slab字体 */
  font-size: 24px; /* 增大字体大小 */
  font-weight: bold; /* 字体加粗 */
  color: rgb(8, 162, 173); /* 设置文字颜色为蓝色 */
  text-align: center; /* 文本居中 */
  display: block; /* 使span元素成为块级元素以支持居中 */
  margin-top: 20px; /* 在图片和文字之间添加一些间隙 */
}

.card-footer {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.icon-with-text {
  display: flex;
  align-items: center;
}

.icon-with-text i.el-icon-view {
  margin-right: 5px;
}

.right-content {
  margin-left: auto;
}

div .el-card-custom {
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

.el-row {
  width: 100%;
  margin-bottom: 100px;
}

.el-col {
  display: flex;
  justify-content: center;
}
.no-border {
  background: none !important;
  border: none !important;
}
.router-link {
  border: none !important;
  background: none !important;
  text-decoration: none; /* 移除超链接的下划线 */
}

.router-link button {
  background: none !important;
  border: none !important;
  box-shadow: none !important; /* 移除可能的阴影 */
}

.el-button--text {
  background: none !important;
  border: none !important;
  box-shadow: none !important;
}

</style>  