-- 1. ������ �븮�̸鼭 ASIA������ �ٹ��ϴ� ��������
--    ���, �����, ���޸�, �μ���, �ٹ�������, �޿��� ��ȸ�Ͻÿ�
--ORACLE
SELECT E.EMP_NO ���, --EMPLOYEE
        E.EMP_NAME �����,--EMPLOYEE
        D.DEPT_TITLE �μ���,--DEPARTMENT
        L.LOCAL_NAME �ٹ�������,--LOCATION
        E.SALARY �޿�--EMPLOYEE
FROM EMPLOYEE E,
        JOB J, -- ���޸�, JOB_NAME �ʿ�. 
        DEPARTMENT D, --������ �븮, DEPT_TITLE �ʿ�
        LOCATION L -- ASIA���� �ٹ�, LOCAL_NAME �ʿ� 
WHERE E.DEPT_CODE=D.DEPT_ID --DEPT_TITLE �����;���
        AND J.JOB_NAME = '�븮' 
        AND E.JOB_CODE = J.JOB_CODE --JOB_NAME �����;���
        AND D.LOCATION_ID=L.LOCAL_CODE --LOCAL_NAME �����;���
        AND L.LOCAL_NAME LIKE ('ASIA%');
--ANSI
SELECT E.EMP_NO ���, --EMPLOYEE
        E.EMP_NAME �����,--EMPLOYEE
        D.DEPT_TITLE �μ���,--DEPARTMENT
        L.LOCAL_NAME �ٹ�������,--LOCATION
        E.SALARY �޿�--EMPLOYEE
FROM EMPLOYEE E
JOIN DEPARTMENT D ON (E.DEPT_CODE=D.DEPT_ID)
JOIN LOCATION L ON D.LOCATION_ID=L.LOCAL_CODE AND LOCAL_NAME LIKE 'ASIA%'
JOIN JOB J ON E.JOB_CODE=J.JOB_CODE AND JOB_NAME='�븮';


-- 2. 70�����̸鼭 �����̰�, ���� ������ ��������
--    �����, �ֹι�ȣ, �μ���, ���޸��� ��ȸ�Ͻÿ�
/*

*/
--ORACLE
SELECT EMP_NAME, 
        EMP_NO, 
        DEPT_TITLE, 
        JOB_NAME
FROM EMPLOYEE E,
        DEPARTMENT,
        JOB J
WHERE E.JOB_CODE=J.JOB_CODE
    AND DEPT_CODE=DEPT_ID
    AND EMP_NO LIKE ('7%') --70����
    AND SUBSTR(EMP_NO,8,1)=2 --�ֹι�ȣ ���ڸ� ������ 2 (����)
    AND EMP_NAME LIKE ('��%');
--ANSI
SELECT EMP_NAME, 
        EMP_NO, 
        DEPT_TITLE, 
        JOB_NAME
FROM EMPLOYEE
JOIN JOB USING(JOB_CODE)
JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID)
WHERE EMP_NO LIKE ('7%') --70����
    AND SUBSTR(EMP_NO,8,1)=2 --�ֹι�ȣ ���ڸ� ������ 2 (����)
    AND EMP_NAME LIKE ('��%');


-- 3. �̸��� '��'�ڰ� ����ִ� ��������
--    ���, �����, ���޸��� ��ȸ�Ͻÿ�
--ORACLE
SELECT EMP_ID,
        EMP_NAME,
        JOB_NAME
FROM EMPLOYEE E, JOB J
WHERE E.JOB_CODE=J.JOB_CODE
    AND EMP_NAME LIKE '%��%';
--ANSI
SELECT EMP_ID,
        EMP_NAME,
        JOB_NAME
FROM EMPLOYEE
JOIN JOB USING(JOB_CODE)
WHERE EMP_NAME LIKE '%��%';


-- 4. �ؿܿ������� �ٹ��ϴ� ��������
--    �����, ���޸�, �μ��ڵ�, �μ����� ��ȸ�Ͻÿ�
--ORACLE
SELECT EMP_NAME,
        JOB_NAME, -- E.JOB_CODE=J.JOB_CODE �ʿ�
        DEPT_CODE,
        DEPT_TITLE -- DEPT_CODE=DEPT_ID �ʿ�
FROM EMPLOYEE E,
    DEPARTMENT,
    JOB J
WHERE E.JOB_CODE=J.JOB_CODE
    AND DEPT_CODE=DEPT_ID
    AND DEPT_TITLE LIKE '�ؿܿ���%';
