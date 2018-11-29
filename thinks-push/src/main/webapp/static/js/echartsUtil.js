(function($){
	$.fn.echart = function(options){
		var myChart = echarts.init(this.get(0));
		var option = {
			title : {
				show : true, //是否显示标题组件
				text : options.title.text, //主标题文本
				x: isIfNull(options.title.x,'center'),//文字水平对齐方式，默认自动[left|center|right]
				textStyle:{
					color:isIfNull(options.title.color,"#333"),//主标题文字的颜色
					fontStyle:'normal', //主标题文字字体的风格[normal|italic|oblique]
					fontWeight:'normal',//主标题文字字体的粗细[normal|bold|bolder|lighter]
					fontFamily:'sans-serif',//主标题文字的字体系列[Microsoft YaHei]
					fontSize:isIfNull(options.title.fontSize,"18"),//主标题文字的字体大小
				},
				subtext:isIfNull(options.title.subtext),//副标题文本，支持使用 \n 换行
				triggerEvent:false,//是否触发事件
				padding:5,//标题内边距，单位px，默认各方向内边距为5，接受数组分别设定上右下左边距
				itemGap:10,//主副标题之间的间距
				backgroundColor:'transparent',//标题背景色，默认透明。
				borderColor:'#ccc',//标题的边框颜色。支持的颜色格式同 backgroundColor。
				borderWidth: 0,//标题的边框线宽。
				borderRadius:0//圆角半径，单位px，支持传入数组分别指定 4 个圆角半径[5, 5, 0, 0] 
			},
			legend:{
				show : true,
				data : options.legend.data,
				type : isIfNull(options.legend.type,'scroll'),//图例的类型[plain|scroll]
				orient : isIfNull(options.legend.orient,'horizontal'),//图例列表的布局朝向[horizontal|vertical]
				padding : 5,//图例内边距，单位px，默认各方向内边距为5，接受数组分别设定上右下左边距
				itemGap : 10,//图例每项之间的间隔。横向布局时为水平间隔，纵向布局时为纵向间隔
				itemWidth : 25,//图例标记的图形宽度
				itemHeight : 14,//图例标记的图形高度
				formatter : null,//用来格式化图例文本，支持字符串模板和回调函数两种形式//使用字符串模板，模板变量为图例名称 {name},formatter: function (name) { return 'Legend ' + name;}
				selectedMode : true,//图例选择的模式，控制是否可以通过点击图例改变系列的显示状态。默认开启图例选择，可以设成 false 关闭
				inactiveColor : '#ccc',//图例关闭时的颜色
				selected:{},//图例选中状态表{ '系列1': true, '系列2': false}
				tooltip : {show: true},
				backgroundColor : 'transparent',//图例背景色，默认透明
				borderColor : '#ccc',//图例的边框颜色
				borderWidth : 0,//图例的边框线宽
				borderRadius : 0,//圆角半径，单位px，支持传入数组分别指定 4 个圆角半径
				pageButtonItemGap : 5,//图例控制块中，按钮和页信息之间的间隔
				pageButtonGap : null,//图例控制块和图例项之间的间隔
				pageButtonPosition : 'end',//图例控制块的位置[start|end]
				pageFormatter : '{current}/{total}',//图例控制块中，页信息的显示格式。默认为 '{current}/{total}'
				pageIconColor : '#2f4554',//翻页按钮的颜色
				pageIconInactiveColor : '#aaa',//翻页按钮不激活时（即翻页到头时）的颜色。
				pageIconSize : 15,//翻页按钮的大小。可以是数字，也可以是数组，如 [10, 3]，表示 [宽，高]
				animation : true, //图例翻页是否使用动画
				animationDurationUpdate : 800,//图例翻页时的动画时长
				top : isIfNull(options.legend.top,'auto'),
				left : isIfNull(options.legend.left,'auto'),
				right : isIfNull(options.legend.right,'30'),
				bottom : isIfNull(options.legend.bottom,'auto'),
			},
			xAxis : {
				show : true,
				name : options.x.name,//坐标轴名称
				type : isIfNull(options.x.type,'category'), //坐标轴类型[value|category|time|log]
				nameLocation : isIfNull(options.x.nameLocation,'end'),//坐标轴名称显示位置[start|middle|center|end]
				gridIndex : 0, //x 轴所在的 grid 的索引，默认位于第一个 grid
				position : isIfNull(options.x.position,'bottom'), //x 轴的位置[top|bottom]
				data : options.x.data,
				nameGap : 15,//坐标轴名称与轴线之间的距离。
				nameRotate : null,//坐标轴名字旋转，角度值。
				inverse : false,//是否是反向坐标轴
				min	: isIfNull(options.x.min,null),//坐标轴刻度最小值。可以设置成特殊值 'dataMin'，此时取数据在该轴上的最小值作为最小刻度，function(value) {return value.min - 20;}
				max : isIfNull(options.x.max,null), //坐标轴刻度最大值。可以设置成特殊值 'dataMax'，此时取数据在该轴上的最大值作为最大刻度，max: function(value) {return value.max - 20;}
				splitNumber : 5,//坐标轴的分割段数，需要注意的是这个分割段数只是个预估值
				minInterval : 0,//自动计算的坐标轴最小间隔大小。
				maxInterval : 0,//自动计算的坐标轴最大间隔大小。
				interval : 0,//强制设置坐标轴分割间隔。
				silent : false,//坐标轴是否是静态无法交互。
				triggerEvent :false,//坐标轴的标签是否响应和触发鼠标事件，默认不响应。
			},
			yAxis : {
				show : true,
				name : options.y.name,//坐标轴名称
				nameLocation : isIfNull(options.y.nameLocation,'end'),//坐标轴名称显示位置[start|middle|center|end]
				type : isIfNull(options.y.type,'value'),//坐标轴类型[value|category|time|log]
				gridIndex : 0,//y 轴所在的 grid 的索引，默认位于第一个 grid。
				position  :isIfNull(options.y.position,'left'),//y 轴的位置[left|right]
				offset : 0 ,//Y 轴相对于默认位置的偏移，在相同的 position 上有多个 Y 轴的时候有用。
				//min : null,//坐标轴刻度最小值。可以设置成特殊值 'dataMin'，此时取数据在该轴上的最小值作为最小刻度，function(value) {return value.min - 20;}
				//max : null, //坐标轴刻度最大值。可以设置成特殊值 'dataMax'，此时取数据在该轴上的最大值作为最大刻度，max: function(value) {return value.max - 20;}
				//splitNumber : 5,//坐标轴的分割段数，需要注意的是这个分割段数只是个预估值
				//minInterval : 0,//自动计算的坐标轴最小间隔大小。
				//maxInterval : 0,//自动计算的坐标轴最大间隔大小。
				//interval : 0,//强制设置坐标轴分割间隔。
				silent : false,//坐标轴是否是静态无法交互。
				triggerEvent :false,//坐标轴的标签是否响应和触发鼠标事件，默认不响应。
			},
			tooltip : {
				show : true,
				trigger : 'axis',//触发类型[item|axis|none]
				showContent : true,//是否显示提示框浮层，默认显示。只需tooltip触发事件或显示axisPointer而不需要显示内容时可配置该项为false。
				alwaysShowContent : false,//是否永远显示提示框内容，默认情况下在移出可触发提示框区域后 一定时间 后隐藏，设置为 true 可以保证一直显示提示框内容。
				triggerOn : 'mousemove|click',//提示框触发的条件[mousemove|click|none]
				confine : false ,//是否将 tooltip 框限制在图表的区域内。
				backgroundColor:'rgba(50,50,50,0.7)',//提示框浮层的背景颜色。
				borderColor :'#333',//提示框浮层的边框颜色。
				borderWidth : 0,//提示框浮层的边框宽。
				padding:5,//提示框浮层内边距，单位px，默认各方向内边距为5，接受数组分别设定上右下左边距。
				extraCssText:''//额外附加到浮层的 css 样式。
			},
			series : options.data
		};
		myChart.setOption(option, true);
	}
	
	function isIfNull(sourceValue,defaultValue){
		return sourceValue?sourceValue:defaultValue;
	}
})(jQuery);