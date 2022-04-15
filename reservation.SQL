--
-- Create Schema Script 
--   Database Version   : 10.2.0.1.0 
--   Toad Version       : 10.6.1.3 
--   DB Connect String  : 192.168.0.150/XE 
--   Schema             : TDS 
--   Script Created by  : TDS 
--   Script Created at  : 20/08/2011 13:21:36 
--   Physical Location  :  
--   Notes              :  
--

-- Object Counts: 
--   Roles: 2           Sys Privs: 168      Roles: 10            
--   Users: 1           Sys Privs: 1        Roles: 2             
--   Tablespaces: 2     DataFiles: 1        TempFiles: 1 
-- 
--   Directories: 2 
--   Functions: 7       Lines of Code: 290 
--   Indexes: 36        Columns: 36         
--   Procedures: 18     Lines of Code: 649 
--   Tables: 28         Columns: 170        Constraints: 34     
--   Types: 1 
--   Views: 8           Columns: 55         


-- "Set define off" turns off substitution variables. 
Set define off; 

--
-- TEMP  (Tablespace) 
--
CREATE TEMPORARY TABLESPACE TEMP TEMPFILE 
  'C:\ORACLEXE\ORADATA\XE\TEMP.DBF' SIZE 53M AUTOEXTEND ON NEXT 640K MAXSIZE UNLIMITED
TABLESPACE GROUP ''
EXTENT MANAGEMENT LOCAL UNIFORM SIZE 1M;


--
-- USERS  (Tablespace) 
--
CREATE TABLESPACE USERS DATAFILE 
  'C:\ORACLEXE\ORADATA\XE\USERS.DBF' SIZE 870M AUTOEXTEND ON NEXT 10M MAXSIZE 5120M
LOGGING
ONLINE
EXTENT MANAGEMENT LOCAL AUTOALLOCATE
BLOCKSIZE 8K
SEGMENT SPACE MANAGEMENT AUTO
FLASHBACK ON;


--
-- "DEFAULT"  (Profile) 
--
CREATE PROFILE "DEFAULT" LIMIT
  SESSIONS_PER_USER UNLIMITED
  CPU_PER_SESSION UNLIMITED
  CPU_PER_CALL UNLIMITED
  CONNECT_TIME UNLIMITED
  IDLE_TIME UNLIMITED
  LOGICAL_READS_PER_SESSION UNLIMITED
  LOGICAL_READS_PER_CALL UNLIMITED
  COMPOSITE_LIMIT UNLIMITED
  PRIVATE_SGA UNLIMITED
  FAILED_LOGIN_ATTEMPTS 10
  PASSWORD_LIFE_TIME UNLIMITED
  PASSWORD_REUSE_TIME UNLIMITED
  PASSWORD_REUSE_MAX UNLIMITED
  PASSWORD_LOCK_TIME UNLIMITED
  PASSWORD_GRACE_TIME UNLIMITED
  PASSWORD_VERIFY_FUNCTION NULL;


--
-- DBA  (Role) 
--
CREATE ROLE DBA NOT IDENTIFIED;

GRANT CHANGE NOTIFICATION TO DBA WITH ADMIN OPTION;
GRANT ADMINISTER ANY SQL TUNING SET TO DBA WITH ADMIN OPTION;
GRANT ALTER ANY SQL PROFILE TO DBA WITH ADMIN OPTION;
BEGIN
SYS.DBMS_RULE_ADM.GRANT_SYSTEM_PRIVILEGE(
  PRIVILEGE    => SYS.DBMS_RULE_ADM.CREATE_RULE_OBJ,
  GRANTEE      => 'DBA',
  GRANT_OPTION => TRUE);
END;
/
GRANT EXPORT FULL DATABASE TO DBA WITH ADMIN OPTION;
BEGIN
SYS.DBMS_RULE_ADM.GRANT_SYSTEM_PRIVILEGE(
  PRIVILEGE    => SYS.DBMS_RULE_ADM.EXECUTE_ANY_EVALUATION_CONTEXT,
  GRANTEE      => 'DBA',
  GRANT_OPTION => TRUE);
END;
/
BEGIN
SYS.DBMS_AQADM.GRANT_SYSTEM_PRIVILEGE (
  PRIVILEGE    => 'DEQUEUE_ANY',
  GRANTEE      => 'DBA',
  ADMIN_OPTION => TRUE);
END;
/
GRANT DROP ANY INDEXTYPE TO DBA WITH ADMIN OPTION;
GRANT ALTER ANY INDEXTYPE TO DBA WITH ADMIN OPTION;
GRANT EXECUTE ANY LIBRARY TO DBA WITH ADMIN OPTION;
GRANT CREATE ANY LIBRARY TO DBA WITH ADMIN OPTION;
GRANT CREATE ANY DIRECTORY TO DBA WITH ADMIN OPTION;
GRANT ALTER PROFILE TO DBA WITH ADMIN OPTION;
GRANT EXECUTE ANY PROCEDURE TO DBA WITH ADMIN OPTION;
GRANT CREATE ROLE TO DBA WITH ADMIN OPTION;
GRANT SELECT ANY SEQUENCE TO DBA WITH ADMIN OPTION;
GRANT DROP ANY INDEX TO DBA WITH ADMIN OPTION;
GRANT UPDATE ANY TABLE TO DBA WITH ADMIN OPTION;
GRANT INSERT ANY TABLE TO DBA WITH ADMIN OPTION;
GRANT SELECT ANY TABLE TO DBA WITH ADMIN OPTION;
GRANT DROP ROLLBACK SEGMENT TO DBA WITH ADMIN OPTION;
GRANT BECOME USER TO DBA WITH ADMIN OPTION;
GRANT DROP TABLESPACE TO DBA WITH ADMIN OPTION;
GRANT ALTER SESSION TO DBA WITH ADMIN OPTION;
GRANT CREATE SESSION TO DBA WITH ADMIN OPTION;
GRANT ANALYZE ANY DICTIONARY TO DBA WITH ADMIN OPTION;
BEGIN
SYS.DBMS_RULE_ADM.GRANT_SYSTEM_PRIVILEGE(
  PRIVILEGE    => SYS.DBMS_RULE_ADM.ALTER_ANY_RULE_SET,
  GRANTEE      => 'DBA',
  GRANT_OPTION => TRUE);
END;
/
BEGIN
SYS.DBMS_RULE_ADM.GRANT_SYSTEM_PRIVILEGE(
  PRIVILEGE    => SYS.DBMS_RULE_ADM.CREATE_RULE_SET_OBJ,
  GRANTEE      => 'DBA',
  GRANT_OPTION => TRUE);
END;
/
GRANT DEBUG ANY PROCEDURE TO DBA WITH ADMIN OPTION;
GRANT CREATE DIMENSION TO DBA WITH ADMIN OPTION;
GRANT ALTER ANY LIBRARY TO DBA WITH ADMIN OPTION;
GRANT UNDER ANY TYPE TO DBA WITH ADMIN OPTION;
GRANT DROP ANY MATERIALIZED VIEW TO DBA WITH ADMIN OPTION;
GRANT DROP ANY TRIGGER TO DBA WITH ADMIN OPTION;
GRANT ALTER ANY PROCEDURE TO DBA WITH ADMIN OPTION;
GRANT FORCE ANY TRANSACTION TO DBA WITH ADMIN OPTION;
GRANT ALTER DATABASE TO DBA WITH ADMIN OPTION;
GRANT DELETE ANY TABLE TO DBA WITH ADMIN OPTION;
GRANT ALTER ROLLBACK SEGMENT TO DBA WITH ADMIN OPTION;
GRANT EXECUTE ANY PROGRAM TO DBA WITH ADMIN OPTION;
BEGIN
SYS.DBMS_RULE_ADM.GRANT_SYSTEM_PRIVILEGE(
  PRIVILEGE    => SYS.DBMS_RULE_ADM.EXECUTE_ANY_RULE,
  GRANTEE      => 'DBA',
  GRANT_OPTION => TRUE);
END;
/
GRANT IMPORT FULL DATABASE TO DBA WITH ADMIN OPTION;
BEGIN
SYS.DBMS_RULE_ADM.GRANT_SYSTEM_PRIVILEGE(
  PRIVILEGE    => SYS.DBMS_RULE_ADM.EXECUTE_ANY_RULE_SET,
  GRANTEE      => 'DBA',
  GRANT_OPTION => TRUE);
END;
/
BEGIN
SYS.DBMS_RULE_ADM.GRANT_SYSTEM_PRIVILEGE(
  PRIVILEGE    => SYS.DBMS_RULE_ADM.CREATE_ANY_RULE_SET,
  GRANTEE      => 'DBA',
  GRANT_OPTION => TRUE);
END;
/
GRANT FLASHBACK ANY TABLE TO DBA WITH ADMIN OPTION;
GRANT RESUMABLE TO DBA WITH ADMIN OPTION;
GRANT ADMINISTER DATABASE TRIGGER TO DBA WITH ADMIN OPTION;
GRANT CREATE ANY OUTLINE TO DBA WITH ADMIN OPTION;
GRANT ALTER ANY DIMENSION TO DBA WITH ADMIN OPTION;
GRANT CREATE ANY DIMENSION TO DBA WITH ADMIN OPTION;
GRANT EXECUTE ANY OPERATOR TO DBA WITH ADMIN OPTION;
GRANT CREATE TYPE TO DBA WITH ADMIN OPTION;
GRANT CREATE TRIGGER TO DBA WITH ADMIN OPTION;
GRANT GRANT ANY ROLE TO DBA WITH ADMIN OPTION;
GRANT DROP ANY VIEW TO DBA WITH ADMIN OPTION;
GRANT CREATE VIEW TO DBA WITH ADMIN OPTION;
GRANT LOCK ANY TABLE TO DBA WITH ADMIN OPTION;
GRANT ALTER USER TO DBA WITH ADMIN OPTION;
GRANT CREATE USER TO DBA WITH ADMIN OPTION;
GRANT ALTER TABLESPACE TO DBA WITH ADMIN OPTION;
GRANT CREATE TABLESPACE TO DBA WITH ADMIN OPTION;
GRANT RESTRICTED SESSION TO DBA WITH ADMIN OPTION;
GRANT CREATE ANY JOB TO DBA WITH ADMIN OPTION;
GRANT CREATE JOB TO DBA WITH ADMIN OPTION;
BEGIN
SYS.DBMS_RULE_ADM.GRANT_SYSTEM_PRIVILEGE(
  PRIVILEGE    => SYS.DBMS_RULE_ADM.CREATE_ANY_RULE,
  GRANTEE      => 'DBA',
  GRANT_OPTION => TRUE);
END;
/
BEGIN
SYS.DBMS_RULE_ADM.GRANT_SYSTEM_PRIVILEGE(
  PRIVILEGE    => SYS.DBMS_RULE_ADM.DROP_ANY_EVALUATION_CONTEXT,
  GRANTEE      => 'DBA',
  GRANT_OPTION => TRUE);
END;
/
BEGIN
SYS.DBMS_RULE_ADM.GRANT_SYSTEM_PRIVILEGE(
  PRIVILEGE    => SYS.DBMS_RULE_ADM.CREATE_ANY_EVALUATION_CONTEXT,
  GRANTEE      => 'DBA',
  GRANT_OPTION => TRUE);
END;
/
BEGIN
SYS.DBMS_RULE_ADM.GRANT_SYSTEM_PRIVILEGE(
  PRIVILEGE    => SYS.DBMS_RULE_ADM.CREATE_EVALUATION_CONTEXT_OBJ,
  GRANTEE      => 'DBA',
  GRANT_OPTION => TRUE);
END;
/
GRANT GRANT ANY OBJECT PRIVILEGE TO DBA WITH ADMIN OPTION;
GRANT SELECT ANY DICTIONARY TO DBA WITH ADMIN OPTION;
GRANT DROP ANY DIMENSION TO DBA WITH ADMIN OPTION;
GRANT UNDER ANY TABLE TO DBA WITH ADMIN OPTION;
GRANT CREATE INDEXTYPE TO DBA WITH ADMIN OPTION;
GRANT CREATE ANY OPERATOR TO DBA WITH ADMIN OPTION;
GRANT DROP ANY LIBRARY TO DBA WITH ADMIN OPTION;
GRANT ANALYZE ANY TO DBA WITH ADMIN OPTION;
GRANT ALTER ANY ROLE TO DBA WITH ADMIN OPTION;
GRANT CREATE ANY SEQUENCE TO DBA WITH ADMIN OPTION;
GRANT CREATE ANY INDEX TO DBA WITH ADMIN OPTION;
GRANT CREATE ANY TABLE TO DBA WITH ADMIN OPTION;
GRANT MANAGE FILE GROUP TO DBA WITH ADMIN OPTION;
GRANT MANAGE SCHEDULER TO DBA WITH ADMIN OPTION;
BEGIN
SYS.DBMS_RESOURCE_MANAGER_PRIVS.GRANT_SYSTEM_PRIVILEGE
  (GRANTEE_NAME   => 'DBA', 
   PRIVILEGE_NAME => 'ADMINISTER_RESOURCE_MANAGER',
   ADMIN_OPTION   => TRUE);
