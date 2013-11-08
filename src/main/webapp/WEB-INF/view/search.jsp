<%@ page pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>CDRSearch</title>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#addFile").click(function() {
             $("#insertInputPoint").after("<input type='file'  name='upload'/><br/>");
		});

	});
</script>
</head>
<body>
	<form method="post"
		action="/hibernarm-management/home/archetypeNameSearch.action">
		<input type="text" name="condition"></input> <input type="submit"
			value="search">
	</form>
	<input type="button" value="add file" id="addFile" />
	<form onsubmit="return true;"
		action="/hibernarm-management/home/fileUpload.action"  method="post" enctype="multipart/form-data">
		<span id="files"> <input type='file' name='upload'/>
			<br id="insertInputPoint"/>
		</span>
		<input type="submit" value="上传" />		
	</form>
</body>
</html>