/**
 * Created by x on 2015/3/14.
 */
var app=angular.module('qpmtools.pmTag.app',['ngAnimate','ui.router','qpmtools.systemRest']);

app.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/pm/tag');

    $stateProvider

        .state('pmTag', {
            url: '/pm/tag',
            templateUrl: 'pm/tag/tag.html',
            controller: 'tagHomeController'
        })
        .state('pmTag.detail', {
            url: '/detail/{id}',
            templateUrl: 'pm/tag/detail.html',
            controller: 'tagDetailController'
        })
        .state('pmTag.edit', {
            url: '/edit/{id}',
            templateUrl: 'pm/tag/edit.html',
            controller: 'tagDetailController'
        })
        .state('pmTag.create', {
            url: '/create',
            templateUrl: 'pm/tag/edit.html',
            controller: 'tagDetailController'
        });
});

app.controller('tagHomeController',['$scope','RestServerce'
    ,function($scope,RestServerce){

        $scope.tagG={
            tagList:[],
            message:'',
            state:'',
            m:'test'};

        $scope.tagG.getTagList=function(){

            RestServerce.get("api/pm/tag/list").then(
                function(data){
                    $scope.tagG.tagList=data;
                },function(error){
                    console.log(error);
                    $scope.tagG.tagList=[];
                });

        };
        $scope.tagG.getTagList();

        $scope.tagG.tips=function(message){
            $scope.tagG.message=message;
            setTimeout(function(){
                $scope.tagG.message="";
                $scope.$apply($scope.tagG.message);
            },2000);
        };

        $scope.remove=function(id){
            console.log("id",id);
            RestServerce.remove("api/pm/tag/"+id).then(
                function(data){
                    console.log(data);
                    $scope.tagG.getTagList();
                    $scope.tagG.tips("删除成功！");

                },function(error){
                   /* console.log(error);
                    $scope.tagG.tips(error);*/
                });
        };

    }]);

app.controller('tagDetailController',['$scope','$stateParams','RestServerce','$state',function($scope,$stateParams,RestServerce,$state){

    $scope.detail={
        name:'',
        description:''
    };

    $scope.tagG.state=$state.current.name;
    var getDetail=function()
    {

        if(typeof($scope.tagG.tagList)!='undefined'&&$scope.tagG.tagList.length!=0)
        {

            $scope.tagG.tagList.forEach(
                function(d){
                    if(d.id==$stateParams.id)
                    {
                        $scope.detail=angular.copy(d);

                    }

                }
            );

        }
        else
        {
            $state.go("pmTag");
        }

    };
    if( $scope.tagG.state!="pmTag.create")
    {
        getDetail();
    }


    var rest=function(url,message){


        RestServerce.post(url+$scope.detail.id,$scope.detail).then(
            function(data){

                $scope.tagG.getTagList();
                console.log(message);
                $scope.tagG.tips(message);
                $scope.detail=angular.copy(data);

            },function(error){
                console.log(error);
                $scope.tagG.tips(error);
            });

    };

    $scope.update=function(){

        rest("api/pm/tag/","更新成功!");
    };



    $scope.create=function(){

        RestServerce.post("api/pm/tag",$scope.detail).then(
            function(data){

                $scope.tagG.getTagList();
                $scope.tagG.tips("创建成功!");
                $scope.detail=angular.copy(data);

            },function(error){
                console.log(error);
                $scope.tagG.tips(error);
            });

    };


}]);