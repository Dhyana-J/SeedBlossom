--DML ����нǽ�����Ǯ��

--1. �������� ���̺�(TB_CLASS_TYPE)�� �Ʒ��� ���� ������ �Է��Ͻÿ�.
INSERT INTO TB_CLASS_TYPE VALUES ('01','�����ʼ�');
INSERT INTO TB_CLASS_TYPE VALUES ('02','��������');
INSERT INTO TB_CLASS_TYPE VALUES ('03','�����ʼ�');
INSERT INTO TB_CLASS_TYPE VALUES ('04','���缱��');
INSERT INTO TB_CLASS_TYPE VALUES ('05','������');

--2. �� ������б� �л����� ������ ���ԵǾ��ִ� �л��Ϲ����� ���̺��� ��������Ѵ�.
CREATE TABLE TB_STUDENT_INFO(
    STUDENT_NO VARCHAR2(10),
    STUDENT_NAME VARCHAR2(40),
    STUDENT_ADDRESS VARCHAR2(200)
);

INSERT INTO TB_STUDENT_INFO(
    SELECT STUDENT_NO,
            STUDENT_NAME,
            STUDENT_ADDRESS
    FROM TB_STUDENT
);


SELECT * FROM TB_STUDENT_INFO;

--3. ������а� �л����� �������� ���ԵǾ� �ִ� �а����� ���̺��� ������� �Ѵ�.
CREATE TABLE TB_DEPT_KOR_LANG(
    STUDENT_NO VARCHAR2(10),
    STUDENT_NAME VARCHAR2(40),
    STUDENT_SSN VARCHAR2(10),
    PROFESSOR_NAME VARCHAR2(40)
);

INSERT INTO TB_DEPT_KOR_LANG(
    SELECT STUDENT_NO,
            STUDENT_NAME,
            CONCAT(19,SUBSTR(STUDENT_SSN,0,2)),
            PROFESSOR_NAME
    FROM TB_STUDENT
    JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
    LEFT JOIN TB_PROFESSOR ON (COACH_PROFESSOR_NO=PROFESSOR_NO)
    WHERE DEPARTMENT_NAME LIKE('�����%')
);

SELECT * FROM TB_DEPT_KOR_LANG;

--4.�� �а����� ������ 10%������Ű�� �Ǿ���. �̿� ����� SQL�� �ۼ�����.
UPDATE TB_DEPARTMENT SET CAPACITY = ROUND(CAPACITY+CAPACITY*0.1);
SELECT * FROM TB_DEPARTMENT;

--5. �й� A413042�� �ڰǿ� �л��� �ּҰ� "����� ���α� ���ε� 181-21 "�� ����Ǿ��ٰ��Ѵ�.
--�ּ����� �����ϱ� ���� ����� SQL �ۼ�����
UPDATE TB_STUDENT
SET STUDENT_ADDRESS = '����� ���α� ���ε� 181-21 '
WHERE STUDENT_NO = 'A413042';

SELECT * FROM TB_STUDENT WHERE STUDENT_NO = 'A413042';

--6. �ֹε�Ϲ�ȣ ��ȣ���� ���� �л����� ���̺��� �ֹι�ȣ ���ڸ��� �������� �ʱ���ߴ�.
UPDATE TB_STUDENT
SET STUDENT_SSN = SUBSTR(STUDENT_SSN,1,6);

SELECT STUDENT_SSN FROM TB_STUDENT;

--7. ���а� ����� �л��� 2005�� 1�б⿡ �ڽ��� ������ '�Ǻλ�����'������ �߸��Ǿ��ٴ� ���� �߰�,
--������û�ߴ�. �ش� ���� ������ 3.5�� ����Ű�� �����Ǿ���.
UPDATE TB_GRADE
SET POINT = 3.5
WHERE(STUDENT_NO, TERM_NO, CLASS_NO, POINT)IN
    (SELECT STUDENT_NO, TERM_NO, CLASS_NO, POINT--,CLASS_NAME
        FROM TB_GRADE
        JOIN TB_STUDENT USING(STUDENT_NO)
        JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
        JOIN TB_CLASS USING(CLASS_NO)
        WHERE STUDENT_NAME='�����' AND
                DEPARTMENT_NAME='���а�' AND
                TERM_NO = '200501' AND
                CLASS_NAME = '�Ǻλ�����');
                
COMMIT;

--8. ���� ���̺�(TB_GRADE)���� ���л����� �����׸��� �����Ͻÿ�.
UPDATE TB_GRADE
SET POINT = NULL
WHERE STUDENT_NO IN (SELECT DISTINCT STUDENT_NO
                        FROM TB_GRADE
                        JOIN TB_STUDENT USING(STUDENT_NO)
                        WHERE ABSENCE_YN = 'Y');

COMMIT;

                
                





