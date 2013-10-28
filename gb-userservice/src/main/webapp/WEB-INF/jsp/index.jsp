<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>PhoneGap</title>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="stylesheet" href="assets/css/bootstrap.css">
<link rel="stylesheet" href="assets/css/font-awesome.css" >
<script src="http://www.waileecn.com/mxk/assets/js/jquery-1.7.2.js" type="text/javascript" ></script>

</head>
<body >
   
 
    
    <button type="button" onclick="registerfunction()" id="gb_register_reg_btn">注册</button>


  <script type="text/javascript">
  
  function registerfunction(){
        //var password = $("#gb_register_password").val();
        //var email = $("#gb_register_email").val();
        //var name = $("#gb_register_name").val();
        alert(name);
        if(true){
          var datas = {"username":"刘一江","email":"dsada@qq.com","password":"122"};
          $.ajax({
    				url : "http://192.168.1.12:3080/gb-userservice/userRegist",
    				type : "POST",
    				cache : false,
    				async : false,
                    contentType: 'application/json',
    				data: JSON.stringify(datas),
    				dataType : "json",
    				success : function(item) {
    					alert(item);
    				}
	       });
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
  
  </script>

  </body>
</html>