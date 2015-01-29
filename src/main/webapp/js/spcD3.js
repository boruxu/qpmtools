(function(){
	  
    var spcD3={		
        author:"boruxu",
        version:"v1.0",
        dependency:"JQuery.js,D3.js",
		size:"设置svg图大小函数",
        XR:"X-R图生成函数",
		width:600,
		height:250,
		margin:50
        };
		
	spcD3.size=function(width,height,margin){
		spcD3.width=width;
		spcD3.height=height;
		spcD3.margin=margin;
		return spcD3;
		}
		
	var timemin=0;
	var timemax=100;
	var svg_1_xmin=0;
	var svg_1_xmax=100;
	
	var svg_2_xmax=0;
	var svg_2_xmax=100;
	//计算出time、svg_x的最大和最小值
	function computeXRMaxMin(spcOutData){
		timemin=d3.min(spcOutData.time)-1;
		timemax=d3.max(spcOutData.time)+1;
		svg_1_xmin=d3.min(spcOutData.x);
		svg_1_xmax=d3.max(spcOutData.x);
	    svg_2_xmin=d3.min(spcOutData.r);
		svg_2_xmax=d3.max(spcOutData.r);
		};
	//画出spc中X-R图，输入x参数为X-R图数据，svg的JQuery选择器字符串，如"#svg1"
    spcD3.XR=function(spcOutData,svgX,svgR){
		
		var svg_1 = d3.select(svgX)     
        .attr("class", "axis")  
        .attr("width", spcD3.width)
        .attr("height", spcD3.height)
		.attr("style","margin:0px 0px 0px 50px");
		
        var svg_2 = d3.select(svgR)     
        .attr("class", "axis")  
        .attr("width", spcD3.width)
        .attr("height", spcD3.height)
		.attr("style","margin:0px 0px 0px 50px");
		
		var margin=spcD3.margin;
		var width=spcD3.width;
		var height=spcD3.height;
		
		//画出X轴
		 var xAxisLength = spcD3.width - 2 * spcD3.margin;
		 ;
		 
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
		renderXRXYPoint(spcOutData,svg_1,svg_2,xAxisLength,yAxisLength,margin);
		  
			  
		}; 
		
		      
<!--建立X灰色坐标轴-->
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
<!--建立灰色Y坐标轴-->
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
<!--画出XY的点函数-->
function renderXRXYPoint(spcOutData,svg_1,svg_2,xAxisLength,yAxisLength,margin){

var scalex= d3.scale.linear()
                    .domain([timemin, timemax])
                    .range([0, xAxisLength]);
var scaley_1= d3.scale.linear()
                    .domain([svg_1_xmax, svg_1_xmin])
                    .range([0, yAxisLength]);
var scaley_2= d3.scale.linear()
                    .domain([svg_2_xmax, svg_2_xmin])
                    .range([0, yAxisLength]);
			
			
var dataset_1=dataToPointArray(spcOutData.time,spcOutData.x);
var dataset_2=dataToPointArray(spcOutData.time,spcOutData.r);

svg_1.selectAll("circle")
	   .data(dataset_1)
	   .enter()
	   .append("circle");
	   
svg_1.selectAll("circle")
	   .data(dataset_1)
       .transition()
	   .attr("cx", function(d) {
		   return scalex(d[0]);})
	   .attr("cy", function(d) {
			   return scaley_1(d[1]);
			   })
	   .attr("r", 4)
	   .attr("transform", function(){
            return "translate(" + margin + "," + margin + ")";
        });	
		

svg_2.selectAll("circle")
	   .data(dataset_2)
	   .enter()
	   .append("circle");
	   
svg_2.selectAll("circle")
	   .data(dataset_2)
       .transition()
	   .attr("cx", function(d) {
		   return scalex(d[0]);})
	   .attr("cy", function(d) {
			   return scaley_2(d[1]);
			   })
	   .attr("r", 4)
	   .attr("transform", function(){
            return "translate(" + margin + "," + margin + ")";
        });	

		
}
<!--数据转换函数-->	
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