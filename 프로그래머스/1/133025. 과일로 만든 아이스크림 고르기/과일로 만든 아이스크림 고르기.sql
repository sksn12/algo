-- 코드를 입력하세요
SELECT A.FLAVOR
from first_half as A
left join icecream_info as B on A.flavor = B.flavor
where A.total_order>3000 and B.INGREDIENT_TYPE = 'fruit_based'
order by A.total_order desc;
