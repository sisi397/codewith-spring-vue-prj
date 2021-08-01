<template>
<div class="router-container">
  <router-view></router-view>
</div>
</template>

<script>

export default {
  name: 'App',
  components: {
  },
  data() {
    return {
      
    }
  },
  methods : {
    
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
html {
    width: 100%; height: 100%;
}
body {
    width: 100%; height: 100%;
    overflow: hidden;
    background-color: #F5F5F5;
}
</style>