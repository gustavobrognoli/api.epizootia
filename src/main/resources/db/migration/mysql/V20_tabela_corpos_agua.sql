CREATE TABLE `Epizootia`.`mod_epizootia_corpos_agua` ( 
	`cd_id` INT NOT NULL AUTO_INCREMENT , 
	`fg_corpos_agua_acude` boolean, 
    `fg_corpos_agua_banhado` boolean,
    `fg_corpos_agua_riacho` boolean,
    `fg_corpos_agua_estuario` boolean,
    `fg_corpos_agua_lagoa` boolean,
    `fg_corpos_agua_lago` boolean, 
    `fg_corpos_agua_laguna"` boolean,
    `fg_corpos_agua_mangue` boolean,
    `fg_corpos_agua_mar` boolean,
    `fg_corpos_agua_represa` boolean,
    `fg_corpos_agua_rio` boolean,
    `fg_corpos_agua_outro` boolean,
    `ds_corpos_agua_outro` boolean,
	`ds_st_fundiaria_outros` VARCHAR(255),
	PRIMARY KEY (`cd_id`)
) ENGINE = InnoDB;