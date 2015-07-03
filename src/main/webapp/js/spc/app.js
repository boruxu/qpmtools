/**
 * Created by BoruXU on 2015/2/19
 * 本文件为spc工具angular路由js文件
 */
var app=angular.module('qpmtools.spc.router',['ngAnimate','ui.router']);

app.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/spc/repository');

    $stateProvider

        .state('spc', {
            url: '/spc',
            templateUrl: 'spc/spc.html',
            controller:'SPCHomeController'
        })
        .state('spc.type', {
            url: '/type',
            templateUrl: 'spc/repository.html'
        })
        .state('spc.input', {
            url: '/input',
            templateUrl: 'spc/input.html'
        })
        .state('spc.plot', {
            url: '/plot',
            templateUrl: 'spc/plot.html'
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
            state:''
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

app.filter('spcTypeFormat',function(){
    return function(e){

        return e.substring(3);
    }
});