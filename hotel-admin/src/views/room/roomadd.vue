<template>
  <el-row>
    <el-col :span="8" :offset="8">
      <div class="addnew-form">
        <div class="text item">
          <p class="addnew-title">输入房间信息</p>
          <el-form :model="addnew" status-icon ref="addnew" label-width="100px">
            <el-form-item label="房间号" prop="number">
              <el-input type="text" v-model="addnew.number"></el-input>
            </el-form-item>
            <el-form-item label="房间类型" prop="type">
              <el-input type="text" v-model="addnew.type"></el-input>
            </el-form-item>
            <el-form-item label="房间状态" prop="state">
              <el-input type="text" v-model="addnew.state"></el-input>
            </el-form-item>
            <el-form-item label="最大容纳人数" prop="maxPeople">
              <el-input type="text" v-model="addnew.maxPeople"></el-input>
            </el-form-item>
            <el-form-item label="客房简介" prop="introduce">
              <el-input type="text" v-model="addnew.introduce"></el-input>
            </el-form-item>
          </el-form>
          <el-button :type="btnType" @click="addnewBtn" class="addnewbtn" :disabled="disabled">
            <i :class="iconstyle"></i> 确认添加
          </el-button>
        </div>
      </div>
    </el-col>
    <el-dialog title="提示" :visible.sync="dialogVisible" width="30%" >
      <span>新房间信息添加成功。</span>
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
        addnew: {
          number: '',
          type: '',
          state: '',
          maxPeople: '',
          introduce: '',
        },
        iconstyle: 'el-icon-document-checked',
        disabled: false,
        btnType: 'primary',
        isRealaddnew: false,
        dialogVisible: false,
      }
    },
    components: {},
    methods: {
      addnewBtn() {
        this.axios.post("http://localhost:8090/admin/addRoom", this.addnew)
        .then(res => {
          this.dialogVisible = true;
        })
        .catch(res => {
          console.log("err: " + res);
        })
      },
    }
  }
</script>

<style>
  .addnew-form {
    margin: 2rem;
  }
</style>
