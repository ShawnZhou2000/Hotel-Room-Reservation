<template>
  <el-row>
    <el-col :span="8" :offset="8">
      <div class="del-form">
        <div class="text item">
          <p class="del-title">输入删除房间id</p>
          <el-form :model="del" status-icon ref="del" label-width="80px">
            <el-form-item label="id" prop="id">
              <el-input type="text" v-model="del.id"></el-input>
            </el-form-item>
          </el-form>
          <el-button :type="btnType" @click="delBtn" class="delbtn" :disabled="disabled">
            <i :class="iconstyle"></i> 确认删除
          </el-button>
        </div>
      </div>
    </el-col>
    <el-dialog title="危险!" :visible.sync="dialogVisible" width="30%">
      <span>删除房间是一个危险的操作，你确定要这么做吗？</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="danger" @click="executeDel">确定</el-button>
      </span>
    </el-dialog>
    <el-dialog title="提示" :visible.sync="delVisible" width="30%">
      <span>{{delMsg}}</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="delVisible = false">确定</el-button>
      </span>
    </el-dialog>
  </el-row>

</template>

<script>
  export default {
    data() {
      return {
        del: {
          id: '',
        },
        iconstyle: 'el-icon-warning-outline',
        disabled: false,
        btnType: 'danger',
        isRealdel: false,
        dialogVisible: false,
        delVisible: false,
        delMsg: "",
      }
    },
    components: {},
    methods: {
      delBtn() {
        this.dialogVisible = true;
      },
      executeDel() {
        this.dialogVisible = false;
        this.axios.post("http://localhost:8090/admin/deleteRoom?roomId=" + this.del.id)
          .then(res => {
            this.delVisible = true;
            this.delMsg = "已执行删除操作。";
          })
          .catch(res => {
            this.delVisible = true;
            this.delMsg = "删除操作执行出错。";
          })
      }
    }
  }
</script>

<style>
  .del-form {
    margin: 2rem;
  }
</style>
