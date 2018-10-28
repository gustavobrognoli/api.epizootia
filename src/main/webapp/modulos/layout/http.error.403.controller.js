angular.module("vigilantos").controller('HttpError403Controller', function ($scope, $location) {
	
	$scope.home = function(){
		$location.path("/home");
	}
	
});