<template>
  <el-container class="wrapper">
    <el-main class="section text-left">
      <el-row>
        <el-col :span="24">
          <el-card class="box-card">
            <el-row>
              <el-col :span="18">
                当前共查找到<span class="order-num">{{length}}</span>条订单
              </el-col>
              <el-col :span="6" class="text-right">
                <el-button @click="back" type="text" class="order-back">返回</el-button>
              </el-col>
            </el-row>
          </el-card>
        </el-col>
        <el-col :span="24" v-for="(order, index) in orderdata" :key="index">
          <el-card class="box-card">
            <el-row class="text item" :gutter="10">
              <el-col :span="8" class="order-head">
                <div class="text-center">
                  <i class="el-icon-tickets order-icon"></i>
                </div>
                <div class="text-center">
                  <p class="order-id">订单号</p>
                  <p class="order-id-data">{{order.order.id}}</p>
                  <p class="order-time">下单时间</p>
                  <p class="order-time-data">{{order.order.createTime}}</p>
                </div>
              </el-col>
              <el-col :span="16" class="order-detail">
                <p>预订房间类型：{{order.room.type.typeName}}</p>
                <p>预订房间号：{{order.room.number}}</p>
                <p>入住时间：{{order.order.inTime}}</p>
                <p>离店时间：{{order.order.leaveTime}}</p>
                <p>入住人数：{{order.order.realPeople}}</p>
                <p>提供发票：
                  <span v-if="order.order.fapiao == 1">是</span>
                  <span v-else>否</span>
                </p>
                <p>预计费用：{{order.order.realPrice}}</p>
                <p>是否受理：
                  <span v-if="order.order.flag == 1">是</span>
                  <span v-else>否</span>
                </p>
              </el-col>
            </el-row>
          </el-card>
        </el-col>
        <el-col :span="24" class="text-center mb-1">
          --- 没有了 ---
        </el-col>
      </el-row>

    </el-main>
    <el-footer class="footer">
      <footbar></footbar>
    </el-footer>
  </el-container>
</template>

<script>
  import footbar from "@/components/footbar.vue";
  export default {
    data() {
      return {
        orderdata: {

        },
        length: '',
      }
    },
    components: {
      footbar,
    },
    methods: {
      back() {
        this.$router.push("/mine");
      },
    },
    mounted() {
      this.axios.get("http://localhost:8090/user/historyOrder")
        .then(res => {
          // console.log(res.data.data);
          this.orderdata = res.data.data;
          this.length = this.orderdata.length;
        })
        .catch(res => {
          console.log("wa!");
        })
    }
  }
</script>

<style>
  .text {
    font-size: 14px;
  }

  .item {
    padding: 15px 0 0 0;
  }

  .box-card {
    margin: 1rem;
  }

  .order-icon {
    font-size: 2rem;
  }

  .order-detail p {
    margin-top: 0;
    color: #606266;
  }

  .order-id,
  .order-time {
    margin-bottom: 0;
    color: #303133;
  }

  .order-id-data,
  .order-time-data {
    margin-top: 0.2rem;
    font-size: 0.5rem;
    color: #606266;
  }

  .order-num {
    font-size: 1.3rem;
    color: #E6A23C;
    margin: 0 0.3rem;
  }

  .order-back {
    padding: 5px;
  }
</style>
