angular.module("vigilantos").controller('NavbarController', 
		function ($scope, api, $uibModal, toastr) {
    $scope.oneAtATime = false;

    $scope.status = {
      isFirstOpen: true,
      isSecondOpen: true,
      isThirdOpen: true
    };  
    
 });
