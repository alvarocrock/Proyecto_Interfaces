 
insert into clientes (DNI, nombre, apellidos,direccion, provincia, poblacion, fecha_alta)
values ("11111111S", "Antonio","Flores Pérez", "direccion cliente 1", "Málaga", "Málaga", "2020-10-28");

insert into clientes (DNI, nombre, apellidos,direccion, provincia, poblacion, fecha_alta)
values ("22222222A", "Paco", "Flores Pérez", "direccion cliente 2", "Málaga", "Málaga", "2020-10-28");

insert into clientes (DNI, nombre, apellidos,direccion, provincia, poblacion, fecha_alta)
values ("3333333B", "Paco", "Fernández Pérez", "direccion cliente 3", "Málaga", "Málaga", "2020-10-28");

insert into clientes (DNI, nombre, apellidos,direccion, provincia, poblacion, fecha_alta)
values ("444444C", "Paco", "Flores García", "direccion cliente 4", "Málaga", "Málaga", "2020-10-28");

insert into clientes (DNI, nombre, apellidos,direccion, provincia, poblacion, fecha_alta)
values ("5555555D", "Juan", "Flores Flores", "direccion cliente 5", "Málaga", "Málaga", "2020-10-28");
insert into clientes (DNI, nombre, apellidos,direccion, provincia, poblacion, fecha_alta)
values ("6666666S", "Antonio", "Flores Pérez", "direccion cliente 6", "Málaga", "Málaga", "2020-10-28");

insert into clientes (DNI, nombre, apellidos,direccion, provincia, poblacion, fecha_alta)
values ("7777777D", "Álvaro", "Vera Pérez", "direccion cliente 7", "Málaga", "Málaga", "2020-10-28");

insert into clientes (DNI, nombre, apellidos,direccion, provincia, poblacion, fecha_alta)
values ("8888888Q", "Roque", "Fernández Verdugo", "direccion cliente 8", "Málaga", "Málaga", "2020-10-28");

insert into clientes (DNI, nombre, apellidos,direccion, provincia, poblacion, fecha_alta)
values ("9999999F", "Sergio", "Burgos García", "direccion cliente 9", "Málaga", "Málaga", "2020-10-28");

insert into clientes (DNI, nombre, apellidos,direccion, provincia, poblacion, fecha_alta)
values ("1010110G", "Pepe", "Flores Córdoba", "direccion cliente 10", "Málaga", "Málaga", "2020-10-28");



insert into usuarios (DNI, nick, passwd,rango, fecha_alta,foto)
values ("1111111U", "ventas", "4b227777d4dd1fc61c6f884f48641d02b4d121d3fd328cb08b5531fcacdabf8a", "vendedor","2020-10-28","src/png/Dolar.png");

insert into usuarios (DNI, nick, passwd,rango, fecha_alta,foto)
values ("2222222U", "mecanico", "4b227777d4dd1fc61c6f884f48641d02b4d121d3fd328cb08b5531fcacdabf8a", "mecanico","2020-10-28","src/png/llave.png");

insert into usuarios (DNI, nick, passwd,rango, fecha_alta,foto)
values ("3333333U", "jefe", "4b227777d4dd1fc61c6f884f48641d02b4d121d3fd328cb08b5531fcacdabf8a", "jefe","2020-10-28","src/png/jefeF.png");

insert into usuarios (DNI, nick, passwd,rango, fecha_alta, foto)
values ("4444444U", "jefeTaller", "4b227777d4dd1fc61c6f884f48641d02b4d121d3fd328cb08b5531fcacdabf8a", "jefeTaller","2020-10-28", "src/png/jefeT.png");



insert into empleados (rango, id_user, id_jefe, fecha_alta, DNI, nombre, apellidos,direccion, provincia, poblacion)
values ("vendedor", 1, 3,"2020-10-28","1111111A", "Pepe", "Flores Córdoba", "direccion empleado 1", "Málaga", "Málaga");

insert into empleados (rango, id_user, id_jefe, fecha_alta, DNI, nombre, apellidos,direccion, provincia, poblacion)
values ("mecanico", 2, 4,"2020-10-28","2222222B", "Juan", "García Córdoba", "direccion empleado 2", "Málaga", "Málaga");

