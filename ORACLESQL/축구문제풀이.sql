--1. PLAYER ���̺��� K02, K05 ���� �ش��ϴ� �������� �̸��� ������, ���ȣ, Ű�� ��ȸ�Ͻÿ�.
SELECT PLAYER_NAME, POSITION, BACK_NO, HEIGHT
FROM PLAYER
WHERE TEAM_ID IN ('K02','K05');

--2. PLAYER ���̺��� ������ ��õ� �������� �̸��� ������ ��ȸ�Ͻÿ�.
SELECT PLAYER_NAME, NATION
FROM PLAYER
WHERE NATION IS NOT NULL;

--3. PLAYER ���̺��� ��ID�� K02�̰ų� K07�� �������� �̸��� ������, ���ȣ, ��ID, Ű�� ��ȸ�Ͻÿ�.
SELECT PLAYER_NAME, POSITION, BACK_NO, TEAM_ID, HEIGHT
FROM PLAYER
WHERE TEAM_ID IN ('K02','K07');

--4.TEAM ���̺��� �� ���� �����ȣ �� ���� '-' �����ڷ� ���Ͽ� ��ID�� �����ȣ ������ ��ȸ�Ͻÿ�.
SELECT TEAM_ID, ZIP_CODE1||'-'||ZIP_CODE2 �����ȣ
FROM TEAM;

--5.PLAYER ���̺��� ��� �������� �ο� ���� ���� ũ�Ⱑ ��ϵ� ������ ��, �ִ� ����� �ּ� ����, ��� ������ ������ ��ȸ�Ͻÿ�.
SELECT COUNT(*) "�ο� ��",
        COUNT(CASE WHEN HEIGHT IS NOT NULL THEN 1 END) "���� ��ϵ� ����",
        MAX(HEIGHT) "�ִ� ����",
        MIN(HEIGHT) "�ּ� ����",
        TRUNC(AVG(HEIGHT)) "��� ����"
FROM PLAYER;

--6.PLAYER ���̺��� Ȱ���Ͽ� �� �� �� �ο����� ��ȸ�ϴ� SQL�� �ۼ��ϵ� ������ �ο� �� �������� �������� �����Ͽ� ��ȸ �Ͻÿ�.
SELECT TEAM_ID, COUNT(*) "�� �� �ο� ��"
FROM PLAYER
GROUP BY TEAM_ID
ORDER BY COUNT(*) DESC;

--7. PLAYER�� TEAM���̺��� Ȱ���� �� ������ �̸��� �Ҽ������� ��ȸ�Ͻÿ�
SELECT PLAYER_NAME, TEAM_NAME
FROM PLAYER P, TEAM T
WHERE P.TEAM_ID = T.TEAM_ID;

--8.PLAYER, TEAM, STADIUM ���̺��� Ȱ���Ͽ� �� �������� ���� ��
--������, ������, �����, ����, Ȩ����� ���� ��ȸ�Ͻÿ�.
SELECT PLAYER_NAME, POSITION, NATION, TEAM_NAME, STADIUM_NAME
FROM PLAYER
JOIN TEAM USING(TEAM_ID)
JOIN STADIUM USING(STADIUM_ID);

--9.TEAM, STADIUM ���̺��� Ȱ���Ͽ� �� ���� �̸��� �����ID, �������� ��ȸ�ϵ�
--�����ID�� �����ϴ� ���� ��ȸ �ϰ� ����� ����� ���� �������� ������ �ǵ��� ��ȸ�Ͻÿ�
SELECT TEAM_NAME, STADIUM_ID, STADIUM_NAME
FROM TEAM
JOIN STADIUM USING(STADIUM_ID)
ORDER BY STADIUM_NAME ASC;

--10.PLAYER ���̺��� Ȱ���Ͽ� ���� ũ�Ⱑ ��� ������ ���� ������ ��� �̻���
--�������� ������, ������, ���ȣ�� �����̸� ���� ������������ ��ȸ�Ͻÿ�.
SELECT PLAYER_NAME, POSITION, BACK_NO
FROM PLAYER
WHERE HEIGHT>=(SELECT TRUNC(AVG(HEIGHT))
                FROM PLAYER)
ORDER BY HEIGHT ASC;

--11.���� �� '������'��� ���������� ���� ���� �ѱ� ��Ī�� ���� ��Ī, �Ҽ� ������ ��ȸ�Ͻÿ�.
SELECT TEAM_NAME, E_TEAM_NAME, REGION_NAME
FROM PLAYER
JOIN TEAM USING(TEAM_ID)
WHERE PLAYER_NAME='������';

--12.PLAYER ���̺��� �� ���� ���� ������ �� �Ҽӵ� ���� ��� ���庸�� ���� ���̰� ����
--�������� ����, ������, ������, ���ȣ, ���� ���̸� ��ȸ�Ͻÿ�.
SELECT PLAYER_NAME, POSITION, BACK_NO, HEIGHT
FROM PLAYER
WHERE HEIGHT<(SELECT TRUNC(AVG(HEIGHT))
                FROM PLAYER)
ORDER BY HEIGHT;

