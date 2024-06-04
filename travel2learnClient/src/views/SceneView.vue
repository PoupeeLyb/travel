<template>
  <div>
    <el-tabs v-model="activeName" class="custom-tabs">
      <el-tab-pane name="first">
        <span slot="label" class="tab-label"><i class="el-icon-location-information"></i> 当前景区</span>
        <div class="scene-detail">
          <el-carousel :interval="5000" indicator-position="inside" class="scene-carousel">
            <el-carousel-item v-for="(imgUrl, index) in sceneImageUrls" :key="index">
              <img :src="imgUrl" alt="Scene Image" class="carousel-image">
            </el-carousel-item>
          </el-carousel>
          <div class="scene-info-card">
            <el-card class="info-card">
              <h3>规划你的行程</h3>
              <h2 class="attraction-name">{{ attraction.name }}</h2>
              <p v-if="!readMore && attraction.introduction.length > 100">
                {{ attraction.introduction.substring(0, 100) + '...' }}
                <el-button type="text" @click="toggleReadMore">阅读更多</el-button>
              </p>
              <p v-else>{{ attraction.introduction }}</p>
            </el-card>
          </div>
          <br>
          <br>
          <p class="tourist-experience">游客体验</p>
          <el-divider class="yellow-divider"></el-divider>
          <diary-display :lists="diaries"></diary-display>
        </div>
      </el-tab-pane>
      <el-tab-pane label="地图" name="second">
        <MapDisplay :centerPoint="centerPoint"></MapDisplay>
      </el-tab-pane>
      <el-tab-pane label="景点玩乐" name="third"></el-tab-pane>
      <el-tab-pane label="美食" name="fourth"></el-tab-pane>
      <el-tab-pane label="公众服务" name="fifth"></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import axios from 'axios';
import DiaryDisplay from '@/components/DiaryDisplay.vue'; // 确保路径正确
import MapDisplay from '@/components/MapDisplay.vue';

export default {
  components: {
    DiaryDisplay,
    MapDisplay
  },
  props: ['id'],
  data() {
    return {
      attraction: {},
      diaries: [],
      sceneImageUrls: [],
      readMore: false,
      activeName: 'first',
      centerPoint: [116.397428, 39.90923]  // 默认中心点，可以根据需要调整
    };
  },
  methods: {
    // fetchCenterPoint 方法被注释，改为从 fetchAttraction 方法中更新中心点
    /*
    fetchCenterPoint() {
      axios.get(`http://localhost:9090/centerPoint/${this.id}`)
        .then(response => {
          const data = typeof response.data === 'string' ? JSON.parse(response.data) : response.data;
          if (data && data.geocodes && data.geocodes.length > 0) {
            const locationStr = data.geocodes[0].location;
            const [longitude, latitude] = locationStr.split(',').map(Number);
            this.centerPoint = [longitude, latitude];
          } else {
            console.error('No geocode information available.');
          }
        })
        .catch(error => {
          console.error('Error fetching center point:', error);
        });
    },
    */

    fetchAttraction() {
      axios.get(`http://localhost:9090/scene/${this.id}`)
        .then(response => {
          this.attraction = response.data.scene;
          this.sceneImageUrls = JSON.parse(this.attraction.imgUrls);
          this.diaries = response.data.diaries.map(diary => ({
            ...diary,
            imgUrls: diary.imgUrls ? JSON.parse(diary.imgUrls) : []
          }));
          // 更新 centerPoint
          const [longitude, latitude] = this.attraction.position.split(',').map(Number);
          this.centerPoint = [longitude, latitude];
        })
        .catch(error => {
          console.error('Error fetching scene details:', error);
        });
    },
    toggleReadMore() {
      this.readMore = !this.readMore;
    }
  },
  created() {
    this.fetchAttraction(); // 只调用 fetchAttraction，它会更新场景和中心点信息
  }
}
</script>

<style scoped>
.scene-detail {
  width: 100%;
  margin: 20px auto;
}

.scene-carousel .carousel-image {
  width: 100%;
  height: auto;
  /* 自适应图片高度 */
  object-fit: cover;
  border-radius: 10px 10px 0 0;
  /* 上角圆润，下角直角 */
}

.scene-info-card {
  margin: 0 0 20px;
  /* 紧贴走马灯部分下面 */
  text-align: left;
}

.info-card {
  background-color: #F2F1EC;
  /* 灰色背景 */
  border-radius: 0 0 10px 10px;
  /* 下角圆润，上角直角 */
  padding: 20px;
}

.info-card h2 {
  font-size: 24px;
  color: #333;
  text-align: left;
  /* 景点名字靠左显示 */
}

.info-card h3 {
  font-size: 16px;
  font-weight: bold;
  color: #333;
  text-align: left;
  /* "规划你的行程" 靠左显示 */
}

.info-card p {
  font-size: 16px;
  color: #666;
  line-height: 1.5;
  margin-bottom: 48px;
  /* 保留三行空白 */
  position: relative;
  /* 定位基于相对位置 */
}

.tourist-experience {
  font-size: 25px;
  /* 加大字体大小 */
  color: #666;
  /* 灰色字体 */
  margin: 30px 0;
  /* 上下间距 */
  text-align: left;
  /* 靠左展示 */
}


.yellow-divider {
  background-color: #eef1f4;
}

.custom-tabs {
  margin-left: 7.5%;
  /* 根据实际页面布局调整 */
  margin-right: 7.5%;
  /* 根据实际页面布局调整 */
}

.custom-tabs>>>.el-tabs__item {
  font-size: 18px;
  /* 字体大小 */
  font-weight: bold;
  /* 字体加粗 */
  color: #545454;
  /* 字体颜色 */
}
</style>