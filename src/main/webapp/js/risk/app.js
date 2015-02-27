/**
 * Created by BoruXU on 2015/2/19
 * 本文件为spc工具angular路由js文件
 */
var app=angular.module('qpmtools.risk.app',['ngAnimate','ui.router','qpmtools.systemRest']);

app.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/risk');

    $stateProvider

        .state('risk', {
            url: '/risk',
            templateUrl: 'risk/risk.html',
            controller:'RiskHomeController'
        })
        //风险库
        .state('risk.type', {
            url: '/type',
            templateUrl: 'risk/type/typeList.html',
            controller:'TypeList'
        })
        .state('risk.type.detail',{
            url:'/detail/{item}',
            templateUrl:'risk/type/typeDetail.html',
            controller:'Detail'
        })
        .state('risk.type.edit',{
            url:'/edit/{item}',
            templateUrl:'risk/type/typeEdit.html',
            controller:'TypeEdit'
        })
        .state('risk.type.create',{
            url:'/create',
            templateUrl:'risk/type/typeCreate.html',
            controller:'TypeEdit'
        })
        .state('risk.identify',{
            url:'/identify',
            templateUrl:'risk/identify.html'
        })
        .state('risk.identify.detail',{
            url:'/detail/:item',
            templateUrl:'risk/identify_detail.html'
        })
        .state('risk.identify.create',{
            url:'/create',
            templateUrl:'risk/identify_create.html'
        })
        .state('risk.identify.modify',{
            url:'/modify/:item',
            templateUrl:'risk/identify_modify.html'
        })

});

//risk全局变量
app.factory('riskGlobal',function(){
   return {
       organizationList:'',
       organization:'',
       project:'',
       riskTypeList:''
   }
});


//risk全局controller设置，作用域有riskType和risk对象，通过设置组织名和项目名变换以上两个对象
app.controller('RiskHomeController',['$scope','RestServerce','riskGlobal'
    ,function($scope,RestServerce,riskGlobal){

        $scope.riskG={selectOrganization:'',
            selectProject:''};
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
                        $scope.riskG.projectList=data.list;
                    },function(error){});
            }

            $scope.$broadcast('organizationChange', $scope.riskG.organizationList);

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


app.controller('IdentifyList',['$scope',function($scope){
    $scope.items=[
        {riskItem:'风险一',riskType:'1',riskInfo:'2fdfkaiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii' +
            'eagekgtiahdfkjdbskbgfkjds',riskPotentialInfluence:'3',riskCondition:'4',
            riskState:'已识别',riskProposeMeasure:'5'},
        {riskItem:'风险二',riskType:'1',riskInfo:'2',riskPotentialInfluence:'3',riskCondition:'4',
            riskState:'已识别',riskProposeMeasure:'5'},
        {riskItem:'风险三',riskType:'1',riskInfo:'2',riskPotentialInfluence:'3',riskCondition:'4',
            riskState:'已识别',riskProposeMeasure:'5'}
    ];



}]);

app.controller('IdentifyDetail',['$scope','$stateParams',function($scope,$stateParams){


    !function setDetail()
    {
        $scope.items.forEach(function(d)
        {
            if(d.riskItem==$stateParams.item)
            {
                $scope.detail=d;
            }
        });

    }();


    $scope.description='<h1>asdasda</h1>';



}]);

