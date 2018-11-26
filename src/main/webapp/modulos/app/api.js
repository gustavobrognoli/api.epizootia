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
// USUARIO //
// =============================================================================================//
		websocket: function(path) {
		    var protocolPrefix = (window.location.protocol === 'https:') ? 'wss:' : 'ws:';
		    return $websocket(protocolPrefix + '//' 
		    				+ location.host + '/'
		    				+ location.pathname + path);
		},
/*		usuario:{
			insert: function(user){
				return $http.post("./rest/usuario", user);
			},
			update: function(user){
				return $http.put("./rest/usuario", user);
			},
			get: function(id){
				return $http.get("./rest/usuario/"+id);
			},
			getUserOnline: function(){
				return $http.get("./rest/usuario/useronline");
			},
			isUserInrole: function( role ){
				return $http.get("./rest/usuario/isuserinrole/"+ role );
			},
			redirectOld: function(){
				$window.open("./rest/usuario/redirect-old", "_blank");
			},
			getDestinatarios: function(){
				return $http.get("./rest/usuario/destinatarios");
			}
		},*/
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

// =============================================================================================//
// MUNICIPIO //
// =============================================================================================//
		municipio:{
			insert: function(municipio){
				return $http.post("./rest/municipio", municipio);
			},
			update: function(municipio){
				return $http.put("./rest/municipio", municipio);
			},
			get: function(id){
				return $http.get("./rest/municipio/"+id);
			},
			getAll:function(){
				return $http.get("./rest/municipio");
			},
            getByUF:function(uf){
                return $http.get("./rest/municipio/municipios/"+uf);
            },
            getByUserId:function(id){
                return $http.get("./rest/municipio/municipios-by-userid/"+id);
            },
            getByRegionalId:function(id){
            	return $http.get("./rest/municipio/municipios-by-regionalid/"+id);
            }
		},
// =============================================================================================//
// REGIONAL //
// =============================================================================================//
		regional:{
			insert: function(regional){
				return $http.post("./rest/regional", regional);
			},
			update: function(municipio){
				return $http.put("./rest/regional", regional);
			},
			get: function(id){
				return $http.get("./rest/regional/"+id);
			},
			getAll:function(){
				return $http.get("./rest/regional");
			},
			getByMunicipio:function(idMunicipio){
				return $http.get("./rest/regional/municipio/"+ idMunicipio );
			}
		},
// =============================================================================================//
// SEMANA //
// =============================================================================================//
		semana:{
			getAll:function(ano){
				return $http.get("./rest/semana/"+ ano);
			},
			getSemanaAtual:function(dtAtual){
				return $http.post("./rest/semana", dtAtual);
			}
		},
// =============================================================================================//
// LOCALIDADES
// =============================================================================================//
        localidade:{
        	getByMunicipio: function( idMunicipio ){
        		return $http.get("./rest/localidade/by-municipio/"+ idMunicipio);
        	}
        },
