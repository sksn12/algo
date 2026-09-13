-- 코드를 입력하세요
SELECT a.PRODUCT_ID, a.PRODUCT_NAME, (sum(b.AMOUNT) * a.price) as TOTAL_SALES
from FOOD_PRODUCT as a
left join FOOD_ORDER as b
on a.PRODUCT_ID= b.PRODUCT_ID
where YEAR(b.PRODUCE_DATE) = '2022' and MONTH(b.PRODUCE_DATE) ='05'
group by b.PRODUCT_ID
order by TOTAL_SALES desc, a.PRODUCT_ID