-- 1. 직급이 대리이면서 ASIA지역에 근무하는 직원들의
--    사번, 사원명, 직급명, 부서명, 근무지역명, 급여를 조회하시오
--ORACLE
SELECT E.EMP_NO 사번, --EMPLOYEE
        E.EMP_NAME 사원명,--EMPLOYEE
        D.DEPT_TITLE 부서명,--DEPARTMENT
        L.LOCAL_NAME 근무지역명,--LOCATION
        E.SALARY 급여--EMPLOYEE
FROM EMPLOYEE E,
        JOB J, -- 직급명, JOB_NAME 필요. 
        DEPARTMENT D, --직급이 대리, DEPT_TITLE 필요
        LOCATION L -- ASIA지역 근무, LOCAL_NAME 필요 
WHERE E.DEPT_CODE=D.DEPT_ID --DEPT_TITLE 가져와야함
        AND J.JOB_NAME = '대리' 
        AND E.JOB_CODE = J.JOB_CODE --JOB_NAME 가져와야함
        AND D.LOCATION_ID=L.LOCAL_CODE --LOCAL_NAME 가져와야함
        AND L.LOCAL_NAME LIKE ('ASIA%');
--ANSI
SELECT E.EMP_NO 사번, --EMPLOYEE
        E.EMP_NAME 사원명,--EMPLOYEE
        D.DEPT_TITLE 부서명,--DEPARTMENT
        L.LOCAL_NAME 근무지역명,--LOCATION
        E.SALARY 급여--EMPLOYEE
FROM EMPLOYEE E
JOIN DEPARTMENT D ON (E.DEPT_CODE=D.DEPT_ID)
JOIN LOCATION L ON D.LOCATION_ID=L.LOCAL_CODE AND LOCAL_NAME LIKE 'ASIA%'
JOIN JOB J ON E.JOB_CODE=J.JOB_CODE AND JOB_NAME='대리';


-- 2. 70년대생이면서 여자이고, 성이 전씨인 직원들의
--    사원명, 주민번호, 부서명, 직급명을 조회하시오
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
    AND EMP_NO LIKE ('7%') --70년대생
    AND SUBSTR(EMP_NO,8,1)=2 --주민번호 뒷자리 시작이 2 (여자)
    AND EMP_NAME LIKE ('전%');
--ANSI
SELECT EMP_NAME, 
        EMP_NO, 
        DEPT_TITLE, 
        JOB_NAME
FROM EMPLOYEE
JOIN JOB USING(JOB_CODE)
JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID)
WHERE EMP_NO LIKE ('7%') --70년대생
    AND SUBSTR(EMP_NO,8,1)=2 --주민번호 뒷자리 시작이 2 (여자)
    AND EMP_NAME LIKE ('전%');


-- 3. 이름에 '형'자가 들어있는 직원들의
--    사번, 사원명, 직급명을 조회하시오
--ORACLE
SELECT EMP_ID,
        EMP_NAME,
        JOB_NAME
FROM EMPLOYEE E, JOB J
WHERE E.JOB_CODE=J.JOB_CODE
    AND EMP_NAME LIKE '%형%';
--ANSI
SELECT EMP_ID,
        EMP_NAME,
        JOB_NAME
FROM EMPLOYEE
JOIN JOB USING(JOB_CODE)
WHERE EMP_NAME LIKE '%형%';


-- 4. 해외영업팀에 근무하는 직원들의
--    사원명, 직급명, 부서코드, 부서명을 조회하시오
--ORACLE
SELECT EMP_NAME,
        JOB_NAME, -- E.JOB_CODE=J.JOB_CODE 필요
        DEPT_CODE,
        DEPT_TITLE -- DEPT_CODE=DEPT_ID 필요
FROM EMPLOYEE E,
    DEPARTMENT,
    JOB J
WHERE E.JOB_CODE=J.JOB_CODE
    AND DEPT_CODE=DEPT_ID
    AND DEPT_TITLE LIKE '해외영업%';
--ANSI
SELECT EMP_NAME,
        JOB_NAME, -- E.JOB_CODE=J.JOB_CODE 필요
        DEPT_CODE,
        DEPT_TITLE -- DEPT_CODE=DEPT_ID 필요
FROM EMPLOYEE
JOIN JOB USING(JOB_CODE)
JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID)
WHERE DEPT_TITLE LIKE '해외영업%';

