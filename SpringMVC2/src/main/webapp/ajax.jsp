<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/5/19
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script language="JavaScript">
        var xmlHttpRequest=null;
        function send() {
            var url="http://localhost:8080/ajax.action?a=10&b=80";
            xmlHttpRequest = new XMLHttpRequest();
            xmlHttpRequest.onreadystatechange= state_Change;
            xmlHttpRequest.open("GET".url,true);
            xmlHttpRequest.send(null);
        }

        function state_Change() {
           if( xmlHttpRequest.readyState == 4){
               var str = xmlHttpRequest.responseText;
            document.getElementById("aa").innerHTML =str;

           }

        }
    </script>
</head>
<body>
<button onclick="send()">发送</button>
<div id="aa"></div>
</body>
</html>
