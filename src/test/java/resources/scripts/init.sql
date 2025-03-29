INSERT INTO public.parent (id, first_name, last_name, email, password, phone, creation_date, created_by, update_date, updated_by)
VALUES (1, 'B', 'G', 'G@B', 'test', null, '2025-02-26 10:21:22.491410', 'me', '2025-02-26 10:21:22.491410', 'me');

INSERT INTO public.child (id, parent_id, first_name, last_name, birthdate, notes, creation_date, created_by, update_date, updated_by)
VALUES (1, 1, 'B', 'G', '2025-01-26', null, '2025-02-26 10:21:54.968982', '', '2025-02-26 10:21:54.968982', '');