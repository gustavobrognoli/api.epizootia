CREATE TABLE `Epizootia`.`mod_epizootia_registro_entomologico` ( 
	`cd_id` INT NOT NULL AUTO_INCREMENT , 
	`dt_data_registro` DATETIME,
	`dt_data_ultimo_registro` DATETIME,
    `cd_metodoCaptura` INT NOT NULL,
	`cd_equipamento` INT NOT NULL,
	`cd_isolamento_viral` INT NOT NULL,
	`cd_recomendacao_vacinal` INT NOT NULL,
	`nu_cobertura_vacinal` VARCHAR(255) NOT NULL,
	`nu_imoveis_visitados_300m` VARCHAR(255) NOT NULL,
	`nu_doses_aplicadas_300m` VARCHAR(255) NOT NULL,
	`nu_focos_aedes_300m` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`cd_id`)
) ENGINE = InnoDB;
