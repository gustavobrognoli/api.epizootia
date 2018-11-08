CREATE TABLE `Epizootia`.`mod_epizootia_ficha` ( 
	`cd_id` INT NOT NULL AUTO_INCREMENT , 
	`dt_data_ocorrencia` DATETIME,
	`cd_id_animal` INT NOT NULL,
    `nu_quantidade` INT NOT NULL,
	`cd_id_localidade` INT NOT NULL,
	`cd_municipio` INT NOT NULL,
	PRIMARY KEY (`cd_id`)
) ENGINE = InnoDB;
