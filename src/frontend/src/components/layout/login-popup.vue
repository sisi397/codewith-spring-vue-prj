<template>
<div id="login-popup">
  <div class="black-bg" @click="closeLoginPopup">
  </div>
  <form class="login-popup-content" @submit.prevent="onSubmit">
      <div class="login-form-hide">
        <img class="hide-btn" @click="closeLoginPopup" src="../../assets/btn_close.svg" alt="">
      </div>
      <h4 class="popup-title">{{popup.popupTitle}}</h4>
      <div class="form-container">
        <p class="guide-to-login">{{popup.popupInfo[popup.index]}}</p>
        <div class="login-form">
          <div class="user-inform">
            <!-- 아이디 -->
            <input class="form-value" :type="formFirst.type" v-model="formFirst.value" :placeholder="placeHolder[0]"> 
            <div class="login-guide" v-if="idGuideDisplay == 1">{{placeHolder[0]}}를 입력해주세요.</div>
          </div>
          <div class="user-inform">
            <!-- 비밀번호 -->
            <input class="form-value" :type="formSecond.type" v-model="formSecond.value" :placeholder="placeHolder[1]">
            <div class="login-guide" v-if="passwordGuideDisplay == 1">{{placeHolder[1]}}를 입력해주세요.</div>
          </div>
        </div>
        <!-- <p id="find-user-id" >가입 정보에 해당하는 아이디는 {{user.id}}입니다.</p> -->
        <div class="signup-guide-container" v-if="popup.popupTitle == '로그인'">
          <span class="guide-to-signup">아직 회원이 아니세요?</span>
          <router-link to="/Signup" class="move-to-signup">회원가입</router-link>
        </div>
        <button type="submit"><img class="login-btn" v-if="popup.popupSort == 'login'" src="../../assets/btn_login.svg" alt="btn_login"></button>
        <button type="submit"><img class="login-btn" v-if="popup.popupSort == 'id' || popup.popupSort == 'password'" src="../../assets/btn_find.svg" alt="btn_login"></button>
        <div class="find-container">
          <span @click="changeFindId" v-if="popup.popupSort == 'password' || popup.popupSort == 'login'">아이디 찾기</span>
          <span v-if="popup.popupSort == 'login'"> | </span>
          <span @click="changeFindPassword" v-if="popup.popupSort == 'id' || popup.popupSort == 'login'">비밀번호 찾기</span>
          <span v-if="popup.popupSort == 'id' || popup.popupSort == 'password'"> | </span>
          <span @click="backLogin" v-if="popup.popupSort == 'id' || popup.popupSort == 'password'">로그인</span>
        </div>
      </div>  

  </form>
</div>
</template>

<script>
import axios from "axios"