// =============================================================================================//
// DENGUE //
// =============================================================================================//
        dengue:{
        	insert: function( focos ){
        		return $http.post("./rest/dengue", focos);
        	},
        	update: function( focos ){
        		return $http.put("./rest/dengue", focos);
        	},
        	delete: function( id ){
          	  return $http.delete("./rest/dengue/"+ id );
        	},
        	get: function( id ){
        		return $http.get("./rest/dengue/"+ id );
        	},
        	getDto: function( filtroDengue ){
        		 return $http.post("./rest/dengue/dto", filtroDengue );
        	},
        	getLocalidades: function( municipio ){
        		 return $http.post("./rest/dengue/localidades", municipio);
        	},
        	getEnderecos: function( localidade ){
        		 return $http.post("./rest/dengue/enderecos", localidade);
        	},
        	getTipoImovel: function(){
        		return $http.get("./rest/dengue/tipoimovel");
        	},
        	getTipoDeposito: function(){
        		return $http.get("./rest/dengue/tipodeposito");
        	},
        	getAtividades: function(){
        		return $http.get("./rest/dengue/atividades");
        	},
        	getRelatorio: function(filtro){
        		
        		$window.open("./rest/dengue/relatorio.xls?" +
        				"type="+ filtro.type +
        				"&idRegional="+ filtro.idRegional +
        				"&idMunicipio="+ filtro.idMunicipio +
        				"&idLocalidade="+ filtro.idLocalidade +
        				"&dtColetaInicio="+ $filter('date')( filtro.dtColetaInicio, 'dd/MM/yyyy') +
        				"&dtColetaFim="+ $filter('date')( filtro.dtColetaFim, 'dd/MM/yyyy') + 
        				"&imovel="+ filtro.imovel +
        				"&deposito="+ filtro.deposito +
        				"&isExistenteAdulto="+ filtro.isExistente +
        				"&idAtividade="+ filtro.idAtividade +
        				"&especie="+ filtro.especie
        				,"_blank");
        	},
        	getDtoMapaById: function( id ){
        		return $http.get("./rest/dengue-mapa/focos/"+ id );
        	},
        	updateLatLonFoco: function( idFoco, latitude, longitude){
        		return $http.post("./rest/dengue-mapa/update-geodata-foco/"+ idFoco +"/"+ latitude +"/"+ longitude)
        	},
        	getMapaFocos: function( filtro ){
        		return $http.post("./rest/dengue-mapa/focos-all", filtro );
        	},
        	getMapaMunicipioFocos: function( inicio, fim, especie ){
        		return $http.get("./rest/dengue-mapa/municipio-focos-all?inicio="+ $filter('date')( inicio, 'dd/MM/yyyy') 
        				+"&fim="+ $filter('date')( fim, 'dd/MM/yyyy')
        				+"&especie="+ especie);
        	},
        	getUploadCaso: function( file ){
        		return $http.post("./rest/upload/file", file );
        	},
        	updateGeodataCasos: function(){
        		return $http.get("./rest/upload/geodata");
        	},
        	getRelatorioFocos: function( inicio, fim ){
        		$window.open("./rest/dengue-relatorio/focos.xls?" +
        				"&dtInicio="+ inicio +
        				"&dtFim="+ fim
        				,"_blank");
        	},
        	getRelatorioGeralFocos: function( inicio, fim ){
        		$window.open("./rest/dengue-relatorio/geral.pdf?" +
        				"&dtInicio="+ inicio +
        				"&dtFim="+ fim
        				,"_blank");
        	},
        	updateGeodata: function(){
        		return $http.get("./rest/dengue/update-geodata");
        	}
        },
        dengueDashboard: {
        	getFocosAno: function(){
        		return $http.get("./rest/dengue-relatorio/focos-ano"); 
        	},
        	getMunicipioFocos: function(ano){
        		return $http.get("./rest/dengue-relatorio/municipio-focos/"+ ano); 
        	},
        	getDepositos: function(ano, municipio){
        		return $http.get("./rest/dengue-relatorio/depositos/"+ ano +"/"+ municipio); 
        	},
        	getImoveis: function(ano, municipio){
        		return $http.get("./rest/dengue-relatorio/imoveis/"+ ano +"/"+ municipio); 
        	},
        	getCasosAno: function(){
        		return $http.get("./rest/dengue-relatorio/casos-ano"); 
        	},
        	getCasosMunicipioAno: function(ano, confirmados){
        		return $http.get("./rest/dengue-relatorio/casos-municipio-ano?ano="+ ano +"&confirmados="+ confirmados );
        	},
        	getAnosMes: function(){
        		return $http.get("./rest/dengue-relatorio/focos-ano-mes");
        	}
        },
        dengueUsuario: {
        	getUsuariosRegionais: function(){
        		return $http.get("./rest/dengue-usuario/usuarios-regionais");
        	},
        	updateAll: function( idUser, lista ){
        		return $http.put("./rest/dengue-usuario/update-all/"+ idUser, lista);
        	},
        	getRegionaisByUser: function( idUser ){
        		return $http.get("./rest/dengue-usuario/regionais-user/"+ idUser );
        	}
        },
        dengueCaso: {
        	getAll: function(dengueFiltros){
        		return $http.post("./rest/dengue-caso/dto", dengueFiltros);
        	},
        	getSinanById: function( id ){
        		return $http.get("./rest/dengue-caso/importados/by-id/"+ id );
        	},
        	getAllImportados: function(dengueFiltros){
        		return $http.post("./rest/dengue-caso/importados/dto", dengueFiltros);
        	},
        	deleteCaso: function(id){
        		return $http.delete("./rest/dengue-caso/vigilantos/"+ id);
        	},
        	getClassificacoes: function(){
        		return $http.get("./rest/dengue-caso/classificacao");
        	},
        	deleteImportado: function(id){
        		return $http.delete("./rest/dengue-caso/importados/"+ id );
        	},
        	getRelatorioVigilantos: function( dengueFiltro ){
        		
        		var inicio = null;
        		var fim = null;
        		
        		if( dengueFiltro.dtColetaInicio != null ){
        			inicio = dengueFiltro.dtColetaInicio.toLocaleDateString();
        		}
        		if( dengueFiltro.dtColetaFim != null ){
        			fim = dengueFiltro.dtColetaFim.toLocaleDateString()
        		}
        		
        		$window.open("./rest/dengue-caso/vigilantos/relatorio_casos.xls?" +
        				"type="+ dengueFiltro.type +
        				"&idRegional="+ dengueFiltro.idRegional +
        				"&idMunicipio="+ dengueFiltro.idMunicipio +
        				"&paciente="+ dengueFiltro.paciente+
        				"&idResultado="+ dengueFiltro.idResultado +
        				"&dtSintomasInicio="+ dengueFiltro.dtSintomasInicio.toLocaleDateString() +
        				"&dtSintomasFim="+ dengueFiltro.dtSintomasFim.toLocaleDateString() +
        				"&dtColetaInicio="+ inicio +
        				"&dtColetaFim="+ fim + 
        				"&idMunicipioNotificante="+ dengueFiltro.idMunicipioNotificante
        				,"_blank");
        	},
        	getMapaCasos: function( dengueFiltro ){
        		return $http.post("./rest/dengue-mapa/casos-all", dengueFiltro ) 
        	},
        	getDtoMapaById: function( id ){
        		return $http.get("./rest/dengue-mapa/casos/"+ id );
        	},
        	updateGeodata: function(){
        		return $http.get("./rest/dengue-caso/update-geodata");
        	}
        	
        },
        resultado:{
        	getAll: function(){
        		return $http.get("./rest/resultado-dengue");
        	}
        },
