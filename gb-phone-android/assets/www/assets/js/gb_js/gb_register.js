$(function () {
   
    var registerfunction = function(){
        var password = $("#gb_register_password").val();
        var email = $("#gb_register_email").val();
        var name = $("# gb_register_name").val();
        if(isName(name) && isPassword(password)){
          var datas = {"username":"刘一江","email":email,"password":password}
          alert(servicepath + "userRegist");
          $.ajax({
				url : servicepath + "userRegist",
				type : "POST",
				cache : false,
				async : false,
				data: datas,
				dataType : "json",
				success : function(item) {
					alert(item);
				}
	       });
        }
    }
   
   function isEmail(strEmail) {
		if (strEmail.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1){
			return true;
		}else{
			alert("邮箱格式有误！");
			return false;
		}
	} 
   
   function isName(parm){
		if(parm != ""){
			return true;
		}else{
		    alert("昵称不能为空");
			return false;
		}
	}
	
   function isPassword(parm){
		if(parm != ""){
			return true;
		}else{
		    alert("密码不能为空");
			return false;
		}
	}
   
   function callback(){
   }
   
    $("#gb_register_reg_btn").click(registerfunction);

});