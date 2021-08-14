<template>
    <div id="nav-menu-popup">
        <!-- <div class="black-bg" @click="closeCoursePopup">
        </div> -->
        <div class="menu-container">
            <router-link :to="{name : 'Signup'}" class="menu" v-if="loginState == 0">Sign up</router-link>
            <div class="menu" @click="openLoginPopup" v-if="loginState == 0">Login</div>
            <div class="menu" @click="logout" v-if="loginState == 1">Logout</div>
            <router-link :to="{name : 'Mypage'}" class="menu" v-if="loginState == 1">My page</router-link>
        </div>
    </div>
</template>

<script>
export default {
    name : "nav-menu-popup",
    data() {
        return {
            loginState : 0,
            navMenuPopupState : 0
        }
    },
    created() {
        this.loginState = JSON.parse(localStorage.getItem('loginState'));
    },
    methods : {
        openLoginPopup() {
            this.$emit('_loginOpen');
        },
        logout() {
            this.$emit("_logout");
        }
    }
}
</script>

<style>
#nav-menu-popup {
    position: absolute;
    top: 90px; right: 20px;
    background: rgba(52, 24, 87, 0.5);
    border-radius: 10px;
    border: 1px solid rgba(255, 249, 249, 0.6);
    margin-top: 20px;
}
#nav-menu-popup .menu-container {
    display: flex; flex-direction: column;
    align-items: flex-end;
    gap: 10px;
    width: 100px;
    padding: 20px 0;
    padding-right: 20px;
}
#nav-menu-popup .menu-container .menu{
    color: white;
    text-align: right;
    cursor: pointer;
}
</style>