--DML 춘대학실습문제풀이

--1. 과목유형 테이블(TB_CLASS_TYPE)에 아래와 가은 데이터 입력하시오.
INSERT INTO TB_CLASS_TYPE VALUES ('01','전공필수');
INSERT INTO TB_CLASS_TYPE VALUES ('02','전공선택');
INSERT INTO TB_CLASS_TYPE VALUES ('03','교양필수');
INSERT INTO TB_CLASS_TYPE VALUES ('04','교양선택');
INSERT INTO TB_CLASS_TYPE VALUES ('05','논문지도');

--2. 춘 기술대학교 학생들의 정보가 포함되어있는 학생일반정보 테이블을 만들고자한다.
CREATE TABLE TB_STUDENT_INFO(
    STUDENT_NO VARCHAR2(10),
    STUDENT_NAME VARCHAR2(40),
    STUDENT_ADDRESS VARCHAR2(200)
);

INSERT INTO TB_STUDENT_INFO(
    SELECT STUDENT_NO,
            STUDENT_NAME,
            STUDENT_ADDRESS
    FROM TB_STUDENT
);


SELECT * FROM TB_STUDENT_INFO;

--3. 국어국문학과 학생들의 정보만이 포함되어 있는 학과정보 테이블을 만들고자 한다.
CREATE TABLE TB_DEPT_KOR_LANG(
    STUDENT_NO VARCHAR2(10),
    STUDENT_NAME VARCHAR2(40),
    STUDENT_SSN VARCHAR2(10),
    PROFESSOR_NAME VARCHAR2(40)
);

INSERT INTO TB_DEPT_KOR_LANG(
    SELECT STUDENT_NO,
            STUDENT_NAME,
            CONCAT(19,SUBSTR(STUDENT_SSN,0,2)),
            PROFESSOR_NAME
    FROM TB_STUDENT
    JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
    LEFT JOIN TB_PROFESSOR ON (COACH_PROFESSOR_NO=PROFESSOR_NO)
    WHERE DEPARTMENT_NAME LIKE('국어국문%')
);

SELECT * FROM TB_DEPT_KOR_LANG;

--4.현 학과들의 정원을 10%증가시키게 되었다. 이에 사용할 SQL문 작성하자.
UPDATE TB_DEPARTMENT SET CAPACITY = ROUND(CAPACITY+CAPACITY*0.1);
SELECT * FROM TB_DEPARTMENT;

--5. 학번 A413042인 박건우 학생의 주소가 "서울시 종로구 숭인동 181-21 "로 변경되었다고한다.
--주소지를 정정하기 위해 사용할 SQL 작성하자
UPDATE TB_STUDENT
SET STUDENT_ADDRESS = '서울시 종로구 숭인동 181-21 '
WHERE STUDENT_NO = 'A413042';

SELECT * FROM TB_STUDENT WHERE STUDENT_NO = 'A413042';

--6. 주민등록번호 보호법에 따라 학생정보 테이블에서 주민번호 뒷자리를 저장하지 않기로했다.
UPDATE TB_STUDENT
SET STUDENT_SSN = SUBSTR(STUDENT_SSN,1,6);

SELECT STUDENT_SSN FROM TB_STUDENT;

--7. 의학과 김명훈 학생은 2005년 1학기에 자신이 수강한 '피부생리학'점수가 잘못되었다는 것을 발견,
--정정요청했다. 해당 과목 학점을 3.5로 변경키로 결정되었다.
UPDATE TB_GRADE
SET POINT = 3.5
WHERE(STUDENT_NO, TERM_NO, CLASS_NO, POINT)IN
    (SELECT STUDENT_NO, TERM_NO, CLASS_NO, POINT--,CLASS_NAME
        FROM TB_GRADE
        JOIN TB_STUDENT USING(STUDENT_NO)
        JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
        JOIN TB_CLASS USING(CLASS_NO)
        WHERE STUDENT_NAME='김명훈' AND
                DEPARTMENT_NAME='의학과' AND
                TERM_NO = '200501' AND
                CLASS_NAME = '피부생리학');
                
COMMIT;

--8. 성적 테이블(TB_GRADE)에서 휴학생들의 성적항목을 제거하시오.
UPDATE TB_GRADE
SET POINT = NULL
WHERE STUDENT_NO IN (SELECT DISTINCT STUDENT_NO
                        FROM TB_GRADE
                        JOIN TB_STUDENT USING(STUDENT_NO)
                        WHERE ABSENCE_YN = 'Y');

COMMIT;

                
                





