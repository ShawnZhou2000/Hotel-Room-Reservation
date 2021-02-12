<template>
  <el-row>
    <el-col :span="8" :offset="8">
      <div class="accept-form">
        <div class="text item">
          <p class="accept-title">输入受理订单id</p>
          <el-form :model="accept" status-icon ref="accept" label-width="80px">
            <el-form-item label="id" prop="id">
              <el-input type="text" v-model="accept.id" ></el-input>
            </el-form-item>
          </el-form>
          <el-button :type="btnType" @click="acceptBtn" class="acceptbtn" :disabled="disabled">
            <i :class="iconstyle"></i> 确认受理
          </el-button>
        </div>
      </div>
    </el-col>
    <el-dialog title="提示" :visible.sync="dialogVisible" width="30%">
      <span>即将确认受理一个订单，你确定要这么做吗？</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="executeAcc">确定</el-button>
      </span>
    </el-dialog>
    <el-dialog title="提示" :visible.sync="accVisible" width="30%">
      <span>{{accMsg}}</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="accVisible = false">确定</el-button>
      </span>
    </el-dialog>
  </el-row>

</template>

<script>
  export default {
    data() {
      return {
        accept: {
          id: '',
        },
        iconstyle: 'el-icon-check',
        disabled: false,
        btnType: 'success',
        isRealaccept: false,
        dialogVisible: false,
        accVisible: false,
        accMsg: "",
      }
    },
    components: {
    },
    methods: {
      acceptBtn() {
        this.dialogVisible = true;
      },
      executeAcc() {
        this.dialogVisible = false;
        this.axios.post("http://localhost:8090/admin/handle?orderId=" + this.accept.id)
          .then(res => {
            this.accVisible = true;
            this.accMsg = "已执行受理订单操作。";
          })
          .catch(res => {
            this.accVisible = true;
            this.accMsg = "受理订单操作执行出错。";
          })
      }
    }
  }
</script>

<style>
  .accept-form {
    margin: 2rem;
  }
</style>
