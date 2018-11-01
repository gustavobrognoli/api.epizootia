CREATE TABLE `Epizootia`.`mod_epizootia_idade` ( 
	`cd_id` INT NOT NULL AUTO_INCREMENT , 
	`ds_idade` VARCHAR(255) NOT NULL , 
	PRIMARY KEY (`cd_id`)
) ENGINE = InnoDB;

INSERT INTO epizootia.mod_epizootia_idade (ds_idade) 
values ("Adulto"),("Filhote"),("Jovem"),("Não Identificado");