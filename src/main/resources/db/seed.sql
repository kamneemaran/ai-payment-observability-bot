INSERT INTO recharge_plans (name, operator, circle, amount, validity, description, plan_type, data_limit)
VALUES
    ('Validity Plan', 'JIO', 'MHG', 399, 30, "VALIDITY PLAN", "VALIDITY", 100),
    ('Data pack Plan', 'VI', 'GJ', 49, 1, 'data pack 2GB/day', "DATA",2.0),
    ('Talktime Plan', 'BSNL', 'MP', 69, 3, 'Talktime plan for 3 days', "TALKTIME",0),
    ('Validity Plan', 'JIO', 'MHG', 400, 30, "VALIDITY PLAN", "VALIDITY",100),
    ('Data pack Plan', 'VI', 'GJ', 199, 18, 'data pack 2GB/day', "DATA", 2.0),
    ('Validity Plan', 'BSNL', 'MP', 599, 10, 'Talktime plan for 3 days', "VALIDITY",8.0),
    ('Validity Plan', 'JIO', 'MHG', 799, 84, "VALIDITY PLAN", "VALIDITY", 100),
    ('Validity Plan', 'VI', 'GJ', 3099, 365, 'data pack 2GB/day', "VALIDITY", 10.0),
    ('Talktime Plan', 'BSNL', 'MP', 69, 15, 'Talktime plan for 3 days', "TALKTIME", 0);

INSERT INTO transactions (user_id, txn_id, status, payment_mode)
VALUES
    ('USER123', 'txn123', 'SUCCESS', 'CC'),
    ('USER234', 'txn234', 'FAILED', 'WALLET'),
    ('USER345', 'txn345', 'SUCCESS', 'DC'),
    ('USER0', 'txn456', 'IN_PROGRESS', 'CC'),
    ('USER567', 'txn1', 'SUCCESS', 'CC'),
    ('USER678', 'txn2', 'FAILED', 'WALLET'),
    ('USER0', 'txn3', 'SUCCESS', 'DC'),
    ('USER0', 'txn4', 'IN_PROGRESS', 'CC'),
    ('USER5', 'txn5', 'FAILED', 'CC'),
    ('USER6', 'txn6', 'FAILED', 'DC'),
    ('USER7', 'txn7', 'FAILED', 'DC'),
    ('USER8', 'txn8', 'FAILED', 'CC'),
    ('USER0', 'txn0', 'SUCCESS', 'UPI');