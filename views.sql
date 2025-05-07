create materialized view accommodation_per_host as
select h.id as host_id, count(c.id) as num_accommodations
from host h left join commodation c on c.host_id = h.id
group by h.id;

create materialized view host_per_country as
select c.id as country_id, count(h.id) as num_hosts
from country c left join host h on h.country_id = c.id
group by c.id