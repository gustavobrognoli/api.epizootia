angular.module("vigilantos")
	.controller("DengueCasosImportacaoController", 
		function($scope, FileUploader, toastr, api, $location) {
	
	api.usuario.getUserOnline().then(function(response){
		$scope.usuarioOnline = response.data;
		$scope.roles = $scope.usuarioOnline.roles;
		
		$scope.getPermissoes();
	});
	
    $scope.getPermissoes = function(){
    	var possuiPermissao = false;
		
    	for(i=0; i < $scope.roles.length; i++ ){
			var role = $scope.roles[i];
	
			if( role.modulo.nome != "dengue" ){
				possuiPermissao = false;
				
				if( role.descricao == "admin" ){
					possuiPermissao = true;
					break;
				}
			}
    	}
    	
    	if( !possuiPermissao ){
			$location.path("/error403");
		}
	}	
		
		
	$scope.fileDBF = {};	

	var uploader = $scope.uploader = new FileUploader({
    	url: './rest/upload/file' ,
    	data: {file: $scope.fileDBF}
    });

    // FILTERS
    
    uploader.filters.push({
      name: 'customFilter',
      fn: function(item, options) {
    	  var exts = item.name.split('.');
    	  var ext = exts[exts.length -1];
    	  if (ext.toUpperCase() == "DBF"){
    		return this.queue.length < 10;
    	  }else {
    		  toastr.error('Extensão do arquivo deve ser .DBF');
    		  return false;  
    	  }
    	  
      }
    });

//    uploader.filters.push({
//      name: 'imageFilter',
//      fn: function(item /*{File|FileLikeObject}*/, options) {
//          var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
//          return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
//      }
//    });

    // CALLBACKS

    uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
    };
    uploader.onAfterAddingFile = function(fileItem) {
    };
    uploader.onAfterAddingAll = function(addedFileItems) {
    };
    uploader.onBeforeUploadItem = function(item) {
    };
    uploader.onProgressItem = function(fileItem, progress) {
    };
    uploader.onProgressAll = function(progress) {
    };
    uploader.onSuccessItem = function(fileItem, response, status, headers) {
    	toastr.success('Importação efetuada com sucesso. '+ response );
    	
    	api.dengue.updateGeodataCasos().then(function(response){
			toastr.success('Latitude/Longitude atualizados');
    	});
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