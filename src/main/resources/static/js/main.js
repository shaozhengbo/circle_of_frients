var vm = new Vue({
	el : '#app',
	data : {
		username : "1",
		password : "1",
		result : "请登录"
	},
	methods : {
		login() {
			axios({
		        method:'get',
		         url:'User/getUserByUsername?username='+vm.username
		     }).then(function(res){
		     });
		}
	}
});