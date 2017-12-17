var hauntedHousesControllers = angular.module("hauntedHousesControllers", ['hauntedHousesServices']);

hauntedHousesControllers.controller('housesCtrl', function ($scope, $rootScope, houseFactory) {
    houseFactory.getAllHouses(
            function (response) {
                $scope.houses = response.data;
            },
            $rootScope.unsuccessfulResponse
            );
});

hauntedHousesControllers.controller('houseDetailCtrl', function ($scope, $routeParams, $rootScope, houseFactory) {
    houseFactory.getHouse(
            $routeParams.id,
            function (response) {
                $scope.house = response.data;
            },
            $rootScope.unsuccessfulResponse
            );
});

// unfinished, doesn't work:
// ---------------
hauntedHousesControllers.controller('newHouseCtrl', function ($scope, $rootScope, $location, houseFactory) {
    $scope.house = {
        'name': '',
        'address': ''
    };
    $scope.create = function() {
        houseFactory.createHouse($scope.house,
            function (response) {
                $scope.house = response.data;
                //$location.path("/houses");
            },
            $rootScope.unsuccessfulResponse
        );
    };
});
// ---------------

hauntedHousesControllers.controller('ghostsCtrl', function ($scope, $rootScope, ghostFactory) {
    ghostFactory.getAllGhosts(
            function (response) {
                $scope.ghosts = response.data;
            },
            $rootScope.unsuccessfulResponse
            );
});

hauntedHousesControllers.controller('ghostDetailCtrl', function ($scope, $routeParams, $rootScope, ghostFactory) {
    ghostFactory.getGhost(
            $routeParams.id,
            function (response) {
                $scope.ghost = response.data;
            },
            $rootScope.unsuccessfulResponse
            );
});

hauntedHousesControllers.controller('abilitiesCtrl', function ($scope, $rootScope, abilityFactory) {
    abilityFactory.getAllAbilities(
            function (response) {
                $scope.abilities = response.data;
            },
            $rootScope.unsuccessfulResponse
            );
});

hauntedHousesControllers.controller('abilityDetailCtrl', function ($scope, $routeParams, $rootScope, abilityFactory) {
    abilityFactory.getAbility(
            $routeParams.id,
            function (response) {
                $scope.ability = response.data;
            },
            $rootScope.unsuccessfulResponse
            );
});