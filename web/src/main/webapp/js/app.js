var module = angular.module('hauntedHouses', ['ngRoute', 'hauntedHousesControllers', 'hauntedHousesServices']);

module.config(function ($routeProvider) {

    $routeProvider
            .when('/', {
                templateUrl: 'partials/home.html'
            })
            .when('/forbidden', {
                templateUrl: 'partials/forbidden.html'
            })
            .otherwise({redirectTo: '/'});

});

/*
module.run(function ($rootScope, $location, $window, loggedUserFactory) {

    loggedUserFactory.getPrincipal(
            function (response) {

                var values = JSON.parse(response.data);

                $rootScope.principal = values.username;
                $rootScope.role = values.role;
            },
            function (response) {
                alert("An error occurred when getting the logged user.");
            }
    );

    $rootScope.unsuccessfulResponse = function (response) {
        if (response.status === 403) {
            $rootScope.page = $location.path();
            $location.path("/forbidden");
        } else if (response.status === 401) {
            $window.location.href = "login.html";
        } else if (response.status === 400 || response.status === 409) {
            document.getElementById('errorOutput').style.display = 'block';
            setTimeout(function () {
                document.getElementById('errorOutput').style.display = 'none';
            }, 3000);
        }
    };

});
*/