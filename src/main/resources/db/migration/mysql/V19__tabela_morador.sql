CREATE TABLE `Epizootia`.`mod_epizootia_sexo` ( 
	`cd_id` INT NOT NULL AUTO_INCREMENT , 
	`nm_morador` VARCHAR(255) NOT NULL ,
	`nu_telefone` VARCHAR(255) NOT NULL , 
	PRIMARY KEY (`cd_id`)
) ENGINE = InnoDB;

INSERT INTO epizootia.mod_epizootia_sexo (nm_morador, nu_telefone) 
values ("Ana", "48 9 9988-8888"),("Maria", "48 9 9977-7777"),("Dete", "48 9 9966-6666");