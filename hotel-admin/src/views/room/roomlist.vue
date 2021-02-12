<template>
  <div class="user-table">
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column prop="id" label="房间id">
      </el-table-column>
      <el-table-column prop="number" label="房间号">
      </el-table-column>
      <el-table-column prop="type" label="房间类型">
      </el-table-column>
      <el-table-column prop="state" label="房间状态">
      </el-table-column>
      <el-table-column prop="maxPeople" label="最大容纳人数">
      </el-table-column>
      <el-table-column prop="introduce" label="客房简介">
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
      this.axios.get("http://localhost:8090/admin/listRooms")
      .then(res => {
        // console.log(res.data.data);
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
