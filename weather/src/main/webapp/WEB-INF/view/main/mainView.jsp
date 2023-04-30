<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="shortcut icon" type="image/x-icon" href="/template/images/favicon.ico" >
<link rel="stylesheet" href="/template/styles/style.css">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>메인페이지</title>
</head>
<body>
    <div class="real-time-status">
        <h1>기상 실황</h1>
        <img id="weatherImage" src="/"/>
        <p id="weatherStatus">흐림</p>
        <p id="temperature"></p>
    </div>

    <div class="container-main">
        <div>
            <img src="/template/images/main/squareMainTab.png"/>
            <a href="/short-term">기상청 단기 예보</a>
        </div>
        <div>
            <img src="/template/images/main/squareMainTab.png"/>
            <a href="/mid-term">기상청 중기 예보</a>
        </div>
        <div>
            <img src="/template/images/main/squareMainTab.png"/>
            <a href="javascript:;" onclick="return false;">추가 예정</a>
        </div>
    </div>

    <script type="text/javascript">
        let url = '${url}';

        fetch(url)
          .then(response => response.json())
          .then(data => {

            let item = data.response.body.items.item;
            let info = JSON.stringify(data);
            console.log(item);

            for (let i = 0; i < item.length; i++) {
                if (item[i].category === "T1H") {
                    console.log(item[i].obsrValue);
                    document.getElementById("temperature").innerHTML = item[i].obsrValue+"℃";
                }
                if (item[i].category === "PTY") {
                    if (item[i].obsrValue === "0") {
                        document.getElementById("weatherStatus").innerHTML = "맑음";
                        document.getElementById("weatherImage").src = "/template/images/main/sunny.png";
                    } else if (item[i].obsrValue === "3" || item[i].obsrValue === "7") {
                        document.getElementById("weatherStatus").innerHTML = "눈";
                        document.getElementById("weatherImage").src = "/template/images/main/snowy.png";
                    } else {
                        document.getElementById("weatherStatus").innerHTML = "비";
                        document.getElementById("weatherImage").src = "/template/images/main/rainy.png";
                    }
                }
            }

          })
          .catch((error) => console.log("error:", error));
    </script>
</body>
</html>