/**
 * Created by BoruXu on 2015/2/20.
 */
var app=angular.module('qpmtools.home',['ui.router']);

app.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/home');

    $stateProvider

        .state('home', {
            url: '/home',
            templateUrl: 'home/home.html'
        });



});