CREATE TABLE IF NOT EXISTS `academiadb`.`enrollment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `start` DATETIME NOT NULL,
  `end` DATETIME NOT NULL,
  `price` DECIMAL NOT NULL,
  `student_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_monthly_payment_student1_idx` (`student_id` ASC) VISIBLE,
  CONSTRAINT `fk_monthly_payment_student1`
    FOREIGN KEY (`student_id`)
    REFERENCES `academiadb`.`student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `academiadb`.`frequency` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `start` DATETIME NULL,
  `end` DATETIME NULL,
  `student_id` INT NOT NULL,
  `note` MEDIUMTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_training_student1_idx` (`student_id` ASC) VISIBLE,
  CONSTRAINT `fk_training_student1`
    FOREIGN KEY (`student_id`)
    REFERENCES `academiadb`.`student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `academiadb`.`phone` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `phone_number` VARCHAR(45) NULL,
  `ddd` VARCHAR(45) NULL DEFAULT NULL,
  `student_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_phone_student1_idx` (`student_id` ASC) VISIBLE,
  CONSTRAINT `fk_phone_student1`
    FOREIGN KEY (`student_id`)
    REFERENCES `academiadb`.`student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `academiadb`.`address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `bairro` VARCHAR(45) NULL DEFAULT NULL,
  `cidade` VARCHAR(45) NULL DEFAULT NULL,
  `student_id` INT NOT NULL,
  `logradouro` VARCHAR(100) NULL DEFAULT NULL,
  `cep` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_address_student1_idx` (`student_id` ASC) VISIBLE,
  CONSTRAINT `fk_address_student1`
    FOREIGN KEY (`student_id`)
    REFERENCES `academiadb`.`student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;