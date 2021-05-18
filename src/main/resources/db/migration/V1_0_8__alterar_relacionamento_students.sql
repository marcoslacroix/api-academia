ALTER TABLE `academiadb`.`student`
ADD COLUMN `company_id` INT NOT NULL AFTER `active_enrollment`,
ADD INDEX `index_company_student` (`company_id` ASC) INVISIBLE;
;
ALTER TABLE `academiadb`.`student`
ADD CONSTRAINT `fk_company_student`
  FOREIGN KEY (`company_id`)
  REFERENCES `academiadb`.`company` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
