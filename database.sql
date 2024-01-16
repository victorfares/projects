-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema remaketp2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema remaketp2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS remaketp2 ;
USE remaketp2 ;

-- -----------------------------------------------------
-- Table remaketp2.SETOR
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS remaketp2.SETOR (
  setor_ident INT NOT NULL,
  setor_nome VARCHAR(45) NOT NULL,
  PRIMARY KEY (setor_ident))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table remaketp2.SUBSETOR
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS remaketp2.SUBSETOR (
  subsetor_ident INT NOT NULL,
  subsetor_nome VARCHAR(60) NOT NULL,
  setor_ident INT NOT NULL,
  PRIMARY KEY (subsetor_ident),
  INDEX fk_SUBSETOR_SETOR_idx (setor_ident ASC) VISIBLE,
  CONSTRAINT fk_SUBSETOR_SETOR
    FOREIGN KEY (setor_ident)
    REFERENCES remaketp2.SETOR (setor_ident)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table remaketp2.SEGMENTO
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS remaketp2.SEGMENTO (
  seg_ident INT NOT NULL,
  seg_nome VARCHAR(80) NOT NULL,
  subsetor_ident INT NOT NULL,
  PRIMARY KEY (seg_ident),
  INDEX fk_SEGMENTO_SUBSETOR1_idx (subsetor_ident ASC) VISIBLE,
  CONSTRAINT fk_SEGMENTO_SUBSETOR1
    FOREIGN KEY (subsetor_ident)
    REFERENCES remaketp2.SUBSETOR (subsetor_ident)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table remaketp2.GOVERNANCA
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS remaketp2.GOVERNANCA (
  gov_ident VARCHAR(10) NOT NULL,
  gov_nome VARCHAR(45) NOT NULL,
  PRIMARY KEY (gov_ident))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table remaketp2.EMPRESA
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS remaketp2.EMPRESA (
  emp_ident VARCHAR(10) NOT NULL,
  emp_nome VARCHAR(45) NOT NULL,
  gov_ident VARCHAR(10) NULL,
  seg_ident INT NOT NULL,
  PRIMARY KEY (emp_ident),
  INDEX fk_EMPRESA_GOVERNANCA1_idx (gov_ident ASC) VISIBLE,
  INDEX fk_EMPRESA_SEGMENTO1_idx (seg_ident ASC) VISIBLE,
  CONSTRAINT fk_EMPRESA_GOVERNANCA1
    FOREIGN KEY (gov_ident)
    REFERENCES remaketp2.GOVERNANCA (gov_ident)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_EMPRESA_SEGMENTO1
    FOREIGN KEY (seg_ident)
    REFERENCES remaketp2.SEGMENTO (seg_ident)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table remaketp2.acao
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS remaketp2.acao (
  acao_codigo VARCHAR(20) NOT NULL,
  emp_ident VARCHAR(10) NOT NULL,
  PRIMARY KEY (acao_codigo),
  INDEX fk_acao_EMPRESA1_idx (emp_ident ASC) VISIBLE,
  CONSTRAINT fk_acao_EMPRESA1
    FOREIGN KEY (emp_ident)
    REFERENCES remaketp2.EMPRESA (emp_ident)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table remaketp2.tipo_indice
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS remaketp2.tipo_indice (
  tipo_ident INT NOT NULL,
  tipo_nome VARCHAR(45) NULL,
  PRIMARY KEY (tipo_ident))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table remaketp2.indice
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS remaketp2.indice (
  ind_ident VARCHAR(45) NOT NULL,
  ind_nome VARCHAR(45) NOT NULL,
  tipo_ident INT NOT NULL,
  PRIMARY KEY (ind_ident),
  INDEX fk_indice_tipo_indice1_idx (tipo_ident ASC) VISIBLE,
  CONSTRAINT fk_indice_tipo_indice1
    FOREIGN KEY (tipo_ident)
    REFERENCES remaketp2.tipo_indice (tipo_ident)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table remaketp2.listagem
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS remaketp2.listagem (
  acao_codigo VARCHAR(20) NOT NULL,
  ind_ident VARCHAR(45) NOT NULL,
  list_quadrimestre INT NOT NULL,
  list_ano YEAR(4) NOT NULL,
  list_qtdacoes INT NOT NULL,
  list_percentual FLOAT NOT NULL,
  PRIMARY KEY (acao_codigo, ind_ident, list_quadrimestre),
  INDEX fk_acao_has_indice_indice1_idx (ind_ident ASC) VISIBLE,
  INDEX fk_acao_has_indice_acao1_idx (acao_codigo ASC) VISIBLE,
  CONSTRAINT fk_acao_has_indice_acao1
    FOREIGN KEY (acao_codigo)
    REFERENCES remaketp2.acao (acao_codigo)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_acao_has_indice_indice1
    FOREIGN KEY (ind_ident)
    REFERENCES remaketp2.indice (ind_ident)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table remaketp2.mercado
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS remaketp2.mercado (
  merc_ident INT NOT NULL,
  merc_nome VARCHAR(45) NOT NULL,
  PRIMARY KEY (merc_ident))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table remaketp2.cotacao
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS remaketp2.cotacao (
  cot_data DATE NOT NULL,
  acao_codigo VARCHAR(20) NOT NULL,
  cot_abertura FLOAT NOT NULL,
  cot_max FLOAT NOT NULL,
  cot_min FLOAT NOT NULL,
  cot_fechamento FLOAT NULL,
  cot_totalnegocios INT NOT NULL,
  cot_totalacoes INT NOT NULL,
  cot_volume FLOAT NOT NULL,
  merc_ident INT NOT NULL,
  INDEX fk_cotacao_acao1_idx (acao_codigo ASC) VISIBLE,
  PRIMARY KEY (acao_codigo),
  INDEX fk_cotacao_mercado1_idx (merc_ident ASC) VISIBLE,
  CONSTRAINT fk_cotacao_acao1
    FOREIGN KEY (acao_codigo)
    REFERENCES remaketp2.acao (acao_codigo)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_cotacao_mercado1
    FOREIGN KEY (merc_ident)
    REFERENCES remaketp2.mercado (merc_ident)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
