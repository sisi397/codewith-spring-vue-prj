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
            <div class="left-section-mypage">
                <!--userImage값이 들어왔을경우-->
                <img v-bind:src="userImage" v-if="userImage!=null" class="user-img">
                <!--userImage값이 null일경우 나타나게 할 디폴트 이미지-->
                <img src="../assets/img_profile-default.svg" v-if="userImage==null||userImage==undefined" class="user-img">     
                <button type="button" class="input-btn">
                    <input type="file" id="input-file" src="" v-on:change="addUserImg" accept="" style="display:none">
                    <label img="../assets/btn_plus.svg" for="input-file">
                        <img src="../assets/btn_plus.svg" alt="add-image" class="add-img" >
                    </label>
                </button>
                <h2>{{login.userName}}</h2>
                <button type="button" class="save-btn" @click="imageUpload">Save</button>
            </div>
            <div class="right-section-mypage">
                <div class="select-step">
                    <button type="button" class="stage-ing-btn" @click="selectIng">진행중인 단계</button>
                    <button type="button" class="step-finish-btn" @click="selectFinish">완료한 단계</button>
                    <button type="button" class="scrap-btn" @click="selectScrap">스크랩</button>
                </div>
                <div class="step-lists">

                    <div class="stage-ing" v-if="stageIngState == true">
                        <ol class="step-list" v-for="(setpWich, index) in stageIng" :key="index">
                            <li v-if="stageIng[index].course===1">HTML</li>
                            <li v-if="stageIng[index].course===2">CSS</li>
                            <li v-if="stageIng[index].course===3">JAVASCRIPT</li>
                            <li v-if="stageIng[index].stage===1">튜토리얼</li>
                            <li v-if="stageIng[index].stage===2">달력만들기</li>
                            <li v-if="stageIng[index].stage===3">회원가입창 만들기</li>
                            <li>{{stageIng[index].modifiedAt.substr(0,10)}}</li>
                        </ol>
                    </div>
                    
                    <div class="stage-finish" v-if="stageFinishState == true">
                        <ol class="step-list" v-for="(setpWich, index) in stageFinish" :key="index">
                            <li v-if="stageFinish[index].course===1">HTML</li>
                            <li v-if="stageFinish[index].course===2">CSS</li>
                            <li v-if="stageFinish[index].course===3">JAVASCRIPT</li>
                            <li v-if="stageFinish[index].stage===1">튜토리얼</li>
                            <li v-if="stageFinish[index].stage===2">달력만들기</li>
                            <li v-if="stageFinish[index].stage===3">회원가입창 만들기</li>
                            <li>{{stageFinish[index].modifiedAt.substr(0,10)}}</li>
                        </ol>
                    </div>
                    
                    <div class="step-scrap" v-if="stageScrapState == true">
                        <ol class="step-list" v-for="(setpWich, index) in scrap" :key="index">
                            <li v-if="scrap[index].course===1">HTML</li>
                            <li v-if="scrap[index].course===2">CSS</li>
                            <li v-if="scrap[index].course===3">JAVASCRIPT</li>
                            <li v-if="scrap[index].stage===1">튜토리얼</li>
                            <li v-if="scrap[index].stage===2">달력만들기</li>
                            <li v-if="scrap[index].stage===3">회원가입창 만들기</li>
                            <li>{{scrap[index].modifiedAt.substr(0,10)}}</li>
                        </ol>
                    </div>
                </div>
            
            </div>
        </body>
    </div>
</template>

<script>
import axios from 'axios'
import HeaderLight from "../components/layout/Header-light.vue"
import LoginPopup from "../components/layout/login-popup.vue"

//import axios from "axios"

