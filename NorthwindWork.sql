

-- Alias - Takma Ad
Select * from customers c
WHERE contact_name LIKE '%a%'
;

-- Joinler
Select * from orders o
inner join customers c 
on o.customer_id = c.customer_id
--

Select * from orders o
right join customers c 
on o.customer_id = c.customer_id
--

select * from customers c

INSERT INTO customers(customer_id, company_name, contact_name, contact_title, address, city, postal_code,country,phone,fax)
VALUES ('HALIT', 'Deneme', 'Halit Kalaycı', 'Abc','Abc','İstanbul','34788','Türkiye','+90', 'abc')


--

select * from orders o 
inner join employees e
ON o.employee_id = e.employee_id

select * from orders o 
right join employees e
ON o.employee_id = e.employee_id

select * from orders o
inner join customer c
on o.customer_id = c.customer_id
inner join order_details od
on o.order_id = od.order_id
inner join products p
on od_product_id = p_p


-- Group BY
Select c.country, c.city, COUNT(*) from customers c
GROUP BY c.country, c.city

Select c.country, COUNT(*) from customers c
GROUP BY c.country
ORDER BY COUNT(*) DESC
--

Select * from customers

Select * from shippers
-- 
Select s.company_name, COUNT(*) from shippers s
inner join orders o 
on s.shipper_id = o.ship_via
GROUP BY s.shipper_id, s.company_name

Select s.company_name, COUNT(o.order_id) from shippers s
left join orders o 
on s.shipper_id = o.ship_via
GROUP BY s.shipper_id, s.company_name
HAVING COUNT(o.order_id) > 250
ORDER BY COUNT(o.order_id) DESC
--

-- Hangi müşteriler 10'dan fazla sipariş vermiş?
Select c.contact_name, COUNT(*) as total_orders from customers c
JOIN orders o on c.customer_id = o.customer_id
GROUP BY c.customer_id , c.contact_name
HAVING COUNT(*) > 10
ORDER BY total_orders DESC
--


-- Toplam cirosu 50k'dan büyük müşteriler

SELECT c.customer_id,c.company_name,c.contact_name,SUM(od.unit_price*od.quantity*(1-od.discount)) FROM customers c
JOIN orders o on c.customer_id = o.customer_id
JOIN order_details od on o.order_id = od.order_id
GROUP BY c.customer_id,c.company_name,c.contact_name
HAVING SUM(od.unit_price*od.quantity*(1-od.discount))>50000

-- Her kategori için en az 5 farklı ürün satan kategoriler
SELECT ca.category_id, ca.category_name, COUNT(p.product_id) FROM categories ca
JOIN products p on ca.category_id = p.category_id
GROUP BY ca.category_id, ca.category_name
HAVING COUNT(p.product_id) >= 5 
ORDER BY COUNT(p.product_id) DESC;

-- Çalışan bazlı toplam satış tutarı (birim fiyat) 

SELECT e.employee_id, e.first_name, e.last_name, SUM(od.unit_price*od.quantity*(1-od.discount)) FROM employees e
JOIN orders o on e.employee_id = o.employee_id
JOIN order_details od on o.order_id = od.order_id
GROUP BY e.employee_id, e.first_name, e.last_name 
ORDER BY SUM(od.unit_price*od.quantity*(1-od.discount)) DESC;

-- Sayfalama?
SELECT * from products p
LIMIT 10
--
Select * from products p
LIMIT 10 OFFSET 5
--

-- 1- Sayfa boyutu? 
-- 2- Aktif sayfa?
-- 1. sayfa, sayfa başı 10 element

-- LIMIT {sayfa_başı_element} OFFSET {(aktif_sayfa-1) * sayfa_başı_element}
Select * from products p
LIMIT 10 OFFSET 0

Select * from products p
LIMIT 10 OFFSET 10