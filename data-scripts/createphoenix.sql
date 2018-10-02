create database phoenix;

use phoenix;

-- -----------------------------------------------------
-- Table `phoenix`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phoenix`.`role` ;

CREATE  TABLE IF NOT EXISTS `phoenix`.`role` (
  `role` VARCHAR(15) NOT NULL ,
  `accessPrivilege` VARCHAR(45) NULL ,
  PRIMARY KEY (`role`) )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `role_UNIQUE` ON `phoenix`.`role` (`role` ASC) ;


-- -----------------------------------------------------
-- Table `phoenix`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phoenix`.`user` ;

CREATE  TABLE IF NOT EXISTS `phoenix`.`user` (
  `id` VARCHAR(40) NOT NULL ,
  `password` VARCHAR(45) NULL ,
  `name` VARCHAR(45) NULL ,
  `role` VARCHAR(255) NULL ,
  PRIMARY KEY (`id`))
--  CONSTRAINT `role`
--    FOREIGN KEY (`role` )
--    REFERENCES `phoenix`.`role` (`role` )
--    ON DELETE NO ACTION
--    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `phoenix`.`user` (`id` ASC) ;

CREATE INDEX `role_index` ON `phoenix`.`user` (`role` ASC) ;

-- -----------------------------------------------------
-- Insert Data For Table `phoenix`.`role`
-- -----------------------------------------------------

-- role, access privilege
insert into `phoenix`.`role` values("presenter","radio program presenter");
insert into `phoenix`.`role` values("manager", "station manager");
insert into `phoenix`.`role` values("admin", "system administrator");
insert into `phoenix`.`role` values("producer", "program producer");


-- -----------------------------------------------------
-- Insert Data For Table `phoenix`.`user`
-- -----------------------------------------------------

-- id, password, name, role
insert into `phoenix`.`user` values("dilbert", "dilbert", "dilbert, the hero", "presenter:producer");
insert into `phoenix`.`user` values("wally", "wally", "wally, the bludger", "producer");
insert into `phoenix`.`user` values("pointyhead", "pointyhead", "pointyhead, the manager", "manager");
insert into `phoenix`.`user` values("catbert", "catbert", "catbert, the hr", "admin:manager");
insert into `phoenix`.`user` values("dogbert", "dogbert", "dogbert, the CEO", "producer:admin");

-- -----------------------------------------------------
-- Table `phoenix`.`radio-program`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phoenix`.`tblradioprogram` ;

CREATE TABLE IF NOT EXISTS `phoenix`.`tblradioprogram` (
  `name` VARCHAR(45) NOT NULL ,
  `desc` VARCHAR(100) NULL ,
  `typicalDuration` TIME NULL ,
  PRIMARY KEY (`name`) ,
  UNIQUE KEY `name_UNIQUE` (`name`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Insert Data For Table `phoenix`.`radio-program`
-- -----------------------------------------------------

insert into `phoenix`.`tblradioprogram` values("short news", "summarised 5 minutes broadcasted every 2 hours", '00:05:00');
insert into `phoenix`.`tblradioprogram` values("news", "full news broadcasted four times a day", '00:30:00');
insert into `phoenix`.`tblradioprogram` values("top 10", "countdown music play of top 10 songs of the week", '01:00:00');
insert into `phoenix`.`tblradioprogram` values("your choice", "audinece ask for music album song of thier choice", '01:00:00');
insert into `phoenix`.`tblradioprogram` values("opinions", "discuss, debate or share opinions regarding a theme or subject", '00:30:00');
insert into `phoenix`.`tblradioprogram` values("noose", "black comedy show", '00:30:00');
insert into `phoenix`.`tblradioprogram` values("ppk", "phu chu kang comedy show", '00:30:00');
insert into `phoenix`.`tblradioprogram` values("dance floor", "dance show", '00:30:00');
insert into `phoenix`.`tblradioprogram` values("charity", "president charity show for unfortunate", '00:30:00');

-- -----------------------------------------------------
-- Table `phoenix`.`annual-schedule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phoenix`.`tblannualschedule` ;

CREATE  TABLE IF NOT EXISTS `phoenix`.`tblannualschedule` (
  `year` VARCHAR(4) NOT NULL ,
  `assignedBy` VARCHAR(45) NULL ,
  PRIMARY KEY (`year`) ,
  UNIQUE KEY `year_UNIQUE` (`year`),
  KEY `is_annual_schedule` (`assignedBy`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `phoenix`.`weekly-schedule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phoenix`.`tblweeklyschedule` ;
CREATE  TABLE IF NOT EXISTS `phoenix`.`tblweeklyschedule` (
  `weekId` int(11) NOT NULL ,
  `startDate` DATE NULL ,
  `assignedBy` VARCHAR(45) NULL ,
  `year` varchar(4) NOT NULL,
  PRIMARY KEY (`weekId`,`year`) ,
  CONSTRAINT `assignedBy`
    FOREIGN KEY (`assignedBy` )
    REFERENCES `phoenix`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `startDate_UNIQUE` ON `phoenix`.`tblweeklyschedule` (`startDate` ASC) ;

CREATE INDEX `id_assigned_by` ON `phoenix`.`tblweeklyschedule` (`assignedBy` ASC) ;

-- -----------------------------------------------------
-- Table `phoenix`.`program-slot`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phoenix`.`tblprogramslot` ;

CREATE  TABLE IF NOT EXISTS `phoenix`.`tblprogramslot` (
  `programSlotId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `assignedBy` varchar(45) NULL ,
  `duration` int(11) NULL ,
  `startDate` DATETIME NULL ,
  `program-name` VARCHAR(45) NULL ,
  `presenterId` VARCHAR(45) NULL ,
  `producerId` VARCHAR(45) NULL ,
  `weekId` int(11) NULL ,
  PRIMARY KEY (`programSlotId`) ,
  KEY `assignedBy_idx` (`assignedBy`),
  KEY `weekId_idx` (`weekId`),
  CONSTRAINT `weekId`
    FOREIGN KEY (`weekId` )
    REFERENCES `phoenix`.`tblweeklyschedule` (`weekId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `programSlotId_UNIQUE` ON `phoenix`.`tblprogramslot` (`programSlotId` ASC) ;