export default {
    name : 'login-popup',
    props : ['_loginPopupState', '_loginState', '_userName'],
    data() {
      return {
        user : {
          id : '',
          name : ''
        },
        popup : {
          index : 0, //0 : 로그인, 1 : html, 2 : css : 3
          popupTitle : '로그인',
          popupSort : 'login',
          popupInfo : ['다양한 실습을 통해 html, css, javascript을 무료로 학습하실 수 있습니다.', '가입정보를 입력하신 후 Find를 클릭하시면 아이디를 확인하실 수 있습니다.', '가입정보를 입력하신 후 Find를 클릭하시면 임시비밀번호가 발송 됩니다.'],
        },
        //state
        loginState : 0, //0은 로그인이 안 된 상태, 1은 로그인이 된 상태
        loginPopupState : this._loginPopupState,
        // form 관련 data
        placeHolder : ["아이디", "비밀번호"],
        formFirst : {
          type : '',
          value : '',
        },
        formSecond : {
          type : 'password',
          value : ''
        },
        //빈 항목 체크 & 채우기 안내하는 메세지
        idGuideDisplay : 0, //1일 되면 아이디 입력 메세지 보여줌
        passwordGuideDisplay : 0, //1일 되면 비번 입력 메세지 보여줌
        nullcheck : 0, //0 : 아이디,비밀번호가 비어있음, 1 : 아이디, 비밀번호가 모두 입력됨(로그인 가능 상태)
      }
    },
    created() {
      this.loginState = this._loginState;
    },
    methods : {
      closeLoginPopup() {
        console.log("로그인 창 닫음");
        this.loginPopupState = 0;
        this.$emit('_loginClose');
      },
      formNullCheck() {
        this.nullcheck = 0 // nullcheck true로 초기화, 초기화를 하지 않을 경우 한 번 1로 바뀌면 계속 유지됨
        if (this.formFirst.value == '' && this.formSecond.value == '') {
          this.idGuideDisplay = 1;
          this.passwordGuideDisplay = 1;
        } else if(this.formFirst.value == '') {
          this.idGuideDisplay = 1;
          this.passwordGuideDisplay = 0;
        } else if(this.formSecond.value == '') {
          this.idGuideDisplay = 0;
          this.passwordGuideDisplay = 1;
        } else { // 아이디, 비밀번호가 모두 입력된 상태인 경우
          console.log("아아디, 비밀번호 모두 입력됨");
          this.idGuideDisplay = 0;
          this.passwordGuideDisplay = 0;
          this.nullcheck = 1
        }
      },
      onSubmit() {
        this.formNullCheck(); //비어있는 항목 체크 후 안내 메세지
        if (this.popup.index === 0) {
          this.tryLogin();
        } else if (this.popup.index === 1) {
          this.findId();
        } else {
          this.findPass();
        }
      },
      formInit() {
        document.getElementsByClassName("form-value").value = "";
        document.getElementsByClassName("form-value").value = "";
        this.formFirst.value = '';
        this.formSecond.value = '';
      },
      tryLogin() {
        if (this.nullcheck == 1) {
          axios
          .post('http://3.36.131.138/api/login', {
            userId : this.formFirst.value,
            password : this.formSecond.value
          })
          .then(res => { //로그인 성공
            if(res.data){
              this.closeLoginPopup();
              this.completeLogin();
            }else{
              alert("아이디 또는 비밀번호가 틀렸습니다.");
              this.formInit(); //form 입력창 초기화
            }
          })
          .catch(err => {
              console.log(err.response);
          })
        }
      },
      completeLogin() { //로그인 성공
        this.loginState = 1
        this.userInfo();
      },
      userInfo() {
        console.log("userInfo 함수를 수행합니다.");
        axios
        .get("http://3.36.131.138/memberInfo")
        .then(res => {
          console.log(res);
          localStorage.setItem('loginState', JSON.stringify(this.loginState));
          localStorage.setItem('userName', res.data.name + '님');
          this.$emit('_completeLogin')
        })
        .catch(err => {
          console.log(err);
        })
      },
      findId() {
        if (this.nullcheck == 1) {          
          axios
          .post('http://3.36.131.138/api/idFind', {
            name : this.formSecond.value,
            email : this.formFirst.value,
          })
          .then(res => {
            console.log(res);
            this.user.id = res.data
            this.formInit(); //form 입력창 초기화
            if (res.data != "") {
              alert("가입정보에 해당하는 아이디는 " + this.user.id + "입니다.")
            } else {
              alert("아이디 찾기에 실패하였습니다. 이메일과 이름을 확인해주세요.")
            }
          })
          .catch(err => {
            console.log(err);
          })
        }
      },
      findPass() {
        if (this.nullcheck == 1) {
          axios
          .put('http://3.36.131.138/api/passFind', {
            userId : this.formSecond.value,
            email : this.formFirst.value,
          })
          .then(res => {
            console.log(res);
            if (res.data) {
              alert("이메일로 임시 비밀번호를 발송하였습니다.")
              this.formInit(); //form 입력창 초기화
            }else {
              alert("비밀번호 찾기에 실패하였습니다. 이메일과 아이디를 확인해주세요.")
            }
          })
          .catch(err => {
            console.log(err);
          })
        }
      },

      // 아이디, 비밀번호 찾기 관련
      changeFindId() {
        this.popup.index = 1
        this.popup.popupTitle = '아이디 찾기';
        this.popup.popupSort = 'id'
        this.placeHolder = ['이메일', '이름']
        this.formInit(); // form 입력창 초기화
        this.formFirst.type = 'email'; //메일양식을 확인하는 메일 칸으로 변경
        this.formSecond.type = 'text'; //이름을 입력할 수 있는 텍스트 칸으로 변경
      },
      changeFindPassword() {
        this.popup.index = 2
        this.popup.popupTitle = '비밀번호 찾기';
        this.popup.popupSort = 'password'
        this.placeHolder = ['이메일', '아이디']
        this.formInit(); // form 입력창 초기화
        this.formFirst.type = 'email'; //메일양식을 확인하는 메일 칸으로 변경
        this.formSecond.type = 'text'; //아이디을 입력할 수 있는 텍스트 칸으로 변경
      },
      backLogin() {
        this.popup.index = 0
        this.popup.popupTitle = '로그인';
        this.popup.popupSort = 'login'
        this.placeHolder = ['아이디', '비밀번호']
        this.formInit(); // form 입력창 초기화
        this.formFirst.type = 'text'; //메일양식을 확인하는 메일 칸으로 변경
        this.formSecond.type = 'password'; //비밀번호를 입력하는 암호 칸으로 변경
      }
    }
  }
