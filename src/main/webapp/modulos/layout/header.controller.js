angular.module("vigilantos").controller('HeaderController', function ($scope, $rootScope, api, toastr) {

	$scope.totalNaoLidas = 0;
	$scope.user = [];
	
//	$scope.usuarioOnline = function(){
//		
//		api.usuario.getUserOnline().then(function(response){
//			$scope.user = response.data;
//
//	   		 api.mensagem.getNaoLidas( $scope.user.id ).then(function(result){
//	   			$scope.totalNaoLidas = result.headers("x-naolidas");
//			 });
//			
////			$rootScope.ws = api.websocket( "/mensagens" );
////			
////			$rootScope.ws.send( $scope.user.id );
////			$rootScope.ws.onMessage(function(event) {
////				$scope.totalNaoLidas = event.data;
////			});
//		});
//	}
//	
//	$scope.usuarioOnline();

});