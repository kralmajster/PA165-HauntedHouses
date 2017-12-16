var hauntedHousesServices = angular.module('hauntedHousesServices', []);
var urlBase = "http://localhost:8080/pa165/rest";

hauntedHousesServices.factory('ghostFactory', ['$http',
    function($http){
        var urlGhost = urlBase.concat("/ghost");
        var urlGhostId = urlGhost.concat("/{id}");
		var dataFactory={};
		
        dataFactory.getAllGhosts = function(success, error) {
            return $http.get(urlGhost).then(success, error);
        };

		dataFactory.giveAbilityToGhost = function(ghostId, success, error) {
            return $http.put(urlGhostId.replace("{id}", ghostId)).then(success, error);
        };
        
		dataFactory.removeAbilityFromGhost = function(ghostId, success, error) {
            return $http.put(urlGhostId.replace("{id}", ghostId)).then(success, error);
        };
        //TODO
		dataFactory.setGhostToHauntInHouse = function(ghostId, success, error) {
            return $http.put(urlGhostId.replace("{id}", ghostId)).then(success, error);
        };

		dataFactory.createGhost = function(ghost, success, error) {
   			return $http.post(urlGhost, ghost).then(success, error);
   		};

   		dataFactory.deleteGhost = function(ghostId, success, error) {
           	return $http.delete(urlGhostId.replace("{id}",ghostId)).then(success, error);
        };

        return dataFactory;
    }
]);