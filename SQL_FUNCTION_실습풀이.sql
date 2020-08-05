--1. 영어영문학과(학과코드 002)학생들의 학번과 이름, 입학년도를 입학년도가 빠른순으로 표시하는 SQL문장을 작성하시오
SELECT STUDENT_NO AS "학번",
    STUDENT_NAME AS "이름",
    TO_CHAR(ENTRANCE_DATE,'YYYY-MM-DD') AS "입학년도"
FROM TB_STUDENT 
WHERE DEPARTMENT_NO = '002'
ORDER BY ENTRANCE_DATE;

--2. 춘기술대학교의 교수 중 이름이 세 글자가 아닌 교수가 한 명 있다고 한다. 그 교수의 이름과 주민번호를 화면에 출력하자.
SELECT PROFESSOR_NAME, PROFESSOR_SSN 
FROM TB_PROFESSOR
WHERE NOT LENGTH(PROFESSOR_NAME) = '3';

--3.춘기술대학교의 남자 교수들의 이름과 나이를 출력하는 SQL문장을 작성하시오.
--단, 이 때 나이가 적은 사람에게서 많은 사람 순서로 화면에 출력되도록 만드시오.
--교수 중 2000년 이후 출생자는 없으며 나이는 '만'으로 계산한다.
SELECT
    PROFESSOR_NAME AS "남자교수이름",
    PROFESSOR_SSN AS "주민번호",
    FLOOR(TO_NUMBER(MONTHS_BETWEEN(SYSDATE,TO_DATE(CONCAT('19',SUBSTR(PROFESSOR_SSN, 1,6)),'YYYYMMDD'))/12)) AS "만나이"
FROM TB_PROFESSOR
WHERE SUBSTR(PROFESSOR_SSN,8,1)=1
ORDER BY 2;

--4.교수들의 이름 중 성을 제외한 이름만 출력하는 SQL문장을 작성하시오. 출력 헤더는 "이름"
SELECT SUBSTR(PROFESSOR_NAME,2,2) 
FROM TB_PROFESSOR;

--5.춘기술대학교의 재수생 입학자를 구하려고 한다. 어떻게 찾아낼 것인가? 이 때, 19살에 입학하면 재수를 하지 않은것으로 간주
 --학번 앞에 A붙으면 2000년도 입학자인듯.
SELECT STUDENT_NO, STUDENT_NAME
FROM TB_STUDENT
-- 입학년도 - 출생년도 = 19이면 재수를 안한 것이다.
WHERE 
CASE
    WHEN SUBSTR(STUDENT_NO,1,1)='A' THEN TO_NUMBER(CONCAT('200',SUBSTR(STUDENT_NO,2,1)))-TO_NUMBER(CONCAT('19',SUBSTR(STUDENT_SSN,1,2)))
    ELSE TO_NUMBER(SUBSTR(STUDENT_NO,1,2))-TO_NUMBER(SUBSTR(STUDENT_SSN,1,2))
END
= 19;

--6. 2020년 크리스마스는 무슨 요일인가?
SELECT TO_CHAR(TO_DATE('2020/12/25'),'DAY') FROM DUAL;

--7. TO_DATE('99/10/11', 'YY/MM/DD'), TO_DATE('49/10/11', 'YY/MM/DD')은 각각 몇 년 몇 월 몇 일을 의미할까?
-- 또 TO_DATE('99/10/11', 'RR/MM/DD'), TO_DATE('49/10/11','RR/MM/DD')은 각각 몇년 몇월 몇일을 의미할까?
--TO_DATE('99/10/11', 'YY/MM/DD') -> 2099/10/11
--TO_DATE('49/10/11', 'YY/MM/DD') -> 2049/10/11
--TO_DATE('99/10/11', 'RR/MM/DD') -> 1999/10/11
--TO_DATE('49/10/11','RR/MM/DD')  -> 2049/10/11
-- YY : 모두 2000년대로 인식. 
-- RR : 현재 년도의 뒤 두자리가 00~49인데 입력년도가 00~49이면 현재세기.
--      현재 년도의 뒤 두자리가 00~49인데 입력년도가 50~99이면 이전세기.(1900년대)
--      현재 년도의 뒤 두자리가 50~99인데 입력년도가 00~49이면 다음세기.(2100년대)
--      현재 년도의 뒤 두자리가 50~99인데 입력년도가 50~99이면 현재세기.(2000년대)
SELECT TO_CHAR(TO_DATE('99/10/11','YY/MM/DD'),'YYYY-MM-DD') AS "1",
    TO_CHAR(TO_DATE('49/10/11', 'YY/MM/DD'),'YYYY-MM-DD') AS "2",
    TO_CHAR(TO_DATE('99/10/11', 'RR/MM/DD'),'YYYY-MM-DD') AS "3",
    TO_CHAR(TO_DATE('49/10/11','RR/MM/DD'),'YYYY-MM-DD') AS "4"
FROM DUAL;

--8.춘기술대학교의 2000년도 이후 입학자들은 학번이 A로 시작하게되어있다. 
-- 2000년도 이전 학번을 받은 학생들의 학번과 이름을 보여주는 SQL문장을 작성하시오.
SELECT STUDENT_NO, STUDENT_NAME 
FROM TB_STUDENT
WHERE NOT SUBSTR(STUDENT_NO,1,1)='A';
