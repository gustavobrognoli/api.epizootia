CREATE TABLE `Epizootia`.`mod_epizootia_caracteristicas` ( 
	`cd_id` INT NOT NULL AUTO_INCREMENT , 
	`fg_caracteristicas_natural` boolean, 
    `fg_caracteristicas_domicilio` boolean,
    `fg_caracteristicas_rural` boolean,
    `fg_caracteristicas_urbano` boolean,
	PRIMARY KEY (`cd_id`)
) ENGINE = InnoDB;