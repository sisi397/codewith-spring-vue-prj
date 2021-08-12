<template>
    <div id="nav-menu-popup">
        <!-- <div class="black-bg" @click="closeCoursePopup">
        </div> -->
        <div class="menu-container">
            <div class="menu">Sign up</div>
            <div class="menu">Login</div>
            <div class="menu">My page</div>
        </div>
    </div>
</template>

<script>
export default {
    name : "nav-menu-popup",
    data() {
        return {
            
        }
    }
}
</script>

<style>
#nav-menu-popup {
    position: absolute;
    top: 90px; right: 20px;
    background: white;
    border-radius: 10px;
}
#nav-menu-popup .menu-container {
    width: 100px;
}
#nav-menu-popup .menu-container .menu{
    color: black;
}
</style>