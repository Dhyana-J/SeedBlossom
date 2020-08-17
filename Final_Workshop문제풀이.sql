/*
�ǽ�����Ǯ��
*/

--1. �������� 25�� �̻��� å ��ȣ�� �������� ȭ�鿡 ����ϴ� SQL�� �ۼ�
SELECT BOOK_NO,
        BOOK_NM
FROM TB_BOOK 
WHERE LENGTH(BOOK_NM)>=25;


--2. �޴��� ��ȣ�� 019�� �����ϴ� �达 ���� ���� �۰��� �̸������� �������� ��, ���� ���� ǥ�õǴ�
--�۰� �̸��� �繫�� ��ȭ��ȣ, ����ȭ��ȣ, �޴�����ȭ��ȣ�� ǥ���ϴ� SQL���� �ۼ�
SELECT * 
FROM (SELECT WRITER_NM, OFFICE_TELNO, HOME_TELNO, MOBILE_NO
        FROM TB_WRITER
        WHERE MOBILE_NO LIKE('019%') AND
            WRITER_NM LIKE('��%')
        ORDER BY WRITER_NM)
WHERE ROWNUM = 1;

--3. ���� ���°� "�ű�"�� �ش��ϴ� �۰����� �� �� ������ ����ϴ� SQL���� �ۼ��Ͻÿ�.
--(��� ����� "�۰�(��)"���� ǥ�õǵ��� �� ��
SELECT COUNT(COMPOSE_TYPE) AS "�۰�(��)"
FROM TB_BOOK_AUTHOR
WHERE COMPOSE_TYPE = '�ű�';

--4. 300������ �̻� ��ϵ� ������ ���� ���� �� ��ϵ� ���� ������ ǥ���ϴ� SQL������ �ۼ��Ͻÿ�.
--(���� ���°� ��ϵ��� ���� ���� ������ ��)
SELECT BOOK_NM,COMPOSE_TYPE, STOCK_QTY
FROM TB_BOOK_AUTHOR
JOIN TB_BOOK USING(BOOK_NO)
WHERE COMPOSE_TYPE IS NOT NULL AND
    PAGE>=300;

--5. ���� �ֱٿ� �߰��� �ֽ��� �̸��� ��������, ���ǻ� �̸��� ǥ���ϴ� SQL���� �ۼ�
SELECT * 
FROM(SELECT BOOK_NM, ISSUE_DATE, PUBLISHER_NM
    FROM TB_BOOK
    ORDER BY ISSUE_DATE DESC)
WHERE ROWNUM = 1;

--6. ���� ���� å�� �� �۰� 3���� �̸��� ������ ǥ���ϵ�, ���� �� ������� ǥ���ϴ� SQL���� �ۼ�
--��, �������� �۰��� ���ٰ� ����. (��� ����� �۰� �̸�, �� ���� ǥ�õǵ��� ����)
SELECT "�۰� �̸�", "�� ��"
FROM
(SELECT WRITER_NM "�۰� �̸�",
        COUNT(WRITER_NO) "�� ��",
        DENSE_RANK() OVER(ORDER BY COUNT(WRITER_NO) DESC) AS "����"
FROM TB_WRITER
JOIN TB_BOOK_AUTHOR USING(WRITER_NO)
GROUP BY WRITER_NM,WRITER_NO
ORDER BY COUNT(WRITER_NO) DESC)
WHERE ���� <=3;

--ROWNUM���
SELECT *
FROM
    (SELECT WRITER_NM "�۰� �̸�",
        COUNT(WRITER_NO) "�� ��"
        FROM TB_WRITER
        JOIN TB_BOOK_AUTHOR USING(WRITER_NO)
        GROUP BY WRITER_NM,WRITER_NO
        ORDER BY COUNT(WRITER_NO) DESC)
WHERE ROWNUM IN (1,2,3);

--7. �۰� ���� ���̺��� ��� ������� �׸��� �����Ǿ� �ִ� ���� �߰��ߴ�. ������ ������� ����
--�� �۰��� ���� ���ǵ����� �����ϰ� ������ ��¥�� ������Ѷ�. COMMITó���ض�.
--�۰��� ���� ���ǵ��� ������
-- �� ���̺��� WRITER_NO�� �� ���̺��� WRITER_NO�� ���� �Ϳ� ���� ISSUE_DATE�����Ű��
-- �׷캰 ��ũ�Űܾ��ҵ�

--���� ���ǵ��� ������ ���̺�ȭ
CREATE TABLE TB_REGIST_DATE AS
WITH RESULT AS (SELECT WRITER_NM,
        ISSUE_DATE,
        DENSE_RANK() 
            OVER(PARTITION BY WRITER_NM ORDER BY WRITER_NM,ISSUE_DATE) AS RANK
FROM TB_BOOK
JOIN TB_BOOK_AUTHOR USING(BOOK_NO)
JOIN TB_WRITER USING(WRITER_NO)
GROUP BY WRITER_NM,ISSUE_DATE
ORDER BY WRITER_NM,ISSUE_DATE ASC)
SELECT WRITER_NM, ISSUE_DATE
FROM RESULT
WHERE RANK = 1;

--������� �׸� �� �۰��� ���� ���ǵ��� �������� ���ս�Ű��.
MERGE INTO TB_WRITER USING TB_REGIST_DATE ON(TB_WRITER.WRITER_NM=TB_REGIST_DATE.WRITER_NM)
WHEN MATCHED THEN
UPDATE SET TB_WRITER.REGIST_DATE = TB_REGIST_DATE.ISSUE_DATE;


--8. TB_BOOK_TRANSLATOR ���̺� ����
CREATE TABLE TB_BOOK_TRANSLATOR(
    BOOK_NO VARCHAR2(10) NOT NULL,
    WRITER_NO VARCHAR2(10) NOT NULL,
    TRANS_LANG VARCHAR2(60),
    CONSTRAINT FK_BOOK_TRANSLATOR_01 FOREIGN KEY(BOOK_NO) REFERENCES TB_BOOK,
    CONSTRAINT FK_BOOK_TRANSLATOR_02 FOREIGN KEY(WRITER_NO) REFERENCES TB_WRITER,
    CONSTRAINT PK_BOOK_TRANSLATOR PRIMARY KEY(BOOK_NO,WRITER_NO)
);

COMMENT ON COLUMN TB_BOOK_TRANSLATOR.BOOK_NO IS '���� ��ȣ';
COMMENT ON COLUMN TB_BOOK_TRANSLATOR.WRITER_NO IS '�۰� ��ȣ';
COMMENT ON COLUMN TB_BOOK_TRANSLATOR.TRANS_LANG IS '���� ���';

INSERT INTO TB_BOOK_TRANSLATOR VALUES(1991060502,2,'ENG');

--9. ���� ���� ����(COMPOSE_TYPE)�� '�ű�','����','��','����'�� �ش��ϴ� �����ʹ�
--���� ���� ���� ���̺��� ���� ���� ���� ���̺�(TB_BOOK_TRANSLATOR)�� �ű�� SQL�ۼ�
--��, TRANS_LANG �÷��� NULL ���·� �д�. �̵��� �����ʹ� ���̻� TB_BOOK_AUTHOR�� �ȳ���������.

CREATE TABLE DATACOPY AS
(SELECT BOOK_NO,WRITER_NO
FROM TB_BOOK_AUTHOR
WHERE COMPOSE_TYPE IN('�ű�','����','��','����'));

MERGE INTO TB_BOOK_TRANSLATOR 
USING DATACOPY
ON(TB_BOOK_TRANSLATOR.BOOK_NO=DATACOPY.BOOK_NO)
WHEN NOT MATCHED THEN
INSERT VALUES(
    DATACOPY.BOOK_NO,
    DATACOPY.WRITER_NO,
    NULL);

DROP TABLE DATACOPY;
DELETE FROM TB_BOOK_AUTHOR
WHERE COMPOSE_TYPE IN('�ű�','����','��','����');

COMMIT;

--10.2007�⵵�� ���ǵ� ������ �̸��� ���ڸ� ǥ���ϴ� SQL���� �ۼ�
SELECT BOOK_NM, WRITER_NM
FROM TB_BOOK_TRANSLATOR
JOIN TB_BOOK USING(BOOK_NO)
JOIN TB_WRITER USING(WRITER_NO)
WHERE ISSUE_DATE LIKE('07/%');

--11. 10�� ����� Ȱ���� ��� ���������� �������� ������ �� ������ �ϴ� �並 �����ϴ� SQL���� �ۼ�
--�� �̸��� VW_BOOK_TRANSLATOR�� �ϰ� ������, ������, �������� ǥ�õǵ��� ����.
CREATE OR REPLACE VIEW VW_BOOK_TRANSLATOR
AS SELECT BOOK_NO, WRITER_NM, ISSUE_DATE
FROM TB_BOOK_TRANSLATOR
JOIN TB_BOOK USING(BOOK_NO)
JOIN TB_WRITER USING(WRITER_NO)
WHERE ISSUE_DATE IS NOT NULL WITH CHECK OPTION;

--12. ���ο� ���ǻ�� �ŷ� ��� ����.���� �ۼ�
INSERT INTO TB_PUBLISHER VALUES('�� ���ǻ�','02-6710-3737',DEFAULT);

--13.�������� �۰��� �̸��� ã������ �Ѵ�. �̸��� �������� ���ڸ� ǥ���ϴ�  SQL���� �ۼ�.
SELECT WRITER_NM,COUNT(WRITER_NM)
FROM TB_WRITER
GROUP BY WRITER_NM
HAVING COUNT(WRITER_NM)>1;

--14.������ ���� ���� �� ���� ����(COMPOSE_TYPE)�� ������ �����͵��� ���� �ʰ� ������. �ش� �÷��� NULL�� ���
-- '����'���� �����ϴ� SQL���� �ۼ�����
UPDATE TB_BOOK_AUTHOR
SET COMPOSE_TYPE = '����'
WHERE COMPOSE_TYPE IS NULL;

--15. �������� �۰� ������ �����Ϸ����Ѵ�. �繫���� �����̰�, �繫�� ��ȭ ��ȣ ������ 3�ڸ��� �۰��� �̸��� �繫�� ��ȭ ��ȣ�� ǥ���ϴ� SQL���� �ۼ�
SELECT WRITER_NM, OFFICE_TELNO
FROM TB_WRITER
WHERE OFFICE_TELNO LIKE('02%')AND
        OFFICE_TELNO LIKE('02-___-%');

--16. 2006�� 1�� �������� ��ϵ� �� 31�� �̻� �� �۰� �̸��� �̸������� ǥ���ϴ� SQL���� �ۼ�
SELECT WRITER_NM
FROM TB_WRITER
WHERE (TO_DATE(20060101,'YYYYMMDD')-REGIST_DATE)>=11315 --11315�� = 31��
ORDER BY WRITER_NM;

--17. ������ �ٽñ� �α⸦ ��� �ִ� 'Ȳ�ݰ���'���ǻ縦 ���� ��ȹ�� ������ �Ѵ�. Ȳ�ݰ��� ���ǻ翡�� ������ ���� �� ��� ���� 10�� �̸��� �������
--����, �����¸� ǥ���ϴ� SQL �ۼ�. ������ 5�� �̸� ������ '�߰��ֹ��ʿ�'��, �������� '�ҷ�����'�� ǥ��.
--������ ���� ��, ������ ������ ǥ�õǵ��� �Ѵ�.
SELECT BOOK_NM, 
        PRICE,
        CASE WHEN STOCK_QTY<5 THEN '�߰��ֹ��ʿ�'
        ELSE '�ҷ�����' 
        END AS ������
FROM TB_BOOK
WHERE PUBLISHER_NM LIKE ('Ȳ�ݰ���%')AND
        STOCK_QTY<10;
        
--18. '��ŸƮ��' ���� �۰��� ���ڸ� ǥ���ϴ� SQL���� �ۼ��Ͻÿ�. (��� ������,����,���ڷ�)
SELECT BOOK_NM ������,
        W1.WRITER_NM ����,
        W2.WRITER_NM ����
FROM TB_BOOK
JOIN TB_BOOK_AUTHOR A USING(BOOK_NO)
JOIN TB_BOOK_TRANSLATOR T USING(BOOK_NO)
JOIN TB_WRITER W1 ON(W1.WRITER_NO = A.WRITER_NO)
JOIN TB_WRITER W2 ON(W2.WRITER_NO = T.WRITER_NO)
WHERE BOOK_NM = '��ŸƮ��';
--�ٽ�Ǯ�����.

--19. ����������� ���ʹ����Ϸκ��� �� 30�� ����ǰ�, �������� 90�� �̻��� ������ ���� ������, ������, ��������, 20%���ϰ���
--ǥ���ϴ� SQL���� �ۼ�. (��� ��� ������, ������, ����(Org), ����(New)�� ǥ��
SELECT BOOK_NM ������,
        STOCK_QTY ������,
        PRICE "����(Org)",
        PRICE*0.8 "����(New)"
FROM TB_BOOK
WHERE (SYSDATE-ISSUE_DATE) > 10950 AND
    STOCK_QTY >90;


