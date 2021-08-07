<template>
    <div id="mypage">
        <LoginPopup
            v-if="loginPopupState == 1" 
            :_loginPopupState = "loginPopupState"
            @_loginClose="loginClose"
        ></LoginPopup>
        <HeaderLight
            :_loginPopupState = "loginPopupState"
            @_loginOpen = "loginOpen"
        ></HeaderLight>
        <body>
        <form class="form-container" @submit.prevent="imageUpload">
            <div class="left-section-mypage">
                <img src="{{userImage}}" class="user-img" alt="profile-img">
                <button type="button" class="input-btn">
                    <label img="../assets/btn_plus.svg" for="input-file">
                        <img src="../assets/btn_plus.svg" alt="add-image" class="add-img" >
                    </label>
                    <input type="file" id="input-file" src="../assets/btn_plus.svg" accept="" style="display:none">
                </button>
                <button type="submit"><img class="signup-btn" src="../assets/btn_signup.svg" alt=""></button>
                <h2>{{userName}}</h2>
            </div>
        </form>
            <div class="right-section-mypage">
                <div class="select-step">
                    <button type="button" class="step-in-progress-btn" @click="stepInProgressState = true, stepCompleteState = false, stepScrappedState = false">진행중인 단계</button>
                    <button type="button" class="step-complete-btn" @click="stepInProgressState = false, stepCompleteState = true, stepScrappedState = false">완료한 단계</button>
                    <button type="button" class="step-scrapped-btn" @click="stepInProgressState = false, stepCompleteState = false, stepScrappedState = true">스크랩</button>
                </div>
                <div class="step-lists">
                    <div class="step-in-progress" v-if="stepInProgressState == true">
                        <ol class="step-list" v-for="(setpWich, index) in stepInProgress" :key="index">
                            <li v-if="stepInProgress[index].course===1">HTML</li>
                            <li v-if="stepInProgress[index].course===2">CSS</li>
                            <li v-if="stepInProgress[index].course===3">JAVASCRIPT</li>
                            <li v-if="stepInProgress[index].stage===1">튜토리얼</li>
                            <li v-if="stepInProgress[index].stage===2">달력만들기</li>
                            <li v-if="stepInProgress[index].stage===3">회원가입창 만들기</li>
                            <li>{{stepInProgress[index].modifiedAt}}</li>
                        </ol>
                    </div>
                    
                    <div class="step-complete" v-if="stepCompleteState == true">
                        <ol class="step-list" v-for="(setpWich, index) in stepComplete" :key="index">
                          <li v-if="stepComplete[index].course===1">HTML</li>
                          <li v-if="stepComplete[index].course===2">CSS</li>
                          <li v-if="stepComplete[index].course===3">JAVASCRIPT</li>
                          <li v-if="stepComplete[index].stage===1">튜토리얼</li>
                          <li v-if="stepComplete[index].stage===2">달력만들기</li>
                          <li v-if="stepComplete[index].stage===3">회원가입창 만들기</li>
                          <li>{{stepComplete[index].modifiedAt}}</li>
                        </ol>
                    </div>
                    
                    <div class="step-scrapped" v-if="stepScrappedState == true">
                        <ol class="step-list" v-for="(setpWich, index) in stepScrapped" :key="index">
                          <li v-if="stepScrapped[index].course===1">HTML</li>
                          <li v-if="stepScrapped[index].course===2">CSS</li>
                          <li v-if="stepScrapped[index].course===3">JAVASCRIPT</li>
                          <li v-if="stepScrapped[index].stage===1">튜토리얼</li>
                          <li v-if="stepScrapped[index].stage===2">달력만들기</li>
                          <li v-if="stepScrapped[index].stage===3">회원가입창 만들기</li>
                          <li>{{stepScrapped[index].modifiedAt}}</li>
                        </ol>
                    </div>
                </div>
            </div>
        </body>
    </div>
</template>

<script>
import HeaderLight from "../components/layout/Header-light.vue"
import LoginPopup from "../components/layout/login-popup.vue"
import axios from "axios";
//import axios from "axios"

