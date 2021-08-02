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
            <div>
                <img src="{{userImage}}" alt="profile-img">
                <input type="file" name="imageupload" accept="image/*">
                <h2>{{userName}}</h2>
            </div>
            <div>
                <h3>진행중인 단계</h3>
                <h3>완료한 단계</h3>
                <h3>스크랩</h3>
                <ol class="step-progress">
                    <li>{{stepInProgress[0]}} {{stepComplete[1]}} {{stepScrapped[2]}}</li>
                </ol>
                <ol class="step-complete">
                    <li>{{stepInProgress}} {{stepComplete}} {{stepScrapped}}</li>
                </ol>
                <ol class="step-scrapped">
                    <li>{{stepInProgress}} {{stepComplete}} {{stepScrapped}}</li>
                </ol>
                

            </div>


        </body>

    </div>
</template>

<script>
import HeaderLight from "../components/layout/Header-light.vue"
import LoginPopup from "../components/layout/login-popup.vue"

export default {
    name : 'Mypage',
    components : {
        HeaderLight, LoginPopup
    },
    data() {
        return {
            loginPopupState : 0
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
#mypage {
    padding: 20px;
}
</style>