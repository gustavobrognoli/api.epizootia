CREATE TABLE `Epizootia`.`mod_epizootia_situacao` ( 
	`cd_id` INT NOT NULL AUTO_INCREMENT , 
	`ds_situacao` VARCHAR(255) NOT NULL , 
	PRIMARY KEY (`cd_id`)
) ENGINE = InnoDB;

INSERT INTO epizootia.mod_epizootia_situacao (ds_situacao) 
values ("Vivo (em bom estado de saúde"),("Morto"),("Vestígios"),("Doente");