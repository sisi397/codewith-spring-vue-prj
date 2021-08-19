<template>
  <!--html에서는 띄어쓰기 하지 말기-->
  <div id="training">
    <LoginPopup
        v-if="loginPopupState == 1" 
        :_loginPopupState = "loginPopupState"
        @_loginClose="loginClose"
        @_completeLogin = "changeLoginState"
    ></LoginPopup>
    <section class="left-section">
      <div class="left-header">
        <router-link class="title-btn" :to="{ name: 'Home' }">CodeWith</router-link>
        <div class="explain-answer">
          <button type="button" @click="modale = true" class="explain-btn">
            설명 다시보기
            <img src="../assets/triangular.svg" class="triangle" alt="설명 다시보기 버튼"/>
          </button>
          <button type="button" @click="showAnswer = true" class="answer-btn">
              정답코드 보기
              <img src="../assets/triangular.svg" class="triangle" alt="정답코드 보기 버튼"/> 
          </button>
          <div class="answer" v-if="showAnswer === true">
            <!--정답코드 보여지는 부분-->
            <button type="button" class="x-btn" @click="showAnswer = false">
              X
            </button>
            <img v-if="selectCourseData.course == 1" :src="answerImgHtml[selectCourseData.stage-1]">
            <img v-if="selectCourseData.course == 2" :src="answerImgCss[selectCourseData.stage-1]">
            <img v-if="selectCourseData.course == 3" :src="answerImgJs[selectCourseData.stage-1]">
          </div>
        </div>
      </div>
      <button type="button" @click="saveScrap()" v-if="bookmarkState === false">
        <img class="bookmark" src="../assets/bookmark-regular.svg">
      </button>
      <button type="button" @click="deleteScrap()" v-if="bookmarkState === true">
        <img class="bookmark" src="../assets/bookmark-solid.svg" >
      </button>
      <!--북마크 체크되어있을때 true, 아니면 false-->
      <!-- 코드 에디터 부분 -->
      <div id="code-editor">
          <codeEditorHtml v-if="selectCourseData.course==1"></codeEditorHtml>
          <codeEditorHtml v-if="selectCourseData.course==undefined "></codeEditorHtml>
          <codeEditorCss1 v-if="selectCourseData.course==2&&selectCourseData.stage==1"></codeEditorCss1>
          <codeEditorCss2 v-if="selectCourseData.course==2&&selectCourseData.stage==2"></codeEditorCss2>
          <codeEditorCss3 v-if="selectCourseData.course==2&&selectCourseData.stage==3"></codeEditorCss3>
          <codeEditorJavascript1 v-if="selectCourseData.course==3&&selectCourseData.stage==1"></codeEditorJavascript1>
          <codeEditorJavascript2 v-if="selectCourseData.course==3&&selectCourseData.stage==2"></codeEditorJavascript2>
          <codeEditorJavascript3 v-if="selectCourseData.course==3&&selectCourseData.stage==3"></codeEditorJavascript3>    
      </div>
      
      <footer>
        <div class="form-buttons">
          <button type="submit" class="submit" @click="onComplete()">완성했어요</button>
          <!-- <button type="button" class="reset" @click="reload">초기화</button> -->
        </div>
        <div class="page-buttons">
          <button type="button" class="prev-page" @click="pageBefore">
            <img
              src="../assets/chevron-left-solid.svg"
              alt="go to the page before"
            />
          </button>
          <button type="button" class="next-page" @click="pageNext">
            <img
              src="../assets/chevron-right-solid.svg"
              alt="go to the next page"
            />
          </button>
        </div>
      </footer>
    </section>
    <section class="right-section">
      <nav>
        <div class="right-header-btn">
          <!--로그인 하지 않았을 경우 보임-->
          <button type="button" class="login-btn" v-if="login.loginState == 0" @click="loginOpen">
            Login
          </button>
          <router-link type="button" class="sign-up-btn" v-if="login.loginState == 0" :to="{ name: 'Signup' }" @click="$router.push({name: 'Signup'})">
            Sign-Up
          </router-link>
          <!--로그인 했을 경우 보임-->
          <router-link class="mypage-btn" v-if="login.loginState == 1" :to="{ name: 'Mypage' }" @click="$router.push({name: 'Mypage'})">
            Mypage
          </router-link>
        </div>
        <h3>{{ courseName[selectCourseData.course-1] }} Step.{{ stepName[selectCourseData.stage-1] }}</h3>
      </nav>
      <div class="goal">
        <button type="button" class="goal-btn" @click="goalBtnState = true">
          정답화면 펼치기
          <img
            src="../assets/triangular.svg"
            class="triangle"
            alt="완성화면 펼치기 버튼"
          />
        </button>
      </div>
      <div class="completeResult" v-if="goalBtnState == true">
        <button type="button" class="x-btn" @click="goalBtnState = false">
          X
        </button>
        <div class="complete-img">
          <img v-if="selectCourseData.course == 1" :src="completeImgHtml[selectCourseData.stage-1]">
          <img v-if="selectCourseData.course == 2" :src="completeImgCss[selectCourseData.stage-1]">
          <img v-if="selectCourseData.course == 3" :src="completeImgJs[selectCourseData.stage-1]">
        </div>
      </div>
      <div class="memo">
        <input type="text" placeholder=" + Memo Here: " maxlength="18" id="user-input" v-model="memo" @keyup.enter="addNewMemo(memo)">
        <div class="memo-group"> <!--사용자 input 나타내기--> 
          <ul class="memo-list">
            <li v-for="(memo, index) in memoList" v-bind:key="memo">
              {{ memo }}
              <button class="delete-memo" type="button" @click="deleteMemo(index)" >X</button>
            </li>
          </ul>
          <button type="button" class="clearMemo" @click="clearMemo">
            <img class="trash-img" src="../../src/assets/trash.svg">
          </button>
          
        </div>
      </div>
      
    </section>

    <!--모달창(설명 뜨는부분)-->
    <div class="black-bg" v-if="modale === true">
      <div class="white-bg-training">
        <!--설명창 title관련-->
        <h1 v-if="selectCourseData.course == 1">{{ exTitleHtml[selectCourseData.stage-1][nextBtnCount] }}</h1>
        <h1 v-if="selectCourseData.course == undefined">Undefined임..</h1>
        <h1 v-if="selectCourseData.course == 3">{{ exTitleJs[selectCourseData.stage-1][nextBtnCount] }}</h1>
        <h1 v-if="selectCourseData.course == 2">{{ exTitleCss[selectCourseData.stage-1][nextBtnCount]}}</h1>
        <img src="../assets/coco_smile.svg" />
        <!--설명창 본문 관련-->
        <div class="white-bg-p">
          <p v-html="exData[nextBtnCount]" v-if="selectCourseData.course == 1"></p>
          <p v-if="selectCourseData.course == undefined">undefined</p>
          <p v-html="exData[nextBtnCount] " v-if="selectCourseData.course == 2"></p>
          <p v-html="exData[nextBtnCount]" v-if="selectCourseData.course == 3"></p>
        </div>
        <div class="white-bg-btns">
          <div class="white-bg-shift-btn">
            <button type="button" class="prev-ex" @click="explainBefore">
              <img
                src="../assets/chevron-left-solid.svg"
                alt="go to the page before"
              />
            </button>
            <button type="button" class="next-ex" @click="explainNext" v-if="this.nextBtnCount != 2">
              <img
                src="../assets/chevron-right-solid.svg"
                alt="go to the next page"
              />
            </button>
          </div>
          <button type="button" @click="modale = false" v-if="this.nextBtnCount != 2" class="skip-btn">
            설명 Skip
          </button>
          <button type="button" @click="explainEnd" v-if="this.nextBtnCount == 2" class="skip-btn">
            코딩 시작!
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import codeEditorHtml from "../components/layout/code-editor-html.vue"
import codeEditorCss1 from "../components/layout/code-editor-css-1.vue"
import codeEditorCss2 from "../components/layout/code-editor-css-2.vue"
import codeEditorCss3 from "../components/layout/code-editor-css-3.vue"
import codeEditorJavascript1 from "../components/layout/code-editor-javascript-1.vue"
import codeEditorJavascript2 from "../components/layout/code-editor-javascript-2.vue"
import codeEditorJavascript3 from "../components/layout/code-editor-javascript-3.vue"
import axios from 'axios'
import LoginPopup from "../components/layout/login-popup.vue"

