<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>echarts</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-2.2.1.min.js?ver=${Math.random()}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/echarts.js?ver=${Math.random()}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/echartsUtil.js?ver=${Math.random()}"></script>
</head>
<body>
	<div id="container" style="height: 500px"></div>
</body>
<script type="text/javascript">
	var legendCount = 10;
	var xItemCount = 12;
	var legendItem = new Array();
	var xItem = new Array();
	for(var j=0;j<xItemCount;j++){
		xItem.push(j);
	}
	
	var data = new Array();
	for(var i=0;i<legendCount;i++){
		legendItem.push('购物'+i);
		var datas = new Array();
		for(var j=0;j<xItemCount;j++){
			datas.push(Math.random()*10000);
		}
		var dataMap = {
			name : '购物'+i,
			data : datas,
			type : parseInt(Math.random()*100%2)==1 ? 'line':'bar'
		};
		data.push(dataMap);
	}
	$("#container").echart({
		title:{
			text:"我不是标题"
		},
		legend:{
			data:legendItem,
			orient:"vertical"
		},
		x:{
			name:"x",
			data:xItem,
		},
		y:{
			name:"y",
		},
		data:data
	});
</script>
</html>