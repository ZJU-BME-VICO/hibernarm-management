<%@ page pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Bootstrap 101 Template</title>
<!-- 最新 Bootstrap 核心 CSS 文件 -->
<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="/hibernarm-management/CSS/bootstrap.min.css">
<script src="/hibernarm-management/JS/jquery-2.0.3.min.js"></script>
<script src="/hibernarm-management/JS/homeArchetype.js"></script>
<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet"
	href="/hibernarm-management/CSS/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="/hibernarm-management/CSS/blueimp-gallery.min.css">
<link rel="stylesheet"
	href="/hibernarm-management/CSS/jquery.fileupload.css">
<link rel="stylesheet"
	href="/hibernarm-management/CSS/jquery.fileupload-ui.css">
<link rel="stylesheet"
	href="/hibernarm-management/CSS/bootstrap-switch.min.css">
<link rel="stylesheet"
	href="/hibernarm-management/CSS/flat-ui-fonts.min.css">
<link rel="stylesheet"
	href="/hibernarm-management/CSS/homeArchetype.css">
<script src="/hibernarm-management/JS/bootstrap.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="/hibernarm-management/JS/bootstrap-switch.min.js"></script>
<script type="text/javascript"
	src="/hibernarm-management/JS/noty/jquery.noty.min.js"></script>

<script type="text/javascript"
	src="/hibernarm-management/JS/noty/layouts/bottomRight.min.js"></script>
<script type="text/javascript"
	src="/hibernarm-management/JS/noty/themes/default.min.js"></script>

</head>
<body>
	<div class="container" id="wholeContainer">
		<div class="row">
			<div class="col-xs-12 col-md-12 col-lg-12">
				<ul class="nav nav-tabs">
					<li class="active"><a
						href="#">Home</a></li>
					<li><a href="/hibernarm-management/searchArchetype?condition=">SearchDis</a></li>
					<li><a href="#">Messages</a></li>
				</ul>
			</div>
		</div>
		<div class="row">
			<div class="col-md-8 col-xs-8 col-xs-offset-2 col-md-offset-2 col-lg-8 col-lg-offset-2"
				>
				<img src="/hibernarm-management/image/be.jpg" class="img-responsive"
					alt="Responsive image">
			</div>
		</div>
		<div class="row">
			<div class="col-md-8 col-xs-8 col-xs-offset-2 col-md-offset-2 col-lg-8 col-lg-offset-2"></div>
		</div>
		<div class="row">
			<div class="col-md-8 col-xs-8 col-xs-offset-2 col-md-offset-2 col-lg-8 col-lg-offset-2"
				id="searchDiv">
				<div class="input-group">
					<input type="text" class="form-control" id="searchConditionInput">
					<span class="input-group-btn">
						<button class="btn btn-primary" type="button" id="searchButton">
							<i class="glyphicon glyphicon-search"></i>
						</button>
					</span>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-8 col-xs-8 col-xs-offset-2 col-md-offset-2 col-lg-8 col-lg-offset-2"
				id="addFileDiv">

				<!-- The fileinput-button span is used to style the file input field as button -->
				<span class="btn btn-success fileinput-button" id="addFiles">
					<i class="glyphicon glyphicon-plus"></i> <span>Add files...</span>
					<input id="inputFiles" type="file" name="files[]" multiple>
				</span>
				<button type="submit" class="btn btn-primary start" id="uploadFlag">
					<i class="glyphicon glyphicon-upload"></i> <span>Start
						upload</span>
				</button>
				<span id="uploadTip"></span>
				<div id="overrideChangedRadio" class="pull-right">
					<label class="label-change-switch"> Override Changed
						<div class="make-switch">
							<input type="checkbox" />
						</div>
					</label>
				</div>

			</div>
		</div>
		<div class="row">
			<div class="col-md-8 col-xs-8 col-xs-offset-2 col-md-offset-2 col-lg-8 col-lg-offset-2">
				<table role="presentation" class="table table-striped">
					<tbody class="files" id="displayFiles">

					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
