<template>
<div id="course-popup">
  <div class="black-bg" @click="closeCoursePopup">
  </div>
  <div class="course-popup-content">
    <div class="login-form-hide">
      <img class="hide-btn" @click="closeCoursePopup" src="../../assets/btn_close.svg" alt="">
    </div>
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
    <!-- 이어하기 버튼 -->
    <router-link :to="{name : 'Training', params : {course : this.selectCourseData.course, stage : this.selectCourseData.stage}}">
      <img class="continue-btn" @click="continueCourse" src="../../assets/btn_continue.svg" alt="continue-btn">
    </router-link>
    <!-- 처음부터 진행 버튼 -->
    <router-link :to="{name : 'Training', params : {_course : this.selectCourseData.course, _stage : this.selectCourseData.stage}}">
      <p class="start-first" @click="startCourse">처음부터 진행하기</p>
    </router-link>
  </div>
</div>
</template>

<script>
import axios from "axios"

export default {
    name : 'course-popup',
    props : ['_selectCourse', '_userName'],
    data() {
        return {
          selectWindow : this._selectWindow,
          windowTitle : ['HTML', 'CSS', 'JavaScript'],
          selectCourseNumber : 1, // html : 1, css : 2, javascript : 3
          selectCourseData : {
            userId: "",
            course: 0,
            stage: 0,
          }
        }
    },
    methods : {
        closeCoursePopup() {
          console.log('코스 창 닫음');
          this.$emit('_courseClose')
        },
        continueCourse() {
          axios
          .get("http://3.36.131.138/api/stageIng/" + this._selectCourse)
          .then(res => {
            console.log(res);
            this.selectCourseData.userId = res.data.userId;
            this.selectCourseData.course = res.data.course;
            this.selectCourseData.stage = res.data.stage;
            console.log(this.selectCourseData);

            this.emitter.emit("_continueCourse", this.selectCourseData);
          })
          .catch(err => {
            console.log(err);
          })
        },
        startCourse() {
          this.selectCourseData.userId = this._userName;
          this.selectCourseData.course = this._selectCourse;
          this.selectCourseData.stage = 1;
          console.log(this.selectCourseData);

          this.emitter.emit("_startCourse", this.selectCourseData);
        }
    }
}
</script>

<style>
#course-popup {
  position: relative;
  display: flex;
  align-items: center; 
  width: 100%; height: 100%;
  position: fixed;
  margin: -20px;
  z-index: 1;
}
.black-bg {
  position: fixed;
  width: 100%; height: 100%;
  background: rgba(0, 0, 0, 0.5);
}
#course-popup .course-popup-content {
  position: relative;
  width: 800px; height: 500px;
  background: white;
  border-radius: 20px;
  margin: auto;
  padding: 0px 50px;
  z-index: 2;
}
#course-popup .login-form-hide {
  position: absolute;
  top: 24px; right: 50px;
  width: 19px; height: 19px;
  border-radius: 50px;
}
#course-popup .login-form-hide:hover {
  background-color: rgb(0, 0, 0, 0.08);
  cursor: pointer;
}
#course-popup .hide-btn {
  width: 12px; height: 12px;
  margin: auto;
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
.guide-to-start {
  color: #606060;
  margin-top: 30px;
}
.tutorial-container {
  display: flex;
  justify-content: center;
  gap: 160px;
  margin-top: 50px;
}
.tutorial-container .tutorial {
  width: 125px;
}
.tutorial-container .tutorial .tutorial-moon {
  width: 40px; height: 40px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: auto;
}
.tutorial-container .tutorial .tutorial-step {
  height: 30px;
  border: 1px solid #BDBDBD;
  border-radius: 10px;
  padding: 0px 10px;
}
.continue-btn {
  margin-top: 60px;
  cursor: pointer;
}
.start-first {
  width: 120px;
  color: #656565;
  border-bottom: 1px solid #848484;
  margin: 10px auto;
  cursor: pointer;
}
</style>