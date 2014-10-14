-- Sequence: autonumerico

-- DROP SEQUENCE autonumerico;

CREATE SEQUENCE autonumerico
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE autonumerico
  OWNER TO marcelo;
-- Sequence: seq_compra

-- DROP SEQUENCE seq_compra;

CREATE SEQUENCE seq_compra
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_compra
  OWNER TO marcelo;
-- Sequence: seq_det_compra

-- DROP SEQUENCE seq_det_compra;

CREATE SEQUENCE seq_det_compra
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_det_compra
  OWNER TO marcelo;
-- Sequence: seq_det_venta

-- DROP SEQUENCE seq_det_venta;

CREATE SEQUENCE seq_det_venta
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_det_venta
  OWNER TO marcelo;
-- Sequence: seq_pagos

-- DROP SEQUENCE seq_pagos;

CREATE SEQUENCE seq_factura
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_factura
  OWNER TO marcelo;
-- Sequence: seq_producto

-- DROP SEQUENCE seq_producto;

CREATE SEQUENCE seq_producto
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_producto
  OWNER TO marcelo;
-- Sequence: seq_proveedor

-- DROP SEQUENCE seq_proveedor;

CREATE SEQUENCE seq_proveedor
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_proveedor
  OWNER TO marcelo;
-- Sequence: seq_venta

-- DROP SEQUENCE seq_venta;

CREATE SEQUENCE seq_venta
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_venta
  OWNER TO marcelo;

-- Table: productos

-- DROP TABLE productos;

CREATE TABLE productos
(
  id integer NOT NULL DEFAULT nextval('seq_producto'::regclass),
  nombre text,
  descripcion text,
  precio integer,
  stock integer,
  stock_minimo integer,
  CONSTRAINT stock_positivo CHECK (stock >= 0),
  CONSTRAINT id_producto PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE productos
  OWNER TO marcelo;


-- Table: clientes

-- DROP TABLE clientes;

CREATE TABLE clientes
(
  id integer NOT NULL DEFAULT nextval('autonumerico'::regclass),
  nombre text,
  email text,
  ci integer,
  CONSTRAINT id_cliente PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE clientes
  OWNER TO marcelo;


-- Table: proveedores

-- DROP TABLE proveedores;

CREATE TABLE proveedores
(
  id integer NOT NULL DEFAULT nextval('seq_proveedor'::regclass),
  nombre text,
  email text,
  ci integer,
  CONSTRAINT id_proveedor PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE proveedores
  OWNER TO marcelo;



-- Table: compras

-- DROP TABLE compras;

CREATE TABLE compras
(
  id integer NOT NULL DEFAULT nextval('seq_compra'::regclass),
  fk_proveedor integer,
  fecha text,
  CONSTRAINT id_compra PRIMARY KEY (id),
  CONSTRAINT compras_fk_proveedor_fkey FOREIGN KEY (fk_proveedor)
      REFERENCES proveedores (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE compras
  OWNER TO marcelo;


-- Table: detalle_compra

-- DROP TABLE detalle_compra;

CREATE TABLE detalle_compra
(
  id integer NOT NULL DEFAULT nextval('seq_det_compra'::regclass),
  fk_producto integer,
  fk_compra integer,
  precio integer,
  cantidad integer,
  CONSTRAINT compra_positiva CHECK (cantidad>0),
  CONSTRAINT detalle_compra_pkey PRIMARY KEY (id),
  CONSTRAINT detalle_compra_fk_compra_fkey FOREIGN KEY (fk_compra)
      REFERENCES compras (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT detalle_compra_fk_producto_fkey FOREIGN KEY (fk_producto)
      REFERENCES productos (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE detalle_compra
  OWNER TO marcelo;

-- Table: ventas

-- DROP TABLE ventas;

CREATE TABLE ventas
(
  id integer NOT NULL DEFAULT nextval('seq_venta'::regclass),
  id_cliente integer,
  fecha text,
  CONSTRAINT ventas_pkey PRIMARY KEY (id),
  CONSTRAINT ventas_id_cliente_fkey FOREIGN KEY (id_cliente)
      REFERENCES clientes (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ventas
  OWNER TO marcelo;


-- Table: detalle_venta

-- DROP TABLE detalle_venta;

CREATE TABLE detalle_venta
(
  id integer NOT NULL DEFAULT nextval('seq_det_venta'::regclass),
  fk_producto integer,
  fk_venta integer,
  precio integer,
  cantidad integer,
  CONSTRAINT venta_positiva CHECK (cantidad>0),
  CONSTRAINT detalle_venta_pkey PRIMARY KEY (id),
  CONSTRAINT detalle_venta_fk_compra_fkey FOREIGN KEY (fk_venta)
      REFERENCES ventas (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT detalle_venta_fk_producto_fkey FOREIGN KEY (fk_producto)
      REFERENCES productos (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE detalle_venta
  OWNER TO marcelo;

-- Table: facturas

-- DROP TABLE facturas;

CREATE TABLE facturas
(
  id integer NOT NULL DEFAULT nextval('seq_factura'::regclass),
  fk_venta integer,
  monto integer,
  fecha text,
  CONSTRAINT facturas_pkey PRIMARY KEY (id),
  CONSTRAINT facturas_fk_venta_fkey FOREIGN KEY (fk_venta)
      REFERENCES ventas (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE facturas
  OWNER TO marcelo;

-- Table: proveedor_producto

-- DROP TABLE proveedor_producto;

CREATE TABLE proveedor_producto
(
  fk_producto integer NOT NULL,
  fk_proveedor integer NOT NULL,
  CONSTRAINT proveedor_producto_pkey PRIMARY KEY (fk_producto, fk_proveedor),
  CONSTRAINT proveedor_producto_fk_producto_fkey FOREIGN KEY (fk_producto)
      REFERENCES productos (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT proveedor_producto_fk_proveedor_fkey FOREIGN KEY (fk_proveedor)
      REFERENCES proveedores (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE proveedor_producto
  OWNER TO marcelo;