insert into empleados (rango, id_user, id_jefe, fecha_alta, DNI, nombre, apellidos,direccion, provincia, poblacion)
values ("Jefe", 3, 3,"2020-10-28","3333333C", "Antonio", "Vergara Gómez", "direccion empleado 3", "Málaga", "Málaga");

insert into empleados (rango, id_user, id_jefe, fecha_alta, DNI, nombre, apellidos,direccion, provincia, poblacion)
values ("Jefe de taller", 4, 3,"2020-10-28","4444444D", "Paco", "Martínez Soria", "direccion empleado 4", "Málaga", "Málaga");


insert into concesionario (nombre)
values ("Concesionaro número 1" );

insert into vehiculos (matricula, bastidor, marca, modelo, precio, fecha_alta, id_usuario, id_conce, tipo, id_cliente,ano,kilometros,combustible)
values ("1111ABC", "número de bastidor 1", "Peugeot","406", 30000,"2020-10-28",1,1,"Coche",1,2005,3000,"diesel" );

insert into vehiculos (matricula, bastidor, marca, modelo, precio, fecha_alta, id_usuario, id_conce, tipo, id_cliente,ano,kilometros,combustible)
values ("2222DEF", "número de bastidor 2", "Renault","307 SW", 20000,"2020-10-28",2,1,"Ciclomotor",2,2013,1000,"gasolina");

insert into vehiculos (matricula, bastidor, marca, modelo, precio, fecha_alta, id_usuario, id_conce, tipo, id_cliente,ano,kilometros,combustible)
values ("3333DEF", "número de bastidor 3", "Fiat","406 SW", 40000,"2020-10-28",3,1,"Motocicleta",3,2010,300,"hibrido");

insert into vehiculos (matricula, bastidor, marca, modelo, precio, fecha_alta, id_usuario, id_conce, tipo, id_cliente,ano,kilometros,combustible)
values ("4444GFR", "número de bastidor 4", "Peugeot","5001", 50000,"2020-10-28",4,1,"Coche",4,2005,200,"electrico");

insert into vehiculos (matricula, bastidor, marca, modelo, precio, fecha_alta, id_usuario, id_conce, tipo, id_cliente,ano,kilometros,combustible)
values ("5555HGT", "número de bastidor 5", "Fiat","6008", 60000,"2020-10-28",2,1,"Ciclomotor",5,2007,0,"diesel");

insert into vehiculos (matricula, bastidor, marca, modelo, precio, fecha_alta, id_usuario, id_conce, tipo, id_cliente,ano,kilometros,combustible)
values ("6666LOI", "número de bastidor 6", "Peugeot","206 SW", 70000,"2020-10-28",2,1,"Motocicleta",6,2010,7800,"gasolina");

insert into vehiculos (matricula, bastidor, marca, modelo, precio, fecha_alta, id_usuario, id_conce, tipo, id_cliente,ano,kilometros,combustible)
values ("7777RTF", "número de bastidor 7", "Peugeot","206", 40000,"2020-10-28",2,1,"Motocicleta",7,2040,2,"gasolina");


insert into vehiculos (matricula, bastidor, marca, modelo, precio, fecha_alta, id_usuario, id_conce, tipo, id_cliente,ano,kilometros,combustible)
values ("9999HYU", "número de bastidor 9", "Peugeot","2022", 33000,"2020-10-28",2,1,"Ciclomotor",8,2015,200,"hibrido");

insert into vehiculos (matricula, bastidor, marca, modelo, precio, fecha_alta, id_usuario, id_conce, tipo, id_cliente,ano,kilometros,combustible)
values ("1010KJU", "número de bastidor 10", "Peugeot","5006", 15000,"2020-10-28",2,1,"Motocicleta",9,2019,0,"electrico");



insert into piezas (descripcion, precio, fecha_alta)
values ("Pieza de la trócola",20, "2020-10-28");

insert into piezas (descripcion, precio, fecha_alta)
values ("Pieza del motor",10, "2020-10-28");

insert into piezas (descripcion, precio, fecha_alta)
values ("Biela",30, "2020-10-28");

