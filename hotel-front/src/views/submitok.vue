<template>
  <el-card class="box-card">
    <div class="item text-center">
      <el-row>
        <el-col :span="24">
          <img src="../assets/img/ok.png" class="img-status" />
        </el-col>
        <el-col :span="24">
          <div class="text text-left">
            <p>我们已经收到您的订单提交信息，请按照预订的入住时间到前台办理入住手续。</p>
            <p>您可以在我的→我的订单中查看所有的订单信息。</p>
          </div>
          <el-button type="primary" @click="check">查看订单</el-button>
          <el-button @click="back">返回主页</el-button>
        </el-col>
      </el-row>
    </div>
  </el-card>
</template>

<script>
 import footbar from "@/components/footbar.vue";
 import store from './../store';
 export default {
   data() {
     return {
       
     }
   },
   components: {
     footbar,
   },
   methods: {
     back() {
       this.$store.commit('setFind');
       this.$router.push("/findroom");
     },
     check() {
       this.$store.commit('setMine');
       this.$router.push("/history");
     }
   }
 }
</script>

<style>
  .text {
    font-size: 1rem;
  }

  .item {
    /* padding: 18px 0; */
  }
  
  .box-card {
    margin: 1rem;
  }
</style>
