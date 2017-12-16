var hauntedHousesControllers = angular.module("hauntedHousesControllers", ['hauntedHousesServices']);

hauntedHousesControllers.controller('housesCtrl', function ($scope, $rootScope, houseFactory) {
    houseFactory.getAllHouses(
            function (response) {
                $scope.houses = response.data;
            },
            $rootScope.unsuccessfulResponse
            );
});