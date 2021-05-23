ALTER TABLE `academiadb`.`student`
DROP COLUMN `active_enrollment`;

ALTER TABLE `academiadb`.`enrollment`
ADD COLUMN `enrollment_locked` BIT(1) NOT NULL DEFAULT b'0' AFTER `student_id`,
ADD COLUMN `start_locked` DATETIME NULL DEFAULT NULL AFTER `enrollment_locked`,
ADD COLUMN `end_locked` DATETIME NULL DEFAULT NULL AFTER `start_locked`,
ADD COLUMN `days_locked` INT NULL DEFAULT NULL AFTER `end_locked`,
ADD COLUMN `start_return` DATETIME NULL DEFAULT NULL AFTER `days_locked`,
ADD COLUMN `end_return` DATETIME NULL DEFAULT NULL AFTER `start_return`,
ADD COLUMN `description` VARCHAR(100) NULL DEFAULT NULL AFTER `end_return`;
