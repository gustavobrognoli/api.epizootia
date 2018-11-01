CREATE TABLE `Epizootia`.`mod_epizootia_nm_popular` ( 
	`cd_id` INT NOT NULL AUTO_INCREMENT , 
	`ds_nome` VARCHAR(255) NOT NULL , 
	PRIMARY KEY (`cd_id`)
) ENGINE = InnoDB;

INSERT INTO epizootia.mod_epizootia_nm_popular (ds_nome) 
values ("Mico"),("Bugio"),("Macaco-aranha"),("Macaco-prego"),
("Mono-carvoeiro"),("Macaco-barrigudo"),("Macaco-esquilo"),("Macaco-da-noite"),("Sagui");