//정답화면 이미지
var imgHtml1 = require("../../src/assets/resultImg/1-1img.png");
var imgHtml2 = require("../../src/assets/resultImg/1-2img.png");
var imgHtml3 = require("../../src/assets/resultImg/1-3img.png");
var imgCss1 = require("../../src/assets/resultImg/2-1img.png");
var imgCss2 = require("../../src/assets/resultImg/2-2img.png");
var imgCss3 = require("../../src/assets/resultImg/2-3img.png");
var imgJs1 = require("../../src/assets/resultImg/3-1img.png");
var imgJs2 = require("../../src/assets/resultImg/3-2img.png");
var imgJs3 = require("../../src/assets/resultImg/3-3img.png");
//정답코드 이미지
var answerHtml1 = require("../../src/assets/answerImg/1-1answer.png");
var answerHtml2 = require("../../src/assets/answerImg/1-2answer.png");
var answerHtml3 = require("../../src/assets/answerImg/1-3answer.png");
var answerCss1 = require("../../src/assets/answerImg/2-1answer.png");
var answerCss2 = require("../../src/assets/answerImg/2-2answer.png");
var answerCss3 = require("../../src/assets/answerImg/2-3answer.png");
var answerJs1 = require("../../src/assets/answerImg/3-1answer.png");
var answerJs2 = require("../../src/assets/answerImg/3-2answer.png");
var answerJs3 = require("../../src/assets/answerImg/3-3answer.png");


