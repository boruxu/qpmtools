/**
 * Created by BoruXU on 2015/2/19
 * 本文件为risk工具angular路由js文件
 */
var app=angular.module('qpmtools.risk.app',['ngAnimate','ui.router','qpmtools.systemRest']);

app.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/risk');

    $stateProvider

        .state('risk', {
            url: '/risk',
            templateUrl: 'risk/risk.html',
            controller: 'RiskHomeController'
        })
        //风险库
        .state('risk.type', {
            url: '/type',
            templateUrl: 'risk/type/typeList.html',
            controller: 'TypeList'
        })
        .state('risk.type.detail', {
            url: '/detail/{item}',
            templateUrl: 'risk/type/typeDetail.html',
            controller: 'Detail'
        })
        .state('risk.type.edit', {
            url: '/edit/{item}',
            templateUrl: 'risk/type/typeEdit.html',
            controller: 'TypeEdit'
        })
        .state('risk.type.create', {
            url: '/create',
            templateUrl: 'risk/type/typeCreate.html',
            controller: 'TypeEdit'
        })
        //风险识别
        .state('risk.identify', {
            url: '/identify',
            templateUrl: 'risk/identify/list.html'
        })
        .state('risk.identify.detail', {
            url: '/detail/{id}',
            templateUrl: 'risk/identify/detail.html',
            controller:'riskDetail'
        })
        .state('risk.identify.edit', {
            url: '/edit/{id}',
            templateUrl: 'risk/identify/edit.html',
            controller:'riskDetail'

        })
        .state('risk.identify.create', {
            url: '/create',
            templateUrl: 'risk/identify/create.html',
            controller:'IdentifyCreate'

        })
        //风险分析
        .state('risk.analysis', {
            url: '/analysis',
            templateUrl: 'risk/analysis/list.html'
        })
        .state('risk.analysis.detail', {
            url: '/detail/{id}',
            templateUrl: 'risk/analysis/detail.html',
            controller:'riskDetail'
        })
        .state('risk.analysis.edit', {
            url: '/edit/{id}',
            templateUrl: 'risk/analysis/edit.html',
            controller:'riskDetail'

        })
        //风险评估
        .state('risk.evaluate', {
            url: '/evaluate',
            templateUrl: 'risk/evaluate/list.html',
            controller:'EvaluateController'
        })
        .state('risk.evaluate.detail', {
            url: '/detail/{id}',
            templateUrl: 'risk/evaluate/detail.html',
            controller:'riskDetail'
        });
});

//risk全局变量
app.factory('riskGlobal',function(){
   return {
       organizationList:'',
       organization:'',
       project:'',
       projectList:'',
       riskTypeList:'',
       riskList:[]
   }
});


