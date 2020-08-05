--1. ������а�(�а��ڵ� 002)�л����� �й��� �̸�, ���г⵵�� ���г⵵�� ���������� ǥ���ϴ� SQL������ �ۼ��Ͻÿ�
SELECT STUDENT_NO AS "�й�",
    STUDENT_NAME AS "�̸�",
    TO_CHAR(ENTRANCE_DATE,'YYYY-MM-DD') AS "���г⵵"
FROM TB_STUDENT 
WHERE DEPARTMENT_NO = '002'
ORDER BY ENTRANCE_DATE;

--2. �������б��� ���� �� �̸��� �� ���ڰ� �ƴ� ������ �� �� �ִٰ� �Ѵ�. �� ������ �̸��� �ֹι�ȣ�� ȭ�鿡 �������.
SELECT PROFESSOR_NAME, PROFESSOR_SSN 
FROM TB_PROFESSOR
WHERE NOT LENGTH(PROFESSOR_NAME) = '3';

--3.�������б��� ���� �������� �̸��� ���̸� ����ϴ� SQL������ �ۼ��Ͻÿ�.
--��, �� �� ���̰� ���� ������Լ� ���� ��� ������ ȭ�鿡 ��µǵ��� ����ÿ�.
--���� �� 2000�� ���� ����ڴ� ������ ���̴� '��'���� ����Ѵ�.
SELECT PROFESSOR_NAME AS "�����̸�",
    FLOOR(TO_NUMBER(MONTHS_BETWEEN(SYSDATE,TO_DATE(CONCAT('19',SUBSTR(PROFESSOR_SSN, 1,6)),'YYYYMMDD'))/12)) AS "������"
FROM TB_PROFESSOR
ORDER BY 2;

--4.�������� �̸� �� ���� ������ �̸��� ����ϴ� SQL������ �ۼ��Ͻÿ�. ��� ����� "�̸�"
SELECT SUBSTR(PROFESSOR_NAME,2,2) 
FROM TB_PROFESSOR;

--5.�������б��� ����� �����ڸ� ���Ϸ��� �Ѵ�. ��� ã�Ƴ� ���ΰ�? �� ��, 19�쿡 �����ϸ� ����� ���� ���������� ����
 --�й� �տ� A������ 2000�⵵ �������ε�.
SELECT STUDENT_NO, STUDENT_NAME
FROM TB_STUDENT
-- ���г⵵ - ����⵵ = 19�̸� ����� ���� ���̴�.
WHERE 
CASE
    WHEN SUBSTR(STUDENT_NO,1,1)='A' THEN TO_NUMBER(CONCAT('200',SUBSTR(STUDENT_NO,2,1)))-TO_NUMBER(CONCAT('19',SUBSTR(STUDENT_SSN,1,2)))
    ELSE TO_NUMBER(SUBSTR(STUDENT_NO,1,2))-TO_NUMBER(SUBSTR(STUDENT_SSN,1,2))
END
= 19;

--6. 2020�� ũ���������� ���� �����ΰ�?
SELECT TO_CHAR(TO_DATE('2020/12/25'),'DAY') FROM DUAL;

--7. TO_DATE('99/10/11', 'YY/MM/DD'), TO_DATE('49/10/11', 'YY/MM/DD')�� ���� �� �� �� �� �� ���� �ǹ��ұ�?
-- �� TO_DATE('99/10/11', 'RR/MM/DD'), TO_DATE('49/10/11','RR/MM/DD')�� ���� ��� ��� ������ �ǹ��ұ�?
--TO_DATE('99/10/11', 'YY/MM/DD') -> 2099/10/11
--TO_DATE('49/10/11', 'YY/MM/DD') -> 2049/10/11
--TO_DATE('99/10/11', 'RR/MM/DD') -> 1999/10/11
--TO_DATE('49/10/11','RR/MM/DD')  -> 2049/10/11
-- YY : ��� 2000���� �ν�. 
-- RR : ���� �⵵�� �� ���ڸ��� 00~49�ε� �Է³⵵�� 00~49�̸� ���缼��.
--      ���� �⵵�� �� ���ڸ��� 00~49�ε� �Է³⵵�� 50~99�̸� ��������.(1900���)
--      ���� �⵵�� �� ���ڸ��� 50~99�ε� �Է³⵵�� 00~49�̸� ��������.(2100���)
--      ���� �⵵�� �� ���ڸ��� 50~99�ε� �Է³⵵�� 50~99�̸� ���缼��.(2000���)
SELECT TO_CHAR(TO_DATE('99/10/11','YY/MM/DD'),'YYYY-MM-DD') AS "1",
    TO_CHAR(TO_DATE('49/10/11', 'YY/MM/DD'),'YYYY-MM-DD') AS "2",
    TO_CHAR(TO_DATE('99/10/11', 'RR/MM/DD'),'YYYY-MM-DD') AS "3",
    TO_CHAR(TO_DATE('49/10/11','RR/MM/DD'),'YYYY-MM-DD') AS "4"
FROM DUAL;

--8.�������б��� 2000�⵵ ���� �����ڵ��� �й��� A�� �����ϰԵǾ��ִ�. 
-- 2000�⵵ ���� �й��� ���� �л����� �й��� �̸��� �����ִ� SQL������ �ۼ��Ͻÿ�.
SELECT STUDENT_NO, STUDENT_NAME 
FROM TB_STUDENT
WHERE NOT SUBSTR(STUDENT_NO,1,1)='A';
