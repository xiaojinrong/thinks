<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>echarts</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-2.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/echarts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/echartsUtil.js"></script>
</head>
<body>
	<div id="container" style="height: 500px"></div>
</body>
<script type="text/javascript">
	$("#container").echart();
</script>
</html>