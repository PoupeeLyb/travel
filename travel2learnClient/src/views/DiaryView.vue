<template>
  <div class="center-content">
    <!-- 左上角下拉菜单 -->
    <el-dropdown class="sort-dropdown">
      <span class="el-dropdown-link">
        排序<i class="el-icon-arrow-down el-icon--right"></i>
      </span>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item>按日期排序</el-dropdown-item>
        <el-dropdown-item>按点击量排序</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>

    <el-button class="edit-button" type="text" @click="drawer = true">
      <i class="el-icon-edit"></i>
    </el-button>

    <!-- 引入日记显示组件 -->
    <diary-display :lists="lists"></diary-display>

    <el-drawer title="输入内容" :visible.sync="drawer" direction="rtl" size="30%">
      <template #title>
        <div class="custom-drawer-title">日记编辑</div>
      </template>
      <el-input type="textarea" autosize placeholder="标题" v-model="textarea1"></el-input>
      <div style="margin: 20px 0;"></div>
      <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 6 }" placeholder="日记内容"
        v-model="textarea2"></el-input>
      <div class="upload-container">
        <el-upload class="resize-upload" action="https://jsonplaceholder.typicode.com/posts/" list-type="picture-card"
          :on-preview="handlePictureCardPreview" :on-remove="handleRemove">
          <i class="el-icon-plus"></i>
        </el-upload>
      </div>
      <el-dialog :visible.sync="dialogVisible">z``
        <img width="100%" :src="dialogImageUrl" alt="">
      </el-dialog>
      <div class="drawer-footer">
        <el-button @click="drawer = false">取消</el-button>
        <el-button type="primary" @click="submitContent">提交</el-button>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import DiaryDisplay from '@/components/DiaryDisplay.vue'; // 引入DiaryDisplay组件
import axios from 'axios';

export default {
  components: {
    DiaryDisplay
  },
  data() {
    return {
      loading: true, // 控制加载状态
      lists: [], // 从后端获取的数据列表
      drawer: false, // 控制抽屉组件的显示与隐藏
      textarea1: '', // 第一个文本域的数据绑定
      textarea2: '', // 第二个文本域的数据绑定
      uploadedFiles: [], // 存储上传的图片信息
      dialogImageUrl: '', // 预览图片的URL
      dialogVisible: false ,// 控制预览对话框的显示
      user:{}
    }
  },
  methods: {
    formatDateTime(dateTimeString) {
      const date = new Date(dateTimeString);
      const time = date.toLocaleString('en-US', { hour: 'numeric', minute: 'numeric', hour12: true });
      return `${date.toLocaleDateString()} ${time}`;
    },
    fetchImages() {
      axios.get('http://localhost:9090/diary')
        .then(response => {
          this.lists = response.data.map(item => ({
            content: item.content || '默认内容',
            imgUrls: JSON.parse(item.imgUrls) || [
              'https://cdn.pixabay.com/photo/2016/11/12/09/58/china-1818535_1280.jpg',
              'https://cdn.pixabay.com/photo/2016/02/05/04/51/china-1180398_1280.jpg'
            ],
            clicks: item.clicks,
            username: item.username || '默认用户名',
            avatarUrl: item.avatarUrl || 'https://avatars.githubusercontent.com/u/112739685?v=4',
            time: this.formatDateTime(item.time) || '刚刚',
            title: item.title || '默认标题'
          }));
          this.loading = false; // 数据加载完成后关闭加载状态
        })
        .catch(error => {
          console.error('Error fetching images:', error);
          this.loading = false; // 出错时关闭加载状态
          alert("Error loading data from the server");
        });
    },
    handleRemove(file, fileList) {
      console.log('Removed file', file);
      this.uploadedFiles = fileList;
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    submitContent() {
      console.log('提交内容:', this.textarea1, this.textarea2, this.uploadedFiles);

      this.drawer = false; // 提交后关闭抽屉
    }
  },
  mounted() {
    this.fetchImages(); // 组件挂载时调用 fetchImages 方法加载数据
  }
}
</script>
<style scoped>
.sort-dropdown {
  position: fixed;
  left: 30px; /* 根据需要调整 */
  top: 80px; /* 根据需要调整 */
  z-index: 1000;
  font-size: 20px; /* 增大字体大小 */
  padding: 10px; /* 增加内边距 */
}

.el-dropdown-link {
  cursor: pointer;
  color: #010d19;
}

.el-icon-arrow-down {
  font-size: 12px;
}

.upload-container {
  margin-top: 20px; /* 增加与输入框的距离 */
}

.resize-upload .el-upload {
  width: 100px; /* 调整上传组件的宽度 */
  height: 100px; /* 调整上传组件的高度 */
}

.resize-upload .el-upload-list--picture-card .el-upload-list__item {
  width: 90px; /* 调整上传列表项的宽度 */
  height: 90px; /* 调整上传列表项的高度 */
}

.resize-upload .el-icon-plus {
  font-size: 24px; /* 图标大小 */
}

.custom-drawer-title {
  text-align: center;
  /* 文本居中 */
  width: 100%;
  /* 确保 div 宽度占满整个标题栏 */
  font-size: 16px;
  /* 可选，调整字体大小 */
  font-weight: bold;
  /* 可选，字体加粗 */
}

.drawer-footer {
  display: flex;
  justify-content: center;
  /* 使按钮居中 */
  gap: 10px;
  /* 按钮之间的间隔 */
  margin-top: 20px;
  /* 与上方输入框的间隔 */
  padding: 10px 20px;
  /* 调整按钮内边距 */
  font-size: 16px;
  /* 字体大小 */
}

.edit-button {
  position: fixed;
  right: 60px;
  top: 80px;
  /* Adjusted to be below the navigation bar */
  z-index: 1000;
  font-size: 40px;
  /* 调整为所需大小 */
}

.center-content {
  max-width: 1200px;
  margin: 0 auto;
  /* Centrally aligns the content */
  display: flex;
  justify-content: center;
}
</style>
