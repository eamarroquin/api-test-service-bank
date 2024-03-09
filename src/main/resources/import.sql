INSERT INTO pais (descripcion) VALUES ('ARGENTINA'), ('BRASIL'), ('CHILE'), ('COLOMBIA'), ('MÉXICO');
INSERT INTO departamento (descripcion, fk_id_pais) VALUES ('ANTIOQUIA', 4), ('ATLÁNTICO', 4), ('BOGOTÁ', 4), ('VALLE DEL CAUCA', 4);
INSERT INTO ciudad (descripcion, fk_id_departamento) VALUES ('BARRANQUILLA', 1), ('BOGOTÁ D.C.', 3), ('CALI', 4), ('ENVIGADO', 1), ('MEDELLÍN', 1);
