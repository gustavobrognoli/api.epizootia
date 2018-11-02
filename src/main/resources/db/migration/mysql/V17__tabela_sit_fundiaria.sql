CREATE TABLE `Epizootia`.`mod_epizootia_sit_fundiaria` ( 
	`cd_id` INT NOT NULL AUTO_INCREMENT , 
	`fg_st_fundiaria_assentamento` boolean, 
    `fg_st_fundiaria_rural` boolean,
    `fg_st_fundiaria_particular` boolean,
    `fg_st_fundiaria_governo` boolean,
    `fg_st_fundiaria_indigina` boolean,
    `fg_st_fundiaria_quilombola` boolean, 
    `fg_st_fundiaria_unidade_conservacao"` boolean,
    `cd_st_fundiaria_unidade_conservacao` INT,
    `fg_st_fundiaria_unidade_outros` boolean,
	`ds_st_fundiaria_outros` VARCHAR(255),
	PRIMARY KEY (`cd_id`)
) ENGINE = InnoDB;