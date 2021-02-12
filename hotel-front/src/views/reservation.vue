<template>
	<div>
		<el-container class="wrapper">
			<el-main class="section text-left">
				<div class="res-bg"></div>
				<el-card class="box-card res-form">
					<div slot="header" class="clearfix">
						<span>预订客房</span>
					</div>
					<div class="text item">
						<el-form :model="resinfo" label-width="80px">
							<el-form-item label="客房类型" >
								<div>{{bookstat.roomType}}</div>
							</el-form-item>

							<el-form-item label="入住时间">
								<el-col :span="24">
									<div>{{bookstat.inTime}} 至</div>
								</el-col>
								<!-- <el-col class="line text-center" :span="12"></el-col> -->
								<el-col :span="24">
									<div>{{bookstat.leaveTime}}</div>
								</el-col>
							</el-form-item>
							
							<el-form-item label="房间号码">
								<div>{{roomtype.number}}</div>
							</el-form-item>

							<el-form-item label="住店人数">
								<el-input-number v-model="resinfo.num" :min="1" :max="maxp" label="请输入" ></el-input-number>
								<span class="ml-1">人</span>
							</el-form-item>
							
							<el-form-item label="提供发票">
								<el-switch v-model="resinfo.isNeedInvoice" ></el-switch>
							</el-form-item>
							
							<el-form-item label="价格">
								<span v-model="resinfo.type" class="room-price">{{sumprice}} 元</span>
							</el-form-item>
							
							<el-form-item>
								<el-button type="primary" @click="submitForm('ruleForm')" >立即预订</el-button>
								<!-- <el-button @click="resetForm('ruleForm')">重置</el-button> -->
							</el-form-item>
						</el-form>
					</div>
				</el-card>
				<!--        <el-button type="success" @click="oktest">预订成功测试</el-button>
        <el-button type="danger" @click="failtest">预订失败测试</el-button> -->
			</el-main>
			<el-footer class="footer">
				<footbar></footbar>
			</el-footer>

			<el-dialog title="提示" :visible.sync="dialogVisible" width="95%">
				<span>你确定要提交预订单吗？</span>
				<span slot="footer" class="dialog-footer">
					<el-button @click="dialogVisible = false">取消</el-button>
					<el-button type="primary" @click="confirmSubmitForm">确定</el-button>
				</span>
			</el-dialog>


		</el-container>
	</div>
</template>

<script>
	import footbar from "@/components/footbar.vue";
	export default {
		data() {
			return {
				bookstat: {},
				roomNumber: '',
				resinfo: {
					num: '',
					isNeedInvoice: false,
				},
				price: 0,
				sumprice: 0,
				maxp: 1,
				lockCondition: false,
				isNotSelect: true,
				checkBtnDis: false,
				searchicon: 'el-icon-search',
				searchBtnType: 'primary',
				dialogVisible: false,
				roomtype: "",
			}
		},
		components: {
			footbar,
		},
		methods: {
			submitForm(formName) {
				this.dialogVisible = true;
			},
			confirmSubmitForm() {
				this.dialogVisible = false;
				let json = {
					"roomId": this.bookstat.roomid,
					"inTime": this.bookstat.inTime,
					"leaveTime": this.bookstat.leaveTime,
					"realPeople": this.resinfo.num,
					"fapiao": this.fapiao,
				};
				console.log(json);
				this.axios.post("http://localhost:8090/user/bookRoom", json)
					.then(res => {
						this.$router.push("/submitok");
					})
					.catch(res => {
						this.$router.push("/submitfail");
					})
			},
			resolveData() {
				// console.log(123123);
				this.axios.post("http://localhost:8090/user/roomDetail?roomId=" + this.bookstat.roomid)
					.then(res => {
						this.roomtype = res.data.data;
						console.log(this.roomtype);
						this.maxp = this.roomtype.maxPeople;
						this.price = this.roomtype.type.price;
						this.calcPrice();
					})
					.catch(() => {
						console.log("死了");
					})
			},
			changeTimeStr(str) {
				str = str.toString();
				str = str.replace(/ GMT.+$/, ''); // Or str = str.substring(0, 24)
				let d = new Date(str);
				let a = [d.getFullYear(), d.getMonth() + 1, d.getDate(), d.getHours(), d.getMinutes(), d.getSeconds()];
				for (var i = 0, len = a.length; i < len; i++) {
					if (a[i] < 10) {
						a[i] = '0' + a[i];
					}
				}
				str = a[0] + '-' + a[1] + '-' + a[2] + ' ' + a[3] + ':' + a[4];
				return str;
			},
			calcPrice() {
				let stra = this.bookstat.leaveTime;
				let sa1 = stra.split(" ")[0].split("-");
				let sa2 = stra.split(" ")[1].split(":");
				let leaveTime = new Date();
				leaveTime.setFullYear(sa1[0],sa1[1],sa1[2]);
				leaveTime.setHours(sa2[0]);
				leaveTime.setMinutes(sa2[1]);
				let strb = this.bookstat.inTime;
				let sb1 = strb.split(" ")[0].split("-");
				let sb2 = strb.split(" ")[1].split(":");
				let inTime = new Date();
				inTime.setFullYear(sb1[0],sb1[1],sb1[2]);
				inTime.setHours(sb2[0]);
				inTime.setMinutes(sb2[1]);
				let realday = (leaveTime - inTime) / 86400000;
				// console.log(Math.ceil(realday));
				realday = Math.ceil(realday);
				let price = this.price;
				this.sumprice = price * realday;
				// console.log(this.bookstat.leaveTime,this.bookstat.inTime,this.bookstat.leaveTime - this.bookstat.inTime);
			}
		},
		mounted() {
			this.bookstat = this.$store.state.bookstat;
			// console.log(this.bookstat);
			this.resolveData();
		},
		computed: {
			fapiao() {
				if (this.resinfo.isNeedInvoice == true)
					return 1;
				else
					return 0;
			}
		}
	}
</script>

<style scoped="scoped">
	.res-bg {
		background: url(../assets/img/reservation.jpg);
		background-size: cover;
		height: 30vh;
		border: 0px solid transparent;
		border-bottom-left-radius: 10px;
		border-bottom-right-radius: 10px;
	}

	.text {
		font-size: 14px;
	}

	.item {
		padding: 9px 0;
	}

	.res-form {
		margin: -5rem 1rem 1rem 1rem;
	}

	/* .el-card__body {
    padding: 20px 20px 20px 0px;
  } */

	.el-select {
		width: 100%;
	}

	.room-price {
		font-size: 1.3rem;
		color: #F56C6C;
	}
</style>
