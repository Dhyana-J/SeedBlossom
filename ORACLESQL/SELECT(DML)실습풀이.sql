
--����� �ǽ�����
--1.�а� �̸��� �迭�� ǥ���Ͻÿ�.
SELECT DEPARTMENT_NAME AS "�а� ��",CATEGORY AS "�迭" FROM TB_DEPARTMENT;

--2.�а��� �а� ������ ����Ѵ�.
SELECT DEPARTMENT_NAME||'�� ������ '||CAPACITY||'�� �Դϴ�.' AS "�а��� ����" FROM TB_DEPARTMENT;

--3. ������а� ���л� �� ���� �������� ���л� ã��
SELECT STUDENT_NAME FROM TB_STUDENT WHERE STUDENT_SSN LIKE '_______2%' AND absence_yn = 'Y' AND DEPARTMENT_NO = '001';

--4. ���������� ���� ���� ��⿬ü�ڵ��� ã�� �̸� �Խ�
SELECT STUDENT_NAME AS "��⿬ü��" 
FROM TB_STUDENT 
WHERE STUDENT_NO IN('A513079','A513090','A513091','A513110','A513119') 
ORDER BY STUDENT_NO DESC;

--5. ���������� 20�� �̻� 30�� ������ �а����� �а� �̸��� �迭�� ����Ͻÿ�.
SELECT DEPARTMENT_NAME, CATEGORY, CAPACITY FROM TB_DEPARTMENT WHERE CAPACITY BETWEEN 20 AND 30;

--6. �� ������б� ���� �̸��� �˾Ƴ� �� �ִ� SQL������ �ۼ��϶�
SELECT PROFESSOR_NAME FROM TB_PROFESSOR WHERE DEPARTMENT_NO IS NULL;

--7. ������� ������ �а��� �����Ǿ����� ���� �л��� �ִ��� Ȯ��
SELECT STUDENT_NAME, DEPARTMENT_NO FROM TB_STUDENT WHERE DEPARTMENT_NO IS NOT NULL;

--8. �������� �����ϴ� ������� � ��������� �����ȣ ���
SELECT CLASS_NO AS "������������" FROM TB_CLASS WHERE PREATTENDING_CLASS_NO IS NOT NULL;

--9. �� ���п��� � �迭���� �ִ��� ��ȸ�ϱ�
SELECT DISTINCT CATEGORY FROM TB_DEPARTMENT ORDER BY CATEGORY;

--10. 02�й� ���� �����ڵ��� ������ ������� �Ѵ�. ������ ������� ������ �������� �л����� �й�, �̸�, �ֹι�ȣ�� ����ϴ� ���� �ۼ�
SELECT STUDENT_NO, STUDENT_NAME, STUDENT_SSN, STUDENT_ADDRESS FROM TB_STUDENT WHERE NOT ABSENCE_YN = 'Y' AND STUDENT_ADDRESS LIKE '%���ֽ�%' AND ENTRANCE_DATE LIKE '02%';

