CREATE TABLE `Epizootia`.`mod_epizootia_recomendacao_vacinal` ( 
	`cd_id` INT NOT NULL AUTO_INCREMENT , 
	`ds_recomendacao_vacinal` VARCHAR(255) NOT NULL , 
	PRIMARY KEY (`cd_id`)
) ENGINE = InnoDB;

INSERT INTO epizootia.mod_epizootia_recomendacao_vacinal (ds_recomendacao_vacinal) 
values ("Área sem Recomendação de Vacina (ASRV)"),("Área com Recomendação de Vacina (ACRV)");