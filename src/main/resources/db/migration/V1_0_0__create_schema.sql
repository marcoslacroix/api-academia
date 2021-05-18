CREATE TABLE IF NOT EXISTS `academiadb`.`student` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NOT NULL,
  `cpf` VARCHAR(45) NULL,
  `email` VARCHAR(50) NULL DEFAULT NULL,
  `birth` DATE NULL DEFAULT NULL,
  `gender` VARCHAR(45) NULL,
  `occupation` VARCHAR(45) NULL,
  `objective` VARCHAR(45) NULL,
  `created_on` DATETIME NULL DEFAULT NULL,
  `updated_on` DATETIME NULL,
  `deleted` BIT(1) NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC) VISIBLE)
ENGINE = InnoDB;