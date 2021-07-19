<template>
    <div class="background">
        <img class="start" src="../../assets/background-tag-head.svg" :style="imgPosition[0]" alt="">
        <img class="middle" src="../../assets/background-tag-body.svg" :style="imgPosition[1]" alt="">
        <img class="end" src="../../assets/background-tag-style.svg" :style="imgPosition[2]" alt="">
        <img class="start" src="../../assets/background-tag-head.svg" :style="imgPosition[3]" alt="">
        <img class="middle" src="../../assets/background-tag-body.svg" :style="imgPosition[4]" alt="">
        <img class="end" src="../../assets/background-tag-style.svg" :style="imgPosition[5]" alt="">
    </div>
</template>

<script>
export default {
    name : "background-main",
    data() {
        return {
            repeatIndex : [0, 1, 2, 0, 1, 2],
            img : ['../../assets/background-tag-head.svg', './../assets/background-tag-body.svg', '../../assets/background-tag-style.svg'],
            imgPosition : ['top: 200px; left: 50px;', 'top: 310px; left: 355px;', 'top: 130px; left: 280px;', 'top : 150px; right : 35px', 'top : 183px; right : 430px', 'bottom : 358px; right : -20px'],
            shining : ['start', 'middle', 'end']
        }
    }
}
</script>

<style scoped>
.background {
    min-width: 1040px;
}
img {
    width: 100px;
    position: absolute;
    animation-name: shining;
    animation-duration: 3s;
    animation-timing-function: linear;
    animation-iteration-count: infinite; /* 애니메이션 무한 반복 */
    animation-direction: alternate;  /* 애니메이션이 끝나면 반대방향으로 다시 이동 */
}
.start {
    animation-delay: 0s;
}
.middle {
    animation-delay: 1s;
}
.end {
    animation-delay: 2s;
}

@keyframes shining {
    from {
        opacity: 0;
    }
    to {
        opacity: 1;
    }
}

@media screen and (max-width : 1040px) {
  img {
      display: none;
  }
}
</style>