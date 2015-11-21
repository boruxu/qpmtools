/**
 * Created by BoruXU on 2015/2/19
 * 本文件为qpmFlow工具angular路由js文件
 */
var app=angular.module('qpmtools.qpmFlow.app',['ngAnimate','ui.router','qpmtools.systemRest']);

app.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/qpmFlow');

    $stateProvider

        .state('qpmFlow', {
            url: '/qpmFlow',
            templateUrl: 'qpmFlow/qpmFlow.html',
            controller: 'qpmFlowHomeController'
        })
        //风险库
        .state('qpmFlow.type', {
            url: '/type',
            templateUrl: 'qpmFlow/type/typeList.html',
            controller: 'TypeList'
        })
        .state('qpmFlow.type.detail', {
            url: '/detail/{item}',
            templateUrl: 'qpmFlow/type/typeDetail.html',
            controller: 'Detail'
        })
        .state('qpmFlow.type.edit', {
            url: '/edit/{item}',
            templateUrl: 'qpmFlow/type/typeEdit.html',
            controller: 'TypeEdit'
        })
        .state('qpmFlow.type.create', {
            url: '/create',
            templateUrl: 'qpmFlow/type/typeCreate.html',
            controller: 'TypeEdit'
        })
        //风险识别
        .state('qpmFlow.identify', {
            url: '/identify',
            templateUrl: 'qpmFlow/identify/list.html'
        })
        .state('qpmFlow.identify.detail', {
            url: '/detail/{id}',
            templateUrl: 'qpmFlow/identify/detail.html',
            controller:'qpmFlowDetail'
        })
        .state('qpmFlow.identify.edit', {
            url: '/edit/{id}',
            templateUrl: 'qpmFlow/identify/edit.html',
            controller:'qpmFlowDetail'

        })
        .state('qpmFlow.identify.create', {
            url: '/create',
            templateUrl: 'qpmFlow/identify/create.html',
            controller:'IdentifyCreate'

        })
        //风险分析
        .state('qpmFlow.analysis', {
            url: '/analysis',
            templateUrl: 'qpmFlow/analysis/list.html'
        })
        .state('qpmFlow.analysis.detail', {
            url: '/detail/{id}',
            templateUrl: 'qpmFlow/analysis/detail.html',
            controller:'qpmFlowDetail'
        })
        .state('qpmFlow.analysis.edit', {
            url: '/edit/{id}',
            templateUrl: 'qpmFlow/analysis/edit.html',
            controller:'qpmFlowDetail'

        })
        //风险评估
        .state('qpmFlow.evaluate', {
            url: '/evaluate',
            templateUrl: 'qpmFlow/evaluate/list.html',
            controller:'EvaluateController'
        })
        .state('qpmFlow.evaluate.detail', {
            url: '/detail/{id}',
            templateUrl: 'qpmFlow/evaluate/detail.html',
            controller:'qpmFlowDetail'
        })
        //风险计划
        .state('qpmFlow.plan', {
            url: '/plan',
            templateUrl: 'qpmFlow/plan/list.html'
        })
        .state('qpmFlow.plan.detail', {
            url: '/detail/{id}',
            templateUrl: 'qpmFlow/plan/detail.html',
            controller:'qpmFlowDetail'
        })
        .state('qpmFlow.plan.edit', {
            url: '/edit/{id}',
            templateUrl: 'qpmFlow/plan/edit.html',
            controller:'qpmFlowDetail'

        })//风险审批
        .state('qpmFlow.approval', {
            url: '/approval',
            templateUrl: 'qpmFlow/approval/list.html'
        })
        .state('qpmFlow.approval.detail', {
            url: '/detail/{id}',
            templateUrl: 'qpmFlow/approval/detail.html',
            controller:'qpmFlowDetail'
        })
        .state('qpmFlow.approval.edit', {
            url: '/edit/{id}',
            templateUrl: 'qpmFlow/approval/edit.html',
            controller:'qpmFlowDetail'

        })
        //风险跟踪
        .state('qpmFlow.track', {
            url: '/track',
            templateUrl: 'qpmFlow/track/list.html'
        })
        .state('qpmFlow.track.detail', {
            url: '/detail/{id}',
            templateUrl: 'qpmFlow/track/detail.html',
            controller:'qpmFlowDetail'
        })
        .state('qpmFlow.track.edit', {
            url: '/edit/{id}',
            templateUrl: 'qpmFlow/track/edit.html',
            controller:'qpmFlowDetail'

        })
        //风险控制
        .state('qpmFlow.control', {
            url: '/control',
            templateUrl: 'qpmFlow/control/list.html',
            controller:'ControlController'
        })
        .state('qpmFlow.control.detail', {
            url: '/detail/{id}',
            templateUrl: 'qpmFlow/control/detail.html',
            controller:'qpmFlowDetail'
        })//风险列表
        .state('qpmFlow.qpmFlowList', {
            url: '/qpmFlowList',
            templateUrl: 'qpmFlow/qpmFlowList/list.html',
            controller:'ControlController'
        })
        .state('qpmFlow.qpmFlowList.detail', {
            url: '/detail/{id}',
            templateUrl: 'qpmFlow/qpmFlowList/detail.html',
            controller:'qpmFlowDetail'
        }).state('qpmFlow.qpmFlowList.track', {
            url: '/track/{id}',
            templateUrl: 'qpmFlow/qpmFlowList/track.html',
            controller:'qpmFlowDetail'
        });
});

