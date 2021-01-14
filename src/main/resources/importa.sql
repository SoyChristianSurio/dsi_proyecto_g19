Insert into roles (nombre) values("ROLE_admin");
Insert into roles (nombre) values("ROLE_estudiante");
Insert into roles (nombre) values("ROLE_docente");
Insert into roles (nombre) values("ROLE_jefe");

Insert into usuarios (username, role_id, password, p_Nombre, s_Nombre, p_Apellido, s_Apellido, email, estudiante, activo) values('admin', 1, '$2a$10$tLizco88QqRv80R/l1IHZubLLhS8rKlXRoSbxg0MJWUU784TuY0f6','Admin','Admin','Admin','Admin','Admin@mail.com',1,1);                      

Insert into usuarios (username, role_id, password, p_Nombre, s_Nombre, p_Apellido, s_Apellido, email, estudiante, activo) values('ES20001', 2, '$2a$10$AlRB81sJ17JU7hRFO0gQreqF4P59UENCEW8C2G2skWLWd39xC0EXO','Christian','Roberto','Monterrosa','Surio','ms12010@ues.edu.sv',1,1);                      
Insert into usuarios (username, role_id, password, p_Nombre, s_Nombre, p_Apellido, s_Apellido, email, estudiante, activo) values('ES20002', 2, '$2a$10$06juFoGqwCqPIsBkM6bgl.99pGuspsZAzagBcBZIBE0lk6KYVgPEW','Alan','Brito','Delgado','Macizo','dm00010@ues.edu.sv',1,0);
Insert into usuarios (username, role_id, password, p_Nombre, s_Nombre, p_Apellido, s_Apellido, email, estudiante, activo) values('ES20003', 2, '$2a$10$Mh1zZv4ckUR2O0kAxJpIquSMXC.dShLCgYzoINUSX3G4/dhqN3qHq','Marta','Susana','Horiana','Ranjita','HR14024@ues.edu.sv',1,1);
Insert into usuarios (username, role_id, password, p_Nombre, s_Nombre, p_Apellido, s_Apellido, email, estudiante, activo) values('ES20004', 2, '$2a$10$pyzyFYBtmouq64FseUGgiu7fcU10N5c5mOkMC5QTDNkF.eF5/K8ri','Huracan','Catrina','Lombardo','Orellana','LO17115@ues.edu.sv',1,1);
Insert into usuarios (username, role_id, password, p_Nombre, s_Nombre, p_Apellido, s_Apellido, email, estudiante, activo) values('ES20005', 2, '$2a$10$CIRkttDNj0sPS1PPQHESouDDJyCAl3tOJwtPAS/Z6su0bgIQ.xrgC','Jeremias','ernesto','Gomez','Figueroa','GF150645@ues.edu.sv',1,1);

Insert into usuarios (username, role_id, password, p_Nombre, s_Nombre, p_Apellido, s_Apellido, email, estudiante, activo) values('jefe', 4, '$2a$10$3x7JIMOpa3Ohkuxc.K/QvuQaUKmfGuw8jYrl0VJjTR2sDQ9oWUHj6','Jefaso','Salón','Clase','Clara','jefe@mail.com',0,1);

Insert into usuarios (username, role_id, password, p_Nombre, s_Nombre, p_Apellido, s_Apellido, email, estudiante, activo) values('docente1', 3, '$2a$10$WeIKzOdbLlH7w6NwjPxRLuKIH9l4/Pvmk0bkBuID2iMPP371ilq0.','Docente1','Salón','Clase','Clara','docente1@mail.com',0,1);
Insert into usuarios (username, role_id, password, p_Nombre, s_Nombre, p_Apellido, s_Apellido, email, estudiante, activo) values('docente2', 3, '$2a$10$DmnxvcDPqP7PirBZdn6exudP/4MoclMsKrDoMvkGLjytCSvczZQl2','Docente2','Salón','Clase','Clara','docente2@mail.com',0,1);
Insert into usuarios (username, role_id, password, p_Nombre, s_Nombre, p_Apellido, s_Apellido, email, estudiante, activo) values('docente3', 3, '$2a$10$2dtdJWn4Tm4bkmqZ1mdjVO4f4GVgKNBO5FI36Pfd88ToiAp8Elniy','Docente3','Salón','Clase','Clara','docente3@ues.edu.sv',0,1);

Insert into departamentos (nombre) values("Farmacia");
Insert into departamentos (nombre) values("Quimica");
Insert into departamentos (nombre) values("Ciencias Básicas");
Insert into departamentos (nombre) values("Investigación");
Insert into departamentos (nombre) values("Física");

Insert into jefaturas (fecha_registro,departamento_id, jefe_id) values('2020-10-01',3,1);
Update departamentos set jefatura_id=1 where (id=3);

Insert into materias (departamento_id, codigo, nombre) values(3,"mat-115","matematicas 1");
Insert into materias (departamento_id, codigo, nombre) values(3,"mat-215","matematicas 2");
Insert into materias (departamento_id, codigo, nombre) values(3,"mat-315","matematicas 3");
Insert into materias (departamento_id, codigo, nombre) values(3,"mat-415","matematicas 4");
Insert into materias (departamento_id, codigo, nombre) values(2,"biq-115","Bioquímica");
Insert into materias (departamento_id, codigo, nombre) values(5,"fiq-115","Fisioquímica");

INSERT INTO ciclo (anio, semestre, activo) VALUES ('2019', '1', 0);
INSERT INTO ciclo (anio, semestre, activo) VALUES ('2019', '2', 0);
INSERT INTO ciclo (anio, semestre, activo) VALUES ('2020', '1', 0);
INSERT INTO ciclo (anio, semestre, activo) VALUES ('2020', '2', 1);
INSERT INTO ciclo (anio, semestre, activo) VALUES ('2021', '1', 0);

INSERT INTO tipos_evaluacion (clave, descripcion) VALUES ('ex-parc', 'Examen Parcial');
INSERT INTO tipos_evaluacion (clave, descripcion) VALUES ('ex-disc', 'Examen de Discusión');
INSERT INTO tipos_evaluacion (clave, descripcion) VALUES ('ex-labo', 'Examen de Laboratorio');
INSERT INTO tipos_evaluacion (clave, descripcion) VALUES ('tarea', 'Tarea Exaula');
INSERT INTO tipos_evaluacion (clave, descripcion) VALUES ('disc', 'Sesión de Discusión');
INSERT INTO tipos_evaluacion (clave, descripcion) VALUES ('labo', 'Sesión de Laboratorio');


insert into materia_impartida (activo,ciclo_id, materia_id) values (1,4,5);

insert into grupo_teo (activo, numero_grupo, asignatura_id) values (1,1,1);
insert into grupo_teo (activo, numero_grupo, asignatura_id) values (1,2,1);
insert into grupo_dis (activo, numero_grupo, asignatura_id) values (1,1,1);
insert into grupo_dis (activo, numero_grupo, asignatura_id) values (1,2,1);




