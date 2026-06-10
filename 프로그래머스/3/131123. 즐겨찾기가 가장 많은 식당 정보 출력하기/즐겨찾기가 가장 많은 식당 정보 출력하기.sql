SELECT FOOD_TYPE, REST_ID, REST_NAME, FAVORITES
FROM (
    SELECT food_type,
           rest_id,
           rest_name, 
           FAVORITES,
           ROW_NUMBER() OVER (PARTITION BY food_type ORDER BY favorites DESC) AS n
    FROM rest_info
) as a
WHERE n = 1
order by FOOD_TYPE desc;