//qpmFlow全局变量
app.factory('qpmFlowGlobal',function(){
   return {
       organizationList:'',
       organization:'',
       project:'',
       projectList:'',
       qpmFlowTypeList:'',
       qpmFlowList:[]
   }
});


//qpmFlow全局controller设置，作用域有qpmFlowType和qpmFlow对象，通过设置组织名和项目名变换以上两个对象
app.controller('qpmFlowHomeController',['$scope','RestServerce','qpmFlowGlobal'
    ,function($scope,RestServerce,qpmFlowGlobal){

        $scope.qpmFlowG={selectOrganization:'',
            selectProject:'',
            qpmFlowList:[]};
        //页面加载的时候获取组织列表
        RestServerce.get("api/system/organization/list").then(
            function(data){
                qpmFlowGlobal.organizationList=data.list;
                $scope.qpmFlowG.organizationList=qpmFlowGlobal.organizationList;
                //向下传递事件
                $scope.$broadcast('organizationChange', $scope.qpmFlowG.organizationList);
            },function(error){});

        //选择组织后,访问后台获取相应所属项目
        $scope.choseO=function(){
            $scope.qpmFlowG.selectProject==null;
            if($scope.qpmFlowG.selectOrganization!=null)
            {
                var url=$scope.qpmFlowG.selectOrganization.name;
                qpmFlowGlobal.organization=url;
                RestServerce.get("api/system/project/list/"+url).then(
                    function(data){
                        qpmFlowGlobal.projectList=data.list;
                        $scope.qpmFlowG.projectList=qpmFlowGlobal.projectList;
                        $scope.qpmFlowG.getqpmFlowTypeListByOrganization();
                        $scope.$broadcast('organizationChange', $scope.qpmFlowG.organizationList);
                        $scope.$broadcast('projectChange',null);
                    },function(error){});
            }
            else{
                qpmFlowGlobal.organization="";
                $scope.$broadcast('organizationChange', $scope.qpmFlowG.organizationList);
                $scope.$broadcast('projectChange',null);
            }


        };

        //选择项目后,访问后台获取相应qpmFlowList
        $scope.choseP=function(){

            if($scope.qpmFlowG.selectProject!=null)
            {
                var url=$scope.qpmFlowG.selectProject.name;
                qpmFlowGlobal.project=url;
                RestServerce.get("api/qpmFlow/qpmFlowItem/list/"+url).then(
                    function(data){
                        qpmFlowGlobal.qpmFlowList=data.list;
                        $scope.qpmFlowG.qpmFlowList=qpmFlowGlobal.qpmFlowList;
                        $scope.$broadcast('projectChange', qpmFlowGlobal.qpmFlowList);
                    },function(error){});
            }
            else{
                qpmFlowGlobal.qpmFlowList='';
                $scope.qpmFlowG.qpmFlowList=qpmFlowGlobal.qpmFlowList;
                $scope.$broadcast('projectChange', qpmFlowGlobal.qpmFlowList);
            }



        };

        $scope.qpmFlowG.getqpmFlowTypeListByOrganization=function(){

            RestServerce.get("api/qpmFlow/qpmFlowType/list/"+$scope.qpmFlowG.selectOrganization.name).then(
                function(data){
                    $scope.qpmFlowG.qpmFlowTypeList=data.list;
                },function(error){
                    $scope.qpmFlowG.qpmFlowTypeList='';
                });

        };

        $scope.qpmFlowG.getqpmFlowListByProject=function(){

            RestServerce.get("api/qpmFlow/qpmFlowItem/list/"+$scope.qpmFlowG.selectProject.name).then(
                function(data){
                    $scope.qpmFlowG.qpmFlowList=data.list;
                },function(error){
                    $scope.qpmFlowG.qpmFlowList='';
                });

        };

        $scope.qpmFlowG.remove=function(id){
            RestServerce.remove("api/qpmFlow/qpmFlowItem/"+id).then(
                function(data){

                    $scope.qpmFlowG.getqpmFlowListByProject();

                },function(error){
                    alert(error.error);
                });

        };


        $scope.qpmFlowG.tips=function(message){
            $scope.qpmFlowG.message=message;
            setTimeout(function(){
                $scope.qpmFlowG.message="";
                $scope.$apply($scope.qpmFlowG.message);
            },2000);
        };

    }]);
