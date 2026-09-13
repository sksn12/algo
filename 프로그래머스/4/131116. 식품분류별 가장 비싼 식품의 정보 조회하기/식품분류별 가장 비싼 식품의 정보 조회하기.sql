-- 코드를 입력하세요
SELECT a.CATEGORY, a.MAX_PRICE, b.PRODUCT_NAME
from (
    select CATEGORY,max(PRICE) as MAX_PRICE
    from FOOD_PRODUCT
    group by CATEGORY
) as a
left join FOOD_PRODUCT as b
on a.CATEGORY = b.CATEGORY and a.MAX_PRICE = b.PRICE
where a.CATEGORY= '과자' || a.CATEGORY= '국'|| a.CATEGORY= '김치' || a.CATEGORY= '식용유'
order by a.MAX_PRICE desc