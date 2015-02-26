/**
 * Created by BoruXU on 2015/2/19
 * 本文件为spc工具angular路由js文件
 */
var app=angular.module('qpmtools.spc.router',['ngAnimate','ui.router']);

app.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/spc/repository');

    $stateProvider

        .state('spc', {
            url: '/spc',
            templateUrl: 'spc/spc.html'
        })
        .state('spc.type', {
            url: '/type',
            templateUrl: 'spc/repository.html'
        })
        .state('spc.input', {
            url: '/input',
            templateUrl: 'spc/input.html'
        })
        .state('spc.plot', {
            url: '/plot',
            templateUrl: 'spc/plot.html'
        });



});