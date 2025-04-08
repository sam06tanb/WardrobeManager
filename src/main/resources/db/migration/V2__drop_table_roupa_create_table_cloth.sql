DROP TABLE IF EXISTS roupa;

CREATE TABLE tb_cloth (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    size VARCHAR(10) NOT NULL,
    color VARCHAR(50) NOT NULL
);

INSERT INTO tb_cloth (name, size, color) VALUES
    ('Basic T-Shirt', 'sizeM', 'White'),
    ('Denim Jacket', 'sizeG', 'Blue'),
    ('Hoodie', 'sizeGG', 'Black'),
    ('Tank Top', 'sizeP', 'Gray'),
    ('Sweater', 'sizeM', 'Beige'),
    ('Leather Jacket', 'sizeG', 'Brown'),
    ('Crop Top', 'sizePP', 'Pink'),
    ('Polo Shirt', 'sizeM', 'Green'),
    ('Jeans', 'sizeGG', 'Dark Blue'),
    ('Windbreaker', 'sizeG', 'Red');