//risk全局controller设置，作用域有riskType和risk对象，通过设置组织名和项目名变换以上两个对象
app.controller('RiskHomeController',['$scope','RestServerce','riskGlobal'
    ,function($scope,RestServerce,riskGlobal){

        $scope.riskG={selectOrganization:'',
            selectProject:'',
            riskList:[]};
        //页面加载的时候获取组织列表
        RestServerce.get("api/system/organization/list").then(
            function(data){
                riskGlobal.organizationList=data.list;
                $scope.riskG.organizationList=riskGlobal.organizationList;
                //向下传递事件
                $scope.$broadcast('organizationChange', $scope.riskG.organizationList);
            },function(error){});

        //选择组织后,访问后台获取相应所属项目
        $scope.choseO=function(){
            $scope.riskG.selectProject==null;
            if($scope.riskG.selectOrganization!=null)
            {
                var url=$scope.riskG.selectOrganization.name;
                riskGlobal.organization=url;
                RestServerce.get("api/system/project/list/"+url).then(
                    function(data){
                        riskGlobal.projectList=data.list;
                        $scope.riskG.projectList=riskGlobal.projectList;
                        $scope.riskG.getRiskTypeListByOrganization();
                        $scope.$broadcast('organizationChange', $scope.riskG.organizationList);
                        $scope.$broadcast('projectChange',null);
                    },function(error){});
            }
            else{
                riskGlobal.organization="";
                $scope.$broadcast('organizationChange', $scope.riskG.organizationList);
                $scope.$broadcast('projectChange',null);
            }


        };

        //选择项目后,访问后台获取相应riskList
        $scope.choseP=function(){

            if($scope.riskG.selectProject!=null)
            {
                var url=$scope.riskG.selectProject.name;
                riskGlobal.project=url;
                RestServerce.get("api/risk/riskItem/list/"+url).then(
                    function(data){
                        riskGlobal.riskList=data.list;
                        $scope.riskG.riskList=riskGlobal.riskList;
                        $scope.$broadcast('projectChange', riskGlobal.riskList);
                    },function(error){});
            }
            else{
                riskGlobal.riskList='';
                $scope.riskG.riskList=riskGlobal.riskList;
                $scope.$broadcast('projectChange', riskGlobal.riskList);
            }



        };

        $scope.riskG.getRiskTypeListByOrganization=function(){

            RestServerce.get("api/risk/riskType/list/"+$scope.riskG.selectOrganization.name).then(
                function(data){
                    $scope.riskG.riskTypeList=data.list;
                },function(error){
                    $scope.riskG.riskTypeList='';
                });

        };

        $scope.riskG.getRiskListByProject=function(){

            RestServerce.get("api/risk/riskItem/list/"+$scope.riskG.selectProject.name).then(
                function(data){
                    $scope.riskG.riskList=data.list;
                },function(error){
                    $scope.riskG.riskList='';
                });

        };

        $scope.riskG.remove=function(id){
            RestServerce.remove("api/risk/riskItem/"+id).then(
                function(data){

                    $scope.riskG.getRiskListByProject();

                },function(error){
                    alert(error.error);
                });

        };


        $scope.riskG.tips=function(message){
            $scope.riskG.message=message;
            setTimeout(function(){
                $scope.riskG.message="";
                $scope.$apply($scope.riskG.message);
            },2000);
        };

    }]);
//风险常见列表controller
app.controller('TypeList',['$scope','RestServerce','riskGlobal',function($scope,RestServerce,riskGlobal){

    $scope.riskTypeG={};


    $scope.riskTypeG.getriskTypeList=function(){
        var selectedOrganizarion=$scope.riskG.selectOrganization;
        if(selectedOrganizarion!=null&&typeof(selectedOrganizarion) != "undefined"&&selectedOrganizarion!="")
        {
            RestServerce.get("api/risk/riskType/list/"+$scope.riskG.selectOrganization.name).then(
                function(data){
                    riskGlobal.riskTypeList=data.list;
                    $scope.riskTypeG.riskTypeList=riskGlobal.riskTypeList;
                },function(error){});

        }
        else
        {
            riskGlobal.riskTypeList=[];
            $scope.riskTypeG.riskTypeList=riskGlobal.riskTypeList;
        }
    };

    //页面加载的时候组织对应的riskType列表
    $scope.riskTypeG.getriskTypeList();

    $scope.$on('organizationChange', function(e, newVal) {

        $scope.riskTypeG.getriskTypeList();

    });

    $scope.remove=function(id){
        RestServerce.remove("api/risk/riskType/"+id).then(
            function(data){

                $scope.riskTypeG.getriskTypeList();

            },function(error){
                alert(error.error);
            });

    };

}]);

app.controller('Detail',['$scope','$stateParams','riskGlobal',function($scope,$stateParams,riskGlobal){

    riskGlobal.riskTypeList.forEach(function(d)
    {

        if(d.id==$stateParams.item)
        {
            $scope.detail=d;
        }
    });


}]);

app.controller('TypeEdit',['$scope','$stateParams','riskGlobal','RestServerce',
    function($scope,$stateParams,riskGlobal,RestServerce){

    riskGlobal.riskTypeList.forEach(function(d)
    {

        if(d.id==$stateParams.item)
        {
            //复制值，不使用引用
            $scope.detail=angular.copy(d);
            $scope.detail.organization=angular.copy(d.organization.name);

        }
    });

    $scope.organizationList=riskGlobal.organizationList;
    $scope.$on('organizationChange',function(e,newval){
        $scope.organizationList=newval;
    });

    $scope.create=function(){
        RestServerce.post("api/risk/riskType/",$scope.detail).then(
            function(data){

                $scope.riskTypeG.getriskTypeList();

            },function(error){
                alert(error.error);
            });

    };
    $scope.update=function(){
        RestServerce.post("api/risk/riskType/"+$scope.detail.id,$scope.detail).then(
            function(data){

                $scope.riskTypeG.getriskTypeList();

            },function(error){
                    alert(error.error);
            });

    };




}]);


