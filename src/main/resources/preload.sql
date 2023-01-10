BEGIN

INSERT INTO
DRONE(id, serialNumber, model, weightLimit, batteryCapacity, state)
VALUES
("fd6bd5c9-0f21-49ce-9a29-b65aeec3314c", "ABCD1234","Lightweight", 200, 100, "LOADING"),
("fd6bd5c9-0f21-49ce-9a29-b65aeec33143", "ABC1234","Heavyweight", 500, 100, "IDLE");

COMMIT;

INSERT INTO
MEDICATION(id, name, weight, code, drone_id)
VALUES
("69b8263e-558b-4958-ae8d-bd6f85b16640", "Bandage", 90, "12345_ADS", "fd6bd5c9-0f21-49ce-9a29-b65aeec3314c")

COMMIT;

END;

