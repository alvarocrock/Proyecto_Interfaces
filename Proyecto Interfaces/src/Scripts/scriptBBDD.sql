
create table if not exists clientes(

DNI varchar (9) unique NOT NULL,
nombre varchar(50),
apellidos varchar(50),
direccion varchar(50),
provincia varchar (50),
poblacion varchar(50),
fecha_alta date,
id_cli int AUTO_INCREMENT unique NOT NULL primary key
);

create table if not exists usuarios(
DNI varchar (9) unique NOT NULL,
Nick varchar (20) not null,
passwd varchar(64) not null,
rango varchar(20),
fecha_alta date,
foto varchar(255),
id_user int AUTO_INCREMENT unique NOT NULL primary key
);

create table if not exists empleados(
id_emple int AUTO_INCREMENT unique NOT NULL primary key,
rango varchar(20),
id_user int,
id_jefe int,
fecha_alta date,
DNI varchar (9) unique NOT NULL,
nombre varchar(50),
apellidos varchar(50),
direccion varchar(50),
provincia varchar (50),
poblacion varchar(50)
);




create table if not exists vehiculos(
id_vehiculo int auto_increment primary key,
matricula varchar(20) unique not null,
bastidor varchar(50) unique not null,
marca varchar(50),
modelo varchar(20),
precio float,
fecha_alta date,
id_cliente int,
id_usuario int,
id_conce int,
tipo varchar(20),
ano int,
kilometros int,
combustible varchar(10)
);

create table if not exists concesionario(
id_conce int auto_increment primary key,
nombre varchar(50)
);

create table if not exists ppto(
id_presupuesto int auto_increment primary key,
id_cli int,
id_emple int,
fecha_ppto date,
fecha_validez date,
id_vehiculo int,
precio float,
estado varchar(15)
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
descripcion Varchar(200),
fecha_repara date,
tiempo float,
id_vehiculo int,
presu_esrti float,
fecha_ini date,
fecha_fn date,
hora_ini char(6),
hora_fn char(6)

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

alter table empleados add foreign key (id_user) references usuarios (id_user);


alter table vehiculos add foreign key (id_cliente) references clientes (id_cli); 
alter table vehiculos add foreign key (id_usuario) references usuarios (id_user); 
alter table vehiculos add foreign key (id_conce) references concesionario (id_conce); 


alter table ppto add foreign key (id_cli) references clientes (id_cli);
alter table ppto add foreign key (id_emple) references empleados (id_emple);
alter table ppto add foreign key (id_vehiculo) references vehiculos (id_vehiculo);


alter table repara add foreign key (id_cli) references clientes (id_cli);
alter table repara add foreign key (id_mec) references empleados (id_emple);

alter table repara add foreign key (id_vehiculo) references vehiculos (id_vehiculo);


alter table ventas add foreign key (id_cli) references clientes (id_cli);
alter table ventas add foreign key (id_emple) references empleados (id_emple);
alter table ventas add foreign key (id_vehiculo) references vehiculos (id_vehiculo);


alter table piezasrep add foreign key (id_pieza) references piezas (id_pieza);
alter table piezasrep add foreign key (id_rep) references repara (id_rep);