export default {
    name : 'Training',
    props : ['_userId', '_course', '_stage', '_loginState', '_loginPopupState'],
    
    components : {
        codeEditorHtml, codeEditorCss1, codeEditorCss2, codeEditorCss3, codeEditorJavascript1, codeEditorJavascript2, codeEditorJavascript3, LoginPopup
    },
    
    data() {
      return {
        modale: true,
        showAnswer: false,
        goalBtnState: false,
        bookmarkState: false,
        loginPopupState : 0,
        nextBtnCount: 0,
        exTitleCount: 0,
        memoList: [],
        memo: "",
        login : {
          loginState : 0, //0은 로그인이 안 된 상태, 1은 로그인이 된 상태
          userName : 'Welcome'
        },
        selectCourseData : { //사용자가 선택한 코스와 관련된 데이터 + 유저 아이디
          course: 1,
          stage: 1,
        },
        userId: "",

        stepNum: ["1", "2", "3"],
        courseName: ["HTML", "CSS", "JS"],
        stepName: ["1 튜토리얼", "2 달력 만들기", "3 회원가입 창"],

        //완성 이미지
        completeImgHtml: [imgHtml1, imgHtml2, imgHtml3],
        completeImgCss: [imgCss1, imgCss2, imgCss3],
        completeImgJs: [imgJs1, imgJs2, imgJs3],
        //정답 코드 이미지
        answerImgHtml:[answerHtml1, answerHtml2, answerHtml3],
        answerImgCss: [answerCss1, answerCss2, answerCss3],
        answerImgJs: [answerJs1, answerJs2, answerJs3],
        //설명 데이터 
        exDataHtml: [["content1","content2", "content3"], ["content4", "content5", "content6"], ["content7", "content8", "content9"]],
        exDataCss: [["content1","content2", "content3"], ["content4", "content5", "content6"], ["content7", "content8", "content9"]],
        exDataJs: [["content1","content2", "content3"], ["content4", "content5", "content6"], ["content7", "content8", "content9"]],
        exData: ["", "", ""],
        exTitleHtml: [
          [
            "HTML Step 1 튜토리얼 진행을 환영합니다!",
            "HTML Step 1 튜토리얼 진행을 환영합니다!",
            "HTML Step 1 튜토리얼 진행을 환영합니다!"
          ],
          [
            "HTML Step 2 달력 만들기 단계를 진행합니다!",
            "HTML Step 2 달력 만들기 단계를 진행합니다!",
            "HTML Step 2 달력 만들기 단계를 진행합니다!"
          ],
          [
            "HTML Step 3 회원가입 창 만들기 단계를 진행합니다!",
            "HTML Step 3 회원가입 창 만들기 단계를 진행합니다!",
            "HTML Step 3 회원가입 창 만들기 단계를 진행합니다!"
          ]
        ],
        exTitleCss: [
          [
            "CSS Step 1 튜토리얼 단계를 진행합니다!",
            "CSS Step 1 튜토리얼 단계를 진행합니다!",
            "CSS Step 1 튜토리얼 단계를 진행합니다!",
          ],
          [
            "CSS Step 2 달력 만들기 단계를 진행합니다!",
            "CSS Step 2 달력 만들기 단계를 진행합니다!",
            "CSS Step 2 달력 만들기 단계를 진행합니다!"
          ],
          [
            "CSS Step 3 회원가입 창 만들기 단계를 진행합니다!",
            "CSS Step 3 회원가입 창 만들기 단계를 진행합니다!",
            "CSS Step 3 회원가입 창 만들기 단계를 진행합니다!"
          ]
        ],  
        exTitleJs: [
          [
            "Javascript Step 1 튜토리얼 단계를 진행합니다!",
            "Javascript Step 1 튜토리얼 단계를 진행합니다!",
            "Javascript Step 1 튜토리얼 단계를 진행합니다!"
          ],
          [
            "Javascript Step 2 달력 만들기 단계를 진행합니다!",
            "Javascript Step 2 달력 만들기 단계를 진행합니다!",
            "Javascript Step 2 달력 만들기 단계를 진행합니다!"
          ],
          [
            "Javascript Step 3 회원가입 창 단계를 진행합니다!",
            "Javascript Step 3 회원가입 창 단계를 진행합니다!",
            "Javascript Step 3 회원가입 창 단계를 진행합니다!"
          ]
        ],
        
      }
    },

    created() {     //home에서 넘어오면서 사용자 course, stage데이터 넘어오도록
      this.selectCourseData.course = JSON.parse(localStorage.getItem('courseData')).course;
      this.selectCourseData.stage = JSON.parse(localStorage.getItem('courseData')).stage;
      this.login.loginState = JSON.parse(localStorage.getItem('loginState'));
      console.log("selectCourseData.course:" + this.selectCourseData.course);
      console.log("selectCourseData.stage:" + this.selectCourseData.stage);
      console.log("loginState:" + this.login.loginState);
  
      this.getHtmlCode();

      if (this.login.loginState == 1) { //로그인이 되었다면 유저 정보 요청해 id 알아오기, post시 이용
        axios
        .get("http://3.36.131.138/memberInfo") //로컬에서는 현재 실행 안될수도 있음 
        .then(res => {
          this.userId = res.data.userId;
          console.log(res);
          console.log("memberInfo실행,userId는:" + this.userId);
          //진행중인 stage 저장, scrap 여부 받아오는것도 로그인 되어있어야 가능
          this.onStageIng();
          this.getScrap();
        })
        .catch(err => {
          console.log(err);
        })
         
      }
    },

    methods: {
      changeLoginState() {
        this.$router.go();
      },
      addNewMemo(memo) {
        //localStorage.setItem(this.memo, JSON.stringify(value));
        if(this.memo != ''){
          this.memoList.push(memo); 
        } //json형식으로 바꿔줌  
        this.memo = ''; //input값 초기화
      },
      deleteMemo(index) {
        //localStorage.removeItem(memo.item);
        this.memoList.splice(index, 1);
      },
      clearMemo() {
        this.memoList = [];
      },
      loginOpen() {
        this.loginPopupState = 1;
      },
      loginClose() {
        this.loginPopupState = 0;   
      },
    
      pageNext(){ //다음 stage로 넘어가는 버튼
        console.log("pagenext실행");
        if(this.login.loginState == 1){
          this.selectCourseData.stage++;  //다음 stage 진행
          this.modale = true; 
        }else{
          this.modale = false;
          if (confirm("다음 단계를 실습하려면 로그인이 필요합니다. 로그인 창으로 이동하시겠습니까?")) {
            this.loginPopupState = 1;
          }
        }
        if (this.selectCourseData.stage == 4) {
          this.selectCourseData.course++; //다음 course로 넘어감
          this.selectCourseData.stage = 1; //다음 course의 stage1부터 시작
          this.modale = true; 
        } 
        if (this.selectCourseData.course == 4) { //3-3에서 pageNext버튼 눌렀을시
          this.selectCourseData.course = 3; //3 초과하는 수는 유효X
          alert("마지막 페이지 입니다!");
          this.modale = false;
          this.selectCourseData.stage = 3; //그대로 머물러 있게 하기 위함 
        }
        this.onStageIng();
        this.bookmarkState = false;
        this.getScrap();
        this.getHtmlCode();
      },

      pageBefore(){ //이전 stage로 돌아가는 버튼
        console.log("pageBefore실행");
        
        this.selectCourseData.stage--;  //이전 stage 진행
        if (this.selectCourseData.stage == 0) {
          this.selectCourseData.course--; //이전 course로 넘어감
          this.selectCourseData.stage = 1; //이전 course의 stage1부터 시작 
        } 
        if (this.selectCourseData.course == 0) { //1-1에서 pageBefore버튼 눌렀을시
          this.selectCourseData.course = 1; //0 이하의 수는 유효X
          alert("첫 페이지 입니다!");
          this.modale = false;
          this.selectCourseData.stage = 1; //그대로 머물러 있게 하기 위함 

        }
        this.onStageIng();
        this.bookmarkState = false;
        this.getScrap();
        this.getHtmlCode();
        this.modale = true; 
      },
      //완성했어요 버튼 클릭시
      onComplete(){
        if (this.login.loginState == 0) {
          alert("완성한 stage 저장을 위해서는 로그인이 필요합니다.");
        }else{
          console.log("oncomplete 함수 실행됨(완성했어요)");
          alert("완료한 단계에 저장되었습니다!");
          axios
          .post('http://3.36.131.138/api/stageFinish', {  //stageFinish (완료 단계) 저장
            userId : this.userId,
            course : this.selectCourseData.course,
            stage: this.selectCourseData.stage
          })
          .then(res => {
            console.log("oncomplete res:" + res);
          })
          .catch(err => {
            console.log(err);
          })
        }
      },
      saveScrap(){   //scrap하기
        if(this.login.loginState == 0) {
          alert("로그인하셔야 스크랩이 가능합니다.");
        }
        if(this.login.loginState == 1 && this.bookmarkState == false) { //로그인이 되어있고, bookmarkstate가 활성화되어있지 않은 상황
            this.bookmarkState = true;
            alert("scrap 되었습니다!");    
            console.log("saveScrap 함수 실행됨");
            axios
            .post('http://3.36.131.138/api/scrap', {
              userId : this.userId,
              course : this.selectCourseData.course,
              stage: this.selectCourseData.stage
            })
            .then(res => {
              console.log(res);
              console.log("saveScrap 실행성공");
            })
            .catch(err => {
              console.log(err);
            })
        }
      },

      deleteScrap(){ 
        this.bookmarkState = false;
        axios
        .delete('http://3.36.131.138/api/scrap/' + this.selectCourseData.course + "/" + this.selectCourseData.stage, {
        })
        .then(res => {
          console.log(res);
          console.log("deleteScrap 실행성공");
        })
        .catch(err => {
          console.log(err);
        }) 
      },

      getScrap(){   //scrap 되어있는지 확인. 페이지 로드 되자마자 실행되어야 함.
        console.log("getScrap 함수 실행됨");
        axios
        .get("http://3.36.131.138/api/scrap" + "/ " + this.selectCourseData.course + "/" + this.selectCourseData.stage )
        .then(res => { //scrap이 되어있는 상태!
          this.bookmarkState = true;
          console.log("getScrap 성공");
          console.log(res);
        })
        .catch(err => { //scrap이 되어있지 않은 상태!
          console.log("getScrap 에러");
          console.log(err);
        })
      },
      onStageIng(){ //바로 실행되어야함 !!
        if(this.login.loginState == 1){ //로그인이 되어있는 경우
          console.log("onStageIng함수 실행됨");
          axios
          .post('http://3.36.131.138/api/stageIng', {  //stageIng (진행중 단계) 저장
            userId : this.userId,
            course : this.selectCourseData.course,
            stage: this.selectCourseData.stage
          })
          .then(res => {
            console.log(res);
            console.log("onStageIng 성공");  
          })
          .catch(err => {
            console.log(err);
          })
        }else{ //로그인 되어있지 않은 경우
          console.log("로그인 되어있지 않아 onstageIng 실행되지 않음");
        }
      },
    
      getHtmlCode(){   //Html코드랑 설명 불러오기. 페이지 로드 되자마자 실행되어야 함.아직 반영 x
        console.log("getHtmlCode 함수 실행됨");
        console.log(this.selectCourseData.course);
        console.log(this.selectCourseData.stage);
          axios
          .get("http://3.36.131.138/api/" + this.selectCourseData.course + "/" + this.selectCourseData.stage) 
            .then(res => {
            console.log("gethtmlcode:");
            console.log(res);
            this.exData[0] = res.data.content1.replace(/(?:\r\n|\r|\n)/g, '<br />').split('\n').join('<br />');
            this.exData[1] = res.data.content2.replace(/(?:\r\n|\r|\n)/g, '<br />').split('\n').join('<br />');
            this.exData[2] = res.data.content3.replace(/(?:\r\n|\r|\n)/g, '<br />').split('\n').join('<br />');
            // this.exData.forEach(this.replaceHtml(this.exData));  
          }) 
          .catch(err => {
            console.log(err);
            console.log("getHtmlCode에러");
          })   

      },

      explainNext(){ //설명창 제목, 본문 넘기기 위함
        this.nextBtnCount++;
        if(this.nextBtnCount == 3) { 
          console.log("설명창 종료");
          this.modale = false;
          this.nextBtnCount = 0; 
        }
      },
      explainEnd() { //설명창 마지막장에서 코딩시작 버튼 눌렀을때 실행 
        this.nextBtnCount++;
        if(this.nextBtnCount == 3) { 
          console.log("설명창 종료");
          this.modale = false;
          this.nextBtnCount = 0; 
        }
      },
      explainBefore() {  //설명창 제목, 본문 뒤로가기 위함
        this.nextBtnCount--;
        if(this.nextBtnCount < 0) {
          this.nextBtnCount = 0; //가장 첫 설명에서 이전으로 넘어갈 순 없으므로 
        }
      }

    } //여기까지 method

}

