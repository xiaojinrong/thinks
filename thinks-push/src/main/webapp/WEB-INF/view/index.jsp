<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Java后端WebSocket的Tomcat实现</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/jquery-2.2.1.min.js?ver=${Math.random()}"></script>
<script type="text/javascript">
	$(function() {
		$("input[name='formSubmit']").click(function() {
			var form = new FormData(document.getElementById("form"));
			$.ajax({
				url : "${pageContent.request.contextPath }/progress/upload",
				data : form,
				processData : false,
				contentType : false,
				type : "post",
				success : function(data) {
					alert(data);
				}
			})
		});
	});
</script>
<style type="text/css">
.progress {
	width: 100%;
	height: 15px;
	position: fixed;
	left: 0;
	right: 0;
	bottom: 0;
}

.progressNumbers {
	width: 100px;
	height: 15px;
	line-height: 15px; position : fixed;
	text-align: center;
	left: 50%;
	right: 50%;
	bottom: 0;
	z-index: 999;
	position: fixed;
}
</style>
</head>
<body>
	<form id="form">
		<input type="file" value="上传图片" name="file" multiple="multiple" /> <input
			type="file" value="上传视频" name="files" multiple="multiple" /> <input
			type="button" name="formSubmit" value="上传" />
	</form>
	Welcome
	<br />
	<input id="text" type="text" />
	<button onclick="send()">发送消息</button>
	<hr />
	<button onclick="closeWebSocket()">关闭WebSocket连接</button>
	<hr />
	<div id="message"></div>
	<div id="progressNumber" class="progressNumbers">0</div>
	<progress id="progress" value="0" max="100" class="progress"></progress>
</body>

<script type="text/javascript">
	var websocket = null;
	//判断当前浏览器是否支持WebSocket
	if ('WebSocket' in window) {
		websocket = new WebSocket("ws://127.0.0.1:8888/websocket");
	} else {
		alert('当前浏览器 Not support websocket')
	}

	//连接发生错误的回调方法
	websocket.onerror = function() {
		setMessageInnerHTML("WebSocket连接发生错误");
	};

	//连接成功建立的回调方法
	websocket.onopen = function() {
		setMessageInnerHTML("WebSocket连接成功");
	}

	//接收到消息的回调方法
	websocket.onmessage = function(event) {
		var message = event.data;
		var json = $.parseJSON(message);
		$("#progressNumber").html(json.percentage);
		$("#progress").val(json.percentage);
		setMessageInnerHTML(message);
	}

	//连接关闭的回调方法
	websocket.onclose = function() {
		setMessageInnerHTML("WebSocket连接关闭");
	}

	//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
	window.onbeforeunload = function() {
		closeWebSocket();
	}

	//将消息显示在网页上
	function setMessageInnerHTML(innerHTML) {
		document.getElementById('message').innerHTML = innerHTML + '<br/>';
	}

	//关闭WebSocket连接
	function closeWebSocket() {
		websocket.close();
	}

	//发送消息
	function send() {
		var message = document.getElementById('text').value;
		websocket.send(message);
	}
</script>
</html>