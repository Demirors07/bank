-- ------------------------------
-- 1️⃣ Customer Tablosu
-- ------------------------------
CREATE TABLE IF NOT EXISTS customer (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(16) NOT NULL,
    address VARCHAR(32),
    city VARCHAR(16),
    PRIMARY KEY (id)
);

-- Örnek veri
INSERT INTO customer (name, address, city) VALUES
('Ali Veli', 'İstiklal Cad. No:10', 'İstanbul'),
('Ayşe Yılmaz', 'Atatürk Cad. No:5', 'Ankara'),
('Mehmet Demir', 'Cumhuriyet Cad. No:23', 'İzmir');


-- ------------------------------
-- 2️⃣ Account Tablosu
-- ------------------------------
CREATE TABLE IF NOT EXISTS account (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(16) NOT NULL,
    balance DECIMAL(15,2) DEFAULT 0.00,
    PRIMARY KEY (id)
);

-- Örnek veri
INSERT INTO account (name, balance) VALUES
('Vadesiz TL', 5000.00),
('Vadeli TL', 15000.00),
('Döviz', 20000.00);


-- ------------------------------
-- 4️⃣ Loan Tablosu
-- ------------------------------
CREATE TABLE IF NOT EXISTS loan (
    id INT NOT NULL AUTO_INCREMENT,
    customer_id INT NOT NULL,
    account_id INT NOT NULL,
    date DATE NOT NULL,
    amount DECIMAL(15,2) NOT NULL CHECK (amount <= 10000),
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customer(id),
    FOREIGN KEY (account_id) REFERENCES account(id)
);

-- Örnek veri
INSERT INTO loan (customer_id, account_id, date, amount) VALUES
(1, 1, '2026-02-01', 5000.00),
(2, 2, '2026-02-03', 7000.00),
(3, 1, '2026-02-05', 3000.00);
