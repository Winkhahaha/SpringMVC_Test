<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/10/11
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/upload.action" enctype="multipart/form-data" method="post">
    照片<input type="file" name="photo"/>
    <input type="submit" value="确定">
    <input type="reset" value="取消">
</form>
</body>
</html>
