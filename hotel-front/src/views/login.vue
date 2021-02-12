<template>
  <div>
    <el-container class="wrapper">
      <el-main class="section text-center">
        <div class="login-bg"></div>
        <el-card class="box-card login-card">
          <div class="text item">
            <p class="login-title">宾馆客房预订系统</p>
            <el-form :model="login" status-icon ref="login" label-width="80px">
              <el-form-item label="账户邮箱" prop="email">
                <el-input type="text" v-model="login.email" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="密码" prop="pass">
                <el-input type="password" v-model="login.password" autocomplete="off"></el-input>
              </el-form-item>
            </el-form>
          </div>
        </el-card>
        <el-button circle :type="btnType" @click="loginBtn" class="loginbtn" :disabled="disabled">
          <i :class="iconstyle"></i>
        </el-button>
        <div class="register">
          <p>
            <router-link to="/register">注册账号</router-link>
          </p>
<!--          <p>
            <div @click="nologinBtn">不登录，随便看看</div>
          </p> -->
        </div>
      </el-main>
    </el-container>
    <el-footer class="footer text-center">
      <copyright></copyright>
    </el-footer>
  </div>
</template>

<script>
  import copyright from "@/components/copyright.vue";
  import store from './../store';
  export default {
    data() {
      return {
        login: {
          email: '',
          password: '',
        },
        iconstyle: 'el-icon-right',
        disabled: false,
        btnType: 'primary',
        isRealLogin: true,
      }
    },
    components: {
      copyright,
    },
    methods: {
      loginBtn() {
        this.iconstyle = "el-icon-loading";
        this.disabled = true;
        // console.log(this.login);
        this.axios.post("http://localhost:8090/user/login", {
          "email": this.login.email,
          "password": this.login.password,
        }).then(res => {
          if (res.data.code == 200) {
            this.iconstyle = "el-icon-check";
            this.btnType = "success";
            setTimeout(() => {
              this.disabled = false;
              this.$store.commit('setFind');
              this.$router.push("/findroom");
            }, 2000);
          }
          else {
            this.iconstyle = "el-icon-close";
            this.btnType = "danger";
            setTimeout(() => {
              this.disabled = false;
              this.iconstyle = "el-icon-right";
              this.btnType = "primary";
            }, 2000);
          }
        })
        .catch(res => {
          console.log(res);
          this.iconstyle = "el-icon-close";
          this.btnType = "danger";
          setTimeout(() => {
            this.disabled = false;
            this.iconstyle = "el-icon-right";
            this.btnType = "primary";
          }, 2000);
        })
      
      },
      nologinBtn() {
        this.$store.commit('setFind');
        this.$router.push("/findroom");
      }
    }
  }
</script>

<style scoped="scoped">
  .login-bg {
    background: rgb(247, 160, 30);
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

  .login-card {
    margin: -7rem 1rem 1rem 1rem;
  }
  
  .login-title {
    font-size: 2rem;
    font-weight: lighter;
    margin-top: 1rem;
  }
  
  .wrapper {
    min-height: 90vh;
  }
  
  .el-main {
    max-height: 90vh;
  }
  
  #app {
    overflow: hidden;
  }
  
  .loginbtn {
    width: 7rem;
    height: 7rem;
    font-size: 3rem;
    margin-top: 2rem;
  }
  
  .register {
    margin-top: 5vh;
  }
  
  .register a, .register div {
    color: #409EFF;
  }
  
  .register p {
    margin: 0.5rem;
  }
  
</style>
