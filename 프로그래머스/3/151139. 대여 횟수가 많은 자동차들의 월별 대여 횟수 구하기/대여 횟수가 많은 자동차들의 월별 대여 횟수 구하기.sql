SELECT
    MONTH(start_date) AS MONTH,
    CAR_ID,
    COUNT(history_id) AS RECORDS
FROM
    CAR_RENTAL_COMPANY_RENTAL_HISTORY
WHERE
    start_date >= "2022-08-01"
    AND start_date < "2022-11-01"
    AND CAR_ID IN (
        SELECT
            CAR_ID
        FROM
            CAR_RENTAL_COMPANY_RENTAL_HISTORY
        WHERE
            start_date >= "2022-08-01"
            AND start_date < "2022-11-01"
        GROUP BY
            CAR_ID
        HAVING
            COUNT(history_id) > 4
    )
GROUP BY
    MONTH,
    CAR_ID
HAVING
    RECORDS > 0
ORDER BY
    MONTH,
    CAR_ID DESC;