//风险常见列表controller
app.controller('TypeList',['$scope','RestServerce','qpmFlowGlobal',function($scope,RestServerce,qpmFlowGlobal){

    $scope.qpmFlowTypeG={};


    $scope.qpmFlowTypeG.getqpmFlowTypeList=function(){
        var selectedOrganizarion=$scope.qpmFlowG.selectOrganization;
        if(selectedOrganizarion!=null&&typeof(selectedOrganizarion) != "undefined"&&selectedOrganizarion!="")
        {
            RestServerce.get("api/qpmFlow/qpmFlowType/list/"+$scope.qpmFlowG.selectOrganization.name).then(
                function(data){
                    qpmFlowGlobal.qpmFlowTypeList=data.list;
                    $scope.qpmFlowTypeG.qpmFlowTypeList=qpmFlowGlobal.qpmFlowTypeList;
                },function(error){});

        }
        else
        {
            qpmFlowGlobal.qpmFlowTypeList=[];
            $scope.qpmFlowTypeG.qpmFlowTypeList=qpmFlowGlobal.qpmFlowTypeList;
        }
    };

    //页面加载的时候组织对应的qpmFlowType列表
    $scope.qpmFlowTypeG.getqpmFlowTypeList();

    $scope.$on('organizationChange', function(e, newVal) {

        $scope.qpmFlowTypeG.getqpmFlowTypeList();

    });

    $scope.remove=function(id){
        RestServerce.remove("api/qpmFlow/qpmFlowType/"+id).then(
            function(data){

                $scope.qpmFlowTypeG.getqpmFlowTypeList();

            },function(error){
                alert(error.error);
            });

    };

}]);

app.controller('Detail',['$scope','$stateParams','qpmFlowGlobal',function($scope,$stateParams,qpmFlowGlobal){

    qpmFlowGlobal.qpmFlowTypeList.forEach(function(d)
    {

        if(d.id==$stateParams.item)
        {
            $scope.detail=d;
        }
    });


}]);