// =============================================================================================//
// ARTROPODES - Animais Peçonhentos //
// =============================================================================================//
        artropodes:{
        	coletadores:{
        		getAll: function(){
        			return $http.get("./rest/artropodes/coletador/all");
        		}
        	},
        	coletador: {
        		insert: function( coletador ){
        			return $http.post("./rest/artropodes/coletador", coletador);
            	},
            	update: function( coletador ){
        			return $http.put("./rest/artropodes/coletador", coletador);
            	},
            	excluir: function( id ){
        			return $http.delete("./rest/artropodes/coletador/" + id);
            	}
        	},
        	tipoColeta:{
        		getAll: function(){
        			return $http.get("./rest/artropodes/tipo-coleta");
        		}
        	},
        	ambienteColeta:{
        		getAll: function(){
        			return $http.get("./rest/artropodes/ambiente-coleta");
        		}
        	},
        	tipoArmadilha:{
        		getAll: function(){
        			return $http.get("./rest/artropodes/tipo-armadilha");
        		}
        	},
        	tipoAnimal:{
        		insert: function( tipoAnimal ){
        			return $http.post("./rest/artropodes/tipo-animal", tipoAnimal);
        		},
            	update: function( tipoAnimal ){
        			return $http.put("./rest/artropodes/tipo-animal", tipoAnimal);
            	},
            	excluir: function( id ){
        			return $http.delete("./rest/artropodes/tipo-animal/" + id);
            	}
        	},
        	amostra:{
        		updateLaudoExterno: function( amostra ){
        			return $http.post("./rest/artropodes/amostra/update-laudo-externo", amostra);
        		}
        	},
        	fichaAmostra:{
        		insert: function( artropodes ){
        			return $http.post("./rest/artropodes/ficha-amostra", artropodes );
        		},
        		update: function( artropodes ){
        			return $http.put("./rest/artropodes/ficha-amostra", artropodes );
        		},
        		excluir: function( id ){
        			return $http.delete("./rest/artropodes/ficha-amostra/"+ id );
        		},
        		getById: function( id){
        			return $http.get("./rest/artropodes/ficha-amostra/"+ id);
        		},
        		getFicha: function( id, idUsuario){
        			return $http.get("./rest/artropodes/ficha-amostra/"+ id + "/" + idUsuario);
        		},
        		getAnimaisByFicha: function( idFicha ){
        			return $http.get("./rest/artropodes/ficha-amostra/animais-by-ficha/"+ idFicha );
        		},
        		getEnderecosByLocalidade: function( idLocalidade ){
        			return $http.get("./rest/artropodes/ficha-amostra/endereco/by-localidade/"+ idLocalidade );
        		},
        		getFichasDto: function(filtro){
        			return $http.post("./rest/artropodes/ficha-amostra/fichas-dto", filtro);
        		},
        		geraEtiqueta: function(filtro){
            		
            		$window.open("./rest/artropodes/ficha-amostra/amostra_etiqueta.pdf?" +
            				"type="+ filtro.type +
            				"&id="+ filtro.id +
            				"&idAmostra="+ filtro.idAmostra
            				,"_blank");
            	},
        		atualizaAmostra: function( amostra ){
        			return $http.put("./rest/artropodes/ficha-amostra/atualiza-amostra", amostra );
        		},
        		geraFichaPfd: function( ficha ){
                    $window.open("./rest/artropodes/ficha-amostra/relatorio.pdf"
                    			+"?id="+ ficha.id,
                    			"_blank");
                }
        
        	},
        	cadastroAuxiliarColetador:{
        		getColetadoresDto: function(filtro){
        			return $http.post("./rest/artropodes/cadastro-auxiliar-coletador/coletadores-dto", filtro);
        		}
        	},
        	cadastroAuxiliarEvolucao:{
        		getEvolucoesDto: function(filtro){
        			return $http.post("./rest/artropodes/cadastro-auxiliar-evolucao/evolucoes-dto", filtro);
        		}
        	},
        	cadastroAuxiliarFamilia:{
        		getFamiliasDto: function(filtro){
        			return $http.post("./rest/artropodes/cadastro-auxiliar-familia/familias-dto", filtro);
        		}
        	},
        	cadastroAuxiliarGenero:{
        		getGenerosDto: function(filtro){
        			return $http.post("./rest/artropodes/cadastro-auxiliar-genero/generos-dto", filtro);
        		}
        	},
        	cadastroAuxiliarEspecie:{
        		getEspeciesDto: function(filtro){
        			return $http.post("./rest/artropodes/cadastro-auxiliar-especie/especies-dto", filtro);
        		}
        	},
        	cadastroAuxiliarLogradouro:{
        		getLogradourosDto: function(filtro){
        			return $http.post("./rest/artropodes/cadastro-auxiliar-logradouro/logradouros-dto", filtro);
        		},
        		
        		insert: function( especie ){
        			return $http.post("./rest/artropodes/cadastro-auxiliar-logradouro", especie);
        		},
        		update: function( especie ){
        			return $http.put("./rest/artropodes/cadastro-auxiliar-logradouro", especie);
            	},
            	excluir: function( id ){
        			return $http.delete("./rest/artropodes/cadastro-auxiliar-logradouro/" + id);
            	}
        	},
        	cadastroAuxiliarTipoAnimal:{
        		getTipoAnimaisDto: function(filtro){
        			return $http.post("./rest/artropodes/cadastro-auxiliar-tipoAnimal/tipoAnimais-dto", filtro);
        		},
        		getMensagens: function(){
        			return $http.post("./rest/artropodes/cadastro-auxiliar-tipoAnimal/mensagens");
        		}
        	},
        	animais:{
        		insert: function( animal ){
        			return $http.post("./rest/artropodes/animais", animal);
        		},
        		getTipos: function(){
        			return $http.get("./rest/artropodes/animais/tipos-animais");
        		},
        		getAnimaisByTipo: function( idTipo ){
        			return $http.get("./rest/artropodes/animais/animais-by-tipo/"+ idTipo );
        		}
        	},
        	animalEvolucao:{
        		insert: function( evolucao ){
        			return $http.post("./rest/artropodes/animal-evolucao", evolucao);
        		},
        		getAll: function(){
        			return $http.get("./rest/artropodes/animal-evolucao");
        		},
        		
        		update: function( evolucao ){
        			return $http.put("./rest/artropodes/animal-evolucao", evolucao);
            	},
            	excluir: function( id ){
        			return $http.delete("./rest/artropodes/animal-evolucao/" + id);
            	}
        		
        		
        	},
        	animalFamilia:{
        		insert: function( familia ){
        			return $http.post("./rest/artropodes/animal-familia", familia);
        		},
        		getAll: function(){
        			return $http.get("./rest/artropodes/animal-familia");
        		},
        		
        		update: function( familia ){
        			return $http.put("./rest/artropodes/animal-familia", familia);
            	},
            	excluir: function( id ){
        			return $http.delete("./rest/artropodes/animal-familia/" + id);
            	}
        	},
        	animalGenero:{
        		insert: function( genero ){
        			return $http.post("./rest/artropodes/animal-genero", genero);
        		},
        		getAll: function(){
        			return $http.get("./rest/artropodes/animal-genero");
        		},
        		getGenerosByFamilia: function( idFamilia ){
        			return $http.get("./rest/artropodes/animal-genero/generos-by-familia/"+ idFamilia );
        		},
        		update: function( genero ){
        			return $http.put("./rest/artropodes/animal-genero", genero);
            	},
            	excluir: function( id ){
        			return $http.delete("./rest/artropodes/animal-genero/" + id);
            	}
        	},
        	animalEspecie:{
        		insert: function( especie ){
        			return $http.post("./rest/artropodes/animal-especie", especie);
        		},
        		getAll: function(){
        			return $http.get("./rest/artropodes/animal-especie");
        		},
        		getEspeciesByGenero: function( idGenero ){
        			return $http.get("./rest/artropodes/animal-especie/especies-by-genero/"+ idGenero );
        		},
        		update: function( especie ){
        			return $http.put("./rest/artropodes/animal-especie", especie);
            	},
            	excluir: function( id ){
        			return $http.delete("./rest/artropodes/animal-especie/" + id);
            	}
        	},
        	destinoAmostra:{
        		getAll: function(){
        			return $http.get("./rest/artropodes/destino-amostra");
        		}
        	},
        	condicaoAmostra:{
        		getAll: function(){
        			return $http.get("./rest/artropodes/condicao-amostra");
        		}
        	},
        	laudo:{
        		insert: function( laudo ){
        			return $http.post("./rest/artropodes/laudo", laudo);
        		},
        		update: function( laudo ){
        			return $http.put("./rest/artropodes/laudo", laudo );
        		},
        		excluir: function( id ){
        			return $http.delete("./rest/artropodes/laudo/"+ id );
        		},
        		getById: function( id, idFicha, idUsuario ){
        		// getById: function( id ){
        			return $http.get("./rest/artropodes/laudo/"+ id + "/" + idUsuario+"/"+idFicha);
        		},
        		getByFichaId: function( id ){
        			return $http.get("./rest/artropodes/laudo/ficha/"+ id);
        		},
        		getLaudosDto: function(filtro){
        			return $http.post("./rest/artropodes/laudo/laudos-dto", filtro);
        		},
        		getAnimaisTaxonomiaByLaudo: function( id ){
        			return $http.get("./rest/artropodes/laudo/animais-taxonomia/"+id);
        		},
        		getAnimaisTaxonomiaByAmostra: function( id ){
        			return $http.get("./rest/artropodes/laudo/animais-taxonomia-by-amostra/"+id);
        		},
        		getTaxonomiaByFichaId: function( id ){
        			return $http.get("./rest/artropodes/laudo/taxonomia/"+ id );
        		},        		
        		getRelatorioByGeneroEspecie: function(relatorioLaudoTaxonomia){
        			return $http.put("./rest/artropodes/laudo/relatorio-laudo-taxonomia-dto", relatorioLaudoTaxonomia);
            	},        		
            	gerarRelatorioEspecLaudoTaxonomia: function(relatorioLaudoTaxonomiaDTO){
            		$http.post('./rest/artropodes/laudo/relatorio_especifico_laudo_taxonomia.pdf',{relatorioLaudoTaxonomiaDTO}, {responseType: 'blob'})
            		   .then(function (response) {       
            			   // Solução para formatar data com
							// $.datepicker.formatDate.
            			   var formatedFileName = relatorioLaudoTaxonomiaDTO.nomeRelatorioJasper != null ? 'relatorio_taxonomia_' + relatorioLaudoTaxonomiaDTO.nomeRelatorioJasper + '_'+ $.datepicker.formatDate('dd/mm/yy', new Date()) : 'relatorio_taxonomia_geral_' + $.datepicker.formatDate('dd/mm/yy', new Date());            			   
            			   var a = document.createElement("a");
            			   document.body.appendChild(a);
            			   var file = new Blob([response.data], {type: 'application/pdf'});
                           var fileURL = window.URL.createObjectURL(file);
                           a.href = fileURL;
                           a.download = formatedFileName;
                           a.click();
                           
                           // Modo para abrir o arquivo PDF em outra aba no
							// Chrome
	        			   // var fileURL = URL.createObjectURL(response.data);
	        			   // window.open(fileURL);
            			   
            		});
            	}
        	},        	
        	arquivoAmostra:{
        		getArquivos: function(idAmostra){
        			return $http.get("./rest/file-amostra/arquivos/" + idAmostra);
        		},
        		excluir: function(idArquivo){
        			return $http.delete("./rest/file-amostra/" + idArquivo);
        		}
        	},
        	arquivoLaudo:{
        		getArquivos: function(idAmostra){
        			return $http.get("./rest/file-laudo/arquivos/" + idAmostra);
        		},
        		excluir: function(idArquivo, idAmostra){
        			return $http.delete("./rest/file-laudo/" + idArquivo+"/"+idAmostra);
        		},
        		download: function(idAmostra, nome){
        			return $http.get("./rest/file-laudo/download/" + idAmostra +"/"+nome);
        		}
        	},
           	amostraMapa:{
           		getMapa: function(filtro){
            		return $http.post("./rest/amostra-mapa/amostras-all", filtro);
            	},
            	getRelatorioMapa: function(filtro){
            		
            		$window.open("./rest/amostra-mapa/mapa-relatorio.xls?" +
            				"type="+ filtro.type +
            				"&idMunicipio="+ filtro.idMunicipio +
            				"&idRegional="+ filtro.idRegional +
            				"&idLocalidade="+ filtro.idLocalidade +
            				"&laudo="+ filtro.laudo +
            				"&idTipoAnimal="+ filtro.idTipoAnimal +
            				// "&idAnimal="+ filtro.idAnimal +
            				"&idEspecie="+ filtro.idEspecie +
            				"&idFicha="+ filtro.idFicha +
            				"&idGenero="+ filtro.idGenero +
            				"&idFamilia="+ filtro.idFamilia +
            				"&dtInicio="+ $filter('date')( filtro.dtInicio, 'dd/MM/yyyy') +
            				"&dtFim="+ $filter('date')( filtro.dtFim, 'dd/MM/yyyy') 
            				,"_blank");
            	},
            	
            	getLocalidades: function(municipio){
             		 return $http.post("./rest/artropodes/ficha-amostra/localidades", municipio);
              	}
            	
        	},
        	// Metodo novo adicionado por Julio
        	
        	getLocalidades: function(municipio){
          		 return $http.post("./rest/artropodes/ficha-amostra/localidades", municipio);
           	},
        	
        	getEnderecos: function( localidade ){
       		 return $http.post("./rest/artropodes/ficha-amostra/enderecos", localidade);
        	}, 
        	importanciaMedica :{
        		getAll: function(){
        			return $http.get("./rest/artropodes/importancia-medica/");
        		}
        	},
        	taxonomia :{
        		excluir: function(id){
        			return $http.delete("./rest/artropodes/taxonomia/"+id);
        		}
        	}
        }     
	}
});