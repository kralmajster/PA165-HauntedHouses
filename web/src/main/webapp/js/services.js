var hauntedHousesServices = angular.module('hauntedHousesServices', []);
var urlBase = "http://localhost:8080/pa165/rest";

hauntedHousesServices.factory('abilityFactory', ['$http',
    function ($http) {
        var urlAbilities = urlBase.concat("/ability");
        var urlAbility = urlAbilities.concat("/{id}");
        var dataFactory = {};

        dataFactory.getAllAbilities = function (success, error) {
            return $http.get(urlAbilities).then(success, error);
        };

        dataFactory.getAbility = function (id, success, error) {
            return $http.get(urlAbilities + "/" + id).then(success, error);
        };

        return dataFactory;
    }
]);

hauntedHousesServices.factory('houseFactory', ['$http',
    function ($http) {
        var urlHouses = urlBase.concat("/house");
        var urlHouse = urlHouses.concat("/{id}");
        var dataFactory = {};

        dataFactory.getAllHouses = function (success, error) {
            return $http.get(urlHouses).then(success, error);
        };

        dataFactory.getHouse = function (id, success, error) {
            return $http.get(urlHouses + "/" + id).then(success, error);
        };

        return dataFactory;
    }
]);

hauntedHousesServices.factory('ghostFactory', ['$http',
    function ($http) {
        var urlGhost = urlBase.concat("/ghost");
        var urlGhostId = urlGhost.concat("/{id}");
        var dataFactory = {};

        dataFactory.getAllGhosts = function (success, error) {
            return $http.get(urlGhost).then(success, error);
        };

        dataFactory.giveAbilityToGhost = function (ghostId, success, error) {
            return $http.put(urlGhostId.replace("{id}", ghostId)).then(success, error);
        };

        dataFactory.removeAbilityFromGhost = function (ghostId, success, error) {
            return $http.put(urlGhostId.replace("{id}", ghostId)).then(success, error);
        };
        //TODO
        dataFactory.setGhostToHauntInHouse = function (ghostId, success, error) {
            return $http.put(urlGhostId.replace("{id}", ghostId)).then(success, error);
        };

        dataFactory.createGhost = function (ghost, success, error) {
            return $http.post(urlGhost, ghost).then(success, error);
        };

        dataFactory.deleteGhost = function (ghostId, success, error) {
            return $http.delete(urlGhostId.replace("{id}", ghostId)).then(success, error);
        };

        return dataFactory;
    }
]);

hauntedHousesServices.factory('loggedUserFactory', ['$http',
    function ($http) {
        var urlUser = urlBase.concat("/person");
        var dataFactory = {};

        dataFactory.getPrincipal = function (success, error) {
            return $http.get(urlUser).then(success, error);
        };

        return dataFactory;
    }
]);