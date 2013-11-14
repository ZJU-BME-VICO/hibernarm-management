<%@ page pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<!--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">-->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>CDRSearch</title>
<link rel=stylesheet type="text/css"
	href="<%=request.getContextPath()%>/CSS/home.css">
<script src="<%=request.getContextPath()%>/JS/jquery-2.0.3.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/JS/my.js"></script>
<style>
</style>
</head>
<body>

	<div id="searchDiv">
		<form method="post"
			action="/hibernarm-management/home/archetypeNameSearch.action">
			<input type="text" name="condition"
				style="width: 500px; height: 30px; font-size: 150%; vertical-align: middle;"></input> <input
				style="vertical-align: middle;" type="image"
				src="<%=request.getContextPath()%>/image/search.gif" alt="Submit">
		</form>
	</div>
	<div id="uploadDiv">
		<input type="button" value="add file" id="addFile" />
		
		<form onsubmit="return false;"
			action="/hibernarm-management/home/fileUpload.action" method="post"
			enctype="multipart/form-data" id="uploadDomain">
			<span id="files"> <input type='file' name='upload'
				class="selectionExist" /><span></span><span></span><br /> <input type='file'
				name='upload' class="selectionExist" /><span></span><span></span><br />
			</span>
			When file existed,override it?<input type="radio" name="overrideFile" value="Y"/>Y
			 <input type="radio" name="overrideFile" value="N" checked="checked">N<br/>
			<input type="button" value="upload" id="uploadButton"/>
		</form>
		<span id="uploadTip"></span>
	</div>
</body>
</html>