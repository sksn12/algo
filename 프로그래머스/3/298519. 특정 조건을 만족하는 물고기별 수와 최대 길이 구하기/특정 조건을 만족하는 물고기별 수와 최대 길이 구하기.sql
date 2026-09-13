select count(*) as FISH_COUNT, max(LENGTH) as MAX_LENGTH, FISH_TYPE
from (
    select ID,FISH_TYPE,ifnull(length,10) as LENGTH
    from fish_info
)as A
group by A.FISH_TYPE
having avg (LENGTH) >= 33
order by FISH_TYPE