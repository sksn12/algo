SELECT board.TITLE, board.BOARD_ID, reply.REPLY_ID, reply.WRITER_ID, reply.CONTENTS, date_format(reply.CREATED_DATE,'%Y-%m-%d') as CREATED_DATE
from used_goods_board as board
join used_goods_reply as reply on board.board_id = reply.board_id
where board.created_date >= '2022-10-01' and board.created_date <= '2022-10-31'
order by reply.created_date, board.title;