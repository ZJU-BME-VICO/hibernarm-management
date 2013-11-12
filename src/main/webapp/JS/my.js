$(document).ready(function() {
		$("#addFile").click(function() {
             $("#insertInputPoint").after("<input type='file'  name='upload'/><br/>");
		});
		$(".selectionExist").change(function(){
			//alert(this.files[0].name);
			var fileName=this.files[0].name;
			$.ajax({
				type:"post",
				url:"/hibernarm-management/asyn/examExist.action",
				data:{examName:fileName},
			    success:function(data,textStatus,jqXHR){
			    }
			});
		});

});