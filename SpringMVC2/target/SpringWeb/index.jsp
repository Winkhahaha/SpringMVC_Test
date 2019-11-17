
<%@  page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World!</h2><br>
<form action="/upload.action" method="post" enctype="multipart/form-data">
<table border="0" cellspacing="1" bgcolor="#7fffd4">
    <caption>文件上传</caption>
    <tr>
        <td>选择文件:</td>
        <td><input type="file" name="afile"/></td>
        <td><input type="submit" value="提交">
    </tr>

</table>
</form>
</body>
</html>
