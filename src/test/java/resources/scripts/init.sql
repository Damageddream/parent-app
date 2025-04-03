INSERT INTO public.parent (first_name, last_name, email, password, creation_date, created_by, update_date,
                           updated_by)
VALUES ('Test', 'Parent', 'parent@test.com', 'test123', '2025-03-30 09:00:00', 'test', '2025-03-30 09:00:00',
        'test');

INSERT INTO public.child (parent_id, first_name, last_name, birthdate, notes, creation_date, created_by,
                          update_date, updated_by)
VALUES (1, 'Child', 'Test', '2020-01-01', 'Healthy', '2025-03-30 09:05:00', 'test', '2025-03-30 09:05:00', 'test');

-- Medication
INSERT INTO public.medication (name, brand, description, dosage_form, open_date, instructions, creation_date,
                               created_by, update_date, updated_by)
VALUES ('Paracetamol', 'HealthCo', 'Used to treat pain and fever',
        'Tablet', '2025-03-28', 'Take after meals',
        '2025-03-30 09:10:00', 'test', '2025-03-30 09:10:00', 'test');
-- Prescription
INSERT INTO public.prescription (child_id, medication_id, dosage_amount, dosage_unit, frequency_per_day, start_date,
                                 end_date, notes,
                                 creation_date, created_by, update_date, updated_by)
VALUES (1, 1, 250.0, 'mg', 3, '2025-03-30', '2025-04-10', 'Take after meals, watch for side effects',
        '2025-03-30 09:15:00', 'test', '2025-03-30 09:15:00', 'test');