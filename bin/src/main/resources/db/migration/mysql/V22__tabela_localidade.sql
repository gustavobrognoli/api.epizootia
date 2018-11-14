CREATE TABLE `Epizootia`.`mod_epizootia_localidade` ( 
	`cd_id` INT NOT NULL AUTO_INCREMENT , 
    `cd_morador` INT NOT NULL,
	`nu_cep` DOUBLE NOT NULL,
	`nm_bairro` VARCHAR(255) NOT NULL,
	`nm_logradouro` VARCHAR(255) NOT NULL,
	`ds_ponto_referencia` VARCHAR(255) NOT NULL,
	`nu_latitude` DOUBLE NOT NULL,
	`nu_longitude` DOUBLE NOT NULL,
	`cd_impactos` INT NOT NULL,
	`cd_caracteristicas` INT NOT NULL,
	`cd_corpos_agua` INT NOT NULL,
	`cd_situacao_fundiaria` INT NOT NULL,
	`cd_registro_entomologico` INT NOT NULL,
	`ds_descricao` VARCHAR(255) NOT NULL,

	PRIMARY KEY (`cd_id`)
) ENGINE = InnoDB;
