/*

@ JOIN?
�� �� �̻��� ���̺��� �����͸� ��ȸ�ϰ��� �� �� ���Ǵ� �����̴�.
��ȸ ����� �ϳ��� ����� (RESULT SET)�� ���´�.

RELATIONAL DB(������ DB)�� �ּ����� �����͸� ������ ���̺� ����ִ�. (������ �ߺ��� �ּ�ȭ�� ���̺�)
=> JOIN ������ ������ DB���� ���̺� "����"�� �α� ���� ����!


JOIN�� ũ�� "ORACLE"�� "ANSI" �������� ������.


        
FROM���� ','�� ������ ��ĥ ���̺� ���� ����ϰ�,
WHERE���� ��ġ�⿡ �� Į������ ���������.

*/

--�� ������� ���, �����, �μ��ڵ�, �μ������ ���� ��ȸ�ϰ� ���� ��?
SELECT EMP_ID, EMP_NAME, DEPT_CODE
FROM EMPLOYEE;

SELECT *
FROM DEPARTMENT,LOCATION
--LEFT JOIN LOCATION ON(LOCATION_ID=LOCAL_CODE)
--WHERE LOCATION_ID = 'L1';
WHERE LOCATION_ID=LOCAL_CODE;


SELECT EMP_ID, EMP_NAME, DEPT_CODE,DEPT_ID, DEPT_TITLE, LOCATION_ID
FROM EMPLOYEE--, DEPARTMENT;
--WHERE DEPT_CODE = DEPT_ID;
--JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID); --�⺻�� INNER JOIN
FULL OUTER JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID);


SELECT EMP_ID, EMP_NAME, E.JOB_CODE, JOB_NAME
FROM EMPLOYEE E, JOB J
WHERE E.JOB_CODE=J.JOB_CODE;

SELECT EMP_ID, EMP_NAME, JOB_CODE, JOB_NAME
FROM EMPLOYEE
JOIN JOB USING(JOB_CODE);

SELECT EMP_ID, EMP_NAME, E.JOB_CODE, JOB_NAME
FROM EMPLOYEE E
JOIN JOB J ON (E.JOB_CODE=J.JOB_CODE);

SELECT EMP_NAME, DEPT_TITLE
FROM EMPLOYEE, DEPARTMENT;
WHERE DEPT_CODE = DEPT_ID; --DEFAULT�� INNER JOIN��
--WHERE DEPT_CODE(+) = DEPT_ID;
--�Ⱥ��� ������ (+)���� �ְ� �������. �״ϱ� (+)�Ⱥ����� �������� ������ �Ͼ��. �׷� ORACLE �������� FULL OUTER�� �������?

--������ �븮�� ����� ���, �����, �޿� ��ȸ
SELECT JOB_CODE
FROM JOB
WHERE JOB_NAME = '�븮';

SELECT EMP_ID, EMP_NAME, SALARY, JOB_NAME, JOB_CODE
FROM EMPLOYEE
JOIN JOB USING(JOB_CODE);

---------------------------------<�ǽ�����>-------------------------------------
--1.�μ��� �λ�������� ������� ���, �����, ���ʽ��� ��ȸ
-->>����Ŭ �� ANSI ���� �� �� �Ẹ��. 
--ORACLE
SELECT EMP_NO, EMP_NAME, NVL(BONUS,'0')
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_TITLE = '�λ������';
--ANSI
SELECT EMP_NO, EMP_NAME, NVL(BONUS,0)
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_TITLE='�λ������');

--2. �μ��� �ѹ��ΰ� �ƴ� ������� �����, �޿�, �Ի��� ��ȸ
-->>���� �� �� �Ẹ��
--ORACLE
SELECT EMP_NAME, SALARY, HIRE_DATE, DEPT_TITLE
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID AND DEPT_TITLE !='�ѹ���';
--ANSI
SELECT EMP_NAME, SALARY, HIRE_DATE, DEPT_TITLE
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID AND DEPT_TITLE !='�ѹ���');

--3. ���ʽ��� �޴� ������� ���, �����, ���ʽ�, �μ����� ��ȸ
-->> ���� �� ��
--ORACLE
SELECT EMP_NO, EMP_NAME, BONUS, DEPT_TITLE
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID
    AND BONUS IS NOT NULL;

--ANSI
SELECT EMP_NO, EMP_NAME, BONUS, DEPT_TITLE
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID)
    AND BONUS IS NOT NULL;


--4. �Ʒ��� �� ���̺� �����ؼ� �μ��ڵ�, �μ���, �����ڵ�, ������(LOCAL_NAME) ��ȸ����
SELECT * FROM DEPARTMENT;
SELECT * FROM LOCATION;

--ORACLE
SELECT DEPT_ID, DEPT_TITLE, LOCAL_CODE, LOCAL_NAME
FROM DEPARTMENT, LOCATION
WHERE LOCATION_ID=LOCAL_CODE;
--ANSI
SELECT DEPT_ID, DEPT_TITLE, LOCAL_CODE, LOCAL_NAME
FROM DEPARTMENT
JOIN LOCATION ON (LOCATION_ID=LOCAL_CODE);

-- ���, �����, �μ���, ���޸�
SELECT EMP_NO, EMP_NAME, DEPT_TITLE, JOB_NAME
FROM EMPLOYEE E, DEPARTMENT, JOB J
WHERE DEPT_CODE=DEPT_ID AND (E.JOB_CODE = J.JOB_CODE);