insert into piezas (descripcion, precio, fecha_alta)
values ("Rueda",80, "2020-10-28");
insert into piezas (descripcion, precio, fecha_alta)
values ("Tapa cubo",40, "2020-10-28");

insert into piezas (descripcion, precio, fecha_alta)
values ("cable de embrague",30, "2020-10-28");

insert into piezas (descripcion, precio, fecha_alta)
values ("Trócola entera",20, "2020-10-28");

insert into piezas (descripcion, precio, fecha_alta)
values ("Otra piezar",100, "2020-10-28");
insert into piezas (descripcion, precio, fecha_alta)
values ("Pedal de freno",250, "2020-10-28");

insert into piezas (descripcion, precio, fecha_alta)
values ("Asiento completo",500, "2020-10-28");

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('1', '1', '1', '2020-12-11', '2020-12-21', '1', '10000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('2', '2', '2', '2020-12-11', '2020-12-21', '1', '20000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('3', '3', '3', '2020-12-11', '2020-12-21', '3', '30000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('4', '2', '2', '2020-01-11', '2020-12-21', '1', '20000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('5', '3', '3', '2020-02-11', '2020-12-21', '3', '30000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('6', '3', '1', '2020-02-11', '2020-03-21', '3', '30000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('7', '4', '2', '2020-02-11', '2020-04-21', '3', '20000');
INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('8', '3', '3', '2020-03-11', '2020-12-21', '3', '30000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('9', '1', '1', '2020-04-11', '2020-03-21', '3', '30000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('10', '5', '2', '2020-05-11', '2020-04-21', '3', '20000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('11', '3', '3', '2020-06-11', '2020-12-21', '3', '30000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('12', '6', '1', '2020-07-11', '2020-03-21', '3', '30000');
INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('13', '3', '2', '2020-08-11', '2020-04-21', '3', '20000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('14', '8', '3', '2020-09-11', '2020-12-21', '3', '30000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('15', '3', '1', '2020-10-11', '2020-03-21', '3', '30000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('16', '3', '2', '2020-11-11', '2020-04-21', '3', '20000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('17', '9', '3', '2020-12-11', '2020-12-21', '3', '30000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('18', '3', '1', '2020-02-11', '2020-03-21', '3', '30000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('19', '3', '2', '2020-03-11', '2020-04-21', '3', '20000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('20', '2', '3', '2020-04-11', '2020-12-21', '3', '30000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('21', '6', '1', '2020-05-11', '2020-03-21', '3', '30000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('22', '7', '2', '2020-06-11', '2020-04-21', '3', '20000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('23', '2', '2', '2020-01-11', '2020-12-21', '1', '20000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('24', '3', '3', '2020-02-11', '2020-12-21', '3', '30000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('25', '3', '1', '2020-02-11', '2020-03-21', '3', '30000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('26', '4', '2', '2020-02-11', '2020-04-21', '3', '20000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('27', '3', '3', '2020-03-11', '2020-12-21', '3', '30000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('28', '1', '1', '2020-04-11', '2020-03-21', '3', '30000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('29', '5', '2', '2020-05-11', '2020-04-21', '3', '20000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('30', '3', '3', '2020-06-11', '2020-12-21', '3', '30000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('31', '6', '1', '2020-07-11', '2020-03-21', '3', '30000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('32', '3', '2', '2020-08-11', '2020-04-21', '3', '20000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('33', '8', '3', '2020-09-11', '2020-12-21', '3', '30000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('34', '3', '1', '2020-10-11', '2020-03-21', '3', '30000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('35', '3', '2', '2020-11-11', '2020-04-21', '3', '20000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('36', '9', '3', '2020-12-11', '2020-12-21', '3', '30000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('37', '3', '1', '2020-02-11', '2020-03-21', '3', '30000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('38', '3', '2', '2020-03-11', '2020-04-21', '3', '20000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('39', '2', '3', '2020-04-11', '2020-12-21', '3', '30000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('40', '6', '1', '2020-05-11', '2020-03-21', '3', '30000');

INSERT INTO `proyecto`.`ventas` (`id_ventas`, `id_cli`, `id_emple`, `fecha_ppto`, `fecha_validez`, `id_vehiculo`, `precio`) 
VALUES ('41', '7', '2', '2020-06-11', '2020-04-21', '3', '20000');

