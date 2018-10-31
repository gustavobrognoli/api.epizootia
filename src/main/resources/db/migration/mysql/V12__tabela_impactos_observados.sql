CREATE TABLE `Epizootia`.`mod_epizootia_impactos_observados` ( 
		`cd_id` INT NOT NULL AUTO_INCREMENT , 
	`fg_anormalidade_baba` boolean, 
    `fg_anormalidade_bicheira` boolean,
    `fg_anormalidade_caroco` boolean,
    `fg_anormalidade_cegueira` boolean,
    `fg_anormalidade_diarreia` boolean,
    `fg_anormalidade_fratura` boolean,
    `fg_anormalidade_queimadura` boolean,
    `fg_anormalidade_sangramento` boolean,
    `fg_anormalidade_secrecao` boolean,
    `ds_anormalidade` VARCHAR(255),
	PRIMARY KEY (`cd_id`)
) ENGINE = InnoDB;