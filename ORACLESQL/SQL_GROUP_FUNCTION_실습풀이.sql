--9. �й��� A517178�� �ѾƸ� �л��� ���� �� ������ ���ϴ� SQL���� �ۼ��Ͻÿ�. 
--��, �� �� ��� ȭ���� ����� "����"�̶�� ������ �ϰ�, ������ �ݿø��Ͽ� �Ҽ��� ���� ���ڸ������� ǥ��.
SELECT ROUND(AVG(POINT),1) "����"
FROM TB_GRADE
WHERE STUDENT_NO = 'A517178';

--10. �а��� �л� ���� ���Ͽ� "�а���ȣ", "�л���(��)"�� ���·� ����� ����� ������� ��µǵ��� �Ͻÿ�.
SELECT DEPARTMENT_NO �а���ȣ,
    COUNT(*) "�л���(��)"
FROM TB_STUDENT
GROUP BY DEPARTMENT_NO
ORDER BY DEPARTMENT_NO ASC;

--11. ���������� �������� ���� �л��� ���� �� �� ���� �Ǵ��� �˾Ƴ���
SELECT COUNT(*)
FROM TB_STUDENT
GROUP BY COACH_PROFESSOR_NO
HAVING COACH_PROFESSOR_NO IS NULL;

--12. �й��� A112113�� ���� �л��� �⵵ �� ������ ���ϴ� SQL���� �ۼ��Ͻÿ�.
--��, �̶� ��� ȭ���� ����� "�⵵", "�⵵ �� ����"�̶�� ������ �ϰ�, ������ �ݿø��Ͽ� �Ҽ��� ���� ���ڸ�����.
SELECT SUBSTR(TERM_NO,1,4) AS "�⵵",
        ROUND(AVG(POINT),1) AS "�⵵ �� ����"
FROM TB_GRADE
WHERE STUDENT_NO = 'A112113'
GROUP BY SUBSTR(TERM_NO,1,4)
ORDER BY SUBSTR(TERM_NO,1,4) ASC;

--13. �а� �� ���л� ���� �ľ��ϰ��� �Ѵ�. �а� ��ȣȭ ���л� ���� ǥ���ϴ� SQL������ �ۼ��Ͻÿ�
SELECT DEPARTMENT_NO "�а��ڵ��",
       COUNT(DECODE(ABSENCE_YN,'Y',1,NULL)) "���л� ��"
FROM TB_STUDENT
GROUP BY DEPARTMENT_NO
ORDER BY DEPARTMENT_NO;

--���л� �� 0�� Ʃ�� ����������?...
-- COUNT(DECODE(ī��Ʈ��Į��,Į����,1,NULL))
-- COUNT ������ DECODE �ǹ� : ī��Ʈ��Į���� Ʃ���� 'Į����'�� ��ġ�ϸ� 1(COUNT����)
--                          ��ġ���� ������, NULL (COUNT���� ���� �Ƚ�Ŵ.)


--14. �� ���б��� �ٴϴ� �������� �л����� �̸��� ã��.
SELECT STUDENT_NAME "�����̸�",
        COUNT(STUDENT_NAME) "������ ��"
FROM TB_STUDENT
GROUP BY STUDENT_NAME
HAVING COUNT(STUDENT_NAME)>=2
ORDER BY STUDENT_NAME;

--15. �й��� A112113�� ���� �л��� �⵵, �б� �� ������ �⵵ �� ��������, ������ ����
SELECT SUBSTR(TERM_NO,1,4) "�⵵",
        SUBSTR(TERM_NO,5,2) "�б�",
        ROUND(AVG(POINT),1) "����"
FROM TB_GRADE
WHERE STUDENT_NO = 'A112113'
GROUP BY ROLLUP(SUBSTR(TERM_NO,1,4),SUBSTR(TERM_NO,5,2))
ORDER BY SUBSTR(TERM_NO,1,4);

--ROLLUP(A,B) : A�� B�� �������� ���� GROUP BY�� �����ϵ�, �� �׷��� ������ Ʃ�� ���� A�� ���� ��� ����� �ٿ��� ������ش�. ���� ������ Ʃ�ÿ��� ��ü �׷쿡 ���� ��� ��� ���.
--CUBE(A,B) : ť��� ROLLUP�� B������ ��� ����� ����ϰ�, ��ü �׷쿡 ���� ��� ����� ����Ѵ�. �׷��ϱ� ���ڷ� �� A,B�� ��� ������ ����ؼ� ����� ��ȯ�Ѵ�.
