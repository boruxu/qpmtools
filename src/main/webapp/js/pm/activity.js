/**
 * Created by x on 2015/3/14.
 */
var app=angular.module('qpmtools.pmActivity.app',['ngAnimate','ui.router','qpmtools.systemRest']);

app.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/pm/activity');

    $stateProvider

        .state('pmActivity', {
            url: '/pm/activity',
            templateUrl: 'pm/activity/activity.html',
            controller: 'activityHomeController'
        })
        .state('pmActivity.detail', {
            url: '/detail/{id}',
            templateUrl: 'pm/activity/detail.html',
            controller: 'activityDetailController'
        })
        .state('pmActivity.edit', {
            url: '/edit/{id}',
            templateUrl: 'pm/activity/edit.html',
            controller: 'activityDetailController'
        })
        .state('pmActivity.create', {
            url: '/create',
            templateUrl: 'pm/activity/edit.html',
            controller: 'activityDetailController'
        });
});

app.controller('activityHomeController',['$scope','RestServerce'
    ,function($scope,RestServerce){

        $scope.activityG={
            activityList:[],
            message:'',
            state:'',
            m:'test'};

        $scope.activityG.getactivityList=function(){

            RestServerce.get("api/pm/activity/list").then(
                function(data){
                    console.log("List",data);
                    data.forEach(function(e)
                    {
                       console.log(e.name);
                        console.log(e.fileName);
                        console.log(e.description);
                    });
                    $scope.activityG.activityList=data;
                },function(error){
                    console.log(error);
                    $scope.activityG.activityList=[];
                });

        };
        $scope.activityG.getactivityList();

        $scope.activityG.tips=function(message){
            $scope.activityG.message=message;

            setTimeout(function(){
                $scope.activityG.message="";
                $scope.$apply($scope.activityG.message);
            },2000);
        };

        $scope.activityG.errors=function(message){
            if(message=="NameDuplicateKey")
            {
                message="命名冲突,工作产品名称已存在,请换一个名称新建工作产品!"
            }
            $scope.activityG.message=message;
            setTimeout(function(){
                $scope.activityG.message="";
                $scope.$apply($scope.activityG.message);
            },10000);
        };

        $scope.remove=function(id){
            $.ajax({

                type: "DELETE",

                url: "api/pm/activity/" +id,

                success: function(data){

                    $scope.activityG.getactivityList();
                    $scope.activityG.tips("删除成功！");
                },

                error:function (e) {
                    $scope.$apply(function () {
                        $scope.activityG.errors(e.responseText);
                    });

                }

            });
        };
        $scope.download=function(id){
            console.log("download");
            var form = $("<form>");   //定义一个form表单
            form.attr('style', 'display:none');   //在form表单中添加查询参数
            form.attr('method', 'get');
            form.attr('id',"activityFileDown");
            form.attr('action',"api/pm/activity/file/"+id);

            $('body').append(form);  //将表单放置在web中
            form.submit();
            $('#activityFileDown').remove();
        };


    }]);

app.controller('activityDetailController',['$scope','$stateParams','RestServerce','$state',function($scope,$stateParams,RestServerce,$state){

    $scope.detail={
        name:'',
        description:''
    };

    $scope.activityG.state=$state.current.name;
    var getDetail=function()
    {

        if(typeof($scope.activityG.activityList)!='undefined'&&$scope.activityG.activityList.length!=0)
        {

            $scope.activityG.activityList.forEach(
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
            $state.go("pmActivity");
        }

    };
    if( $scope.activityG.state!="pmActivity.create")
    {
        getDetail();
    }



    $scope.update=function(){

        var fd = new FormData();
        fd.append("activity",JSON.stringify($scope.detail));
        fd.append("file-data", document.getElementById('fileData').files[0]);
        $.ajax({

            contentType: false,

            type: "POST",

            url: "api/pm/activity/" +$scope.detail.id,

            data: fd,

            processData: false, //不用Jquery转化data

            success: function(data){

                $scope.activityG.getactivityList();
                $scope.activityG.tips("更新成功!");
                $scope.detail=angular.copy(data);

            },

            error:function (e) {
                $scope.$apply(function () {
                    $scope.activityG.errors(e.responseText);
                });

            }

        });
    };



    $scope.create=function(){

        var fd = new FormData();
        fd.append("activity",JSON.stringify($scope.detail));
        fd.append("file-data", document.getElementById('fileData').files[0]);
        $.ajax({

            contentType: false,

            type: "POST",

            url: "api/pm/activity",

            data: fd,

            processData: false, //不用Jquery转化data

            success: function(data){

                $scope.activityG.getactivityList();
                $scope.activityG.tips("新建成功!");
                $scope.detail=angular.copy(data);

            },

            error:function (e) {
                $scope.$apply(function () {
                    $scope.activityG.errors(e.responseText);
                });

            }

        });

    };
    $scope.fileRemove=function(){
        $scope.detail.fileName=null;
        $scope.activityG.errors("删除文件必须要保证未选择新的文档并提交后才可生效！");
    }



}]);