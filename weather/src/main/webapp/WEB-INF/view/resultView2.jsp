<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
    <title>기상청 단기 예보</title>
</head>
<body>
    <div id="showList">
    </div>
    <a href="/short-term">목록</a>

    <script type="text/javascript">
        let url = '${url}';
        let id = '${id}';

        url = url.replaceAll("base_date=20230501", "base_date=20230501");
        url = url.replaceAll("base_time=0500", "base_time=1400");
        console.log(url);

        fetch(url)
          .then(response => response.json())
          .then(data => {
            let html = data.response.body.items.item;
            $('#showList').append(JSON.stringify(html));

            fetch("/short-term/data", {
              method: "POST",
              headers: {
                "Content-Type": "application/json; charset=utf-8"
              },
              body: JSON.stringify({
                id : id,
                data : JSON.stringify(data)
              }),
            }).then((response) => console.log(response));
          })
          .catch((error) => console.log("error:", error));

    </script>
</body>
</html>