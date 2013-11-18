<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet type="text/css" href="CSS/displayContent.css">
<script src="JS/jquery-2.0.3.js"></script>
<script type="text/javascript" src="JS/displayContent.js"></script>
</head>
<body>
	ARCHETYPE
	<br />
	<s:if test="archetypeBean!=null">
		<a
			href="/hibernarm-management/fileDownload.action?keyName=<s:property value="archetypeBean.name"/>&fileType=adl">
			<s:property	value="archetypeBean.name" />
		</a>
	</s:if>
	<br />
	<div id="archetypeDiv">
		<s:property value="archetypeBean.content"></s:property>
	</div>
	<br /> 
	ARM
	<br /> 
	<s:if test="armBean!=null">
		<a
			href="/hibernarm-management/fileDownload.action?keyName=<s:property value="armBean.name"/>&fileType=arm">
			<s:property value="armBean.name" />
		</a>
	</s:if>
	<br />
	<div id="armDiv">
		<s:property value="armBean.content"></s:property>
	</div>
</body>
</html>