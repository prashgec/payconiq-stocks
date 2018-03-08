angular.module('myApp', [])
.controller('StockController', function($scope, $http) {
    $http.get('http://localhost:8181/api/stocks').
        then(function(response) {
            $scope.stocks = response.data;
        });
});
