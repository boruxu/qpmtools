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
        .state('risk.type', {
            url: '/type',
            templateUrl: 'risk/type/typeList.html'
        })
        .state('risk.type.detail',{
            url:'/detail/:item',
            templateUrl:'risk/type/typeDetail.html',
            controller:'Detail'
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

//risk全局controller设置，作用域有riskType和risk对象，通过设置组织名和项目名变换以上两个对象
app.controller('RiskHomeController',['$scope','RestServerce'
    ,function($scope,RestServerce){

		$scope.riskG={selectOrganization:'',
    		selectProject:''};    
		RestServerce.get("api/system/organization/list").then(
				function(data){
					$scope.organizationList=data.list;
				},function(error){});
    
}]);

app.controller('TypeList',['$scope',function($scope){
    $scope.items=[
        {name:'需求阶段稳定性',description:'涉及需求变更的程度、对需求变更的控制能力以及变更后可能的影响。',
        exsample:'客户多次对软件的需求进行变更。',mitigationMeasure:'A 要求设计提高模块的独立性，高内聚，低耦合。' +
    'B 采用迭代开发。C 定义适合的变更流程。D 要求客户对需求文档进行确认。A 要求设计提高模块的独立性，高内聚，低耦合。' +
            'B 采用迭代开发。C 定义适合的变更流程。D 要求客户对需求文档进行确认。',emergencyMeasure:'A 保持与客户的沟通，及时获得需求变更' +
    '的信息。B 接到需求变更的要求后与客户协商，降低变更的影响。'},
        {name:'需求阶段完整性',description:'涉及需求的缺失、遗漏或不完整，没有达到客户合同中的预期。',
            exsample:'子系统中某模块缺少一部分需求信息的详细描述，导致数据库需要重新设计。',mitigationMeasure:'A 采用同行评审活动。' +
            'B 需求开发角色对照合同的需求范围，进行审查。C 充分利用需求跟踪矩阵，来进行追踪确认。',emergencyMeasure:
            'A 及时修订需求规格说明书，补充丢失内容，并通知相关人员，做相应调整。B 通过加班完成，或者调整计划，重新安排任务。'}
    ];

}]);

app.controller('Detail',['$scope','$stateParams',function($scope,$stateParams){

    !function setDetail()
    {
        $scope.items.forEach(function(d)
        {

            if(d.name==$stateParams.item)
            {
                $scope.detail=d;
            }
        });

    }();

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




}]);