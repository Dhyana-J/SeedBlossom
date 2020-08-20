--1. PLAYER 테이블에서 K02, K05 팀에 해당하는 선수들의 이름과 포지션, 등번호, 키를 조회하시오.
SELECT PLAYER_NAME, POSITION, BACK_NO, HEIGHT
FROM PLAYER
WHERE TEAM_ID IN ('K02','K05');

--2. PLAYER 테이블에서 국적이 명시된 선수들의 이름과 국적을 조회하시오.
SELECT PLAYER_NAME, NATION
FROM PLAYER
WHERE NATION IS NOT NULL;

--3. PLAYER 테이블에서 팀ID가 K02이거나 K07인 선수들의 이름과 포지션, 등번호, 팀ID, 키를 조회하시오.
SELECT PLAYER_NAME, POSITION, BACK_NO, TEAM_ID, HEIGHT
FROM PLAYER
WHERE TEAM_ID IN ('K02','K07');

--4.TEAM 테이블에서 각 팀의 우편번호 두 개를 '-' 구분자로 합하여 팀ID와 우편번호 조합을 조회하시오.
SELECT TEAM_ID, ZIP_CODE1||'-'||ZIP_CODE2 우편번호
FROM TEAM;

--5.PLAYER 테이블에서 모든 선수들의 인원 수와 신장 크기가 등록된 선수의 수, 최대 신장과 최소 신장, 평균 신장의 정보를 조회하시오.
SELECT COUNT(*) "인원 수",
        COUNT(CASE WHEN HEIGHT IS NOT NULL THEN 1 END) "신장 등록된 선수",
        MAX(HEIGHT) "최대 신장",
        MIN(HEIGHT) "최소 신장",
        TRUNC(AVG(HEIGHT)) "평균 신장"
FROM PLAYER;

--6.PLAYER 테이블을 활용하여 각 팀 별 인원수를 조회하는 SQL을 작성하되 정렬은 인원 수 기준으로 내림차순 정렬하여 조회 하시오.
SELECT TEAM_ID, COUNT(*) "팀 별 인원 수"
FROM PLAYER
GROUP BY TEAM_ID
ORDER BY COUNT(*) DESC;

--7. PLAYER와 TEAM테이블을 활용해 각 선수의 이름과 소속팀명을 조회하시오
SELECT PLAYER_NAME, TEAM_NAME
FROM PLAYER P, TEAM T
WHERE P.TEAM_ID = T.TEAM_ID;

--8.PLAYER, TEAM, STADIUM 테이블을 활용하여 각 선수들의 정보 중
--선수명, 포지션, 출신지, 팀명, 홈경기장 명을 조회하시오.
SELECT PLAYER_NAME, POSITION, NATION, TEAM_NAME, STADIUM_NAME
FROM PLAYER
JOIN TEAM USING(TEAM_ID)
JOIN STADIUM USING(STADIUM_ID);

--9.TEAM, STADIUM 테이블을 활용하여 각 팀의 이름과 경기장ID, 경기장명을 조회하되
--경기장ID가 존재하는 팀만 조회 하고 결과는 경기장 명이 오름차순 정렬이 되도록 조회하시오
SELECT TEAM_NAME, STADIUM_ID, STADIUM_NAME
FROM TEAM
JOIN STADIUM USING(STADIUM_ID)
ORDER BY STADIUM_NAME ASC;

--10.PLAYER 테이블을 활용하여 신장 크기가 모든 선수의 신장 길이의 평균 이상인
--선수들의 선수명, 포지션, 등번호를 선수이름 기준 오름차순으로 조회하시오.
SELECT PLAYER_NAME, POSITION, BACK_NO
FROM PLAYER
WHERE HEIGHT>=(SELECT TRUNC(AVG(HEIGHT))
                FROM PLAYER)
ORDER BY HEIGHT ASC;

--11.선수 중 '정현수'라는 동명이인이 속한 팀의 한글 명칭과 영문 명칭, 소속 지역을 조회하시오.
SELECT TEAM_NAME, E_TEAM_NAME, REGION_NAME
FROM PLAYER
JOIN TEAM USING(TEAM_ID)
WHERE PLAYER_NAME='정현수';

