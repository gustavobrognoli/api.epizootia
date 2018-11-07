CREATE TABLE `Epizootia`.`mod_epizootia_isolamento_viral` ( 
	`cd_id` INT NOT NULL AUTO_INCREMENT , 
	`ds_isolamento_viral` VARCHAR(255),
	`ds_resultado` VARCHAR(255),
	`fg_especies_encontradas_haemagogus` boolean, 
    `fg_especies_encontradas_sabethes"` boolean,
    `fg_especies_encontradas_aegypti` boolean,
    `fg_especie_encontrada_anopheles` boolean,
    `fg_especie_encontrada_albopictus` boolean,
	PRIMARY KEY (`cd_id`)
) ENGINE = InnoDB;