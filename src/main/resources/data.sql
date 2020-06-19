SET FOREIGN_KEY_CHECKS=0;
TRUNCATE TABLE product;
TRUNCATE TABLE discount;

insert into product (id, unit_price, product_code, product_name)
values (1, 40, 'a', 'Product A'),
       (2, 50, 'b', 'Product B'),
       (3, 25, 'c', 'Product C'),
       (4, 20, 'd', 'Product D');

insert into discount (id, discounted_value, minimum_quantity_required, product_id)
values (1, 100, 3, 1),
       (2, 80, 2, 2);
