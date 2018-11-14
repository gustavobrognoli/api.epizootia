CREATE TABLE `Epizootia`.`mod_epizootia_tempo_obito` ( 
	`cd_id` INT NOT NULL AUTO_INCREMENT , 
	`ds_tempo_obito` VARCHAR(255) NOT NULL , 
	PRIMARY KEY (`cd_id`)
) ENGINE = InnoDB;

INSERT INTO epizootia.mod_epizootia_tempo_obito (ds_tempo_obito) 
values ("Até 2 horas"),("Até 8 horas"),("Até 24 horas"),("Superior a  24 horas"),("Não avaliado");