<template>
  <div>
    <el-container class="wrapper">
      <el-main class="section">
        <div class="detail-header" :style="bgimg">

        </div>
        <el-card class="box-card detail-intro">
          <div slot="header" class="clearfix">
            <span class="detail-title">{{detaildata.type.typeName}}</span>
          </div>
          <div class="item text text-left">
           <el-row>
              <el-col :span="8" class="mb-1">
                <!-- <i data-feather="home" height="12"></i> -->
                <span>面积：</span>
                <span>{{detaildata.introduces.面积}}</span>
              </el-col>
             <el-col :span="8" class="mb-1">
                <!-- <i data-feather="users" height="12"></i> -->
                <span>容纳：</span>
                <span>{{detaildata.introduces.容纳}}</span>
              </el-col>
              <el-col :span="8" class="mb-1">
                <!-- <i data-feather="monitor" height="12"></i> -->
                <span>电脑：</span>
                <span>{{detaildata.introduces.电脑}}</span>
              </el-col>
              <el-col :span="8" class="mb-1">
                <!-- <i data-feather="coffee" height="12"></i> -->
                <span>热水：</span>
                <span>{{detaildata.introduces.热水}}</span>
              </el-col>
              <el-col :span="8" class="mb-1">
                <!-- <i data-feather="wifi" height="12"></i> -->
                <span>WIFI：</span>
                <span>{{detaildata.introduces.WIFI}}</span>
              </el-col>
              <el-col :span="8" class="mb-1">
                <!-- <i data-feather="tv" height="12"></i> -->
                <span>电视：</span>
                <span>{{detaildata.introduces.电视}}</span>
              </el-col>
              <el-col :span="8" class="mb-1">
                <!-- <i class="el-icon-dish m-i"></i> -->
                <span>早餐：</span>
                <span>{{detaildata.introduces.早餐}}</span>
              </el-col>
            </el-row>
          </div>
        </el-card>
        <el-card class="box-card detail-gotobuy">
          <div class="text item">
            <span class="detail-subtitle">房间价格</span>
          </div>
          <div class="text item">
            <span class="detail-price">￥{{detaildata.type.price}}</span>
            <span class="detail-danwei"> 元/晚</span>
          </div>
          <div class="text item">
            <el-button type="primary" class="btn-block" @click="resNow">
              立即预订
              <i class="el-icon-right"></i>
            </el-button>
          </div>
        </el-card>
      </el-main>
      <el-footer class="footer">
        <footbar></footbar>
      </el-footer>
    </el-container>
  </div>
  </div>
</template>

<script>
  import footbar from "@/components/footbar.vue";
  // import feather from 'feather-icons';
  export default {
    data() {
      return {
        bgimg: "background: url(../assets/img/detailimg.jpg);",
        id: "",
        detaildata: {},
      }
    },
    components: {
      footbar,
    },
    methods: {
      resNow() {
        this.$store.commit('setRes');
				this.$store.state.bookstat.roomid = this.id;
				this.$store.state.bookstat.roomType = this.detaildata.type.typeName;
        this.$router.push("/reservation");
      }
    },
    mounted() {
      // feather.replace();
      this.id = this.$route.query.roomId;
			if (this.id != undefined) {
				// console.log(this.id);
				this.axios.post("http://localhost:8090/user/roomDetail?roomId=" + this.id)
				.then(res => {
				  this.detaildata = res.data.data;
				  // console.log(this.detaildata.type.price);
				  this.bgimg = "background: url(" + this.detaildata.type.coverImage + ");";
				  this.bgimg += "background-size: cover;";
				})
				.catch(() => {
				  console.log("无了");
				});
			}
      
    }
  }
</script>

<style scoped="scoped">
  .detail-header {
    height: 30rem;
    border: 0px solid transparent;
    border-bottom-left-radius: 10px;
    border-bottom-right-radius: 10px;
  }

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

  .box-card {
    text-align: left;
  }

  .detail-intro {
    margin: -5rem 1rem 1rem 1rem;
  }

  .detail-gotobuy {
    margin: 1rem;
  }

  .detail-title {
    font-weight: bold;
    font-size: 1.2rem;
  }

  .detail-subtitle {
    font-size: 1.2rem;
  }

  .detail-price {
    font-size: 2rem;
    color: #F56C6C;
  }

  .detail-danwei {
    font-size: 1.1rem;
  }

  .m-i {
    margin: 0 0.28rem;
  }
</style>