</script>

<style>
/**color palette 
  #6F47FF 쨍한 바꾼 배경색
  #765fd7 원래 배경색
  #d4d2db 연한 배경색
  #cdbcff 연보라색 (원래 제목색)
  #8faccc 회하늘색(버튼색)
*/

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

.nanumsquare {
    font-family: 'NanumSquare', sans-serif !important;
}

* {
  box-sizing: border-box;
  font-family: 'NanumSquare', sans-serif !important;
}


#training {
  margin: 0;
  width: 100vw;
  height: 100vh;
  background: #6F47FF;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  overflow: hidden;
}

#result-box {
  display: flex;
  background: #fefefe;
  color: #333333;
  margin-left: 100px;
}
#training #code-editor {
  height: calc(100% - 154px);
}
#training #code-editor div{
  height: 100%;
}
#training iframe {
  display: flex;
  width: 600px; height:100%;
  margin-left:0;
  background-color: white;
}
#code-editor span {
  position: absolute;
  width: 40%;
  top: 24%; left: 31%;
  background: #d4d2db;
}

/*left section 관련*/

#training .left-section {
  position: relative;
  display: flex;
  background: #d4d2db;
  width: 75%;
  height: 100%;
  flex-direction: column;
}
#training .left-header {
  display: flex;
  width: 100%;
  height: 77px;
  padding: 0px 30px;
  flex-direction: row;
  /* align-content: center; */
  justify-content: space-between;
  align-items: center;
}

