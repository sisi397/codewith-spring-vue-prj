<template>
    <div id="header">
        <div id="logo">
            <router-link to="/"><img id="logo-letter" src="../../assets/logo-letter.png" alt=""></router-link>
            <img id="logo-img" src="../../assets/main-page-logo.svg" alt="">
        </div>
        <div class="nav-menu">
            <router-link class="menu" v-if="_loginState == 0" :to="{name : 'Signup'}">Sign up</router-link>
            <img class="nav-menu-division" v-if="_loginState == 0" src="../../assets/nav-menu-division.svg" alt="">
            <button class="menu" @click="openLoginPopup" v-if="_loginState == 0">Login</button>
            <button class="menu" @click="openLoginPopup" v-if="_loginState == undefined">Login</button>
            <button class="menu" @click="logout" v-if="_loginState == 1">Logout</button>
            <img class="nav-menu-division" v-if="_loginState == 1" src="../../assets/nav-menu-division.svg" alt="">
            <!-- 로그인이 된 경우 마이페이지로 이동 -->
            <router-link class="menu" :to="{name : 'Mypage'}" v-if="_loginState == 1">My page</router-link>
        </div>
        <button class="nav-menu-open" v-if="navMenuPopupState == 0" @click="openNavMenuPopup" type="button"><img src="../../assets/btn_hamburger.svg" alt=""></button>
        <button class="nav-menu-close" v-if="navMenuPopupState == 1" @click="closeNavMenuPopup" type="button"><img src="../../assets/btn_x.svg" alt=""></button>
        <NavMenuPopup
            class="navMenuPopup"
            v-if="navMenuPopupState == 1"
            :navMenuPopupState = "navMenuPopupState"
            @_loginOpen = "openLoginPopup"
            @_logout = "logout"
            >
        </NavMenuPopup>     
    </div>

</template>

<script>
import NavMenuPopup from "./nav-menu-popup.vue"
import axios from "axios"

export default {
    name: "header-dark",
    props : ['_loginPopupState', '_loginState'],
    components : {
        NavMenuPopup
    },
    data() {
        return {
            navMenuPopupState : 0, //0은 메뉴 팝업이 닫힌 상태, 1은 팝업이 열린 상태
        }
    },
    methods : {
        openLoginPopup() {
            this.navMenuPopupState = 0;
            this.loginPopupState = 1;
            this.$emit('_loginOpen');
        },
        openNavMenuPopup() {
            this.navMenuPopupState = 1;
        },
        closeNavMenuPopup() {
            this.navMenuPopupState = 0;
        },
        logout() {
            if (confirm("Logout 하시겠습니까?")) {
                axios
                .delete('http://3.36.131.138/api/logout')
                .then(() => {
                    localStorage.setItem('loginState', JSON.stringify(0)); //loginState 0으로 초기화 후 로컬스토리지에 다시 저장
                    localStorage.setItem('userName', 'Welcome');
                    this.$emit('_logout');
                })
                .catch(err => {
                    console.log();
                    console.log(err);
                })
            }
        }
    }
}
</script>

<style>
body {
    margin: 0px;
}
div{
    box-sizing: border-box;
}
a {
    text-decoration: none;
}
button {
    background-color: rgba(0, 0, 0, 0);
    border: 0;
    outline: 0;
}
#header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 100%; height: 70px;
    background-color: rgba(254, 249, 249, 0.1);
    border-radius: 20px;
    padding: 0px 30px;
    color: white;
    font-weight: 500;
}
#logo {
    display: flex;
    align-items: center;
}
#logo-letter {
    width: 110px;
}
#logo-img {
    width: 100px;
    margin-left: 30px;
}

.nav-menu .menu {
    color: #FFFFFF;
    font-size: 18px;
}
.nav-menu button {
    font-weight: 500;
}
.nav-menu .nav-menu-division {
    margin: 0px 14px;
}
#header .nav-menu-open {
    display: none;
}
#header .nav-menu-close {
    display: none;
}
#header .navMenuPopup {
    display: none;
}
@media screen and (max-width : 700px) {
    #header .navMenuPopup {
        display: flex;
    }
    #header .nav-menu {
        display: none;
    }
    #header .nav-menu-open {
        display: block;
        width: 28px; height: 28px;
        padding: 0px;
    }
    #header .nav-menu-close {
        display: block;
        width: 24px; height: 24px;
        padding: 0px;
    }
    #header .nav-menu-open img{
        width: 100%;
    }
}
</style>