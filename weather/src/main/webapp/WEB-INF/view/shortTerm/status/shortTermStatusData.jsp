<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="shortcut icon" type="image/x-icon" href="/template/images/favicon.ico" >
<link rel="stylesheet" href="/template/styles/style.css">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="/template/js/jquery-3.6.4.js"></script>
    <title>기상청 초단기 실황 조회</title>
</head>
<body>
    <c:if test="${not empty shortTermStatusDto}">
        <ul>
            <li>
                조회기준일 : ${shortTermStatusDto.baseDate}
            </li>
            <li>
                조회기준시간 : ${shortTermStatusDto.baseTime}
            </li>
            <!-- x값, y값 은 위치로 바꿔야함 -->
            <li>
                x값 : ${shortTermStatusDto.nxValue}
            </li>
            <li>
                y값 : ${shortTermStatusDto.nyValue}
            </li>
            <li>
                기온 : ${shortTermStatusDto.temperature}
            </li>
            <li>
                1시간 강수량 : ${shortTermStatusDto.hourPrecipitation}
            </li>
            <li>
                풍속(동서성분) : ${shortTermStatusDto.horizontalWind}
            </li>
            <li>
                풍속(남북성분) : ${shortTermStatusDto.verticalWind}
            </li>
            <li>
                습도 : ${shortTermStatusDto.humidity}
            </li>
            <li>
                강수형태 : ${shortTermStatusDto.rainType}
            </li>
            <li>
                풍향 : ${shortTermStatusDto.windDirection}
            </li>
            <li>
                풍속 : ${shortTermStatusDto.windSpeed}
            </li>
            <li>
                버전 : ${shortTermStatusDto.version}
            </li>
        </ul>
    </c:if>

    <button onclick="save();">데이터 저장</button>
    <button onclick="location.href='/short-term/location'">뒤로</button>

    <script type="text/javascript">
        function save() {
            const jsonData = '${shortTermStatusDtoJson}';
            if (jsonData) {
                fetch('/short-term/status/data', {
                  method: "POST",
                  headers: {
                    "Content-Type": "application/json; charset=utf-8"
                  },
                  body: JSON.stringify({
                    data : jsonData
                  }),
                }).then((response) => response.json())
                .then((response) => {
                    if (response.baseDate !== "") {
                        alert("데이터를 성공적으로 저장하였습니다.");
                    } else if (response.baseDate === "") {
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