--ANSI
SELECT EMP_NAME,
        JOB_NAME, -- E.JOB_CODE=J.JOB_CODE �ʿ�
        DEPT_CODE,
        DEPT_TITLE -- DEPT_CODE=DEPT_ID �ʿ�
FROM EMPLOYEE
JOIN JOB USING(JOB_CODE)
JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID)
WHERE DEPT_TITLE LIKE '�ؿܿ���%';

-- 5. ���ʽ��� �޴� ��������
--    �����, ���ʽ�, ����, �μ���, �ٹ��������� ��ȸ�Ͻÿ�
--ORACLE
SELECT EMP_NAME,
        BONUS,
        SALARY*12 ����,
        DEPT_TITLE, -- DEPT_CODE=DEPT_ID
        LOCAL_NAME -- LOCATION_ID = LOCAL_CODE
FROM EMPLOYEE,
    DEPARTMENT,
    LOCATION
WHERE DEPT_CODE=DEPT_ID(+) --DEPARTMENT�� EMPLOYEE�� ��ŷ�Ѵ�.
    AND LOCATION_ID=LOCAL_CODE(+) --LOCATION�� DEPARTMENT�� ��ŷ�Ѵ�. 
    AND BONUS IS NOT NULL;
--�ϵ����̰� �Ⱥ��δ�..! (+)�� �߰�������! �߰��Ҷ��� �帧�� ����ؼ� �ۼ�������.
--������ Ʃ���� ���⶧���� ������ ���� �߸��Ǿ����� Ȯ���� �����ϴ�. �ٵ� Ʃ���� 1�ﰳ�� ���� �� �߸��ߴ��� ���� ����ұ�..?
--ANSI
SELECT EMP_NAME,
        BONUS,
        SALARY*12 ����,
        DEPT_TITLE, -- DEPT_CODE=DEPT_ID
        LOCAL_NAME -- LOCATION_ID = LOCAL_CODE
FROM EMPLOYEE
LEFT JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID)
LEFT JOIN LOCATION ON (LOCATION_ID=LOCAL_CODE)
WHERE BONUS IS NOT NULL;

-- 6. �μ��� �ִ� ��������
--    �����, ���޸�, �μ���, �ٹ��������� ��ȸ�Ͻÿ�
--ORACLE
SELECT EMP_NAME,
        JOB_NAME, --JOIN JOB USING(JOB_CODE)
        DEPT_TITLE, -- DEPT_CODE=DEPT_ID
        LOCAL_NAME --LOCATION_ID=LOCAL_CODE
FROM EMPLOYEE E,
        DEPARTMENT,
        JOB J,
        LOCATION
WHERE E.JOB_CODE=J.JOB_CODE
    AND DEPT_CODE=DEPT_ID
    AND LOCATION_ID=LOCAL_CODE
    AND DEPT_CODE IS NOT NULL;
--ANSI
SELECT EMP_NAME,
        JOB_NAME, --JOIN JOB USING(JOB_CODE)
        DEPT_TITLE, -- DEPT_CODE=DEPT_ID
        LOCAL_NAME --LOCATION_ID=LOCAL_CODE
FROM EMPLOYEE
JOIN JOB USING(JOB_CODE)
JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID)
JOIN LOCATION ON (LOCATION_ID=LOCAL_CODE)
WHERE DEPT_CODE IS NOT NULL;


-- 7. '�ѱ�'�� '�Ϻ�'�� �ٹ��ϴ� �������� 
--    �����, �μ���, �ٹ�������, �ٹ��������� ��ȸ�Ͻÿ�
--ORACLE
SELECT EMP_NAME,
        DEPT_TITLE, --JOIN EMP DEPT 
        LOCAL_NAME, --JOIN DEPT LOC
        NATIONAL_NAME --JOIN LOC NATION
FROM EMPLOYEE,
    DEPARTMENT,
    LOCATION,
    NATIONAL
WHERE DEPT_CODE=DEPT_ID
    AND LOCATION_ID=LOCAL_CODE
    AND LOCATION.NATIONAL_CODE=NATIONAL.NATIONAL_CODE
    AND (NATIONAL.NATIONAL_CODE = 'KO' OR NATIONAL.NATIONAL_CODE = 'JP');
--ANSI
SELECT EMP_NAME,
        DEPT_TITLE, --JOIN EMP DEPT 
        LOCAL_NAME, --JOIN DEPT LOC
        NATIONAL_NAME --JOIN LOC NATION
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID)
JOIN LOCATION ON (LOCATION_ID=LOCAL_CODE)
JOIN NATIONAL ON (NATIONAL.NATIONAL_CODE=LOCATION.NATIONAL_CODE)
WHERE NATIONAL.NATIONAL_CODE = 'KO' 
    OR NATIONAL.NATIONAL_CODE = 'JP';

