<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>기상청 중기 해상 예보</title>
</head>
<body>
    <c:if test="${not empty midTermOceanDto}">
        <ul>
            <li>
                예보구역 :
                <c:choose>
                    <c:when test="${midTermOceanDto.regId eq '12A20000'}">
                        서해중부
                    </c:when>
                    <c:when test="${midTermOceanDto.regId eq '12A30000'}">
                        서해남부
                    </c:when>
                    <c:when test="${midTermOceanDto.regId eq '12B10000'}">
                        남해서부
                    </c:when>
                    <c:when test="${midTermOceanDto.regId eq '12B20000'}">
                        남해동부
                    </c:when>
                    <c:when test="${midTermOceanDto.regId eq '12C10000'}">
                        동해남부
                    </c:when>
                    <c:when test="${midTermOceanDto.regId eq '12C20000'}">
                        동해중부
                    </c:when>
                    <c:when test="${midTermOceanDto.regId eq '12C30000'}">
                        동해북부
                    </c:when>
                    <c:when test="${midTermOceanDto.regId eq '12A10000'}">
                        서해북부
                    </c:when>
                    <c:when test="${midTermOceanDto.regId eq '12B10500'}">
                        제주도
                    </c:when>
                    <c:when test="${midTermOceanDto.regId eq '12D00000'}">
                        대화퇴
                    </c:when>
                    <c:when test="${midTermOceanDto.regId eq '12E00000'}">
                        동중국해
                    </c:when>
                    <c:when test="${midTermOceanDto.regId eq '12F00000'}">
                        규슈
                    </c:when>
                    <c:when test="${midTermOceanDto.regId eq '12G00000'}">
                        연해주
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>
            </li>
            <li>
                3일후 오전날씨 : ${midTermOceanDto.wf3Am}
            </li>
            <li>
                3일후 오후날씨 : ${midTermOceanDto.wf3Pm}
            </li>
            <li>
                4일후 오전날씨 : ${midTermOceanDto.wf4Am}
            </li>
            <li>
                4일후 오후날씨 : ${midTermOceanDto.wf4Pm}
            </li>
            <li>
                5일후 오전날씨 : ${midTermOceanDto.wf5Am}
            </li>
            <li>
                5일후 오후날씨 : ${midTermOceanDto.wf5Pm}
            </li>
            <li>
                6일후 오전날씨 : ${midTermOceanDto.wf6Am}
            </li>
            <li>
                6일후 오후날씨 : ${midTermOceanDto.wf6Pm}
            </li>
            <li>
                7일후 오전날씨 : ${midTermOceanDto.wf7Am}
            </li>
            <li>
                7일후 오후날씨 : ${midTermOceanDto.wf7Pm}
            </li>
            <li>
                8일후 날씨예보 : ${midTermOceanDto.wf8}
            </li>
            <li>
                9일후 날씨예보 : ${midTermOceanDto.wf9}
            </li>
            <li>
                10일후 날씨예보 : ${midTermOceanDto.wf10}
            </li>
        </ul>
    </c:if>
    <a href="/mid-term">목록</a>
</body>
</html>