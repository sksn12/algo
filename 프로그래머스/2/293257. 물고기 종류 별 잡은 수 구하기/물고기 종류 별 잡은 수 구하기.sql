select count(*) as fish_count, A.fish_name as fish_name
from fish_name_info as A
right join fish_info as B on A.fish_type = B.fish_type
group by A.fish_name
order by count(*) desc;
