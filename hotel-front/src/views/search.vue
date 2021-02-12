<template>
  <div>
    <el-container class="wrapper">
      <el-main class="section text-left">
        <el-card class="box-card p-box" shadow="never">
          <div class="text item">
            <el-row :gutter="10">
              <el-col :span="18">
                <el-form label-position="left" ref="form" :model="form">
                  <el-row :gutter="10">
                    <el-col :span="12">
                      <el-date-picker type="datetime" placeholder="入住时间" v-model="form.indate" style="width: 100%;"></el-date-picker>
                    </el-col>
                    <el-col :span="12">
                      <el-date-picker type="datetime" placeholder="离店时间" v-model="form.leavedate" style="width: 100%;"></el-date-picker>
                    </el-col>
                  </el-row>
                </el-form>
              </el-col>
              <el-col :span="6">
                <el-button type="primary" icon="el-icon-search" class="btn-block" @click="searchBtn"></el-button>
              </el-col>
            </el-row>
            <el-row class="mt-1">
              <el-col :span="24">
                <span class="search-info">
                  在您提供的时间段内，共查找到 <span class="search-num">{{len}}</span> 条结果
                </span>
              </el-col>
            </el-row>
          </div>
        </el-card>
        <el-row>
          <el-col :span="12">
            <el-row>
              <el-col :span="24" v-for="(room, index) in showlist1" :key="index" :offset="0">
                <el-card shadow="never" :body-style="{ padding: '0px' }">
                  <div @click="toDetail(room.id)">
                    <img :src="room.type.coverImage" class="image">
                  </div>
                  <div style="padding: 14px;">
                    <div @click="toDetail(room.id)">
                      <p class="search-title">
                        房间{{room.number}}
                        ：
                        {{room.type.typeName}}
                      </p>
                    </div>
                    <p class="search-price">￥{{room.type.price}}</p>
                    <p class="search-comment">{{room.type.feature}}</p>
                  </div>
                </el-card>
              </el-col>
            </el-row>
          </el-col>
          <el-col :span="12">
            <el-row>
              <el-col :span="24" v-for="(room, index) in showlist2" :key="index" :offset="0">
                <el-card shadow="never" :body-style="{ padding: '0px' }">
                  <div @click="toDetail(room.id)">
                    <img :src="room.type.coverImage" class="image">
                  </div>
                  <div style="padding: 14px;">
                    <div @click="toDetail(room.id)">
                      <p class="search-title">
                        房间{{room.number}}
                        ：
                        {{room.type.typeName}}
                      </p>
                    </div>
                    <p class="search-price">￥{{room.type.price}}</p>
                    <p class="search-comment">{{room.type.feature}}</p>
                  </div>
                </el-card>
              </el-col>
            </el-row>
          </el-col>
        </el-row>
      </el-main>
      <el-footer class="footer">
        <footbar></footbar>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
  import footbar from "@/components/footbar.vue";
  export default {
    data() {
      return {
        form: {
          indate: '',
          leavedate: '',
          currentDate: new Date()
        },
        showlist1: [],
        showlist2: [],
        searchSet: [],
        len: '',
      }
    },
    components: {
      footbar,
    },
    methods: {
      searchBtn() {
        this.axios.post("http://localhost:8090/user/listRoom", {
            "inTime": this.changeTimeStr(this.form.indate),
            "leaveTime": this.changeTimeStr(this.form.leavedate)
          })
          .then(res => {
            this.$store.state.searchSet = res.data.data;
            this.$store.state.searchTime.inTime = this.changeTimeStr(this.form.indate);
            this.$store.state.searchTime.leaveTime = this.changeTimeStr(this.form.leavedate);
            this.updateWaterFall();
						this.updateTime();
          })
          .catch(() => {
            console.log("error");
          })
      },
      updateWaterFall() {
        this.searchSet = this.$store.state.searchSet;
        this.len = this.searchSet.length;
        for (let i in this.searchSet) {
          if (i <= this.len / 2) {
            this.showlist1.push(this.searchSet[i]);
          } else {
            this.showlist2.push(this.searchSet[i]);
          }
        }
        // console.log(this.showlist1, this.showlist2);
        this.form.indate = this.$store.state.searchTime.inTime;
        this.form.leavedate = this.$store.state.searchTime.leaveTime;
      },
      changeTimeStr(str) {
        str = str.toString();
        str = str.replace(/ GMT.+$/, ''); // Or str = str.substring(0, 24)
        let d = new Date(str);
        let a = [d.getFullYear(), d.getMonth() + 1, d.getDate(), d.getHours(), d.getMinutes(), d.getSeconds()];
        for (var i = 0, len = a.length; i < len; i++) {
          if (a[i] < 10) {
            a[i] = '0' + a[i];
          }
        }
        str = a[0] + '-' + a[1] + '-' + a[2] + ' ' + a[3] + ':' + a[4];
        return str;
      },
      toDetail(id) {
        this.$router.push("/detail?roomId=" + id);
      },
			updateTime() {
				this.$store.state.bookstat.inTime = this.changeTimeStr(this.form.indate);
				this.$store.state.bookstat.leaveTime = this.changeTimeStr(this.form.leavedate);
			}
    },
    mounted() {
      this.updateWaterFall();
			this.updateTime();
    }
  }
</script>

<style scoped="scoped">
  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }

  .text {
    font-size: 14px;
  }

  .item {
    /* padding: 2px 0; */
  }

  .box-card {
    width: 100vw;
  }

  /*  .el-card__body {
    padding: 0 20px;
  } */

  .search-info {
    font-size: 0.9rem;
    color: #606266;
  }

  .search-num {
    font-size: 1.1rem;
    color: #409EFF;
  }

  .search-title {
    font-weight: bold;
    font-size: 1.2rem;
    color: #303133;
  }

  .search-price {
    font-size: 1.1rem;
    color: #F56C6C;
  }

  .search-comment {
    font-size: 0.9rem;
    color: #909399;
  }

  p {
    margin-top: 0;
    margin-bottom: 0.3rem;
  }
</style>
