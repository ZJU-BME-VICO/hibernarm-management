$(document).ready(function() {
	    $("#addFile").click(function() {
             $("#files").append("<input type='file'  name='upload' class='selectionExist'/><span></span><span></span><br/>");
		});
		$("#files").on('change','input.selectionExist',function(){
			$(this).next().text("");
			$(this).next().next().text("");
			var chooseFile=this;
			if(this.files.length>0){
				var formData = new FormData();
				formData.append("singleFile",this.files[0]);
				$.ajax({
					type:"post",
					url:"/hibernarm-management/examExist.action",
					data:formData,
					dataType:"json",
					processData: false,  // tell jQuery not to process the data
					contentType: false,  // tell jQuery not to set contentType
				    success:function(data,textStatus,jqXHR){
				    	$(chooseFile).next().text(data.existStatus);
				    	$(chooseFile).next().next().text(data.existName);
				    }
				});
			}
			
		});
		$("#uploadDiv").on('click','#uploadButton',function(){
			$("#uploadTip").text("");
			var overiddenFlag=$("input[name='overrideFile']:checked").val();
			var fileInputList=$(".selectionExist");
			var fileListSent=[];
			for(var i=0;i<fileInputList.length;i++){
                  if(fileInputList[i].files.length>0){
                	  if($(fileInputList[i]).next().text()==""){
                		  $("#uploadTip").text("validation of files is executing,wait a moment");
                		  return;
                	  }
                	  if($(fileInputList[i]).next().text()=="NONE"){
                		  fileListSent.push(fileInputList[i].files[0]);               		  
                	  }
                	  if($(fileInputList[i]).next().text()=="CHANGED"&&overiddenFlag=="Y"){
                		  fileListSent.push(fileInputList[i].files[0]);               		  
                	  }
                	  
                	
                  }
			}
			if(fileListSent.length>0){
				var formData = new FormData();		
				for(var i=0;i<fileListSent.length;i++){
					formData.append("upload",fileListSent[i]);
				}
				$.ajax({
					type:"post",
					url:"/hibernarm-management/fileUpload.action",
					data:formData,
					dataType:"json",
					processData: false,  // tell jQuery not to process the data
					contentType: false,  // tell jQuery not to set contentType
				    success:function(data,textStatus,jqXHR){
                          if(data.uploadResult=="fail"){
                        	  $("#uploadTip").text("upload files failes"+data.uploadResultDescription);
                          }else if(data.uploadResult=="success"){
                        	  $("#uploadTip").text("upload files success");
                          }
				    }
				});
			}else if(fileListSent.length==0){
				 $("#uploadTip").text("there are no files needed to be uploaded");
			}
			
		});

});