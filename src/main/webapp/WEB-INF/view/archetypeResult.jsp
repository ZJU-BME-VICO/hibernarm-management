<%@ page language="java"  pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"> 
<title>Insert title here</title>
</head>
<body>
  <s:iterator value="archetypeBeanList" id="singleBean">
     <a href="/hibernarm-management/home/displayAction.action?keyName=<s:property value="name"/>"> <s:property value="name"/> </a> <br/>
  </s:iterator>
</body>

</html>