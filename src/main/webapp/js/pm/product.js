/**
 * Created by x on 2015/3/14.
 */
var app=angular.module('qpmtools.pmProduct.app',['ngAnimate','ui.router','qpmtools.systemRest']);

app.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/pm/product');

    $stateProvider

        .state('pmProduct', {
            url: '/pm/product',
            templateUrl: 'pm/product/product.html',
            controller: 'productHomeController'
        })
        .state('pmProduct.detail', {
            url: '/detail/{id}',
            templateUrl: 'pm/product/detail.html',
            controller: 'productDetailController'
        })
        .state('pmProduct.edit', {
            url: '/edit/{id}',
            templateUrl: 'pm/product/edit.html',
            controller: 'productDetailController'
        })
        .state('pmProduct.create', {
            url: '/create',
            templateUrl: 'pm/product/edit.html',
            controller: 'productDetailController'
        });
});

app.controller('productHomeController',['$scope','RestServerce'
    ,function($scope,RestServerce){

        $scope.productG={
            productList:[],
            message:'',
            state:'',
            m:'test'};

        $scope.productG.getproductList=function(){

            RestServerce.get("api/pm/product/list").then(
                function(data){
                    $scope.productG.productList=data;
                },function(error){
                    console.log(error);
                    $scope.productG.productList=[];
                });

        };
        $scope.productG.getproductList();

        $scope.productG.tips=function(message){
            $scope.productG.message=message;

            setTimeout(function(){
                $scope.productG.message="";
                $scope.$apply($scope.productG.message);
            },2000);
        };

        $scope.productG.errors=function(message){
            if(message=="NameDuplicateKey")
            {
                message="命名冲突,工作产品名称已存在,请换一个名称新建工作产品!"
            }
            $scope.productG.message=message;
            setTimeout(function(){
                $scope.productG.message="";
                $scope.$apply($scope.productG.message);
            },10000);
        };

        $scope.remove=function(id){
            console.log("id",id);
            RestServerce.remove("api/pm/product/"+id).then(
                function(data){
                    console.log(data);
                    $scope.productG.getproductList();
                    $scope.productG.tips("删除成功！");

                },function(error){
                   /* console.log(error);
                    $scope.productG.tips(error);*/
                });
        };

    }]);

app.controller('productDetailController',['$scope','$stateParams','RestServerce','$state',function($scope,$stateParams,RestServerce,$state){

    $scope.detail={
        name:'',
        description:''
    };

    $scope.productG.state=$state.current.name;
    var getDetail=function()
    {

        if(typeof($scope.productG.productList)!='undefined'&&$scope.productG.productList.length!=0)
        {

            $scope.productG.productList.forEach(
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
            $state.go("pmProduct");
        }

    };
    if( $scope.productG.state!="pmProduct.create")
    {
        getDetail();
    }


    var rest=function(url,message){


        RestServerce.post(url+$scope.detail.id,$scope.detail).then(
            function(data){

                $scope.productG.getproductList();
                console.log(message);
                $scope.productG.tips(message);
                $scope.detail=angular.copy(data);

            },function(error){
                console.log(error);
                $scope.productG.tips(error);
            });

    };

    $scope.update=function(){

        rest("api/pm/product/","更新成功!");
    };



    $scope.create=function(){

        var fd = new FormData();
        fd.append("product",JSON.stringify($scope.detail));
        fd.append("file-data", document.getElementById('fileData').files[0]);
        $.ajax({

            contentType: false,

            type: "POST",

            url: "api/pm/product",

            data: fd,

            processData: false, //不用Jquery转化data

            success: function(data){

                $scope.productG.getproductList();
                $scope.productG.tips("新建成功!");
                $scope.detail=angular.copy(data);

            },

            error:function (e) {
                $scope.$apply(function () {
                    $scope.productG.errors(e.responseText);
                });

            }

        });

    };


}]);