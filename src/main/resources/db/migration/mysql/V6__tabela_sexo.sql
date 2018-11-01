CREATE TABLE `Epizootia`.`mod_epizootia_sexo` ( 
	`cd_id` INT NOT NULL AUTO_INCREMENT , 
	`ds_sexo` VARCHAR(255) NOT NULL , 
	PRIMARY KEY (`cd_id`)
) ENGINE = InnoDB;

INSERT INTO epizootia.mod_epizootia_sexo (ds_sexo) 
values ("Não Identificado"),("Macho"),("Fêmea");