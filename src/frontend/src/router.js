import { createWebHistory, createRouter } from 'vue-router';
import Home from './views/Home.vue';
import MyPage from "./views/Mypage.vue";
import SignUp from "./views/Signup.vue";
import Training from "./views/training.vue";

const router = createRouter({
    history : createWebHistory(),
    routes : [
        {
            path : '/',
            name : 'Home',
            component : Home
        },
        {
            path : '/Mypage',
            name : 'Mypage',
            component : MyPage
        },
        {
            path : '/Signup',
            name : 'Signup',
            component : SignUp
        },
        {
            path : '/Training',
            name : 'Training',
            component : Training
        }
    ]
})

export default router;