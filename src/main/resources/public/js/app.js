'use strict';

angular.module('myApp', [])
  .controller('GameController', function($scope, $http){
    $scope.$watch('search', function() {
      fetch();
    });

    $scope.search = "";

    function fetch(){
      $http.get("http://localhost:8080/games")
      .then(function(response){ $scope.gamesList = response.data; });

      //$http.get("http://localhost:8080/games" + $scope.search)
      //.then(function(response){ $scope.related = response.data; });
      
    }

    $scope.update = function(games){
      $scope.search = games.gameName;	
    };

    $scope.select = function(){
      this.setSelectionRange(0, this.value.length);
    }
  });