--12.PLAYER 테이블에서 각 팀에 속한 선수들 중 소속된 팀의 평균 신장보다 신장 길이가 작은
--선수들의 팀명, 선수명, 포지션, 등번호, 신장 길이를 조회하시오.
SELECT PLAYER_NAME, POSITION, BACK_NO, HEIGHT
FROM PLAYER
WHERE HEIGHT<(SELECT TRUNC(AVG(HEIGHT))
                FROM PLAYER)
ORDER BY HEIGHT;

--13. 선수의 이름과 포지션, 등번호, 팀ID, 팀명을 조회하는 뷰(V_TEAM_PLAYER)를 하나 생성한 뒤
--생성한 뷰를 활용하여 '황'씨성을 가진 선수들의 정보를 조회하시오.
CREATE VIEW V_TEAM_PLAYER
AS SELECT PLAYER_NAME, POSITION, BACK_NO, TEAM_ID, TEAM_NAME
    FROM PLAYER
    JOIN TEAM USING(TEAM_ID);

SELECT * FROM V_TEAM_PLAYER
WHERE PLAYER_NAME LIKE('황%');

--14. 울산 현대 팀에 '박주호' 선수가 새로 영입되었다.
     --해당 선수의 정보 중 포지션은 DF이며 1987년 3월 16일생, 신장과 몸무게가 각각 176cm, 75kg으로 
     --나간다고 했을 때, 박주호 선수의 선수ID를 기존 선수들 중 가장 큰 숫자를 지닌 선수에서 
     --숫자를 하나 증가시켜 추가할 수 있는 쿼리를 작성하시오.
INSERT INTO PLAYER(PLAYER_ID,PLAYER_NAME,TEAM_ID,POSITION,BIRTH_DATE,HEIGHT,WEIGHT)
(SELECT PLAYER_ID+1,
        '박주호',
        'K01',
        'DF',
        '1987/03/16',
        176,
        75
FROM PLAYER
JOIN TEAM USING(TEAM_ID)
WHERE PLAYER_ID = (SELECT MAX(PLAYER_ID)
                    FROM PLAYER));

--15. SCHEDULE에 기록된 정보들 중 가장 높은 골이 기록된 경기들의 날짜와 경기장 명, HOME팀과 AWAY팀의 팀 명과 각 팀이 기록한 골의 수를 조회하시오.
--문제가 모호함. 가장 높은 골이 한 팀이 넣은 골 수를 말하는 건지 양팀의 골의 합이 높은건지 알 수 없다. 
--양팀 기준인 경우
SELECT SCHE_DATE,
        STADIUM_NAME,
        H.TEAM_NAME HOME팀,
        A.TEAM_NAME AWAY팀,
        HOME_SCORE,
        AWAY_SCORE
FROM SCHEDULE
JOIN STADIUM USING(STADIUM_ID)
JOIN TEAM H ON (H.TEAM_ID=SCHEDULE.HOMETEAM_ID)
JOIN TEAM A ON (A.TEAM_ID=SCHEDULE.AWAYTEAM_ID)
WHERE HOME_SCORE+AWAY_SCORE = (SELECT MAX(HOME_SCORE+AWAY_SCORE)
                                FROM SCHEDULE);

--한 팀 기준인 경우

--골 많이넣은 순서대로 내림차순 정렬
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

--ROWNUM을 사용하기위해 SELECT FROM 서브쿼리사용
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

--16. 최근 한국 스폰서들의 경제상황이 안 좋아져 팀 구단 중 현재 선수가 3명 이하인 구단을 정리하게 되었다. TEAM 테이블을 활용하여 현재 소속된 선수가 3명 이하인 구단을 찾아
--해당 데이터를 삭제하는 쿼리를 작성하시오.

DELETE FROM TEAM 
WHERE TEAM_ID 
IN(SELECT TEAM_ID
FROM PLAYER
GROUP BY TEAM_ID
HAVING COUNT(*)<=3);
--제약조건때매 안지워짐. 아래 구문 실행하여 외래키 비활성화
ALTER TABLE PLAYER
DISABLE CONSTRAINT PLAYER_FK CASCADE;
COMMIT;


