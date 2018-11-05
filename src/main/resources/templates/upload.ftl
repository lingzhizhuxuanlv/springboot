<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>文件上传</title>
</head>
<body>
<h2>文件上传</h2>
<form action="${base}/upload" method="post" enctype="multipart/form-data">
    <p>选择文件:<input type="file" name="files"></p>
    <p><input type="submit" value="提交"></p>
</form>
</body>
</html>
