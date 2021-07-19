<template>
    <div id="header">        
        <div id="logo">
            <a href=""><img id="logo-letter" src="../../assets/logo-letter.png" alt=""></a>
            <img id="logo-img" src="../../assets/main-page-logo.svg" alt="">
        </div>
        <div class="nav-menu">
            <router-link class="menu" to="/Signup">Sign up</router-link>
            <img class="nav-menu-division" src="../../assets/nav-menu-division.svg" alt="">
            <button class="menu" @click="openLoginPopup">Login</button>
            <img class="nav-menu-division" src="../../assets/nav-menu-division.svg" alt="">
            <router-link class="menu" to="/Mypage">My page</router-link>
        </div>
    </div>

</template>

<script>

export default {
    name: "header-dark",
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
    .nav-menu .nav-menu-division {
        margin: 0px 14px;
    }
</style>