select a.item_id, a.item_name, a.rarity
from item_info as a 
left join item_tree as b on a.item_id = b.parent_item_id
where b.parent_item_id is null 
order by a.item_id desc