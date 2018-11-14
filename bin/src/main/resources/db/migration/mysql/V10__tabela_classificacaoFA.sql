CREATE TABLE `Epizootia`.`mod_epizootia_classificacao_fa` ( 
	`cd_id` INT NOT NULL AUTO_INCREMENT , 
	`fg_classificacao_fa_confirmado` boolean, 
    `fg_classificacao_fa_descartado` boolean,
    `fg_classificacao_fa_ignorado` boolean,
        
	PRIMARY KEY (`cd_id`)
) ENGINE = InnoDB;