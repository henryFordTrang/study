<!DOCTYPE html>
<html>
<head>
    <title>字典</title>   
</head>
<script src="/study/statics/libs/header.js"></script>
<body>
<div id="vue">
<h1>Hello ${name} !</h1>
<img class="verify-img" alt="如果看不清楚，请单击图片刷新！" title="点击刷新" class="pointer" :src="src" @click="refreshCode">
</div>
<script>
var vm = new Vue({
	el:"#vue",
	data(){
		return{
			src:'/study/sys/captcha'
		}
	},
	methods:{
		refreshCode(){
			this.src="/study/sys/captcha?t="+$.now();
		}
	},
	mounted(){
		
	}
})
</script>
</body>
</html>