.title-btn {
  width: 100px;
  font-size: 24px;
  font-family: "SpoqaHanSans";
  font-weight: 600;
  color: #6F47FF;
  background: none;
  border: none;
}

.form-buttons {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  height: 40px;
}
.submit {
  width: 169px;
  height: 40px;
  font-size: 17px;
  margin-right: 40px;
  border-radius: 6px;
  border: none;
  background: rgb(137,68,250);
  background: linear-gradient(90deg, rgba(137,68,250,1) 0%, rgba(75,93,252,1) 49%, rgba(48,134,253,1) 67%, rgba(0,209,255,1) 95%);
  color: white;
  border: white solid 1px;
}
.reset {
  width: 100px;
  font-size: 17px;
  border-radius: 6px;
  border: none;
  background-color: #6F47FF;
  color: white;
}
form {
  display: flex;
  overflow-y: scroll;
}
.page-buttons {
  width: 280px;
}
.code-num {
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  margin-left: 7px;
  width: 10px;
  height: 800px;
  color: #cdbcff;
  font-size: 15px;
}
.textbox {
  display: flex;
  flex-direction: row;
  height: 700px;
  overflow-y: scroll;
}

/*모달창 관련*/

.white-bg-training {
  display: flex;
  flex-direction: column;
  align-items: center;
  position: relative;
  margin: 6% 33% auto auto;
  width: 45%;
  height: 59%;
  background: linear-gradient( 
  0deg
  ,rgb(255 255 255 / 90%) 0%, rgb(255 255 255 / 97%) 32%, rgba(255,255,255,1) 95%);
  border-radius: 20px;
  padding: 10px 20px;
}
/* width: 900px;
  height: 618px; */
