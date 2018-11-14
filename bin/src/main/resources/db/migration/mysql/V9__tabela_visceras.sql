CREATE TABLE `Epizootia`.`mod_epizootia_visceras` ( 
	`cd_id` INT NOT NULL AUTO_INCREMENT , 
	`fg_visceras_figado` boolean, 
    `fg_visceras_rim` boolean,
    `fg_visceras_cerebro` boolean,
    `fg_visceras_baco` boolean,
    `fg_visceras_pulmao` boolean,
    `fg_visceras_coracao` boolean,
    `fg_visceras_sangue` boolean,
    `fg_visceras_soro` boolean,
    `ds_visceras_motivo` VARCHAR(255),
        
	PRIMARY KEY (`cd_id`)
) ENGINE = InnoDB;
