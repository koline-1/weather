<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="shortcut icon" type="image/x-icon" href="/template/images/favicon.ico" >
<link rel="stylesheet" href="/template/styles/style.css">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>기상청 중기 기상전망</title>
</head>
<body>
    <c:if test="${not empty midTermExpectationDto}">
        <ul>
            <li>
                예보구역 :
                <c:choose>
                    <c:when test="${midTermExpectationDto.stnId eq '105'}">
                        강원도
                    </c:when>
                    <c:when test="${midTermExpectationDto.stnId eq '108'}">
                        전국
                    </c:when>
                    <c:when test="${midTermExpectationDto.stnId eq '109'}">
                        서울, 인천, 경기도
                    </c:when>
                    <c:when test="${midTermExpectationDto.stnId eq '131'}">
                        충청북도
                    </c:when>
                    <c:when test="${midTermExpectationDto.stnId eq '133'}">
                        대전, 세종, 충청남도
                    </c:when>
                    <c:when test="${midTermExpectationDto.stnId eq '146'}">
                        전라북도
                    </c:when>
                    <c:when test="${midTermExpectationDto.stnId eq '156'}">
                        광주, 전라남도
                    </c:when>
                    <c:when test="${midTermExpectationDto.stnId eq '143'}">
                        대구, 경상북도
                    </c:when>
                    <c:when test="${midTermExpectationDto.stnId eq '159'}">
                        부산, 울산, 경상남도
                    </c:when>
                    <c:when test="${midTermExpectationDto.stnId eq '184'}">
                        제주도
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>
            </li>
            <li>
                기상전망 : ${midTermExpectationDto.wfSv}
            </li>
        </ul>
    </c:if>
    <button onclick="location.href='/mid-term/expectation/location'">뒤로</button>
    <button onclick="saveData();">데이터 저장</button>

    <script>
        function saveData() {
            const jsonData = '${midTermExpectationDtoJson}'.replaceAll("\n", "\\n");
            if (jsonData) {
                fetch("/mid-term/expectation/data", {
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