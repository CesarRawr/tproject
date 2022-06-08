create table inventario (
	id int auto_increment primary key,
	nombre varchar(100) not null,
	cantidad int not null,
	gramaje int not null,
	precio float not null
);

create table venta (
	id int auto_increment primary key,
	cantidad int not null,
	vendedor varchar(50) not null,
	articuloID int,
	FOREIGN KEY (articuloID) REFERENCES inventario(id)
);

create table usuario (
	id int auto_increment primary key,
	nickname varchar(100) not null
);

create table stock (
	id int primary key,
	usuario int not null,
	tortillas int not null,
	totopos int not null,
	FOREIGN KEY (usuario) REFERENCES usuario(id)
);

insert into usuario (nickname) values (
	"cesar_rwr"
);

insert into inventario (nombre, cantidad, gramaje, precio) values (
	"papas",
	20,
	150,
	16
);

insert into stock (id, usuario, tortillas, totopos) values (1, 1, 0, 0);
