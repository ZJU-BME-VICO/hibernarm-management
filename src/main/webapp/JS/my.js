function checkUploadForm(){
	$("#uploadTip").text("");
	var validateResult=true;
	var filesList=$(".selectionExist");
	var selectFilesInput=0;
	var unformedFiles=0;
	var existFile=0;
    for(var i=0;i<filesList.length;i++){
    	if(filesList[i].files.length>0){
    		var singleFileName=filesList[i].files[0].name;
    		var suf=singleFileName.substring(singleFileName.lastIndexOf(".")+1);
    		if(suf!="arm"&&suf!="adl"){
    			unformedFiles++;
    		}
    		if($(filesList[i]).next().text().length>0){
    			existFile++;
    		}
    		selectFilesInput++;
    	}
    }
    if(selectFilesInput==0){
    	$("#uploadTip").text("you must choose at least one file");
    	validateResult=false;
    }
    if(unformedFiles>0){
    	$("#uploadTip").text("files type are not correct");
    	validateResult=false;
    }
    if($("input[name='overrideFile']:checked").val()=="N"&&existFile==selectFilesInput&&existFile>0){
    	$("#uploadTip").text("beacuse you don't want to override existed files ,it is unnecessary to upload your files which are exist");
    	validateResult=false;
    }
	return validateResult;
}
$(document).ready(function() {
		$("#addFile").click(function() {
             $("#files").append("<input type='file'  name='upload' class='selectionExist'/><span></span><span style='display:none'></span><br/>");
		});
		$("#files").on('change','input.selectionExist',function(){
			$(this).next().text("");
			var chooseFile=this;
			if(this.files.length>0){
				var formData = new FormData();
				formData.append("singleFile",this.files[0]);
				$.ajax({
					type:"post",
					url:"/hibernarm-management/asyn/examExist.action",
					data:formData,
					processData: false,  // tell jQuery not to process the data
					contentType: false,  // tell jQuery not to set contentType
				    success:function(data,textStatus,jqXHR){
				    	if(data=="EXISTED"){
				    		$(chooseFile).next().text("\""+fileName+"\" existed");
				    	}
				    	
				    }
				});
			}
			
		});

});