-- 8. ���ʽ��� ���� �ʴ� ������ �� �����ڵ尡 J4 �Ǵ� J7�� ��������
--    �����, ���޸�, �޿��� ��ȸ�Ͻÿ�
--ORACLE
SELECT EMP_NAME,
        JOB_NAME, --E.JOB_CODE=J.JOB_CODE
        SALARY
FROM EMPLOYEE,
    JOB
WHERE EMPLOYEE.JOB_CODE=JOB.JOB_CODE
    AND BONUS IS NULL
    AND (EMPLOYEE.JOB_CODE='J4' OR EMPLOYEE.JOB_CODE='J7');
--ANSI
SELECT EMP_NAME,
        JOB_NAME, --E.JOB_CODE=J.JOB_CODE
        SALARY
FROM EMPLOYEE
JOIN JOB USING(JOB_CODE)
WHERE BONUS IS NULL
    AND(JOB_CODE='J4' OR JOB_CODE='J7');

-- 9. ���, �����, ���޸�, �޿����, ������ ��ȸ�ϴµ�
--    �̶� ���п� �ش��ϴ� ����
--    �޿������ S1, S2�� ��� '���'
--    �޿������ S3, S4�� ��� '�߱�'
--    �޿������ S5, S6�� ��� '�ʱ�' ���� ��ȸ�ǰ� �Ͻÿ�.
--ORACLE
SELECT EMP_ID,
        EMP_NAME,
        JOB_NAME, -- JOB_CODE=JOB_CODE
        SAL_LEVEL �޿����, --NONEQU JOIN
        CASE WHEN SAL_LEVEL = 'S1'
                OR SAL_LEVEL = 'S2'
                THEN '���'
            WHEN SAL_LEVEL = 'S3'
                OR SAL_LEVEL = 'S4'
                THEN '�߱�'
            WHEN SAL_LEVEL = 'S5'
                OR SAL_LEVEL = 'S6'
                THEN '�ʱ�'
            END
FROM EMPLOYEE,
        JOB,
        SAL_GRADE
WHERE EMPLOYEE.JOB_CODE=JOB.JOB_CODE
    AND SALARY BETWEEN MIN_SAL AND MAX_SAL;
--ANSI
SELECT EMP_ID,
        EMP_NAME,
        JOB_NAME, -- JOB_CODE=JOB_CODE
        SAL_LEVEL �޿����, --NONEQU JOIN
        CASE WHEN SAL_LEVEL = 'S1'
                OR SAL_LEVEL = 'S2'
                THEN '���'
            WHEN SAL_LEVEL = 'S3'
                OR SAL_LEVEL = 'S4'
                THEN '�߱�'
            WHEN SAL_LEVEL = 'S5'
                OR SAL_LEVEL = 'S6'
                THEN '�ʱ�'
            END
FROM EMPLOYEE
JOIN JOB USING(JOB_CODE)
JOIN SAL_GRADE ON (SALARY BETWEEN MIN_SAL AND MAX_SAL);
   
-- 10. �� �μ��� �� �޿����� ��ȸ�ϵ�
--     �̶�, �� �޿����� 1000���� �̻��� �μ���, �޿����� ��ȸ�Ͻÿ�
--ORACLE
SELECT DEPT_TITLE,
        SUM(SALARY)
FROM EMPLOYEE,
    DEPARTMENT
WHERE DEPT_CODE = DEPT_ID
GROUP BY (DEPT_TITLE)
HAVING SUM(SALARY)>=10000000;
--ANSI
SELECT DEPT_TITLE,
        SUM(SALARY)
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID)
GROUP BY (DEPT_TITLE)
HAVING SUM(SALARY)>=10000000;


-- 11. �� �μ��� ��ձ޿��� ��ȸ�Ͽ� �μ���, ��ձ޿� (����ó��)�� ��ȸ�Ͻÿ�.
--      ��, �μ���ġ�� �ȵ� ������� ��յ� ���� �����Բ� �Ͻÿ�.
--ORACLE
SELECT DEPT_TITLE,
        TRUNC(AVG(SALARY)) ��ձ޿�
FROM EMPLOYEE,
        DEPARTMENT
WHERE DEPT_CODE=DEPT_ID(+)
GROUP BY (DEPT_TITLE);
--ANSI
SELECT DEPT_TITLE,
        TRUNC(AVG(SALARY)) ��ձ޿�
FROM EMPLOYEE
LEFT JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID)
GROUP BY (DEPT_TITLE);