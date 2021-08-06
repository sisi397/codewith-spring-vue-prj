<template>
  <!--html에서는 띄어쓰기 하지 말기-->
  <div id="training">
    <section class="left-section">
      <div class="left-header">
        <router-link class="title-btn" :to="{ name: '' }">CodeWith</router-link>
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
      <!--북마크 체크되어있을때 true, 아니면 false-->
      <!-- 코드 에디터 부분 -->
      <div id="code-editor">
        <p class="codepen" data-height="100%" data-theme-id="39664" data-default-tab="html,result" data-slug-hash="21881c03a017142cf6350cb206ac74f2" data-editable="true" data-user="futuredevsy" style="box-sizing: border-box; display: flex; align-items: center; justify-content: center; border: 2px solid; margin: 1em 0; padding: 1em;">
          <span>See the Pen <a href="https://codepen.io/futuredevsy/pen/21881c03a017142cf6350cb206ac74f2">
          </a> by FutureDevSY (<a href="https://codepen.io/futuredevsy">@futuredevsy</a>)
          on <a href="https://codepen.io">CodePen</a>.</span>
        </p>
      </div>
      <footer>
        <div class="form-buttons">
          <button type="submit" class="submit" @click="onComplete">완성했어요</button>
          <button type="button" class="reset">초기화</button>
        </div>
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
      </footer>
    </section>
    <img class="bookmark-r" src="../assets/bookmark-regular.svg" @click="saveScrap" v-if="bookmarkState === false">
    <img class="bookmark-s" src="../assets/bookmark-solid.svg" @click="bookmarkState = false" v-if="bookmarkState === true">
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
import axios from "axios"
export default {
  name : 'Training',
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
      bookmarkState: false
    }
  },
  // props: {
  //   memoList: {type: Array, default: () => [] }
  // },
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
    onComplete(){    //완성했어요 버튼 클릭시
      console.log("complete 함수 실행됨");
      axios
          .post('http://3.36.131.138/api/stageFinish', {  //stageFinish (완료 단계) 저장
            userId : 'id',
            course : 3,
            stage: 2,
          })
          .then(res => {
            console.log(res);
          })
          .catch(err => {
            console.log(err);
          })
    },
    onStageIng(){ // 페이지 로드 되자마자 실행되어야 함. 진행 정보 저장)
      console.log("onStageIng 함수 실행됨");
      axios
          .post('http://3.36.131.138/api/stageIng', {  //stageIng (진행중 단계) 저장
            userId : 'id',
            course : 3,
            stage: 2,
          })
          .then(res => {
            console.log(res);
          })
          .catch(err => {
            console.log(err);
          })
    },
    saveScrap(){   //scrap 저장하기
      this.bookmarkState = true
      console.log("saveScrap 함수 실행됨");
      axios
          .post('http://3.36.131.138/api/scrap', {
            userId : 'id',
            course : 3,
            stage: 2,
          })
          .then(res => {
            console.log(res);
          })
          .catch(err => {
            console.log(err);
          })
    },
    getScrap(){   //현재페이지 scrap 되어있는지 확인. scrap 되어있다면 bookmark true,페이지 로드 되자마자 실행되어야 함. (아직 서버 반영 안됨!!!)
      console.log("getScrap 함수 실행됨");
      axios
          .get("http://localhost:8090/api/scrap/3/2") // 3-2 (javascript/2단계가 스크랩 되어있는지 확인)
          .then(res => {
            this.bookmarkState = true
            console.log(res);
          })
          .catch(err => {
            console.log(err);
          })
    },
    getHtmlCode(){   //Html코드랑 설명 불러오기. 페이지 로드 되자마자 실행되어야 함.
      console.log("getHtmlCode 함수 실행됨");
      axios
          .get("http://3.36.131.138/api/html/1") //
          .then(res => {
            console.log(res);
          })
          .catch(err => {
            console.log(err);
          })
    }
  }
};
</script>