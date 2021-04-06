DROP TABLE IF EXISTS CLIENTES;
 
CREATE TABLE IF NOT EXISTS CLIENTES (
  `id` bigint(11) AUTO_INCREMENT PRIMARY KEY,
  `primer_nombre` varchar(250) NOT NULL,
  `segundo_nombre` varchar(250) NOT NULL,
  `primer_apellido` varchar(250) NOT NULL,
  `segundo_apellido` varchar(250) NOT NULL,
  `estado` int(11) NOT NULL
);
