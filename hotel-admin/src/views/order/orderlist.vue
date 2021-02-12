<template>
  <div class="user-table">
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column prop="id" label="id">
      </el-table-column>
      <el-table-column prop="userId" label="用户id">
      </el-table-column>
      <el-table-column prop="roomId" label="房间id">
      </el-table-column>
      <el-table-column prop="createTime" label="下单时间">
      </el-table-column>
      <el-table-column prop="inTime" label="入住时间">
      </el-table-column>
      <el-table-column prop="leaveTime" label="离店时间">
      </el-table-column>
      <el-table-column prop="realPrice" label="总计价格">
      </el-table-column>
      <el-table-column prop="realPeople" label="入住人数">
      </el-table-column>
      <el-table-column prop="fapiao" label="是否需要发票">
      </el-table-column>
      <el-table-column prop="flag" label="受理状态">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        tableData: [],
      }
    },
    methods: {

    },
    mounted() {
      this.axios.get("http://localhost:8090/admin/listOrders")
      .then(res => {
        console.log(res.data.data);
        this.tableData = res.data.data;
      })
      .catch(res => {
        console.log("err: " + res);
      })
    }
  }
</script>

<style>
  .user-table {
    margin: 2rem;
  }
</style>
