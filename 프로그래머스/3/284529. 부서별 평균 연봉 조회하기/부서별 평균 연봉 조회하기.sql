select A.dept_id, A.DEPT_NAME_EN, round(avg(B.SAL)) as AVG_SAL
from HR_DEPARTMENT as A
right join HR_EMPLOYEES as B on A.dept_id = B.dept_id
group by A.dept_id, A.DEPT_NAME_EN
order by AVG_SAL desc;
