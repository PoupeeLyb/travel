<template>
    <el-header class="header">
        <div class="header-container">
            <div class="header-left">
                <i class="el-icon-map-location"></i>
                <span>Travel2Learn</span>
            </div>
            <el-menu mode="horizontal" class="header-menu" background-color="transparent" text-color="#fff"
                active-text-color="#ffd04b">
                <router-link to="/home" class="router-link">
                    <el-menu-item index="1">
                        <i class="el-icon-s-home"></i>
                        <span>Home</span>
                    </el-menu-item>
                </router-link>
                <router-link to="/diary" class="router-link">
                    <el-menu-item index="2">
                        <i class="el-icon-news"></i>
                        <span>Diary</span>
                    </el-menu-item>
                </router-link>
                <router-link to="/ai" class="router-link">
                    <el-menu-item index="3">
                        <i class="el-icon-s-opportunity"></i>
                        <span>AI</span>
                    </el-menu-item>
                </router-link>
                <el-submenu>
                    <template slot="title"><el-avatar :src="avatarUrl"></el-avatar></template>
                    <el-menu-item index="4-1">历史记录</el-menu-item>
                    <el-menu-item index="4-2">收藏</el-menu-item>
                    <el-menu-item index="4-3">我的博客</el-menu-item>
                    <el-menu-item index="4-4">设置</el-menu-item>
                </el-submenu>
            </el-menu>
            <!-- 添加搜索框和按钮 -->
            <div class="search-container">
                <el-input v-model="searchQuery" placeholder="搜索" class="search-input"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
            </div>
        </div>
    </el-header>
</template>

<script>
export default {
    data() {
        return {
            avatarUrl: 'https://avatars.githubusercontent.com/u/112739685?v=4',
            searchQuery: '',
        }
    },
    methods: {
        handleSearch() {
            console.log('搜索内容：', this.searchQuery);
        }
    }
}
</script>

<style scoped>
.header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 90px;
}

.header-container {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 100%;
}

.header-left {
    display: flex;
    align-items: center;
}

.header-left i,
.header-left span {
    color: rgb(255, 94, 0);
    font-family: 'Arial', sans-serif;
    font-size: 24px;
}

.header-left i {
    margin-right: 10px;
}

.header-menu {
    display: flex;
    flex-grow: 1;
    justify-content: center;
}

.router-link {
    display: flex;
    align-items: center;
    color: inherit;
    /* Use the text color of the parent element */
    text-decoration: none;
}

.search-container {
    display: flex;
    align-items: center;
}

.search-input {
    max-width: 200px;
}
</style>