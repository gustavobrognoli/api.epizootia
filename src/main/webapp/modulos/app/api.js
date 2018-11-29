angular.module("vigilantos").factory("api", function($http, $window, $filter, $websocket){

	return{    
// =============================================================================================//
// EPIZOOTIA
// =============================================================================================//
		 epizootia:{
	        
			 animal:{
	        	insert: function( animal ){
	        		return $http.post("./api/animal", animal);
	        	},
	        	getAll: function(){
	        		return $http.get("./api/animal");
	        	},
	        	get: function( id ){
	        		return $http.get("./api/animal/" + id);
	            },
	            excluir: function( id ){
	        		return $http.delete("./api/animal/" + id);
	            }
	        },
	        
	        nomePopular:{
	        	insert: function( nomePopular ){
	        		return $http.post("./api/nomePopular", nomePopular);
	        	},
	        	getAll: function(){
	        		return $http.get("./api/nomePopular");
	        	},
	            excluir: function( id ){
	        		return $http.delete("./api/nomePopular" + id);
	            }
	        },
	        
	        especie:{
	        	insert: function( especie ){
	        		return $http.post("./api/especie", especie);
	        	},
	        	getAll: function(){
	        		return $http.get("./api/especie");
	        	},
	            excluir: function( id ){
	        		return $http.delete("./api/especie/" + id);
	            }
	        },
	        	        
	        idade:{
	        	insert: function( idade ){
	        		return $http.post("./api/idade", idade);
	        	},
	        	getAll: function(){
	        		return $http.get("./api/idade");
	        	},
	            excluir: function( id ){
	        		return $http.delete("./api/idade/" + id);
	            }
	        },
	        
	        sexo:{
	        	insert: function( sexo ){
	        		return $http.post("./api/sexo", sexo);
	        	},
	        	getAll: function(){
	        		return $http.get("./api/sexo");
	        	},
	            excluir: function( id ){
	        		return $http.delete("./api/sexo/" + id);
	            }
	        },
	        
	        situacao:{
	        	insert: function( situacao ){
	        		return $http.post("./api/situacao", situacao);
	        	},
	        	getAll: function(){
	        		return $http.get("./api/situacao");
	        	},
	            excluir: function( id ){
	        		return $http.delete("./api/situacao/" + id);
	            }
	        },
	        
	        tempoObito:{
	        	insert: function( tempoObito ){
	        		return $http.post("./api/tempoObito", tempoObito);
	        	},
	        	getAll: function(){
	        		return $http.get("./api/tempoObito");
	        	},
	            excluir: function( id ){
	        		return $http.delete("./api/tempoObito/" + id);
	            }
	        },

	        vidaLivre:{
	        	insert: function( vidaLivre ){
	        		return $http.post("./api/vidaLivre", vidaLivre);
	        	},
	        	getAll: function(){
	        		return $http.get("./api/vidaLivre");
	        	},
	            excluir: function( id ){
	        		return $http.delete("./api/vidaLivre/" + id);
	            }
	        },
	        
	        apreensao:{
	        	insert: function( apreensao ){
	        		return $http.post("./api/apreensao", apreensao);
	        	},
	        	getAll: function(){
	        		return $http.get("./api/apreensao");
	        	},
	            excluir: function( id ){
	        		return $http.delete("./api/apreensao/" + id);
	            }
	        },
	        
	        cativeiro:{
	        	insert: function( cativeiro ){
	        		return $http.post("./api/cativeiro", cativeiro);
	        	},
	        	getAll: function(){
	        		return $http.get("./api/cativeiro");
	        	},
	            excluir: function( id ){
	        		return $http.delete("./api/cativeiro/" + id);
	            }
	        },
	        
	        viscera:{
	        	insert: function( viscera ){
	        		return $http.post("./api/viscera", viscera);
	        	},
	        	getAll: function(){
	        		return $http.get("./api/viscera");
	        	},
	            excluir: function( id ){
	        		return $http.delete("./api/viscera/" + id);
	            }
	        },
	        
	        anormalidade:{
	        	insert: function( anormalidade ){
	        		return $http.post("./api/anormalidade", anormalidade);
	        	},
	        	getAll: function(){
	        		return $http.get("./api/anormalidade");
	        	},
	            excluir: function( id ){
	        		return $http.delete("./api/anormalidade/" + id);
	            }
	        },
	        
	        localidade:{
	        	insert: function( localidade ){
	        		return $http.post("./api/localidade", localidade);
	        	},
	        	getAll: function(){
	        		return $http.get("./api/localidade");
	        	},
	            excluir: function( id ){
	        		return $http.delete("./api/localidade/" + id);
	            }
	        },
	        
	        morador:{
	        	insert: function( morador ){
	        		return $http.post("./api/morador", morador);
	        	},
	        	getAll: function(){
	        		return $http.get("./api/morador");
	        	},
	            excluir: function( id ){
	        		return $http.delete("./api/morador/" + id);
	            }
	        },

	        corposAgua:{
	        	insert: function( corposAgua ){
	        		return $http.post("./api/corposAgua", corposAgua);
	        	},
	        	getAll: function(){
	        		return $http.get("./api/corposAgua");
	        	},
	            excluir: function( id ){
	        		return $http.delete("./api/corposAgua/" + id);
	            }
	        },
	        
	        situacaoFundiaria:{
	        	insert: function( situacaoFundiaria ){
	        		return $http.post("./api/situacaoFundiaria", situacaoFundiaria);
	        	},
	        	getAll: function(){
	        		return $http.get("./api/situacaoFundiaria");
	        	},
	            excluir: function( id ){
	        		return $http.delete("./api/situacaoFundiaria/" + id);
	            }
	        },

	        unidadeConservacao:{
	        	insert: function( unidadeConservacao ){
	        		return $http.post("./api/unidadeConservacao", unidadeConservacao);
	        	},
	        	getAll: function(){
	        		return $http.get("./api/unidadeConservacao");
	        	},
	            excluir: function( id ){
	        		return $http.delete("./api/unidadeConservacao/" + id);
	            }
	        },
	        
	        registroEntomologico:{
	        	insert: function( registroEntomologico ){
	        		return $http.post("./api/registroEntomologico", registroEntomologico);
	        	},
	        	getAll: function(){
	        		return $http.get("./api/registroEntomologico");
	        	},
	            excluir: function( id ){
	        		return $http.delete("./api/registroEntomologico/" + id);
	            }
	        },
	        
	        metodoCaptura:{
	        	insert: function( metodoCaptura ){
	        		return $http.post("./api/metodoCaptura", metodoCaptura);
	        	},
	        	getAll: function(){
	        		return $http.get("./api/metodoCaptura");
	        	},
	            excluir: function( id ){
	        		return $http.delete("./api/metodoCaptura/" + id);
	            }
	        },
	        
	        impacto:{
	        	getAll: function(){
	        		return $http.get("./api/impacto");
	            }
	        },
	        
	        caracteristica:{
	        	getAll: function(){
	        		return $http.get("./api/caracteristica");
	            }
	        },
	        
	        equipamento:{
	        	getAll: function(){
	        		return $http.get("./api/equipamento");
	            }
	        },
	        
	        genero:{
	        	getAll: function(){
	        		return $http.get("./api/genero");
	            }
	        },
	        
	        resultado:{
	        	insert: function( resultado ){
	        		return $http.post("./api/classificacaoFA", resultado);
	        	},
	        	getAll: function(){
	        		return $http.get("./api/classificacaoFA");
	        	},
	            excluir: function( id ){
	        		return $http.delete("./api/classificacaoFA/" + id);
	            }
	        },
	        
	        ficha:{
	        	insert: function( resultado ){
	        		return $http.post("./api/ficha", ficha);
	        	},
	        	getAll: function(){
	        		return $http.get("./api/ficha");
	        	},
	            get: function( id ){
	        		return $http.get("./api/ficha/" + id);
	            },
	            excluir: function( id ){
	        		return $http.delete("./api/ficha/" + id);
	            },
	            getClassificao: function( id ){
	            	return $http.get("./api/ficha/classificacao/" + id);
	            }
	        },
	    }, 

// =============================================================================================//
// MENSAGEM //
// =============================================================================================//
		mensagem:{
			insert: function( msgn ){
				return $http.post("./rest/mensagem", msgn );
			},
			update: function( msgn ){
				return $http.put("./rest/mensagem", msgn );
			},
			excluir: function( id ){
				return $http.delete("./rest/mensagem/"+ id );
			},
			get: function(id){
				return $http.get("./rest/mensagem/"+ id);
			},
			getNaoLidas: function(idUser){
				return $http.get("./rest/mensagem/naolidas/"+ idUser );
			},
			getRecebidas: function( filtro ){
				return $http.post("./rest/mensagem/recebidas", filtro );
			},
			getEnviadas: function( filtro ){
				return $http.post("./rest/mensagem/enviadas", filtro );
			},
			updateLida: function( id ){
				return $http.put("./rest/mensagem/update-lida/"+ id );
			}
		},  
	}
});