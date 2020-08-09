/*
03_SELECT(OPTION)�ǽ�����Ǯ��
*/

--1. �л��̸��� �ּ����� ǥ���Ͻÿ�. ��, ��� ����� "�л� �̸�", "�ּ���"�� �ϰ�, �̸����� ������������
SELECT STUDENT_NAME "�л� �̸�",
        STUDENT_ADDRESS �ּ���
FROM TB_STUDENT
ORDER BY STUDENT_NAME;

--2. �������� �л����� �̸��� �ֹι�ȣ�� ���̰� ���� ������ ȭ�鿡 ����Ͻÿ�
SELECT STUDENT_NAME,
        STUDENT_SSN
FROM TB_STUDENT
WHERE ABSENCE_YN = 'Y'
ORDER BY STUDENT_SSN DESC;

--3. �ּ����� �������� ��⵵�� �л��� �� 1900��� �й��� ���� �л����� �̸��� �й�, �ּҸ� �̸��� ������������ ���
SELECT STUDENT_NAME "�л��̸�",
        STUDENT_NO "�й�",
        STUDENT_ADDRESS "������ �ּ�"
FROM TB_STUDENT
WHERE STUDENT_NO NOT LIKE 'A%'
    AND (STUDENT_ADDRESS LIKE '���%' OR STUDENT_ADDRESS LIKE '����%')
ORDER BY STUDENT_NAME;

--4.���� ���а� ���� �� ���� ���̰� ���� ������� �̸��� Ȯ���� �� �ִ� SQL������ �ۼ��Ͻÿ�
SELECT PROFESSOR_NAME,
        PROFESSOR_SSN
FROM TB_PROFESSOR
JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
WHERE DEPARTMENT_NO=005
ORDER BY PROFESSOR_SSN;

--5.2004�� 2�б⿡ 'C3118100'������ ������ �л����� ������ ��ȸ�Ϸ��Ѵ�. ������ ���� �л����� ǥ��, ������ ������ �й��� ���� �л����� ǥ���ϴ� ���� �ۼ�
SELECT STUDENT_NO,
        POINT
FROM TB_GRADE
WHERE CLASS_NO = 'C3118100'
    AND TERM_NO = '200402'
ORDER BY POINT DESC,STUDENT_NO;

--6.�л� ��ȣ, �л� �̸�, �а� �̸��� �л� �̸����� �������� ������ �������
SELECT STUDENT_NO,
        STUDENT_NAME,
        DEPARTMENT_NAME
FROM TB_STUDENT
JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
ORDER BY STUDENT_NAME;

--7.�� ������б��� ���� �̸��� ������ �а� �̸��� ����ϴ� SQL������ �ۼ�
SELECT CLASS_NAME,
        DEPARTMENT_NAME
FROM TB_CLASS
JOIN TB_DEPARTMENT USING(DEPARTMENT_NO);

--8. ���� ���� �̸��� ã������ �Ѵ�. ���� �̸��� ���� �̸��� ����ϴ� SQL���� �ۼ�����
SELECT CLASS_NAME,
        PROFESSOR_NAME
FROM TB_PROFESSOR
JOIN TB_CLASS_PROFESSOR USING(PROFESSOR_NO)
JOIN TB_CLASS USING(CLASS_NO);

--9. 8���� ����� '�ι���ȸ' �迭�� ���� ������ ���� �̸��� ã������ �Ѵ�. �̿� �ش��ϴ� ���� �̸��� ���� �̸��� ����ϴ� SQL���� �ۼ�����
SELECT CLASS_NAME,
        PROFESSOR_NAME
FROM TB_PROFESSOR
JOIN TB_CLASS_PROFESSOR USING(PROFESSOR_NO)
JOIN TB_CLASS USING(CLASS_NO)
JOIN TB_DEPARTMENT ON (TB_DEPARTMENT.DEPARTMENT_NO=TB_PROFESSOR.DEPARTMENT_NO)
WHERE CATEGORY = '�ι���ȸ';

--10. '�����а�'�л����� ������ ���Ϸ��� �Ѵ�. �����а� �л����� "�й�","�л��̸�","��ü����"�� ����ϴ� SQL�� �ۼ�
SELECT STUDENT_NO "�й�",
        STUDENT_NAME "�л� �̸�",
        ROUND(AVG(POINT),1) "��ü����"
FROM TB_STUDENT
JOIN TB_GRADE USING(STUDENT_NO)
JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
WHERE DEPARTMENT_NAME = '�����а�'
GROUP BY STUDENT_NO, POINT, STUDENT_NAME;

--11. �й��� A313047�� �л��� �б��� ���������� �ʴ�. ������������ ������ �����ϱ� ���� �а��̸�, �л��̸��� �������� �̸��� �ʿ��ϴ�. 
SELECT DEPARTMENT_NAME,
        STUDENT_NAME,
        PROFESSOR_NAME
FROM TB_STUDENT
JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
JOIN TB_PROFESSOR ON (COACH_PROFESSOR_NO=PROFESSOR_NO)
WHERE STUDENT_NO = 'A313047';

