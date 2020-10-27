create database if not exists proyecto;

use proyecto;

create table if not exists clientes(

DNI varchar (20) unique NOT NULL,
Nombre varchar(20),
apellidos varchar(20),
diereccion varchar(20),
provincia varchar (20),
poblacion varchar(20),
fecha_alta date,
id_cli int AUTO_INCREMENT unique NOT NULL primary key
);

create table if not exists usuarios(
DNI varchar (20) unique NOT NULL,
Nick varchar (20) not null,
passwd varchar(20) not null,
rango varchar(20),
fecha_alta date,
id_user int AUTO_INCREMENT unique NOT NULL primary key
);

create table if not exists empleados(
id_emple int primary key,
rango varchar(20),
id_user int,
id_jefe int
);




create table if not exists vehiculos(
id_vehiculo int auto_increment primary key,
matricula varchar(20) unique not null,
bastidor varchar(20) unique not null,
marca varchar(20),
modelo varchar(20),
precio float,
fecha_alta date,
id_usuario int
);

create table if not exists ppto(
id_presupuesto int auto_increment primary key,
id_cli int,
id_emple int,
fecha_ppto date,
fecha_validez date,
id_vehiculo int,
precio float
);



create table if not exists Ventas(
id_ventas int auto_increment unique not null primary key,
id_cli int,
id_emple int,
fecha_ppto date,
fecha_validez date,
id_vehiculo int,
precio float
);


create table if not exists Repara(
id_rep int auto_increment unique not null primary key,
id_cli int,
id_jefe_taller int,
id_mec int,
fecha_repara date,
tiempo float,
id_vehiculo int,
presu_esrti float,
fecha_ini date,
fecha_fn date
);

create table if not exists PiezasRep(
id_rep int not null primary key,
id_pieza int,
precio float
);

create table if not exists piezas(
id_pieza int auto_increment unique not null primary key,
descripcion  varchar(100),
precio float,
fecha_alta date
);

-- añadir FK a empleados
alter table empleados add foreign key (id_user) references usuarios (id_user);
-- falla
alter table empleados add foreign key (rango) references usuarios (rango);

-- añadir FK a ppta(presupuesto)
alter table ppto add foreign key (id_cli) references clientes (id_cli);
alter table ppto add foreign key (id_emple) references empleados (id_emple);
alter table ppto add foreign key (id_vehiculo) references vehiculos (id_vehiculo);

-- añadir FK a repara
alter table repara add foreign key (id_cli) references clientes (id_cli);
alter table repara add foreign key (id_mec) references empleados (id_emple);
-- falla
alter table repara add foreign key (id_jefe_taller) references empleados (id_jefe);
alter table repara add foreign key (id_vehiculo) references vehiculos (id_vehiculo);

-- añadir FK a ventas
alter table ventas add foreign key (id_cli) references clientes (id_cli);
alter table ventas add foreign key (id_emple) references empleados (id_emple);
alter table ventas add foreign key (id_vehiculo) references vehiculos (id_vehiculo);

-- añadir FK a piezasrep
alter table piezasrep add foreign key (id_pieza) references piezas (id_pieza);
alter table piezasrep add foreign key (id_rep) references repara (id_rep);
