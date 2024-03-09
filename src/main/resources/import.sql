INSERT INTO pais (descripcion) VALUES ('ARGENTINA'), ('BRASIL'), ('CHILE'), ('COLOMBIA'), ('MÉXICO');
INSERT INTO departamento (descripcion, fk_id_pais) VALUES ('ANTIOQUIA', 4), ('ATLÁNTICO', 4), ('BOGOTÁ', 4), ('VALLE DEL CAUCA', 4);
INSERT INTO ciudad (descripcion, fk_id_departamento) VALUES ('BARRANQUILLA', 1), ('BOGOTÁ D.C.', 3), ('CALI', 4), ('ENVIGADO', 1), ('MEDELLÍN', 1);
INSERT INTO tipo_cuenta (descripcion) VALUES ('CUENTA DE AHORRO'), ('CUENTA CORRIENTE');
INSERT INTO tipo_movimiento (descripcion) VALUES ('CONSIGNACIÓN'), ('RETIRO');
INSERT INTO Cliente (dni, nombre, clave) VALUES ('12345678', 'Cliente 1', 'clave1'), ('87654321', 'Cliente 2', 'clave2');
INSERT INTO Cuenta (numero_cuenta, saldo, fk_id_ciudad, fk_id_cliente, fk_id_tipo_cuenta) VALUES ('1234567890123456', 1000.00, 1, 1, 1), ('9876543210987654', 500.00, 2, 2, 2), ('1111222233334444', 1500.00, 3, 1, 1), ('5555666677778888', 2000.00, 4, 2, 2), ('9999000011112222', 800.00, 1, 1, 1);
INSERT INTO Movimiento (fecha, observacion, valor, fk_id_ciudad, fk_id_cuenta, fk_id_tipo_movimiento) VALUES ('2024-03-09', 'Depósito inicial', 1000.00, 1, 1, 1), ('2024-03-09', 'Retiro en cajero', 200.00, 2, 2, 2), ('2024-03-10', 'Transferencia recibida', 500.00, 3, 1, 1), ('2024-03-10', 'Compra con tarjeta', 100.00, 4, 2, 2), ('2024-03-11', 'Depósito en efectivo', 300.00, 3, 1, 1), ('2024-02-09', 'Depósito inicial', 1000.00, 1, 1, 1), ('2024-02-09', 'Retiro en cajero', 200.00, 2, 2, 2), ('2024-02-10', 'Transferencia recibida', 500.00, 3, 1, 1), ('2024-02-10', 'Compra con tarjeta', 100.00, 4, 2, 2), ('2024-02-11', 'Depósito en efectivo', 300.00, 3, 1, 1), ('2024-01-09', 'Depósito inicial', 1000.00, 1, 1, 1), ('2024-01-09', 'Retiro en cajero', 200.00, 2, 2, 2), ('2024-01-10', 'Transferencia recibida', 500.00, 3, 1, 1), ('2024-01-10', 'Compra con tarjeta', 100.00, 4, 2, 2), ('2024-01-11', 'Depósito en efectivo', 300.00, 3, 1, 1), ('2024-01-05', 'Depósito inicial', 1000.00, 1, 1, 1), ('2024-01-06', 'Retiro en cajero', 200.00, 2, 2, 2), ('2024-01-07', 'Transferencia recibida', 500.00, 3, 1, 1), ('2024-01-08', 'Compra con tarjeta', 100.00, 4, 2, 2), ('2024-01-08', 'Depósito en efectivo', 300.00, 3, 1, 1);
