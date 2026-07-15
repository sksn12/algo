select INGREDIENT_TYPE, sum(TOTAL_ORDER) as TOTAL_ORDER
    from FIRST_HALF as a inner join ICECREAM_INFO as b on a.FLAVOR=b.FLAVOR
    group by INGREDIENT_TYPE
    order by TOTAL_ORDER 
    