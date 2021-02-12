<template>
  <div class="user-table">
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column prop="id" label="id" class="text-center">
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间">
      </el-table-column>
      <el-table-column prop="email" label="邮箱">
      </el-table-column>
      <el-table-column prop="userName" label="用户名">
      </el-table-column>
      <el-table-column prop="password" label="密码">
      </el-table-column>
      <el-table-column prop="sex" label="性别">
      </el-table-column>
      <el-table-column prop="phone" label="手机号码">
      </el-table-column>
      <el-table-column prop="jifen" label="账户积分">
      </el-table-column>
      <el-table-column prop="state" label="状态">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        tableData: []
      }
    },
    methods: {

    },
    mounted() {
      this.axios.get("http://localhost:8090/admin/listUsers")
      .then(res => {
        // console.log(res.data.data);
        this.tableData = res.data.data;
      })
      .catch(res => {
        console.log("err:" + res);
      });
    }
  }
</script>

<style>
  .user-table {
    margin: 2rem;
  }
</style>
