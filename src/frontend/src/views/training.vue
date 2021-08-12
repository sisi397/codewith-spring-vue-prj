<template>
  <!--html에서는 띄어쓰기 하지 말기-->
  <div id="training">
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
            <p>{{ answerCode[0] }}</p>
          </div>
        </div>
      </div>
      <img class="bookmark" src="../assets/bookmark-regular.svg" @click="bookmarkState = true" v-if="bookmarkState === false"> 
      <img class="bookmark" src="../assets/bookmark-solid.svg" @click="bookmarkState = false" v-if="bookmarkState === true">
      <!--북마크 체크되어있을때 true, 아니면 false-->
      <!-- 코드 에디터 부분 -->
      <div id="code-editor">
          <p class="codepen" data-height="100%" data-theme-id="39664" data-default-tab="html,result" data-slug-hash="21881c03a017142cf6350cb206ac74f2" data-editable="true" data-user="futuredevsy" style="box-sizing: border-box; display: flex; align-items: center; justify-content: center; border: 2px solid; margin: 1em 0; padding: 1em;">
          <span>새로고침 해주세요.</span>
          </p>
      </div>

      <footer>
        <div class="form-buttons">
          <button type="submit" class="submit">완성했어요</button>
          <button type="button" class="reset">초기화</button>
        </div>
        <div class="page-buttons">
          <button type="button" class="prev-page">
            <img
              src="../assets/chevron-left-solid.svg"
              alt="go to the page before"
            />
          </button>
          <button type="button" class="next-page">
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
        <div class="log-btn">
          <!--로그인 하지 않았을 경우 보임-->
          <button type="button" class="login-btn" v-if="loginState == false">
            Login
          </button>
          <button type="button" class="sign-up-btn" v-if="loginState == false">
            Sign-up
          </button>
        </div>
        <div>
          <!--로그인 했을 경우 보임-->
          <button type="button">
            <router-link class="mypage-btn" v-if="loginState == true" :to="{ name: 'Mypage' }" @click="$router.push({name: 'Mypage'})">Mypage</router-link>
          </button>
        </div>
        <h3>HTML Step.{{ stepNum[0] }} {{ stepName[0] }}</h3>
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
        <div class="complete-p">{{ complete[0] }}</div>
      </div>
      <div class="memo">
        <input type="text" placeholder=" + Memo Here: " maxlength="18" id="user-input" v-model="memo" @keyup.enter="addNewMemo(memo)">
        <div class="memo-group"> <!--사용자 input 나타내기--> 
          <ul class="memo-list">
            <li v-for="(memo, index) in memoList" v-bind:key="memo">
              {{ memo }}
              <button class="deleteMemo" type="button" @click="deleteMemo(index)" >X</button>
            </li>
          </ul>
          <button type="button" class="clearMemo" @click="clearMemo">Clear</button>
        </div>
      </div>
      
    </section>

    <!--모달창(설명 뜨는부분)-->
    <div class="black-bg" v-if="modale == true">
      <div class="white-bg">
        <h1>{{ exTitle[0] }}</h1>
        <img src="../assets/coco_smile.svg" />
        <p>{{ exParagraph[0] }}</p>
        <div class="white-bg-btns">
          <button type="button" class="prev-ex">
            <img
              src="../assets/chevron-left-solid.svg"
              alt="go to the page before"
            />
          </button>
          <button type="button" class="next-ex">
            <img
              src="../assets/chevron-right-solid.svg"
              alt="go to the next page"
            />
          </button>
          <button type="button" @click="modale = false" class="skip-btn">
            설명 Skip
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
    name : 'Training',
    props : ['_course', '_stage'],
    // props: {
    //   memoList: {type: Array, default: () => [] }
    // },
    mounted() {
      this.setCourseData();
      this.getCourseData();
    },
    data() {
      return {
        memoList: [], 
        modale: false,
        showAnswer: false,
        goalBtnState: false,
        loginState: true,
        stepNum: ["1", "2", "3"],
        stepName: ["튜토리얼", "회원가입 창", "달력 만들기"],
        answerCode: ["이곳에 정답 코드가 보여집니다", "어쩌구"],
        complete: ["완성화면", "로그인 완성화면 예시", "달력 완성화면 예시"],
        exTitle: [
          "HTML Step 1 튜토리얼 진행을 환영합니다!",
          "HTML Step 2 회원가입창 만들기 단계를 진행합니다!",
        ],
        exParagraph: [
          "코드를 작성하기에 앞서,\nhtml의 뼈대가 될 기본 구조부터 알아볼거예요!",
        ],
        bookmarkState: false,
        selectCourseData : { //사용자가 선택한 코스와 관련된 데이터 + 유저 아이디
          course: 0,
          stage: 0,
        }
      }  
    },
    methods: {
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
      setCourseData() {
        if (this._course != undefined) {
          console.log('setLocalStorage :' + this._course, ',' + this._stage);
          localStorage.setItem('courseData', JSON.stringify({course : this._course, stage : this._stage}));
        }
      },
      getCourseData() {
        let courseData = JSON.parse(localStorage.getItem('courseData'))
        console.log('getLocalStorage :' + courseData.course + ',' + courseData.stage);
        this.selectCourseData.course = this._course;
        this.selectCourseData.stage = this._stage;
      }
    }
  
  
};

</script>

<style>
/**color palette 
  #6F47FF 쨍한 바꾼 배경색
  #765fd7 원래 배경색
  #d4d2db 연한 배경색
  #cdbcff 연보라색 (원래 제목색)
  #8faccc 회하늘색(버튼색)
*/

