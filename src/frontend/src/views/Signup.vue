<template>
    <div id="signup">
        <SignupCompletePopup
            v-if="signupComplete == 1"
            :_userName = "userName"
        >
        </SignupCompletePopup>
        <LoginPopup
            v-if="loginPopupState == 1" 
            :_loginPopupState = "loginPopupState"
            @_loginClose = "loginClose"
        ></LoginPopup>
        <HeaderLight
            :_loginPopupState = "loginPopupState"
            @_loginOpen = "loginOpen"
        ></HeaderLight>
        <div class="signup-container">
            <!-- <div class="form-container">
                <p>이름</p>
                <input v-model="signupForm.signupName" type="text">
                <p>아이디</p>
                <input v-model="signupForm.signupId" type="text">
                <p>이메일</p>
                <input v-model="signupForm.signupEmail" type="text">
                <p>비밀번호</p>
                <input v-model="signupForm.signupPassword" type="password">
                <p>비밀번호 확인</p>
                <input v-model="signupPasswordConfirm" type="password">
                <p class="signup-guide" v-if="signupPasswordGuideDisplay == 1">입력한 비밀번호와 일치하지 않습니다.</p>
            </div> -->
            <form class="form-container" @submit.prevent="onSubmit">
                <p>이름</p>
                <input type="text" name="name" v-model="signupForm.signupName">
                <p>아이디</p>
                <input type="text" name="id" v-model="signupForm.signupId">
                <p>이메일</p>
                <input type="email" name="email" v-model="signupForm.signupEmail">
                <p>비밀번호</p>
                <input type="password" name="password" v-model="signupForm.signupPassword">
                <p>비밀번호 확인</p>
                <input type="password" v-model="signupPasswordConfirm">
                <p class="signup-guide" v-if="signupPasswordGuideDisplay == 1">입력한 비밀번호와 일치하지 않습니다.</p>
                <button type="submit"><img class="signup-btn" src="../assets/btn_signup.svg" alt=""></button>
            </form>
            <!-- <img class="signup-btn" @click="signupConfirm" src="../assets/btn_signup.svg" alt=""> -->
            <div class="login-guide-container">
                <span class="guide-to-login">이미 계정이 있으세요?</span>
                <button class="move-to-login">로그인</button>
            </div>
        </div>
    </div>
</template>

<script>
import HeaderLight from "../components/layout/Header-light.vue"
import LoginPopup from "../components/layout/login-popup.vue"
import SignupCompletePopup from "../components/layout/signup-complete-popup.vue"
import axios from "axios"

export default {
    name : 'Signup',
    components : {
        HeaderLight, LoginPopup, SignupCompletePopup
    },
    data() {
        return {
            loginPopupState : 0,
            userName : '',
            signupForm : {
                signupId : '',
                signupName : '',
                signupEmail : '',
                signupPassword : '',
            },
            signupPasswordConfirm : '',
            signupFormNullcheck : true,
            signupPasswordGuideDisplay : 0, //1이 되면 비밀번호 중복 확인 메세지 띄움
            signupComplete : 0 //0은 회원가입이 되지 않은 상태, 1은 회원가입이 된 상태
        }
    },
    methods : {
        loginOpen() {
            this.loginPopupState = 1;
        },
        loginClose() {
            this.loginPopupState = 0;
        },
        nullCheck() {
            console.log("nullCheck 함수 실행됨");
            for (const key in this.signupForm) {
                if (this.signupForm[key] == '') { //모든 항목이 채워져 있는지 확인
                    alert("모든 항목을 입력해주세요.")
                    console.log("비어있는 항목이 있습니다.");
                    break;
                } else { 
                    this.signupFormNullcheck = false
                    console.log("비어있는 항목이 없습니다.");
                }
            }
        },
        onSubmit() {
            this.nullCheck();
            if (this.signupFormNullcheck == false) {
                if (this.signupForm.signupPassword != this.signupPasswordConfirm) { //일치하지 않는 경우
                    this.signupPasswordGuideDisplay = 1
                    console.log("비밀번호가 일치하지 않습니다.");
                } else { // 일치하는 경우
                    this.signupPasswordGuideDisplay = 0
                    console.log("비밀번호가 일치합니다.");
                    this.submitForm();
                }
            }
        },
        submitForm(){
            console.log("submitForm 함수 실행됨");
            axios
            .post('http://3.36.131.138/api/signup', {
                userId : this.signupForm.signupId,
                email: this.signupForm.signupEmail,
                password: this.signupForm.signupPassword,
                name : this.signupForm.signupName
            })
            .then(res => {
                console.log(res);
                this.userName = res.data.name
                this.signupComplete = 1;
            })
            .catch(err => {
                console.log(err);
            })
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
#signup form {
    overflow: hidden;
}
.signup-container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: 350px;
    margin: auto;
}
.form-container{
    display: flex;
    flex-direction: column;
    margin-top: 30px;
}
.form-container p{
  align-self: flex-start;
  margin-left: 25px;
}
.form-container input{
  width: 100%; height: 35px;
  border: 1px solid #C4C4C4;
  border-radius: 10px;
}
.form-container button {
    padding: 0;
}
#signup .signup-guide {
    display: block;
    float: left;
    margin: 5px 0 0 10px;
    color: #F93079;
    font-size: 12px;
}
.signup-btn {
    width: 350px;
    margin-top: 45px;
    cursor: pointer;
}
.login-guide-container {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 15px;
}
#signup .login-guide-container .guide-to-login {
    margin: 0px;
    color: #606060;
}
#signup .move-to-login {
  display: inline-block;
  margin-left: 5px;
  color: #765FD7;
  font-size: 16px;
  font-weight: 700;
  cursor: pointer;
}
</style>