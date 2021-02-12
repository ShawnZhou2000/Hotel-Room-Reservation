<template>
  <el-row>
    <el-col :span="8" :offset="8">
      <div class="reject-form">
        <div class="text item">
          <p class="reject-title">输入取消订单id</p>
          <el-form :model="reject" status-icon ref="reject" label-width="80px">
            <el-form-item label="id" prop="id">
              <el-input type="text" v-model="reject.id" ></el-input>
            </el-form-item>
          </el-form>
          <el-button :type="btnType" @click="rejectBtn" class="rejectbtn" :disabled="disabled">
            <i :class="iconstyle"></i> 确认取消
          </el-button>
        </div>
      </div>
    </el-col>
    <el-dialog title="危险!" :visible.sync="dialogVisible" width="30%">
      <span>取消订单是一个危险的操作，你确定要这么做吗？</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="danger" @click="executeRej">确定</el-button>
      </span>
    </el-dialog>
    <el-dialog title="提示" :visible.sync="rejVisible" width="30%">
      <span>{{rejMsg}}</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="rejVisible = false">确定</el-button>
      </span>
    </el-dialog>
  </el-row>

</template>

<script>
  export default {
    data() {
      return {
        reject: {
          id: '',
        },
        iconstyle: 'el-icon-warning-outline',
        disabled: false,
        btnType: 'danger',
        isRealreject: false,
        dialogVisible: false,
        rejVisible: false,
        rejMsg: "",
      }
    },
    components: {
    },
    methods: {
      rejectBtn() {
        this.dialogVisible = true;
      },
      executeRej() {
        this.dialogVisible = false;
        this.axios.post("http://localhost:8090/admin/unsubscribe?orderId=" + this.reject.id)
          .then(res => {
            this.rejVisible = true;
            this.rejMsg = "已执行取消订单操作。";
          })
          .catch(res => {
            this.rejVisible = true;
            this.rejMsg = "取消订单操作执行出错。";
          })
      }
    }
  }
</script>

<style>
  .reject-form {
    margin: 2rem;
  }
</style>
