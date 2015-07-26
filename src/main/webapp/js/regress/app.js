/**
 * Created by BoruXU on 2015/2/19
 * 本文件为regress工具angular路由js文件
 */
var app=angular.module('qpmtools.regress.app',['ngAnimate','ui.router','qpmtools.systemRest']);

app.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/regress');

    $stateProvider

        .state('regress', {
            url: '/regress',
            templateUrl: 'regress/regress.html',
            controller:'regressHomeController'
        })
        .state('regress.detail', {
            url: '/detail/{id}',
            templateUrl: 'regress/detail.html',
            controller:'regressDetailController'
        })
        .state('regress.edit', {
            url: '/edit/{id}',
            templateUrl: 'regress/edit.html',
            controller:'regressDetailController'
        })
        .state('regress.create', {
            url: '/create',
            templateUrl: 'regress/edit.html',
            controller:'regressDetailController'
        });



});

app.controller('regressHomeController',['$scope','RestServerce'
    ,function($scope,RestServerce){

        //初始化regress
        $scope.regress={
            organizationList:'',
            projectList:'',
            selectOrganization:'',
            selectProject:'',
            regressList:[],
            message:'',
            state:'null',
            plotTypeList:[]
        };

        //页面加载的时候获取组织列表
        RestServerce.get("api/system/organization/list").then(
            function(data){

                $scope.regress.organizationList=data.list;

            },function(error){});

        //选择组织后,访问后台获取相应所属项目
        $scope.choseO=function(){
            $scope.regress.selectProject=null;
            $scope.regress.regressList='';
            if($scope.regress.selectOrganization!=null)
            {
                var url=$scope.regress.selectOrganization.name;

                RestServerce.get("api/system/project/list/"+url).then(
                    function(data){
                        $scope.regress.projectList=data.list;
                    },function(error){});
            }
            else{
                $scope.regress.regressList='';
            }

        };

        //选择项目后,访问后台获取相应regressList(nameList)
        $scope.choseP=function(){

            if($scope.regress.selectProject!=null)
            {
                $scope.regress.getregressListByProject();
            }
            else{
                $scope.regress.regressList=[];
            }



        };

        $scope.regress.getregressListByProject=function(){

            RestServerce.get("api/regress/list/"+$scope.regress.selectProject.name).then(
                function(data){
                    $scope.regress.regressList=data;
                },function(error){
                    $scope.regress.regressList=[];
                });

        };

        $scope.regress.tips=function(message){
            $scope.regress.message=message;
            setTimeout(function(){
                $scope.regress.message="";
                $scope.$apply($scope.regress.message);
            },2000);
        };

        $scope.remove=function(id){
            RestServerce.remove("api/regress/"+id).then(
                function(data){

                    $scope.regress.getregressListByProject();
                    $scope.regress.tips("删除成功！");

                },function(error){
                    $scope.regress.tips(error);
                });
        };


    }]);



app.controller('regressDetailController',['$scope','$stateParams','RestServerce','$state',function($scope,$stateParams,RestServerce,$state){

    $scope.detail={
        name:'',
        project:{name:''},
        input:{},
        output:{}
    };
    $scope.number=0;
    $scope.subNumber=0;
    $scope.numberArray=[];
    $scope.subNumberArray=[];



    //整个页面的类型
    $scope.type=$stateParams.type;


    //跟新页面的状态信息
    $scope.regress.state=$state.current.name;


    start();

    function start()
    {
        if(typeof($scope.regress.regressList)!='undefined'&&$scope.regress.regressList.length!=0
            &&($scope.regress.state=="regress.edit"||$scope.regress.state=="regress.detail"))
        {
            $scope.regress.regressList.forEach(
                function(d){
                    if(d.id==$stateParams.id)
                    {
                        $scope.detail=angular.copy(d);
                    }
                }
            );
        }
        //单独刷新时，返回上一级
        else if($scope.regress.state=="regress.create")
        {
            $scope.detail={
                name:'',
                input:{
                    x:[],
                    y:[],
                    yName:'',
                    xName:[]
                }
            }
        }
        else
        {
            $state.go("regress");
            return ;
        }

        $scope.number=$scope.detail.input.x.length;
        $scope.numberArray.length=$scope.number;
        if(typeof ($scope.detail.input.x[0])!='undefined') {
            $scope.subNumber=$scope.detail.input.x[0].length;
            $scope.subNumberArray.length=$scope.subNumber;
        }

    }


    $scope.changeNumber=function(){
        $scope.numberArray.length=$scope.number;
        if($scope.number>=0)
        {

            if($scope.number<$scope.detail.input.y.length||$scope.number<$scope.detail.input.x.length)
            {
                $scope.detail.input.y=$scope.detail.input.y.slice(0,$scope.number);

                var temp=[];
                for(var j=0;j<$scope.number;j++)
                {
                    temp[j]=$scope.detail.input.x[j];
                    console.log(temp[j]);
                }
                $scope.detail.input.x=temp;

            }
            else
            {

                var addn=Math.max($scope.number-$scope.detail.input.y.length,
                    $scope.number-$scope.detail.input.x.length);
                var tail=Math.max($scope.detail.input.y.length,$scope.detail.input.x.length);
                for(var i=0;i<addn;i++)
                {
                    $scope.detail.input.x[tail+i]=[];
                    $scope.detail.input.y[tail+i]='';
                }
            }



        }
        else{
            alert("数组数为正数");
        }

    };

    $scope.changeSubNumber=function(){

        if($scope.subNumber>=0)
        {
            $scope.subNumberArray.length=$scope.subNumber;
            if(typeof($scope.detail.input.x[0]) !="undefined")
            {
                if($scope.subNumber<$scope.detail.input.x[0].length)
                {
                    for(var i=0;i<$scope.number;i++)
                    {
                        $scope.detail.input.x[i]=$scope.detail.input.x[i].slice(0,$scope.subNumber);
                    }
                }

            }

        }
        else{
            alert("子组数为正数");
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
        postJSON.project=$scope.regress.selectProject.name;
        if(postJSON.name==''||typeof (postJSON.project)=='undefined')
        {
            $scope.regress.tips("提交失败，请填写完整信息！");
            return ;
        }

        postJSON["input"]=$scope.detail.input;

        var url="";
        if(postOrCreate=="edit")
        {
            url="/"+$scope.detail.id;
        }

        RestServerce.post("api/regress/"+url,postJSON).then(
            function(data){
                if(typeof(data.error)!='undefined')
                {
                    $scope.regress.tips(data.error);
                }
                else{
                    $scope.regress.getregressListByProject();
                    $scope.regress.tips(message);
                    $scope.detail=angular.copy(data);
                }
            },function(error){
                $scope.regress.tips(error);
            });

    };

    $scope.update=function(){
        rest("更新成功!","edit");
    };

    $scope.create=function(){
        rest("新建成功!","create");
    };


}]);

app.filter("arrayDisplay", function() {
    return  function(e) {
        var out='';
        e.forEach(
            function(e){
                out+=e+',';
            }
        );

        return out.slice(0,out.length-1);
    }

});

