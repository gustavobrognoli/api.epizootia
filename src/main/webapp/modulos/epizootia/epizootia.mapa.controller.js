angular.module("vigilantos").controller("EpizootiaMapaController", function($scope, api, utils, NgMap, $location){
			
			$scope.mapa = {};
			$scope.isEdicao = false;
			$scope.dengueFiltro = {};
			$scope.isOpen = false;
			
			$scope.registros = [
				{posicao: [-27.593854, -48.577817]},
				{posicao: [-27.595385, -48.577163]},	
				{posicao: [-27.596483, -48.580190]},
				{posicao: [-27.598738, -48.571219]},
				{posicao: [-27.597326, -48.570806]}
			];
			
			$scope.registrosFA = [
				{posicao: [-27.592392, -48.581440]},
				{posicao: [-27.586005, -48.586490]},	
				{posicao: [-27.598738, -48.571219]},
				{posicao: [-27.597326, -48.570806]}
			];

			$scope.openMenu = function(){
				$scope.isOpen = true;
			}
			
//			$scope.getMunicipipoSC = function(){
//				api.municipio.getByUF('SC').then(function(response){
//					$scope.municipios = response.data;
//				});
//			}
			
				
//			$scope.getByMunicipio = function(){
//				$scope.dengueFiltro.idMunicipio = -1;
//				
//		        if( $scope.municipio != null ){
//		        	$scope.dengueFiltro.idMunicipio = $scope.municipio.id;
//		        	
//		        	api.dengue.getLocalidades( $scope.municipio ).then(function(response){
//		        		$scope.localidades = response.data;
//		        	});
//		        }
//		        else{
//		        	$scope.localidades = [];
//		        }
//		    }
			
			// armazena no escopo
			NgMap.getMap().then(function(map) {
			    $scope.googlemaps = map;
			});
			
			$scope.init = function(){
				$scope.mapa.posicao = [-27.4658, -50.7504];
				$scope.mapa.zoom = 8;

				$scope.dengueFiltro.dtColetaFim = new Date();
				$scope.dengueFiltro.dtColetaInicio = utils.dateUtils.getInicioAno();			
				
			}
			
			$scope.abrirInfo = function(event, $index){
					$scope.googlemaps.showInfoWindow("epizootia-info", this);
			}
	
			$scope.init();

		});