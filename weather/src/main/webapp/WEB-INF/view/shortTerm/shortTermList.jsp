<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="shortcut icon" type="image/x-icon" href="/template/images/favicon.ico" >
<link rel="stylesheet" href="/template/styles/style.css">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>기상청 단기 예보</title>
</head>
<body>
    <a href="/short-term/location?serviceId=expectation">단기 예보 조회</a><br>
    <a href="/short-term/location?serviceId=extraExpectation">초단기 예보 조회</a><br>
    <a href="/short-term/location?serviceId=status">초단기 실황 조회</a><br>
    <button onclick="location.href='/'">뒤로</button>
</body>
</html>