angular.module("vigilantos").controller("GraficoComparativoAnualController", function($scope, api) {
	
	$scope.options = {legend: {display: true}};
	$scope.options.legend.position = "top";
	
	
	$scope.getFocos = function(){
		api.dengueDashboard.getAnosMes().then(function(response){

			var anos = [];
			for( i=0; i < response.data.length; i++){
				var ano = data[i].ano;
				
				var achou = false;
				for( j=0; j < anos.length; j++){
					var busca = anos[j];
					if( busca == ano ){
						achou = true;
						break;
					}
				}
				
				if( !achou ){
					anos.push( ano );
				}
			}
			
			$scope.series = anos;
			
			var axis = false;
			var cor = 0;

			var resultados = []; 
			var labels = [];
			
			for(i=0; i < anos.length; i++) {
				var a = anos[i];
				
				var nova = [];
				var novaLabel = [];
				for( j=0; j < response.data.length; j++){
					var o = data[j];
					
					if ( o.ano == a){
						nova.push( o );
						novaLabel.push( "Ano: "+ o.ano + " Mês: "+ o.mesExtenso + " Focos: "+ o.total );
					} 
				}
			
				resultados.push( nova );
				labels.push( novaLabel );
			}
			
			$scope.labels = labels;
			$scope.data = resultados;
			
//				//Cria uma nova serie e seta as propriedades
//				var localSeries:LineSeries = new LineSeries();
//				localSeries.dataProvider = nova;
//				localSeries.yField = "total";
//				localSeries.xField = "mes";
//				localSeries.setStyle("form","curve");
//				
//				var s1:Stroke = new Stroke( Variaveis.getInstance().getColor(cor++) ,3,.7); 
//				localSeries.setStyle("lineStroke", s1);
//				
//				// Valor que sera exibido no tooltip e no ano
//				localSeries.displayName = String(a);
//				
//				// Back up the current series on the chart.
//				var currentSeries:Array = chartLinha.series;
//				// Add the new series to the current Array of series.
//				currentSeries.push(localSeries);
//				// Add the new Array of series to the chart.
//				chartLinha.series = currentSeries;
//				
//				if ( ! axis ){
//					this.hAxis.dataProvider = nova;
//					this.hAxis.labelFunction = categoryAxis_labelFunc;
//					axis = true;
//				}
//			}
			
			console.info( anos );
			
		});
	}
	
	$scope.getFocos();
	
});