<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="/hibernarm-management/CSS/bootstrap.min.css">
<link rel="stylesheet"
	href="/hibernarm-management/CSS/searchArchetypeBootStrapVersion.css">
<link rel="stylesheet"
	href="/hibernarm-management/CSS/bootstrap-theme.min.css">
<link rel="stylesheet" href="/hibernarm-management/CSS/prettify.css">
<script src="/hibernarm-management/JS/jquery-2.0.3.min.js"></script>
<script src="/hibernarm-management/JS/jquery.hotkeys.min.js"></script>
<script src="/hibernarm-management/JS/bootstrap.min.js"></script>
<script src="/hibernarm-management/JS/bootstrap-wysiwyg.min.js"></script>
</head>
<body>
	<div class="container" id="wholeContainer">
	    <div class="row">
			<div class="col-lg-12">
				<ul class="nav nav-tabs">
					<li><a
						href="/hibernarm-management/homeArchetype">Home</a></li>
					<li><a href="/hibernarm-management/searchArchetype?condition=">SearchDis</a></li>
					<li class="active">Messages</li>
				</ul>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				ARCHETYPE <br />
				<s:if test="archetypeBean!=null">
					<a
						href="/hibernarm-management/fileDownload.action?keyName=<s:property value="archetypeBean.name"/>&fileType=adl">
						<s:property value="archetypeBean.name" />
					</a>
				</s:if>
				<br />
				
				<textarea id="archetypeDiv" cols="160" rows="50">
					<s:property value="archetypeBean.content"></s:property>
				</textarea>
				<br />
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				ARM <br />
				<s:if test="armBean!=null">
					<a
						href="/hibernarm-management/fileDownload.action?keyName=<s:property value="armBean.name"/>&fileType=arm">
						<s:property value="armBean.name" />
					</a>
				</s:if>
				<br />
				<textarea id="armDiv" cols="160" rows="50">
					<s:property value="armBean.content"></s:property>
				</textarea>
			</div>
		</div>
	</div>
</body>
</html>