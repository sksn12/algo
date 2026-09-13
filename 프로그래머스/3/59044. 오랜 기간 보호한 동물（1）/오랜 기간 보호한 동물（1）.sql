-- 코드를 입력하세요
SELECT A.NAME, A.DATETIME
from ANIMAL_INS as A
left join ANIMAL_OUTS as B on A.ANIMAL_ID=B.ANIMAL_ID
where B.ANIMAL_ID is null
order by DATETIME 
limit 3;
