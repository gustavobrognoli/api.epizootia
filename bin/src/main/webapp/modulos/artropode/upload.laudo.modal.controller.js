//angular.module("vigilantos").controller('UploadLaudoModalController', function ($scope, $location, api, utils, $routeParams, toastr, $uibModal, $uibModalInstance, FileUploader, amostra) {
//
////	$scope.ficha = ficha;
//	$scope.status = [];
//	api.usuario.getUserOnline().then(function(response){
//		$scope.usuarioOnline = response.data;
//		$scope.roles = $scope.usuarioOnline.roles;
//		
//		//$scope.getPermissoes();
//	});
//    
//	$scope.fechar = function(){
//		$uibModalInstance.close($scope.status);
//	}
//	
//		
//	$scope.arquivo = {};	
//	
//
//	var uploader = $scope.uploader = new FileUploader({
//    	url: './rest/file-laudo/upload/'+amostra.id,
//    	data: {file: $scope.arquivo}
//    });
//	
//    // FILTERS
//    uploader.filters.push({
//      name: 'imageFilter',
//      fn: function(item /*{File|FileLikeObject}*/, options) {
//          var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
//          return '|jpg|png|jpeg|bmp|gif|pdf|doc|docx|xls|xlsx'.indexOf(type) !== -1;
//      }
//    });
//
//    // CALLBACKS
//    uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
//    };
//    uploader.onAfterAddingFile = function(fileItem) {
//    };
//    uploader.onAfterAddingAll = function(addedFileItems) {
//    	
//    };
//    uploader.onBeforeUploadItem = function(item) {
//    	$scope.arquivo.nome = item.file.name;
//    };
//    uploader.onProgressItem = function(fileItem, progress) {
//    };
//    uploader.onProgressAll = function(progress) {
//    	
//    };
//    uploader.onSuccessItem = function(fileItem, response, status, headers) {
//    	toastr.success(response);
//    	$scope.status.push(status);
//    	$scope.amostra = amostra;
//    };
//    uploader.onErrorItem = function(fileItem, response, status, headers) {
//    	$scope.status.push(status);
//    };
//    uploader.onCancelItem = function(fileItem, response, status, headers) {
//    	$scope.status.push(status);
//    };
//    uploader.onCompleteItem = function(fileItem, response, status, headers) {
//    };
//
//    uploader.onCompleteAll = function() {
//    	
//    };  
//    
//    
//    $scope.uploadAll = function() {
//    	uploader.uploadAll();
//    }
//});