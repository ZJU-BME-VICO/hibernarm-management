<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<style type="text/css">
textarea {
	width: 100%;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	ARCHETYPE
	<p>
		<s:property value="archetypeBean.name" />
	</p>
	<textarea rows="50" readonly="readonly">
  <s:property value="archetypeBean.content"></s:property>
</textarea>
<s:if test="archetypeBean!=null">
	<a
		href="/hibernarm-management/download.action?keyName=<s:property value="archetypeBean.name"/>&fileType=adl">download</a>
</s:if>
	<br />
	<br /> ARMBean
	<p>
		<s:property value="armBean.name" />
	</p>
	<textarea rows="50" readonly="readonly">
  <s:property value="armBean.content"></s:property>
</textarea>
<s:if test="armBean!=null">
	<a
		href="/hibernarm-management/download.action?keyName=<s:property value="armBean.name"/>&fileType=arm">download</a>
</s:if>
</body>
</html>