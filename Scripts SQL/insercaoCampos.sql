INSERT INTO epizootia.mod_epizootia_morador (nm_morador, nu_telefone) 
values ("Ana", "48 9 9988-8888"),("Maria", "48 9 9977-7777"),("Dete", "48 9 9966-6666");

INSERT INTO epizootia.mod_epizootia_nm_popular (ds_nome) 
values ("Mico"),("Bugio"),("Macaco-aranha"),("Macaco-prego"),
("Mono-carvoeiro"),("Macaco-barrigudo"),("Macaco-esquilo"),("Macaco-da-noite"),("Sagui");

INSERT INTO epizootia.mod_epizootia_especie (ds_especie) 
values ("Mico chrysoleucus"),("Alouatta caraya"),("Ateles belzebuth"),("Sapajus apella"),
("Brachyteles arachnoides"),("Lagothrix cana"),("Ateles chamek"),("Aotus azarae"),("Leontopithecus rosalia");

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
values ("Solo"),("Copa");

INSERT INTO epizootia.mod_epizootia_sit_fundiaria (ds_nome) 
values ("Não identificada"),("Assentamento"),("Comunidade Rural"),("Propriedade particular"),("Terra do Governo"),("Terra Indigêna"),("Terra Quilombola"),("Unidade de Conservação"),("Outra");

INSERT INTO epizootia.mod_epizootia_corpos_agua (ds_nome)
values ("Nenhum"),("Açude"),("Área alagada/Brejo/Banhado"),("Corixo/Igarapé/Riacho"),("Estuário"),("Lagoa"),("Lago"),("Laguna"),("Mangue"),("Mar"),("Represa"),("Rio"),("Outro");

INSERT INTO epizootia.mod_epizootia_animal (cd_anormalidade, cd_especie, cd_idade, cd_nm_popular, cd_sexo, cd_situacao, cd_tempo_obito, cd_viscera, cd_id_ficha) 
values ("1","1","1","1","1","1","1","1","1");

INSERT INTO epizootia.mod_epizootia_localidade (nm_bairro, cd_caracteristicas, nu_cep, cd_corpos_agua, ds_descricao, cd_impacto, nu_latitude, nm_logradouro, cd_morador, ds_ponto_referencia, cd_registro_entomologico, cd_situacao_fundiaria) 
values ("1","1","1","1","1","1","1","1","1","1","1","1");

INSERT INTO epizootia.mod_epizootia_ficha (ds_classificacaofa, dt_data_ocorrencia, cd_id_localidade, cd_municipio, nu_quantidade)
values ("1","1998-01-01 23:59:59.997","1","1","1");