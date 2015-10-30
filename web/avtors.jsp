<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 30.10.2015
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Авторы</title>
</head>
<body>
 <h1>Авторы</h1>
 <ul>
     <c:forEach var="avtor" items="${avtors}">
         <li>
             <c:out value="${avtor.name}"/>
         </li>
     </c:forEach>
 </ul>
</body>
</html>
