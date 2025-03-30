INSERT INTO public.parent (id, first_name, last_name, email, password, phone, creation_date, created_by, update_date, updated_by)
VALUES (1, 'Test', 'Parent', 'parent@test.com', 'test123', null, '2025-02-26 10:00:00', 'test', '2025-02-26 10:00:00', 'test');

INSERT INTO public.child (id, parent_id, first_name, last_name, birthdate, notes, creation_date, created_by, update_date, updated_by)
VALUES (1, 1, 'Test', 'Child', '2020-01-01', 'Healthy', '2025-02-26 10:10:00', 'test', '2025-02-26 10:10:00', 'test');