@import url(https://spoqa.github.io/spoqa-han-sans/css/SpoqaHanSans-kr.css);

* {
  box-sizing: border-box;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  font-family: "SpoqaHanSans";
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
  width: 600px;
  margin-left:0;
  background-color: white;
  z-index: 1;
}

/*left section 관련*/
/*.left-middle {
  width: 1200px;
  height: 700px;
  display:flexbox;
  flex-direction: row;
}*/

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


/*
.left-section textarea {
  margin: 0px auto auto 20px;
  height: 100%;
  width: 460px;
  background: none;
  color: white;
  font-size: 16px;
  overflow: hidden;
  resize: none;
}
*/
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
  width: 140px;
  height: 40px;
  font-size: 17px;
  margin-right: 40px;
  border-radius: 6px;
  border: none;
  background-color: #24b3ca;
  color: white;
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
.black-bg {
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  position: fixed;
  padding: 20px;

}
.white-bg {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 130px auto;
  width: 900px;
  height: 600px;
  background: linear-gradient(
    0deg,
    rgba(255, 255, 255, 0.42826086956521736) 0%,
    rgba(255, 255, 255, 0.8152173913043479) 52%,
    rgba(255, 255, 255, 0.9391304347826087) 79%
  );
  border-radius: 20px;
  padding: 20px;
}
.white-bg h1 {
  margin: 0;
  position: relative;
  font-weight: normal;
  font-size: 29px;
}

.white-bg h1::after {
  content: "";
  display: block;
  width: 900px;
  height: 1.5px;
  background: radial-gradient(
    circle,
    rgba(118, 95, 215, 1) 22%,
    rgba(85, 128, 226, 0.86) 62%,
    rgba(0, 212, 255, 0.35) 97%
  );
  /* position: relative;
  right: 90px; */
}
.white-bg > img {
  width: 120px;
  height: 100px;
  position: relative;
  left: 350px;
  bottom: 40px;
}
.white-bg p {
  width: 700px;
  height: 450px;
  position: relative;
  top: -60px;
  margin: 0;
  text-align: center;
  font-size: 18px;
}
.white-bg button {
  background: none;
  border: none;
}
.white-bg button :not(.skip-btn),
.white-bg button img {
  width: 80px;
  height: 60px;
}
.white-bg-btns {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  margin-left: 70px;
}
.white-bg-btns img:hover {
  transform: scale(1.15);
  transition: 0.3s;
}

.skip-btn {
  width: 70px;
  padding: 0;
  font-size: 17px;
  color: #3b3485;
  background-color: white;
  border-radius: 5px;
  position: relative;
  left: 250px;
  bottom: -20px;
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
  /* display: flex;
  flex-direction: row;
  align-content: center;
  margin: 20px auto auto 600px; */
}

.answer {
  width: 550px;
  height: 710px;
  background: white;
  border-radius: 8px;
  position: relative;
  top: 94px; right: 411px;
  border: #d4d2db thin solid;
  border-radius: 20px;
  z-index: 3;
}
.answer p {
  width: 500px;
  height: 750px;
  margin: auto auto;
  position: fixed;
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
  width: 100%; height: 77px;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 0 100px;
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
nav {
  display: flex;
  width: 100%;
  flex-direction: row;
  align-items: center;
  justify-content: center;
}

.right-section {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  width: 25%;
  padding: 0 10px;
}

h3 {
  font-size: 17px;
  color: #d4d2db;
  margin-left: 10px;
  font-weight:bolder;
}
.log-btn {
  /*login,sign-up 버튼 감싸는div*/
  margin: 20px 20px;
  background: none;
}
.sign-up-btn {
  margin-left: 15px;
  background: none;
  border: none;
  font-size: 16px;
  color:#d4d2db;
}
.sign-up-btn::after {
  content: "";
  float: right;
  margin-left: 15px;
  width: 1px;
  height: 22px;
  vertical-align: middle;
  background:#d4d2db;
  position: fixed;
}
.login-btn {
  background: none;
  border: none;
  font-size: 16px;
  color:#d4d2db;
}
.login-btn::after {
  content: "";
  position: absolute;
  top: 30px; right: 344px;
  width: 4px; height: 4px;
  border-radius: 50%;
  background:#d4d2db;
}
.mypage-btn {
  color: #d4d2db;
  font-weight:bolder;
  border: none;
  background: none;
  font-size: 16px;
}
.mypage-btn::after {
  content: "";
  float: right;
  width: 1.5px;
  height: 22px;
  vertical-align: middle;
  background: rgb(116, 116, 116);
  margin-left: 14px;
}

.goal {
  width: 100%;
  display: flex;
  flex-direction: row-reverse;
}
.goal-btn {
  padding: 0;
  margin-right: 33px;
  width: 150px;
  height: 32px;
  border: none;
  border-radius: 8px;
  font-size: 16px;
  background-color: white;
  color: rgb(59, 59, 59);
}
.completeResult {
  display: flex;
  position: absolute;
  top: 100px; right: 18px;
  flex-direction: column;
  width: 550px;
  height: 700px;
  background: white;
  border-radius: 20px; border: #d4d2db thin solid;
  z-index: 2;
}
.complete-p {
  width: 500px; height: 600px;
  margin: auto auto;
}
.x-btn {
  width: 30px; height: 30px;
  margin-left: 510px;
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
  margin: 100px auto;
  width: 100%; height: 100%;
  background-color: aliceblue;

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
  width: 100%;
  height: 70%;
  padding: 0 20px;
}
.memo-group ul li {
  width: fit-content;
}
.memo-list {
  width: 100%; height: 100%;
  overflow-y: scroll;
  margin: 0px;
  margin-top: 10px;
  background: none;
}
.clearMemo {
  position: absolute;
  right: 75px;
  bottom: 40px;
  color: #55708d;
  z-index: 1;
  
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
  top: 65px; right: 70px;
  border: thin;
}

@media screen and (max-width : 1040px) {
  
}

</style>