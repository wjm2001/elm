<template>
  <div class="wrapper">
    <div class="head">
      <img src="../assets/img.png">
      <p>{{string}}</p>
    </div>

    <!-- 底部菜单部分 -->
    <Footer></Footer>
  </div>
</template>

<script>
import Footer from '../components/Footer.vue';

export default {
  name: "Mine",
  data(){
    return{
      string:{}
    }
  },
  created() {
    this.$axios.get('user/backendA').then(response =>{
      this.string = response.data
    })
  },
  components:{
    Footer
  }
}
</script>

<style scoped>
/* 总容器 */
.wrapper {
  width: 100%;
  height: 100%;
}

.wrapper .head{
  width: 100%;
}

img{
  width: 30vw;
  padding: 35vw 35vw 0 35vw;
}

p{
  text-align: center;
}

</style>