app.controller('TypeEdit',['$scope','$stateParams','qpmFlowGlobal','RestServerce',
    function($scope,$stateParams,qpmFlowGlobal,RestServerce){

    qpmFlowGlobal.qpmFlowTypeList.forEach(function(d)
    {

        if(d.id==$stateParams.item)
        {
            //复制值，不使用引用
            $scope.detail=angular.copy(d);
            $scope.detail.organization=angular.copy(d.organization.name);

        }
    });

    $scope.organizationList=qpmFlowGlobal.organizationList;
    $scope.$on('organizationChange',function(e,newval){
        $scope.organizationList=newval;
    });

    $scope.create=function(){
        RestServerce.post("api/qpmFlow/qpmFlowType/",$scope.detail).then(
            function(data){

                $scope.qpmFlowTypeG.getqpmFlowTypeList();

            },function(error){
                alert(error.error);
            });

    };
    $scope.update=function(){
        RestServerce.post("api/qpmFlow/qpmFlowType/"+$scope.detail.id,$scope.detail).then(
            function(data){

                $scope.qpmFlowTypeG.getqpmFlowTypeList();

            },function(error){
                    alert(error.error);
            });

    };




}]);


app.controller('IdentifyCreate',['$scope','RestServerce',function($scope,RestServerce){

    //create页面
    $scope.detail={
        qpmFlowState:"已识别"
    };

    $scope.create=function(){

        var date=new Date();

        $scope.detail.qpmFlowTrack={
            measureType:'识别风险',
            measureMan:'测试人员',
            measureTime:date
        };

        RestServerce.post("api/qpmFlow/qpmFlowItem",$scope.detail).then(
            function(data){

                $scope.qpmFlowG.getqpmFlowListByProject();
                $scope.qpmFlowG.tips("创建成功！");
            },function(error){});

    };


}]);


