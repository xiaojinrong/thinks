(function($){
	$.fn.echart = function(){
		var myChart = echarts.init(this.get(0));
		var option = {
				title : {
					show : true, //是否显示标题组件
					text : "你好", //主标题文本
					link : "", //主标题文本超链接
					target : "blank", //指定窗口打开主标题超链接[self|blank]
					textStyle:{
						color:"red",//主标题文字的颜色
						fontStyle:"normal", //主标题文字字体的风格[normal|italic|oblique]
						fontWeight:"normal",//主标题文字字体的粗细[normal|bold|bolder|lighter]
						fontFamily:"sans-serif",//主标题文字的字体系列[Microsoft YaHei]
						fontSize:"18",//主标题文字的字体大小
						align:"center",//文字水平对齐方式，默认自动[left|center|right]
						verticalAlign:"middle",//文字垂直对齐方式，默认自动[top|middle|bottom]
						lineHeight:"",//行高
						width:"",//文字块的宽度。一般不用指定，不指定则自动是文字的宽度
						height:"",//文字块的高度。一般不用指定，不指定则自动是文字的高度
						textBorderColor:"transparent",//文字本身的描边颜色
						textBorderWidth:0,//文字本身的描边宽度
						textShadowColor:"transparent"//文字本身的阴影颜色
					},
					subtext:"",//副标题文本，支持使用 \n 换行
					sublink:"",//副标题文本超链接
					subtarget:"",//指定窗口打开副标题超链接[self|blank]
					triggerEvent:false,//是否触发事件
					padding:5,//标题内边距，单位px，默认各方向内边距为5，接受数组分别设定上右下左边距
					itemGap:10,//主副标题之间的间距
					zlevel:0,//所有图形的 zlevel 值
					z:2,//组件的所有图形的z值。控制图形的前后顺序。z值小的图形会被z值大的图形覆盖。
					left:"auto",//grid 组件离容器左侧的距离。
					top:"auto",//grid 组件离容器上侧的距离。
					right:"auto",//grid 组件离容器右侧的距离。
					bottom:"auto",//grid 组件离容器底侧的距离。
					backgroundColor:"transparent",//标题背景色，默认透明。
					borderColor:"#ccc",//标题的边框颜色。支持的颜色格式同 backgroundColor。
					borderWidth: 0,//标题的边框线宽。
					borderRadius:0//圆角半径，单位px，支持传入数组分别指定 4 个圆角半径[5, 5, 0, 0] 
				},
				xAxis : {
					type : 'category',
					data : [ 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun' ]
				},
				yAxis : {
					type : 'value'
				},
				series : [ {
					data : [ 820, 932, 901, 934, 1290, 1330, 1320 ],
					type : 'bar'
				} ]
		};
		myChart.setOption(option, true);
	}
})(jQuery);