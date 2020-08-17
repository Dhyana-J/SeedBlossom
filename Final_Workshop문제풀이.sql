/*
실습문제풀이
*/

--1. 도서명이 25자 이상인 책 번호와 도서명을 화면에 출력하는 SQL문 작성
SELECT BOOK_NO,
        BOOK_NM
FROM TB_BOOK 
WHERE LENGTH(BOOK_NM)>=25;


--2. 휴대폰 번호가 019로 시작하는 김씨 성을 가진 작가를 이름순으로 정렬했을 때, 가장 먼저 표시되는
--작가 이름과 사무실 전화번호, 집전화번호, 휴대폰전화번호를 표시하는 SQL구문 작성
SELECT * 
FROM (SELECT WRITER_NM, OFFICE_TELNO, HOME_TELNO, MOBILE_NO
        FROM TB_WRITER
        WHERE MOBILE_NO LIKE('019%') AND
            WRITER_NM LIKE('김%')
        ORDER BY WRITER_NM)
WHERE ROWNUM = 1;

--3. 저작 형태가 "옮김"에 해당하는 작가들이 총 몇 명인지 계산하는 SQL구문 작성하시오.
--(결과 헤더는 "작가(명)"으로 표시되도록 할 것
SELECT COUNT(COMPOSE_TYPE) AS "작가(명)"
FROM TB_BOOK_AUTHOR
WHERE COMPOSE_TYPE = '옮김';

--4. 300페이지 이상 등록된 도서의 저작 형태 및 등록된 도서 수량을 표시하는 SQL구문을 작성하시오.
--(저작 형태가 등록되지 않은 경우는 제외할 것)
SELECT BOOK_NM,COMPOSE_TYPE, STOCK_QTY
FROM TB_BOOK_AUTHOR
JOIN TB_BOOK USING(BOOK_NO)
WHERE COMPOSE_TYPE IS NOT NULL AND
    PAGE>=300;

--5. 가장 최근에 발간된 최신작 이름과 발행일자, 출판사 이름을 표시하는 SQL구문 작성
SELECT * 
FROM(SELECT BOOK_NM, ISSUE_DATE, PUBLISHER_NM
    FROM TB_BOOK
    ORDER BY ISSUE_DATE DESC)
WHERE ROWNUM = 1;

--6. 가장 많은 책을 쓴 작가 3명의 이름과 수량을 표시하되, 많이 쓴 순서대로 표시하는 SQL구문 작성
--단, 동명이인 작가는 없다고 가정. (결과 헤더는 작가 이름, 권 수로 표시되도록 하자)
SELECT "작가 이름", "권 수"
FROM
(SELECT WRITER_NM "작가 이름",
        COUNT(WRITER_NO) "권 수",
        DENSE_RANK() OVER(ORDER BY COUNT(WRITER_NO) DESC) AS "순위"
FROM TB_WRITER
JOIN TB_BOOK_AUTHOR USING(WRITER_NO)
GROUP BY WRITER_NM,WRITER_NO
ORDER BY COUNT(WRITER_NO) DESC)
WHERE 순위 <=3;

--ROWNUM사용
SELECT *
FROM
    (SELECT WRITER_NM "작가 이름",
        COUNT(WRITER_NO) "권 수"
        FROM TB_WRITER
        JOIN TB_BOOK_AUTHOR USING(WRITER_NO)
        GROUP BY WRITER_NM,WRITER_NO
        ORDER BY COUNT(WRITER_NO) DESC)
WHERE ROWNUM IN (1,2,3);

--7. 작가 정보 테이블의 모든 등록일자 항목이 누락되어 있는 것을 발견했다. 누락된 등록일자 값을
--각 작가의 최초 출판도서의 발행일과 동일한 날짜로 변경시켜라. COMMIT처리해라.
--작가의 최초 출판도서 발행일
-- 이 테이블의 WRITER_NO랑 그 테이블의 WRITER_NO가 같은 것에 한해 ISSUE_DATE변경시키자
-- 그룹별 랭크매겨야할듯

--최초 출판도서 발행일 테이블화
CREATE TABLE TB_REGIST_DATE AS
WITH RESULT AS (SELECT WRITER_NM,
        ISSUE_DATE,
        DENSE_RANK() 
            OVER(PARTITION BY WRITER_NM ORDER BY WRITER_NM,ISSUE_DATE) AS RANK
FROM TB_BOOK
JOIN TB_BOOK_AUTHOR USING(BOOK_NO)
JOIN TB_WRITER USING(WRITER_NO)
GROUP BY WRITER_NM,ISSUE_DATE
ORDER BY WRITER_NM,ISSUE_DATE ASC)
SELECT WRITER_NM, ISSUE_DATE
FROM RESULT
WHERE RANK = 1;

--등록일자 항목에 각 작가의 최초 출판도서 발행일을 병합시키자.
MERGE INTO TB_WRITER USING TB_REGIST_DATE ON(TB_WRITER.WRITER_NM=TB_REGIST_DATE.WRITER_NM)
WHEN MATCHED THEN
UPDATE SET TB_WRITER.REGIST_DATE = TB_REGIST_DATE.ISSUE_DATE;


--8. TB_BOOK_TRANSLATOR 테이블 생성
CREATE TABLE TB_BOOK_TRANSLATOR(
    BOOK_NO VARCHAR2(10) NOT NULL,
    WRITER_NO VARCHAR2(10) NOT NULL,
    TRANS_LANG VARCHAR2(60),
    CONSTRAINT FK_BOOK_TRANSLATOR_01 FOREIGN KEY(BOOK_NO) REFERENCES TB_BOOK,
    CONSTRAINT FK_BOOK_TRANSLATOR_02 FOREIGN KEY(WRITER_NO) REFERENCES TB_WRITER,
    CONSTRAINT PK_BOOK_TRANSLATOR PRIMARY KEY(BOOK_NO,WRITER_NO)
);

COMMENT ON COLUMN TB_BOOK_TRANSLATOR.BOOK_NO IS '도서 번호';
COMMENT ON COLUMN TB_BOOK_TRANSLATOR.WRITER_NO IS '작가 번호';
COMMENT ON COLUMN TB_BOOK_TRANSLATOR.TRANS_LANG IS '번역 언어';

INSERT INTO TB_BOOK_TRANSLATOR VALUES(1991060502,2,'ENG');

--9. 도서 저작 형태(COMPOSE_TYPE)이 '옮김','역주','편역','공역'에 해당하는 데이터는
--도서 저자 정보 테이블에서 도서 역자 정보 테이블(TB_BOOK_TRANSLATOR)로 옮기는 SQL작성
--단, TRANS_LANG 컬럼은 NULL 상태로 둔다. 이동된 데이터는 더이상 TB_BOOK_AUTHOR에 안남도록하자.

CREATE TABLE DATACOPY AS
(SELECT BOOK_NO,WRITER_NO
FROM TB_BOOK_AUTHOR
WHERE COMPOSE_TYPE IN('옮김','역주','편역','공역'));

MERGE INTO TB_BOOK_TRANSLATOR 
USING DATACOPY
ON(TB_BOOK_TRANSLATOR.BOOK_NO=DATACOPY.BOOK_NO)
WHEN NOT MATCHED THEN
INSERT VALUES(
    DATACOPY.BOOK_NO,
    DATACOPY.WRITER_NO,
    NULL);

DROP TABLE DATACOPY;
DELETE FROM TB_BOOK_AUTHOR
WHERE COMPOSE_TYPE IN('옮김','역주','편역','공역');

COMMIT;

--10.2007년도에 출판된 번역서 이름과 역자를 표시하는 SQL구문 작성
SELECT BOOK_NM, WRITER_NM
FROM TB_BOOK_TRANSLATOR
JOIN TB_BOOK USING(BOOK_NO)
JOIN TB_WRITER USING(WRITER_NO)
WHERE ISSUE_DATE LIKE('07/%');

--11. 10번 결과를 활용해 대상 번역서들의 출판일을 변경할 수 없도록 하는 뷰를 생성하는 SQL구문 작성
--뷰 이름은 VW_BOOK_TRANSLATOR로 하고 도서명, 번역자, 출판일이 표시되도록 하자.
CREATE OR REPLACE VIEW VW_BOOK_TRANSLATOR
AS SELECT BOOK_NO, WRITER_NM, ISSUE_DATE
FROM TB_BOOK_TRANSLATOR
JOIN TB_BOOK USING(BOOK_NO)
JOIN TB_WRITER USING(WRITER_NO)
WHERE ISSUE_DATE IS NOT NULL WITH CHECK OPTION;

--12. 새로운 출판사와 거래 계약 맺음.구문 작성
INSERT INTO TB_PUBLISHER VALUES('춘 출판사','02-6710-3737',DEFAULT);

--13.동명이인 작가의 이름을 찾으려고 한다. 이름과 동명이인 숫자를 표시하는  SQL구문 작성.
SELECT WRITER_NM,COUNT(WRITER_NM)
FROM TB_WRITER
GROUP BY WRITER_NM
HAVING COUNT(WRITER_NM)>1;

--14.도서의 저자 정보 중 저작 형태(COMPOSE_TYPE)이 누락된 데이터들이 적지 않게 존재함. 해당 컬럼이 NULL인 경우
-- '지음'으로 변경하는 SQL구문 작성하자
UPDATE TB_BOOK_AUTHOR
SET COMPOSE_TYPE = '지음'
WHERE COMPOSE_TYPE IS NULL;

--15. 서울지역 작가 모임을 개최하려고한다. 사무실이 서울이고, 사무실 전화 번호 국번이 3자리인 작가의 이름과 사무실 전화 번호를 표시하는 SQL구문 작성
SELECT WRITER_NM, OFFICE_TELNO
FROM TB_WRITER
WHERE OFFICE_TELNO LIKE('02%')AND
        OFFICE_TELNO LIKE('02-___-%');

--16. 2006년 1월 기준으로 등록된 지 31년 이상 된 작가 이름을 이름순으로 표시하는 SQL구문 작성
SELECT WRITER_NM
FROM TB_WRITER
WHERE (TO_DATE(20060101,'YYYYMMDD')-REGIST_DATE)>=11315 --11315일 = 31년
ORDER BY WRITER_NM;

--17. 요즘들어 다시금 인기를 얻고 있는 '황금가지'출판사를 위한 기획전 열려고 한다. 황금가지 출판사에서 발행한 도서 중 재고 수량 10권 미만인 도서명과
--가격, 재고상태를 표시하는 SQL 작성. 재고수량 5권 미만 도서는 '추가주문필요'로, 나머지는 '소량보유'로 표시.
--재고수량 많은 순, 도서명 순으로 표시되도록 한다.
SELECT BOOK_NM, 
        PRICE,
        CASE WHEN STOCK_QTY<5 THEN '추가주문필요'
        ELSE '소량보유' 
        END AS 재고상태
FROM TB_BOOK
WHERE PUBLISHER_NM LIKE ('황금가지%')AND
        STOCK_QTY<10;
        
--18. '아타트롤' 도서 작가와 역자를 표시하는 SQL구문 작성하시오. (헤더 도서명,저자,역자로)
SELECT BOOK_NM 도서명,
        W1.WRITER_NM 저자,
        W2.WRITER_NM 역자
FROM TB_BOOK
JOIN TB_BOOK_AUTHOR A USING(BOOK_NO)
JOIN TB_BOOK_TRANSLATOR T USING(BOOK_NO)
JOIN TB_WRITER W1 ON(W1.WRITER_NO = A.WRITER_NO)
JOIN TB_WRITER W2 ON(W2.WRITER_NO = T.WRITER_NO)
WHERE BOOK_NM = '아타트롤';


--19. 현재기준으로 최초발행일로부터 만 30년 경과되고, 재고수량이 90권 이상인 도서에 대해 도서명, 재고수량, 원래가격, 20%인하가격
--표시하는 SQL구문 작성. (결과 헤더 도서명, 재고수량, 가격(Org), 가격(New)로 표시
SELECT BOOK_NM 도서명,
        STOCK_QTY 재고수량,
        PRICE "가격(Org)",
        PRICE*0.8 "가격(New)"
FROM TB_BOOK
WHERE (SYSDATE-ISSUE_DATE) > 10950 AND
    STOCK_QTY >90;


