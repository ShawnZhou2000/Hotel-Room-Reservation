<template>
  <div class="mb-1">
    <el-row class="mt-1" v-for="(comm, index) in comment" :key="index">
      <el-col :span="12" :offset="6">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>#{{comm.comment.id}} From {{comm.user.userName}} (userid: {{comm.user.id}})</span>
            <span>Phone: {{comm.user.phone}} Email: {{comm.user.email}}</span>
          </div>
          <div class="text item">
            <p>留言内容： {{comm.comment.information}}</p>
            <p>评分等级： {{comm.comment.type}}星</p>
            
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        comment: [],
      }
    },
    methods: {

    },
    mounted() {
      this.axios.get("http://localhost:8090/admin/listComment")
      .then(res => {
        this.comment = res.data.data;
        console.log(this.comment);
      })
      .catch(res => {
        console.log("res: " + res);
      })
    }
  }
</script>

<style scoped="scoped">
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
</style>
