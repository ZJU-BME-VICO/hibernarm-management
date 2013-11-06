<%@ page pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>CDRSearch</title>
</head>
<body>
   <form method="post" action="/hibernarm-management/home/archetypeNameSearch.action">
     <input type="text" name="condition"></input>
     <input type="submit" value="search" >    
   </form>
</body>
</html>