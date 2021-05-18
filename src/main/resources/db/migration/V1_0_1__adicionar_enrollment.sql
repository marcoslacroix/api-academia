ALTER TABLE `academiadb`.`student`
ADD COLUMN `active_enrollment` BIT(1) NULL DEFAULT b'0' AFTER `deleted`;
