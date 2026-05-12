CREATE TABLE IF NOT EXISTS recharge_plans (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    operator VARCHAR(100),
    circle VARCHAR(100),
    amount DECIMAL(10,2),
    validity VARCHAR(100),
    description VARCHAR(250),
    plan_type VARCHAR(50),
    data_limit DECIMAL(5,2)
);

CREATE TABLE IF NOT EXISTS transactions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(100),
    txn_id VARCHAR(100),
    status VARCHAR(50),
    payment_mode VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);