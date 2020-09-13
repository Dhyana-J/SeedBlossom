--9. 학번이 A517178인 한아름 학생의 학점 총 평점을 구하는 SQL문을 작성하시오. 
--단, 이 때 출력 화면의 헤더는 "평점"이라고 찍히게 하고, 점수는 반올림하여 소수점 이하 한자리까지만 표시.
SELECT ROUND(AVG(POINT),1) "평점"
FROM TB_GRADE
WHERE STUDENT_NO = 'A517178';

--10. 학과별 학생 수를 구하여 "학과번호", "학생수(명)"의 형태로 헤더를 만들어 결과값이 출력되도록 하시오.
SELECT DEPARTMENT_NO 학과번호,
    COUNT(*) "학생수(명)"
FROM TB_STUDENT
GROUP BY DEPARTMENT_NO
ORDER BY DEPARTMENT_NO ASC;

--11. 지도교수를 배정받지 못한 학생의 수는 몇 명 정도 되는지 알아내렴
SELECT COUNT(*)
FROM TB_STUDENT
GROUP BY COACH_PROFESSOR_NO
HAVING COACH_PROFESSOR_NO IS NULL;

--12. 학번이 A112113인 김고운 학생의 년도 별 평점을 구하는 SQL문을 작성하시오.
--단, 이때 출력 화면의 헤더는 "년도", "년도 별 평점"이라고 찍히게 하고, 점수는 반올림하여 소수점 이하 한자리까지.
SELECT SUBSTR(TERM_NO,1,4) AS "년도",
        ROUND(AVG(POINT),1) AS "년도 별 평점"
FROM TB_GRADE
WHERE STUDENT_NO = 'A112113'
GROUP BY SUBSTR(TERM_NO,1,4)
ORDER BY SUBSTR(TERM_NO,1,4) ASC;

--13. 학과 별 휴학생 수를 파악하고자 한다. 학과 번호화 휴학생 수를 표시하는 SQL문장을 작성하시오
SELECT DEPARTMENT_NO "학과코드명",
       COUNT(DECODE(ABSENCE_YN,'Y',1,NULL)) "휴학생 수"
FROM TB_STUDENT
GROUP BY DEPARTMENT_NO
ORDER BY DEPARTMENT_NO;

--휴학생 수 0인 튜플 어떻게출력하지?...
-- COUNT(DECODE(카운트할칼럼,칼럼값,1,NULL))
-- COUNT 내부의 DECODE 의미 : 카운트할칼럼의 튜플이 '칼럼값'과 일치하면 1(COUNT실행)
--                          일치하지 않으면, NULL (COUNT갯수 증가 안시킴.)


--14. 춘 대학교에 다니는 동명이인 학생들의 이름을 찾자.
SELECT STUDENT_NAME "동일이름",
        COUNT(STUDENT_NAME) "동명인 수"
FROM TB_STUDENT
GROUP BY STUDENT_NAME
HAVING COUNT(STUDENT_NAME)>=2
ORDER BY STUDENT_NAME;

--15. 학번이 A112113인 김고운 학생의 년도, 학기 별 평점과 년도 별 누적평점, 총평점 구해
SELECT SUBSTR(TERM_NO,1,4) "년도",
        SUBSTR(TERM_NO,5,2) "학기",
        ROUND(AVG(POINT),1) "평점"
FROM TB_GRADE
WHERE STUDENT_NO = 'A112113'
GROUP BY ROLLUP(SUBSTR(TERM_NO,1,4),SUBSTR(TERM_NO,5,2))
ORDER BY SUBSTR(TERM_NO,1,4);

--ROLLUP(A,B) : A와 B를 기준으로 묶어 GROUP BY를 수행하되, 각 그룹의 마지막 튜플 끝에 A에 대한 계산 결과를 붙여서 출력해준다. 이후 마지막 튜플에는 전체 그룹에 대한 계산 결과 출력.
--CUBE(A,B) : 큐브는 ROLLUP에 B에대한 계산 결과도 출력하고, 전체 그룹에 대한 계산 결과도 출력한다. 그러니까 인자로 들어간 A,B의 모든 조합을 고려해서 결과를 반환한다.
