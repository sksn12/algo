select count(*) as fish_count, month(time) as month
from FISH_INFO
group by month
having count(*)>0
order by month 