export default {
    name : 'Mypage',
    components : {
        HeaderLight, LoginPopup
    },
    //넘겨주는 데이터 변수명 앞에 언더바 붙이기
    data() {
        return {
            loginPopupState : 0,
            userImage : null,
            login : {
                loginState : 0, //0은 로그인이 안 된 상태, 1은 로그인이 된 상태
                userName : 'user'
            },
            userId : '',
            memo : [],
            course : [],
            stage : [],
            stageIng : [],
            stageFinish :   [],
            scrap : [],
            stageIngState : true,
            stageFinishState : false,
            stageScrapState  : false

        }
    },
    created() { //로그인이 된 상태 가정, db에 회원정보 넘어간 상태 
        this.login.loginState = JSON.parse(localStorage.getItem('loginState'));
        this.login.userName = localStorage.getItem('userName');    
        console.log("created실행");
        console.log(this.login.userName);
        
        axios
        .get("http://3.36.131.138/memberInfo")
        .then(res => {
          this.userId = res.data.userId;
        })
        .catch(err => {
          console.log(err);
        })
        axios
        .get("http://3.36.131.138/api/stageIng")
        .then(res => {
            this.stageIng = res.data;
            console.log(res);
        })
        .catch(err => {
          console.log(err);
        })
        axios
        .get("http://3.36.131.138/api/stageFinish")
        .then(res => {
          this.stageFinish = res.data;
          console.log(res);
        })
        .catch(err => {
          console.log(err);
        })
        axios
        .get("http://3.36.131.138/api/scrap")
        .then(res => {
          this.scrap = res.data;
          console.log(res);
        })
        .catch(err => {
          console.log(err);
        })
        this.userImg(); //이미지 받아오기 실행
    },
    methods : {
        loginOpen() {
            this.loginPopupState = 1;
        },
        loginClose() {
            this.loginPopupState = 0;   
        },
        //버튼 눌렀을시 나타날 stage 목록 설정 위한 함수들
        selectIng() { 
            this.stageIngState = true;
            this.stageFinishState = false;
            this.stageScrapState = false;
        },
        selectFinish() {
            this.stageIngState = false;
            this.stageFinishState = true;
            this.stageScrapState = false;
        },
        selectScrap() {
            this.stageIngState = false;
            this.stageFinishState = false;
            this.stageScrapState = true;
        },
        addUserImg(event) {
            var file = event.target.files[0];
            this.userImage = window.URL.createObjectURL(file);
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
            console.log("imageUpload 실행 성공");
            console.log(res);
            this.userImg();
          })
          .catch(err=>{
            console.log(err);
          })
        },
        userImg() { //user 이미지 받아오기 
            console.log("userImg 받아오기 실행");
            axios
            .get('http://3.36.131.138/api/userImg')
            .then(res => {
                console.log(res);
                console.log(res.data);
                if(res.data != null) {
                    this.userImage = "/home/ubuntu/images" + res.data;
                }
                this.userImage = null;   
            })
            .catch(err => {
                console.log(err);
            })
        }
    } //methods 끝

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
    width: 88vw; ; height: 80vh;
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
    width: 400px; height: auto;
    margin-top: 160px;
}
.left-section-mypage h2 {
    width: 240px;
    margin: auto auto;
    padding: 13px 0 13px 0;
    font-size: 24px;
    font-weight: normal;
    border-top: #8944FA thin solid;
    border-bottom: #8944FA thin solid;
}
.user-img {
    width:230px; height: 230px;
    display: flex;
    margin: auto;
    border-radius: 50%;
    position: relative;
}

.add-img {
    width:60px; height:60px;
    position: absolute;
    top: 3px; right: 1px;
}
.input-btn:hover, add-img:hover {
    cursor:pointer;
}

.input-btn {
    width: 60px; height: 60px;
    position: relative;
    top: -79px; left: 239px;
}

.save-btn {
    background: none;
    border: thin solid #8944FA; border-radius: 25px;
    width: 90px; height: 35px;
    margin: 30px auto;
    font-size: 18px; color: #535353;
    
    background-color: white;
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
    width: 597px;
    margin-right:6px; margin-bottom: 15px;
    
}
.select-step button {
    font-size: 20px;
    font-weight:normal;
    color: black;
    width: 130px; height: 30px;
    position: relative;
}
.stage-ing-btn::after {
    content:'';
    display: flex;
    position: relative;
    top: 18px; right: 45px;
    width: 200px; height: 2px;
    background: var(--color_light-purple);
    transform: translateX(0) scaleX(0);
    -webkit-transform: translateX(0) scaleX(0);
    transition: transform .3s;
    -webkit-transition: transform .3s;
}
.stage-ing-btn:hover::after{
    transform: translateX(0) scaleX(1.1);
    -webkit-transform: translateX(0) scaleX(1.1);
    transition: transform .3s;
    -webkit-transition: transform .3s;
}
.step-finish-btn::after {
    content:'';
    display: flex;
    position: relative;
    top: 18px; right: 38px;
    width: 200px; height: 2px;
    background: var(--color_light-purple);
    transform: translateX(0) scaleX(0);
    -webkit-transform: translateX(0) scaleX(0);
    transition: transform .3s;
    -webkit-transition: transform .3s;
}
.step-finish-btn:hover::after{
    transform: translateX(0) scaleX(1.1);
    -webkit-transform: translateX(0) scaleX(1.1);
    transition: transform .3s;
    -webkit-transition: transform .3s;
}
.scrap-btn::after {
    content:'';
    display: flex;
    position: relative;
    top: 18px; right: 32px;
    width: 200px; height: 2px;
    background: var(--color_light-purple);
    transform: translateX(0) scaleX(0);
    -webkit-transform: translateX(0) scaleX(0);
    transition: transform .3s;
    -webkit-transition: transform .3s;
}
.scrap-btn:hover::after{
    transform: translateX(0) scaleX(1.1);
    -webkit-transform: translateX(0) scaleX(1.1);
    transition: transform .3s;
    -webkit-transition: transform .3s;
}

.step-lists {
    /* display: flex;
    flex-direction: row;
    justify-content: space-between; */
    width: 700px; height: 560px;
    border-top: solid thin var(--color_dark-gray);
    border-bottom: solid thin var(--color_dark-gray);
    overflow: scroll;
    
}

.list-item {
    display:flex;
    width: 700px;
    font-size: 20px;
    flex-direction: row;
    justify-content:space-around;
    border-bottom: var(--color_gray) thin solid;

}

.step-list{
    width: 700px;
    display: flex;
    flex-direction: row;
    justify-content:space-around;
    border-bottom: var(--color_gray) thin solid;
    background: rgba(222, 228, 234, 0.473);
    margin: 0;
    padding: 20px 0 20px 0;
    

}
.step-list li{
    list-style: none;
    width: 138px;
    list-style: none;
    font-size: 19px;
    /* margin: auto 40px auto 40px; */
}

</style>