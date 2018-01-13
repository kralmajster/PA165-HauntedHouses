var hauntedHousesControllers = angular.module("hauntedHousesControllers", ['hauntedHousesServices']);

hauntedHousesControllers.controller('housesCtrl', function ($scope, $http, $rootScope, houseFactory) {
    houseFactory.getAllHouses(
            function (response) {
                $scope.houses = response.data;
            },
            $rootScope.unsuccessfulResponse
            );

    $scope.deleteHouse = function (house) {
        $http.delete('/pa165/rest/house/delete/{id}'.replace("{id}", house.id))
        .then(function success(response) {
                houseFactory.getAllHouses(
                    function (response) {
                        $scope.houses = response.data;
                    },
                    $rootScope.unsuccessfulResponse
                );
            }, function error(response) {
                $rootScope.unsuccessfulResponse;
            });
    };
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


hauntedHousesControllers.controller('newHouseCtrl',
    function ($scope, $routeParams, $http, $location, $rootScope, houseFactory) {        
        
        houseFactory.getAllPeople(
                function (response) {
                    $scope.persons = response.data;
                },
                $rootScope.unsuccessfulResponse
                );
        
        $scope.house = {
            'name': '',
            'address': '',
            'ownerID': ''
            
        };

        $scope.create = function (house) {
             $http({
                method: 'POST',
                url: '/pa165/rest/house/create',
                data: house
            }).then(function success(response) {
                //change view to list of products
                $location.path("/houses");
            }, function error(response) {
                $rootScope.unsuccessfulResponse;
            });
        };
   
});

hauntedHousesControllers.controller('updateHouseCtrl',
    function ($scope, $routeParams, $http, $location, $rootScope, houseFactory) {

    houseFactory.getHouse(
            $routeParams.id,
            function (response) {
                $scope.house = response.data;
            },
            $rootScope.unsuccessfulResponse
        );

        houseFactory.getAllPeople(
                function (response) {
                    $scope.persons = response.data;
                },
                $rootScope.unsuccessfulResponse
            );

        $scope.house = {
            'name': '',
            'address': '',
            'ownerID': ''
        };


        $scope.update = function (house) {
            $http({
                method: 'PUT',
                url: '/pa165/rest/house/update',
                data: house
            }).then(function success(response) {
                //change view to list of products
                $location.path("/houses");
            }, function error(response) {
                $rootScope.unsuccessfulResponse;
            });
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

hauntedHousesControllers.controller('newGhostCtrl', function ($scope, $rootScope, $location, houseFactory, ghostFactory) {
    $scope.ghost = {
        "name": '',
        "hauntFrom": '1992-05-13 00:00',
        "hauntTo": '1999-05-13 00:00',
    };


    var test = {
        "name":"ddd234ddd",
        "hauntFrom":"1992-05-13 00:00",
        "hauntTo":"1999-05-13 00:00"
        // "house": {
        //     "id":1,
        //     "name":"The Hovel",
        //     "address":"Bloodcurdling 666",
        //     "becameHauntedDate":"1762-12-24 00:00",
        //     "history":"The hovel is an awful home of witches and their helpers.",
        //     "ownerID":2
        // }
    };
    //
    // var house = {
    //     "id":1,
    //     "name":"The Hovel",
    //     "address":"Bloodcurdling 666",
    //     "becameHauntedDate":"1762-12-24 00:00",
    //     "history":"The hovel is an awful home of witches and their helpers.",
    //     "ownerID":2
    // }


    houseFactory.getAllHouses(
        function (response) {
            $scope.houses = response.data;
        },
        $rootScope.unsuccessfulResponse
    );

    $scope.create = function() {
        ghostFactory.createGhost(test,//$scope.ghost,
            function (response) {
                $scope.ghost = response.data;
                $location.path("/ghosts");
            },
            $rootScope.unsuccessfulResponse
        );
    };
});


/**
 * ABILITIES
 * ABILITIES
 * ABILITIES
 * ABILITIES
 * ABILITIES 
 */

hauntedHousesControllers.controller('abilitiesCtrl', function ($scope, $http, $location, $rootScope, abilityFactory) {
    abilityFactory.getAllAbilities(
            function (response) {
                $scope.abilities = response.data;
            },
            $rootScope.unsuccessfulResponse
            );
    $scope.deleteAbility = function (ability) {
        $http.delete('/pa165/rest/ability/delete/{id}'.replace("{id}", ability.id))
            .then(function success(response) {
                abilityFactory.getAllAbilities(
                    function (response) {
                        $scope.abilities = response.data;
                    },
                    $rootScope.unsuccessfulResponse
                    );
            }, function error(response) {
                $rootScope.unsuccessfulResponse;
            });
    };
    
    $scope.find = function (searchText) {
        $http.get('/pa165/rest/ability/search/{searchText}'.replace("{searchText}", searchText))
            .then(function success(response) {
                ab = response.data;
                if ( ab.constructor === Array ) {
                    $scope.abilities = ab;                    
                } else {
                    $scope.abilities = [];
                    $scope.abilities.push(ab);
                }
            }, function error(response) {
                $rootScope.unsuccessfulResponse;
            });
        };
    
});

hauntedHousesControllers.controller('abilityDetailCtrl', function ($scope, $routeParams, $rootScope, abilityFactory) {
    abilityFactory.getAbility(
            $routeParams.id,
            function (response) {
                $scope.ability = response.data;
            },
            $rootScope.unsuccessfulResponse
            );
    abilityFactory.getGhostsOfAbility(
            $routeParams.id,
            function (response) {
                $scope.ghosts = response.data;            
            },
            $rootScope.unsuccessfulResponse            
            );
    
});

hauntedHousesControllers.controller('newAbilityCtrl',
    function ($scope, $routeParams, $http, $location, $rootScope, abilityFactory) {

        abilityFactory.getAbilityTypes(
                function (response) {
                    $scope.types = response.data;
                },
                $rootScope.unsuccessfulResponse
                );
        
        //$scope.toopes = ['NOISE', 'FIRE', 'DARKNESS', 'GORE'];
        
        $scope.ability = {
            'name': '',
            'description': '',
            'type': '' //this shit is causing some problems, not when creating the enum in controller
        };

        $scope.create = function (ability) {
             $http({
                method: 'POST',
                url: '/pa165/rest/ability/create',
                data: ability
            }).then(function success(response) {
                //change view to list of abilities
                $location.path("/abilities");
            }, function error(response) {
                $rootScope.unsuccessfulResponse;
            });
        };
});

hauntedHousesControllers.controller('updateAbilityCtrl',
    function ($scope, $routeParams, $http, $location, $rootScope, abilityFactory) {
        abilityFactory.getAbilityTypes(
                function (response) {
                    $scope.types = response.data;
                },
                $rootScope.unsuccessfulResponse
                );
        
        abilityFactory.getAbility(
            $routeParams.id,
            function (response) {
                $scope.ability = response.data;
            },
            $rootScope.unsuccessfulResponse
            );
        //$scope.upd.id = $scope.ability.id;
        $scope.update = function (ab) {
             $http({
                method: 'PUT',
                url: '/pa165/rest/ability/update',
                data: ab
            }).then(function success(response) {
                //change view to list of abilities
                $location.path("/abilities");
            }, function error(response) {
                $rootScope.unsuccessfulResponse;
            });
        };
});