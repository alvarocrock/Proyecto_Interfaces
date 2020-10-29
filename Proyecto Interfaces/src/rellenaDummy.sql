 
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



insert into usuarios (DNI, nick, passwd,rango, fecha_alta)
values ("1111111U", "ventas", "1234", "vendedor","2020-10-28");

insert into usuarios (DNI, nick, passwd,rango, fecha_alta)
values ("2222222U", "mecanico", "1234", "mecanico","2020-10-28");

insert into usuarios (DNI, nick, passwd,rango, fecha_alta)
values ("3333333U", "jefe", "1234", "jefe","2020-10-28");

insert into usuarios (DNI, nick, passwd,rango, fecha_alta)
values ("4444444U", "jefeTaller", "1234", "jefeTaller","2020-10-28");



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

insert into vehiculos (matricula, bastidor, marca, modelo, precio, fecha_alta, id_usuario, id_conce, tipo, id_cliente)
values ("1111ABC", "número de bastidor 1", "Peugeot","406", 30000,"2020-10-28",1,1,"Coche",1 );

insert into vehiculos (matricula, bastidor, marca, modelo, precio, fecha_alta, id_usuario, id_conce, tipo, id_cliente)
values ("2222DEF", "número de bastidor 2", "Renault","307 SW", 20000,"2020-10-28",2,1,"Ciclomotor",2 );

insert into vehiculos (matricula, bastidor, marca, modelo, precio, fecha_alta, id_usuario, id_conce, tipo, id_cliente)
values ("3333DEF", "número de bastidor 3", "Fiat","406 SW", 40000,"2020-10-28",3,1,"Motocicleta",3 );

insert into vehiculos (matricula, bastidor, marca, modelo, precio, fecha_alta, id_usuario, id_conce, tipo, id_cliente)
values ("4444GFR", "número de bastidor 4", "Peugeot","5001", 50000,"2020-10-28",4,1,"Coche",4);

insert into vehiculos (matricula, bastidor, marca, modelo, precio, fecha_alta, id_usuario, id_conce, tipo, id_cliente)
values ("5555HGT", "número de bastidor 5", "Fiat","6008", 60000,"2020-10-28",2,1,"Ciclomotor",5 );

insert into vehiculos (matricula, bastidor, marca, modelo, precio, fecha_alta, id_usuario, id_conce, tipo, id_cliente)
values ("6666LOI", "número de bastidor 6", "Peugeot","206 SW", 70000,"2020-10-28",2,1,"Motocicleta",6 );

insert into vehiculos (matricula, bastidor, marca, modelo, precio, fecha_alta, id_usuario, id_conce, tipo, id_cliente)
values ("7777RTF", "número de bastidor 7", "Peugeot","206", 40000,"2020-10-28",2,1,"Motocicleta",7 );

insert into vehiculos (matricula, bastidor, marca, modelo, precio, fecha_alta, id_usuario, id_conce, tipo, id_cliente)
values ("8888HGT", "número de bastidor 8", "Peugeot","3001", 10000,"2020-10-28",2,1,"Coche",8 );

insert into vehiculos (matricula, bastidor, marca, modelo, precio, fecha_alta, id_usuario, id_conce, tipo, id_cliente)
values ("9999HYU", "número de bastidor 9", "Peugeot","2022", 33000,"2020-10-28",2,1,"Ciclomotor",8 );

insert into vehiculos (matricula, bastidor, marca, modelo, precio, fecha_alta, id_usuario, id_conce, tipo, id_cliente)
values ("1010KJU", "número de bastidor 10", "Peugeot","5006", 15000,"2020-10-28",2,1,"Motocicleta",9 );



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


