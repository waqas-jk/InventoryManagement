
INSERT INTO brand (id, name) VALUES (1,'Brand 1');
INSERT INTO brand (id, name) VALUES (2,'Brand 2');

INSERT INTO country (id, name) VALUES (1,'USA');
INSERT INTO country (id, name) VALUES (2,'Ireland');
INSERT INTO country (id, name) VALUES (3,'Netherlands');
INSERT INTO country (id, name) VALUES (4,'Dubai');
INSERT INTO country (id, name) VALUES (5,'Australia');
INSERT INTO country (id, name) VALUES (6,'Italy');
INSERT INTO country (id, name) VALUES (7,'Pakistan');
INSERT INTO country (id, name) VALUES (8,'Mexico');

INSERT INTO product_size (id, name) VALUES (1,'10 ml');
INSERT INTO product_size (id, name) VALUES (2,'20 ml');
INSERT INTO product_size (id, name) VALUES (3,'30 ml');
INSERT INTO product_size (id, name) VALUES (6,'Large');
INSERT INTO product_size (id, name) VALUES (4,'Small ');

INSERT INTO product_type (id, name, is_Internal) VALUES (1,'Finished Product','F');
INSERT INTO product_type (id, name, is_Internal) VALUES (2,'Component','T');
INSERT INTO product_type (id, name, is_Internal) VALUES (3,'Packaging Material','T');

INSERT INTO office (id, name, country_id, city) VALUES (1,'USA Office',1,'NewYork');
INSERT INTO office (id, name, country_id, city) VALUES (2,'Ireland Office',2,'Dublin');

INSERT INTO warehouse (id, name, office_id, city, is_deleted) VALUES (1,'Warehouse 1',1,'NewYork','F');
INSERT INTO warehouse (id, name, office_id, city, is_deleted) VALUES (2,'Warehouse 2',1,'Washington','F');
INSERT INTO warehouse (id, name, office_id, city, is_deleted) VALUES (3,'Warehouse 3',1,'Dallas','F');
INSERT INTO warehouse (id, name, office_id, city, is_deleted) VALUES (4,'Warehouse 4',2,'Dublin','F');
INSERT INTO warehouse (id, name, office_id, city, is_deleted) VALUES (5,'Warehouse 5',2,'Cork','F');

INSERT INTO product (id, name, description, brand_id, product_type_id, is_deleted) VALUES (1,'Product A',NULL,1,1,'F');

INSERT INTO product_size_mapping (id, product_id, product_size_id) VALUES (1,1,1);
INSERT INTO product_size_mapping (id, product_id, product_size_id) VALUES (2,1,2);
INSERT INTO product_size_mapping (id, product_id, product_size_id) VALUES (3,1,3);

INSERT INTO inventory (warehouse_id, product_id, product_size_id, moq, qpb, reorder_point, in_stock_quantity, in_transit_quantity, available_quantity) VALUES (1,1,1,50,6,15,10,5,8);
INSERT INTO inventory (warehouse_id, product_id, product_size_id, moq, qpb, reorder_point, in_stock_quantity, in_transit_quantity, available_quantity) VALUES (2,1,2,1000,12,100,900,1000,100);
INSERT INTO inventory (warehouse_id, product_id, product_size_id, moq, qpb, reorder_point, in_stock_quantity, in_transit_quantity, available_quantity) VALUES (2,1,3,34,43,9,150,80,90);
INSERT INTO inventory (warehouse_id, product_id, product_size_id, moq, qpb, reorder_point, in_stock_quantity, in_transit_quantity, available_quantity) VALUES (3,1,1,45,50,100,100,30,50);
INSERT INTO inventory (warehouse_id, product_id, product_size_id, moq, qpb, reorder_point, in_stock_quantity, in_transit_quantity, available_quantity) VALUES (3,1,3,50,36,45,40,12,32);
INSERT INTO inventory (warehouse_id, product_id, product_size_id, moq, qpb, reorder_point, in_stock_quantity, in_transit_quantity, available_quantity) VALUES (4,1,1,1,4,6,10,5,7);
INSERT INTO inventory (warehouse_id, product_id, product_size_id, moq, qpb, reorder_point, in_stock_quantity, in_transit_quantity, available_quantity) VALUES (4,1,3,5,36,10,100,0,100);
INSERT INTO inventory (warehouse_id, product_id, product_size_id, moq, qpb, reorder_point, in_stock_quantity, in_transit_quantity, available_quantity) VALUES (5,1,1,10,8,10,10,100,0);

