/*
1)	평균 연봉(salary)이 가장 높은 나라는?
 */
select co.country_name "나라이름", A.av "평균연봉"
from countries co, (select lo.country_id, avg(su) av
from locations lo, (select location_id, avg(su) su
                    from departments de, (select department_id, avg(salary) su
                                          from employees
                                          group by department_id) A 
                    where de.department_id = A.department_id
                    group by location_id) A
where lo.location_id = A.location_id
group by lo.country_id) A
where A.country_id = co.country_id
and A.av = (select max(A.av)
from (select lo.country_id, avg(su) av
      from locations lo, (select location_id, avg(su) su
                          from departments de, (select department_id, avg(salary) su
                                                from employees
                                                group by department_id) A 
                          where de.department_id = A.department_id
                          group by location_id) A
where lo.location_id = A.location_id
group by lo.country_id) A);
/*
2)	평균 연봉(salary)이 가장 높은 지역은?
*/
select re.region_name "지역이름", A.av "평균연봉"
from regions re, (select co.region_id, avg(av) av
                  from countries co, (select lo.country_id, avg(su) av
                  from locations lo, (select location_id, avg(su) su
                  from departments de, (select department_id, avg(salary) su
                                        from employees
                                        group by department_id) A 
                  where de.department_id = A.department_id
                  group by location_id) A
                  where lo.location_id = A.location_id
                  group by lo.country_id) A
where co.country_id = A.country_id
group by co.region_id) A
where A.region_id = re.region_id 
AND av = (select max(A.av) 
from (select co.region_id, avg(av) av
      from countries co, (select lo.country_id, avg(su) av
                          from locations lo, (select location_id, avg(su) su
                                              from departments de, (select department_id, avg(salary) su
                                                                    from employees
                                                                    group by department_id) A 
                                              where de.department_id = A.department_id
                                              group by location_id) A
                          where lo.location_id = A.location_id
                          group by lo.country_id) A
where co.country_id = A.country_id
group by co.region_id) A);
/*
3)	가장 많은 직원이 있는 부서는 어떤 부서인가요?
*/
select department_name "부서이름", A.co "직원수"
from (select de.department_name, count(*) co
	  from employees em, departments de
	  where em.department_id = de.department_id
	  group by de.department_name) A
where A.co = (select max(A.co) ma
			  from (select de.department_name, count(*) co
			  		from employees em, departments de
					where em.department_id = de.department_id
					group by de.department_name) A);