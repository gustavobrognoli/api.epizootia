angular.module("vigilantos").controller('UploadAmostraModalController', function ($scope, $location, api, utils, $routeParams, toastr, $uibModal, $uibModalInstance, FileUploader, amostra) {

	$scope.amostra = amostra;

	api.usuario.getUserOnline().then(function(response){
		$scope.usuarioOnline = response.data;
		$scope.roles = $scope.usuarioOnline.roles;
		
		//$scope.getPermissoes();
	});
    
	$scope.fechar = function(){
		
		$uibModalInstance.close($scope.arquivo);
		
	}
	
//    $scope.getPermissoes = function(){
//    	var possuiPermissao = false;
//		
//    	for(i=0; i < $scope.roles.length; i++ ){
//			var role = $scope.roles[i];
//	
//			if( role.modulo.nome != "dengue" ){
//				possuiPermissao = false;
//				
//				if( role.descricao == "admin" ){
//					possuiPermissao = true;
//					break;
//				}
//			}
//    	}
//    	
//    	if( !possuiPermissao ){
//			$location.path("/error403");
//		}
//	}	
		
		
	$scope.arquivo = {};	
	
	

	
	var uploader = $scope.uploader = new FileUploader({
		url: './rest/file-amostra/upload/'+amostra.id,
		data: {file: $scope.arquivo}
	});
	// FILTERS
	uploader.filters.push({
		name: 'imageFilter',
		fn: function(item /*{File|FileLikeObject}*/, options) {
			var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
			return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
		}
	});
	
	// CALLBACKS
	uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
	};
	uploader.onAfterAddingFile = function(fileItem) {
	};
	uploader.onAfterAddingAll = function(addedFileItems) {
		
	};
	uploader.onBeforeUploadItem = function(item) {
		$scope.arquivo.nome = item.file.name;
	};
	uploader.onProgressItem = function(fileItem, progress) {
	};
	uploader.onProgressAll = function(progress) {
		
	};
	uploader.onSuccessItem = function(fileItem, response, status, headers) {
		toastr.success(response );
		//$scope.fechar();
	};
	uploader.onErrorItem = function(fileItem, response, status, headers) {
	};
	uploader.onCancelItem = function(fileItem, response, status, headers) {
	};
	uploader.onCompleteItem = function(fileItem, response, status, headers) {
	};
	
	uploader.onCompleteAll = function() {
		
	};
	
	
});