export default {
    name : 'Mypage',
    components : {
        HeaderLight, LoginPopup
    },
    data() {
        return {
            loginPopupState : 0,
            userImage : "../assets/img_profile-default.svg",
            userName : '',
            stepInProgress : [],
            stepComplete : [],
            stepScrapped : [],
            stepInProgressState : true,
            stepCompleteState : false,
            stepScrappedState  : false
        }
    },
  created() {
    axios
        .get("http://3.36.131.138/memberInfo")
        .then(res => {
          this.userName = res.data.userId;
          console.log(res.data.userId);
        })
        .catch(err => {
          console.log(err);
        })
    axios
        .get("http://3.36.131.138/api/stageIng")
        .then(res => {
          this.stepInProgress = res.data;
          console.log(res);
          console.log(this.stepInProgress[0].userId);
          console.log(this.stepInProgress[1].course);
        })
        .catch(err => {
          console.log(err);
        })
    axios
        .get("http://3.36.131.138/api/stageFinish")
        .then(res => {
          this.stepComplete = res.data;
          console.log(res);
        })
        .catch(err => {
          console.log(err);
        })
    axios
        .get("http://3.36.131.138/api/scrap")
        .then(res => {
          this.stepScrapped = res.data;
          console.log(res);
        })
        .catch(err => {
          console.log(err);
        })
    this.userImg();
  },
  methods : {
        loginOpen() {
            this.loginPopupState = 1;
        },
        loginClose() {
            this.loginPopupState = 0;
        },
        imageUpload(){
          var frm = new FormData();
          var photoFile = document.getElementById("input-file");
          frm.append("file", photoFile.files[0]);
          axios
          .post("http://3.36.131.138/api/userImg", frm, {
            headers: { 'Content-Type': 'multipart/form-data' }
          })
          .then(res=>{
            console.log(res);
            this.userImg();
          })
          .catch(err=>{
            console.log(err);
          })
        },
      userImg(){
        axios
            .get("http://3.36.131.138/api/userImg")
            .then(res=>{
              console.log(res);
              console.log(res.data);
              if(res.data==null){
                this.userImage="/home/ubuntu/images/53df9f25-6cc3-4a53-8ae7-771d594be0a5_캡처.JPG"
              }else{
                this.userImage = "/home/ubuntu/images" + res.data
              }
            })
            .catch(err=>{
              console.log(err);
            })
      },
    }
}
</script>

<style>

:root {
    --color_main-purple: #8944FA;
    --color_light-purple: #8944FA;
    --color_dark-purple: #4C1993;
    --color_navy: #30355C;
    --color_point-mint: #00D1FF;
    --color_white: #FFFFFF;
    --color_black: #000000;
    --color_light-gray: #F5F5F5;
    --color_gray: #D2D2D2;
    --color_dark-gray: #898A8D;
}



#mypage {
    padding: 20px;
    
}

#mypage body {
    display: flex;
    flex-direction: row;
    width: 88vw; ; height: 90vh;
    justify-content: space-between;
    margin: auto; 
}



/*left section 관련*/

.left-section-mypage{
    display: flex;
    flex-direction: column;
    align-content: center;
    align-content: center;
    justify-content: center;
    width: 400px; height: 400px;
    margin-top: 160px;
}
.left-section-mypage h2 {
    width: 240px;
    margin: auto auto;
    padding: 13px 0 13px 0;
    font-size: 24px;
    font-weight: normal;
    border-top: #6F47FF thin solid;
    border-bottom: #6F47FF thin solid;
}
.user-img {
    width:240px; height: 240px;
    display: flex;
    margin: auto;
}

.add-img {
    width:60px; height:60px;
    position: relative;
}
.input-btn:hover {
    cursor:pointer;
}
.input-btn {
    width: 60px; height: 60px;
    position: relative;
    top: -63px; left: 243px;
}


/*right section 관련*/

.right-section-mypage {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 700px;
    margin: 60px auto;
}


.select-step {
    display: flex;
    flex-direction: row;
    justify-content:space-between;
    width: 633px;
    margin-right:6px; margin-bottom: 15px;
    
}
.select-step button {
    font-size: 21px;
    font-weight:normal;
    color: black;
    width: 130px; height: 30px
}
.step-in-progress-btn::after {
    content:'';
    display: flex;
    position: relative;
    top: 17px; right: 38px;
    width: 200px; height: 2px;
    background: var(--color_light-purple);

}

.step-lists {
    /* display: flex;
    flex-direction: row;
    justify-content: space-between; */
    width: 700px; height: 560px;
    border-top: solid thin var(--color_dark-gray);
    border-bottom: solid thin var(--color_dark-gray);
}
.step-list li {
    display:flex;
    font-size: 20px;
    width: 200px;
    flex-direction: row;
    justify-content: center;

}
.step-list{
    width: 700px;
    display: flex;
    flex-direction: row;
    justify-content:space-between;
    margin: 0; padding: 0;
    padding-top: 13px; padding-bottom: 13px;
    border-bottom: var(--color_gray) thin solid;

}
</style>