CREATE TABLE `Epizootia`.`mod_epizootia_especie` ( 
	`cd_id` INT NOT NULL AUTO_INCREMENT , 
	`ds_especie` VARCHAR(255) NOT NULL , 
	PRIMARY KEY (`cd_id`)
) ENGINE = InnoDB;

INSERT INTO epizootia.mod_epizootia_especie (ds_especie) 
values ("Mico chrysoleucus"),("Alouatta caraya"),("Ateles belzebuth"),("Sapajus apella"),
("Brachyteles arachnoides"),("Lagothrix cana"),("Ateles chamek"),("Aotus azarae"),("Leontopithecus rosalia");