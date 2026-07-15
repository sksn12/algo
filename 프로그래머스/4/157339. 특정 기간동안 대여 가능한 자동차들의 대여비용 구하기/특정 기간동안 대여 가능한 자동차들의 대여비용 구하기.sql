with history as (
    SELECT *
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
    WHERE start_date <= '2022-11-30' AND end_date >= '2022-11-01'
),price as (
    select *
    from CAR_RENTAL_COMPANY_DISCOUNT_PLAN
    where car_type in ('세단','suv') and DURATION_TYPE ='30일 이상'
)

SELECT a.car_id,a.car_type,round((a.daily_fee*30* (1-(c.discount_rate/100)))) as fee
from CAR_RENTAL_COMPANY_CAR as a
left join history as b on a.car_id= b.car_id
left join price as c on a.car_type = c.car_type
where a.CAR_TYPE in ('SUV','세단') 
        and b.car_id is null
        and round((a.daily_fee*30* (1-(c.discount_rate/100)))) >= 500000
        and round((a.daily_fee*30* (1-(c.discount_rate/100)))) < 2000000
order by fee desc, a.car_type,a.car_id desc;