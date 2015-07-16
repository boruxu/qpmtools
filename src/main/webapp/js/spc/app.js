/**
 * Created by BoruXU on 2015/2/19
 * 本文件为spc工具angular路由js文件
 */
var app=angular.module('qpmtools.spc.app',['ngAnimate','ui.router','qpmtools.systemRest']);

app.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/spc');

    $stateProvider

        .state('spc', {
            url: '/spc',
            templateUrl: 'spc/spc.html',
            controller:'SPCHomeController'
        })
        .state('spc.detail', {
            url: '/detail/{type}/{id}',
            templateUrl: 'spc/detail.html',
            controller:'SPCDetailController'
        })
        .state('spc.edit', {
            url: '/edit/{type}/{id}',
            templateUrl: 'spc/edit.html',
            controller:'SPCDetailController'
        })
        .state('spc.create', {
            url: '/create/{type}',
            templateUrl: 'spc/edit.html',
            controller:'SPCDetailController'
        });



});

app.controller('SPCHomeController',['$scope','RestServerce'
    ,function($scope,RestServerce){

        //初始化spc
        $scope.spc={
            organizationList:'',
            projectList:'',
            selectOrganization:'',
            selectProject:'',
            spcList:[],
            message:'',
            state:'null',
            plotTypeList:[]
        };

        //页面加载的时候获取组织列表
        RestServerce.get("api/system/organization/list").then(
            function(data){

                $scope.spc.organizationList=data.list;

            },function(error){});

        //选择组织后,访问后台获取相应所属项目
        $scope.choseO=function(){
            $scope.spc.selectProject==null;
            $scope.spc.spcList='';
            if($scope.spc.selectOrganization!=null)
            {
                var url=$scope.spc.selectOrganization.name;

                RestServerce.get("api/system/project/list/"+url).then(
                    function(data){
                        $scope.spc.projectList=data.list;
                    },function(error){});
            }
            else{
                $scope.spc.spcList='';
            }

        };

        //选择项目后,访问后台获取相应spcList(nameList)
        $scope.choseP=function(){

            if($scope.spc.selectProject!=null)
            {
                $scope.spc.getSPCListByProject();
            }
            else{
                $scope.spc.spcList=[];
            }



        };

        $scope.spc.getSPCListByProject=function(){

            RestServerce.get("api/spc/plotList/"+$scope.spc.selectProject.name).then(
                function(data){
                    $scope.spc.spcList=data;
                },function(error){
                    $scope.spc.spcList=[];
                });

        };


        var getPlotTypeList=function()
        {

            RestServerce.get("api/spc/help/plotTypeList").then(
                function(data){
                    $scope.spc.plotTypeList=data;
                },function(error){
                    $scope.spc.plotTypeList=[];
                    console.log(error);
                });
        };

        getPlotTypeList();





        $scope.spc.tips=function(message){
            $scope.spc.message=message;
            setTimeout(function(){
                $scope.spc.message="";
                $scope.$apply($scope.spc.message);
            },2000);
        };

        $scope.remove=function(type,id){
            RestServerce.remove("api/spc/"+type+"/"+id).then(
                function(data){

                    $scope.spc.getSPCListByProject();
                    $scope.spc.tips("删除成功！");

                },function(error){
                    $scope.spc.tips(error);
                });
        };

        $scope.selectType=function(){
            $("#spcTypeSelect").addClass("md-show");
        };
        $scope.closeSelectType=function(){
            $("#spcTypeSelect").removeClass("md-show");
        };


    }]);



