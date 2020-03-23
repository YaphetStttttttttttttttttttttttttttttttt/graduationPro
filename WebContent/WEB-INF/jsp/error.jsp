<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>哎呦，系统失联了...LLLLLLL</h3>
 
      <%
          Exception ex = (Exception) request.getAttribute("exception");
      %>
      <H1>
          Exception:
          <%=ex.toString()%>
     </H1>
</body>
</html>