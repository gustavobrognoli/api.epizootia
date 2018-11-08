CREATE TABLE `Epizootia`.`mod_epizootia_animal` ( 
	`cd_id` INT NOT NULL AUTO_INCREMENT , 
	`cd_nm_popular` INT NOT NULL , 
	`cd_especie` INT NOT NULL , 
	`cd_situacao` INT NOT NULL , 
	`cd_anormalidade` INT NOT NULL ,	
	`cd_sexo` INT NOT NULL , 
	`cd_idade` INT NOT NULL , 
	`fg_apreensao` boolean NOT NULL , 
	`fg_vida_livre` boolean NOT NULL ,
	`fg_cativeiro` boolean NOT NULL ,    
	`cd_tempo_obito` INT NOT NULL ,	
	`cd_visceras` INT NOT NULL , 
	`cd_classificacao_fa` INT NOT NULL , 
        
	PRIMARY KEY (`cd_id`)
) ENGINE = InnoDB;