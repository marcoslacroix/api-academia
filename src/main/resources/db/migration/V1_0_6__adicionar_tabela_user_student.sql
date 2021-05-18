CREATE TABLE IF NOT EXISTS `academiadb`.`user_student` (
  `user_id` INT NOT NULL,
  `student_id` INT NOT NULL,
  INDEX `fk_user_student_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_user_student_student1_idx` (`student_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_student_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `academiadb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_student_student1`
    FOREIGN KEY (`student_id`)
    REFERENCES `academiadb`.`student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;