app.controller('qpmFlowDetail',['$scope','$stateParams','RestServerce','$state',function($scope,$stateParams,RestServerce,$state){


    var getDetail=function()
    {

        if(typeof($scope.qpmFlowG.qpmFlowList)!='undefined')
        {

            $scope.qpmFlowG.qpmFlowList.forEach(
              function(d){
                  if(d.id==$stateParams.id)
                  {
                      $scope.detail=angular.copy(d);
                      $scope.detail.qpmFlowType= angular.copy(d.qpmFlowType.name);
                      $scope.detail.project=angular.copy(d.project.name);
                      if($state.current.name=='qpmFlow.identify.edit')
                      {
                          $scope.detail.qpmFlowState="已识别";
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

                $scope.qpmFlowG.getqpmFlowListByProject();
                $scope.qpmFlowG.tips(message);

            },function(error){
                alert(error.error);
            });

    };


    $scope.update=function(){

        var date=new Date();

        $scope.detail.qpmFlowTrack={
            measureType:'已识别风险修改',
            measureMan:'测试人员',
            measureTime:date
        };

        rest("api/qpmFlow/qpmFlowItem/identify/","修改成功!");


    };

    var setPDU=function(){
        if(typeof($scope.detail)!='undefined'
            &&typeof($scope.detail.qpmFlowPosibility)!='undefined'
            &&typeof($scope.detail.qpmFlowDamage)!='undefined'
            &&typeof($scope.detail.qpmFlowUrgency)!='undefined')
        {
            $scope.posibilityList.forEach(
                function(d)
                {
                    if(d.name==$scope.detail.qpmFlowPosibility)
                    {
                        $scope.qpmFlowPosibility=d;

                    }

                }
            );

            $scope.damageList.forEach(
                function(d)
                {
                    if(d.name==$scope.detail.qpmFlowDamage)
                    {
                        $scope.qpmFlowDamage=d;
                    }

                }
            );

            $scope.urgencyList.forEach(
                function(d)
                {
                    if(d.name==$scope.detail.qpmFlowUrgency)
                    {
                        $scope.qpmFlowUrgency=d;
                    }

                }
            );

        }


    };

    //风险分析
    if($state.current.name=='qpmFlow.analysis.edit'
        || $state.current.name=='qpmFlow.plan.edit'
        || $state.current.name=='qpmFlow.approval.edit'
        || $state.current.name=='qpmFlow.track.edit'
        || $state.current.name=='qpmFlow.control.edit')
    {

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

        setPDU();






    }





    $scope.Analysis=function(){

        var date=new Date();

        if($scope.detail.qpmFlowState=="已识别")
        {

            $scope.detail.qpmFlowTrack={
                measureType:'分析风险',
                measureMan:'测试人员',
                measureTime:date
            };
            $scope.detail.qpmFlowState="已分析";
        }
        else
        {
            $scope.detail.qpmFlowTrack={
                measureType:'重新分析风险',
                measureMan:'测试人员',
                measureTime:date
            };

        }


        if($scope.detail.qpmFlowPriority==0)
        {
            alert("请选择优先级选项！");
        }
        else{
            $scope.detail.qpmFlowPosibility=$scope.qpmFlowPosibility.name;
            $scope.detail.qpmFlowDamage=$scope.qpmFlowDamage.name;
            $scope.detail.qpmFlowUrgency=$scope.qpmFlowUrgency.name;
            rest("api/qpmFlow/qpmFlowItem/analysis/","分析成功!");
        }



    };

    $scope.plan=function(){

        var date=new Date();

        if($scope.detail.qpmFlowState=="已评估")
        {

            $scope.detail.qpmFlowTrack={
                measureType:'计划风险',
                measureMan:'测试人员',
                measureTime:date
            };
            $scope.detail.qpmFlowState="已计划";
        }
        else
        {
            $scope.detail.qpmFlowTrack={
                measureType:'重新计划风险',
                measureMan:'测试人员',
                measureTime:date
            };

        }


        if($scope.detail.qpmFlowPriority==0)
        {
            alert("请选择优先级选项！");
        }
        else if(typeof ($scope.detail.qpmFlowPlanMeasure)=='undefined'
            ||$scope.detail.qpmFlowPlanMeasure.planMeasureType=='') {

            alert("请选择风险计划选项！")
        }
        else if($scope.detail.qpmFlowPlanMeasure.qpmFlowIndicator=='') {

            alert("请选择风险指示器选项！")
        }
        else
        {
            $scope.detail.qpmFlowPosibility=$scope.qpmFlowPosibility.name;
            $scope.detail.qpmFlowDamage=$scope.qpmFlowDamage.name;
            $scope.detail.qpmFlowUrgency=$scope.qpmFlowUrgency.name;

            rest("api/qpmFlow/qpmFlowItem/plan/","计划成功!");

        }


    };

    $scope.approval=function() {

        var date = new Date();

        if ($scope.detail.qpmFlowState == "已计划") {

            $scope.detail.qpmFlowTrack = {
                measureType: '审批风险',
                measureMan: '测试人员',
                measureTime: date
            };

            $scope.detail.qpmFlowState = "已审批";
        }
        else {
            $scope.detail.qpmFlowTrack = {
                measureType: '重新审批风险',
                measureMan: '测试人员',
                measureTime: date
            };

        }


        if ($scope.detail.qpmFlowPriority == 0) {
            alert("请选择优先级选项！");
        }
        else if (typeof ($scope.detail.qpmFlowPlanMeasure) == 'undefined'
            || $scope.detail.qpmFlowPlanMeasure.planMeasureType == '') {

            alert("请选择风险计划选项！");
        }
        else if ($scope.detail.qpmFlowPlanMeasure.qpmFlowIndicator == '') {

            alert("请选择风险指示器选项！");
        }
        else if (typeof ($scope.detail.qpmFlowApproval) == 'undefined'
            || $scope.detail.qpmFlowApproval.qpmFlowApprovalResult == '') {
            alert("请选择风险审批结果选项！");
        }
        else {
            $scope.detail.qpmFlowPosibility = $scope.qpmFlowPosibility.name;
            $scope.detail.qpmFlowDamage = $scope.qpmFlowDamage.name;
            $scope.detail.qpmFlowUrgency = $scope.qpmFlowUrgency.name;
            $scope.detail.qpmFlowApproval.qpmFlowApprovalTime = date;

            rest("api/qpmFlow/qpmFlowItem/approval/", "审批成功!");

        }
    };
    $scope.track=function(){

        var date=new Date();

        if($scope.detail.qpmFlowState=="已审批")
        {

            $scope.detail.qpmFlowTrack={
                measureType:'跟踪风险',
                measureMan:'测试人员',
                measureTime:date
            };

            $scope.detail.qpmFlowState="已跟踪";
        }
        else
        {
            $scope.detail.qpmFlowTrack={
                measureType:'继续跟踪风险',
                measureMan:'测试人员',
                measureTime:date
            };

        }


        if($scope.detail.qpmFlowPriority==0)
        {
            alert("请选择优先级选项！");
        }
        else if(typeof ($scope.detail.qpmFlowPlanMeasure)=='undefined'
            ||$scope.detail.qpmFlowPlanMeasure.planMeasureType=='') {

            alert("请选择风险计划选项！");
        }
        else if($scope.detail.qpmFlowPlanMeasure.qpmFlowIndicator=='') {

            alert("请选择风险指示器选项！");
        }
        else if(typeof ($scope.detail.qpmFlowApproval)=='undefined'
            ||$scope.detail.qpmFlowApproval.qpmFlowApprovalResult=='')
        {
            alert("请选择风险审批结果选项！");
        }
        else
        {
            $scope.detail.qpmFlowPosibility=$scope.qpmFlowPosibility.name;
            $scope.detail.qpmFlowDamage=$scope.qpmFlowDamage.name;
            $scope.detail.qpmFlowUrgency=$scope.qpmFlowUrgency.name;
            $scope.detail.qpmFlowApproval.qpmFlowApprovalTime=date

           
            rest("api/qpmFlow/qpmFlowItem/track/","跟踪成功!");

        }


    };



    //display
    $scope.display={
        text:'修改其他项',
        value:false,
        class:'glyphicon glyphicon-chevron-up'
    };

    $scope.displayOther=function(){

        if($scope.display.value==false)
        {
            $scope.display={
                text:'收起',
                value:true,
                class:'glyphicon glyphicon-chevron-down'
            };
        }
        else
        {
            $scope.display={
                text:'修改其他项',
                value:false,
                class:'glyphicon glyphicon-chevron-up'
            };
        }

    };



}]);

app.controller('EvaluateController',['$scope','RestServerce',function($scope,RestServerce){



    $scope.setEvaluate=function(id){

        var detail='';

        if(typeof($scope.qpmFlowG.qpmFlowList)!='undefined')
        {

            $scope.qpmFlowG.qpmFlowList.forEach(
                function(d){
                    if(d.id==id)
                    {
                        detail=angular.copy(d);
                        detail.qpmFlowType= angular.copy(d.qpmFlowType.name);
                        detail.project=angular.copy(d.project.name);

                    }

                }
            );

            detail.qpmFlowState="已评估";

            var date=new Date();

            detail.qpmFlowTrack={
                measureType:'评估风险',
                measureMan:'测试人员',
                measureTime:date
            };

            RestServerce.post("api/qpmFlow/qpmFlowItem/evaluate/"+detail.id,detail).then(
                function(data){

                    $scope.qpmFlowG.getqpmFlowListByProject();
                    $scope.qpmFlowG.tips("评估成功！");
                },function(error){});

        }


    };



}]);

app.controller('ControlController',['$scope','RestServerce',function($scope,RestServerce){



    $scope.control=function(id,state){

        var detail='';

        if(typeof($scope.qpmFlowG.qpmFlowList)!='undefined')
        {

            $scope.qpmFlowG.qpmFlowList.forEach(
                function(d){
                    if(d.id==id)
                    {
                        detail=angular.copy(d);
                        detail.qpmFlowType= angular.copy(d.qpmFlowType.name);
                        detail.project=angular.copy(d.project.name);

                    }

                }
            );
            var date=new Date();

            if(state==0)
            {
                detail.qpmFlowState="已关闭";
                detail.qpmFlowTrack={
                    measureType:'关闭风险',
                    measureMan:'测试人员',
                    measureTime:date
                };
            }
            else
            {
                detail.qpmFlowState="已计划";
                detail.qpmFlowTrack={
                    measureType:'变更风险计划',
                    measureMan:'测试人员',
                    measureTime:date
                };
            }

            RestServerce.post("api/qpmFlow/qpmFlowItem/control/"+detail.id,detail).then(
                function(data){

                    $scope.qpmFlowG.getqpmFlowListByProject();
                    $scope.qpmFlowG.tips("操作成功！");
                },function(error){});

        }


    };



}]);

