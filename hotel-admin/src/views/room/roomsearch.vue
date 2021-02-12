<template>
  <div>
    <el-row>
      <el-col :span="8" :offset="8">
        <div class="search-form">
          <div class="text item">
            <p class="search-title">输入要查询的房间id</p>
            <el-form :model="search" status-icon ref="search" label-width="80px">
              <el-form-item label="id" prop="id">
                <el-input type="text" v-model="search.id"></el-input>
              </el-form-item>
            </el-form>
            <el-button :type="btnType" @click="searchBtn" class="searchbtn" :disabled="disabled">
              <i :class="iconstyle"></i> 查询
            </el-button>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12" v-if="isRealsearch" :offset="6">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>房间详情</span>
          </div>
          <div class="text item">
            <div class="user-list">
              <el-row :gutter="10">
                <el-col :span="4">
                  房间id:{{listData.id}}
                </el-col>
                <el-col :span="4">
                  最大人数:{{listData.maxPeople}}
                </el-col>
                <el-col :span="4">
                  房间号:{{listData.number}}
                </el-col>
                <el-col :span="4">
                  特性:{{listData.type.feature}}
                </el-col>
                <el-col :span="4">
                  房间类别:{{listData.type.id}}
                </el-col>
              </el-row>
              <el-row :gutter="10">
                <el-col :span="8">
                  房间简介:{{listData.type.introduce}}
                </el-col>
                <el-col :span="8">
                  每日单价:{{listData.type.price}}
                </el-col>
                <el-col :span="8">
                  类别名称:{{listData.type.typeName}}
                </el-col>
              </el-row>
              <el-row :gutter="10">
                <el-col :span="24">
                  房间参数:
                  <p>{{listData.room.introduce}}</p>
                </el-col>
              </el-row>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        search: {
          id: '',
        },
        iconstyle: 'el-icon-search',
        disabled: false,
        btnType: 'primary',
        isRealsearch: false,
        listData: {},
      }
    },
    components: {},
    methods: {
      searchBtn() {
        this.axios.post("http://localhost:8090/admin/roomDetail?roomId=" + this.search.id)
          .then(res => {
            this.listData = res.data.data;
            this.isRealsearch = true;
            console.log(res.data.data);
          })
          .catch(res => {
            console.log("err: " + res);
          })
      },
    }
  }
</script>

<style>
  .search-form {
    margin: 2rem;
  }

  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }

  .el-row {
    padding: 1rem 0;
  }
</style>
