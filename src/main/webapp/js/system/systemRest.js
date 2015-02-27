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
            var getUrl=restAngular.all("");
            return getUrl.get(resource);
        },
        post:function(resource,json){
            var jsons=JSON.stringify(json);
            var cUrl=restAngular.all(resource);
            return cUrl.post(jsons);
        },
        remove:function(resource){
            var removeUrl=restAngular.all(resource);
            return removeUrl.remove();
        }
    };
}]);