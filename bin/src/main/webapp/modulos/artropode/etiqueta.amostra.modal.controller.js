//angular.module("vigilantos").controller('EtiquetaAmostaModalController', function ($scope, $location, api, utils, $routeParams, toastr, $uibModal, $uibModalInstance, amostra) {
//
//	$scope.amostra = amostra;
//	api.usuario.getUserOnline().then(function(response){
//		$scope.usuarioOnline = response.data;
//		$scope.roles = $scope.usuarioOnline.roles;
//		
//	});
//
//	$scope.fechar = function(){
//		$uibModalInstance.close();
//	}
//
//	$scope.geraEtiqueta = function(){
//		if($scope.form.$valid){
//
//			if($scope.amostra.id > 0){
//				
//				$scope.filtro.id = $scope.amostra.id;
//				$scope.filtro.id = (typeof $scope.filtro.id == 'undefined' ? -1 : ($scope.filtro.id == null ? -1 : $scope.filtro.id));	
//			}
//			
//			$scope.filtro.type = "pdf";
//			$scope.filtro.nuExemplares = (typeof $scope.filtro.nuExemplares == 'undefined' ? -1 : ($scope.filtro.nuExemplares == null ? -1 : $scope.filtro.nuExemplares));
//				
//			api.artropodes.fichaAmostra.geraEtiqueta($scope.filtro);
//			
//			$scope.fechar();
//			
//		}
//	}
//	
//});