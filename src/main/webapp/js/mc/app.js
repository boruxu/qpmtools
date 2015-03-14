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
            message:''};
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

    }]);
