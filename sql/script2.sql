
create table cliente (
	id bigint not null auto_increment, 
	apellido varchar(255), 
	codigo varchar(255), 
	nombre varchar(255), 
	idCuenta bigint, 
	primary key (id)
	) engine=InnoDB

	
create table cuenta (
	id bigint not null, 
	numero varchar(255), 
	idCliente bigint, 
	primary key (id)
	) engine=InnoDB

	
create table factura (
	id integer not null auto_increment, 
	numero nvarchar(255) not null, 
	idFact_Cliente bigint, 
	primary key (id)
	) engine=InnoDB


create table hibernate_sequence (
	next_val bigint
	) engine=InnoDB
	
alter table cliente 
	add constraint fk_idCliente_idCuenta 
	foreign key (idCuenta) references cuenta (id)

alter table cuenta 
	add constraint fk_idCuenta_idCliente 
	foreign key (idCliente) references cliente (id)

alter table factura 
	add constraint fk_id_factura_id_cliente 
	foreign key (idFact_Cliente) references cliente (id)

select next_val as id_val from hibernate_sequence for update

update hibernate_sequence set next_val= ? where next_val=?

insert into cuenta (idCliente, numero, id) values (?, ?, ?)

insert into factura (idFact_Cliente, numero) values (?, ?)


	
	