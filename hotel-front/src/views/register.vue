<template>
  <div>
    <el-container class="wrapper">
      <el-main class="section text-center">
        <div class="register-bg"></div>
        <el-card class="box-card register-card">
          <div class="text item">
            <p class="register-title">账号注册</p>
            <el-form :model="register" status-icon ref="register" label-width="80px">
              <el-form-item label="用户名" prop="username">
                <el-input type="text" v-model="register.username" suffix-icon="el-icon-user"></el-input>
              </el-form-item>
              <el-form-item label="密码" prop="password">
                <el-input type="password" v-model="register.password" suffix-icon="el-icon-lock"></el-input>
              </el-form-item>
              <el-form-item label="确认密码" prop="confirm">
                <el-input type="password" v-model="register.confirm" suffix-icon="el-icon-lock"></el-input>
              </el-form-item>
              <el-form-item label="手机号码" prop="phone">
                <el-input type="text" v-model="register.phone" suffix-icon="el-icon-mobile-phone"></el-input>
              </el-form-item>
              <el-form-item label="邮箱" prop="confirm">
                <el-input type="email" v-model="register.email" suffix-icon="el-icon-message"></el-input>
              </el-form-item>
              <el-form-item label="性别" prop="gender">
                <el-radio v-model="register.gender" label="1">男</el-radio>
                <el-radio v-model="register.gender" label="0">女</el-radio>
              </el-form-item>
            </el-form>
            <el-button :type="btnType" @click="registerBtn('register')" class="registerbtn" :disabled="disabled">
              <i :class="iconstyle"></i> 注册账号
            </el-button>
            <el-button @click="resetForm('register')">重置</el-button>
          </div>
        </el-card>

        <div class="register">
          <p>
            <router-link to="/login">已有账号？立即登录</router-link>
          </p>
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
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.register.checkPass !== '') {
            this.$refs.register.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.register.pass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        register: {
          username: '',
          password: '',
          confirm: '',
          email: '',
          phone: '',
          gender: '',
        },
        iconstyle: 'el-icon-document-checked',
        disabled: false,
        btnType: 'primary',
        isRealRegister: false,
        rules: {
          password: [{
            validator: validatePass,
            trigger: 'blur'
          }],
          confirm: [{
            validator: validatePass2,
            trigger: 'blur'
          }],
        }
      }
    },
    components: {
      copyright,
    },
    methods: {
      resetForm(register) {
        // console.log(this.$refs[register]);
        this.$refs[register].resetFields();
      },
      registerBtnTest(register) {
        // console.log(this.$refs[register].validate);
        this.$refs[register].validate((valid) => {
          if (valid) {
            console.log(this.register);
          } else {
            console.log("err");
            return false;
          }
        })
      },
      registerBtn(register) {
        this.$refs[register].validate((valid) => {
          if (valid) {
            this.iconstyle = "el-icon-loading";
            this.disabled = true;
            this.axios.post("http://localhost:8090/user/register", {
                "email": this.register.email,
                "userName": this.register.username,
                "password": this.register.password,
                "sex": this.register.gender,
                "phone": this.register.phone
              })
              .then(res => {
                if (res.status == 200) {
                  this.iconstyle = "el-icon-check";
                  this.btnType = "success";
                  setTimeout(() => {
                    this.disabled = false;
                    this.$router.push("/login");
                  }, 2000);
                } else {
                  this.iconstyle = "el-icon-close";
                  this.btnType = "danger";
                  setTimeout(() => {
                    this.disabled = false;
                    this.iconstyle = "el-icon-document-checked";
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
                  this.iconstyle = "el-icon-document-checked";
                  this.btnType = "primary";
                }, 2000);
              })
          } else {
            console.log('error submit!!');
            this.iconstyle = "el-icon-close";
            this.btnType = "danger";
            setTimeout(() => {
              this.disabled = false;
              this.iconstyle = "el-icon-document-checked";
              this.btnType = "primary";
            }, 2000);
            return false;
          }
        });
      },
    }
  }
</script>

<style scoped="scoped">
  .register-bg {
    background: rgb(247, 160, 30);
    height: 20vh;
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

  .register-card {
    margin: -5rem 1rem 1rem 1rem;
  }

  .register-title {
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

  .registerbtn {
    /* margin-top: 2rem; */
  }

  .register a,
  .register div {
    color: #409EFF;
  }

  .register p {
    margin: 0.5rem;
  }
</style>