--13. ������ �̸��� ������, ���ȣ, ��ID, ������ ��ȸ�ϴ� ��(V_TEAM_PLAYER)�� �ϳ� ������ ��
--������ �並 Ȱ���Ͽ� 'Ȳ'������ ���� �������� ������ ��ȸ�Ͻÿ�.
CREATE VIEW V_TEAM_PLAYER
AS SELECT PLAYER_NAME, POSITION, BACK_NO, TEAM_ID, TEAM_NAME
    FROM PLAYER
    JOIN TEAM USING(TEAM_ID);

SELECT * FROM V_TEAM_PLAYER
WHERE PLAYER_NAME LIKE('Ȳ%');

--14. ��� ���� ���� '����ȣ' ������ ���� ���ԵǾ���.
     --�ش� ������ ���� �� �������� DF�̸� 1987�� 3�� 16�ϻ�, ����� �����԰� ���� 176cm, 75kg���� 
     --�����ٰ� ���� ��, ����ȣ ������ ����ID�� ���� ������ �� ���� ū ���ڸ� ���� �������� 
     --���ڸ� �ϳ� �������� �߰��� �� �ִ� ������ �ۼ��Ͻÿ�.
INSERT INTO PLAYER(PLAYER_ID,PLAYER_NAME,TEAM_ID,POSITION,BIRTH_DATE,HEIGHT,WEIGHT)
(SELECT PLAYER_ID+1,
        '����ȣ',
        'K01',
        'DF',
        '1987/03/16',
        176,
        75
FROM PLAYER
JOIN TEAM USING(TEAM_ID)
WHERE PLAYER_ID = (SELECT MAX(PLAYER_ID)
                    FROM PLAYER));

--15. SCHEDULE�� ��ϵ� ������ �� ���� ���� ���� ��ϵ� ������ ��¥�� ����� ��, HOME���� AWAY���� �� ��� �� ���� ����� ���� ���� ��ȸ�Ͻÿ�.
--������ ��ȣ��. ���� ���� ���� �� ���� ���� �� ���� ���ϴ� ���� ������ ���� ���� �������� �� �� ����. 
--���� ������ ���
SELECT SCHE_DATE,
        STADIUM_NAME,
        H.TEAM_NAME HOME��,
        A.TEAM_NAME AWAY��,
        HOME_SCORE,
        AWAY_SCORE
FROM SCHEDULE
JOIN STADIUM USING(STADIUM_ID)
JOIN TEAM H ON (H.TEAM_ID=SCHEDULE.HOMETEAM_ID)
JOIN TEAM A ON (A.TEAM_ID=SCHEDULE.AWAYTEAM_ID)
WHERE HOME_SCORE+AWAY_SCORE = (SELECT MAX(HOME_SCORE+AWAY_SCORE)
                                FROM SCHEDULE);

--�� �� ������ ���

--�� ���̳��� ������� �������� ����
SELECT SCHE_DATE, 
        STADIUM_NAME,
        H.TEAM_NAME,
        A.TEAM_NAME,
        HOME_SCORE,
        AWAY_SCORE
FROM SCHEDULE
JOIN STADIUM USING (STADIUM_ID)
JOIN TEAM H ON (H.TEAM_ID=SCHEDULE.HOMETEAM_ID)
JOIN TEAM A ON (A.TEAM_ID=AWAYTEAM_ID)
WHERE HOME_SCORE IS NOT NULL AND
        AWAY_SCORE IS NOT NULL
ORDER BY GREATEST(HOME_SCORE,AWAY_SCORE) DESC;

--ROWNUM�� ����ϱ����� SELECT FROM �����������
SELECT *--SCHE_DATE, STADIUM_NAME, HOMETEAM_NAME,AWAYTEAM_NAME,HOME_SCORE,AWAY_SCORE
FROM (SELECT SCHE_DATE, 
        STADIUM_NAME,
        H.TEAM_NAME HOMETEAM_NAME,
        A.TEAM_NAME AWAYTEAM_NAME,
        HOME_SCORE,
        AWAY_SCORE
    FROM SCHEDULE
    JOIN STADIUM USING (STADIUM_ID)
    JOIN TEAM H ON (H.TEAM_ID=SCHEDULE.HOMETEAM_ID)
    JOIN TEAM A ON (A.TEAM_ID=AWAYTEAM_ID)
    WHERE HOME_SCORE IS NOT NULL AND
        AWAY_SCORE IS NOT NULL
    ORDER BY GREATEST(HOME_SCORE,AWAY_SCORE) DESC
) R
WHERE ROWNUM = 1;

--16. �ֱ� �ѱ� ���������� ������Ȳ�� �� ������ �� ���� �� ���� ������ 3�� ������ ������ �����ϰ� �Ǿ���. TEAM ���̺��� Ȱ���Ͽ� ���� �Ҽӵ� ������ 3�� ������ ������ ã��
--�ش� �����͸� �����ϴ� ������ �ۼ��Ͻÿ�.

DELETE FROM TEAM 
WHERE TEAM_ID 
IN(SELECT TEAM_ID
FROM PLAYER
GROUP BY TEAM_ID
HAVING COUNT(*)<=3);
--�������Ƕ��� ��������. �Ʒ� ���� �����Ͽ� �ܷ�Ű ��Ȱ��ȭ
ALTER TABLE PLAYER
DISABLE CONSTRAINT PLAYER_FK CASCADE;
COMMIT;


