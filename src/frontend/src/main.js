import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import mitt from 'mitt'; 

import VueSweetalert2 from 'vue-sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';

const emitter = mitt();
const app = createApp(App);
app.config.globalProperties.emitter = emitter;

app.use(router);
app.use(VueSweetalert2);
app.mount('#app')