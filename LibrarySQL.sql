--- Bir kütüphane sistemi veritabanı tasarlıyoruz. 
--- En klasik senaryolar olmak zorunda, ekstralar eklenebilir.
--- Kitap, Öğrenci, Görevli, Ödünç Alma, İade, Ceza
--- Bunların tamamı .dql olarak oluşturulacak. 
--- DDL Komutları
--- *DML komutları (Her tabloya min. 5 DML komutu)
--- .sql, .txt

----------------------------------LIBRARY DATABASE SYSTEM----------------------------------
----- Created By Bahadır Sina Terzioğlu -----

-- CREATE TABLES (DDL) --
CREATE TABLE students(
	id SERIAL PRIMARY KEY, 
	name VARCHAR(100) NOT NULL,  
	surname VARCHAR(100) NOT NULL,
	email VARCHAR(100) UNIQUE NOT NULL,
	phone VARCHAR(100) NOT NULL, 
	student_no VARCHAR(100) NOT NULL
);

CREATE TABLE books(
	id SERIAL PRIMARY KEY, 
	name_book VARCHAR(100) NOT NULL,  
	isbn VARCHAR(100) NOT NULL,
	stock  INTEGER NOT NULL,
	publication_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE employees(
	id SERIAL PRIMARY KEY, 
	name VARCHAR(100) NOT NULL,  
	surname VARCHAR(100) NOT NULL,
	email VARCHAR(100) UNIQUE NOT NULL,
	phone VARCHAR(100) NOT NULL, 
	role VARCHAR(100) NOT NULL
);

CREATE TABLE borrow(
	id SERIAL PRIMARY KEY, 
	borrow_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
	last_restitute_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	student_id INTEGER NOT NULL, 
	FOREIGN KEY (student_id) REFERENCES students(id),
	book_id INTEGER NOT NULL, 
	FOREIGN KEY (book_id) REFERENCES books(id),
	employee_id INTEGER NOT NULL, 
	FOREIGN KEY (employee_id) REFERENCES employees(id)
);

CREATE TABLE restitute(
	id SERIAL PRIMARY KEY, 
	return_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
	delay_day_count  INTEGER NOT NULL,
	borrow_id INTEGER NOT NULL, 
	FOREIGN KEY (borrow_id) REFERENCES borrow(id),
	employee_id INTEGER NOT NULL, 
	FOREIGN KEY (employee_id) REFERENCES employees(id)
);

CREATE TABLE fine(
	id SERIAL PRIMARY KEY, 
	paid_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
	paid BOOLEAN NOT NULL,
	date_fine_price DOUBLE PRECISION NOT NULL, 
	borrow_id INTEGER NOT NULL, 
	FOREIGN KEY (borrow_id) REFERENCES borrow(id),
	employee_id INTEGER NOT NULL, 
	FOREIGN KEY (employee_id) REFERENCES employees(id)
);

-- ADD DATAS -- 
INSERT INTO students (name, surname, email, phone, student_no) VALUES
('bahadir', 'terzioglu', 'bahadirterzioglu@gmail.com', '0511111111', '2023001'),
('eda', 'erbay', 'edaerbay@gmail.com', '052222222', '2023002'),
('babur', 'onceler', 'baburonceler@gmail.com', '0533333333', '2023003'),
('taha', 'erturk', 'tahaerturk@gmail.com', '0544444444', '2023004'),
('emin', 'reyhani', 'eminreyhani@gmail.com', '05555555555', '2023005');

INSERT INTO books (name_book, isbn, stock, publication_date) VALUES
('Suç ve Ceza', '9789750719387', 3, '2020-01-15 10:00:00'),
('Tutunamayanlar', '9789754700114', 5, '2019-03-20 11:30:00'),
('Kürk Mantolu Madonna', '9789753638029', 4, '2021-06-10 09:15:00'),
('Sefiller', '9786053320012', 2, '2018-09-05 14:45:00'),
('1984', '9789750718533', 6, '2022-11-25 16:20:00');

INSERT INTO employees (name, surname, email, phone, role) VALUES
('arda', 'erdogdu', 'ardaerdogu@gmail.com', '056666666', 'Librarian'),
('furkan', 'yavuz', 'furkanyavuz@gmail.com', '057777777', 'Assistant'),
('koray', 'cakmakli', 'koraycakmakli@gmail.com', '0588888888', 'Archivist'),
('berat', 'asar', 'beratasar@gmail.com', '059999999', 'Librarian'),
('buse', 'kara', 'busekara@gmail.com', '050000000', 'Manager');

INSERT INTO borrow (borrow_date, last_restitute_date, student_id, book_id, employee_id) VALUES
('2026-04-01 09:00:00', '2026-04-10 17:00:00', 1, 1, 1),
('2026-04-02 10:30:00', '2026-04-12 17:00:00', 2, 2, 2),
('2026-04-03 11:15:00', '2026-04-13 17:00:00', 1, 3, 3),
('2026-04-04 14:00:00', '2026-04-14 17:00:00', 3, 1, 4),
('2026-04-05 15:45:00', '2026-04-15 17:00:00', 4, 4, 5);

INSERT INTO restitute (return_date, delay_day_count, borrow_id, employee_id) VALUES
('2026-04-11 10:00:00', 1, 1, 1),
('2026-04-12 16:20:00', 0, 2, 2),
('2026-04-15 09:10:00', 2, 3, 3),
('2026-04-14 13:40:00', 0, 4, 2),
('2026-04-18 11:25:00', 3, 5, 4);

INSERT INTO fine (paid_date, paid, date_fine_price, borrow_id, employee_id) VALUES
('2026-04-12 12:00:00', true, 15.50, 1, 1),
('2026-04-13 14:30:00', false, 0.00, 2, 2),
('2026-04-16 10:45:00', true, 25.00, 3, 3),
('2026-04-15 15:10:00', false, 0.00, 4, 4),
('2026-04-19 09:50:00', true, 35.75, 5, 5);

-- UPDATE, DELETE, SELECT (DML) -- 

-- students table operations --
UPDATE students SET name = 'osman', surname = 'kacar', email = 'osmankacar@hotmail.com', phone= '050111101', student_no = '2025001'
WHERE student_no = '2023003';

UPDATE students SET phone= '053222222'
WHERE student_no = '2023002';

SELECT * FROM students;

SELECT * FROM students WHERE name LIKE 'b%';

ALTER TABLE students ADD COLUMN age INT;

UPDATE students SET age=24 WHERE id=1;
UPDATE students SET age=25 WHERE id=2;
UPDATE students SET age=23 WHERE id=3;
UPDATE students SET age=21 WHERE id=4;
UPDATE students SET age=24 WHERE id=5;

SELECT * FROM students ORDER BY age asc;

-- books table operations -- 
UPDATE books
SET stock = stock - 1
WHERE id = 2;

UPDATE books
SET publication_date = '2023-01-10 09:30:00'
WHERE name_book = '1984';

SELECT * FROM books;

SELECT name_book, stock
FROM books
WHERE stock <= 3;

SELECT name_book, isbn
FROM books
WHERE name_book ILIKE '%a%';

SELECT COUNT(*) AS total_book_count
FROM books;

SELECT *
FROM books
ORDER BY stock DESC;

-- employees table operations -- 

UPDATE employees
SET role = 'Senior Librarian'
WHERE id = 1;

UPDATE employees
SET phone = '05012345678'
WHERE email = 'furkanyavuz@gmail.com';

SELECT * FROM employees;

SELECT name, surname, role
FROM employees
WHERE role = 'Librarian';

SELECT role, COUNT(*) AS employee_count
FROM employees
GROUP BY role;

SELECT name, surname
FROM employees
ORDER BY surname ASC;

-- borrow table operations -- 

UPDATE borrow
SET last_restitute_date = '2026-04-18 17:00:00'
WHERE id = 2;

UPDATE borrow
SET employee_id = 4
WHERE id = 3;

SELECT * FROM borrow;

SELECT student_id, COUNT(*) AS total_borrow
FROM borrow
GROUP BY student_id
ORDER BY total_borrow DESC;

SELECT book_id, COUNT(*) AS how_many_times_borrowed
FROM borrow
GROUP BY book_id
ORDER BY how_many_times_borrowed DESC;

SELECT *
FROM borrow
WHERE borrow_date BETWEEN '2026-04-02 00:00:00' AND '2026-04-05 23:59:59';

SELECT s.name, s.surname, b.name_book, br.borrow_date, br.last_restitute_date
FROM borrow br
JOIN students s ON br.student_id = s.id
JOIN books b ON br.book_id = b.id
ORDER BY br.borrow_date ASC;

-- restitute table operations -- 
UPDATE restitute
SET delay_day_count = 4
WHERE id = 5;

UPDATE restitute
SET employee_id = 1
WHERE id = 4;

SELECT * FROM restitute;

SELECT *
FROM restitute
WHERE delay_day_count > 0;

SELECT AVG(delay_day_count) AS average_delay_day
FROM restitute;

SELECT MAX(delay_day_count) AS max_delay_day
FROM restitute;

SELECT r.id, r.return_date, r.delay_day_count, e.name, e.surname
FROM restitute r
JOIN employees e ON r.employee_id = e.id
ORDER BY r.return_date ASC;
-- fine table operations -- 
UPDATE fine
SET paid = true, paid_date = '2026-04-20 15:00:00'
WHERE id = 2;

UPDATE fine
SET date_fine_price = 30.00
WHERE borrow_id = 3;

SELECT * FROM fine;

SELECT *
FROM fine
WHERE paid = false;

SELECT SUM(date_fine_price) AS total_fine_amount
FROM fine;

SELECT AVG(date_fine_price) AS average_fine_amount
FROM fine;

SELECT f.id, s.name, s.surname, bk.name_book, f.date_fine_price, f.paid
FROM fine f
JOIN borrow br ON f.borrow_id = br.id
JOIN students s ON br.student_id = s.id
JOIN books bk ON br.book_id = bk.id
ORDER BY f.date_fine_price DESC;


-- Bir öğrencinin aldığı kitaplar ve aldığı ceza sayısı
SELECT s.name, s.surname, COUNT(DISTINCT br.id) AS borrowed_book_count, COUNT(DISTINCT f.id) AS fine_count
FROM students s
LEFT JOIN borrow br ON s.id = br.student_id
LEFT JOIN fine f ON br.id = f.borrow_id
WHERE s.id = 1
GROUP BY s.id, s.name, s.surname;
-- Hangi öğrenci hangi kitabı almış 
SELECT s.name, s.surname, b.name_book, br.borrow_date
FROM borrow br
JOIN students s ON br.student_id = s.id
JOIN books b ON br.book_id = b.id
ORDER BY br.borrow_date;
-- Gecikmeli iade yapan öğrenciler hangileri
SELECT s.name, s.surname, b.name_book, r.delay_day_count
FROM restitute r
JOIN borrow br ON r.borrow_id = br.id
JOIN students s ON br.student_id = s.id
JOIN books b ON br.book_id = b.id
WHERE r.delay_day_count > 0
ORDER BY r.delay_day_count DESC;

-- En çok kitap alan öğrenciyi bulma
SELECT s.name, s.surname, COUNT(br.id) AS total_borrow
FROM students s
JOIN borrow br ON s.id = br.student_id
GROUP BY s.id, s.name, s.surname
ORDER BY total_borrow DESC
LIMIT 1;
-- Cezayı hangi personel işlemiş onu sorguladım
SELECT e.name, e.surname, COUNT(f.id) AS total_fine_process
FROM employees e
JOIN fine f ON e.id = f.employee_id
GROUP BY e.id, e.name, e.surname
ORDER BY total_fine_process DESC;