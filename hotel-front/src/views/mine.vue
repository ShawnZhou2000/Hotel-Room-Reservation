<template>
  <div>
    <el-container class="wrapper">
      <el-main class="section text-left">
        <div class="mine-bg"></div>
        <el-card class="box-card mine-card">
          <div class="text item">
            <el-row>
              <el-col :span="6" class="text-right">
                <img v-if="userdata.sex == 1" src="../assets/img/man.png" class="avatar" />
                <img v-else src="../assets/img/woman.png" class="avatar" />
              </el-col>
              <el-col :span="18">
                <p class="username">
                  {{userdata.userName}}
                  <i v-if="userdata.sex == 1" class="el-icon-male man"></i>
                  <i v-else class="el-icon-female woman"></i>
                </p>
                <p class="userid">id：{{userdata.id}}</p>
              </el-col>
            </el-row>
          </div>
        </el-card>
        <el-card class="box-card mine-card-tool">
          <div class="text item">
            <el-row>
              <el-col :span="6" class="text-center">
                <router-link to="/history" class="text-plain">
                  <p class="tool-icon">
                    <i class="el-icon-tickets order"></i>
                  </p>
                  <p class="tool-text">
                    我的订单
                  </p>
                </router-link>
              </el-col>
              <el-col :span="6" class="text-center">
                <router-link to="/checkinfo" class="text-plain">
                  <p class="tool-icon">
                    <i class="el-icon-user info"></i>
                  </p>
                  <p class="tool-text">
                    我的信息
                  </p>
                </router-link>
              </el-col>
              <el-col :span="6" class="text-center">
                <router-link to="/changepswd" class="text-plain">
                  <p class="tool-icon">
                    <i class="el-icon-setting password"></i>
                  </p>
                  <p class="tool-text">
                    密码设置
                  </p>
                </router-link>
              </el-col>
              <el-col :span="6" class="text-center">
                <router-link to="/contact" class="text-plain">
                  <p class="tool-icon">
                    <i class="el-icon-edit-outline contact"></i>
                  </p>
                  <p class="tool-text">
                    联系我们
                  </p>
                </router-link>
              </el-col>
            </el-row>
          </div>
        </el-card>
        <div class="btn-logout">
          <el-button type="danger" class="btn-block" @click="dialogVisible = true">
            <i class="el-icon-switch-button"></i> 退出登录
          </el-button>
        </div>
      </el-main>
      <el-footer class="footer">
        <footbar></footbar>
      </el-footer>
    </el-container>

    <el-dialog title="退出" :visible.sync="dialogVisible" width="95%">
      <span>你确定要退出登录吗？</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false">取消</el-button>
        <el-button @click="logoutBtn">确定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
  import footbar from "@/components/footbar.vue";
  export default {
    data() {
      return {
        dialogVisible: false,
        userdata: {
        }
      }
    },
    components: {
      footbar,
    },
    methods: {
      logoutBtn() {
        this.dialogVisible = false;
        this.$router.push("/login");
        this.axios.get("http://localhost:8090/user/logout")
        .then(res => {
          // console.log("成功" + res);
        })
        .catch(res => {
          console.log("失败" + res);
        });
      },
    },
    mounted() {
      this.axios.get("http://localhost:8090/user/userDetail")
      .then(res => {
        this.userdata = res.data.data;
        // console.log(this.userdata);
      })
      .catch(res => {
        console.log("失败" + res);
      });
    }
  }
</script>

<style scoped="scoped">
  .mine-bg {
    background: url(../assets/img/mine.jpg);
    height: 30vh;
    background-size: 100%;
    border: 0px solid transparent;
    border-bottom-left-radius: 10px;
    border-bottom-right-radius: 10px;
  }

  .text {
    font-size: 14px;
  }

  .item {
    /* padding: 18px 0; */
  }

  .mine-card {
    margin: -3rem 1rem 1rem 1rem;
  }

  .avatar {
    width: 5rem;
  }

  .username {
    font-size: 1.2rem;
    margin-bottom: 4px;
    margin-left: 1rem;
    color: #303133;
  }

  .userid {
    margin-left: 1rem;
    margin-top: 0;
    color: #909399;
  }

  .man {
    color: #409EFF;
    margin-left: 0.5rem;
  }

  .woman {
    color: #ff557f;
    margin-left: 0.5rem;
  }


  .mine-card-tool {
    margin: 1rem;
  }

  .tool-icon {
    font-size: 2rem;
    margin: 0.5rem 0;
  }

  .tool-text {
    margin-top: 0.5rem;
  }

  .order {
    color: #53A8FF;
  }

  .contact {
    color: #F56C6C;
  }

  .info {
    color: rgb(247, 160, 30);
  }

  .password {

    color: #67C23A;
  }
</style>
