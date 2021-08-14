<template>
    <div id="header-light">
        <div id="logo">
            <router-link to="/"><img id="logo-letter" src="../../assets/logo-letter-purple.svg" alt=""></router-link>
            <img id="logo-img" src="../../assets/sub-page-logo.svg" alt="">
        </div>
        <div class="nav-menu">
            <button class="menu-light" @click="openLoginPopup" v-if="loginState == 0">Login</button>
            <button class="menu-light" @click="logout" v-if="loginState == 1">Logout</button>
        </div>
    </div>

</template>

<script>
import axios from "axios"

export default {
    name: "header-light",
    props : ['_loginPopupState'],
    components : {
        
    },
    data() {
        return {
            loginPopupState : this._loginPopupState, //0은 닫힌 상태, 1은 열린 상태
            loginState : 0,
            navMenuPopupState : 0, //0은 메뉴 팝업이 닫힌 상태, 1은 팝업이 열린 상태
        }
    },
    created() {
        this.loginState = JSON.parse(localStorage.getItem('loginState'));
    },
    methods : {
        openLoginPopup() {
            console.log("로그인 창 띄움");
            this.loginPopupState = 1;
            this.$emit('_loginOpen');
        },
        logout() {
            if (confirm("Logout 하시겠습니까? Logout 후에는 Home으로 이동합니다.")) {
                axios
                .delete('http://3.36.131.138/api/logout')
                .then(() => {
                    console.log("로그아웃 되었습니다.");
                    localStorage.setItem('loginState', JSON.stringify(0)); //loginState 0으로 초기화 후 로컬스토리지에 다시 저장
                    localStorage.setItem('userName', 'Welcome');
                    // this.$router.push({name : 'Home'});
                    this.$emit('_logout');
                })
                .catch(err => {
                    console.log();
                    console.log(err);
                })
            }
        },
        openNavMenuPopup() {
            console.log("메뉴 창 열림");
            this.navMenuPopupState = 1;
        },
        closeNavMenuPopup() {
            console.log("메뉴 창 닫힘");
            this.navMenuPopupState = 0;
        }
    },
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
#header-light .nav-menu-open {
    display: none;
}
#header-light .nav-menu-close {
    display: none;
}
#header-light .navMenuPopup {
    display: none;
}
/* @media screen and (max-width: 700px){
    #header-light .navMenuPopup {
        display: flex;
    }
    #header-light .nav-menu {
        display: none;
    }
    #header-light .nav-menu-open {
        display: block;
        width: 28px; height: 28px;
        padding: 0px;
    }
    #header-light .nav-menu-close {
        display: block;
        width: 24px; height: 24px;
        padding: 0px;
    }
    #header-light .nav-menu-open img{
        width: 100%;
    }
} */
</style>