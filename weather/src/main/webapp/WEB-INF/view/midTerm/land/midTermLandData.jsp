<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="shortcut icon" type="image/x-icon" href="/template/images/favicon.ico" >
<link rel="stylesheet" href="/template/styles/style.css">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>기상청 중기 육상 예보</title>
</head>
<body>
    <c:if test="${not empty midTermLandDto}">
        <ul>
            <li>
                예보구역 :
                <c:choose>
                    <c:when test="${midTermLandDto.regId eq '11B00000'}">
                        서울, 인천, 경기도
                    </c:when>
                    <c:when test="${midTermLandDto.regId eq '11D10000'}">
                        강원도영서
                    </c:when>
                    <c:when test="${midTermLandDto.regId eq '11D20000'}">
                        강원도영동
                    </c:when>
                    <c:when test="${midTermLandDto.regId eq '11C20000'}">
                        대전, 세종, 충청남도
                    </c:when>
                    <c:when test="${midTermLandDto.regId eq '11C10000'}">
                        충청북도
                    </c:when>
                    <c:when test="${midTermLandDto.regId eq '11F20000'}">
                        광주, 전라남도
                    </c:when>
                    <c:when test="${midTermLandDto.regId eq '11F10000'}">
                        전라북도
                    </c:when>
                    <c:when test="${midTermLandDto.regId eq '11H10000'}">
                        대구, 경상북도
                    </c:when>
                    <c:when test="${midTermLandDto.regId eq '11H20000'}">
                        부산, 울산, 경상남도
                    </c:when>
                    <c:when test="${midTermLandDto.regId eq '11G00000'}">
                        제주도
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>
            </li>
            <li>
                3일 후 오전 강수 확률 : ${midTermLandDto.rnSt3Am}%
            </li>
            <li>
                3일 후 오후 강수 확률 : ${midTermLandDto.rnSt3Pm}%
            </li>
            <li>
                4일 후 오전 강수 확률 : ${midTermLandDto.rnSt4Am}%
            </li>
            <li>
                4일 후 오후 강수 확률 : ${midTermLandDto.rnSt4Pm}%
            </li>
            <li>
                5일 후 오전 강수 확률 : ${midTermLandDto.rnSt5Am}%
            </li>
            <li>
                5일 후 오후 강수 확률 : ${midTermLandDto.rnSt5Pm}%
            </li>
            <li>
                6일 후 오전 강수 확률 : ${midTermLandDto.rnSt6Am}%
            </li>
            <li>
                6일 후 오후 강수 확률 : ${midTermLandDto.rnSt6Pm}%
            </li>
            <li>
                7일 후 오전 강수 확률 : ${midTermLandDto.rnSt7Am}%
            </li>
            <li>
                7일 후 오후 강수 확률 : ${midTermLandDto.rnSt7Pm}%
            </li>
            <li>
                8일 후 강수 확률 : ${midTermLandDto.rnSt8}%
            </li>
            <li>
                9일 후 강수 확률 : ${midTermLandDto.rnSt9}%
            </li>
            <li>
                10일 후 강수 확률 : ${midTermLandDto.rnSt10}%
            </li>
            <li>
                3일 후 오전 날씨예보 : ${midTermLandDto.wf3Am}
            </li>
            <li>
                3일 후 오후 날씨예보 : ${midTermLandDto.wf3Pm}
            </li>
            <li>
                4일 후 오전 날씨예보 : ${midTermLandDto.wf4Am}
            </li>
            <li>
                4일 후 오후 날씨예보 : ${midTermLandDto.wf4Pm}
            </li>
            <li>
                5일 후 오전 날씨예보 : ${midTermLandDto.wf5Am}
            </li>
            <li>
                5일 후 오후 날씨예보 : ${midTermLandDto.wf5Pm}
            </li>
            <li>
                6일 후 오전 날씨예보 : ${midTermLandDto.wf6Am}
            </li>
            <li>
                6일 후 오후 날씨예보 : ${midTermLandDto.wf6Pm}
            </li>
            <li>
                7일 후 오전 날씨예보 : ${midTermLandDto.wf7Am}
            </li>
            <li>
                7일 후 오후 날씨예보 : ${midTermLandDto.wf7Pm}
            </li>
            <li>
                8일 후 날씨예보 : ${midTermLandDto.wf8}
            </li>
            <li>
                9일 후 날씨예보 : ${midTermLandDto.wf9}
            </li>
            <li>
                10일 후 날씨예보 : ${midTermLandDto.wf10}
            </li>
        </ul>
    </c:if>
    <button onclick="location.href='/mid-term/land/location'">뒤로</button>
    <button onclick="saveData();">데이터 저장</button>

    <script>
        function saveData() {
            const jsonData = '${midTermLandDtoJson}';
            if (jsonData) {
                fetch("/mid-term/land/data", {
                  method: "POST",
                  headers: {
                    "Content-Type": "application/json; charset=utf-8"
                  },
                  body: JSON.stringify({
                    data : jsonData
                  }),
                }).then((response) => response.json())
                .then((response) => {
                    if (response.regId !== "") {
                        alert("데이터를 성공적으로 저장하였습니다.");
                    } else if (response.regId === "") {
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