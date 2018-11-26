INSERT INTO epizootia.mod_epizootia_morador (nm_morador, nu_telefone) 
values ("Ana", "48 9 9988-8888"),("Maria", "48 9 9977-7777"),("Dete", "48 9 9966-6666");

INSERT INTO epizootia.mod_epizootia_nm_popular (ds_nome) 
values ("Bugio"),("Macaco-prego"),("Sagui/Mico");

INSERT INTO epizootia.mod_epizootia_especie (ds_especie) 
values ("Alouatta sp"),("Sapajus sp"), ("Callithrix sp");

INSERT INTO epizootia.mod_epizootia_situacao (ds_situacao) 
values ("Vivo (em bom estado de saúde)"),("Morto"),("Vestígios"),("Doente");

INSERT INTO epizootia.mod_epizootia_idade (ds_idade) 
values ("Adulto"),("Filhote"),("Jovem"),("Não Identificado");

INSERT INTO epizootia.mod_epizootia_sexo (ds_sexo) 
values ("Macho"),("Fêmea"),("Não Identificado");

INSERT INTO epizootia.mod_epizootia_tempo_obito (ds_tempo_obito) 
values ("Até 2 horas"),("Até 8 horas"),("Até 24 horas"),("Superior a  24 horas"),("Não avaliado");

INSERT INTO epizootia.mod_epizootia_unidade_conservacao (ds_nome) 
values ("Zoologico"),("Parque"),("Praça");

INSERT INTO epizootia.mod_epizootia_mtd_captura (ds_mtd_captura) 
values ("Captura de Solo"),("Captura de Copa");

INSERT INTO epizootia.mod_epizootia_sit_fundiaria (ds_nome) 
values ("Não identificada"),("Assentamento"),("Comunidade Rural"),("Propriedade particular"),("Terra do Governo"),("Terra Indigêna"),("Terra Quilombola"),("Unidade de Conservação"),("Outra");

INSERT INTO epizootia.mod_epizootia_corpos_agua (ds_nome)
values ("Nenhum"),("Açude"),("Área alagada/Brejo/Banhado"),("Corixo/Igarapé/Riacho"),("Estuário"),("Lagoa"),("Lago"),("Laguna"),("Mangue"),("Mar"),("Represa"),("Rio"),("Outro");

INSERT INTO epizootia.mod_epizootia_classificacao_fa (ds_classificacao_fa) 
values ("Em análise"),("Ignorado"),("Descartado"),("Confirmado");

INSERT INTO epizootia.mod_epizootia_anormalidade (cd_sintoma)
values ("Nenhum"),("Baba"),("Bicheira"),("Caroço/Tumor"),("Cegueira"),("Diarreia"),("Fratura"),("Queimadura"),("Sangramento"),("Secreção"),("Outros");

INSERT INTO epizootia.mod_epizootia_visceras (fg_coleta, ds_viscera)
values (1, "Fígado"),(1, "Rim"),(1, "Cérebro"),(1, "Baço"),(1, "Pulmão"),(1, "Coração"),(1, "Sangue"),(1, "Soro");

INSERT INTO epizootia.mod_epizootia_genero (ds_genero)
values ("Haemagogus"),("Sabethes"),("Aedes Aegypti"),("Aedes Albopictus"),("Anopheles");

INSERT INTO epizootia.mod_epizootia_apreensao (ds_apreensao)
values ("Sim"),("Não"),("Não sabe informar");

INSERT INTO epizootia.mod_epizootia_vida_livre (ds_vida_livre)
values ("Sim"),("Não"),("Não sabe informar");

INSERT INTO epizootia.mod_epizootia_cativeiro (ds_cativeiro)
values ("Sim"),("Não"),("Não sabe informar");

INSERT INTO epizootia.mod_epizootia_caracteristica (nm_caracteristica)
values ("Natural (Silvestre)"), ("Próximo a Domicilio"),("Rural"),("Urbano");

INSERT INTO epizootia.mod_epizootia_impactos_observados (nm_impacto)
values ("Assentamento "),("Alteração de rios/lagos/lagoas"),("Avanço Agropecuário"), 
("Desastres naturais"),("Exploração de Petróleo e Gás"), ("Queimadas/Incêndios"),
("Grandes Obras"), ("Impactos acidentais"),("Pressão imobiliária"), 
("Desmatamento"),("Turismo ecológico"), ("Urbanização recente");

INSERT INTO epizootia.mod_epizootia_equipamento (ds_equipamento)
values ("Puçá"),("Castro"),("Shanonn"), ("Barraca"),("Armadilha CDC");

-- INSERT INTO epizootia.mod_epizootia_animal (ds_outras_informacoes, cd_apreensao, cd_vida_livre, cd_cativeiro, cd_especie, cd_idade, cd_nm_popular, cd_sexo, cd_situacao, cd_tempo_obito, cd_viscera, cd_ficha) 
-- values ("Animal 1","1","1","1","1","1","1","1","1","1","1","1");

-- INSERT INTO epizootia.mod_epizootia_animal (ds_outras_informacoes, cd_apreensao, cd_vida_livre, cd_cativeiro, cd_especie, cd_idade, cd_nm_popular, cd_sexo, cd_situacao, cd_tempo_obito, cd_viscera, cd_ficha) 
-- values ("Animal 2","1","1","1","1","1","1","1","1","1","1","1");

