<template>
  <el-row>
    <el-col :span="8" :offset="8">
      <div class="change-form">
        <div class="text item">
          <p class="change-title">输入修改用户id</p>
          <el-form :model="change" status-icon ref="change" label-width="80px">
            <el-form-item label="id" prop="id">
              <el-input type="text" v-model="change.id"></el-input>
            </el-form-item>
          </el-form>
          <el-button type="primary" @click="queryBtn" class="changebtn mb-1">
            <i class="el-icon-search"></i> 查询
          </el-button>
          <p class="change-title">输入修改信息</p>
          <el-form :model="change" status-icon ref="change" label-width="80px">
            <el-form-item label="用户名" prop="username">
              <el-input type="text" v-model="change.userName"></el-input>
            </el-form-item>
            <el-form-item label="创建时间" prop="username">
              <el-input type="datetime" v-model="change.createTime"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input type="text" v-model="change.password" disabled="disabled"></el-input>
            </el-form-item>
            <el-form-item label="积分" prop="jifenb">
              <el-input type="text" v-model="change.jifen"></el-input>
            </el-form-item>
            <el-form-item label="手机号码" prop="phone">
              <el-input type="text" v-model="change.phone"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
              <el-input type="email" v-model="change.email"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="gender">
              <el-select v-model="change.sex" placeholder="请选择">
                <el-option label="男(1)" value="1"></el-option>
                <el-option label="女(0)" value="0"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="账号状态" prop="state">
              <el-input type="text" v-model="change.state"></el-input>
            </el-form-item>
          </el-form>
          <el-button :type="btnType" @click="changeBtn" class="changebtn" :disabled="disabled">
            <i :class="iconstyle"></i> 确认修改
          </el-button>
        </div>
      </div>
    </el-col>
    <el-dialog title="提示" :visible.sync="dialogVisible" width="30%">
      <span>信息修改成功。</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false">确定</el-button>
      </span>
    </el-dialog>
  </el-row>

</template>

<script>
  export default {
    data() {
      return {
        change: {
          id: '',
          createTime: '',
          userName: '',
          password: '',
          email: '',
          phone: '',
          sex: '',
          jifen: '',
          state: '',
        },
        genderRaw: "",
        iconstyle: 'el-icon-document-checked',
        disabled: false,
        btnType: 'primary',
        isRealchange: false,
        dialogVisible: false,
      }
    },
    components: {},
    methods: {
      changeBtn() {
        this.axios.post("http://localhost:8090/admin/updateUser", this.change)
          .then(res => {
            console.log(res);
            this.dialogVisible = true;
          })
          .catch(res => {
            // console.log("error: " + res);
          })
      },
      queryBtn() {
        this.axios.post("http://localhost:8090/admin/getUserById?userId=" + this.change.id)
          .then(res => {
            console.log(res.data.data);
            this.change.createTime = res.data.data.createTime;
            this.change.userName = res.data.data.userName;
            this.change.password = res.data.data.password;
            this.change.email = res.data.data.email;
            this.change.phone = res.data.data.phone;
            this.change.jifen = res.data.data.jifen;
            this.change.state = res.data.data.state;
            this.change.sex = res.data.data.sex;
          })
          .catch(res => {
            console.log("err:" + res);
          })
      }
    },
    computed: {
      showgender() {
        if (this.genderRaw == 0)
          return "女";
        else
          return "男";
      }
    }
  }
</script>

<style>
  .change-form {
    margin: 2rem;
  }
</style>
