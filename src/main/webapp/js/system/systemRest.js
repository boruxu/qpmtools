/**
 * Created by BoruXu on 2015/2/26.
 */
var app=angular.module('qpmtools.systemRest', ['restangular']);
app.factory('RestServerce', ['Restangular', function(Restangular) {
    var restAngular = Restangular.withConfig(function(Configurer) {
        Configurer.setBaseUrl('/qpmtools');
    });
    return {
        get: function(resource) {
            var getUrl=restAngular.all('');
            return getUrl.get(resource);
        },
        //create和update都用post
        post:function(resource,json){
            var cUrl=restAngular.all(resource);
            return cUrl.post(json);
        },
        remove:function(resource){
            var removeUrl=restAngular.all(resource);
            return removeUrl.remove();
        }
    };
}]);