app.controller('SPCDetailController',['$scope','$stateParams','RestServerce','$state',function($scope,$stateParams,RestServerce,$state){

    $scope.detail={
        name:'',
        project:{name:''},
        input:{},
        output:{}

    };
    $scope.number=0;
    $scope.subNumber=0;
    $scope.numberArray=[];
    $scope.subNumberArray=[];


    $scope.sigmaSet=false;
    $scope.sigmaSetValue='设置系统标准差';
    $scope.sigmaSetF=function(){
        if($scope.sigmaSet==false)
        {
            $scope.sigmaSet=true;
            $scope.sigmaSetValue='取消设置系统标准差';
        }
        else
        {
            $scope.sigmaSet=false;
            $scope.detail.input.sigma=null;
            $scope.sigmaSetValue='设置系统标准差';
        }
    };

    //整个页面的类型
    $scope.type=$stateParams.type;


    //跟新页面的状态信息
    $scope.spc.state=$state.current.name;

    start();

    function start()
    {
        if(typeof($scope.spc.spcList)!='undefined'&&$scope.spc.spcList.length!=0
            &&($scope.spc.state=="spc.edit"||$scope.spc.state=="spc.detail"))
        {
            $scope.spc.spcList.forEach(
                function(d){
                    if(d.id==$stateParams.id)
                    {
                        $scope.detail=angular.copy(d);
                        if(typeof ($scope.detail.input.sigma)!='undefined'&&$scope.detail.input.sigma!=null)
                        {
                            $scope.sigmaSet=true;
                            $scope.sigmaSetValue='取消设置系统标准差';
                        }
                    }
                }
            );

            controlPlotByType();
        }
        //单独刷新时，返回上一级
        else if($scope.spc.state=="spc.create")
        {
            //初始化c图数据格式
            if($scope.type=="Z"||$scope.type=="U")
            {
                $scope.detail={
                    name:'',
                    input:{
                        x:[],
                        xName:'',
                        time:[],
                        timeName:'',
                        aName:'',
                        a:[]
                    }
                }
            }
            else if($scope.type=="XS"||$scope.type=="XR"){
                $scope.detail={
                    name:'',
                    input:{
                        x:[],
                        xName:'',
                        time:[],
                        timeName:''
                    }
                }
            }
            else{
                $scope.detail={
                    name:'',
                    input:{
                        x:[],
                        xName:'',
                        time:[],
                        timeName:''
                    }
                }
            }
        }
        else
        {
            $state.go("spc");
            return ;
        }

        $scope.number=$scope.detail.input.x.length;
        $scope.numberArray.length=$scope.number;
        if(($scope.type=="XS"||$scope.type=="XR")&&(typeof ($scope.detail.input.x[0])!='undefined'))
        {
            $scope.subNumber=$scope.detail.input.x[0].length;
            $scope.subNumberArray.length=$scope.subNumber;
        }

    }


    $scope.changeNumber=function(){


        if($scope.number>=0)
        {
            $scope.numberArray.length=$scope.number;
            if($scope.number<$scope.detail.input.time.length||$scope.number<$scope.detail.input.x.length)
            {
                $scope.detail.input.time=$scope.detail.input.time.slice(0,$scope.number);
                if($scope.type!="XS"&&$scope.type!="XR")
                {
                    $scope.detail.input.x=$scope.detail.input.x.slice(0,$scope.number);
                }
                else{
                    var temp=[];
                    for(var j=0;j<$scope.number;j++)
                    {
                        temp[j]=$scope.detail.input.x[j];
                        console.log(temp[j]);
                    }
                    $scope.detail.input.x=temp;
                }
            }
            else if($scope.type=="XS"||$scope.type=="XR")
            {

                var addn=Math.max($scope.number-$scope.detail.input.time.length,
                    $scope.number-$scope.detail.input.x.length);
                var tail=Math.max($scope.detail.input.time.length,$scope.detail.input.x.length);
                for(var i=0;i<addn;i++)
                {
                    $scope.detail.input.x[tail+i]=[];
                    $scope.detail.input.time[tail+i]='';
                }
            }

        }
        else{
            alert("数组数为正数");
        }

    };

    $scope.changeSubNumber=function(){
        console.log($scope.subNumber);
        if($scope.subNumber>=0)
        {
            $scope.subNumberArray.length=$scope.subNumber;
            if(typeof($scope.detail.input.x[0]) !="undefined")
            {
                if($scope.subNumber<$scope.detail.input.x[0].length)
                {
                    for(var i=0;i<$scope.number;i++)
                    {
                        $scope.detail.input.x[i]=$scope.detail.input.x[i].slice(0,$scope.subNumber);
                    }
                }

            }

        }
        else{
            alert("子组数为正数");
        }
    };

    var rest=function(message,postOrCreate){
        var postJSON={
            id:'',
            name:'',
            project:''
        };
        postJSON.id=$scope.detail.id;
        postJSON.name=$scope.detail.name;
        postJSON.project=$scope.spc.selectProject.name;
        if(postJSON.name==''||typeof (postJSON.project)=='undefined')
        {
            $scope.spc.tips("提交失败，请填写完整信息！");
            return ;
        }
       /* if($stateParams.type=="C")
        {
            postJSON.inputC=$scope.detail.input;
        }*/

        postJSON["input"+ $scope.type]=$scope.detail.input;

        var url="";
        if(postOrCreate=="edit")
        {
            url="/"+$scope.detail.id;
        }

        RestServerce.post("api/spc/"+ $scope.type+url,postJSON).then(
            function(data){
                if(typeof(data.error)!='undefined')
                {
                    $scope.spc.tips(data.error);
                }
                else{
                    $scope.spc.getSPCListByProject();
                    $scope.spc.tips(message);
                    $scope.detail=angular.copy(data);
                    controlPlotByType();

                }
            },function(error){
                $scope.spc.tips(error);
            });

    };

    $scope.update=function(){
        rest("更新成功!","edit");
    };

    $scope.create=function(){
        rest("新建成功!","create");
    };
    function controlPlotByType(){
        if( $scope.type=="C")
        {
            controlPlot($scope.detail.name,$scope.detail.output.time,$scope.detail.input.timeName
                ,$scope.detail.output.x,$scope.detail.input.xName,$scope.detail.output.cUCL,
                $scope.detail.output.cCL,$scope.detail.output.cLCL,$scope.detail.type,'',"controlPlot");
        }
        else if( $scope.type=="XmR")
        {
            controlPlot($scope.detail.name,$scope.detail.output.time,$scope.detail.input.timeName
                ,$scope.detail.output.x,$scope.detail.input.xName,$scope.detail.output.xUCL,
                $scope.detail.output.xCL,$scope.detail.output.xLCL,$scope.detail.type,"单值","controlPlot");
            controlPlot($scope.detail.name,$scope.detail.output.time,$scope.detail.input.timeName
                ,$scope.detail.output.mr,$scope.detail.input.mrName,$scope.detail.output.mrUCL,
                $scope.detail.output.mrCL,$scope.detail.output.mrLCL,$scope.detail.type,"移动极差","controlPlot2");
        }
        else if( $scope.type=="Z")
        {
            controlPlot($scope.detail.name,$scope.detail.output.time,$scope.detail.input.timeName
                ,$scope.detail.output.x,$scope.detail.input.xName+"的离度（以西格玛单位）","",
                "","",$scope.detail.type,"","controlPlot");

        }
        else if($scope.type=="U")
        {
            controlPlot($scope.detail.name,$scope.detail.output.time,$scope.detail.input.timeName
                ,$scope.detail.output.x,$scope.detail.input.xName,$scope.detail.output.uUCL,
                $scope.detail.output.uCL,$scope.detail.output.uLCL,$scope.detail.type,"","controlPlot");
        }
        else if($scope.type=="XR"){
            controlPlot($scope.detail.name,$scope.detail.output.time,$scope.detail.input.timeName
                ,$scope.detail.output.x,$scope.detail.input.xName,$scope.detail.output.xUCL,
                $scope.detail.output.xCL,$scope.detail.output.xLCL,$scope.detail.type,"单值","controlPlot");
            controlPlot($scope.detail.name,$scope.detail.output.time,$scope.detail.input.timeName
                ,$scope.detail.output.r,$scope.detail.input.rName,$scope.detail.output.rUCL,
                $scope.detail.output.rCL,$scope.detail.output.rLCL,$scope.detail.type,$scope.detail.input.rName,"controlPlot2");
        }
        else{
            controlPlot($scope.detail.name,$scope.detail.output.time,$scope.detail.input.timeName
                ,$scope.detail.output.x,$scope.detail.input.xName,$scope.detail.output.xUCL,
                $scope.detail.output.xCL,$scope.detail.output.xLCL,$scope.detail.type,"单值","controlPlot");
            controlPlot($scope.detail.name,$scope.detail.output.time,$scope.detail.input.timeName
                ,$scope.detail.output.s,$scope.detail.input.sName,$scope.detail.output.sUCL,
                $scope.detail.output.sCL,$scope.detail.output.sLCL,$scope.detail.type,$scope.detail.input.sName,"controlPlot2");
        }
    }
    function controlPlot(name,x,xname,y,yname,ucl,cl,lcl,type,typeSub,divid){
        var myChart = echarts.init(document.getElementById(divid));

        var xlength = x.length;

        var uclA=[];
        var lclA=[];

      /*  for(var i=0;i<xlength;i++)
        {
            uclA.push(ucl);
            clA.push(cl);
            lclA.push(lcl);
        }*/
        Array.prototype.max = function(){
            return Math.max.apply({},this)
        };
        Array.prototype.min = function(){
            return Math.min.apply({},this)
        };

        var tempArray=[];
        y.forEach(function(e){
            //利用js的弱类型转化
            tempArray.push(e-0);
        });
        y=tempArray;
        var max='';
        var min='';
        if(type=="U")
        {

            ucl.forEach(function(e){
                //利用js的弱类型转化
                uclA.push(e-0);
            });
            lcl.forEach(function(e){
                //利用js的弱类型转化
                lclA.push(e-0);
            });

            max=Math.max(uclA.max(),y.max());
            min=Math.min(lclA.min(),y.min());


        }
        else if(type=="Z")
        {
            max=y.max();
            min=y.min();
        }
        else
        {
            max=Math.max(ucl, y.max());
            min=Math.min(lcl, y.min());
        }



        var option={
            title : {
                text: name,
                subtext:  type+typeSub+"控制图"
            },
            toolbox: {
                show : true,
                feature : {
                    dataView : {show: true,
                        lang: ['<strong>'+name+'——数据视图</strong>', '关闭'],
                        readOnly:true,
                        optionToContent: function(opt) {
                            var axisData = opt.xAxis[0].data;
                            var name= opt.xAxis[0].name;
                            var series = opt.series;
                            var table='<p><strong>UCL:</strong>' +ucl+'<br/>'+
                                '<strong>CL:</strong>' +cl +'<br/>'+
                                '<strong>LCL:</strong>' +lcl +
                                '</p>';
                            table += '<table style="width:100%;text-align:center"><tbody><tr>'
                                + '<td><strong>'+name+'</strong></td>'
                                + '<td><strong>' + series[0].name + '</strong></td>'
                                + '</tr>';
                            for (var i = 0, l = axisData.length; i < l; i++) {
                                table += '<tr>'
                                    + '<td>' + axisData[i] + '</td>'
                                    + '<td>' + series[0].data[i] + '</td>'
                                    + '</tr>';
                            }
                            table += '</tbody></table>';

                            return table;
                        }


                    },
                    dataZoom : {
                        show : true,
                        title : {
                            dataZoom : '区域缩放',
                            dataZoomReset : '区域缩放-后退'
                        }
                    },
                    saveAsImage : {show: true}
                }
            },
            legend: {
                orient: 'horizontal', // 'vertical'
                x: 'center', // 'center' | 'left' | {number},
                y: 'top', // 'center' | 'bottom' | {number}
                backgroundColor: '#eee',
                borderWidth: 2,
                padding: 10,    // [5, 10, 15, 20]
                itemGap: 20,
                data: [
                    yname,
                    'UCL','CL', 'LCL'
                ]
            },
            tooltip : {
                trigger: 'item '
            },
            xAxis : [
                {
                    name:xname,
                    type : 'category',
                    data :  x
                }
            ],
            yAxis : [
                {
                    name:yname,
                    type : 'value',
                    scale: true,
                    min:min,
                    max:max
                }
            ],
            symbolList:
                [
                'circle', '', '', ''
                ]
             ,
            series : [
                {
                    name: yname,
                    type:'line',
                    data:y
                }
               ,
                {
                    name: "UCL",
                    type: 'line',
                    data:[],
                    markLine: {

                        data: [
                            [
                                { value: ucl, xAxis: -1, yAxis: ucl},
                                { xAxis: xlength , yAxis: ucl}
                            ]

                        ]

                    }
                }
                ,
                {
                    name: "CL",
                    type: 'line',
                    data:[],
                    markLine: {
                        itemStyle: {
                            normal: {
                                lineStyle: {
                                    type: "solid"
                                }
                            }
                        },
                        data: [
                            [
                                { value: cl, xAxis: -1, yAxis: cl},
                                { xAxis: xlength , yAxis: cl}
                            ]
                        ]

                    }
                },

                {
                    name: "LCL",
                    type: 'line',
                    data:[],
                    markLine: {
                        data: [
                            [
                                { value: lcl, xAxis: -1, yAxis: lcl},
                                { xAxis: xlength, yAxis: lcl}
                            ]
                        ]

                    }
                }

            ]
        };

        // 为echarts对象加载数据

        if(type=="Z")
        {
            var optionZ=angular.copy(option);
            optionZ.toolbox.feature.dataView= {
                show: true,
                lang: ['<strong>' + name + '——数据视图</strong>', '关闭'],
                readOnly: true,
                optionToContent: function (opt) {
                    var axisData = opt.xAxis[0].data;
                    var name = opt.xAxis[0].name;
                    var series = opt.series;
                    var table = '<table style="width:100%;text-align:center"><tbody><tr>'
                        + '<td><strong>' + name + '</strong></td>'
                        + '<td><strong>' + series[0].name + '</strong></td>'
                        + '</tr>';
                    for (var i = 0, l = axisData.length; i < l; i++) {
                        table += '<tr>'
                            + '<td>' + axisData[i] + '</td>'
                            + '<td>' + series[0].data[i] + '</td>'
                            + '</tr>';
                    }
                    table += '</tbody></table>';

                    return table;
                }
            };
            /*optionZ.yAxis.scale=false;*/
            optionZ.legend.data=[
                yname,
                '西格玛单位控制线'
            ];

            optionZ.series=[
                {
                    name: yname,
                    type:'line',
                    data:y
                }
                ,
                {
                    name: "西格玛单位控制线",
                    type: 'line',
                    data:[],
                    markLine: {

                        data:(function(){
                            //动态生成相应的控制线
                            var data=[];
                            for(var i=parseInt(Math.max(-3,min));i<=parseInt(Math.min(3,max));i++)
                            {
                                var dataItem=[
                                    { value: '', xAxis: -1, yAxis: ''},
                                    { xAxis: xlength , yAxis: ''}
                                ];

                                dataItem[0].value=i;
                                dataItem[0].yAxis=i;
                                dataItem[1].yAxis=i;
                                data.push(dataItem);
                            }
                            return data;

                        })()
                    }
                }
            ];
            myChart.setOption(optionZ);
        }
        else if(type=="U")
        {
            var optionU=angular.copy(option);
            optionU.toolbox.feature.dataView= {
                show: true,
                lang: ['<strong>' + name + '——数据视图</strong>', '关闭'],
                readOnly: true,
                optionToContent: function (opt) {
                    var axisData = opt.xAxis[0].data;
                    var name = opt.xAxis[0].name;
                    var series = opt.series;
                    var table='<p>'+
                        '<strong>CL:</strong>' +cl +'<br/>'+
                        '</p>';
                    table += '<table style="width:100%;text-align:center"><tbody><tr>'
                        + '<td><strong>' + name + '</strong></td>'
                        + '<td><strong>' + series[0].name + '</strong></td>'
                        + '<td><strong>' + 'UCL' + '</strong></td>'
                        + '<td><strong>' + 'LCL' + '</strong></td>'
                        + '</tr>';
                    for (var i = 0, l = axisData.length; i < l; i++) {
                        table += '<tr>'
                            + '<td>' + axisData[i] + '</td>'
                            + '<td>' + series[0].data[i] + '</td>'
                            + '<td>' + ucl[i] + '</td>'
                            + '<td>' + lcl[i] + '</td>'
                            + '</tr>';
                    }
                    table += '</tbody></table>';

                    return table;
                }
            };
            optionU.series=[
                {
                    name: yname,
                    type:'line',
                    data:y
                }
                ,
                {
                    name: "UCL",
                    type: 'line',
                    data:[],
                    tooltip : {
                        trigger: 'item '
                    },
                    markLine: {
                        itemStyle: {
                            normal: {
                                lineStyle: {
                                    type: "solid"
                                },
                                label:{
                                    show: false
                                }
                            }

                        },
                        symbol: [
                            'circle', 'circle'
                        ],
                        symbolSize:0.5,
                   /*     large:true,*/
                        data: (function(){

                            var data=[];
                            for(var i=0;i<xlength;i++)
                            {
                                var dataItem=[
                                    { value: '', xAxis: '', yAxis: ''},
                                    { xAxis: '' , yAxis: ''}
                                ];
                                var dataItem2=[
                                    { value: '', xAxis: '', yAxis: ''},
                                    { xAxis: '' , yAxis: ''}
                                ];

                                dataItem[0].value=ucl[i];
                                dataItem[0].xAxis=i-0.5;
                                dataItem[0].yAxis=ucl[i];
                                dataItem[1].xAxis=i+0.5;
                                dataItem[1].yAxis=ucl[i];
                                data.push(dataItem);

                                if((i+1)<xlength)
                                {
                                    dataItem2[0].value="";
                                    dataItem2[0].xAxis=i+0.5;
                                    dataItem2[0].yAxis=ucl[i];
                                    dataItem2[1].xAxis=i+0.5;
                                    dataItem2[1].yAxis=ucl[i+1];
                                    data.push(dataItem2);

                                }

                            }
                            return data;

                        })()

                    }
                }
                ,
                {
                    name: "CL",
                    type: 'line',
                    data:[],
                    markLine: {
                        itemStyle: {
                            normal: {
                                lineStyle: {
                                    type: "solid"
                                }
                            }
                        },
                        data: [
                            [
                                { value: cl, xAxis: -1, yAxis: cl},
                                { xAxis: xlength , yAxis: cl}
                            ]
                        ]

                    }
                },

                {
                    name: "LCL",
                    type: 'line',
                    data:[],
                    markLine: {
                        itemStyle: {
                            normal: {
                                lineStyle: {
                                    type: "solid"
                                },
                                label:{
                                    show: false
                                }
                            }

                        },
                        symbol: [
                            'circle', 'circle'
                        ],
                        symbolSize:0.5,
                        data: (function(){

                            var data=[];
                            for(var i=0;i<xlength;i++)
                            {
                                var dataItem=[
                                    { value: '', xAxis: '', yAxis: ''},
                                    { xAxis: '' , yAxis: ''}
                                ];
                                var dataItem2=[
                                    { value: '', xAxis: '', yAxis: ''},
                                    { xAxis: '' , yAxis: ''}
                                ];

                                dataItem[0].value=lcl[i];
                                dataItem[0].xAxis=i-0.5;
                                dataItem[0].yAxis=lcl[i];
                                dataItem[1].xAxis=i+0.5;
                                dataItem[1].yAxis=lcl[i];
                                data.push(dataItem);

                                if((i+1)<xlength)
                                {
                                    dataItem2[0].value="";
                                    dataItem2[0].xAxis=i+0.5;
                                    dataItem2[0].yAxis=lcl[i];
                                    dataItem2[1].xAxis=i+0.5;
                                    dataItem2[1].yAxis=lcl[i+1];
                                    data.push(dataItem2);

                                }

                            }
                            return data;

                        })()

                    }
                }

            ];
            myChart.setOption(optionU);
        }
        else{
            myChart.setOption(option);
        }
    }
}]);

app.filter("arrayDisplay", function() {
    return  function(e) {
        var out='';
        e.forEach(
            function(e){
                out+=e+',';
            }
        );

        return out.slice(0,out.length-1);
    }

});