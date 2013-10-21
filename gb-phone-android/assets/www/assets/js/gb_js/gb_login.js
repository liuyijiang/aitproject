
$(function () {

    var checklogin = function(){
      return true;
    }

   var userlogin = function(){
      $("#gb_login_progress").show();
      if(checklogin()){
         window.location.href = "gb_index.html";
      }else{
      	alert("登陆失败");
      }
     
   }
   

   $("#gb_login_btn").click(userlogin);

});