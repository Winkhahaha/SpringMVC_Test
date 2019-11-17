
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/js/jquery-3.4.1.min.js"></script>
    <script language="JavaScript">
        function json() {
            // $.getJSON(
            //     "http://localhost:8080/json.action?id=6667&name=you",
            //     function (data) {
            //         alert(data);
            //     }
            //
            // );

            $.ajax({
                type:"post",
                url:"http://localhost:8080/json.action",
                contentType:"application/json;charset=utf-8",
                data: "{ \"id\": \"2020\", \"name\" : \"zhangsan\" }",
                success:function (data) {
                    alert(data);
                    //alert(data.name);
                }
            });

        }
    </script>
</head>
<body>
<button onclick="json()">json</button>
</body>
</html>