-- INSERT INTO epizootia.mod_epizootia_animal (ds_outras_informacoes, cd_apreensao, cd_vida_livre, cd_cativeiro, cd_especie, cd_idade, cd_nm_popular, cd_sexo, cd_situacao, cd_tempo_obito, cd_viscera, cd_ficha) 
-- values ("Animal 3","2","2","2","2","2","2","2","2","2","2","1");

INSERT INTO epizootia.mod_epizootia_animal (ds_outras_informacoes, cd_apreensao, cd_vida_livre, cd_cativeiro, cd_especie, cd_idade, cd_nm_popular, cd_sexo, cd_situacao, cd_tempo_obito, cd_ficha) 
values ("Animal 1","1","1","1","1","1","1","1","1","1","1");

INSERT INTO epizootia.mod_epizootia_animal (ds_outras_informacoes, cd_apreensao, cd_vida_livre, cd_cativeiro, cd_especie, cd_idade, cd_nm_popular, cd_sexo, cd_situacao, cd_tempo_obito, cd_ficha) 
values ("Animal 2","1","1","1","1","1","1","1","1","1","1");

INSERT INTO epizootia.mod_epizootia_animal (ds_outras_informacoes, cd_apreensao, cd_vida_livre, cd_cativeiro, cd_especie, cd_idade, cd_nm_popular, cd_sexo, cd_situacao, cd_tempo_obito, cd_ficha) 
values ("Animal 3","2","2","2","2","2","2","2","2","2","2");

INSERT INTO epizootia.mod_epizootia_isolamento_viral (ds_resultado, cd_genero)
values ("1","1");

INSERT INTO epizootia.mod_epizootia_localidade (nm_bairro, cd_caracteristica, nu_cep, cd_corpos_agua, ds_descricao, cd_impacto, nu_longitude, nu_latitude, nm_logradouro, cd_morador, ds_ponto_referencia, cd_situacao_fundiaria) 
values ("Barreiros","1","1","1","1","1",-27.592392,-48.581441,"1","1","1","1");

INSERT INTO epizootia.mod_epizootia_localidade (nm_bairro, cd_caracteristica, nu_cep, cd_corpos_agua, ds_descricao, cd_impacto, nu_longitude, nu_latitude, nm_logradouro, cd_morador, ds_ponto_referencia, cd_situacao_fundiaria) 
values ("Floresta","1","1","1","1","1",-27.586005,-48.586491,"1","1","1","1");

INSERT INTO epizootia.mod_epizootia_localidade (nm_bairro, cd_caracteristica, nu_cep, cd_corpos_agua, ds_descricao, cd_impacto, nu_longitude, nu_latitude, nm_logradouro, cd_morador, ds_ponto_referencia, cd_situacao_fundiaria) 
values ("Floresta","1","1","1","1","1",-27.595385, -48.577163,"1","1","1","1");

INSERT INTO epizootia.mod_epizootia_localidade (nm_bairro, cd_caracteristica, nu_cep, cd_corpos_agua, ds_descricao, cd_impacto, nu_longitude, nu_latitude, nm_logradouro, cd_morador, ds_ponto_referencia, cd_situacao_fundiaria) 
values ("Floresta","1","1","1","1","1",-27.596333, -48.578111,"1","1","1","1");

INSERT INTO epizootia.mod_epizootia_ficha (dt_data_ocorrencia, cd_localidade, ds_municipio, id_classificacaofa, cd_registro_entomologico)
values ("2018-01-01 23:59:59.997","1","São José","1","1");

INSERT INTO epizootia.mod_epizootia_ficha (dt_data_ocorrencia, cd_localidade, ds_municipio, id_classificacaofa, cd_registro_entomologico)
values ("2018-01-01 23:59:59.997","2","São José","2","1");

INSERT INTO epizootia.mod_epizootia_ficha (dt_data_ocorrencia, cd_localidade, ds_municipio, id_classificacaofa, cd_registro_entomologico)
values ("2018-01-01 23:59:59.997","3","São José","3","1");

INSERT INTO epizootia.mod_epizootia_ficha (dt_data_ocorrencia, cd_localidade, ds_municipio, id_classificacaofa, cd_registro_entomologico)
values ("2018-01-01 23:59:59.997","4","São José","4","1");

INSERT INTO epizootia.mod_epizootia_registro_entomologico (dt_data_registro, dt_data_ultimo_registro, nu_cobertura_vacinal, nu_doses_aplicadas_300m, cd_equipamento, nu_focos_aedes_300m, nu_imoveis_visitados_300m, cd_isolamento_viral, cd_metodo_captura)
values ("2018-11-03 23:59:59.997","2018-10-03 23:59:59.997","1","1", "1", "1", "1", "1", "1");

INSERT INTO epizootia.mod_epizootia_registro_entomologico (dt_data_registro, dt_data_ultimo_registro, nu_cobertura_vacinal, nu_doses_aplicadas_300m, cd_equipamento, nu_focos_aedes_300m, nu_imoveis_visitados_300m, cd_isolamento_viral, cd_metodo_captura)
values ("2018-11-03 23:59:59.997","2018-10-03 23:59:59.997","1","1", "1", "1", "1", "1", "1");

INSERT INTO epizootia.mod_epizootia_registro_entomologico (dt_data_registro, dt_data_ultimo_registro, nu_cobertura_vacinal, nu_doses_aplicadas_300m, cd_equipamento, nu_focos_aedes_300m, nu_imoveis_visitados_300m, cd_isolamento_viral, cd_metodo_captura)
values ("2018-11-03 23:59:59.997","2018-10-03 23:59:59.997","1","1", "1", "1", "1", "1", "1");