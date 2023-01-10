DELETE FROM public.drone;
DELETE FROM public.medication;

INSERT INTO public.drone(id, serial_number, model, weight_limit, battery_capacity, state) VALUES ('fd6bd5c9-0f21-49ce-9a29-b65aeec3314c', 'ABCD1234','LIGHTWEIGHT', 200, 100, 'LOADING');
INSERT INTO public.drone(id, serial_number, model, weight_limit, battery_capacity, state) VALUES ('fd6bd5c9-0f21-49ce-9a29-b65aeec33143', 'ABC1234','HEAVYWEIGHT', 500, 100, 'IDLE');
INSERT INTO public.drone(id, serial_number, model, weight_limit, battery_capacity, state) VALUES ('6f74fa28-bf1e-49e5-843c-54ea91d8c5d9', 'XYZ123','HEAVYWEIGHT', 500, 25, 'IDLE');

INSERT INTO public.medication(id, name, weight, code, drone_id) VALUES ('69b8263e-558b-4958-ae8d-bd6f85b16640', 'Bandage', 90, '12345_ADS', 'fd6bd5c9-0f21-49ce-9a29-b65aeec3314c');