</script>

<style>
#login-popup {
  position: relative;
  display: flex;
  align-items: center; 
  width: 100%; height: 100%;
  position: fixed;
  z-index: 1;
}
#login-popup form{
  overflow: hidden;
}
#login-popup .black-bg {
  position: fixed;
  width: 110%; height: 110%;
  right: -1%;
  background: rgba(0, 0, 0, 0.5);
}
#login-popup .login-popup-content {
  position: relative;
  display: flex; flex-direction: column;
  align-items: center;
  width: 700px; height: 450px;
  background: white;
  border-radius: 20px;
  margin: auto;
  padding: 0px 50px;
}
#login-popup .popup-title {
  width: 100%;
  padding: 20px 0px;
  border-bottom: 2px solid rgba(138, 101, 242, 0.44);
  color: #4D24A4;
  font-size: 20px;
  font-weight: 700;
}
#login-popup .login-form-hide {
  position: absolute;
  top: 24px; right: 50px;
  width: 19px; height: 19px;
  border-radius: 50px;
}
#login-popup .login-form-hide:hover {
  background-color: rgb(0, 0, 0, 0.08);
  cursor: pointer;
}
#login-popup .hide-btn {
  width: 12px; height: 12px;
  margin: auto;
}
#login-popup .login-popup-content .form-container {
  width: 100%; height: 100%;
  display: flex; flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 0;
}
#login-popup .guide-to-login {
  width: 100%;
  margin: 0;
  font-size: 15px;
  color: #606060;
}
#login-popup .login-form{
  display: flex; flex-direction: column;
  gap: 20px;
  width: 350px;
  margin: 40px 0;
}
#login-popup .login-form p{
  float: left;
  margin-left: 25px;
}
#login-popup .login-form input{
  width: 100%; height: 35px;
  border: 1px solid #C4C4C4;
  border-radius: 10px;
  font-family: Arial, Helvetica, sans-serif !important;
}
#login-popup .login-form .login-guide {
  display: block;
  float: left;
  margin: 5px 0 0 10px;
  color: #F93079;
  font-size: 12px;
}
.signup-guide-container {
  margin-bottom: 20px;
}
.guide-to-signup {
  color: #606060;
}
.move-to-signup {
  display: inline-block;
  margin-left: 5px;
  color: #765FD7;
  font-weight: 700;
  cursor: pointer;
}
#find-user-id {
  width: 100%;

}
.login-btn {
  cursor: pointer;
}
.find-container {
  font-size: 14px;
  margin-top: 15px;
}
.find-container span {
  margin: 0 5px;
  cursor: pointer;
}
@media screen and (max-width : 700px) {
  #login-popup .login-popup-content {
    width : 600px
  }
}
</style>