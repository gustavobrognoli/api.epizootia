angular.module("vigilantos").controller("AboutController", function($scope){
    
	$scope.ano = {};
	
	$scope.getAno = function(){

		var data = new Date();
		var anoAtual = data.getFullYear();
		
		$scope.ano = "2006-"+ anoAtual;
	}
	
	$scope.getAno();
	
});