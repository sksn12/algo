SELECT c.item_id, c.item_name, c.rarity
FROM (
    SELECT a.item_id, b.item_name, b.rarity,b.price
    FROM ITEM_TREE a
    LEFT JOIN ITEM_INFO b ON a.parent_item_id = b.ITEM_ID
    where b.rarity='RARE'
) AS sq left join item_info as c 
    on sq.item_id = c.item_id
    order by c.item_id desc;