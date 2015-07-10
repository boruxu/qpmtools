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
    $scope.number="";
    $scope.numberArray=[];


    //跟新页面的状态信息
    $scope.spc.state=$state.current.name;

    var start=function()
    {
        if(typeof($scope.spc.spcList)!='undefined'&&$scope.spc.spcList.length!=0
            &&($scope.spc.state=="spc.edit"||$scope.spc.state=="spc.detail"))
        {
            $scope.spc.spcList.forEach(
                function(d){
                    if(d.id==$stateParams.id)
                    {
                        $scope.detail=angular.copy(d);
                    }

                }
            );
            //spcD3.size(1000,500,50).C($scope.detail.output,"#svg1");

            controlPlotByType();


        }
        //单独刷新时，返回上一级
        else if($scope.spc.state=="spc.create")
        {
            //初始化c图数据格式
            $scope.detail={
                name:'',
                input:{
                    x:[],
                    xName:'',
                    time:[],
                    timeName:[]
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

    };

    start();

    $scope.changeNumber=function(){
        if($scope.number>=0)
        {
            $scope.numberArray.length=$scope.number;
            if($scope.number<$scope.detail.input.time.length)
            {
                $scope.detail.input.time=$scope.detail.input.time.slice(0,$scope.number);
                $scope.detail.input.x=$scope.detail.input.x.slice(0,$scope.number);
            }
        }
        else{
            alert("数组数为正数");
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

        postJSON["input"+$stateParams.type]=$scope.detail.input;

        var url="";
        if(postOrCreate=="edit")
        {
            url="/"+$scope.detail.id;
        }

        RestServerce.post("api/spc/"+$stateParams.type+url,postJSON).then(
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
        if($stateParams.type=="C")
        {
            controlPlot($scope.detail.name,$scope.detail.output.time,$scope.detail.input.timeName
                ,$scope.detail.output.x,$scope.detail.input.xName,$scope.detail.output.cUCL,
                $scope.detail.output.cCL,$scope.detail.output.cLCL,$scope.detail.type,"controlPlot");
        }
        else if($stateParams.type=="XmR")
        {
            controlPlot($scope.detail.name,$scope.detail.output.time,$scope.detail.input.timeName
                ,$scope.detail.output.x,$scope.detail.input.xName,$scope.detail.output.xUCL,
                $scope.detail.output.xCL,$scope.detail.output.xLCL,$scope.detail.type+"单值","controlPlot");
            controlPlot($scope.detail.name,$scope.detail.output.time,$scope.detail.input.timeName
                ,$scope.detail.output.mr,$scope.detail.input.mrName,$scope.detail.output.mrUCL,
                $scope.detail.output.mrCL,$scope.detail.output.mrLCL,$scope.detail.type+"移动极差","controlPlot2");
        }
    }
    function controlPlot(name,x,xname,y,yname,ucl,cl,lcl,type,divid){
        var myChart = echarts.init(document.getElementById(divid));

        var xlength = x.length;

        var uclA=[];
        var clA=[];
        var lclA=[];

      /*  for(var i=0;i<xlength;i++)
        {
            uclA.push(ucl);
            clA.push(cl);
            lclA.push(lcl);
        }*/

        var option={
            title : {
                text: name,
                subtext:  type+"控制图"
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
                    scale: true
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
                    data:uclA,
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
                    data:clA,
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
                    data:lclA,
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
        myChart.setOption(option);

    }

    /*
     //获取mc模拟数据
     $scope.get=function(){

         RestServerce.get("api/mc/"+$scope.detail.id).then(
             function(data){
                 $scope.detail=angular.copy(data);
                 mcD3.size(1000,500).compute($scope.detail.result,$scope.detail.mcParam.simulationNumber);
             },function(error){
                 alert(error.error);
             });
     };



     $scope.addN=function(){
         var mcN={
             name:"",
             value:""
         };
         if(typeof($scope.detail.mcParam.mcNormalParamList)=='undefined')
         {
             $scope.detail.mcParam.mcNormalParamList=[];
         }
         $scope.detail.mcParam.mcNormalParamList.push(mcN);

     };
     $scope.removeN=function(index){

         $scope.detail.mcParam.mcNormalParamList.splice(index,1);

     };
     $scope.addA=function(){
         var mcA={
             name:"",
             type:"",
             distributionParam:[0,0,0]
         };
         if(typeof($scope.detail.mcParam.mcNormalParamList)=='undefined')
         {
             $scope.detail.mcParam.mcNormalParamList=[];
         }
         $scope.detail.mcParam.mcAssumptionParamList.push(mcA);

     };
     $scope.removeA=function(index){

         $scope.detail.mcParam.mcAssumptionParamList.splice(index,1);

     };*/

}]);

