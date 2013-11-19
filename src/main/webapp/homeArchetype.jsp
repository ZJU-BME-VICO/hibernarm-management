<%@ page pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<!--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">-->
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel=stylesheet type="text/css" href="CSS/homeArchetype.css">
		<script src="JS/jquery-2.0.3.js"></script>
		<script type="text/javascript" src="JS/homeArchetype.js"></script>
	</head>
	<body>
	<div id="wrap">
	    <div id="header">
	    </div>
	    <div id="mainContent">
	        <div id="pictureFlag">
	             <div id="pictureFlagTakeFirst"></div>
	             <div id="mainPictureFlag"></div>
	             <div id="pictureFlagTakeSecond"></div>            
	        </div>
			<div id="searchDiv">
				<form method="post" action="/hibernarm-management/searchArchetype.action">
				<div id="searchBorderDiv">
					<input id="searchInput" type="text" name="condition"></input> 
					<input id="searchSubmit" type="image" src="image/search.png" alt="Submit"></input>
				</div>
				</form>
			</div>
			<div id="uploadDistrict">
			<div id="uploadDistrictTakenFirt"></div>
			<div id="uploadDistrictTakenSecond"></div>
			<div id="uploadDiv">
				<input type="image" src="image/AddFile.png" id="addFile" />
				Override changed?
				<input type="radio" name="overrideFile" value="Y" />Y
				<input type="radio" name="overrideFile" value="N" checked="checked" />N
				<input type="image" src="image/upload.jpg" id="uploadButton"/>
				<span id="uploadTip"></span>
				<form onsubmit="return false;"
					action="/hibernarm-management/fileUpload.action" method="post"
					enctype="multipart/form-data" id="uploadDomain">
					<span id="files"> 
						<input type='file' name='upload' class="selectionExist"/>
						<span></span>
						<span></span>
						<br /> 
						<input type='file' name='upload' class="selectionExist" />
						<span></span>
						<span></span>
						<br />
					</span>
					<br/>			
				</form>
			</div>			
			</div>
	  </div>
	</div>
	</body>
</html>