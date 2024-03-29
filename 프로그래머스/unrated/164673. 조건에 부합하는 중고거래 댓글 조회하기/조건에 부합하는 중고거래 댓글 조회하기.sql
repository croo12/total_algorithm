-- 코드를 입력하세요
SELECT TITLE, a.BOARD_ID, REPLY_ID, b.WRITER_ID, b.CONTENTS, 
DATE_FORMAT(b.CREATED_DATE, "%Y-%m-%d") as CREATED_DATE 
FROM USED_GOODS_BOARD as a, USED_GOODS_REPLY as b 
WHERE a.BOARD_ID = b.BOARD_ID 
AND a.CREATED_DATE BETWEEN "2022-10-01 00:00:00" AND "2022-10-31 23:59:59"
ORDER BY b.CREATED_DATE asc, TITLE asc;