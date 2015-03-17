/**
 * Created by x on 2015/3/14.
 */
var app=angular.module('qpmtools.mc.app',['ngAnimate','ui.router','qpmtools.systemRest']);

app.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/mc');

    $stateProvider

        .state('mc', {
            url: '/mc',
            templateUrl: 'mc/mc.html',
            controller: 'MCHomeController'
        })
        .state('mc.detail', {
            url: '/detail/{id}',
            templateUrl: 'mc/detail.html',
            controller: 'DetailController'
        })
        .state('mc.edit', {
            url: '/edit/{id}',
            templateUrl: 'mc/edit.html',
            controller: 'DetailController'
        })
        .state('mc.create', {
            url: '/create',
            templateUrl: 'mc/edit.html',
            controller: 'DetailController'
        });
});

app.controller('MCHomeController',['$scope','RestServerce'
    ,function($scope,RestServerce){

        $scope.mcG={
            organizationList:'',
            projectList:'',
            selectOrganization:'',
            selectProject:'',
            mcList:[],
            message:'',
            state:''};
        //页面加载的时候获取组织列表
        RestServerce.get("api/system/organization/list").then(
            function(data){

                $scope.mcG.organizationList=data.list;

            },function(error){});

        //选择组织后,访问后台获取相应所属项目
        $scope.choseO=function(){
            $scope.mcG.selectProject==null;
            $scope.mcG.mcList='';
            if($scope.mcG.selectOrganization!=null)
            {
                var url=$scope.mcG.selectOrganization.name;

                RestServerce.get("api/system/project/list/"+url).then(
                    function(data){
                        $scope.mcG.projectList=data.list;
                    },function(error){});
            }
            else{
                $scope.mcG.mcList='';
            }

        };

        //选择项目后,访问后台获取相应mcList(nameList)
        $scope.choseP=function(){

            if($scope.mcG.selectProject!=null)
            {
                $scope.mcG.getMCListByProject();
            }
            else{
                $scope.mcG.mcList=[];
            }



        };

        $scope.mcG.getMCListByProject=function(){

            RestServerce.get("api/mc/list/"+$scope.mcG.selectProject.name).then(
                function(data){
                    $scope.mcG.mcList=data;
                },function(error){
                    $scope.mcG.mcList=[];
                });

        };




        $scope.mcG.tips=function(message){
            $scope.mcG.message=message;
            setTimeout(function(){
                $scope.mcG.message="";
                $scope.$apply($scope.mcG.message);
            },2000);
        };

        $scope.mcG.remove=function(id){
            RestServerce.remove("api/mc/"+id).then(
                function(data){

                    $scope.mcG.getMCListByProject();
                    $scope.mcG.tips("删除成功！");

                },function(error){
                    alert(error);
                });
        };

    }]);

app.controller('DetailController',['$scope','$stateParams','RestServerce','$state',function($scope,$stateParams,RestServerce,$state){

    $scope.detail={
        name:'',
        project:{name:''},
        mcParam:{
            predictionName:'',
            predictionValue:'',
            confidenceInterval:'',
            simulationNumber:'',
            mcAssumptionParamList:[],
            mcNormalParamList:[]
        },
        result:[]

    };

    $scope.mcG.state=$state.current.name;
    var getDetail=function()
    {

        if(typeof($scope.mcG.mcList)!='undefined')
        {

            $scope.mcG.mcList.forEach(
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

    var rest=function(url,message){


        RestServerce.post(url+$scope.detail.id,$scope.detail).then(
            function(data){

                $scope.mcG.getMCListByProject();
                $scope.mcG.tips(message);
                $scope.detail=angular.copy(data);
                mcD3.compute($scope.detail.result,800,500,$scope.detail.mcParam.simulationNumber);
            },function(error){
                alert(error.error);
            });

    };

    $scope.update=function(){

        rest("api/mc/","更新成功!");
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

    };

}]);