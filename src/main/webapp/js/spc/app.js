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
            url: '/detail/{id}',
            templateUrl: 'spc/detail.html',
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
            state:'null'
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





     /*   $scope.spc.tips=function(message){
            $scope.spc.message=message;
            setTimeout(function(){
                $scope.spc.message="";
                $scope.$apply($scope.spc.message);
            },2000);
        };

        $scope.spc.remove=function(id){
            RestServerce.remove("api/mc/"+id).then(
                function(data){

                    $scope.spc.getspcListByProject();
                    $scope.spc.tips("删除成功！");

                },function(error){
                    alert(error);
                });
        };*/

    }]);



app.controller('SPCDetailController',['$scope','$stateParams','RestServerce','$state',function($scope,$stateParams,RestServerce,$state){

    $scope.detail={
        name:'',
        project:{name:''},
        input:{},
        output:{}

    };

    var getDetail=function()
    {

        if(typeof($scope.spc.spcList)!='undefined')
        {

            $scope.spc.spcList.forEach(
                function(d){
                    if(d.id==$stateParams.id)
                    {
                        $scope.detail=angular.copy(d);

                    }

                }
            );

        }

    };

    getDetail();

    spcD3.size(1000,500,50).C($scope.detail.output,"#svg1");




   /* var rest=function(url,message){


        RestServerce.post(url+$scope.detail.id,$scope.detail).then(
            function(data){

                $scope.mcG.getMCListByProject();
                $scope.mcG.tips(message);
                $scope.detail=angular.copy(data);
                mcD3.size(1000,500).compute($scope.detail.result,$scope.detail.mcParam.simulationNumber);
            },function(error){
                alert(error.error);
            });

    };

    $scope.update=function(){

        rest("api/mc/","更新成功!");
    };

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


app.filter('spcTypeFormat',function(){
    return function(e){

        return e.substring(3);
    }
});