END;
/
GRANT ALTER ANY OUTLINE TO DBA WITH ADMIN OPTION;
GRANT DROP ANY CONTEXT TO DBA WITH ADMIN OPTION;
GRANT EXECUTE ANY INDEXTYPE TO DBA WITH ADMIN OPTION;
GRANT UNDER ANY VIEW TO DBA WITH ADMIN OPTION;
GRANT DROP ANY TYPE TO DBA WITH ADMIN OPTION;
GRANT ALTER ANY TYPE TO DBA WITH ADMIN OPTION;
GRANT ALTER ANY MATERIALIZED VIEW TO DBA WITH ADMIN OPTION;
GRANT CREATE PROFILE TO DBA WITH ADMIN OPTION;
GRANT DROP PUBLIC DATABASE LINK TO DBA WITH ADMIN OPTION;
GRANT ALTER ANY INDEX TO DBA WITH ADMIN OPTION;
GRANT CREATE CLUSTER TO DBA WITH ADMIN OPTION;
GRANT COMMENT ANY TABLE TO DBA WITH ADMIN OPTION;
GRANT DROP ANY TABLE TO DBA WITH ADMIN OPTION;
GRANT CREATE ROLLBACK SEGMENT TO DBA WITH ADMIN OPTION;
GRANT AUDIT SYSTEM TO DBA WITH ADMIN OPTION;
GRANT ALTER SYSTEM TO DBA WITH ADMIN OPTION;
GRANT MANAGE ANY FILE GROUP TO DBA WITH ADMIN OPTION;
GRANT EXECUTE ANY CLASS TO DBA WITH ADMIN OPTION;
BEGIN
SYS.DBMS_RULE_ADM.GRANT_SYSTEM_PRIVILEGE(
  PRIVILEGE    => SYS.DBMS_RULE_ADM.DROP_ANY_RULE_SET,
  GRANTEE      => 'DBA',
  GRANT_OPTION => TRUE);
END;
/
GRANT DEBUG CONNECT SESSION TO DBA WITH ADMIN OPTION;
GRANT ON COMMIT REFRESH TO DBA WITH ADMIN OPTION;
BEGIN
SYS.DBMS_AQADM.GRANT_SYSTEM_PRIVILEGE (
  PRIVILEGE    => 'ENQUEUE_ANY',
  GRANTEE      => 'DBA',
  ADMIN_OPTION => TRUE);
END;
/
GRANT CREATE ANY INDEXTYPE TO DBA WITH ADMIN OPTION;
GRANT CREATE ANY TYPE TO DBA WITH ADMIN OPTION;
GRANT DROP ANY DIRECTORY TO DBA WITH ADMIN OPTION;
GRANT ALTER RESOURCE COST TO DBA WITH ADMIN OPTION;
GRANT CREATE ANY PROCEDURE TO DBA WITH ADMIN OPTION;
GRANT CREATE PROCEDURE TO DBA WITH ADMIN OPTION;
GRANT FORCE TRANSACTION TO DBA WITH ADMIN OPTION;
GRANT ALTER ANY SEQUENCE TO DBA WITH ADMIN OPTION;
GRANT CREATE SEQUENCE TO DBA WITH ADMIN OPTION;
GRANT CREATE ANY VIEW TO DBA WITH ADMIN OPTION;
GRANT DROP PUBLIC SYNONYM TO DBA WITH ADMIN OPTION;
GRANT DROP ANY SYNONYM TO DBA WITH ADMIN OPTION;
GRANT CREATE ANY CLUSTER TO DBA WITH ADMIN OPTION;
GRANT BACKUP ANY TABLE TO DBA WITH ADMIN OPTION;
GRANT CREATE TABLE TO DBA WITH ADMIN OPTION;
GRANT ADMINISTER SQL TUNING SET TO DBA WITH ADMIN OPTION;
GRANT MERGE ANY VIEW TO DBA WITH ADMIN OPTION;
GRANT DROP ANY OUTLINE TO DBA WITH ADMIN OPTION;
GRANT CREATE OPERATOR TO DBA WITH ADMIN OPTION;
GRANT CREATE LIBRARY TO DBA WITH ADMIN OPTION;
GRANT GRANT ANY PRIVILEGE TO DBA WITH ADMIN OPTION;
GRANT DROP PROFILE TO DBA WITH ADMIN OPTION;
GRANT ALTER ANY TRIGGER TO DBA WITH ADMIN OPTION;
GRANT CREATE ANY TRIGGER TO DBA WITH ADMIN OPTION;
GRANT DROP ANY PROCEDURE TO DBA WITH ADMIN OPTION;
GRANT AUDIT ANY TO DBA WITH ADMIN OPTION;
GRANT DROP ANY ROLE TO DBA WITH ADMIN OPTION;
GRANT DROP ANY SEQUENCE TO DBA WITH ADMIN OPTION;
GRANT CREATE PUBLIC SYNONYM TO DBA WITH ADMIN OPTION;
GRANT CREATE SYNONYM TO DBA WITH ADMIN OPTION;
GRANT DROP ANY CLUSTER TO DBA WITH ADMIN OPTION;
GRANT ALTER ANY TABLE TO DBA WITH ADMIN OPTION;
GRANT CREATE EXTERNAL JOB TO DBA WITH ADMIN OPTION;
GRANT READ ANY FILE GROUP TO DBA WITH ADMIN OPTION;
GRANT CREATE ANY SQL PROFILE TO DBA WITH ADMIN OPTION;
GRANT DROP ANY SQL PROFILE TO DBA WITH ADMIN OPTION;
GRANT SELECT ANY TRANSACTION TO DBA WITH ADMIN OPTION;
GRANT ADVISOR TO DBA WITH ADMIN OPTION;
BEGIN
SYS.DBMS_RULE_ADM.GRANT_SYSTEM_PRIVILEGE(
  PRIVILEGE    => SYS.DBMS_RULE_ADM.DROP_ANY_RULE,
  GRANTEE      => 'DBA',
  GRANT_OPTION => TRUE);
END;
/
BEGIN
SYS.DBMS_RULE_ADM.GRANT_SYSTEM_PRIVILEGE(
  PRIVILEGE    => SYS.DBMS_RULE_ADM.ALTER_ANY_RULE,
  GRANTEE      => 'DBA',
  GRANT_OPTION => TRUE);
END;
/
BEGIN
SYS.DBMS_RULE_ADM.GRANT_SYSTEM_PRIVILEGE(
  PRIVILEGE    => SYS.DBMS_RULE_ADM.ALTER_ANY_EVALUATION_CONTEXT,
  GRANTEE      => 'DBA',
  GRANT_OPTION => TRUE);
END;
/
GRANT CREATE ANY CONTEXT TO DBA WITH ADMIN OPTION;
BEGIN
SYS.DBMS_AQADM.GRANT_SYSTEM_PRIVILEGE (
  PRIVILEGE    => 'MANAGE_ANY',
  GRANTEE      => 'DBA',
  ADMIN_OPTION => TRUE);
END;
/
GRANT GLOBAL QUERY REWRITE TO DBA WITH ADMIN OPTION;
GRANT QUERY REWRITE TO DBA WITH ADMIN OPTION;
GRANT DROP ANY OPERATOR TO DBA WITH ADMIN OPTION;
GRANT EXECUTE ANY TYPE TO DBA WITH ADMIN OPTION;
GRANT CREATE ANY MATERIALIZED VIEW TO DBA WITH ADMIN OPTION;
GRANT CREATE MATERIALIZED VIEW TO DBA WITH ADMIN OPTION;
GRANT CREATE PUBLIC DATABASE LINK TO DBA WITH ADMIN OPTION;
GRANT CREATE DATABASE LINK TO DBA WITH ADMIN OPTION;
GRANT CREATE ANY SYNONYM TO DBA WITH ADMIN OPTION;
GRANT ALTER ANY CLUSTER TO DBA WITH ADMIN OPTION;
GRANT DROP USER TO DBA WITH ADMIN OPTION;
GRANT MANAGE TABLESPACE TO DBA WITH ADMIN OPTION;
--
-- SCHEDULER_ADMIN  (Role) 
--
GRANT SCHEDULER_ADMIN TO DBA WITH ADMIN OPTION;
--
-- EXECUTE_CATALOG_ROLE  (Role) 
--
GRANT EXECUTE_CATALOG_ROLE TO DBA WITH ADMIN OPTION;
--
-- DELETE_CATALOG_ROLE  (Role) 
--
GRANT DELETE_CATALOG_ROLE TO DBA WITH ADMIN OPTION;
--
-- PLUSTRACE  (Role) 
--
GRANT PLUSTRACE TO DBA WITH ADMIN OPTION;
--
-- SELECT_CATALOG_ROLE  (Role) 
--
GRANT SELECT_CATALOG_ROLE TO DBA WITH ADMIN OPTION;
--
-- EXP_FULL_DATABASE  (Role) 
--
GRANT EXP_FULL_DATABASE TO DBA;
--
-- GATHER_SYSTEM_STATISTICS  (Role) 
--
GRANT GATHER_SYSTEM_STATISTICS TO DBA;
--
-- IMP_FULL_DATABASE  (Role) 
--
GRANT IMP_FULL_DATABASE TO DBA;
--
-- XDBADMIN  (Role) 
--
GRANT XDBADMIN TO DBA;
--
-- XDBWEBSERVICES  (Role) 
--
GRANT XDBWEBSERVICES TO DBA;


--
-- RESOURCE  (Role) 
--
CREATE ROLE RESOURCE NOT IDENTIFIED;

GRANT CREATE TRIGGER TO RESOURCE;
GRANT CREATE SEQUENCE TO RESOURCE;
GRANT CREATE TYPE TO RESOURCE;
GRANT CREATE PROCEDURE TO RESOURCE;
GRANT CREATE CLUSTER TO RESOURCE;
GRANT CREATE OPERATOR TO RESOURCE;
GRANT CREATE INDEXTYPE TO RESOURCE;
GRANT CREATE TABLE TO RESOURCE;


--
-- TDS  (User) 
--
CREATE USER TDS
  IDENTIFIED BY VALUES '44AC2CF9B5780687'
  DEFAULT TABLESPACE USERS
  TEMPORARY TABLESPACE TEMP
  PROFILE DEFAULT
  ACCOUNT UNLOCK;
  -- 2 Roles for TDS 
  GRANT DBA TO TDS;
  GRANT RESOURCE TO TDS;
  ALTER USER TDS DEFAULT ROLE ALL;
  -- 1 System Privilege for TDS 
  GRANT UNLIMITED TABLESPACE TO TDS;


--
-- MYTABLETYPE  (Type) 
--
CREATE OR REPLACE TYPE TDS."MYTABLETYPE"                                                                          as table of varchar2(255);
/


--
-- DATA_PUMP_DIR  (Directory) 
--
CREATE OR REPLACE DIRECTORY 
DATA_PUMP_DIR AS 
'C:\oraclexe\app\oracle\admin\XE\dpdump\';


--
-- ORACLECLRDIR  (Directory) 
--
CREATE OR REPLACE DIRECTORY 
ORACLECLRDIR AS 
'C:\oraclexe\app\oracle\product\10.2.0\server\bin\clr';