app.controller('IdentifyCreate',['$scope','RestServerce',function($scope,RestServerce){

    //create页面
    $scope.detail={
        riskState:"已识别"
    };

    $scope.create=function(){

        var date=new Date();

        $scope.detail.riskTrack={
            measureType:'识别风险',
            measureMan:'测试人员',
            measureTime:date
        };

        RestServerce.post("api/risk/riskItem",$scope.detail).then(
            function(data){

                $scope.riskG.getRiskListByProject();
                $scope.riskG.tips("创建成功！");
            },function(error){});

    };


}]);


app.controller('riskDetail',['$scope','$stateParams','RestServerce','$state',function($scope,$stateParams,RestServerce,$state){


    var getDetail=function()
    {

        if(typeof($scope.riskG.riskList)!='undefined')
        {

            $scope.riskG.riskList.forEach(
              function(d){
                  if(d.id==$stateParams.id)
                  {
                      $scope.detail=angular.copy(d);
                      $scope.detail.riskType= angular.copy(d.riskType.name);
                      $scope.detail.project=angular.copy(d.project.name);
                      if($state.current.name=='risk.identify.edit')
                      {
                          $scope.detail.riskState="已识别";
                      }
                  }

              }
            );

        }

    };

    getDetail();

    var rest=function(url,message){

        RestServerce.post(url+$scope.detail.id,$scope.detail).then(
            function(data){

                $scope.riskG.getRiskListByProject();
                $scope.riskG.tips(message);

            },function(error){
                alert(error.error);
            });

    };


    $scope.update=function(){

        var date=new Date();

        $scope.detail.riskTrack={
            measureType:'已识别风险修改',
            measureMan:'测试人员',
            measureTime:date
        };

        rest("api/risk/riskItem/identify/","修改成功!");


    };


    //风险分析
    $scope.posibilityList=[
        {name:'高',value:'9'},
        {name:'中',value:'6'},
        {name:'低',value:'3'}
    ];
    $scope.damageList=[
        {name:'高',value:'9'},
        {name:'中',value:'6'},
        {name:'低',value:'3'}
    ];
    $scope.urgencyList=[
        {name:'近期',value:'9'},
        {name:'中期',value:'6'},
        {name:'远期',value:'3'}
    ];


    $scope.Analysis=function(){

        var date=new Date();

        if($scope.detail.riskState=="已识别")
        {

            $scope.detail.riskTrack={
                measureType:'分析风险',
                measureMan:'测试人员',
                measureTime:date
            };
            $scope.detail.riskState="已分析";
        }
        else
        {
            $scope.detail.riskTrack={
                measureType:'重新分析风险',
                measureMan:'测试人员',
                measureTime:date
            };

        }


        if($scope.detail.riskPriority==0)
        {
            alert("请选择优先级选项！");
        }
        else{
            $scope.detail.riskPosibility=$scope.riskPosibility.name;
            $scope.detail.riskDamage=$scope.riskDamage.name;
            $scope.detail.riskUrgency=$scope.riskUrgency.name;
            rest("api/risk/riskItem/analysis/","分析成功!");
        }



    };



}]);

app.controller('EvaluateController',['$scope','RestServerce',function($scope,RestServerce){



    $scope.setEvaluate=function(id){

        var detail='';

        if(typeof($scope.riskG.riskList)!='undefined')
        {

            $scope.riskG.riskList.forEach(
                function(d){
                    if(d.id==id)
                    {
                        detail=angular.copy(d);
                        detail.riskType= angular.copy(d.riskType.name);
                        detail.project=angular.copy(d.project.name);

                    }

                }
            );

            detail.riskState="已评估";

            var date=new Date();

            detail.riskTrack={
                measureType:'评估风险',
                measureMan:'测试人员',
                measureTime:date
            };

            RestServerce.post("api/risk/riskItem/evaluate/"+detail.id,detail).then(
                function(data){

                    $scope.riskG.getRiskListByProject();
                    $scope.riskG.tips("评估成功！");
                },function(error){});

        }


    };



}]);

