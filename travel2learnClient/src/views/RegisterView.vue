<template>
    <form @submit.prevent="formSubmit" class="register-form">
      <!-- 用户名 -->
      <div class="item">
        <input type="text" @input="registerUserBlur" name="username" placeholder="请输入用户名" v-model="username" />
        <div class="error" v-if="userDecide === false">请输入用户名</div>
        <div v-else-if="userDecide === 1">
          <i class="success-icon" @click="agreementBind"></i>
        </div>
        <div class="error" v-if="userDecide === 2">用户名不存在</div>
      </div>
      <!-- 新密码 -->
      <div class="item">
        <div class="passwordText">
          <input :type="passwordShow" @input="registerPasswordBlur" name="password" placeholder="请输入6到20位密码" v-model="password" />
          
        </div>
        <div class="error" v-if="passwordDecide === false">请输入密码</div>
        <div v-else-if="passwordDecide === 1">
          <i class="success-icon" @click="agreementBind"></i>
        </div>
        <div class="error" v-if="passwordDecide === 2">密码不符合格式</div>
      </div>
      <!-- 确认新密码 -->
      <div class="item">
        <div class="passwordText">
          <input :type="passwordAgainShow" @input="registerPasswordAgainBlur" name="passwordAgain" placeholder="请再次输入密码" v-model="passwordAgain" />
          
        </div>
        <div class="error" v-if="passwordAgainDecide === false">请再次输入密码</div>
        <div v-else-if="passwordAgainDecide === 1">
          <i class="success-icon" @click="agreementBind"></i>
        </div>
        <div class="error" v-if="passwordAgainDecide === 2">密码与上面不同</div>
      </div>
      <!-- 确定 -->
      <button type="primary" @click="register" style="width: 100%; padding: 10px; border: none; background-color: #007bff; color: #fff; border-radius: 5px;">注册</button>
    </form>
  </template>
  
  <script>
  import axios from 'axios'; // 导入axios
  export default {
    data() {
      return {
        userDecide: false,
        user:{},
        username: '',
        password: '',
        passwordAgain:'',
        passwordShow: 'password',
        passwordIsShow: false,
        passwordDecide: false,
        passwordAgainDecide: false,
        passwordAgainShow: 'password',
        passwordAgainIsShow: false,
      };
    },
    methods: {
      registerUserBlur() {
        if(this.username=="")
         this.userDecide = false;
        else{
          this.userDecide=1;
        }
      },
      registerPasswordBlur() {
        if(this.password==""){
          this.passwordDecide=false;
        }
        else if(this.password.length<=20&&this.password.length>=6){
          this.passwordDecide=1;
        }
          else{
            this.passwordDecide=2;
          }
      },
      registerPasswordAgainBlur() {
        if (this.passwordAgain=="") {
          this.passwordAgainDecide = false;
        } else {
          if(this.passwordAgain==this.password){
            this.passwordAgainDecide=1;
          }
          else{
            this.passwordAgainDecide=2;
          }
        }
      },
      formSubmit() {
        this.user.username=this.username;
        this.user.password=this.password;
        console.log(this.user);
        if (this.passwordDecide === 1 && this.passwordAgainDecide === 1 && this.userDecide === 1) {
         axios({
              method:'POST',
              url:'http://localhost:9090/user/register',
              data:this.user
          }).then(result=>{
          //成功的回调
          alert(result.data);
          if(result.data!='用户名已注册')
          this.$router.push({ name: 'login'});
          }).catch(err=>{
          //失败的回调 
          console.log(err);
          })
        } else {
          alert("注册失败");
        }
      },
      PasswordTouchEye() {
        this.passwordShow = this.passwordIsShow ? 'password' : 'text';
        this.passwordIsShow = !this.passwordIsShow;
      },
      PasswordAgainTouchEye() {
        this.passwordAgainShow = this.passwordAgainIsShow ? 'password' : 'text';
        this.passwordAgainIsShow = !this.passwordAgainIsShow;
      },
      agreementBind() {
        // 处理同意协议逻辑
      },
    },
  };
  </script>
  
  <style scoped>
  .register-form {
    width: 100%;
    background-color: white;
    padding: 20px;
    border-radius: 10px;
  }
  .item {
    margin-bottom: 20px;
  }
  .item input {
    font-size: 16px;
    padding: 10px;
    width: 100%;
    border: 1px solid #ccc;
    border-radius: 5px;
  }
  .error {
    color: red;
    font-size: 12px;
    margin-top: 5px;
  }
  .passwordText {
    position: relative;
  }
  .passwordImage {
    position: absolute;
    right: 10px;
    top: 50%;
    transform: translateY(-50%);
    cursor: pointer;
  }
  .success-icon {
    color: green;
    cursor: pointer;
  }
  </style>
  