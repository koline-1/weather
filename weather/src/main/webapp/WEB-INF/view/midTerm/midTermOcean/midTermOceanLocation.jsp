<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="shortcut icon" type="image/x-icon" href="/template/images/favicon.ico" >
<link rel="stylesheet" href="/template/styles/style.css">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>기상청 중기 해상 예보</title>
</head>
<body>
    <div class="mid-term-location-div">
        <div class="mid-term-location-div2">
            <a href="/mid-term/ocean/data?location=12A20000" class="mid-term-location-a">서해중부</a>
            <a href="/mid-term/ocean/data?location=12A30000" class="mid-term-location-a">서해남부</a>
        </div>
        <div class="mid-term-location-div2">
            <a href="/mid-term/ocean/data?location=12B10000" class="mid-term-location-a">남해서부</a>
            <a href="/mid-term/ocean/data?location=12B20000" class="mid-term-location-a">남해동부</a>
        </div>
        <div class="mid-term-location-div2">
            <a href="/mid-term/ocean/data?location=12C10000" class="mid-term-location-a">동해남부</a>
            <a href="/mid-term/ocean/data?location=12C20000" class="mid-term-location-a">동해중부</a>
        </div>
        <div class="mid-term-location-div2">
            <a href="/mid-term/ocean/data?location=12C30000" class="mid-term-location-a">동해북부</a>
            <a href="/mid-term/ocean/data?location=12A10000" class="mid-term-location-a">서해북부</a>
        </div>
        <div class="mid-term-location-div2">
            <a href="/mid-term/ocean/data?location=12B10500" class="mid-term-location-a">제주도</a>
            <a href="/mid-term/ocean/data?location=12D00000" class="mid-term-location-a">대화퇴</a>
        </div>
        <div class="mid-term-location-div2">
            <a href="/mid-term/ocean/data?location=12E00000" class="mid-term-location-a">동중국해</a>
            <a href="/mid-term/ocean/data?location=12F00000" class="mid-term-location-a">규슈</a>
        </div>
        <div class="mid-term-location-div2">
            <a href="/mid-term/ocean/data?location=12G00000" class="mid-term-location-a">연해주</a>
        </div>
    </div>
    <button onclick="location.href='/mid-term'">뒤로</button>
</body>
</html>