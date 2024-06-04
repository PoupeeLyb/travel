<template>
  <div class="container">
    <div class="input-container">
      <el-input class="input-style" v-model="prompt" placeholder="Enter a prompt"></el-input>
      <div class="spacer"></div>
      <el-button type="primary" :loading="loading" @click="generateImage">Generate Image</el-button>
    </div>
    <div v-if="imageUrl" class="img-container">
      <img :src="imageUrl" alt="Generated Image" />
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      prompt: '',
      imageUrl: null,
      loading: false
    };
  },
  methods: {
    async generateImage() {
      this.loading = true;
      try {
        const response = await axios.post('http://localhost:9090/generate-image', { prompt: this.prompt });
        this.imageUrl = response.data.image_url;
      } catch (error) {
        console.error('Error generating image:', error);
        alert(`Error generating image: ${error.message}`);
      } finally {
        this.loading = false;
      }
    }
  }
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  height: 100vh;
  padding-top: 100px;
}

.input-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.input-style {
  max-width: 300px;
  width: 100%;
}

.spacer {
  height: 20px;
}

.el-button {
  margin-top: 20px;
}

.img-container {
  margin-top: 20px;
  display: flex;        /* 使用 flex 布局 */
  justify-content: center; /* 水平居中 */
  align-items: center;   /* 垂直居中 */
  width: 100%;           /* 容器宽度为 100% */
}

img {
  max-width: 80%;
  max-height: 80vh;
}
</style>
