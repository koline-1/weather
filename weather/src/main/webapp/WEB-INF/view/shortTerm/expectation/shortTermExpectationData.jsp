<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="shortcut icon" type="image/x-icon" href="/template/images/favicon.ico" >
<link rel="stylesheet" href="/template/styles/style.css">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="/template/js/jquery-3.6.4.js"></script>
    <title>기상청 단기 예보 조회</title>
</head>
<body>
    <c:forEach var="item" items="${shortTermExpectationDtoList}">
        <div>
            <ul>
                <li>
                    조회기준일 : ${item.baseDate}
                </li>
                <li>
                    조회기준시간 : ${item.baseTime}
                </li>
                <li>
                    예보기준일 : ${item.forecastDate}
                </li>
                <li>
                    예보기준시간 : ${item.forecastTime}
                </li>
                <!-- x값, y값 은 위치로 바꿔야함 -->
                <li>
                    x값 : ${item.nxValue}
                </li>
                <li>
                    y값 : ${item.nyValue}
                </li>
                <li>
                    1시간 기온 : ${item.hourTemperature}
                </li>
                <li>
                    풍속(동서성분) : ${item.horizontalWind}
                </li>
                <li>
                    풍속(남북성분) : ${item.verticalWind}
                </li>
                <li>
                    풍향 : ${item.windDirection}
                </li>
                <li>
                    풍속 : ${item.windSpeed}
                </li>
                <li>
                    하늘상태 : ${item.skyStatus}
                </li>
                <li>
                    강수확률 : ${item.rainPossibility}
                </li>
                <li>
                    강수형태 : ${item.rainType}
                </li>
                <li>
                    파고 : ${item.waveHeight}
                </li>
                <li>
                    1시간 강수량 : ${item.hourPrecipitation}
                </li>
                <li>
                    1시간 신적설 : ${item.snowDepth}
                </li>
                <li>
                    습도 : ${item.humidity}
                </li>
                <c:if test="${not empty item.minimumTemperature}">
                    <li>
                        일 최저기온 : ${item.minimumTemperature}
                    </li>
                </c:if>
                <c:if test="${not empty item.maximumTemperature}">
                    <li>
                        일 최고기온 : ${item.maximumTemperature}
                    </li>
                </c:if>
                <li>
                    버전 : ${item.version}
                </li>
            </ul>
        </div>
    </c:forEach>

    <button onclick="save();">데이터 저장</button>
    <button onclick="location.href='/short-term/location'">뒤로</button>

    <script type="text/javascript">
        function save() {
            const jsonData = '${shortTermExpectationDtoListJson}';
            if (jsonData) {
                fetch('/short-term/expectation/data', {
                  method: "POST",
                  headers: {
                    "Content-Type": "application/json; charset=utf-8"
                  },
                  body: JSON.stringify({
                    data : jsonData
                  }),
                }).then((response) => response.json())
                .then((response) => {
                    if (response.count == 0) {
                        alert("이미 저장한 데이터 입니다.");
                    } else if (response.count > 0) {
                        alert(response.count+"개의 데이터를 성공적으로 저장하였습니다.");
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