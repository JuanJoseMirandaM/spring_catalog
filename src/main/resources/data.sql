INSERT INTO t_users (id, name, email, password, status, created_at, updated_at, user_id) VALUES
(1, 'Juan Jose Miranda', 'juanjose@spring.com', '12345', 'CREATED', '2020-01-20', '2020-01-20', 1);

INSERT INTO t_brands (id, name, status, created_at, updated_at, user_id)  VALUES
(1, 'apple', 'CREATED','2020-01-20', '2020-01-20', 1),
(2, 'lenovo', 'CREATED','2020-01-20', '2020-01-20', 1),
(3, 'asus', 'CREATED', '2020-01-20', '2020-01-20', 1);

INSERT INTO t_products (id, name, description, stock, price, status, created_at, updated_at, user_id, brand_id) VALUES
(1, 'MAcBook Pro 13" 2020', 'macbook pro', '5', '320.90', 'CREATED', '2020-01-20', '2020-01-20', 1, 1),
(2, 'Vivobook', 'modelo 2015', '7', '150.45', 'CREATED', '2020-01-20', '2020-01-20', 1, 3),
(3, 'Yoga 365', '2 in 1', '2', '180.50', 'CREATED', '2020-01-20', '2020-01-20', 1, 2);