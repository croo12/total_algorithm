-- 코드를 입력하세요
SELECT f.FLAVOR 
FROM FIRST_HALF f, 
(
    SELECT tmp.FLAVOR, SUM(tmp.TOTAL_ORDER) AS TOTAL_ORDER FROM JULY tmp
    GROUP BY tmp.FLAVOR
) j
WHERE f.FLAVOR = j.FLAVOR  
GROUP BY f.FLAVOR
ORDER BY SUM(f.TOTAL_ORDER) + SUM(j.TOTAL_ORDER) DESC
LIMIT 3;
