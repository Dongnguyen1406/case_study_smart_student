<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:forward page="/WEB-INF/view/user/student.jsp" />
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
  
</head>

<body>
<h1><%= "Hello World!" %></h1>
<br/>
<c:redirect url="/login"></c:redirect>
</body>
</html>