/**
 * Created by x on 2015/7/10.
 */
var app=angular.module('qpmtools.asset.app',['ngAnimate','ui.router','qpmtools.systemRest']);

app.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/asset');

    $stateProvider

        .state('asset', {
            url: '/asset',
            templateUrl: 'asset/asset.html',
            controller: 'AssetHomeController'
        })
        .state('asset.detail', {
            url: '/detail/{id}',
            templateUrl: 'asset/detail.html',
            controller: 'AssetDetailController'
        });
});

app.controller('AssetHomeController',['$scope','RestServerce'
    ,function($scope,RestServerce){

        $scope.asset={
            api:'',
            assetList:[],
            message:'',
            state:''};

        $scope.getAsset=function(){
            if($scope.asset.api=='test')
            {
                //测试数据
                $scope.asset.assetList=[];

            }
            else{
                RestServerce.get($scope.asset.api).then(
                    function(data){
                        $scope.asset.assetList=data;
                    },function(error){
                        scope.asset.tips(error);
                    });

            }
        };


        $scope.asset.tips=function(message){
            $scope.asset.message=message;
            setTimeout(function(){
                $scope.asset.message="";
                $scope.$apply($scope.asset.message);
            },2000);
        };

        /*$scope.asset.remove=function(id){
            RestServerce.remove("api/asset/"+id).then(
                function(data){

                    $scope.asset.getassetListByProject();
                    $scope.asset.tips("删除成功！");

                },function(error){
                    alert(error);
                });
        };*/

    }]);

app.controller('AssetDetailController',['$scope','$stateParams','RestServerce','$state',function($scope,$stateParams,RestServerce,$state){



}]);