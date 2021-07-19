<template>
    <div id="signup">
        <LoginPopup
            v-if="loginPopupState == 1" 
            :_loginPopupState = "loginPopupState"
            @_loginClose = "loginClose"
        ></LoginPopup>
        <HeaderLight
            :_loginPopupState = "loginPopupState"
            @_loginOpen = "loginOpen"
        ></HeaderLight>
        <div class="form-container">
        <form @submit.prevent="onSubmit">
            <p>이름</p>
            <input type="text" name="name" v-model="name">
            <p>닉네임</p>
            <input type="text" name="nickname" v-model="nickname">
            <p>아이디</p>
            <input type="text" name="id" v-model="id">
            <p>이메일</p>
            <input type="email" name="email" v-model="email">
            <p>비밀번호</p>
            <input type="password" name="password" v-model="password">
            <p>비밀번호</p>
            <input type="password">
            <button type="submit">가입</button>
      </form>
      </div>
      <img class="signup-btn" src="../assets/btn_signup.svg" alt="">
    </div>
</template>


<script>
import HeaderLight from "../components/layout/Header-light.vue"
import LoginPopup from "../components/layout/login-popup.vue"
import axios from "axios"

export default {
    name : 'Signup',
    components : {
        HeaderLight, LoginPopup
    },
    data() {
        return {
          form: {
            name: '',
            nickname: '',
            id: '',
            email: '',
            password: '',
          }
        }
    },
    methods : {
        loginOpen() {
            this.loginPopupState = 1;
        },
        loginClose() {
            this.loginPopupState = 0;
        },
        async onSubmit(){
            const response = await axios.post('http://localhost:8080/api/signup', {
              id : this.form.id,
              nickname : this.nickname,
              email: this.email,
              password: this.password,
              name : this.name
          })
          console.log(this.form.email);
          console.log(response);
        }
    }
}
</script>

<style>
#signup {
    padding: 20px;
}
input {
    box-sizing: border-box;
}
.form-container{
  width: 350px;
  margin: auto;
}
.form-container p{
  float: left;
  margin-left: 25px;
}
.form-container input{
  width: 100%; height: 30px;
  border: 1px solid #C4C4C4;
  border-radius: 10px;
}
.signup-btn {
    width: 350px;
    margin-top: 45px;
}
</style>