ALTER TABLE `academiadb`.`user`
ADD COLUMN `admin` BIT(1) NULL DEFAULT b'0' AFTER `company_id`;