.white-bg-training h1 {
  margin: 0;
  margin: auto auto;
  /* position: relative; */
  font-weight: normal;
  font-size: 23px;
}

.white-bg-training::before {
  content: "";
  display: block;
  width: 100%;
  height: 1.5px;
  background: radial-gradient(
    circle,
    rgba(118, 95, 215, 1) 22%,
    rgba(85, 128, 226, 0.86) 62%,
    rgba(0, 212, 255, 0.35) 97%
  );
  position: absolute;
  top: 10%;
  margin-top: 7px;
}
.white-bg-training > img {
  width: 15%;
  height: 15%;
  position: absolute;
  right: 2%;
  top: 2%;
}
.white-bg-p{ /*p를 감싸는 영역*/ 
  display: flex;
  flex-direction: column;
  align-content: center;
  width: 90%;
  height: 70%;
  margin: auto auto;
  overflow-y: scroll;
}

.white-bg-training p {
  display: flex;
  margin: auto auto;
  text-align: center;
  font-size: 18px;
  
}

.white-bg-training button :not(.skip-btn),
.white-bg-training button img {
  width: 80px;
  height: 60px;
}

.white-bg-btns img:hover {
  transform: scale(1.15);
  transition: 0.3s;
}

.white-bg-shift-btn{
  width:auto;
  margin: auto auto;
  position: relative;
}

