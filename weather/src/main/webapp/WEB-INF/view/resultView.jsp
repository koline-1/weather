<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>기상청 중기 예보</title>
</head>
<body>
    <c:if test="${not empty resultList}">
        <c:forEach var="item" items="${resultList}">
            <c:forEach var="map" items="${item}">
                ${map.key} : ${map.value}<br>
            </c:forEach>
        </c:forEach>
    </c:if>
    <a href="/mid-term">목록</a>
</body>
</html>