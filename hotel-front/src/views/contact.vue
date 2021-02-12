<template>
  <div>
    <el-container class="wrapper">
      <el-main class="section text-center">
        <div class="contact-bg"></div>
        <el-card class="box-card contact-card">
          <div class="text item">
            <p class="contact-title">联系我们</p>
            <div class="contact-info text-left">
              <p class="contact-eng">DaXueCheng Hotel, Inc</p>
              <p>山东省济南市长清区紫薇路2333号</p>
              <p>长清大学城星级宾馆</p>
              <p>Tel：0538-12345678</p>
              <p>Fax：0538-87654321</p>
            </div>
            <p class="contact-subtitle">服务评价</p>
            <span>为我们的服务打个分吧~</span>
            <el-rate v-model="contact.eva" :colors="colors" class="mb-1">
            </el-rate>
            <el-form :model="contact" status-icon ref="contact" class="mb-1">
              <el-input type="textarea" :autosize="{ minRows: 4, maxRows: 10}" placeholder="输入你的建议或意见吧..." v-model="contact.comment">
              </el-input>
            </el-form>
            <el-button @click="submitBtn" :type="btnType" class="contactbtn" :disabled="disabled">
              <i :class="iconstyle"></i> {{btnText}}
            </el-button>
            <el-button @click="back">返回</el-button>
          </div>
        </el-card>
      </el-main>
    </el-container>
    <el-footer class="footer text-center">
      <footbar></footbar>
    </el-footer>
  </div>
</template>

<script>
  import footbar from "@/components/footbar.vue";
  import store from './../store';
  export default {
    data() {
      return {
        contact: {
          comment: '',
          eva: null,
        },
        iconstyle: 'el-icon-document-checked',
        disabled: false,
        btnType: 'primary',
        isRealcontact: false,
        colors: ['#99A9BF', '#F7BA2A', '#FF9900'],
        btnText: '提交反馈'
      }
    },
    components: {
      footbar,
    },
    mounted() {
      this.$store.commit("setMine");
    },
    methods: {
      back() {
        this.$router.push("/mine");
      },
      submitBtn() {
        console.log(this.contact);
        this.disabled = true;
        this.iconstyle = "el-icon-loading";
        this.axios.post("http://localhost:8090/user/publishComment", {
          "information": this.contact.comment,
          "type": this.contact.eva,
        })
        .then(res => {
          console.log(res);
					if (res.data.code == 200) {
						this.iconstyle = "el-icon-check";
						this.btnType = "success";
						this.btnText = "您的反馈已提交！";
					}
          else {
						this.iconstyle = "el-icon-close";
						this.btnType = "danger";
						this.btnText = res.data.data;
					}
        })
        .catch(res => {
          console.log(res);
          this.iconstyle = "el-icon-close";
          this.btnType = "danger";
          this.btnText = "您的反馈未能提交"
        })
      }
    },
  }
</script>

<style scoped="scoped">
  .contact-bg {
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

  .contact-card {
    margin: -5rem 1rem 1rem 1rem;
  }

  .contact-title {
    font-size: 2rem;
    font-weight: lighter;
    margin-top: 1rem;
  }

  .wrapper {
    min-height: 94vh;
  }

  .el-main {
    max-height: 94vh;
  }

  #app {
    overflow: hidden;
  }

  .contactbtn {
    /* margin-top: 2rem; */
  }

  .contact {
    margin-top: 5vh;
  }

  .contact a,
  .contact div {
    color: #409EFF;
  }

  .contact p {
    margin: 0.5rem;
  }

  .contact-eng {
    font-size: 1rem;
    font-weight: bold;
    margin-bottom: 0;
  }

  .contact-info p {
    margin: 0.4rem 0;
  }

  .contact-subtitle {
    font-size: 1.5rem;
    font-weight: lighter;
  }
</style>
