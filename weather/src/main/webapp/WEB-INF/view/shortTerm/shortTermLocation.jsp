<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="shortcut icon" type="image/x-icon" href="/template/images/favicon.ico" >
<link rel="stylesheet" href="/template/styles/style.css">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="/template/js/jquery-3.6.4.js"></script>
    <title>기상청 단기 예보 위치 설정</title>
</head>
<body>
    <div style="color:white;">
        <h1>위치 설정</h1>
        <ul>
            <li class="location_li" nxValue="61" nyValue="120">
                수원시 ㅇㅇ구 ㅇㅇ동
            </li>
            <li class="location_li" nxValue="55" nyValue="127">
                부산시 ㅇㅇ구 ㅇㅇ동
            </li>
        </ul>
    </div>
    <button onclick="shortTermData();">조회</button>
    <button onclick="location.href='/short-term'">뒤로</button>

    <script type="text/javascript">
        let nxValue = "";
        let nyValue = "";
        $(".location_li").on("click", function(event) {
            if (event.target.style.backgroundColor === "") {
                $(".location_li").css({backgroundColor:""});
                event.target.style.backgroundColor = "#888888";
                nxValue = event.target.getAttribute("nxValue");
                nyValue = event.target.getAttribute("nyValue");
            } else {
                event.target.style.backgroundColor = "";
                nxValue = "";
                nyValue = "";
            }
        });

        const url = new URL(window.location.href);
        const serviceId = url.searchParams.get("serviceId");

        console.log("serviceId = ",serviceId);

        const shortTermData = () => {
            if (nxValue === "" || nyValue === "") {
                alert("위치를 설정해 주세요.");
                return false;
            }

            if (serviceId === "") {
                alert("조회할 서비스를 신청해 주세요.");
                return false;
            }
            location.href = "/short-term/"+serviceId+"/data?nxValue="+nxValue+"&nyValue="+nyValue;
        };
    </script>
</body>
</html>