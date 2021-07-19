<template>
<div class="course-popup" @click="closeCoursePopup">
    <div class="white-bg">
      <div class="windowTitle">
        <h4 class="select-course-name">{{selectWindow}}</h4> <h4 class="process">Process</h4>
      </div>
      <p class="guide-to-start">시작할 단계를 선택하세요<br>이미 진행중인 프로세스가 있다면, 이어 진행하기가 가능합니다</p>
      <div class="tutorial-container">
        <div class="tutorial">
          <div class="tutorial-moon"><img src="../../assets/img_cresent-moon-purple.svg" alt=""></div>
          <p>step1</p>
          <button class="tutorial-step">튜토리얼</button>
        </div>
        <div class="tutorial">
          <div class="tutorial-moon"><img src="../../assets/img_half-moon-purple.svg" alt=""></div>
          <p>step2</p>
          <button class="tutorial-step">달력 만들기</button>
        </div>
        <div class="tutorial">
          <div class="tutorial-moon"><img src="../../assets/img_full-moon-purple.svg" alt=""></div>
          <p>step3</p>
          <button class="tutorial-step">회원가입 창 만들기</button>
        </div>
      </div>
      <img class="continue-btn" src="../../assets/btn_continue.svg" alt="continue-btn">
      <p class="start-first">처음부터 진행하기</p>
    </div>
</div>
</template>

<script>
export default {
    name : 'course-popup',
    props : ['_selectWindow'],
    data() {
        return {
            selectWindow : this._selectWindow,
        }
    },
    methods : {
        closeCoursePopup() {
            console.log('코스 창 닫음');
            this.$emit('_courseClose')
        }
    }
}
</script>

<style>
.course-popup {
  position: fixed;
  display: flex;
  align-items: center; 
  width: 100%; height: 100%;
  margin: -20px;
  background: rgba(0, 0, 0, 0.5);
  z-index: 1;
}
.white-bg {
  width: 800px; height: 500px;
  background: white;
  border-radius: 20px;
  margin: auto;
  padding: 0px 50px;
}
.windowTitle {
  display: flex;
  justify-content: center;
  padding: 20px 0px;
  border-bottom: 2px solid rgba(138, 101, 242, 0.44);
  font-size: 20px;
  font-weight: 500;
}
.windowTitle .select-course-name {
  color: #4D24A4;
}
.windowTitle .process {
  color: #606060;
  margin-left: 10px;
}
.white-bg .guide-to-start {
  color: #606060;
  margin-top: 30px;
}
.white-bg .tutorial-container {
  display: flex;
  justify-content: center;
  gap: 160px;
  margin-top: 50px;
}
.white-bg .tutorial-container .tutorial {
  width: 125px;
}
.white-bg .tutorial-container .tutorial .tutorial-moon {
  width: 40px; height: 40px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: auto;
}
.white-bg .tutorial-container .tutorial .tutorial-step {
  height: 30px;
  border: 1px solid #BDBDBD;
  border-radius: 10px;
  padding: 0px 10px;
}
.white-bg .continue-btn {
  margin-top: 60px;
}
.white-bg .start-first {
  width: 120px;
  color: #656565;
  border-bottom: 1px solid #848484;
  margin: 10px auto;
}
</style>