.skip-btn {
  width: auto; height: auto;
  padding: 0;
  font-size: 17px; font-weight: bolder;
  color: #431AD7;
  background: none;
  position: absolute;
  right: 6%;
  top: 90%;
}

/*설명,정답버튼, 밑에있는 버튼 등 middle section  */

.middle-section {
  display: flex;
  width: 400px;
  flex-direction: column;
  justify-content: space-between;
  margin-right: 450px;
  
}

.explain-answer {
  margin-right: 2%;
}

.answer {
  width: 32%;
  height: 74%;
  background: #f4f5f6;
  border-radius: 8px;
  position:fixed;
  top: 13%; right: 31%;
  border: #d4d2db thin solid;
  border-radius: 20px;
  overflow-y: scroll;
  z-index: 3;
}
.answer img {
  width: 100%; height: 88%;
  margin: auto auto;
  
  /* position: fixed; */
}
.answer-btn {
  width: 140px;
  height: 32px;
  border: none;
  border-top-right-radius: 8px;
  border-bottom-right-radius: 8px;
  font-size: 16px;
  background-color: white;
  color: rgb(59, 59, 59);
}
.explain-btn {
  width: 140px;
  height: 32px;
  border: none;
  border-top-left-radius: 8px;
  border-bottom-left-radius: 8px;
  font-size: 16px;
  color: rgb(59, 59, 59);
  background-color: white;
}

.answer .x-btn{
  width: 30px; height: 30px;
  position: relative;
  left:45%; top: 4px;
  margin-top: 7px;
  background: none;
  border: none;
  font-size: 16px;
}

.result {
  position: inherit;
  width: 550px;
  height: 700px;
  background-color: white;
  border-radius: 20px;
  margin-left: 700px;
  margin-top: 45px;
  border: #765fd7 1.5px solid;
  background: none;
}

