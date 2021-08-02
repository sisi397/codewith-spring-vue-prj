<template>
    <div id="signup-complete-popup">
        <div class="black-bg" @click="closeCoursePopup">
        </div>
        <div class="signup-complete-white-bg">
            <div class="signup-popup-content">
                <div class="left">
                    <img src="../../assets/img_singup-complete-check.svg" alt="">
                </div>
                <div class="right">
                    <p class="complete-guide">SIGNUP<br>COMPLETE!</p>
                    <p class="complete-greeting">
                       <strong>{{userName}}</strong>님 환영합니다!<br>여러분의 코딩 학습을 위하여 최선을 다하겠습니다.
                    </p>
                </div>
            </div>
            <router-link to="/" class="from-signup-to-home"><img src="../../assets/btn_to-home.svg" alt=""></router-link>
        </div>
    </div>
</template>

<script>
export default {
    name : "signup-complete-popup",
    props : ["_userName"],
    data() {
        return {
            userName : this._userName
        }
    }
}
</script>

<style>
#signup-complete-popup {
    position: relative;
    display: flex;
    align-items: center; 
    width: 100%; height: 100%;
    position: fixed;
    margin: -20px;
    z-index: 1;
}
#signup-complete-popup .black-bg {
    position: fixed;
    width: 100%; height: 100%;
    background: rgba(0, 0, 0, 0.5);
}
#signup-complete-popup .signup-complete-white-bg {
    position: relative;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 800px; height: 500px;
    background: white;
    border-radius: 20px;
    margin: auto;
    padding: 0px 130px;
}
#signup-complete-popup .signup-popup-content{
    display: flex;
    flex-direction: row;
    margin-top: 69px;
}
#signup-complete-popup .signup-popup-content .left{
    line-height: 154px;
    padding-right: 100px;
    border-right: 5px solid #4D24A4;
}
#signup-complete-popup .signup-popup-content .left img{
    vertical-align: middle;
}
#signup-complete-popup .signup-popup-content .right{
    display: flex;
    flex-direction: column;
    padding-left: 20px;
}
#signup-complete-popup .signup-popup-content .complete-guide{
    margin: 0;
    margin-bottom: 10px;
    text-align: left;
    color: #4D24A4;
    font-weight: 700;
    font-size: 45px;
}
#signup-complete-popup .signup-popup-content .complete-greeting{
    margin: 0;
    text-align: left;
}
#signup-complete-popup .signup-complete-white-bg .from-signup-to-home {
    align-self: flex-end;
    margin-top: 15px;
}
</style>