--12. 2007�⵵�� '�ΰ������' ������ ������ �л��� ã�� �л��̸��� �����б⸦ ǥ���ϴ� SQL���� �ۼ�
SELECT STUDENT_NAME,
        TERM_NO "TERM_NAME"
FROM TB_STUDENT
JOIN TB_GRADE USING(STUDENT_NO)
JOIN TB_CLASS USING(CLASS_NO)
WHERE CLASS_NAME = '�ΰ������'
        AND TERM_NO LIKE ('2007%');
        
--13. ��ü�� �迭 ���� �� ���� ��米���� �� �� �������� ���� ������ ã�� �� ���� �̸��� �а� �̸��� ����ϴ� SQL���� �ۼ��Ͻÿ�
SELECT CLASS_NAME,
        DEPARTMENT_NAME
FROM TB_CLASS
LEFT JOIN TB_CLASS_PROFESSOR USING(CLASS_NO)
JOIN TB_DEPARTMENT USING (DEPARTMENT_NO)
WHERE CATEGORY = '��ü��'
    AND PROFESSOR_NO IS NULL
ORDER BY DEPARTMENT_NAME DESC;

--14.�� ������б� ���ݾƾ��а� �л��帣�� ���������� �Խ��ϰ��� �Ѵ�. �л��̸��� �������� �̸��� ã�� ���� ���� ������ ���� �л��� ��� "�������� ������"���� ǥ��
SELECT STUDENT_NAME "�л��̸�",
        NVL(PROFESSOR_NAME,'�������� ������') "��������"
FROM TB_STUDENT
JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
LEFT JOIN TB_PROFESSOR ON (COACH_PROFESSOR_NO = PROFESSOR_NO)
WHERE DEPARTMENT_NAME = '���ݾƾ��а�'
ORDER BY STUDENT_NAME DESC;

--15. ���л��� �ƴ� �л� �� ������ 4.0�̻��� �л��� ã�� �� �л��� �й�,�̸�,�а�,�̸�,������ ����ϴ� SQL���� �ۼ��Ͻÿ�
SELECT STUDENT_NO "�й�",
        STUDENT_NAME "�̸�",
        DEPARTMENT_NAME "�а� �̸�",
        TRUNC(AVG(POINT),2) "����"
FROM TB_STUDENT
JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
JOIN TB_GRADE USING(STUDENT_NO)
WHERE ABSENCE_YN = 'N'
GROUP BY STUDENT_NO, STUDENT_NAME, DEPARTMENT_NAME
HAVING AVG(POINT)>=4.0
ORDER BY STUDENT_NAME;

--16. ȯ�������а� ����������� ���� �� ������ �ľ��� �� �ִ� SQL�� �ۼ�
SELECT CLASS_NO,
        CLASS_NAME,
        TRUNC(AVG(POINT),8)
FROM TB_CLASS
JOIN TB_GRADE USING(CLASS_NO)
JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
WHERE DEPARTMENT_NAME = 'ȯ�������а�'
    AND CLASS_TYPE LIKE ('����%')
GROUP BY CLASS_NO,CLASS_NAME;

--17. �������б��� �ٴϰ� �ִ� �ְ��� �л��� ���� �� �л����� �̸��� �ּҸ� ����ϴ� SQL���� �ۼ��Ͻÿ�
SELECT STUDENT_NAME,
        STUDENT_ADDRESS
FROM TB_STUDENT
WHERE DEPARTMENT_NO = (SELECT DEPARTMENT_NO
                        FROM TB_STUDENT
                        WHERE STUDENT_NAME='�ְ���');

--18. ������а����� �� ������ ���� ���� �л��� �̸��� �й��� ǥ���ϴ� SQL���� �ۼ��Ͻÿ�
SELECT STUDENT_NO,
        STUDENT_NAME
FROM (SELECT STUDENT_NO,
        STUDENT_NAME,
        DENSE_RANK() OVER(ORDER BY AVG(POINT) DESC) AS "����"
        FROM TB_STUDENT
        JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
        JOIN TB_GRADE USING(STUDENT_NO)
        WHERE DEPARTMENT_NAME='������а�'
        GROUP BY STUDENT_NO,STUDENT_NAME)
WHERE "����" = 1;

--19. �������б��� "ȯ�������а�"�� ���� ���� �迭 �а����� �а��� �������� ������ �ľ��ϱ� ���� ������ SQL�� �ۼ�
SELECT DEPARTMENT_NAME "�迭 �а���",
        ROUND(AVG(POINT),1) "��������"
FROM TB_DEPARTMENT
JOIN TB_CLASS USING(DEPARTMENT_NO)
JOIN TB_GRADE USING(CLASS_NO)
WHERE CATEGORY = (SELECT CATEGORY
                    FROM TB_DEPARTMENT
                    WHERE DEPARTMENT_NAME='ȯ�������а�')
    AND CLASS_TYPE LIKE('����%')
GROUP BY DEPARTMENT_NAME
ORDER BY DEPARTMENT_NAME;

        








