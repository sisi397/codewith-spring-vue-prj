<template>
    <div id="header">
        <div id="logo">
            <router-link to="/"><img id="logo-letter" src="../../assets/logo-letter.png" alt=""></router-link>
            <img id="logo-img" src="../../assets/main-page-logo.svg" alt="">
        </div>
        <div class="nav-menu">
            <router-link class="menu" to="/Signup">Sign up</router-link>
            <img class="nav-menu-division" src="../../assets/nav-menu-division.svg" alt="">
            <button class="menu" @click="openLoginPopup" v-if="loginState == 0">Login</button>
            <button class="menu" @click="logout" v-if="loginState == 1">Logout</button>
            <img class="nav-menu-division" v-if="loginState == 1" src="../../assets/nav-menu-division.svg" alt="">
            <!-- 로그인이 된 경우 마이페이지로 이동 -->
            <router-link class="menu" to="/Mypage" v-if="loginState == 1">My page</router-link>
        </div>
        <button class="nav-hamburger-menu" @click="openNavMenuPopup" type="button"><img src="../../assets/btn_hamburger.svg" alt=""></button>
        <NavMenuPopup
            class="navMenuPopup"
            >
            <!-- v-if="navMenuPopupState == 1" -->
        </NavMenuPopup>     
    </div>

</template>

<script>
import NavMenuPopup from "./nav-menu-popup.vue"

export default {
    name: "header-dark",
    props : ['_loginPopupState', '_loginState'],
    components : {
        NavMenuPopup
    },
    data() {
        return {
            navMenuPopupState : 0, //0은 팝업이 닫힌 상태, 1은 팝업이 열린 상태
            loginState : 0 //0은 로그인이 안 된 상태, 1은 로그인이 된 상태
        }
    },
    created() {
        this.loginState = JSON.parse(localStorage.getItem('loginState'));
        console.log("loginState : ", this.loginState, "loginPopupState : ", this.loginPopupState);
    },
    methods : {
        openLoginPopup() {
            console.log("로그인 창 띄움");
            this.loginPopupState = 1;
            this.$emit('_loginOpen');
        },
        openNavMenuPopup() {
            if (this.navMenuPopupState == 0) {
                console.log("nav-menu 창 띄움");
                this.navMenuPopupState = 1;
            } else {
                console.log("nav-menu 창 끔");
                this.navMenuPopupState = 0;
            }
        },
        logout() {
            if (confirm("Logout 하시겠습니까?", this.loginState)) {
            this.loginState = 0
            this.$emit('_logout', this.loginState)
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
.nav-hamburger-menu {
    display: none;
}
#header .navMenuPopup {
    display: none;
}
@media screen and (max-width : 1040px) {
    #header .nav-menu {
        display: none;
    }
    #header .nav-hamburger-menu {
        display: block;
        width: 28px; height: 28px;
        padding: 0px;
    }
    #header .nav-hamburger-menu img{
        width: 100%;
    }
    #header .navMenuPopup {
        display: flex;
        flex-direction: column;
    }
}
</style>