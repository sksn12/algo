SELECT b.user_id as USER_ID, b.nickname as NICKNAME, sum(a.price) as TOTAL_SALES
from USED_GOODS_BOARD as a 
join USED_GOODS_USER  as b on a.WRITER_ID = b.user_id
where a.status = 'DONE'
group by b.user_id
having sum(a.price)>=700000
order by total_sales;

