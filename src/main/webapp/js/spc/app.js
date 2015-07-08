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
         /*
        $scope.spc.remove=function(id){
            RestServerce.remove("api/mc/"+id).then(
                function(data){

                    $scope.spc.getspcListByProject();
                    $scope.spc.tips("删除成功！");

                },function(error){
                    alert(error);
                });
        };*/

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
        if(typeof($scope.spc.spcList)!='undefined'&&$scope.spc.spcList.length!=0)
        {
            $scope.spc.spcList.forEach(
                function(d){
                    if(d.id==$stateParams.id)
                    {
                        $scope.detail=angular.copy(d);
                    }

                }
            );

            spcD3.size(1000,500,50).C($scope.detail.output,"#svg1");

            if($scope.spc.state=="spc.edit")
            {
                $scope.number=$scope.detail.input.x.length;
                $scope.numberArray.length=$scope.number;
            }

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


        }
        //单独刷新时，返回上一级
        else
        {
            $state.go("spc");
        }

    };

    start();


    var rest=function(message){
        var postJSON={
            id:'',
            name:'',
            project:'',
            inputC:''
        };
        postJSON.id=$scope.detail.id;
        postJSON.name=$scope.detail.name;
        postJSON.project=$scope.spc.selectProject.name;
        if($stateParams.type=="C")
        {
            console.log("type selec");
            postJSON.inputC=$scope.detail.input;
        }
        RestServerce.post("api/spc/"+$stateParams.type+"/"+$scope.detail.id,postJSON).then(
            function(data){

                $scope.spc.getSPCListByProject();
                $scope.spc.tips(message);
                $scope.detail=angular.copy(data);
                spcD3.size(1000,500,50).C($scope.detail.output,"#svg1");
            },function(error){
                $scope.spc.tips(error.error);
            });

    };

    $scope.update=function(){
        rest("更新成功!");
    };
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

    $scope.create=function(){

        RestServerce.post("api/mc",$scope.detail).then(
            function(data){

                $scope.mcG.getMCListByProject();
                $scope.mcG.tips("创建成功!");

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

