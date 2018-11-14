CREATE TABLE `Epizootia`.`mod_epizootia_equipamento` ( 
	`cd_id` INT NOT NULL AUTO_INCREMENT , 
	`fg_equipamento_puca` boolean, 
    `fg_equipamento_castro` boolean,
    `fg_equipamento_shanoon` boolean,
    `fg_equipamento_cdc` boolean,
        
	PRIMARY KEY (`cd_id`)
) ENGINE = InnoDB;
