<template>
    <div id="header-light">
        <div id="logo">
            <router-link to="/"><img id="logo-letter" src="../../assets/logo-letter-purple.svg" alt=""></router-link>
            <img id="logo-img" src="../../assets/sub-page-logo.svg" alt="">
        </div>
        <div class="nav-menu">
            <router-link class="menu-light" to="/Signup">Sign up</router-link>
            <img class="nav-menu-division" src="../../assets/nav-menu-division-dark.svg" alt="">
            <button class="menu-light" @click="openLoginPopup">Login</button>
            <img class="nav-menu-division" src="../../assets/nav-menu-division-dark.svg" alt="">
            <router-link class="menu-light" to="/Mypage">My page</router-link>
        </div>
    </div>

</template>

<script>

export default {
    name: "header-light",
    props : ['_loginPopupState'],
    components : {

    },
    data() {
        return {
            loginPopupState : this._loginPopupState //0은 닫힌 상태, 1은 열린 상태
        }
    },
    methods : {
        openLoginPopup() {
            console.log("로그인 창 띄움");
            this.loginPopupState = 1;
            this.$emit('_loginOpen');
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
        cursor: pointer;
    }
    #header-light {
        display: flex;
        justify-content: space-between;
        align-items: center;
        width: 100%; height: 70px;
        background-color: white;
        border-radius: 20px;
        padding: 0px 30px;
        font-weight: 500;
        border: thin solid #e2e2e2;
    }
    #header-light #logo {
        display: flex;
        align-items: center;
    }
    #header-light #logo-letter {
        width: 110px;
    }
    #header-light #logo-img {
        width: 100px;
        margin-left: 30px;
    }

    #header-light .nav-menu .menu-light {
        color: #4C1993;
        font-size: 18px;
    }
    #header-light .nav-menu .nav-menu-division {
        margin: 0px 14px;
    }
</style>