<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
</head>
<body>
	${msg }
	<form action="${pageContent.request.contextPath }/progress/upload"
		method="post" enctype="multipart/form-data">
		<input type="file" value="上传图片" name="file" /> <input type="submit"
			value="上传" />
	</form>
	<a href="${pageContent.request.contextPath }/progress/down">下载</a>
</body>
</html>