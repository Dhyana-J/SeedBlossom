--����� �ǽ����� Ǯ��

--1. �迭 ������ ������ ī�װ� ���̺��� ������� �Ѵ�. 
CREATE TABLE TB_CATEGORY(
    NAME VARCHAR2(10),
    USE_YN CHAR(1) DEFAULT 'Y'
);

--2. ���� ������ ������ ���̺��� ������� �Ѵ�.
CREATE TABLE TB_CLASS_TYPE(
    NO VARCHAR2(5),
    NAME VARCHAR2(10)
);

--3. TB_CATEGORY ���̺��� NAME�÷��� PRIMARY KEY�� �����Ͻÿ�
ALTER TABLE TB_CATEGORY ADD CONSTRAINT NAME_PK PRIMARY KEY(NAME);

--4. TB_CLASS_TYPE ���̺��� NAMEĮ���� NULL ���� ���� �ʵ��� �Ӽ� ����
ALTER TABLE TB_CLASS_TYPE MODIFY NAME NOT NULL;

--5. �� ���̺��� Į�� ���� NO�� ���� ���� Ÿ���� �����ϸ鼭 ũ��� 10����,
--�÷����� NAME�� ���� ���������� ���� Ÿ���� �����ϸ鼭 ũ�� 20���� �����Ͻÿ�.
ALTER TABLE TB_CLASS_TYPE
MODIFY NO VARCHAR2(10)
MODIFY NAME VARCHAR2(20);

ALTER TABLE TB_CATEGORY
MODIFY NAME VARCHAR2(20);

--6. �� ���̺��� NO�÷��� NAME�÷��� �̸��� ���� TB_�� ������ ���̺� �̸��� �տ� ���� ���·� ����
ALTER TABLE TB_CATEGORY
RENAME COLUMN TB_CATEGORY_NAME TO CATEGORY_NAME;

ALTER TABLE TB_CLASS_TYPE
RENAME COLUMN NAME TO CLASS_TYPE_NAME;

--7. TB_CATEGORY ���̺�� TB_CLASS_TYPE ���̺��� PRIMARY KEY �̸��� ������ ���� �����Ͻÿ�
-- PK_+ Į���̸�
ALTER TABLE TB_CATEGORY
RENAME CONSTRAINT NAME_PK TO PK_CATEGORY_NAME;

--TB_CLASS_TYPE ���̺� PK �߰� ���س���. �߰�������.
ALTER TABLE TB_CLASS_TYPE
ADD CONSTRAINT PK_NO PRIMARY KEY(NO);

--8. ������ ���� INSERT���� �����Ѵ�.
INSERT INTO TB_CATEGORY VALUES('����','Y');
INSERT INTO TB_CATEGORY VALUES('�ڿ�����','Y');
INSERT INTO TB_CATEGORY VALUES('����','Y');
INSERT INTO TB_CATEGORY VALUES('��ü��','Y');
INSERT INTO TB_CATEGORY VALUES('�ι���ȸ','Y');
COMMIT;

--9. TB_DEPARTMENT�� CATEGORY�÷��� TB_CATEGORY ���̺��� CATEGORY_NAME �÷��� �θ����� �����ϵ���
--FOREIGN KEY �����Ͻÿ�. �� �� KEY �̸��� KFI_���̺��_�÷���.
ALTER TABLE TB_DEPARTMENT
ADD CONSTRAINT FK_DEPARTMENT_CATEGORY 
FOREIGN KEY(CATEGORY) REFERENCES TB_CATEGORY(CATEGORY_NAME);

