CREATE TABLE IF NOT EXISTS `academiadb`.`company` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NULL,
  `deleted` BIT(1) NULL DEFAULT b'0',
  `created_on` DATETIME NULL DEFAULT NULL,
  `updated_on` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


ALTER TABLE `academiadb`.`user`
ADD COLUMN `company_id` INT NOT NULL AFTER `deleted`,
ADD INDEX `fk_user_company_idx` (`company_id` ASC) VISIBLE;
;
ALTER TABLE `academiadb`.`user`
ADD CONSTRAINT `fk_user_company`
  FOREIGN KEY (`company_id`)
  REFERENCES `academiadb`.`company` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

