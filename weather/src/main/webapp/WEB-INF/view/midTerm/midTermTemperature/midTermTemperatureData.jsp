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
    <c:if test="${not empty midTermTemperatureDto}">
        <ul>
            <li>
                예보구역 :
                <c:choose>
                    <c:when test="${midTermTemperatureDto.regId eq '11B10101'}">
                        서울
                    </c:when>
                    <c:when test="${midTermTemperatureDto.regId eq '11G00401'}">
                        서귀포
                    </c:when>
                    <c:when test="${midTermTemperatureDto.regId eq '11B20201'}">
                        인천
                    </c:when>
                    <c:when test="${midTermTemperatureDto.regId eq '11F20501'}">
                        광주
                    </c:when>
                    <c:when test="${midTermTemperatureDto.regId eq '11B20601'}">
                        수원
                    </c:when>
                    <c:when test="${midTermTemperatureDto.regId eq '21F20801'}">
                        목포
                    </c:when>
                    <c:when test="${midTermTemperatureDto.regId eq '11B20305'}">
                        파주
                    </c:when>
                    <c:when test="${midTermTemperatureDto.regId eq '11F20401'}">
                        여수
                    </c:when>
                    <c:when test="${midTermTemperatureDto.regId eq '11D10301'}">
                        춘천
                    </c:when>
                    <c:when test="${midTermTemperatureDto.regId eq '11F10201'}">
                        전주
                    </c:when>
                    <c:when test="${midTermTemperatureDto.regId eq '11D10401'}">
                        원주
                    </c:when>
                    <c:when test="${midTermTemperatureDto.regId eq '21F10501'}">
                        군산
                    </c:when>
                    <c:when test="${midTermTemperatureDto.regId eq '11D20501'}">
                        강릉
                    </c:when>
                    <c:when test="${midTermTemperatureDto.regId eq '11H20201'}">
                        부산
                    </c:when>
                    <c:when test="${midTermTemperatureDto.regId eq '11C20401'}">
                        대전
                    </c:when>
                    <c:when test="${midTermTemperatureDto.regId eq '11H20101'}">
                        울산
                    </c:when>
                    <c:when test="${midTermTemperatureDto.regId eq '11C20101'}">
                        서산
                    </c:when>
                    <c:when test="${midTermTemperatureDto.regId eq '11H20301'}">
                        창원
                    </c:when>
                    <c:when test="${midTermTemperatureDto.regId eq '11C20404'}">
                        세종
                    </c:when>
                    <c:when test="${midTermTemperatureDto.regId eq '11H10701'}">
                        대구
                    </c:when>
                    <c:when test="${midTermTemperatureDto.regId eq '11C10301'}">
                        청주
                    </c:when>
                    <c:when test="${midTermTemperatureDto.regId eq '11H10501'}">
                        안동
                    </c:when>
                    <c:when test="${midTermTemperatureDto.regId eq '11G00201'}">
                        제주
                    </c:when>
                    <c:when test="${midTermTemperatureDto.regId eq '11H10201'}">
                        포항
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>
            </li>
            <li>
                3일 후 예상최저기온 : ${midTermTemperatureDto.taMin3}℃
            </li>
            <li>
                3일 후 예상최저기온 하한 범위 : ${midTermTemperatureDto.taMin3Low}
            </li>
            <li>
                3일 후 예상최저기온 상한 범위 : ${midTermTemperatureDto.taMin3High}
            </li>
            <li>
                3일 후 예상최고기온 : ${midTermTemperatureDto.taMax3}℃
            </li>
            <li>
                3일 후 예상최고기온 하한 범위 : ${midTermTemperatureDto.taMax3Low}
            </li>
            <li>
                3일 후 예상최고기온 상한 범위 : ${midTermTemperatureDto.taMax3High}
            </li>
            <li>
                4일 후 예상최저기온 : ${midTermTemperatureDto.taMin4}℃
            </li>
            <li>
                4일 후 예상최저기온 하한 범위 : ${midTermTemperatureDto.taMin4Low}
            </li>
            <li>
                4일 후 예상최저기온 상한 범위 : ${midTermTemperatureDto.taMin4High}
            </li>
            <li>
                4일 후 예상최고기온 : ${midTermTemperatureDto.taMax4}℃
            </li>
            <li>
                4일 후 예상최고기온 하한 범위 : ${midTermTemperatureDto.taMax4Low}
            </li>
            <li>
                4일 후 예상최고기온 상한 범위 : ${midTermTemperatureDto.taMax4High}
            </li>
            <li>
                5일 후 예상최저기온 : ${midTermTemperatureDto.taMin5}℃
            </li>
            <li>
                5일 후 예상최저기온 하한 범위 : ${midTermTemperatureDto.taMin5Low}
            </li>
            <li>
                5일 후 예상최저기온 상한 범위 : ${midTermTemperatureDto.taMin5High}
            </li>
            <li>
                5일 후 예상최고기온 : ${midTermTemperatureDto.taMax5}℃
            </li>
            <li>
                5일 후 예상최고기온 하한 범위 : ${midTermTemperatureDto.taMax5Low}
            </li>
            <li>
                5일 후 예상최고기온 상한 범위 : ${midTermTemperatureDto.taMax5High}
            </li>
            <li>
                6일 후 예상최저기온 : ${midTermTemperatureDto.taMin6}℃
            </li>
            <li>
                6일 후 예상최저기온 하한 범위 : ${midTermTemperatureDto.taMin6Low}
            </li>
            <li>
                6일 후 예상최저기온 상한 범위 : ${midTermTemperatureDto.taMin6High}
            </li>
            <li>
                6일 후 예상최고기온 : ${midTermTemperatureDto.taMax6}℃
            </li>
            <li>
                6일 후 예상최고기온 하한 범위 : ${midTermTemperatureDto.taMax6Low}
            </li>
            <li>
                6일 후 예상최고기온 상한 범위 : ${midTermTemperatureDto.taMax6High}
            </li>
            <li>
                7일 후 예상최저기온 : ${midTermTemperatureDto.taMin7}℃
            </li>
            <li>
                7일 후 예상최저기온 하한 범위 : ${midTermTemperatureDto.taMin7Low}
            </li>
            <li>
                7일 후 예상최저기온 상한 범위 : ${midTermTemperatureDto.taMin7High}
            </li>
            <li>
                7일 후 예상최고기온 : ${midTermTemperatureDto.taMax7}℃
            </li>
            <li>
                7일 후 예상최고기온 하한 범위 : ${midTermTemperatureDto.taMax7Low}
            </li>
            <li>
                7일 후 예상최고기온 상한 범위 : ${midTermTemperatureDto.taMax7High}
            </li>
            <li>
                8일 후 예상최저기온 : ${midTermTemperatureDto.taMin8}℃
            </li>
            <li>
                8일 후 예상최저기온 하한 범위 : ${midTermTemperatureDto.taMin8Low}
            </li>
            <li>
                8일 후 예상최저기온 상한 범위 : ${midTermTemperatureDto.taMin8High}
            </li>
            <li>
                8일 후 예상최고기온 : ${midTermTemperatureDto.taMax8}℃
            </li>
            <li>
                8일 후 예상최고기온 하한 범위 : ${midTermTemperatureDto.taMax8Low}
            </li>
            <li>
                8일 후 예상최고기온 상한 범위 : ${midTermTemperatureDto.taMax8High}
            </li>
            <li>
                9일 후 예상최저기온 : ${midTermTemperatureDto.taMin9}℃
            </li>
            <li>
                9일 후 예상최저기온 하한 범위 : ${midTermTemperatureDto.taMin9Low}
            </li>
            <li>
                9일 후 예상최저기온 상한 범위 : ${midTermTemperatureDto.taMin9High}
            </li>
            <li>
                9일 후 예상최고기온 : ${midTermTemperatureDto.taMax9}℃
            </li>
            <li>
                9일 후 예상최고기온 하한 범위 : ${midTermTemperatureDto.taMax9Low}
            </li>
            <li>
                9일 후 예상최고기온 상한 범위 : ${midTermTemperatureDto.taMax9High}
            </li>
            <li>
                10일 후 예상최저기온 : ${midTermTemperatureDto.taMin10}℃
            </li>
            <li>
                10일 후 예상최저기온 하한 범위 : ${midTermTemperatureDto.taMin10Low}
            </li>
            <li>
                10일 후 예상최저기온 상한 범위 : ${midTermTemperatureDto.taMin10High}
            </li>
            <li>
                10일 후 예상최고기온 : ${midTermTemperatureDto.taMax10}℃
            </li>
            <li>
                10일 후 예상최고기온 하한 범위 : ${midTermTemperatureDto.taMax10Low}
            </li>
            <li>
                10일 후 예상최고기온 상한 범위 : ${midTermTemperatureDto.taMax10High}
            </li>
        </ul>
    </c:if>
    <button onclick="location.href='/mid-term/temperature/location'">뒤로</button>
    <button onclick="saveData();">데이터 저장</button>

    <script>
        function saveData() {
            const jsonData = '${midTermTemperatureDtoJson}';
            if (jsonData) {
                fetch("/mid-term/temperature/data", {
                  method: "POST",
                  headers: {
                    "Content-Type": "application/json; charset=utf-8"
                  },
                  body: JSON.stringify({
                    data : jsonData
                  }),
                }).then((response) => response.text())
                .then((response) => {
                    if (response === "saved") {
                        alert("데이터를 성공적으로 저장하였습니다.");
                    } else if (response === "exists") {
                        alert("이미 저장한 데이터 입니다.");
                    } else {
                        alert("알 수 없는 오류가 발생했습니다.");
                    }
                })
                .catch((error) => console.log("error:", error));
            } else {
                alert("데이터를 확인하여 주세요.");
            }
        }
    </script>
</body>
</html>