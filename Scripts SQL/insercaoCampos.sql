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

INSERT INTO epizootia.mod_epizootia_unidade_conservacao (ds_nome_unidade_conservacao) 
values ("Zoologico"),("Parque"),("Praça");

INSERT INTO epizootia.mod_epizootia_mtd_captura (ds_mtd_captura) 
values ("Solo"),("Copa");