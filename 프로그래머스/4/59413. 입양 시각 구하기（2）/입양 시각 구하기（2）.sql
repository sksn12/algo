with recursive hours as (
    select 0 as hour
    
    union all 
    
    select hour+1
    from hours
    where hour<23
)

select  h.hour,COUNT(a.DATETIME) AS count
from hours h
left join ANIMAL_OUTS as a
on h.hour = HOUR(a.DATETIME)
group by h.hour
ORDER BY h.hour;