-- 5. 보너스를 받는 직원들의
--    사원명, 보너스, 연봉, 부서명, 근무지역명을 조회하시오
--ORACLE
SELECT EMP_NAME,
        BONUS,
        SALARY*12 연봉,
        DEPT_TITLE, -- DEPT_CODE=DEPT_ID
        LOCAL_NAME -- LOCATION_ID = LOCAL_CODE
FROM EMPLOYEE,
    DEPARTMENT,
    LOCATION
WHERE DEPT_CODE=DEPT_ID(+) --DEPARTMENT는 EMPLOYEE에 도킹한다.
    AND LOCATION_ID=LOCAL_CODE(+) --LOCATION은 DEPARTMENT에 도킹한다. 
    AND BONUS IS NOT NULL;
--하동운이가 안보인다..! (+)를 추가해주자! 추가할때는 흐름을 고려해서 작성해주자.
--지금은 튜플이 적기때문에 눈으로 뭐가 잘못되었는지 확인이 가능하다. 근데 튜플이 1억개면 내가 뭘 잘못했는지 검증 어떻게할까..?
--ANSI
SELECT EMP_NAME,
        BONUS,
        SALARY*12 연봉,
        DEPT_TITLE, -- DEPT_CODE=DEPT_ID
        LOCAL_NAME -- LOCATION_ID = LOCAL_CODE
FROM EMPLOYEE
LEFT JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID)
LEFT JOIN LOCATION ON (LOCATION_ID=LOCAL_CODE)
WHERE BONUS IS NOT NULL;

-- 6. 부서가 있는 직원들의
--    사원명, 직급명, 부서명, 근무지역명을 조회하시오
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


-- 7. '한국'과 '일본'에 근무하는 직원들의 
--    사원명, 부서명, 근무지역명, 근무국가명을 조회하시오
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

-- 8. 보너스를 받지 않는 직원들 중 직급코드가 J4 또는 J7인 직원들의
--    사원명, 직급명, 급여를 조회하시오
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

-- 9. 사번, 사원명, 직급명, 급여등급, 구분을 조회하는데
--    이때 구분에 해당하는 값은
--    급여등급이 S1, S2인 경우 '고급'
--    급여등급이 S3, S4인 경우 '중급'
--    급여등급이 S5, S6인 경우 '초급' 으로 조회되게 하시오.
--ORACLE
SELECT EMP_ID,
        EMP_NAME,
        JOB_NAME, -- JOB_CODE=JOB_CODE
        SAL_LEVEL 급여등급, --NONEQU JOIN
        CASE WHEN SAL_LEVEL = 'S1'
                OR SAL_LEVEL = 'S2'
                THEN '고급'
            WHEN SAL_LEVEL = 'S3'
                OR SAL_LEVEL = 'S4'
                THEN '중급'
            WHEN SAL_LEVEL = 'S5'
                OR SAL_LEVEL = 'S6'
                THEN '초급'
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
        SAL_LEVEL 급여등급, --NONEQU JOIN
        CASE WHEN SAL_LEVEL = 'S1'
                OR SAL_LEVEL = 'S2'
                THEN '고급'
            WHEN SAL_LEVEL = 'S3'
                OR SAL_LEVEL = 'S4'
                THEN '중급'
            WHEN SAL_LEVEL = 'S5'
                OR SAL_LEVEL = 'S6'
                THEN '초급'
            END
FROM EMPLOYEE
JOIN JOB USING(JOB_CODE)
JOIN SAL_GRADE ON (SALARY BETWEEN MIN_SAL AND MAX_SAL);
   
-- 10. 각 부서별 총 급여합을 조회하되
--     이때, 총 급여합이 1000만원 이상인 부서명, 급여합을 조회하시오
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


-- 11. 각 부서별 평균급여를 조회하여 부서명, 평균급여 (정수처리)로 조회하시오.
--      단, 부서배치가 안된 사원들의 평균도 같이 나오게끔 하시오.
--ORACLE
SELECT DEPT_TITLE,
        TRUNC(AVG(SALARY)) 평균급여
FROM EMPLOYEE,
        DEPARTMENT
WHERE DEPT_CODE=DEPT_ID(+)
GROUP BY (DEPT_TITLE);
--ANSI
SELECT DEPT_TITLE,
        TRUNC(AVG(SALARY)) 평균급여
FROM EMPLOYEE
LEFT JOIN DEPARTMENT ON (DEPT_CODE=DEPT_ID)
GROUP BY (DEPT_TITLE);