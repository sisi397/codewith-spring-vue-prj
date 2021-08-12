<template>
    <div id="home">
        <Background />
        <LoginPopup
            v-if="loginPopupState == 1" 
            :_loginPopupState = "loginPopupState"
            :_loginState = "login.loginState"
            :_userName = "login.userName"
            @_loginClose="loginClose"
            @_completeLogin = "changeLoginState"
        ></LoginPopup>
        <CoursePopup
            v-if="coursePopupState == 1"
            :_userName = "login.userName"
            :_selectCourse = "selectCourse"
            @_courseClose = "courseClose"
        ></CoursePopup>
        <Header 
            :_loginPopupState = "loginPopupState"
            :_loginState = "login.loginState"
            @_loginOpen="loginOpen"
            @_logout = "logout"
        ></Header>
        <div class="guide">
            <div class="greeting">
            <strong>{{login.userName}}!</strong><br>
            Free Course For Beginner<br>
            당신의 코딩학습에  코드윗이 함께할게요.
            </div>
            <img class="coco" src="../assets/coco_smile.svg" alt="">
        </div>
        <div class="course-container">
            <div class="course" style="align-self: flex-end;">
              <img class="course-moon" src="../assets/img_cresent-moon-white.svg" alt="">
              <div class="course-box" @click="courseOpen(1)">
                  <img src="../assets/course/html.svg" alt="">
                  <div class="course-name">HTML<br>Course</div>
              </div>
            </div>
            <div class="course" style="align-self: center">
              <img class="course-moon" src="../assets/img_half-moon-white.svg" alt="">
              <div class="course-box" @click="courseOpen(2)">
                  <img src="../assets/course/css.svg" alt="">
                  <div class="course-name">CSS<br>Course</div>
              </div>
            </div>
            <div class="course" style="align-self: flex-start">
              <img class="course-moon" src="../assets/img_full-moon-white.svg" alt="">
              <div class="course-box" @click="courseOpen(3)">
                  <img src="../assets/course/js.svg" alt="">
                  <div class="course-name">JavaScript<br>Course</div>
              </div>
            </div>
        </div>
        <div class="course-select">
            <span class="course-select-start">Learing Frontend Course<br></span>
            <span class="course-select-greeting">원하는 학습 단계를 선택하세요!</span>
        </div>
    </div>

</template>

<script>
import Header from "../components/layout/Header-dark.vue"
import Background from "../components/layout/background-main.vue"
import LoginPopup from "../components/layout/login-popup.vue"
import CoursePopup from '../components/layout/course-popup.vue'


export default {
  name: 'App',
  components: {
    Header, Background, LoginPopup,CoursePopup
  },
  data() {
    return {
      selectCourse : 0, // html : 1, css : 2, javascript : 3
      courseImg : ['../assets/img_cresent-moon-white.svg', '../assets/img_half-moon-white.svg', '../assets/img_full-moon-white.svg'],
      courseStyle : ['align-self: flex-end' ,'align-self: center','align-self: flex-start'],
      loginPopupState : 0, //0은 창 닫힌 상태, 1은 창 열린 상태
      // windowTitle : ['HTML', 'CSS', 'JavaScript'],
      coursePopupState : 0, //0은 창 닫힌 상태, 1은 창 열린 상태
      navMenuPopupState : 0, //0은 팝업이 닫힌 상태, 1은 팝업이 열린 상태
      login : {
        loginState : 0, //0은 로그인이 안 된 상태, 1은 로그인이 된 상태
        userName : 'Welcome'
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
    courseOpen(course) {
      this.selectCourse = course;
      this.coursePopupState = 1;
    },
    courseClose() {
      this.coursePopupState = 0;
    },
    changeLoginState(loginState, userName) {
      this.login.loginState = loginState; //loginState 1로 변경(로그인 완료)
      console.log("loginState : ", this.login.loginState);
      this.login.userName = userName + '님'; //환영문구를 welcome!에서 유저이름!으로 변경
    },
    logout(loginState) {
      this.login.loginState = loginState; //loginState 0으로 변경(로그아웃)
      this.login.userName = "welcome";
      console.log("로그아웃합니다. / loginState : ", this.login.loginState);
    }
  }
}
</script>

<style>
:root {
    /* color */
    --color_main-purple : #8944FA;
    --color_light-purple : #8944FA;
    --color_dark-purple : #4C1993;
    --color_navy : #30355C;
    --color_point-mint : #00D1FF;

    --color_white : #FFFFFF;
    --color_black : #000000;
    --color_light-gray : #F5F5F5;
    --color_gray : #D2D2D2;
    --color_dark-gray : #898A8D;
}
#home {
    width: 100%;
    height: 100%;
    background-image: url("../assets/background-main.png");
    background-size: cover;
    background-repeat: no-repeat;
    padding: 20px;
    padding-bottom: 40px;
}
div {
  box-sizing: border-box;
}
h4 {
  margin: 0px;
}

/* 안내 문구(with 코코) */
.guide {
  text-align: left;
  margin-top: 60px;
  margin-left: 190px;
}
.guide .greeting {
  display: inline-block;
  color: var(--color_white);
  text-align: left;
}
.guide .coco{
  width: 150px;
}
/* html, css, javascript 코스 박스들 css */
.course-container {
  height: 380px;
  display: flex; gap: 90px;
  justify-content: center;
  margin-top: 50px;
}
.course-container .course .course-moon {
  opacity: 0.6;
}
.course-container .course-box {
  width: 220px; height: 250px;
  background-color: var(--color_white);
  margin-top: 20px; padding: 25px;
  border-radius: 15px;
  box-shadow: 10px 10px 10px rgba(0, 0, 0, 0.11);
  cursor: pointer;
}
.course-container .course-box img{
  width: 100px;
}
.course-container .course .course-box .course-name {
  color: #818181;
  margin-top: 30px;
  font-size: 20px;
  font-weight: 850;
}
  /* 코스 박스들 hover 효과 */
  .course:hover {
    transform: translateY(-10px);
  }
  .course:hover .course-moon{
    opacity: 1;
  }
/* 코스 선택하라는 안내 */
.course-select {
  width: 600px;
  float: right;
  margin: -30px 220px 0 0;
  text-align: right;
  font-size: 20px;
}
.course-select .course-select-start {
  color: #5D3CBB;
  font-weight: 600;
}
.course-select .course-select-greeting {
  color: #8B8B8C;
}
@media screen and (max-width : 1040px) {
  body {
    overflow-y: scroll;
  }
  #header-light .nav-menu {
    display: none;
  }
  .guide {
    display: flex;
    justify-content: center;
    margin: 60px 0px;
  }
  .guide .coco {
    display: none;
  }
  .course-container {
    flex-direction: column;
    justify-content: flex-start;
    gap: 10px;
    width: 220px; height: 100%;
    margin: auto;
  }
  .course-container .course {
    align-self: center;
  }
  .course-container .course-moon {
    display: none;
  }
  .course-select {
    display: none;
  }
}

</style>