--
-- H_REALISASI  (Table) 
--
CREATE TABLE TDS.H_REALISASI
(
  KODEREALISASI   VARCHAR2(20 BYTE)             NOT NULL,
  KODEBP          VARCHAR2(20 BYTE),
  KODEPENGAJUAN   VARCHAR2(20 BYTE),
  JENISREALISASI  VARCHAR2(50 BYTE),
  TANGGAL         DATE,
  TOTALBIAYA      NUMBER,
  IE              VARCHAR2(1 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOCOMPRESS ;


--
-- D_TGLPENGAJUAN  (Table) 
--
CREATE TABLE TDS.D_TGLPENGAJUAN
(
  KODETP         VARCHAR2(25 BYTE)              NOT NULL,
  KODEPENGAJUAN  VARCHAR2(20 BYTE),
  TGLAWAL        DATE,
  TGLAKHIR       DATE,
  NAMATARIF      VARCHAR2(20 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOCOMPRESS ;


--
-- M_PARAMETER  (Table) 
--
CREATE TABLE TDS.M_PARAMETER
(
  PARNAME   VARCHAR2(255 BYTE),
  PARVALUE  VARCHAR2(255 BYTE),
  PARTYPE   VARCHAR2(50 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOCOMPRESS ;


--
-- H_PENGAJUAN  (Table) 
--
CREATE TABLE TDS.H_PENGAJUAN
(
  KODEPENGAJUAN   VARCHAR2(20 BYTE)             NOT NULL,
  KODEBP          VARCHAR2(20 BYTE),
  JENISREALISASI  VARCHAR2(50 BYTE),
  TANGGAL         DATE,
  VERSI           NUMBER,
  TOTALBIAYA      NUMBER,
  KODEORG         VARCHAR2(20 BYTE),
  BATAL           NUMBER                        DEFAULT 0,
  IE              VARCHAR2(1 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOCOMPRESS ;


--
-- TEMP_ITEM  (Table) 
--
CREATE TABLE TDS.TEMP_ITEM
(
  KODETP    VARCHAR2(25 BYTE),
  KODEITEM  VARCHAR2(20 BYTE),
  JAMAWAL   VARCHAR2(20 BYTE),
  JAMAKHIR  VARCHAR2(20 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOCOMPRESS ;


--
-- D_TGLREALISASI  (Table) 
--
CREATE TABLE TDS.D_TGLREALISASI
(
  KODETR         VARCHAR2(25 BYTE)              NOT NULL,
  KODEREALISASI  VARCHAR2(20 BYTE),
  TGLAWAL        DATE,
  TGLAKHIR       DATE,
  NAMATARIF      VARCHAR2(20 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOCOMPRESS ;


--
-- M_HARILIBUR  (Table) 
--
CREATE TABLE TDS.M_HARILIBUR
(
  KETERANGAN  VARCHAR2(255 BYTE),
  TANGGAL     DATE
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOCOMPRESS ;


--
-- M_JENIS  (Table) 
--
CREATE TABLE TDS.M_JENIS
(
  KODEJENIS  VARCHAR2(20 BYTE)                  NOT NULL,
  NAMAJENIS  VARCHAR2(50 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOCOMPRESS ;


--
-- M_MENU  (Table) 
--
CREATE TABLE TDS.M_MENU
(
  KODEFORM    VARCHAR2(20 BYTE)                 NOT NULL,
  NAMAFORM    VARCHAR2(200 BYTE),
  KODEPARENT  VARCHAR2(255 BYTE),
  URL         VARCHAR2(500 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOCOMPRESS ;


--
-- M_PEMBEBANAN  (Table) 
--
CREATE TABLE TDS.M_PEMBEBANAN
(
  KODEPEMBEBANAN  VARCHAR2(20 BYTE)             NOT NULL,
  NAMAPEMBEBANAN  VARCHAR2(50 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOCOMPRESS ;


--
-- M_SETTINGACC  (Table) 
--
CREATE TABLE TDS.M_SETTINGACC
(
  KODEFORM          VARCHAR2(20 BYTE)           NOT NULL,
  ACCFORM           VARCHAR2(1 BYTE),
  EDITFINALAPPROVE  VARCHAR2(1 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOCOMPRESS ;


--
-- M_USER  (Table) 
--
CREATE TABLE TDS.M_USER
(
  USERID      VARCHAR2(50 BYTE)                 NOT NULL,
  KODEBP      VARCHAR2(20 BYTE),
  PASS        VARCHAR2(30 BYTE),
  AKTIF       VARCHAR2(1 BYTE),
  COSTCENTER  VARCHAR2(20 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOCOMPRESS ;


--
-- MH_KONFIGURASIACC  (Table) 
--
CREATE TABLE TDS.MH_KONFIGURASIACC
(
  KODEACC     VARCHAR2(20 BYTE)                 NOT NULL,
  KODEFORM    VARCHAR2(20 BYTE),
  PRIORITAS   NUMBER,
  KETERANGAN  VARCHAR2(255 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOCOMPRESS ;


--
-- M_OPTION  (Table) 
--
CREATE TABLE TDS.M_OPTION
(
  JAMSEWAMULAI         DATE,
  JAMSEWAAKHIR         DATE,
  SELISIHJAMSEWA       NUMBER,
  JEDAJAMSEWA          NUMBER,
  BATASMINPENGAJUAN    NUMBER,
  BATASMINPERUBAHAN    NUMBER,
  TOLERANSIKELEBIHAN   NUMBER,
  PEMBULATANKELEBIHAN  NUMBER
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOCOMPRESS ;


--
-- JADWALSEWARUANG  (Table) 
--
CREATE GLOBAL TEMPORARY TABLE TDS.JADWALSEWARUANG
(
  ID          VARCHAR2(20 BYTE)                 NOT NULL,
  START_DATE  VARCHAR2(20 BYTE),
  END_DATE    VARCHAR2(20 BYTE),
  TEXT        VARCHAR2(50 BYTE),
  KODEITEM    VARCHAR2(20 BYTE),
  READONLY    VARCHAR2(20 BYTE)
)
ON COMMIT DELETE ROWS;


--
-- TEMP_SD_ITEMPENGAJUAN  (Table) 
--
CREATE GLOBAL TEMPORARY TABLE TDS.TEMP_SD_ITEMPENGAJUAN
(
  ID           VARCHAR2(20 BYTE),
  KODETP       VARCHAR2(25 BYTE),
  KODEITEM     VARCHAR2(20 BYTE),
  JUMLAHAWAL   NUMBER,
  JUMLAHAKHIR  NUMBER,
  JAMAWAL      DATE,
  JAMAKHIR     DATE,
  DURASI       NUMBER,
  BIAYA        NUMBER,
  ANTRIAN      NUMBER,
  KETERANGAN   VARCHAR2(255 BYTE),
  BIAYAPERJAM  NUMBER
)
ON COMMIT DELETE ROWS;


--
-- PK_MH_KONFIGURASIACC  (Index) 
--
CREATE UNIQUE INDEX TDS.PK_MH_KONFIGURASIACC ON TDS.MH_KONFIGURASIACC
(KODEACC)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- RELATIONSHIP_44_FK2  (Index) 
--
CREATE INDEX TDS.RELATIONSHIP_44_FK2 ON TDS.MH_KONFIGURASIACC
(KODEFORM)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- RELATIONSHIP_39_FK  (Index) 
--
CREATE INDEX TDS.RELATIONSHIP_39_FK ON TDS.M_SETTINGACC
(KODEFORM)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- PK_M_PEMBEBANAN  (Index) 
--
CREATE UNIQUE INDEX TDS.PK_M_PEMBEBANAN ON TDS.M_PEMBEBANAN
(KODEPEMBEBANAN)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- PK_M_MENU  (Index) 
--
CREATE UNIQUE INDEX TDS.PK_M_MENU ON TDS.M_MENU
(KODEFORM)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- PK_M_JENIS  (Index) 
--
CREATE UNIQUE INDEX TDS.PK_M_JENIS ON TDS.M_JENIS
(KODEJENIS)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- RELATIONSHIP_23_FK  (Index) 
--
CREATE INDEX TDS.RELATIONSHIP_23_FK ON TDS.H_REALISASI
(KODEBP)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- RELATIONSHIP_25_FK  (Index) 
--
CREATE INDEX TDS.RELATIONSHIP_25_FK ON TDS.H_REALISASI
(KODEPENGAJUAN)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- PK_H_REALISASI  (Index) 
--
CREATE UNIQUE INDEX TDS.PK_H_REALISASI ON TDS.H_REALISASI
(KODEREALISASI)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- RELATIONSHIP_33_FK  (Index) 
--
CREATE INDEX TDS.RELATIONSHIP_33_FK ON TDS.H_PENGAJUAN
(KODEBP)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- PK_D_TGLREALISASI  (Index) 
--
CREATE UNIQUE INDEX TDS.PK_D_TGLREALISASI ON TDS.D_TGLREALISASI
(KODETR)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- RELATIONSHIP_26_FK  (Index) 
--
CREATE INDEX TDS.RELATIONSHIP_26_FK ON TDS.D_TGLREALISASI
(KODEREALISASI)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- PK_D_TGLPENGAJUAN  (Index) 
--
CREATE UNIQUE INDEX TDS.PK_D_TGLPENGAJUAN ON TDS.D_TGLPENGAJUAN
(KODETP)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- RELATIONSHIP_44_FK  (Index) 
--
CREATE INDEX TDS.RELATIONSHIP_44_FK ON TDS.D_TGLPENGAJUAN
(KODEPENGAJUAN)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- PK_M_USER  (Index) 
--
CREATE UNIQUE INDEX TDS.PK_M_USER ON TDS.M_USER
(USERID)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- RELATIONSHIP_40_FK  (Index) 
--
CREATE INDEX TDS.RELATIONSHIP_40_FK ON TDS.M_USER
(KODEBP)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- GEN_KODEJENIS  (Procedure) 
--
CREATE OR REPLACE PROCEDURE TDS.GEN_KODEJENIS
(KodeBaru out Varchar2)
is
  kode M_JENIS.KODEJENIS%type;
  temp number;
begin
  select max(to_number(KODEJENIS)) into temp from M_JENIS;

  if temp is null then
     temp:=1;
  else
     temp:=temp+1;
  end if;
  kode := to_char(temp);
  for i in 1..3-length(to_char(temp)) loop
      kode := '0' || kode;
  end loop;
  KodeBaru := kode;
end;
/


--
-- GEN_KODEPENGAJUAN  (Procedure) 
--
CREATE OR REPLACE PROCEDURE TDS.GEN_KODEPENGAJUAN
(KodeBaru out varchar2,versi in number,tanggaltrans in varchar2)
is
  kode varchar2(20);
  temp number;
begin
  select max(to_number(substr(kodepengajuan,11,4))) into temp 
  from h_pengajuan
  where substr(kodepengajuan,5,2) = to_char(to_date(tanggaltrans,'mm/dd/yyyy'),'YY');--TO_CHAR(sysdate, 'YY');
  
  if temp is null then
     temp:=1;
  else
     temp:=temp+1;
  end if; 

  kode := 'PEN/'||to_char(to_date(tanggaltrans,'mm/dd/yyyy'),'YY')
  	   ||'.'||to_char(to_date(tanggaltrans,'mm/dd/yyyy'),'MM')||'/';--TO_CHAR(sysdate, 'YY')||'.'||TO_CHAR(sysdate, 'MM')||'/';
  for i in 1..4-length(temp) loop
    kode := kode||'0';
  end loop;
  kode:=kode||to_char(temp);
  KodeBaru:=kode||'.'||versi;
end;
/


--
-- GEN_KODETP  (Procedure) 
--
CREATE OR REPLACE PROCEDURE TDS.GEN_KODETP
(KodeBaru out varchar2,pkodepengajuan in varchar2)
is
  kode varchar2(25);
  temp number;
begin
  select max(to_number(substr(kodetp,18,4))) into temp 
  from d_tglpengajuan
  where kodepengajuan = pkodepengajuan;
  
  if temp is null then
     temp:=1;
  else
     temp:=temp+1;
  end if; 

  kode := pkodepengajuan||'-';
  for i in 1..4-length(temp) loop
    kode := kode||'0';
  end loop;
  kode:=kode||to_char(temp);
  KodeBaru:=kode;
end;
/


--
-- CEK_DATAKEMBAR_MHKONFACC  (Procedure) 
--
CREATE OR REPLACE PROCEDURE TDS.CEK_DATAKEMBAR_MHKONFACC 
(varkembar out varchar2, pkodeacc in Varchar2, pkodeform in Varchar2, pprioritas in Integer, pmode in Varchar2)
is
  temp number;
  temp2 number;
  kodekembar varchar2(255);
begin
  select count(*) into temp 
  from MH_KONFIGURASIACC
  where upper(kodeacc) = upper(pkodeacc);
	
  select count(*) into temp2 
  from MH_KONFIGURASIACC 
  where upper(kodeform) = upper(pkodeform) and prioritas = pprioritas and upper(kodeacc) not in upper(pkodeacc);
  
  if temp > 0 then
  	 if (pmode = 'insert') then
  	 	kodekembar := 'Kodeacc '||pkodeacc||', telah ada sebelumnya';	
	 end if; 
  end if;
  if temp2 > 0 then
  		kodekembar := 'Kode Form '||pkodeform||' dengan prioritas '||pprioritas||' telah ada sebelumnya';	 
  end if;
  
  varkembar := kodekembar;
  
end;
/


--
-- GEN_KODEREALISASI  (Procedure) 
--
CREATE OR REPLACE PROCEDURE TDS.GEN_KODEREALISASI
(KodeBaru out varchar2,versi in number,tanggaltrans in varchar2)
is
  kode varchar2(20);
  temp number;
begin
  select max(to_number(substr(koderealisasi,11,4))) into temp 
  from h_realisasi
  where substr(koderealisasi,5,2) = to_char(to_date(tanggaltrans,'mm/dd/yyyy'),'YY');--TO_CHAR(sysdate, 'YY');
  
  if temp is null then
     temp:=1;
  else
     temp:=temp+1;
  end if; 

  kode := 'REA/'||to_char(to_date(tanggaltrans,'mm/dd/yyyy'),'YY')
  	   ||'.'||to_char(to_date(tanggaltrans,'mm/dd/yyyy'),'MM')||'/';  --TO_CHAR(sysdate, 'YY')||'.'||TO_CHAR(sysdate, 'MM')||'/';
  for i in 1..4-length(temp) loop
    kode := kode||'0';
  end loop;
  kode:=kode||to_char(temp);
  KodeBaru:=kode||'.'||versi;
end;
/


--
-- GEN_KODETR  (Procedure) 
--
CREATE OR REPLACE PROCEDURE TDS.GEN_KODETR
(KodeBaru out varchar2,pkoderealisasi in varchar2)
is
  kode varchar2(25);
  temp number;
begin
  select max(to_number(substr(kodetr,18,4))) into temp 
  from d_tglrealisasi
  where koderealisasi = pkoderealisasi;
  
  if temp is null then
     temp:=1;
  else
     temp:=temp+1;
  end if; 

  kode := pkoderealisasi||'-';
  for i in 1..4-length(temp) loop
    kode := kode||'0';
  end loop;
  kode:=kode||to_char(temp);
  KodeBaru:=kode;
end;
/


--
-- CEK_DATAKEMBAR_MMENU  (Procedure) 
--
CREATE OR REPLACE PROCEDURE TDS.CEK_DATAKEMBAR_MMENU (varkembar out varchar2, pkodeform in Varchar2, pnamaform in Varchar2, purl in Varchar2, pmode in Varchar2)
is
  temp number;
  temp2 number;
  temp3 number;
  kodekembar varchar2(255);
begin
  select count(*) into temp 
  from M_MENU 
  where upper(kodeform) = upper(pkodeform);
	
  select count(*) into temp2 
  from M_MENU 
  where upper(namaform) = upper(pnamaform) and upper(kodeform) not in upper(pkodeform);
  
  select count(*) into temp3 
  from M_MENU 
  where upper(url) = upper(purl) and upper(kodeform) not in upper(pkodeform) and url is not null;
  
  if temp > 0 then
  	 if (pmode = 'insert') then
  	 	kodekembar := 'Kode Form '||pkodeform||', telah ada sebelumnya.';	
	 end if; 
  end if;
  if temp2 > 0 then
  		kodekembar := 'Nama Form '||pnamaform||', telah ada sebelumnya.';	 
  end if;
  if temp3 > 0 then
  		kodekembar := 'URL '||purl||', telah ada sebelumnya.';	 
  end if;
  
  varkembar := kodekembar;
  
end;
/


--
-- CEK_DATAKEMBAR_MJENIS  (Procedure) 
--
CREATE OR REPLACE PROCEDURE TDS.CEK_DATAKEMBAR_MJENIS (varkembar out varchar2, pkodejenis in Varchar2, pnamajenis in Varchar2, pmode in Varchar2)
is
  temp number;
  temp2 number;
  kodekembar varchar2(255);
begin
  select count(*) into temp 
  from M_JENIS 
  where upper(kodejenis) = upper(pkodejenis);
	
  select count(*) into temp2 
  from M_JENIS 
  where upper(namajenis) = upper(pnamajenis) and upper(kodejenis) not in upper(pkodejenis);
  
  if temp > 0 then
  	 if (pmode = 'insert') then
  	 	kodekembar := 'Kode jenis '||pkodejenis||', telah ada sebelumnya.';	
	 end if; 
  end if;
  if temp2 > 0 then
  		kodekembar := 'Nama jenis '||pnamajenis||', telah ada sebelumnya.';	 
  end if;
  
  varkembar := kodekembar;
  
end;
/


--
-- CEK_DATAKEMBAR_MSETTINGACC  (Procedure) 
--
CREATE OR REPLACE PROCEDURE TDS.CEK_DATAKEMBAR_MSETTINGACC (varkembar out varchar2, pkodeform in Varchar2)
is
  temp number;
  kodekembar varchar2(255);
begin
  select count(*) into temp 
  from M_SETTINGACC 
  where kodeform = pkodeform;
  
  if temp > 0 then
  	 kodekembar := 'Kode Form '||pkodeform||',';	  
  end if;
   
  varkembar := kodekembar;
  
end;
/


--
-- CEK_DATAKEMBAR_MUSER  (Procedure) 
--
CREATE OR REPLACE PROCEDURE TDS.CEK_DATAKEMBAR_MUSER 
(varkembar out varchar2, puserid in Varchar2, pkodebp in Varchar2, pmode in Varchar2)
is
  temp number;
  temp2 number;
  kodekembar varchar2(255);
begin

  select count(*) into temp 
  from M_USER
  where upper(userid) = upper(puserid);
	
  select count(*) into temp2 
  from M_USER
  where kodebp = pkodebp and upper(userid) not in upper(puserid);
  
  if temp > 0 then
  	 if (pmode = 'insert') then
  	 	kodekembar := 'Userid '||puserid||', telah ada sebelumnya';	
	 end if; 
  end if;
  if temp2 > 0 then
  		kodekembar := 'Kodebp '||pkodebp||' telah ada sebelumnya';	 
  end if;
  
  varkembar := kodekembar;
  
end;
/


--
-- CEK_DATAKEMBAR_MPEMBEBANAN  (Procedure) 
--
CREATE OR REPLACE PROCEDURE TDS.CEK_DATAKEMBAR_MPEMBEBANAN (varkembar out varchar2, pkodepembebanan in Varchar2, pnamapembebanan in Varchar2, pmode in Varchar2)
is
  temp number;
  temp2 number;
  kodekembar varchar2(255);
begin
  select count(*) into temp 
  from M_PEMBEBANAN  
  where upper(kodepembebanan) = upper(pkodepembebanan);
	
  select count(*) into temp2 
  from M_PEMBEBANAN 
  where upper(namapembebanan) = upper(pnamapembebanan) and upper(kodepembebanan) not in upper(pkodepembebanan);
  
  if temp > 0 then
  	 if (pmode = 'insert') then
  	 	kodekembar := 'Kode Pembebanan '||pkodepembebanan||', telah ada sebelumnya.';	
	 end if; 
  end if;
  if temp2 > 0 then
  		kodekembar := 'Nama Pembebanan '||pnamapembebanan||', telah ada sebelumnya.';	 
  end if;
  
  varkembar := kodekembar;
  
end;
/


--
-- GENERATE_ANTRIAN  (Function) 
--
CREATE OR REPLACE FUNCTION TDS.generate_antrian
(jamAwal in number,jamAkhir in number) 
return number
as
    antrian number;
begin
    antrian := 20;
    return antrian;
end;
/


--
-- CALC_PEMBULATANDURASISEWA  (Function) 
--
CREATE OR REPLACE FUNCTION TDS.calc_pembulatandurasisewa
(pjamawal in varchar2, pjamakhir in varchar2) 
return number
as 
 --totaltemp number;
 tgljamawal date;
 tgljamakhir date; 
 --menemukan_zona_jamawal boolean; 
 durasisewa number;
 pembulatandurasisewa number;
 menittoleransisewaitem number;
 fraksi number;
begin
 select to_number(parvalue) into menittoleransisewaitem from m_parameter where parname = 'menittoleransisewaitem';
 select to_number(parvalue) into fraksi from m_parameter where parname = 'intervalperhitunganbiayasewaitem';
 --menemukan_zona_jamawal:=false; 
 tgljamawal := to_date('1899-12-30 '||pjamawal,'yyyy-mm-dd hh24:mi');
 tgljamakhir := to_date('1899-12-30 '||pjamakhir,'yyyy-mm-dd hh24:mi'); 
 select round((tgljamakhir - tgljamawal) * (60 * 24),2) into durasisewa from dual;
 if (durasisewa-60)>0 then
  durasisewa := durasisewa - 60;
  pembulatandurasisewa := 60;
  while durasisewa>=60 loop   
   durasisewa := durasisewa - 60;
   pembulatandurasisewa := pembulatandurasisewa+60;     
  end loop;
  if durasisewa > menittoleransisewaitem then
   pembulatandurasisewa := pembulatandurasisewa+60; 
  end if;
 else
  if durasisewa <= menittoleransisewaitem then
   pembulatandurasisewa := 0;
  else
   pembulatandurasisewa := 60;
  end if;
 end if;
 
 
    return pembulatandurasisewa;
end;
/


--
-- VM_HRIS_JABATANKARYAWAN  (View) 
--
CREATE OR REPLACE FORCE VIEW TDS.VM_HRIS_JABATANKARYAWAN
(NIK, KODEPERUSAHAAN, KODEJABATAN, TGLAWALMENJABAT, TGLAKHIRMENJABAT, 
 JABATANUTAMA, PJS, KODEESELON, KODEGRADE, FIELDBANTUAN)
AS 
select "NIK","KODEPERUSAHAAN","KODEJABATAN","TGLAWALMENJABAT","TGLAKHIRMENJABAT","JABATANUTAMA","PJS","KODEESELON","KODEGRADE","FIELDBANTUAN" from hris.m_jabatankaryawan;


--
-- M_MENU_ORDERBYKODEFORM  (View) 
--
CREATE OR REPLACE FORCE VIEW TDS.M_MENU_ORDERBYKODEFORM
(KODEFORM, NAMAFORM, KODEPARENT, URL)
AS 
select "KODEFORM","NAMAFORM","KODEPARENT","URL" from m_menu order by kodeform;


--
-- VM_HRIS_BPARTNERLENGKAP  (View) 
--
CREATE OR REPLACE FORCE VIEW TDS.VM_HRIS_BPARTNERLENGKAP
(KODEBP, NIK, NAMABP, KODEPERUSAHAAN, NAMAPERUSAHAAN, 
 KODEORG, NAMAORG, KODEJABATAN, NAMAJABATAN, KODECOSTCENTER, 
 USERID, BPKARYAWAN, BPCUSTOMER)
AS 
select p."KODEBP",p."NIK",p."NAMABP",p."KODEPERUSAHAAN",p."NAMAPERUSAHAAN",p."KODEORG",p."NAMAORG",p."KODEJABATAN",p."NAMAJABATAN", q.kodecostcenter, r.userid, p.bpkaryawan,p.bpcustomer
from
(
select x.kodebp, x.nik, x.namabp, y.kodeperusahaan, o.namaperusahaan, z.kodeorg, z.namaorg, y.kodejabatan, z.namajabatan, x.bpkaryawan,x.bpcustomer
from 
(
 select 
  a.kodebp, b.nik, a.namabp, a.bpkaryawan,a.bpcustomer
 from 
  hris.vm_bpartnerlengkap a,
  hris.vm_karyawan b
 where
  a.kodebp = b.kodebp(+)
 
  MINUS
 
 select 
  a.kodebp, b.nik, a.namabp, a.bpkaryawan,a.bpcustomer
 from 
  hris.vm_bpartnerlengkap a,
  hris.vm_karyawan b, hris.h_terminasi c
 where
  a.kodebp = b.kodebp(+)
  and c.nik = b.nik
)x,
  hris.m_jabatankaryawan y, hris.vm_jabatan z, hris.vm_perusahaan o
where x.nik = y.nik(+)
and y.kodejabatan = z.kodejabatan(+)
--and y.kodejabatan = o.kodeperusahaan(+)
and y.kodeperusahaan = o.kodeperusahaan(+)
) p, hris.m_organisasi q, tds.m_user r
where p.kodeorg=q.kodeorg(+)
and p.kodebp=r.kodebp(+)
order by kodebp, nik;


--
-- VM_HRIS_COSTCENTER  (View) 
--
CREATE OR REPLACE FORCE VIEW TDS.VM_HRIS_COSTCENTER
(KODECOSTCENTER, NAMACOSTCENTER, COSTCENTER)
AS 
select "KODECOSTCENTER","NAMACOSTCENTER","COSTCENTER" 
from 
  hris.vm_costcenter;


--
-- VM_HRIS_KARYAWAN  (View) 
--
CREATE OR REPLACE FORCE VIEW TDS.VM_HRIS_KARYAWAN
(NIK, NAMABP, KODEBP, KODEPERUSAHAAN, KODELOKASI, 
 NAMALOKASI, KODEORG, NAMAORG, KODEJABATAN, NAMAJABATAN, 
 NAMANIK)
AS 
select
  NIK, NAMABP, KODEBP, KODEPERUSAHAAN, KODELOKASI, NAMALOKASI, KODEORG, NAMAORG, 
  KODEJABATAN,NAMAJABATAN, NIK||' - '||NAMABP namanik
from 
  hris.vm_karyawanbelumterminasi;


--
-- VM_FORM_NEEDACC  (View) 
--
CREATE OR REPLACE FORCE VIEW TDS.VM_FORM_NEEDACC
(KODEFORM, NAMAFORM, ACCFORM, EDITFINALAPPROVE)
AS 
SELECT a.kodeform, b.namaform, a.accform, a.editfinalapprove 
FROM M_SETTINGACC a, M_MENU b 
WHERE a.kodeform = b.kodeform(+) 
and a.accform = '1';


--
-- VM_HARILIBUR  (View) 
--
CREATE OR REPLACE FORCE VIEW TDS.VM_HARILIBUR
(KETERANGAN, TANGGAL, KODELOKASI)
AS 
select "KETERANGAN","TANGGAL","KODELOKASI" from hris.m_harilibur;


--
-- M_HISTORYFORM  (Table) 
--
CREATE TABLE TDS.M_HISTORYFORM
(
  KODEFORM     VARCHAR2(200 BYTE),
  TABEL        VARCHAR2(50 BYTE),
  PK1          VARCHAR2(30 BYTE),
  PK1VALUE     VARCHAR2(100 BYTE),
  PK2          VARCHAR2(30 BYTE),
  PK2VALUE     VARCHAR2(100 BYTE),
  PK3          VARCHAR2(30 BYTE),
  PK3VALUE     VARCHAR2(100 BYTE),
  KETERANGAN   VARCHAR2(255 BYTE),
  CREATEDBY    VARCHAR2(20 BYTE),
  CREATEDDATE  DATE,
  UPDATEDBY    VARCHAR2(20 BYTE),
  UPDATEDDATE  DATE,
  DELETEDBY    VARCHAR2(20 BYTE),
  DELETEDDATE  DATE
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOCOMPRESS ;


--
-- D_COSTCENTERPENGAJUAN  (Table) 
--
CREATE TABLE TDS.D_COSTCENTERPENGAJUAN
(
  KODEPENGAJUAN   VARCHAR2(20 BYTE),
  KODECOSTCENTER  VARCHAR2(20 BYTE),
  USERID          VARCHAR2(50 BYTE),
  APPROVAL        VARCHAR2(1 BYTE),
  PRESENTASE      NUMBER,
  NOMINAL         NUMBER
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOCOMPRESS ;


--
-- D_COSTCENTERREALISASI  (Table) 
--
CREATE TABLE TDS.D_COSTCENTERREALISASI
(
  KODEREALISASI   VARCHAR2(20 BYTE),
  KODECOSTCENTER  VARCHAR2(20 BYTE),
  USERID          VARCHAR2(50 BYTE),
  APPROVAL        VARCHAR2(1 BYTE),
  PRESENTASE      NUMBER,
  NOMINAL         NUMBER
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOCOMPRESS ;


--
-- MD_COSTCENTERUSER  (Table) 
--
CREATE TABLE TDS.MD_COSTCENTERUSER
(
  KODECOSTCENTER  VARCHAR2(20 BYTE),
  USERID          VARCHAR2(50 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOCOMPRESS ;


--
-- MD_KONFIGURASIACC  (Table) 
--
CREATE TABLE TDS.MD_KONFIGURASIACC
(
  KODEACC      VARCHAR2(20 BYTE)                NOT NULL,
  USERID       VARCHAR2(50 BYTE),
  KODEJABATAN  VARCHAR2(20 BYTE),
  DEFAULTACC   VARCHAR2(1 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOCOMPRESS ;


--
-- MD_PEMBEBANAN  (Table) 
--
CREATE TABLE TDS.MD_PEMBEBANAN
(
  KODEPEMBEBANAN  VARCHAR2(20 BYTE)             NOT NULL,
  KODECOSTCENTER  VARCHAR2(20 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOCOMPRESS ;


--
-- M_ACC  (Table) 
--
CREATE TABLE TDS.M_ACC
(
  USERID         VARCHAR2(50 BYTE),
  KODEFORM       VARCHAR2(20 BYTE),
  KODETRANSAKSI  VARCHAR2(20 BYTE),
  TGLACC         DATE,
  PRIORITAS      NUMBER,
  KODEJABATAN    VARCHAR2(20 BYTE),
  STATUSACC      INTEGER                        DEFAULT 0
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOCOMPRESS ;


--
-- M_ITEM  (Table) 
--
CREATE TABLE TDS.M_ITEM
(
  KODEITEM            VARCHAR2(20 BYTE)         NOT NULL,
  KODEJENIS           VARCHAR2(20 BYTE),
  NAMAITEM            VARCHAR2(50 BYTE),
  DURASIMINSEWA       NUMBER,
  KAPASITAS           NUMBER,
  KETERANGAN          VARCHAR2(255 BYTE),
  AKTIF               VARCHAR2(1 BYTE),
  STATUSAVAILABILITY  VARCHAR2(1 BYTE),
  PRICECHANGEABLE     VARCHAR2(1 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOCOMPRESS ;


--
-- M_PERMISSION  (Table) 
--
CREATE TABLE TDS.M_PERMISSION
(
  KODEFORM     VARCHAR2(20 BYTE)                NOT NULL,
  USERID       VARCHAR2(50 BYTE)                NOT NULL,
  PVIEW        VARCHAR2(1 BYTE)                 DEFAULT '0',
  PADD         VARCHAR2(1 BYTE)                 DEFAULT '0',
  PUPDATE      VARCHAR2(1 BYTE)                 DEFAULT '0',
  PDELETE      VARCHAR2(1 BYTE)                 DEFAULT '0',
  PPRINT       VARCHAR2(1 BYTE)                 DEFAULT '0',
  BATAL_TRANS  VARCHAR2(1 BYTE)                 DEFAULT '0'
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOCOMPRESS ;


--
-- SD_ITEMPENGAJUAN  (Table) 
--
CREATE TABLE TDS.SD_ITEMPENGAJUAN
(
  KODETP         VARCHAR2(25 BYTE),
  KODEITEM       VARCHAR2(20 BYTE),
  JUMLAHAWAL     NUMBER,
  JUMLAHAKHIR    NUMBER,
  JAMAWAL        DATE,
  JAMAKHIR       DATE,
  DURASI         NUMBER,
  BIAYA          NUMBER,
  ANTRIAN        NUMBER,
  KETERANGAN     VARCHAR2(255 BYTE),
  BIAYAPERJAM    NUMBER,
  JUMLAHPESERTA  NUMBER                         DEFAULT 0
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOCOMPRESS ;


--
-- SD_ITEMREALISASI  (Table) 
--
CREATE TABLE TDS.SD_ITEMREALISASI
(
  KODETR         VARCHAR2(25 BYTE),
  JUMLAHAKHIR    NUMBER,
  JAMAWAL        DATE,
  JAMAKHIR       DATE,
  DURASI         NUMBER,
  BIAYA          NUMBER,
  STATUSSEBELUM  VARCHAR2(30 BYTE),
  STATUSSESUDAH  VARCHAR2(30 BYTE),
  KETERANGAN     VARCHAR2(255 BYTE),
  KODEITEM       VARCHAR2(20 BYTE),
  BIAYAPERJAM    NUMBER,
  JUMLAHPESERTA  NUMBER                         DEFAULT 0
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOCOMPRESS ;


--
-- RELATIONSHIP_31_FK  (Index) 
--
CREATE INDEX TDS.RELATIONSHIP_31_FK ON TDS.M_PERMISSION
(USERID)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- RELATIONSHIP_32_FK  (Index) 
--
CREATE INDEX TDS.RELATIONSHIP_32_FK ON TDS.M_PERMISSION
(KODEFORM)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- PK_M_ITEM  (Index) 
--
CREATE UNIQUE INDEX TDS.PK_M_ITEM ON TDS.M_ITEM
(KODEITEM)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- RELATIONSHIP_37_FK  (Index) 
--
CREATE INDEX TDS.RELATIONSHIP_37_FK ON TDS.M_ITEM
(KODEJENIS)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- RELATIONSHIP_19_FK  (Index) 
--
CREATE INDEX TDS.RELATIONSHIP_19_FK ON TDS.M_ACC
(USERID)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- RELATIONSHIP_21_FK  (Index) 
--
CREATE INDEX TDS.RELATIONSHIP_21_FK ON TDS.M_ACC
(KODEFORM)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- RELATIONSHIP_41_FK  (Index) 
--
CREATE INDEX TDS.RELATIONSHIP_41_FK ON TDS.MD_PEMBEBANAN
(KODEPEMBEBANAN)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- RELATIONSHIP_42_FK  (Index) 
--
CREATE INDEX TDS.RELATIONSHIP_42_FK ON TDS.MD_PEMBEBANAN
(KODECOSTCENTER)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- RELATIONSHIP_15_FK  (Index) 
--
CREATE INDEX TDS.RELATIONSHIP_15_FK ON TDS.MD_KONFIGURASIACC
(KODEACC)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- RELATIONSHIP_30_FK  (Index) 
--
CREATE INDEX TDS.RELATIONSHIP_30_FK ON TDS.MD_KONFIGURASIACC
(USERID)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- RELATIONSHIP_35_FK  (Index) 
--
CREATE INDEX TDS.RELATIONSHIP_35_FK ON TDS.MD_COSTCENTERUSER
(USERID)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- RELATIONSHIP_36_FK  (Index) 
--
CREATE INDEX TDS.RELATIONSHIP_36_FK ON TDS.MD_COSTCENTERUSER
(KODECOSTCENTER)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- RELATIONSHIP_28_FK  (Index) 
--
CREATE INDEX TDS.RELATIONSHIP_28_FK ON TDS.D_COSTCENTERPENGAJUAN
(USERID)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- RELATIONSHIP_22_FK  (Index) 
--
CREATE INDEX TDS.RELATIONSHIP_22_FK ON TDS.D_COSTCENTERPENGAJUAN
(KODEPENGAJUAN)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- RELATIONSHIP_24_FK  (Index) 
--
CREATE INDEX TDS.RELATIONSHIP_24_FK ON TDS.D_COSTCENTERPENGAJUAN
(KODECOSTCENTER)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- RELATIONSHIP_27_FK  (Index) 
--
CREATE INDEX TDS.RELATIONSHIP_27_FK ON TDS.SD_ITEMREALISASI
(KODETR)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- RELATIONSHIP_43_FK  (Index) 
--
CREATE INDEX TDS.RELATIONSHIP_43_FK ON TDS.SD_ITEMPENGAJUAN
(KODETP)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- RELATIONSHIP_45_FK  (Index) 
--
CREATE INDEX TDS.RELATIONSHIP_45_FK ON TDS.SD_ITEMPENGAJUAN
(KODEITEM)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- CEK_SDITEM_PENGAJUAN  (Procedure) 
--
CREATE OR REPLACE PROCEDURE TDS.CEK_SDITEM_PENGAJUAN 
(hasil out varchar2, 
 pkodetp in Varchar2, 
 pkodeitem in Varchar2, 
 pjamawal in Varchar2, 
 pjamakhir in Varchar2
)
is
  temp number;
  hitung varchar2(255);
  varhasil varchar2(255);
  xtglawal date;
  xtglakhir date;
  xnamaitem varchar2(255);
  
  xtampawal date;
  xtampakhir date;
  xselisihtgl number;
begin
 
  --gen_jdwlsewaruang;

  hitung := 0;
  select count(*) into hitung
  from d_tglpengajuan a
  where
	a.kodetp = pkodetp;
  
  if (hitung > 0) then
	  select a.tglawal into xtglawal
	  from d_tglpengajuan a
	  where
		a.kodetp = pkodetp;
  
	  select a.tglakhir into xtglakhir
	  from d_tglpengajuan a
	  where
		a.kodetp = pkodetp;
	  
	  xselisihtgl := xtglakhir-xtglawal;
  	
	  --ini utk mencari apakah item tersebut sudah ada yg menyewa sebelumnya dengan jeda waktu 30 menit
	  temp := 0;
	  for j in 0..xselisihtgl
	  loop 	
		  select count(*) into temp
		  from jadwalsewaruang a
		  where pkodeitem = a.kodeitem
		  and (
		        (to_date((to_char(xtglawal+j,'YYYY-MM-DD')||' '||pjamawal),'YYYY-MM-DD HH24:MI') > to_date(a.start_date,'YYYY-MM-DD HH24:MI')-(1/24/2)
				  and to_date((to_char(xtglawal+j,'YYYY-MM-DD')||' '||pjamawal),'YYYY-MM-DD HH24:MI') < to_date(a.end_date,'YYYY-MM-DD HH24:MI')+(1/24/2)
			    ) OR
		        (to_date((to_char(xtglakhir+j,'YYYY-MM-DD')||' '||pjamakhir),'YYYY-MM-DD HH24:MI') > to_date(a.start_date,'YYYY-MM-DD HH24:MI')-(1/24/2)
		          and to_date((to_char(xtglakhir+j,'YYYY-MM-DD')||' '||pjamakhir),'YYYY-MM-DD HH24:MI') < to_date(a.end_date,'YYYY-MM-DD HH24:MI')+(1/24/2)
			    )
			  );
			  
		  /*select count(*) into temp
		  from jadwalsewaruang a
		  where pkodeitem = a.kodeitem
		  and (
		        (to_date((to_char(xtglawal+j,'YYYY-MM-DD')||' '||pjamawal),'YYYY-MM-DD HH24:MI') >= to_date(a.start_date,'YYYY-MM-DD HH24:MI')
				  and to_date((to_char(xtglawal+j,'YYYY-MM-DD')||' '||pjamawal),'YYYY-MM-DD HH24:MI') <= to_date(a.end_date,'YYYY-MM-DD HH24:MI')
			    ) OR
		        (to_date((to_char(xtglakhir+j,'YYYY-MM-DD')||' '||pjamakhir),'YYYY-MM-DD HH24:MI') >= to_date(a.start_date,'YYYY-MM-DD HH24:MI')
		          and to_date((to_char(xtglakhir+j,'YYYY-MM-DD')||' '||pjamakhir),'YYYY-MM-DD HH24:MI') <= to_date(a.end_date,'YYYY-MM-DD HH24:MI')
			    )
			  ); */
		  
		  /*select count(*) into temp
		  from jadwalsewaruang a
		  where pkodeitem = a.kodeitem
		  and (
			  	  (to_date((to_char(xtglakhir+j,'YYYY-MM-DD')||' '||pjamakhir),'YYYY-MM-DD HH24:MI') <= to_date(a.start_date,'YYYY-MM-DD HH24:MI'))
				  OR
				  (to_date((to_char(xtglawal+j,'YYYY-MM-DD')||' '||pjamawal),'YYYY-MM-DD HH24:MI') >= to_date(a.end_date,'YYYY-MM-DD HH24:MI')) 
			  );*/
		  
			  
		  if temp > 0 then
	  	 	select a.namaitem into xnamaitem
			from m_item a 
			where a.kodeitem = pkodeitem;
			
			
	  	 	  varhasil := 'Tidak bisa menyimpan data, '||xnamaitem||' tgl '||to_char(xtglawal+j,'dd Mon YYYY')||' jam '||pjamawal||' s/d '
			    ||pjamakhir||' telah ada sebelumnya.';
			exit;
	  	  end if;
		  
	      temp := 0;
	  end loop;
	  
	
	  
  end if;
  hasil := varhasil;
  
end;
/


--
-- CEK_DATAKEMBAR_MDPEMBEBANAN  (Procedure) 
--
CREATE OR REPLACE PROCEDURE TDS.CEK_DATAKEMBAR_MDPEMBEBANAN (varkembar out varchar2, pkodepembebanan in Varchar2, pkodecostcenter in Varchar2)
is
  temp number;
  --temp2 number;
  kodekembar varchar2(255);
begin
	
  select count(*) into temp
  from MD_PEMBEBANAN 
  where upper(kodepembebanan) = upper(pkodepembebanan) and upper(kodecostcenter) = upper(pkodecostcenter);
  
  if temp > 0 then
  	 	kodekembar := 'Kode Cost Center '||pkodecostcenter||', telah ada sebelumnya.';	
  end if;
  
  varkembar := kodekembar;
  
end;
/


--
-- CEK_DATAKEMBAR_MPERMISSION  (Procedure) 
--
CREATE OR REPLACE PROCEDURE TDS.CEK_DATAKEMBAR_MPERMISSION (varkembar out varchar2, puserid in Varchar2, pkodeform in Varchar2)
is
  temp number;
  --temp2 number;
  kodekembar varchar2(255);
begin
	
  select count(*) into temp
  from M_PERMISSION 
  where upper(kodeform) = upper(pkodeform) and upper(userid) = upper(puserid);
  
  if temp > 0 then
  	 	kodekembar := 'Kode Form '||pkodeform||', telah ada sebelumnya.';	
  end if;
  
  varkembar := kodekembar;
  
end;
/


--
-- GEN_JDWLSEWARUANG  (Procedure) 
--
CREATE OR REPLACE PROCEDURE TDS.gen_jdwlsewaruang
is
  selisihtgl number;
  rowcount number;
  koderealisasitemp varchar2(20);
begin
 for i in(
  SELECT distinct a.KODEPENGAJUAN, c.NAMAORG 
  FROM h_pengajuan a,
   (SELECT substr(kodepengajuan,0,14) kode,max(versi) versi from h_pengajuan group by  substr(kodepengajuan,0,14)) b, 
   vm_hris_bpartnerlengkap c
   WHERE substr(a.kodepengajuan,0,14) = b.kode AND a.versi = b.versi 
  AND a.kodebp=c.kodebp AND a.batal=0
 )
 loop   
  select count(*) into rowcount from h_realisasi where kodepengajuan = i.KODEPENGAJUAN;
  if rowcount > 0 then
   select KODEREALISASI into koderealisasitemp from h_realisasi where kodepengajuan = i.KODEPENGAJUAN;
   for j in (
    select * from d_tglrealisasi where koderealisasi = koderealisasitemp
   )
   loop
    for k in(
     select a.* from sd_itemrealisasi a, m_item b where b.STATUSAVAILABILITY = '1' and a.KODETR = j.KODETR and a.KODEITEM = b.KODEITEM
    )
    loop
     selisihtgl := j.TGLAKHIR - j.TGLAWAL;
     for l in 0..selisihtgl 
     loop
      insert into JADWALSEWARUANG (ID,START_DATE,END_DATE,TEXT,KODEITEM,READONLY)
      values ((select to_char(count(*)) from JADWALSEWARUANG),to_char(j.TGLAWAL+l,'yyyy-mm-dd')||' '||to_char(k.JAMAWAL,'hh24:mi'),
       to_char(j.TGLAWAL+l,'yyyy-mm-dd')||' '||to_char(k.JAMAKHIR,'hh24:mi'),
       i.NAMAORG,k.KODEITEM,'true');
     end loop;
    end loop;
   end loop;  
  else
   for j in (
    select * from d_tglpengajuan where kodepengajuan = i.kodepengajuan
   )
   loop
    for k in(
     select a.* from sd_itempengajuan a, m_item b where b.STATUSAVAILABILITY = '1' and a.KODETP = j.KODETP and a.KODEITEM = b.KODEITEM
    )
    loop
     selisihtgl := j.TGLAKHIR - j.TGLAWAL;
     for l in 0..selisihtgl 
     loop
      insert into JADWALSEWARUANG (ID,START_DATE,END_DATE,TEXT,KODEITEM,READONLY)
      values ((select to_char(count(*)) from JADWALSEWARUANG),to_char(j.TGLAWAL+l,'yyyy-mm-dd')||' '||to_char(k.JAMAWAL,'hh24:mi'),
       to_char(j.TGLAWAL+l,'yyyy-mm-dd')||' '||to_char(k.JAMAKHIR,'hh24:mi'),
       i.NAMAORG,k.KODEITEM,'true');
     end loop;
    end loop;
   end loop;
  end if;
  
 end loop;
  
end;
/


--
-- GEN_JDWLSEWARUANGPERUBAHAN  (Procedure) 
--
CREATE OR REPLACE PROCEDURE TDS.gen_jdwlsewaruangperubahan(pkodepengajuan in varchar2)
is
  selisihtgl number;
  rowcount number;
  koderealisasitemp varchar2(20);
begin
 for i in(
  select KODEPENGAJUAN,NAMAORG
  from (
      SELECT distinct a.KODEPENGAJUAN, c.NAMAORG 
      FROM h_pengajuan a,
       (SELECT substr(kodepengajuan,0,14) kode,max(versi) versi from h_pengajuan group by  substr(kodepengajuan,0,14)) b, 
       vm_hris_bpartnerlengkap c
       WHERE substr(a.kodepengajuan,0,14) = b.kode AND a.versi = b.versi 
      AND a.kodebp=c.kodebp AND a.batal=0
    ) d
  where substr(d.KODEPENGAJUAN,1,14) != substr(pkodepengajuan,1,14)
 )
 loop   
  select count(*) into rowcount from h_realisasi where kodepengajuan = i.KODEPENGAJUAN;
  if rowcount > 0 then
   select KODEREALISASI into koderealisasitemp from h_realisasi where kodepengajuan = i.KODEPENGAJUAN;
   for j in (
    select * from d_tglrealisasi where koderealisasi = koderealisasitemp
   )
   loop
    for k in(
     select a.* from sd_itemrealisasi a, m_item b where b.STATUSAVAILABILITY = '1' and a.KODETR = j.KODETR and a.KODEITEM = b.KODEITEM
    )
    loop
     selisihtgl := j.TGLAKHIR - j.TGLAWAL;
     for l in 0..selisihtgl 
     loop
      insert into JADWALSEWARUANG (ID,START_DATE,END_DATE,TEXT,KODEITEM,READONLY)
      values ((select to_char(count(*)) from JADWALSEWARUANG),to_char(j.TGLAWAL+l,'yyyy-mm-dd')||' '||to_char(k.JAMAWAL,'hh24:mi'),
       to_char(j.TGLAWAL+l,'yyyy-mm-dd')||' '||to_char(k.JAMAKHIR,'hh24:mi'),
       i.NAMAORG,k.KODEITEM,'true');
     end loop;
    end loop;
   end loop;  
  else
   for j in (
    select * from d_tglpengajuan where kodepengajuan = i.kodepengajuan
   )
   loop
    for k in(
     select a.* from sd_itempengajuan a, m_item b where b.STATUSAVAILABILITY = '1' and a.KODETP = j.KODETP and a.KODEITEM = b.KODEITEM
    )
    loop
     selisihtgl := j.TGLAKHIR - j.TGLAWAL;
     for l in 0..selisihtgl 
     loop
      insert into JADWALSEWARUANG (ID,START_DATE,END_DATE,TEXT,KODEITEM,READONLY)
      values ((select to_char(count(*)) from JADWALSEWARUANG),to_char(j.TGLAWAL+l,'yyyy-mm-dd')||' '||to_char(k.JAMAWAL,'hh24:mi'),
       to_char(j.TGLAWAL+l,'yyyy-mm-dd')||' '||to_char(k.JAMAKHIR,'hh24:mi'),
       i.NAMAORG,k.KODEITEM,'true');
     end loop;
    end loop;
   end loop;
  end if;
  
 end loop;
  
end;
/


--
-- CEK_DATAKEMBAR_MITEM  (Procedure) 
--
CREATE OR REPLACE PROCEDURE TDS.CEK_DATAKEMBAR_MITEM (varkembar out varchar2, pkodeitem in Varchar2, pnamaitem in Varchar2, pmode in Varchar2)
is
  temp number;
  temp2 number;
  kodekembar varchar2(255);
begin
  select count(*) into temp 
  from M_ITEM
  where upper(kodeitem) = upper(pkodeitem);
	
  select count(*) into temp2 
  from M_ITEM
  where upper(namaitem) = upper(pnamaitem) and upper(kodeitem) not in upper(pkodeitem);
  
  if temp > 0 then
  	 if (pmode = 'insert') then
  	 	kodekembar := 'Kode item '||pkodeitem||', telah ada sebelumnya.';	
	 end if; 
  end if;
  if temp2 > 0 then
  		kodekembar := 'Nama item '||pnamaitem||', telah ada sebelumnya.';	 
  end if;
  
  varkembar := kodekembar;
  
end;
/


--
-- GETLISTMENU  (Function) 
--
CREATE OR REPLACE FUNCTION TDS.getListMenu (kodeMenuCurrent in Varchar2,levelMenu in number) return varchar2
is
  --fungsi ini mengembalikan list menu dalam bentuk tree dengan jumlah tanda # sebagai penanda kedalaman dari cabangnya
  jmlAnakMenu number;
  arrayKODEFORM varchar2(4000);
  kodeLvlOrgTujuanTemp varchar2(20);
  j number;
  levelMenuTemp number;
  looping_ke number;
  namaOrgTemp varchar2(100);
  levelMenutujuantemp varchar2(20);
  levelMenumintemp varchar2(20);
begin    
    select count(*) into jmlAnakMenu from m_menu_orderbykodeform where KODEPARENT=kodeMenuCurrent;
    
  --select nvl(namaorg,' ') into namaOrgTemp from m_menu where KODEFORM = kodeMenuCurrent;
  --arrayKODEFORM:=namaOrgTemp;
    arrayKODEFORM:=kodeMenuCurrent;
    levelMenuTemp:=levelMenu;
    if jmlAnakMenu > 0 then
      looping_ke := 0;
      for i in ( select KODEFORM from m_menu_orderbykodeform where KODEPARENT=kodeMenuCurrent) 
      loop
  looping_ke:=looping_ke+1;
        --select kodelevelMenuanisasi into kodeLvlOrgTujuanTemp  from m_menu where KODEFORM=i.KODEFORM;
        --if(kodeLvlOrgTujuanTemp=levelMenuMin) then
  -- arrayKODEFORM:=arrayKODEFORM||'|'||chr(9);
  -- j:=1;
  -- while j<=levelMenuTemp 
  -- loop
  --  arrayKODEFORM:=arrayKODEFORM||chr(9);
  --  j:=j+1;
  -- end loop; 
   
  -- arrayKODEFORM:=arrayKODEFORM||i.KODEFORM;
        --else
 
   
   --select levelMenu into levelMenutujuantemp from m_levelMenuanisasi where kodelevelMenuanisasi = kodeLvlOrgTujuanTemp;
   --select levelMenu into levelMenumintemp from m_levelMenuanisasi where kodelevelMenuanisasi = levelMenuMin;
   --if(levelMenutujuantemp>=levelMenumintemp) then
    --arrayKODEFORM:=arrayKODEFORM;
   --else
    if looping_ke > 1 then
     levelMenuTemp:=levelMenuTemp-1;
    end if;
    arrayKODEFORM:=arrayKODEFORM||'|'||'+';
    j:=1;
    while j<=levelMenuTemp 
    loop
     arrayKODEFORM:=arrayKODEFORM||'+';
     j:=j+1;
    end loop; 
    levelMenuTemp:=levelMenuTemp+1;
    arrayKODEFORM:=arrayKODEFORM||getListMenu(i.KODEFORM,levelMenuTemp);
   --end if;
   
  --end if;
      end loop; 
    else
      return (arrayKODEFORM);
    end if;

  return (arrayKODEFORM);
end;
/


--
-- SPLITSTRINGTOROWTABLE_WITHKRES  (Function) 
--
CREATE OR REPLACE FUNCTION TDS.splitStringToRowTable_withkres
(p_str   in varchar2,p_delim in varchar2 default '|') 
return MYTABLETYPE
as
    l_str     long default p_str || p_delim;
    l_n     number;
    l_data     MYTABLETYPE := MYTABLETYPE();
 	kodeTemp varchar2(20);
 	namaFormTemp varchar2(50);
 	linkTemp varchar2(500);
begin
    loop
        l_n := instr( l_str, p_delim );
        exit when (nvl(l_n,0) = 0);
        l_data.extend;
  		kodeTemp:=ltrim(rtrim(substr(l_str,1,l_n-1)));
  		kodeTemp:= REPLACE(kodeTemp,'+','');
  		select nvl(namaform,' '),url into namaFormTemp,linkTemp from m_menu_orderbykodeform where kodeform = kodeTemp;
  		if ((linkTemp is null)or(linkTemp='')) then
		   linkTemp:= 'null';
		end if;
        l_data( l_data.count ) := replace(ltrim(rtrim(substr(l_str,1,l_n-1))),kodeTemp,namaFormTemp)||':'||kodeTemp||':'||linkTemp;
        l_str := substr( l_str, l_n+length(p_delim) );
    end loop;
    return l_data;
end;
/


--
-- SPLITSTRTOROWTABLE_WITHKRES_2  (Function) 
--
CREATE OR REPLACE FUNCTION TDS.splitStrToRowTable_withkres_2
(p_str   in varchar2,p_delim in varchar2 default '|',puserid in varchar2) 
return MYTABLETYPE
as
    l_str     long default p_str || p_delim;
    l_n     number;
    l_data     MYTABLETYPE := MYTABLETYPE();
  kodeTemp varchar2(20);
  namaFormTemp varchar2(50);
  linkTemp varchar2(500);
 rowcount number;
begin
    loop
        l_n := instr( l_str, p_delim );
        exit when (nvl(l_n,0) = 0);
        
    kodeTemp:=ltrim(rtrim(substr(l_str,1,l_n-1)));
    kodeTemp:= REPLACE(kodeTemp,'+','');
    select nvl(namaform,' '),url into namaFormTemp,linkTemp from m_menu_orderbykodeform where kodeform = kodeTemp;  
  rowcount:=1;  
    if ((linkTemp is null)or(linkTemp='')) then
     linkTemp:= 'null';
  else
   select count(*) into rowcount from m_menu where 
    kodeform = kodeTemp and kodeform in (select kodeform from m_permission where userid = puserid);
  end if;
  if (rowcount>0) then
   l_data.extend;
   l_data( l_data.count ) := replace(ltrim(rtrim(substr(l_str,1,l_n-1))),kodeTemp,namaFormTemp)||':'||kodeTemp||':'||linkTemp;
  end if;
        l_str := substr( l_str, l_n+length(p_delim) );
    end loop;
    return l_data;
end;
/


--
-- VM_MUSER_KONFIGURASIACC  (View) 
--
CREATE OR REPLACE FORCE VIEW TDS.VM_MUSER_KONFIGURASIACC
(USERID, PASS, KODEFORM, PRIORITAS, KODEJABATAN, 
 NAMAJABATAN, NAMABP)
AS 
select e.userid,a.pass, d.kodeform, d.prioritas,e.kodejabatan,b.namajabatan, c.namabp
from m_user a, VM_HRIS_BPARTNERLENGKAP c,MH_KONFIGURASIACC d, md_konfigurasiacc e
left join hris.m_jabatan b on e.kodejabatan = b.kodejabatan
where
 d.kodeacc = e.kodeacc  
 and e.userid = c.userid
 and e.userid = a.userid;


--
-- MD_TARIFITEM  (Table) 
--
CREATE TABLE TDS.MD_TARIFITEM
(
  IE         VARCHAR2(1 BYTE),
  KODEITEM   VARCHAR2(20 BYTE)                  NOT NULL,
  NAMATARIF  VARCHAR2(50 BYTE),
  JAMAWAL    DATE,
  JAMAKHIR   DATE,
  BIAYA      NUMBER
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOCOMPRESS ;


--
-- RELATIONSHIP_38_FK  (Index) 
--
CREATE INDEX TDS.RELATIONSHIP_38_FK ON TDS.MD_TARIFITEM
(KODEITEM)
TABLESPACE USERS
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           );


--
-- GENERATE_DETILJAMSEWAITEM  (Procedure) 
--
CREATE OR REPLACE PROCEDURE TDS.generate_detilJamSewaItem
(pkodebp in varchar2,pkodeitem in varchar2, pjamawal in varchar2, pjamakhir in varchar2,pnamatarif in varchar2,pjumlahawal in number,pjumlahakhir in number,pIE in varchar2) 
as
 totalbiaya number;
 tarif number;
 tgljamawal date;
 tgljamakhir date;  
 current_jamawalitem_md_tarif date;
 before_jamawalitem_md_tarif date; 
 jamakhir_inserted date; 
 durasisewa number;
 pembulatandurasisewa number;
 menittoleransisewaitem number;
 fraksi number;
 n number;
 j number;
 id_temp number;
 rowbaru boolean;
begin
 totalbiaya:= 0;
 select to_number(parvalue) into menittoleransisewaitem from m_parameter where parname = 'menittoleransisewaitem';
 select to_number(parvalue) into fraksi from m_parameter where parname = 'intervalperhitunganbiayasewaitem';
 --menemukan_zona_jamawal:=false; 
 tgljamawal := to_date('1899-12-30 '||pjamawal,'yyyy-mm-dd hh24:mi');
 tgljamakhir := to_date('1899-12-30 '||pjamakhir,'yyyy-mm-dd hh24:mi'); 
 select round((tgljamakhir - tgljamawal) * (60 * 24),2) into durasisewa from dual;
 if (durasisewa-60)>0 then
  durasisewa := durasisewa - 60;
  pembulatandurasisewa := 60;
  while durasisewa>=60 loop   
     durasisewa := durasisewa - 60;
     pembulatandurasisewa := pembulatandurasisewa+60;     
  end loop;
  if durasisewa > menittoleransisewaitem then
     pembulatandurasisewa := pembulatandurasisewa+60; 
  end if;
 else
  if durasisewa <= menittoleransisewaitem then
     pembulatandurasisewa := 0;
  else
     pembulatandurasisewa := 60;
  end if;
 end if; 
 n:=pembulatandurasisewa/fraksi;
 before_jamawalitem_md_tarif:=null;
 FOR i IN 1..n LOOP 
  -- +/- 1/(24 * 60) -- min
  -- +/- 1/(24 * 60 * 60) -- sec   
  if i > 1 then
   tgljamawal := tgljamawal+ ((1/(24 * 60))*(fraksi));
  end if;  
  dbms_output.put_line(to_char(tgljamawal,'yyyy-mm-dd hh24:mi') );
  select biaya,jamawal into tarif,current_jamawalitem_md_tarif
  from md_tarifitem 
  where kodeitem = pkodeitem
     and IE = pIE
     and namatarif = pnamatarif
     and (jamawal <= tgljamawal) and (tgljamawal <= jamakhir);  
  
  if (before_jamawalitem_md_tarif != current_jamawalitem_md_tarif)or(before_jamawalitem_md_tarif is null) then
   rowbaru := true;
   totalbiaya:=tarif;
   j:=1;
  else
   j:=j+1;
   totalbiaya:=totalbiaya+tarif;
   rowbaru := false;
  end if;
  select count(*) into id_temp from TEMP_SD_ITEMPENGAJUAN;
  if rowbaru then
   if i = n then
    jamakhir_inserted := tgljamakhir;
   else
    jamakhir_inserted := tgljamawal+((1/(24 * 60))*(fraksi));
   end if;
   
   insert into TEMP_SD_ITEMPENGAJUAN (ID,KODETP,KODEITEM,JUMLAHAWAL,JUMLAHAKHIR,JAMAWAL,JAMAKHIR,
    DURASI,BIAYA,ANTRIAN,KETERANGAN,BIAYAPERJAM)
   values(to_char(id_temp+1),'',pkodeitem,pjumlahawal,pjumlahakhir,tgljamawal,jamakhir_inserted-(1/(24 * 60)),
    j,totalbiaya*pjumlahakhir,0,'',tarif);
  else
   if i = n then
    jamakhir_inserted := tgljamakhir;
   else
    jamakhir_inserted := tgljamawal+((1/(24 * 60))*(fraksi));
   end if;
   
   update TEMP_SD_ITEMPENGAJUAN
   set JAMAKHIR = jamakhir_inserted-(1/(24 * 60)), DURASI = j, BIAYA = totalbiaya*pjumlahakhir,BIAYAPERJAM=tarif
   where to_char(ID) = to_char(id_temp);
  end if;
  before_jamawalitem_md_tarif:=current_jamawalitem_md_tarif;
 END LOOP;     
end;
/


--
-- TOTALBIAYASEWAITEM  (Function) 
--
CREATE OR REPLACE FUNCTION TDS.totalbiayasewaitem
(pkodebp in varchar2,pkodeitem in varchar2, pjamawal in varchar2, pjamakhir in varchar2,pnamatarif in varchar2) 
return number
as
    totalbiaya number;
 tarif number;
 --totaltemp number;
 tgljamawal date;
 tgljamakhir date; 
 --menemukan_zona_jamawal boolean; 
 durasisewa number;
 pembulatandurasisewa number;
 menittoleransisewaitem number;
 fraksi number;
begin
 totalbiaya:= 0;
 select to_number(parvalue) into menittoleransisewaitem from m_parameter where parname = 'menittoleransisewaitem';
 select to_number(parvalue) into fraksi from m_parameter where parname = 'intervalperhitunganbiayasewaitem';
 --menemukan_zona_jamawal:=false; 
 tgljamawal := to_date('1899-12-30 '||pjamawal,'yyyy-mm-dd hh24:mi');
 tgljamakhir := to_date('1899-12-30 '||pjamakhir,'yyyy-mm-dd hh24:mi'); 
 select round((tgljamakhir - tgljamawal) * (60 * 24),2) into durasisewa from dual;
 if (durasisewa-60)>0 then
  durasisewa := durasisewa - 60;
  pembulatandurasisewa := 60;
  while durasisewa>=60 loop   
   durasisewa := durasisewa - 60;
   pembulatandurasisewa := pembulatandurasisewa+60;     
  end loop;
  if durasisewa > menittoleransisewaitem then
   pembulatandurasisewa := pembulatandurasisewa+60; 
  end if;
 else
  if durasisewa <= menittoleransisewaitem then
   pembulatandurasisewa := 0;
  else
   pembulatandurasisewa := 60;
  end if;
 end if;
 
 FOR i IN 1..(pembulatandurasisewa/fraksi) LOOP 
  -- +/- 1/(24 * 60) -- min
  -- +/- 1/(24 * 60 * 60) -- sec 
  tgljamawal := tgljamawal+ ((1/(24 * 60))*(fraksi*(i-1)));
  select biaya into tarif
  from md_tarifitem 
  where kodeitem = pkodeitem
   and IE = (select distinct (case when bpkaryawan = '1' then 'I'
                     else 'E' end) IE
      from vm_hris_bpartnerlengkap
      where kodebp = pkodebp )
   and namatarif = pnamatarif
   and (jamawal <= tgljamawal) and (tgljamawal <= jamakhir);
  totalbiaya:=totalbiaya+tarif;
 END LOOP; 
 
 
 /*
 for  i in 
 (
  select * from md_tarifitem 
  where kodeitem = pkodeitem
   and IE = (select distinct (case when bpkaryawan = '1' then 'I'
                     else 'E' end) IE
      from vm_hris_bpartnerlengkap
      where kodebp = pkodebp )
   order by jamakhir asc
 )
 loop
  if not menemukan_zona_jamawal then
   if (i.jamawal <= tgljamawal) and (tgljamawal <= i.jamakhir) then
    if (tgljamakhir <= i.jamakhir) then
     select CEIL(AVG(CAST((round((tgljamakhir - tgljamawal) * (60 * 24),2))/60 as float))) into totaltemp from dual;
     totalbiaya := totalbiaya + (totaltemp*i.biaya);     
     exit;
    else
     select CEIL(AVG(CAST((round((i.jamakhir - tgljamawal) * (60 * 24),2))/60 as float))) into totaltemp from dual;
     totalbiaya := totalbiaya + (totaltemp*i.biaya);     
    end if;
    menemukan_zona_jamawal := true;
   end if;
  else
   if (i.jamawal <= tgljamakhir) and (tgljamakhir <= i.jamakhir) then
    select CEIL(AVG(CAST((round((tgljamakhir - i.jamawal ) * (60 * 24),2))/60 as float))) into totaltemp from dual;
    totalbiaya := totalbiaya + (totaltemp*i.biaya);    
    exit;
   else
    select CEIL(AVG(CAST((round((i.jamakhir - i.jamawal) * (60 * 24),2))/60 as float))) into totaltemp from dual;
    totalbiaya := totalbiaya + (totaltemp*i.biaya);    
   end if;
  end if;
 end loop;
 */
    return totalbiaya;
end;
/


--
-- GETBIAYAITEMPERJAM  (Function) 
--
CREATE OR REPLACE FUNCTION TDS.getBiayaItemPerjam
(pkodebp in varchar2,pkodeitem in varchar2, pjamawal in varchar2, pjamakhir in varchar2,pnamatarif in varchar2,pIE in varchar2) 
return number
as 

 tgljamawal date;
 tgljamakhir date; 
 tarif number;
begin
 tgljamawal := to_date('1899-12-30 '||pjamawal,'yyyy-mm-dd hh24:mi');
 tgljamakhir := to_date('1899-12-30 '||pjamakhir,'yyyy-mm-dd hh24:mi'); 
 select biaya into tarif
  from md_tarifitem 
  where kodeitem = pkodeitem
   and IE = pIE
   and namatarif = pnamatarif
   and (jamawal <= tgljamawal) and (tgljamawal <= jamakhir);
 
 
    return tarif;
end;
/


-- 
-- Non Foreign Key Constraints for Table H_REALISASI 
-- 
ALTER TABLE TDS.H_REALISASI ADD (
  CONSTRAINT PK_H_REALISASI
  PRIMARY KEY
  (KODEREALISASI)
  USING INDEX TDS.PK_H_REALISASI);


-- 
-- Non Foreign Key Constraints for Table D_TGLPENGAJUAN 
-- 
ALTER TABLE TDS.D_TGLPENGAJUAN ADD (
  CONSTRAINT PK_D_TGLPENGAJUAN
  PRIMARY KEY
  (KODETP)
  USING INDEX TDS.PK_D_TGLPENGAJUAN);


-- 
-- Non Foreign Key Constraints for Table H_PENGAJUAN 
-- 
ALTER TABLE TDS.H_PENGAJUAN ADD (
  PRIMARY KEY
  (KODEPENGAJUAN)
  USING INDEX
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));


-- 
-- Non Foreign Key Constraints for Table D_TGLREALISASI 
-- 
ALTER TABLE TDS.D_TGLREALISASI ADD (
  CONSTRAINT PK_D_TGLREALISASI
  PRIMARY KEY
  (KODETR)
  USING INDEX TDS.PK_D_TGLREALISASI);


-- 
-- Non Foreign Key Constraints for Table M_JENIS 
-- 
ALTER TABLE TDS.M_JENIS ADD (
  CONSTRAINT PK_M_JENIS
  PRIMARY KEY
  (KODEJENIS)
  USING INDEX TDS.PK_M_JENIS);


-- 
-- Non Foreign Key Constraints for Table M_MENU 
-- 
ALTER TABLE TDS.M_MENU ADD (
  CONSTRAINT PK_M_MENU
  PRIMARY KEY
  (KODEFORM)
  USING INDEX TDS.PK_M_MENU);


-- 
-- Non Foreign Key Constraints for Table M_PEMBEBANAN 
-- 
ALTER TABLE TDS.M_PEMBEBANAN ADD (
  CONSTRAINT PK_M_PEMBEBANAN
  PRIMARY KEY
  (KODEPEMBEBANAN)
  USING INDEX TDS.PK_M_PEMBEBANAN);


-- 
-- Non Foreign Key Constraints for Table M_USER 
-- 
ALTER TABLE TDS.M_USER ADD (
  CONSTRAINT PK_M_USER
  PRIMARY KEY
  (USERID)
  USING INDEX TDS.PK_M_USER);


-- 
-- Non Foreign Key Constraints for Table MH_KONFIGURASIACC 
-- 
ALTER TABLE TDS.MH_KONFIGURASIACC ADD (
  CONSTRAINT PK_MH_KONFIGURASIACC
  PRIMARY KEY
  (KODEACC)
  USING INDEX TDS.PK_MH_KONFIGURASIACC);


-- 
-- Non Foreign Key Constraints for Table M_ITEM 
-- 
ALTER TABLE TDS.M_ITEM ADD (
  CONSTRAINT PK_M_ITEM
  PRIMARY KEY
  (KODEITEM)
  USING INDEX TDS.PK_M_ITEM);


-- 
-- Foreign Key Constraints for Table D_TGLREALISASI 
-- 
ALTER TABLE TDS.D_TGLREALISASI ADD (
  CONSTRAINT FK_D_TGLREA_RELATIONS_H_REALIS 
  FOREIGN KEY (KODEREALISASI) 
  REFERENCES TDS.H_REALISASI (KODEREALISASI));


-- 
-- Foreign Key Constraints for Table M_MENU 
-- 
ALTER TABLE TDS.M_MENU ADD (
  CONSTRAINT FK_M_MENU 
  FOREIGN KEY (KODEPARENT) 
  REFERENCES TDS.M_MENU (KODEFORM));


-- 
-- Foreign Key Constraints for Table M_SETTINGACC 
-- 
ALTER TABLE TDS.M_SETTINGACC ADD (
  CONSTRAINT FK_M_SETTIN_RELATIONS_M_MENU 
  FOREIGN KEY (KODEFORM) 
  REFERENCES TDS.M_MENU (KODEFORM));


-- 
-- Foreign Key Constraints for Table MH_KONFIGURASIACC 
-- 
ALTER TABLE TDS.MH_KONFIGURASIACC ADD (
  CONSTRAINT FK_MH_KONFI_RELATIONS_M_MENU 
  FOREIGN KEY (KODEFORM) 
  REFERENCES TDS.M_MENU (KODEFORM));


-- 
-- Foreign Key Constraints for Table M_HISTORYFORM 
-- 
ALTER TABLE TDS.M_HISTORYFORM ADD (
  CONSTRAINT FK_M_HISTORY_RELATION_M_USER 
  FOREIGN KEY (CREATEDBY) 
  REFERENCES TDS.M_USER (USERID));

ALTER TABLE TDS.M_HISTORYFORM ADD (
  CONSTRAINT FK_M_HISTORY_RELATION_M_USER2 
  FOREIGN KEY (UPDATEDBY) 
  REFERENCES TDS.M_USER (USERID));

ALTER TABLE TDS.M_HISTORYFORM ADD (
  CONSTRAINT FK_M_HISTORY_RELATION_M_USER3 
  FOREIGN KEY (DELETEDBY) 
  REFERENCES TDS.M_USER (USERID));

ALTER TABLE TDS.M_HISTORYFORM ADD (
  CONSTRAINT FK_M_HISTORY_RELATION_M_MENU 
  FOREIGN KEY (KODEFORM) 
  REFERENCES TDS.M_MENU (KODEFORM));


-- 
-- Foreign Key Constraints for Table D_COSTCENTERPENGAJUAN 
-- 
ALTER TABLE TDS.D_COSTCENTERPENGAJUAN ADD (
  CONSTRAINT FK_D_COSTCE_RELATIONS_M_USER 
  FOREIGN KEY (USERID) 
  REFERENCES TDS.M_USER (USERID));


-- 
-- Foreign Key Constraints for Table D_COSTCENTERREALISASI 
-- 
ALTER TABLE TDS.D_COSTCENTERREALISASI ADD (
  CONSTRAINT FK_D_COSTRE_RELATIONS_M_USER 
  FOREIGN KEY (USERID) 
  REFERENCES TDS.M_USER (USERID));


-- 
-- Foreign Key Constraints for Table MD_COSTCENTERUSER 
-- 
ALTER TABLE TDS.MD_COSTCENTERUSER ADD (
  CONSTRAINT FK_MD_COSTC_RELATIONS_M_USER 
  FOREIGN KEY (USERID) 
  REFERENCES TDS.M_USER (USERID));


-- 
-- Foreign Key Constraints for Table MD_KONFIGURASIACC 
-- 
ALTER TABLE TDS.MD_KONFIGURASIACC ADD (
  CONSTRAINT FK_MD_KONFI_RELATIONS_MH_KONFI 
  FOREIGN KEY (KODEACC) 
  REFERENCES TDS.MH_KONFIGURASIACC (KODEACC));

ALTER TABLE TDS.MD_KONFIGURASIACC ADD (
  CONSTRAINT FK_MD_KONFI_RELATIONS_M_USER 
  FOREIGN KEY (USERID) 
  REFERENCES TDS.M_USER (USERID));


-- 
-- Foreign Key Constraints for Table MD_PEMBEBANAN 
-- 
ALTER TABLE TDS.MD_PEMBEBANAN ADD (
  CONSTRAINT FK_MD_PEMBE_RELATIONS_M_PEMBEB 
  FOREIGN KEY (KODEPEMBEBANAN) 
  REFERENCES TDS.M_PEMBEBANAN (KODEPEMBEBANAN));


-- 
-- Foreign Key Constraints for Table M_ACC 
-- 
ALTER TABLE TDS.M_ACC ADD (
  CONSTRAINT FK_M_ACC_RELATIONS_M_USER 
  FOREIGN KEY (USERID) 
  REFERENCES TDS.M_USER (USERID));

ALTER TABLE TDS.M_ACC ADD (
  CONSTRAINT FK_M_ACC_RELATIONS_M_MENU 
  FOREIGN KEY (KODEFORM) 
  REFERENCES TDS.M_MENU (KODEFORM));


-- 
-- Foreign Key Constraints for Table M_ITEM 
-- 
ALTER TABLE TDS.M_ITEM ADD (
  CONSTRAINT FK_M_ITEM_RELATIONS_M_JENIS 
  FOREIGN KEY (KODEJENIS) 
  REFERENCES TDS.M_JENIS (KODEJENIS));


-- 
-- Foreign Key Constraints for Table M_PERMISSION 
-- 
ALTER TABLE TDS.M_PERMISSION ADD (
  CONSTRAINT FK_M_PERMIS_RELATIONS_M_USER 
  FOREIGN KEY (USERID) 
  REFERENCES TDS.M_USER (USERID));

ALTER TABLE TDS.M_PERMISSION ADD (
  CONSTRAINT FK_M_PERMIS_RELATIONS_M_MENU 
  FOREIGN KEY (KODEFORM) 
  REFERENCES TDS.M_MENU (KODEFORM));


-- 
-- Foreign Key Constraints for Table SD_ITEMPENGAJUAN 
-- 
ALTER TABLE TDS.SD_ITEMPENGAJUAN ADD (
  CONSTRAINT FK_SD_ITEMP_RELATIONS_D_TGLPEN 
  FOREIGN KEY (KODETP) 
  REFERENCES TDS.D_TGLPENGAJUAN (KODETP));

ALTER TABLE TDS.SD_ITEMPENGAJUAN ADD (
  CONSTRAINT FK_SD_ITEMP_RELATIONS_M_ITEM 
  FOREIGN KEY (KODEITEM) 
  REFERENCES TDS.M_ITEM (KODEITEM));


-- 
-- Foreign Key Constraints for Table SD_ITEMREALISASI 
-- 
ALTER TABLE TDS.SD_ITEMREALISASI ADD (
  CONSTRAINT FK_SD_ITEMR_RELATIONS_D_TGLREA 
  FOREIGN KEY (KODETR) 
  REFERENCES TDS.D_TGLREALISASI (KODETR));

ALTER TABLE TDS.SD_ITEMREALISASI ADD (
  FOREIGN KEY (KODEITEM) 
  REFERENCES TDS.M_ITEM (KODEITEM));


-- 
-- Foreign Key Constraints for Table MD_TARIFITEM 
-- 
ALTER TABLE TDS.MD_TARIFITEM ADD (
  CONSTRAINT FK_MD_TARIF_RELATIONS_M_ITEM 
  FOREIGN KEY (KODEITEM) 
  REFERENCES TDS.M_ITEM (KODEITEM));