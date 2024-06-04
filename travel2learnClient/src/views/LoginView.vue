<template>
    <div class="content">
      <!-- 用户名 -->
      <div class="username">
        <div class="title">用户名:</div>
        <div class="text">
          <input v-model="username" @input="usernameInput" type="text" placeholder="用户名/邮箱/手机号"/>
        </div>
      </div>
      <!-- 分隔线 -->
      <div class="hr"></div>
      <!-- 密码 -->
      <div class="password">
        <div class="title">密码:</div>
        <div class="text">
          <input v-model="password" @input="passwordInput" :type="show" placeholder="请输入密码" />
        </div>
      </div>
      <!-- 登录按钮 -->
      <button class="login" :disabled="loginBtnState" @click="login">登录</button>
      <!-- 注册导航 -->
      <div class="register">
        <!-- 手机号注册 -->
        <div>
          <a href="/register">手机号注册</a>
        </div>
        <!-- 找回密码 -->
        <div>
          <a href="/findkey">找回密码</a>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios'; // 导入axios
  export default {
    data() {
      return {
        user:{},
        loginBtnState: true,
        username: "",
        password: "",
        show: 'password',
        isShow: true
      };
    },
    methods: {
      usernameInput() {
        if (this.username !== "") {
          if (this.password !== "") {
            this.loginBtnState = false;
          }
        } else {
          this.loginBtnState = true;
        }
      },
      passwordInput() {
        if (this.password !== "") {
          if (this.username !== "") {
            this.loginBtnState = false;
          }
        } else {
          this.loginBtnState = true;
        }
      },
      login() {
        // 登录逻辑
        console.log("正在登录");
        this.user.username=this.username;
        this.user.password=this.password;
        axios({
            method:'post',
            url:'http://localhost:9090/user/login',
            data:this.user
        }).then(result=>{
        //成功的回调
        console.log(result.data);
        if(result.data=="用户名不存在"||result.data=="密码错误"){
          alert(result.data);
        }
        else{
          alert("登录成功");
          this.$store.dispatch('asyncUpdateUser',result.data);
          console.log(this.$store.getters.getUser.id);
        }

        
        }).catch(err=>{
        //失败的回调 
        console.log(err);
        })
      },
      toggleShow() {
        this.show = this.show === 'password' ? 'password' : 'text';
        this.isShow = !this.isShow;
      }
    }
  };
  </script>
<style scoped>

html, body {
  height: 100%; /* 确保html和body填满整个视窗高度 */
  margin: 0;
  padding: 0;
  background-color: #f8f9fa; /* 背景色 */
}

.content {
  position: absolute; /* 使用绝对定位 */
  top: 50%; /* 置于顶部50%的位置 */
  left: 50%; /* 置于左侧50%的位置 */
  transform: translate(-50%, -50%); /* 使用transform偏移，确保content的中心与视窗的中心对齐 */
  display: flex;
  flex-direction: column;
  align-items: center; /* 水平居中 */
  width: 100%; /* 可以调整为所需的最大宽度 */
  max-width: 300px; /* 限制最大宽度，依您的需求而定 */
}


.username,
.password {
  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 10px 0;
  border-bottom: 1px solid #ccc;
  width: 100%; /* 确保宽度是父容器的100% */
  max-width: 300px; /* 限制最大宽度为300px */
}

.title {
  width: 60px;
  font-size: 16px;
}

.text {
  flex: 1;
}

input {
  width: 100%;
  font-size: 16px;
  border: none;
  outline: none;
}

.login {
  margin-top: 20px;
  width: 100%; /* 确保宽度是父容器的100% */
  max-width: 300px; /* 限制最大宽度为300px */
  height: 40px;
  line-height: 40px;
  text-align: center;
  background-color: #007bff;
  color: #fff;
  font-size: 16px;
  border: none;
  border-radius: 5px;
  outline: none;
  cursor: pointer;
}

.register {
  display: flex;
  justify-content: space-around;
  margin-top: 10px;
  font-size: 14px;
  width: 100%; /* 确保宽度是父容器的100% */
  max-width: 300px; /* 限制最大宽度为300px */
}
</style>
