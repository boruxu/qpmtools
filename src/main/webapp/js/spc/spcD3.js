(function(){
	  
    var spcD3={		
        author:"boruxu",
        version:"v1.0",
        dependency:"JQuery.js,D3.js",
		size:"设置svg图大小函数",
        XR:"X-R图生成函数",
        XS:"X-S图生成函数",
		XMR:"XMR图生成函数",
        C:"C图生成函数",
		width:600,
		height:250,
		margin:50
        };
		
	spcD3.size=function(width,height,margin){
		spcD3.width=width;
		spcD3.height=height;
		spcD3.margin=margin;
		return spcD3;
		};
	var svg_1;
	var svg_2;	
	var timemin=0;
	var timemax=100;
	var svg_1_xmin=0;
	var svg_1_xmax=100;
	
	var svg_2_xmin=0;
	var svg_2_xmax=100;

    //字符串数组转换为数字数组
    function stringArrayToNumberArray(array){
        var tempArray=[];
        array.forEach(function(e){
            //利用js的弱类型转化
            tempArray.push(e-0);
        });
        return tempArray;

    }
	//计算出time、svg_x的最大和最小值
	function computeC(spcOutData){

        var timeArray=stringArrayToNumberArray(spcOutData.time);
        var xArray=stringArrayToNumberArray(spcOutData.x);

		timemin=d3.min(timeArray)-1;
		timemax=d3.max(timeArray)+1;
        var xMin=d3.min(xArray);
        var xMax=d3.max(xArray);
        //图表xy轴最大最小值预留长度
        var range=(xMax-xMin)*0.05;

		if(spcOutData.cLCL<xMin){
			svg_1_xmin=spcOutData.cLCL-range;
			}
		else{
			svg_1_xmin=xMin-range;
			}

		if(spcOutData.cUCL>xMax){
			svg_1_xmax=spcOutData.cUCL+range;
			}
		else{
			svg_1_xmax=xMax+range;
			}


		}
    function computeXRMaxMin(spcOutData){
        timemin=d3.min(spcOutData.time)-1;
        timemax=d3.max(spcOutData.time)+1;

        var range=(d3.max(spcOutData.x)-d3.min(spcOutData.x))*0.2;
        var temp=d3.min(spcOutData.x);
        if(spcOutData.xLCL<temp){
            svg_1_xmin=spcOutData.xLCL-range;
        }
        else{
            svg_1_xmin=temp-range;
        }

        temp=d3.max(spcOutData.x);
        if(spcOutData.xUCL>temp){
            svg_1_xmax=spcOutData.xUCL+range;
        }
        else{
            svg_1_xmax=temp+range;
        }

        range=(d3.max(spcOutData.r)-d3.min(spcOutData.r))*0.2;
        temp=d3.min(spcOutData.r);
        if(spcOutData.rLCL<temp){
            svg_2_xmin=spcOutData.rLCL-range;
        }
        else{
            svg_2_xmin=temp-range;
        }

        temp=d3.max(spcOutData.r);
        if(spcOutData.rUCL>temp){
            svg_2_xmax=spcOutData.rUCL+range;
        }
        else{
            svg_2_xmax=temp+range;
        }

    }
	//画出spc中X-R图，输入x参数为X-R图数据，svg的JQuery选择器字符串，如"#svg1"
    spcD3.XR=function(spcOutData,svgX,svgR){
		
		 svg_1 = d3.select(svgX)     
        .attr("class", "axis")  
        .attr("width", spcD3.width)
        .attr("height", spcD3.height)
		.attr("style","margin:0px 0px 0px 50px");
		
        svg_2 = d3.select(svgR)     
        .attr("class", "axis")  
        .attr("width", spcD3.width)
        .attr("height", spcD3.height)
		.attr("style","margin:0px 0px 0px 50px");
		
		var margin=spcD3.margin;
		var width=spcD3.width;
		var height=spcD3.height;
		
		//画出X轴
		 var xAxisLength = spcD3.width - 2 * spcD3.margin;
		 
		 computeXRMaxMin(spcOutData);

         var scale= d3.scale.linear()
                    .domain([timemin, timemax])
                    .range([0, xAxisLength]);
    
         var xAxis = d3.svg.axis()
            .scale(scale)
            .tickSubdivide(1)
            .orient("bottom");
            
         svg_1.append("g")       
              .attr("class", "x-axis")
              .attr("transform", function(){ 
			  return "translate(" + margin + "," + (height - margin) + ")";})
              .call(xAxis);
			  
	     svg_2.append("g")       
              .attr("class", "x-axis")
              .attr("transform", function(){ 
			  return "translate(" + margin + "," + (height - margin) + ")";})
              .call(xAxis);	
		//画Y轴
			  
		 var yAxisLength = spcD3.height - 2 * spcD3.margin;
		

         var scale_1 = d3.scale.linear()
                         .domain([svg_1_xmax,svg_1_xmin])
                         .range([0, yAxisLength]);

         var yAxis_1 = d3.svg.axis()
                         .scale(scale_1)
                         .tickSubdivide(1)
                         .orient("left");
        
         svg_1.append("g")       
              .attr("class", "y-axis")
              .attr("transform", function(){return "translate(" + margin + "," + margin + ")";})
              .call(yAxis_1);
		
         var scale_2 = d3.scale.linear()
                    .domain([svg_2_xmax, svg_2_xmin])
                    .range([0, yAxisLength]);

         var yAxis_2 = d3.svg.axis()
                          .scale(scale_2)
                          .tickSubdivide(1)
                          .orient("left");
        
         svg_2.append("g")       
              .attr("class", "y-axis")
              .attr("transform", function(){return "translate(" + margin + "," + margin + ")";})
              .call(yAxis_2);
			  
		renderXGridlines(yAxisLength);

		renderYGridlines(xAxisLength);
		renderXRXYLines(spcOutData,svg_1,svg_2,xAxisLength,yAxisLength,margin);
		  
			  
		};

    //画出spc中C图
    spcD3.C=function(spcOutData,svgX){

        svg_1 = d3.select("svg")
            .attr("class", "axis")
            .attr("width", spcD3.width)
            .attr("height", spcD3.height)
            .attr("style","margin:0px 0px 0px 50px");

        var margin=spcD3.margin;
        var width=spcD3.width;
        var height=spcD3.height;

        //画出X轴
        var xAxisLength = spcD3.width - 2 * spcD3.margin;

        computeC(spcOutData);

        var scale= d3.scale.linear()
            .domain([timemin, timemax])
            .range([0, xAxisLength]);

        var xAxis = d3.svg.axis()
            .scale(scale)
            .tickSubdivide(1)
            .orient("bottom");

        svg_1.append("g")
            .attr("class", "x-axis")
            .attr("transform", function(){
                return "translate(" + margin + "," + (height - margin) + ")";})
            .call(xAxis);

        //画Y轴

        var yAxisLength = spcD3.height - 2 * spcD3.margin;


        var scale_1 = d3.scale.linear()
            .domain([svg_1_xmax,svg_1_xmin])
            .range([0, yAxisLength]);

        var yAxis_1 = d3.svg.axis()
            .scale(scale_1)
            .tickSubdivide(1)
            .orient("left");

        svg_1.append("g")
            .attr("class", "y-axis")
            .attr("transform", function(){return "translate(" + margin + "," + margin + ")";})
            .call(yAxis_1);


        renderXGridlines(yAxisLength);
        renderYGridlines(xAxisLength);
        renderXRXYLinesSingle(spcOutData,svg_1,xAxisLength,yAxisLength,margin);


    };


//建立X灰色坐标轴
function renderXGridlines(yAxisLength){
    var lines = d3.selectAll("g.x-axis g.tick")
            .select("line.grid-line")
            .remove(); 
            
    lines = d3.selectAll("g.x-axis g.tick")
            .append("line") 
            .classed("grid-line", true)

    lines.attr("x1", 0) 
            .attr("y1", 0)
            .attr("x2", 0)
            .attr("y2", - yAxisLength); 
}
//建立灰色Y坐标轴
function renderYGridlines(xAxisLength){

    var lines = d3.selectAll("g.y-axis g.tick")
            .select("line.grid-line").remove(); 
    
    lines = d3.selectAll("g.y-axis g.tick")
            .append("line") 
            .classed("grid-line", true)
            
    lines.attr("x1", 0)
        .attr("y1", 0)
        .attr("x2", xAxisLength)
        .attr("y2", 0);
}
//画出XY的点函数(单个图)
function renderXRXYLinesSingle(spcOutData,svg_1,xAxisLength,yAxisLength,margin){

var scalex= d3.scale.linear()
                    .domain([timemin, timemax])
                    .range([0, xAxisLength]);
var scaley_1= d3.scale.linear()
                    .domain([svg_1_xmax, svg_1_xmin])
                    .range([0, yAxisLength]);

			
			
				
		var c_lineDataset_1=[["UCL",spcOutData.cUCL],["CL",spcOutData.cCL],["LCL",spcOutData.cLCL]];
	

	   //画控制限
		var lineEnter_1=svg_1.selectAll("g.control")
		     .data(c_lineDataset_1)
             .enter()
			 .append("g")
			 .attr("class","control")
			 .attr("transform", function(){return "translate(" + margin + "," + margin + ")";});
			 
			 lineEnter_1 .append("line")                
             .attr("class", "control_line")
			 .attr("class",function(d,i){ return "control_line "+"_"+i;});
			 
			 lineEnter_1 .append("text")                
             .attr("class", "control_line")
			 .attr("class",function(d,i){ return "control_line "+"_"+i;});
			 
			 
	    svg_1.selectAll("line.control_line")
                .data(c_lineDataset_1)
                .transition() 
			 .attr("x1",0)
			 .attr("y1",function(d){  return scaley_1(d[1]);})
			 .attr("x2",xAxisLength)
			 .attr("y2",function(d){return scaley_1(d[1]);});
			 
		  svg_1.selectAll("text.control_line")
                .data(c_lineDataset_1)
                .transition() 
			 .attr("x",xAxisLength-margin)
			 .attr("y",function(d){  return scaley_1(d[1])-2;})
			 .text(function(d){  return d[0]+":"+d[1];});
			 


			 
			 
var dataset_1=dataToPointArray(spcOutData.time,spcOutData.x);


//画连接线
		var lines_1= d3.svg.line() 
                        .x(function (d) { return scalex(d[0]); })
                        .y(function (d) { return scaley_1(d[1]); });

		var lineDataset_1=new Array;
		lineDataset_1.push(dataset_1);

		svg_1.selectAll("path.line")
                    .data(lineDataset_1)
                .enter() 
                .append("path")                
                .attr("class", "line")
				.attr("transform", function(){
            return "translate(" + margin + "," + margin + ")";
        });


        svg_1.selectAll("path.line")
                .data(lineDataset_1)
                .transition() 
                .attr("d", function (d) {  return lines_1(d); });
				

				
		//画点

var dotEnter_1=svg_1.selectAll("g.dot")
	   .data(dataset_1)
	   .enter()
	   .append("g")
	   .attr("class", "dot")
	   .attr("transform", function(){
            return "translate(" + margin + "," + margin + ")";
        });
		
	   dotEnter_1.append("circle")
	            .attr("class", "dot");	    

	   
svg_1.selectAll("circle.dot")
	   .data(dataset_1)
       .transition()
	   .attr("cx", function(d) { 
		   return scalex(d[0]);})
	   .attr("cy", function(d) {
			   return scaley_1(d[1]);
			   })
	   .attr("r", 4);	
//点上的数字
dotEnter_1.append("text")
.attr("class", "dot");

svg_1.selectAll("text.dot")
	   .data(dataset_1)
	   .enter()
	   .append("text")
	   .attr("class", "dot");
	   
svg_1.selectAll("text.dot")
	   .data(dataset_1)
       .transition()
	   .attr("x", function(d) { 
		   return scalex(d[0]);})
	   .attr("y", function(d) {
			   return scaley_1(d[1]);
			   })
	   .attr("dx",4)
	   .attr("dy",-4)
	   .text(function (d) {
                    return d[1];
                });	


		
		return spcD3;
		
	
}

    function renderXRXYLines(spcOutData,svg_1,svg_2,xAxisLength,yAxisLength,margin){

        var scalex= d3.scale.linear()
            .domain([timemin, timemax])
            .range([0, xAxisLength]);
        var scaley_1= d3.scale.linear()
            .domain([svg_1_xmax, svg_1_xmin])
            .range([0, yAxisLength]);
        var scaley_2= d3.scale.linear()
            .domain([svg_2_xmax, svg_2_xmin])
            .range([0, yAxisLength]);



        var c_lineDataset_1=[["UCL",spcOutData.xUCL],["CL",spcOutData.xCL],["LCL",spcOutData.xLCL]];
        var c_lineDataset_2=[["UCL",spcOutData.rUCL],["CL",spcOutData.rCL],["LCL",spcOutData.rLCL]];


        //画控制限
        var lineEnter_1=svg_1.selectAll("g.control")
            .data(c_lineDataset_1)
            .enter()
            .append("g")
            .attr("class","control")
            .attr("transform", function(){return "translate(" + margin + "," + margin + ")";});

        lineEnter_1 .append("line")
            .attr("class", "control_line")
            .attr("class",function(d,i){ return "control_line "+"_"+i;});

        lineEnter_1 .append("text")
            .attr("class", "control_line")
            .attr("class",function(d,i){ return "control_line "+"_"+i;});


        svg_1.selectAll("line.control_line")
            .data(c_lineDataset_1)
            .transition()
            .attr("x1",0)
            .attr("y1",function(d){  return scaley_1(d[1]);})
            .attr("x2",xAxisLength)
            .attr("y2",function(d){return scaley_1(d[1]);});

        svg_1.selectAll("text.control_line")
            .data(c_lineDataset_1)
            .transition()
            .attr("x",xAxisLength-margin)
            .attr("y",function(d){  return scaley_1(d[1])-2;})
            .text(function(d){  return d[0]+":"+d[1];});

        var lineEnter_2=svg_2.selectAll("g.control")
            .data(c_lineDataset_1)
            .enter()
            .append("g")
            .attr("class","control")
            .attr("transform", function(){return "translate(" + margin + "," + margin + ")";});

        lineEnter_2 .append("line")
            .attr("class", "control_line")
            .attr("class",function(d,i){ return "control_line "+"_"+i;});

        lineEnter_2 .append("text")
            .attr("class", "control_line")
            .attr("class",function(d,i){ return "control_line "+"_"+i;});


        svg_2.selectAll("line.control_line")
            .data(c_lineDataset_2)
            .transition()
            .attr("x1",0)
            .attr("y1",function(d){  return scaley_2(d[1]);})
            .attr("x2",xAxisLength)
            .attr("y2",function(d){return scaley_2(d[1]);});

        svg_2.selectAll("text.control_line")
            .data(c_lineDataset_2)
            .transition()
            .attr("x",xAxisLength-margin)
            .attr("y",function(d){  return scaley_2(d[1])-2;})
            .text(function(d){  return d[0]+":"+d[1];});


        var dataset_1=dataToPointArray(spcOutData.time,spcOutData.x);
        var dataset_2=dataToPointArray(spcOutData.time,spcOutData.r);

//画连接线
        var lines_1= d3.svg.line()
            .x(function (d) { return scalex(d[0]); })
            .y(function (d) { return scaley_1(d[1]); });
        var lines_2= d3.svg.line()
            .x(function (d) { return scalex(d[0]); })
            .y(function (d) { return scaley_2(d[1]); });

        var lineDataset_1=new Array;
        lineDataset_1.push(dataset_1);
        var lineDataset_2=new Array;
        lineDataset_2.push(dataset_2);

        svg_1.selectAll("path.line")
            .data(lineDataset_1)
            .enter()
            .append("path")
            .attr("class", "line")
            .attr("transform", function(){
                return "translate(" + margin + "," + margin + ")";
            });

        svg_2.selectAll("path.line_2")
            .data(lineDataset_2)
            .enter()
            .append("path")
            .attr("class", "line_2")
            .attr("transform", function(){
                return "translate(" + margin + "," + margin + ")";
            });


        svg_1.selectAll("path.line")
            .data(lineDataset_1)
            .transition()
            .attr("d", function (d) {  return lines_1(d); });

        svg_2.selectAll("path.line_2")
            .data(lineDataset_2)
            .transition()
            .attr("d", function (d) { return lines_2(d); });




        //画点

        var dotEnter_1=svg_1.selectAll("g.dot")
            .data(dataset_1)
            .enter()
            .append("g")
            .attr("class", "dot")
            .attr("transform", function(){
                return "translate(" + margin + "," + margin + ")";
            });

        dotEnter_1.append("circle")
            .attr("class", "dot");


        svg_1.selectAll("circle.dot")
            .data(dataset_1)
            .transition()
            .attr("cx", function(d) {
                return scalex(d[0]);})
            .attr("cy", function(d) {
                return scaley_1(d[1]);
            })
            .attr("r", 4);
//点上的数字
        dotEnter_1.append("text")
            .attr("class", "dot");

        svg_1.selectAll("text.dot")
            .data(dataset_1)
            .enter()
            .append("text")
            .attr("class", "dot");

        svg_1.selectAll("text.dot")
            .data(dataset_1)
            .transition()
            .attr("x", function(d) {
                return scalex(d[0]);})
            .attr("y", function(d) {
                return scaley_1(d[1]);
            })
            .attr("dx",4)
            .attr("dy",-4)
            .text(function (d) {
                return d[1];
            });



        var dotEnter_2=svg_2.selectAll("g.dot")
            .data(dataset_2)
            .enter()
            .append("g")
            .attr("class", "dot")
            .attr("transform", function(){
                return "translate(" + margin + "," + margin + ")";
            });

        dotEnter_2.append("circle")
            .attr("class", "dot");


        svg_2.selectAll("circle.dot")
            .data(dataset_2)
            .transition()
            .attr("cx", function(d) {
                return scalex(d[0]);})
            .attr("cy", function(d) {
                return scaley_2(d[1]);
            })
            .attr("r", 4);

        dotEnter_2.append("text")
            .attr("class", "dot");

        svg_2.selectAll("text.dot")
            .data(dataset_2)
            .enter()
            .append("text")
            .attr("class", "dot");

        svg_2.selectAll("text.dot")
            .data(dataset_2)
            .transition()
            .attr("x", function(d) {
                return scalex(d[0]);})
            .attr("y", function(d) {
                return scaley_2(d[1]);
            })
            .attr("dx",4)
            .attr("dy",-4)
            .text(function (d) {
                return d[1];
            });

        return spcD3;


    }


     spcD3.XS=function(spcOutData,svgX,svgR){
		 
		 //转换数据格式以复用XR
		 var spcOutDataXR={};
		 spcOutDataXR.x=spcOutData.x;
		 spcOutDataXR.time=spcOutData.time;
		 spcOutDataXR.r=spcOutData.s;
		 spcOutDataXR.xUCL=spcOutData.xUCL;
		 spcOutDataXR.xCL=spcOutData.xCL;
		 spcOutDataXR.xLCL=spcOutData.xLCL;
		 spcOutDataXR.rUCL=spcOutData.sUCL;
		 spcOutDataXR.rCL=spcOutData.sCL;
		 spcOutDataXR.rLCL=spcOutData.sLCL;

		 spcD3.XR(spcOutDataXR,svgX,svgR);
		 
		 }
		 
		 spcD3.XMR=function(spcOutData,svgX,svgR){
		 
		 //转换数据格式以复用XR
		 var spcOutDataXR={};
		 spcOutDataXR.x=spcOutData.x;
		 spcOutDataXR.time=spcOutData.time;
		 spcOutDataXR.r=spcOutData.mr;
		 spcOutDataXR.xUCL=spcOutData.xUCL;
		 spcOutDataXR.xCL=spcOutData.xCL;
		 spcOutDataXR.xLCL=spcOutData.xLCL;
		 spcOutDataXR.rUCL=spcOutData.mrUCL;
		 spcOutDataXR.rCL=spcOutData.mrCL;
		 spcOutDataXR.rLCL=spcOutData.mrLCL;

		 spcD3.XR(spcOutDataXR,svgX,svgR);
		 
		 };
     spcD3.empty=function(svgX,svgR){
		 $(svgX).empty();
		 $(svgX).height(0);
		 $(svgR).empty();
		 $(svgR).height(0);
		 return spcD3;
		 };

    //数据转换函数
function dataToPointArray(data_x,data_y){
//输入数据为dataXY格式
    var dataset=new Array();
	var datasetX=new Array();
	data_x.forEach(function(d)
	{
		datasetX.push(d);
		});
    data_y.forEach(function(d,i)
	{
		dataset.push([datasetX[i],d])
		});
	return dataset;
}

		  
   

     this.spcD3 = spcD3;
	
	
	
	
	})();