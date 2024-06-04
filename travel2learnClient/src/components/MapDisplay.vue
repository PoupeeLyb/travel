<template>
  <div>
    <div id="map" style="width: 100%; height: 400px;"></div>
    <div class="button-container">
      <button @click="fetchAndDrawRoute">查询路线</button>
    </div>
  </div>
</template>

<style>
.button-container {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}
</style>

<script>
import axios from 'axios';
/* global AMap */
export default {
  name: 'MyMap',
  props: {
    centerPoint: {
      type: Array,
    },
    startPoint: {
      type: Array,
      default: () => [118.041577, 27.762553]  // 默认起始点，北京天安门
    },
    endPoint: {
      type: Array,
      default: () => [118.097128, 27.766527] // 默认终点，北京故宫
    }
  },
  data() {
    return {
      map: null
    };
  },
  mounted() {
    this.initMap();
  },
  watch: {
  centerPoint(newVal) {
    if (this.map && newVal) {
      this.map.setCenter(new AMap.LngLat(newVal[0], newVal[1]));
    }
  }
},
  methods: {
    initMap() {
      this.$nextTick(() => {
        if (window.AMap) {
          this.map = new AMap.Map('map', {
            center: this.centerPoint,  // 使用 prop centerPoint 作为地图中心
            zoom: 13
          });

          this.addMarker(this.startPoint, 'https://webapi.amap.com/theme/v1.3/markers/n/mark_b.png', '起始点');
          this.addMarker(this.endPoint, 'https://webapi.amap.com/theme/v1.3/markers/n/mark_r.png', '终点');
        } else {
          console.error('AMap SDK is not loaded.');
        }
      });
    },
    addMarker(position, iconUrl, title) {
      new AMap.Marker({
        map: this.map,
        position: position,
        icon: new AMap.Icon({
          size: new AMap.Size(40, 50),
          image: iconUrl,
          imageSize: new AMap.Size(40, 50)
        }),
        title: title
      });
    },
    fetchAndDrawRoute() {
      axios.post('http://localhost:9090/routediy', {
        origin: this.startPoint.join(','),
        destination: this.endPoint.join(',')
      }).then(response => {
        const path = response.data.route.paths[0].steps.reduce((acc, step) => {
          return acc.concat(step.polyline.split(';').map(point => {
            return point.split(',').map(Number);
          }));
        }, []);
        this.drawRoute(path);
      }).catch(error => {
        console.error('查询路线失败:', error);
      });
    },
    drawRoute(path) {
      var polyline = new AMap.Polyline({
        path: path,
        strokeColor: '#0066ff',
        strokeOpacity: 1,
        strokeWeight: 6,
        strokeStyle: 'solid',
        showDir: true,
        strokeDasharray: [10, 5, 0]
      });
      polyline.setMap(this.map);
    }
  }
}

</script>
