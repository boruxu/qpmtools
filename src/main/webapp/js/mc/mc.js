/**
 * Created by x on 2015/3/17.
 */
!(function()
{

    var mcD3={
        compute:"",
        size:'',
        width:'',
        height:''
    };
    mcD3.size=function(width,height)
    {
        mcD3.width=width;
        mcD3.height=height;
        return mcD3;
    };
    mcD3.compute=function(mcArray,simulationNumber)
    {
        $("svg").empty();
        var margin = {top: 10, right: 100, bottom: 30, left:50},
            width = mcD3.width - margin.left - margin.right,
            height = mcD3.height - margin.top - margin.bottom;

        var min=d3.min(mcArray);
        var max=d3.max(mcArray);
        var middle=(max-min)/2+min;

        var x = d3.scale.linear()
            .domain([min,max])
            .range([0,width]);

        //分组数
        var k=simulationNumber/10;
        console.log("k",k);
        console.log("width",width);
        var groupNumber=Math.min(k,width);
        console.log("groupNumber",groupNumber);
        var data = d3.layout.histogram()
            .bins(x.ticks(groupNumber))
        (mcArray);


        var recty=d3.max(data, function(d) { return d.y; });
        var y = d3.scale.linear()
            .domain([0,recty])
            .range([height, 0]);

        var y1 = d3.scale.linear()
            .domain([0, recty/simulationNumber])
            .range([height, 0]);


        var xAxis = d3.svg.axis()
            .scale(x)
            .orient("bottom")
            .ticks(20);

        var yAxisr= d3.svg.axis()
            .scale(y)
            .orient("right")
            .ticks(10);

        var yAxisl = d3.svg.axis()
            .scale(y1)
            .orient("left")
            .ticks(10)
            ;

        var svg = d3.select("svg")
            .attr("width", width + margin.left + margin.right)
            .attr("height", height + margin.top + margin.bottom)
            .append("g")
            .attr("transform", "translate(" + margin.left + "," + margin.top + ")");


        var bar = svg.selectAll(".bar")
            .data(data)
            .enter().append("g")
            .attr("class", "bar")
            .attr("transform", function(d) { return "translate(" + x(d.x) + "," + y(d.y) + ")"; });
//显示柱状条形的width宽度=一个条形分组所占宽度（用var x转换对应宽度【一个分组的刻度范围对应像素大小】）
        bar.append("rect")
            .attr("x", 1)
            .attr("width",x(min+data[0].dx))
            .attr("height", function(d) { return height - y(d.y); });

        /*bar.append("text")
         .attr("dy", ".75em")
         .attr("y", 6)
         .attr("x", x(data[0].dx) / 2)
         .attr("text-anchor", "middle")
         .text(function(d) { return d.x; });
         */
        svg.append("g")
            .attr("class", "axis")
            .attr("transform", "translate(0," + height + ")")
            .call(xAxis);

        svg.append("g")
            .attr("class", "axis")
            .attr("transform", "translate(0,0)")
            .call(yAxisl);

        svg.append("g")
            .attr("class", "axis")
            .attr("transform", "translate(0,0)")
            .call(yAxisl);

        svg.append("g")
            .attr("class", "axis")
            .attr("transform", "translate("+width+",0)")
            .call(yAxisr);



        var brush = d3.svg.brush()
            .x(x)
            .extent([min,max])
            .on("brushstart", brushstart)
            .on("brush", brushmove)
            .on("brushend", brushend);

        var brushg = svg.append("g")
            .attr("class", "brush")
            .call(brush);

        var arc = d3.svg.arc()
            .outerRadius(height / 20)
            .innerRadius(height / 40)
            .startAngle(0)
            .endAngle(function(d, i) { return i ? -Math.PI : Math.PI; });

        /*范围选择的三角形提示标识的绘制*/
        var lineData = [ { "x": 0,   "y": 0},  { "x":20,  "y":-20},
            { "x": 20,  "y": 20}];

        var lineData1 = [ { "x": 0,   "y": 0},  { "x":-20,  "y":-20},
            { "x": -20,  "y": 20}];

        var triangle=d3.svg.line()
            .interpolate("linear-closed")
            .x(function(d) { return d.x;})
            .y(function(d) {return d.y;});

        brushg.selectAll(".resize").append("path")
            .attr("transform", "translate(0,"+height+")")
        ;
        var resize=brushg.selectAll(".resize");
        resize.select("path").attr("d", function(d,i){
            if(i==0)
            {return triangle(lineData); }
            if(i==1)
            {return triangle(lineData1);}
        });

        var rect= svg.selectAll(".bar").selectAll("rect");

        brushg.selectAll("rect")
            .attr("height", height);

        brushstart();
        brushmove();

        function brushstart() {
            svg.classed("rect", true);
            var s = brush.extent();
            rect.classed("selected",function(d){ return d.x>=s[0]&&d.x<s[1]});
            /*d3.select("#lowerBound").text(s[0]);
            d3.select("#upperBound").text(s[1]);*/
        }

        function brushmove() {
            var s = brush.extent();
            rect.classed("selected",function(d){ return d.x>=s[0]&&d.x<s[1]});
            /*d3.select("#lowerBound").text(s[0]);
            d3.select("#upperBound").text(s[1]);
            d3.select("#probability").text();*/
        }

        function brushend() {
            svg.classed("rect", !d3.event.target.empty());
        }

        return mcD3;

    };

    this.mcD3=mcD3;

})();