footer {
  display: flex;
  width: 100%; height: 10%;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding-right: 16%; padding-left: 17%;
}

footer img {
  width: 60px;
  height: 60px;
  background:none;
  border: none;
}

footer img:hover {
  transform: scale(1.15);
  transition: 0.3s;
}

.prev-page,
.next-page {
  background: none;
  border: none;
}

/*right-section 관련 부분들*/
.right-section nav {
  display: flex;
  width: 108%; height: 3%;
  margin: 0;
  margin-bottom: 4%;
  flex-direction: row;
  align-items: center;
  justify-items: center;
  justify-content:center;
  padding-left: 7px;
}

.right-section {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  width: 25%;
  padding: 22px 2%;
}

.right-section nav h3 {
  display: flex;
  font-size: 16px;
  color: #FFFFFF;
  font-weight:bolder;
  margin-left: 12px;
}
/* .right-header-btn {
  background: none;
  display: flex;
  flex-direction: row;
  width: 
} */
.sign-up-btn {
  background: none;
  border: none;
  font-size: 15px;
  font-weight:bold;
  color:#ffffff;
  padding-right: 12px;
  border-right: 1px solid #8cfaff;
}

.right-header-btn .login-btn {
  position: relative;
  background: none;
  border: none;
  margin: 0;
  margin-right: 8px;
  font-size: 15px;
  font-weight:bold;
  color:#ffffff;
}

.mypage-btn {
  color: #ffffff;
  font-weight:bold;
  background: none;
  font-size: 17px;
  position: relative;
  padding-right: 12px;
  border-right: 1px solid #8cfaff;
  

}

.goal {
  width: 100%;
  display: flex;
  flex-direction: row-reverse;
}
.goal-btn {
  padding: 0;
  width: 150px;
  height: 32px;
  border: none;
  border-radius: 8px;
  font-size: 16px;
  background-color: white;
  color: rgb(59, 59, 59);
  margin: 20px 10px;
}
.completeResult {
  display: flex;
  position: absolute;
  top: 100px; right: 18px;
  flex-direction: column;
  width: 29%;
  height: 74%;
  background: white;
  border-radius: 20px; border: #d4d2db thin solid;
  z-index: 2;
  overflow: hidden;
}
.complete-img img {
  width: 100%; height: 100%;
  margin: auto auto;
}
.completeResult .x-btn {
  width: 30px; height: 30px;
  position: relative;
  left:92%; top: 4px;
  margin-top: 7px;
  background: none;
  border: none;
  font-size: 16px;
}
.memo {
  display: flex;
  position: relative;
  flex-direction: column;
  align-items: center;
  margin: 10% auto;
  width: 100%; height: 60%;
  background-color: aliceblue;
  overflow: hidden;
  box-shadow: 5px 5px 5px 4px #5133c3;

}
.memo::before {
  content: "";
  display: inline-block;
  position:relative;
  /* left: 0px; top: 0px; */
  width:100%; height:20px;
  background-color: #c5dcf1;
  
  
}
.memo input {
  width: calc(100% - 40px); height: 25px;
  border:#8faccc solid thin;
  border-radius: 5px;
  margin: 10px;
}
.memo-group{
  position: relative;
  width: 100%;
  height: 82%;
  padding: 0 20px;
}
.memo-group ul li {
  width: fit-content;
  
}
.memo-list {
  width: 100%; height: 96%;
  overflow-y: scroll;
  margin: 0px;
  margin-top: 10px;
  background: none;
  list-style: none;
  position: relative;
}

.delete-memo {
  position: absolute;
  color: #0066ff;
  font-weight: bold;
  left: 7%;
}
.trash-img {
  top: 95%;
  width: 7%;
  height: 7%;
  position: absolute;
  left: 84%;
}

/*모든 버튼/router 링크에 적용되는 속성 */
button:hover {
  cursor: pointer;
}
router-link:hover {
  cursor: pointer;
}

/*북마크, 삼각형 등 기타 이미지들*/
.triangle {
  width: 10px;
  height: 10px;
  margin-left: 6px;
}
.bookmark{
  position: absolute;
  width:40px; height:40px;
  top: 65px; right: 53%;
  border: thin;
}

@media screen and (max-width : 1040px) {
  
}

</style>