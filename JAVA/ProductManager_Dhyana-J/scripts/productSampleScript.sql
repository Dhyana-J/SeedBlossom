DROP TABLE PRODUCT;

CREATE TABLE PRODUCT(
    PRODUCT_ID VARCHAR(20) CONSTRAINT PK PRIMARY KEY,
    P_NAME VARCHAR(40) NOT NULL,
    PRICE NUMBER NOT NULL,
    DESCRIPTION VARCHAR(40),
    STOCK NUMBER NOT NULL
);



INSERT INTO PRODUCT VALUES('nb_ss7','�Ｚ��Ʈ��',1570000,'�ø���7',10);
INSERT INTO PRODUCT VALUES('nb_ama4','�ƺϿ���',1200000,'xcode4',20);
INSERT INTO PRODUCT VALUES('pc_ibm','ibmPC',750000,'window8',5);

COMMENT ON COLUMN PRODUCT.PRODUCT_ID IS '��ǰ���̵�';
COMMENT ON COLUMN PRODUCT.P_NAME IS '��ǰ��';
COMMENT ON COLUMN PRODUCT.PRICE IS '��ǰ����';
COMMENT ON COLUMN PRODUCT.DESCRIPTION IS '��ǰ������';
COMMENT ON COLUMN PRODUCT.STOCK IS '���';

COMMIT;

--SELECT * FROM PRODUCT;