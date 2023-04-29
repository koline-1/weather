<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="shortcut icon" type="image/x-icon" href="/template/images/favicon.ico" >
<link rel="stylesheet" href="/template/styles/style.css">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>기상청 중기 예보</title>
</head>
<body>
    <a href="/mid-term/ocean/location">중기해상예보조회</a><br>
    <a href="/mid-term/temperature?time=0600">중기기온조회 (06시 기준)</a><br>
    <c:if test="${time gt 1800}">
        <a href="/mid-term/temperature?time=1800">중기기온조회 (18시 기준)</a><br>
    </c:if>
    <a href="/mid-term/land?time=0600">중기육상예보조회 (06시 기준)</a><br>
    <c:if test="${time gt 1800}">
        <a href="/mid-term/land?time=1800">중기육상예보조회 (18시 기준)</a><br>
    </c:if>
    <a href="/mid-term/expectation?time=0600">중기전망조회 (06시 기준)</a><br>
    <c:if test="${time gt 1800}">
        <a href="/mid-term/expectation?time=1800">중기전망조회 (18시 기준)</a><br>
    </c:if>
    <button onclick="location.href='/'">뒤로</button>
</body>
</html>