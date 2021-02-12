<template>
  <el-row>
    <el-col :span="8" :offset="8">
      <div class="change-form">
        <div class="text item">
          <p class="change-title">输入修改房间id</p>
          <el-form :model="change" status-icon ref="change" label-width="80px">
            <el-form-item label="id" prop="id">
              <el-input type="text" v-model="change.id"></el-input>
            </el-form-item>
          </el-form>
          <el-button type="primary" @click="queryBtn" class="mb-1">
            <i class="el-icon-search"></i> 查询
          </el-button>
          <p class="change-title">输入修改信息</p>
          <el-form :model="change" status-icon ref="change" label-width="100px">
            <el-form-item label="房间号" prop="number">
              <el-input type="text" v-model="change.number"></el-input>
            </el-form-item>
            <el-form-item label="房间类型" prop="type">
              <el-input type="text" v-model="change.type"></el-input>
            </el-form-item>
            <el-form-item label="房间状态" prop="state">
              <el-input type="text" v-model="change.state"></el-input>
            </el-form-item>
            <el-form-item label="最大容纳人数" prop="maxPeople">
              <el-input type="text" v-model="change.maxPeople"></el-input>
            </el-form-item>
            <el-form-item label="客房简介" prop="introduce">
              <el-input type="text" v-model="change.introduce"></el-input>
            </el-form-item>
          </el-form>
          <el-button :type="btnType" @click="changeBtn" class="changebtn" :disabled="disabled">
            <i :class="iconstyle"></i> 确认修改
          </el-button>
        </div>
      </div>
    </el-col>
    <el-dialog title="提示" :visible.sync="dialogVisible" width="30%">
      <span>{{msgText}}</span>
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
          number: '',
          type: '',
          state: '',
          maxPeople: '',
          introduce: '',
        },
        iconstyle: 'el-icon-document-checked',
        disabled: false,
        btnType: 'primary',
        isRealchange: false,
        dialogVisible: false,
        msgText: "",
      }
    },
    components: {},
    methods: {
      queryBtn() {
        this.axios.post("http://localhost:8090/admin/roomDetail?roomId=" + this.change.id)
          .then(res => {
            console.log(res.data.data);
            this.change.number = res.data.data.room.number;
            this.change.type = res.data.data.type.id;
            this.change.state = res.data.data.room.state;
            this.change.maxPeople = res.data.data.room.maxPeople;
            this.change.introduce = res.data.data.room.introduce;
          })
          .catch(res => {
            console.log("err:" + res);
          })
      },
      changeBtn() {
        this.axios.post("http://localhost:8090/admin/updateRoom", this.change)
        .then(res => {
          this.dialogVisible = true;
          this.msgText = "信息修改成功。";
        })
        .catch(res => {
          this.dialogVisible = true;
          this.msgText = "信息修改失败。"
        })
      },
    }
  }
</script>

<style>
  .change-form {
    margin: 2rem;
  }
</style>
