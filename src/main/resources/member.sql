CREATE TABLE MEMBER (
   USER_NO NUMBER(10) PRIMARY_KEY,
   EMAIL VARCHAR2(60) UNIQUE NOT NULL,
   PASSWORD VARCHAR2(65) NOT NULL,
   USER_NM VARCHAR2(40) NOT NULL,
   MOBILE VARCHAR2(11) NOT NULL,
   REG_DT DATE DEFAULT SYSDATE,
   MOD_DT DATE
);

CREATE SEQUENCE SEQ_MEMBER;