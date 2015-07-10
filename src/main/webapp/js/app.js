/**
 * Created by Boruxu on 2015/2/20.
 * qpmtools Angular前端框架主js
 */
var app=angular.module('qpmtools',['ui.router','qpmtools.home','qpmtools.spc.app',
'qpmtools.risk.app','qpmtools.mc.app','qpmtools.asset.app','kendo.directives']);

app.config(function($stateProvider, $urlRouterProvider){

    $urlRouterProvider.otherwise("/home");

});

app.controller('IndexController',function($scope,$location,$rootScope){

});


