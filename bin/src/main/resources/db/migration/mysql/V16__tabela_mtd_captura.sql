CREATE TABLE `Epizootia`.`mod_epizootia_mtd_captura` ( 
	`cd_id` INT NOT NULL AUTO_INCREMENT , 
	`ds_mtd_captura` VARCHAR(255) NOT NULL , 
	PRIMARY KEY (`cd_id`)
) ENGINE = InnoDB;

INSERT INTO epizootia.mod_epizootia_mtd_captura (ds_mtd_captura) 
values ("Captura de Solo"),("Captura de Copa");