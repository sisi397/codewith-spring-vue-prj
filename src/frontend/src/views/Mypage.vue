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
                <img src="../assets/img_profile-default.svg" class="user-img" alt="profile-img">
                <button type="button" class="input-btn">
                    <label img="../assets/btn_plus.svg" for="input-file">
                        <img src="../assets/btn_plus.svg" alt="add-image" class="add-img" >
                    </label>
                    <input type="file" id="input-file" src="../assets/btn_plus.svg" accept="" style="display:none">
                </button>
                <h2>{{userName}}</h2>
            </div>
            <div class="right-section-mypage">
                <div class="select-step">
                    <button type="button" class="step-in-progress-btn" @click="stepInProgressState = true, stepCompleteState = false, stepScrappedState = false">진행중인 단계</button>
                    <button type="button" class="step-complete-btn" @click="stepInProgressState = false, stepCompleteState = true, stepScrappedState = false">완료한 단계</button>
                    <button type="button" class="step-scrapped-btn" @click="stepInProgressState = false, stepCompleteState = false, stepScrappedState = true">스크랩</button>
                </div>
                <div class="step-lists">
                    <div class="step-in-progress" v-if="stepInProgressState == true">
                        <ol class="step-list">
                            <li>{{stepInProgress.stepWhich}}</li>
                            <li>{{stepInProgress.stepWhere}}</li>
                            <li>{{stepInProgress.stepWhen}}</li>    
                        </ol>
                        <ol class="step-list">
                            <li>{{stepInProgress.stepWhich}}</li>
                            <li>{{stepInProgress.stepWhere}}</li>
                            <li>{{stepInProgress.stepWhen}}</li>    
                        </ol>
                        <ol class="step-list">
                            <li>{{stepInProgress.stepWhich}}</li>
                            <li>{{stepInProgress.stepWhere}}</li>
                            <li>{{stepInProgress.stepWhen}}</li>    
                        </ol>
                    </div>
                    
                    <div class="step-complete" v-if="stepCompleteState == true">
                        <ol class="step-list">
                            <li>{{stepComplete.stepWhich}}</li>
                            <li>{{stepComplete.stepWhere}}</li>
                            <li>{{stepComplete.stepWhen}}</li>    
                        </ol>
                        <ol class="step-list">
                            <li>{{stepComplete.stepWhich}}</li>
                            <li>{{stepComplete.stepWhere}}</li>
                            <li>{{stepComplete.stepWhen}}</li>    
                        </ol>
                        <ol class="step-list">
                            <li>{{stepComplete.stepWhich}}</li>
                            <li>{{stepComplete.stepWhere}}</li>
                            <li>{{stepComplete.stepWhen}}</li>    
                        </ol>
                    </div>
                    
                    <div class="step-scrapped" v-if="stepScrappedState == true">
                        <ol class="step-list">
                            <li>{{stepScrapped.stepWhich}}</li>
                            <li>{{stepScrapped.stepWhere}}</li>
                            <li>{{stepScrapped.stepWhen}}</li>    
                        </ol>
                        <ol class="step-list">
                            <li>{{stepScrapped.stepWhich}}</li>
                            <li>{{stepScrapped.stepWhere}}</li>
                            <li>{{stepScrapped.stepWhen}}</li>    
                        </ol>
                        <ol class="step-list">
                            <li>{{stepScrapped.stepWhich}}</li>
                            <li>{{stepScrapped.stepWhere}}</li>
                            <li>{{stepScrapped.stepWhen}}</li>    
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
//import axios from "axios"

export default {
    name : 'Mypage',
    components : {
        HeaderLight, LoginPopup
    },
    data() {
        return {
            loginPopupState : 0,
            userImage : "",
            userName : '최소영',
            stepInProgress : {
                stepWhich : 'HTML',
                stepWhere : '튜토리얼',
                stepWhen : '21.08.01'
            },
            stepComplete : {
                stepWhich : 'CSS',
                stepWhere : '튜토리얼',
                stepWhen : '21.08.01'
            },
            stepScrapped : {
                stepWhich : 'Javascript',
                stepWhere : '튜토리얼',
                stepWhen : '21.08.01'
            },
            stepInProgressState : true,
            stepCompleteState : false,
            stepScrappedState  : false

        }
    },
    methods : {
        loginOpen() {
            this.loginPopupState = 1;
        },
        loginClose() {
            this.loginPopupState = 0;
        }
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