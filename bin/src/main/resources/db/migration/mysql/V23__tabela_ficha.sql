CREATE TABLE `Epizootia`.`mod_epizootia_ficha` ( 
	`cd_id` INT NOT NULL AUTO_INCREMENT , 
	`dt_data_ocorrencia` DATETIME,
	`cd_id_animal` INT NOT NULL,
    `nu_quantidade` INT NOT NULL,
	`cd_id_localidade` INT NOT NULL,
	`cd_municipio` INT NOT NULL,
	PRIMARY KEY (`cd_id`)
) ENGINE = InnoDB;


ALTER TABLE `epizootia`.`mod_epizootia_ficha` 
ADD INDEX `cd_id_animal_idx` (`cd_id_animal` ASC),
ADD INDEX `cd_id_localidade_idx` (`cd_id_localidade` ASC);
ALTER TABLE `epizootia`.`mod_epizootia_ficha` 
ADD CONSTRAINT `cd_id_animal`
  FOREIGN KEY (`cd_id_animal`)
  REFERENCES `epizootia`.`mod_epizootia_animal` (`cd_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `cd_id_localidade`
  FOREIGN KEY (`cd_id_localidade`)
  REFERENCES `epizootia`.`mod_epizootia_localidade` (`cd_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
