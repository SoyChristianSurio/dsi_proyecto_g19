Insert into usuarios (username, p_Nombre, s_Nombre, p_Apellido, s_Apellido, email, estudiante, activo) values('ms12010','Christian','Roberto','Monterrosa','Surio','ms12010@ues.edu.sv',1,1);                      
Insert into usuarios (username, p_Nombre, s_Nombre, p_Apellido, s_Apellido, email, estudiante, activo) values('dm00010','Alan','Brito','Delgado','Macizo','dm00010@ues.edu.sv',1,0);
Insert into usuarios (username, p_Nombre, s_Nombre, p_Apellido, s_Apellido, email, estudiante, activo) values('HR14024','Marta','Susana','Horiana','Ranjita','HR14024@ues.edu.sv',1,1);
Insert into usuarios (username, p_Nombre, s_Nombre, p_Apellido, s_Apellido, email, estudiante, activo) values('LO17115','Huracan','Catrina','Lombardo','Orellana','LO17115@ues.edu.sv',1,1);
Insert into usuarios (username, p_Nombre, s_Nombre, p_Apellido, s_Apellido, email, estudiante, activo) values('GF15064','Geremias','ernesto','Gomez','Figueroa','GF150645@ues.edu.sv',1,1);

Insert into usuarios (username, p_Nombre, s_Nombre, p_Apellido, s_Apellido, email, estudiante, activo) values('docente1','Docente','Uno','Clase','Clara','docente1@ues.edu.sv',0,1);
Insert into usuarios (username, p_Nombre, s_Nombre, p_Apellido, s_Apellido, email, estudiante, activo) values('docente2','Docente','Dos','Clase','Clara','docente2@ues.edu.sv',0,1);
Insert into usuarios (username, p_Nombre, s_Nombre, p_Apellido, s_Apellido, email, estudiante, activo) values('docente3','Docente','Tres','Clase','Clara','docente3@ues.edu.sv',0,1);

Insert into departamentos (nombre) values("Comedia");
Insert into departamentos (nombre) values("ver el cielo");
Insert into departamentos (nombre) values("ciencias básicas");
Insert into departamentos (nombre) values("hablantes de esto y lo otro");
Insert into departamentos (nombre) values("Ver el pasto crecer");

Insert into materias (codigo, nombre) values("mat-115","matematicas 1");
Insert into materias (codigo, nombre) values("mat-215","matematicas 2");
Insert into materias (codigo, nombre) values("mat-315","matematicas 3");
Insert into materias (codigo, nombre) values("mat-115","matematicas 4");
Insert into materias (codigo, nombre) values("slc-115","sacudirse la camisa 1");
Insert into materias (codigo, nombre) values("pcv-115","pues, como te va? 1");

Insert into jefaturas (fecha_registro,departamento_id, jefe_id) values('2020-10-01',3,1);

Update departamentos set jefatura_id=1 where (id=3);

