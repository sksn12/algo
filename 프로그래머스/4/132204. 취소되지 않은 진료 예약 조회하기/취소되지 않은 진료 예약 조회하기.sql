-- 코드를 입력하세요
SELECT A.apnt_no, C.pt_name, C.pt_no, B.mcdp_cd, B.dr_name, A.apnt_ymd
from (
    select *
    from appointment
    where APNT_CNCL_YN = 'N' and MCDP_CD = 'CS' and DATE(APNT_YMD) = '2022-04-13%'
) as A
left join doctor as B on A.mddr_id = B.DR_id
left join patient as C on A.pt_no = C.pt_no
order by A.apnt_ymd;

