insert into asignatura
(
   id,
   nombre,
   descripcion,
   curso
)
select
   1,
   'Matemáticas I',
   'aprendizaje de vectores, geometría, algebra y numeros primos',
   1
from
   dual
where not exists
(
   select
      1
   from
      asignatura
   where
      id = 1
);
insert into asignatura
(
   id,
   nombre,
   descripcion,
   curso
)
select
   2,
   'Literatura',
   'enseñanza de la cultura literaria en la edad de Oro en esta y epoca renacentista',
   1
from
   dual
where not exists
(
   select
      1
   from
      asignatura
   where
      id = 2
);
insert into asignatura
(
   id,
   nombre,
   descripcion,
   curso
)
select
   3,
   'Filosofia',
   'enseñanza del pensamiento a lo largo del tiempo',
   1
from
   dual
where not exists
(
   select
      1
   from
      asignatura
   where
      id = 3
);
insert into rol (id, rol)
	select 1, 'ADMIN' from dual where not exists (select 1 from rol where id = 1);

insert into rol (id, rol)
	select 2, 'CONSULTA' from dual where not exists (select 1 from rol where id = 2);

/* pass = 1111 */
insert into usuario (username, nombre, password, rol_id)
	select 'user1', 'admin', '$2a$10$5xOe75pbLcAjp0TbVWaluunrSshgYdH82YNwGd.b0Os4hAWbIEkry', 1 from dual where not exists (select 1 from usuario where username = 'user1');

insert into usuario (username, nombre, password, rol_id)
	select 'user2', 'consulta', '$2a$10$5xOe75pbLcAjp0TbVWaluunrSshgYdH82YNwGd.b0Os4hAWbIEkry', 2 from dual where not exists (select 1 from usuario where username = 'user2');