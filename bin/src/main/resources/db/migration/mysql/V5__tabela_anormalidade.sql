CREATE TABLE `Epizootia`.`mod_epizootia_anormalidade` ( 
	`cd_id` INT NOT NULL AUTO_INCREMENT , 
	`fg_anormalidade_baba` boolean NOT NULL , 
    `fg_anormalidade_bicheira` boolean NOT NULL ,
    `fg_anormalidade_caroco` boolean NOT NULL ,
    `fg_anormalidade_cegueira` boolean NOT NULL ,
    `fg_anormalidade_diarreia` boolean NOT NULL ,
    `fg_anormalidade_fratura` boolean NOT NULL ,
    `fg_anormalidade_queimadura` boolean NOT NULL ,
    `fg_anormalidade_sangramento` boolean NOT NULL ,
    `fg_anormalidade_secrecao` boolean NOT NULL ,
    `ds_anormalidade` VARCHAR(255) NOT NULL ,
        
	PRIMARY KEY (`cd_id`)
) ENGINE = InnoDB;