SELECT EMP_NO, EMP_NAME, DEPT_TITLE, JOB_NAME
FROM EMPLOYEE
JOIN JOB USING (JOB_CODE)
RIGHT OUTER JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID); -- LEFT RIGHT �� �ᵵ �˾Ƽ� OUTER��� �˾Ƶ���

SELECT EMP_NO, EMP_NAME, DEPT_TITLE--JOB_NAME
FROM EMPLOYEE E, DEPARTMENT--, JOB J
WHERE DEPT_CODE(+)=DEPT_ID; -- AND E.JOB_CODE = J.JOB_CODE;
--WHERE DEPT_ID=DEPT_CODE(+) AND E.JOB_CODE = J.JOB_CODE;

-- 2) RIGHT [OUTER] JOIN : �� ���̺� �� ������ ����� ���̺��� �������� JOIN
-->> ANSI ����
SELECT EMP_NAME, DEPT_TITLE, SALARY
FROM EMPLOYEE
RIGHT DEPARTMENT ON (DEPT_CODE = DEPT_ID);

SELECT EMP_NAME, DEPT_TITLE, SALARY
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE(+)=DEPT_ID;

--------------------------------------------------------------------------------------

/*
    3. CARTESIAN PRODUCT / CROSS JOIN
    ��� ���̺��� �� ����� ���μ��� ���ε� �����Ͱ� ��ȸ (������)
    �� ���̺��� ����� ��� ������ ����� ������ ��� --> ����� ������ ��� --> ����ȭ�� ����
*/

-- �����, �μ���
-->> ����Ŭ ����
SELECT EMP_NAME, DEPT_TITLE
FROM EMPLOYEE, DEPARTMENT; --> 23*9 => 207��

-->> ANSI ����
SELECT EMP_NAME, DEPT_TITLE
FROM EMPLOYEE
CROSS JOIN DEPARTMENT;

-----------------------------------------------------

/*
 4. ��ü ���� (SELF JOIN)
*/

SELECT E.EMP_ID, E.EMP_NAME, E.DEPT_CODE, E.MANAGER_ID, M.EMP_NAME
FROM EMPLOYEE E,EMPLOYEE M
WHERE E.MANAGER_ID(+) = M.EMP_ID;

SELECT E.EMP_ID, E.EMP_NAME, E.DEPT_CODE, E.MANAGER_ID, M.EMP_NAME
FROM EMPLOYEE E
LEFT JOIN EMPLOYEE M ON (E.MANAGER_ID = M.EMP_ID);
---------------------------------------------------------------------
--�� ����
-- �����, �޿�
SELECT EMP_NAME, SALARY
FROM EMPLOYEE;

SELECT*
FROM SAL_GRADE;

-- �����, �޿�, �޿����(SAL_LEVEL)
-->> ����Ŭ ����
SELECT EMP_NAME, SALARY, SAL_LEVEL
FROM EMPLOYEE, SAL_GRADE
WHERE SALARY BETWEEN MIN_SAL AND MAX_SAL;

SELECT EMP_NAME, SALARY, SAL_LEVEL
FROM EMPLOYEE
JOIN SAL_GRADE ON (SALARY BETWEEN MIN_SAL AND MAX_SAL);

-------------------------------------------------------------------------

SELECT * FROM EMPLOYEE; -- DEPT_CODE
SELECT * FROM DEPARTMENT; -- DEPT_ID    LOCATION_ID
SELECT * FROM LOCATION; -- LOCAL_CODE

SELECT EMP_ID, EMP_NAME, DEPT_TITLE, LOCAL_NAME
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN LOCATION ON (LOCATION_ID=LOCAL_CODE);

SELECT EMP_ID, EMP_NAME, DEPT_TITLE, LOCAL_NAME
FROM EMPLOYEE, DEPARTMENT, LOCATION
WHERE DEPT_CODE = DEPT_ID AND LOCATION_ID=LOCAL_CODE;

--------------------------------------------------------------------------------
--���, �����, �μ���, ���޸�, �ٹ�����, �ٹ�������, �޿���� ��ȸ
--ORACLE
SELECT EMP_NO ���,
        EMP_NAME �����, 
        DEPT_TITLE �μ���, 
        J.JOB_NAME ���޸�, 
        LOCAL_NAME �ٹ�����, 
        NATIONAL_NAME �ٹ�������, 
        SAL_LEVEL �޿����
FROM EMPLOYEE E, 
        DEPARTMENT D,
        JOB J, 
        LOCATION L, 
        NATIONAL N, 
        SAL_GRADE
WHERE E.DEPT_CODE=D.DEPT_ID 
        AND E.JOB_CODE = J.JOB_CODE
        AND D.LOCATION_ID=L.LOCAL_CODE 
        AND L.NATIONAL_CODE = N.NATIONAL_CODE 
        AND E.SALARY BETWEEN MIN_SAL AND MAX_SAL;
-- ANSI
SELECT EMP_NO ���,
        EMP_NAME �����, 
        DEPT_TITLE �μ���, 
        J.JOB_NAME ���޸�, 
        LOCAL_NAME �ٹ�����, 
        NATIONAL_NAME �ٹ�������, 
        SAL_LEVEL �޿����
FROM EMPLOYEE E
JOIN DEPARTMENT D ON (E.DEPT_CODE=D.DEPT_ID)
JOIN JOB J ON (E.JOB_CODE = J.JOB_CODE)
JOIN LOCATION L ON (D.LOCATION_ID=L.LOCAL_CODE)
JOIN NATIONAL N ON (L.NATIONAL_CODE=N.NATIONAL_CODE)
JOIN SAL_GRADE ON (E.SALARY BETWEEN MIN_SAL AND MAX_SAL);







