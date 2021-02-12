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
						<el-form :model="resinfo" :rules="rules" ref="ruleForm" label-width="80px" class="demo-ruleForm">
							<el-form-item label="客房类型" prop="type" required @click="resolveData">
								<el-select v-model="resinfo.type" placeholder="请选择" :disabled="lockCondition">
									<el-option v-for="item in roomtype" :key="item.id" :label="item.typeName" :value="item.id">
									</el-option>
								</el-select>
							</el-form-item>

							<el-form-item label="入住时间" required>
								<el-col :span="24" class="mb-1">
									<el-form-item prop="indate">
										<el-date-picker :disabled="lockCondition" type="datetime" placeholder="入住日期" v-model="resinfo.indate" style="width: 100%;">
										</el-date-picker>
									</el-form-item>
								</el-col>
								<!-- <el-col class="line text-center" :span="24">至</el-col> -->
								<el-col :span="24">
									<el-form-item prop="leavedate">
										<el-date-picker :disabled="lockCondition" type="datetime" placeholder="离店日期" v-model="resinfo.leavedate" style="width: 100%;">
										</el-date-picker>
									</el-form-item>
								</el-col>
							</el-form-item>

							<el-form-item>
								<el-button :type="searchBtnType" @click="checkroom" :disabled="checkBtnDis">
									<i :class="searchicon"></i>
									查询可用房间
								</el-button>
							</el-form-item>

							<!-- 提供一个房间号码对房间id的映射，即用户选择的是房间号，但存的是房间id -->
							<el-form-item label="房间号码" prop="id" required>
								<el-select v-model="resinfo.id" placeholder="请先选择房间类型" :disabled="isNotSelect">
									<el-option v-for="item in roomnumber" :key="item.id" :label="item.number" :value="item.id">
									</el-option>
								</el-select>
							</el-form-item>

							<el-form-item label="住店人数" prop="num" required>
								<el-input-number v-model="resinfo.num" :min="1" :max="maxp" label="请输入" :disabled="isNotSelect"></el-input-number>
								<span class="ml-1">人</span>
							</el-form-item>
							<el-form-item label="提供发票" prop="invoice">
								<el-switch v-model="resinfo.isNeedInvoice" :disabled="isNotSelect"></el-switch>
							</el-form-item>
							<el-form-item label="价格">
								<span v-model="resinfo.type" class="room-price">{{sumprice}} 元</span>
							</el-form-item>
							<el-form-item>
								<el-button type="primary" @click="submitForm('ruleForm')" :disabled="isNotSelect">立即预订</el-button>
								<el-button @click="resetForm('ruleForm')">重置</el-button>
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
				resinfo: {
					id: '',
					type: '',
					indate: '',
					leavedate: '',
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
				roomnumber: [],
				roomtype: [],
				rules: {
					type: [{
						required: true,
						message: '请选择房间类型',
						trigger: 'change'
					}],
					id: [{
						required: true,
						message: '请选择房间号',
						trigger: 'change'
					}],
					indate: [{
						type: 'date',
						required: true,
						message: '请选择入住时间',
						trigger: 'change'
					}],
					leavedate: [{
						type: 'date',
						required: true,
						message: '请选择离店时间',
						trigger: 'change'
					}],
				}
			}
		},
		components: {
			footbar,
		},
		methods: {
			submitForm(formName) {
				this.dialogVisible = true;
			},
			resetForm(formName) {
				this.checkBtnDis = false;
				this.searchicon = 'el-icon-search';
				this.searchBtnType = "primary";
				this.isNotSelect = true;
				this.price = 0;
				this.sumprice = 0; 
				this.resinfo.isNeedInvoice = false;
				this.$refs[formName].resetFields();
			},
			oktest() {
				this.$router.push("/submitok");
			},
			failtest() {
				this.$router.push("/submitfail");
			},
			confirmSubmitForm() {
				this.dialogVisible = false;
				let json = {
					"roomId": parseInt(this.resinfo.id),
					"inTime": this.changeTimeStr(this.resinfo.indate),
					"leaveTime": this.changeTimeStr(this.resinfo.leavedate),
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
				this.axios.get("http://localhost:8090/user/listTypes")
					.then(res => {
						this.roomtype = res.data.data;
						// console.log(this.roomtype);
					})
					.catch(() => {
						console.log("死了");
					})
			},
			checkroom() {
				this.checkBtnDis = true;
				this.searchicon = 'el-icon-loading';
				this.axios.post("http://localhost:8090/user/listRoomsByTypeId", {
						"typeId": this.resinfo.type,
						"inTime": this.changeTimeStr(this.resinfo.indate),
						"leaveTime": this.changeTimeStr(this.resinfo.leavedate)
					})
					.then(res => {
						this.roomnumber = res.data.data;
						console.log(this.roomnumber);
						this.searchicon = "el-icon-check";
						this.searchBtnType = "success";
						this.isNotSelect = false;
						this.lockCondition = true;
						this.price = this.roomnumber[0].type.price;
						this.maxp = this.roomnumber[0].maxPeople;
						this.calcPrice();
					})
					.catch(() => {
						this.searchicon = "el-icon-close";
						this.searchBtnType = "danger";
						console.log("error");
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
				let realday = Math.abs(this.resinfo.leavedate - this.resinfo.indate) / 86400000;
				console.log(Math.ceil(realday));
				realday = Math.ceil(realday);
				let price = this.price;
				this.sumprice = price * realday;
				console.log(this.sumprice, price, realday);
			}
		},
		mounted() {
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
