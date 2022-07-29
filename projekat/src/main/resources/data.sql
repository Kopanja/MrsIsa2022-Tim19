INSERT INTO role (role) VALUES ('ADMIN'), ('OWNER'), ('CLIENT');

INSERT INTO city (name) VALUES ('Fruska Gora'), 
('Palic'), 
('Novi Sad'), 
('Beograd'), 
('Bela Crkva'), 
('Tara'), 
('Kopaonik'), 
('Zlatibor');

INSERT INTO user (active,email,firstname,lastname,password,phone_number,city_id,role_id) 
VALUES (true,"kopanja023@gmail.com","Marko","Kopanja","$2a$10$1tLtceUPxsH5QcrXxptRZ.IxwuiAf5iTuuh0kvbbzXiY09Q659xAq","+38162440790",3,1);

INSERT INTO user (active,email,firstname,lastname,password,phone_number,city_id,role_id) 
VALUES (true, "accomowner@gmail.com","Nikola","Pasic","$2a$10$1tLtceUPxsH5QcrXxptRZ.IxwuiAf5iTuuh0kvbbzXiY09Q659xAq","+3816432123",2,2);

INSERT INTO user (active,email,firstname,lastname,password,phone_number,city_id,role_id) 
VALUES (true, "handtclient2@gmail.com","Boat","Owner","$2a$10$1tLtceUPxsH5QcrXxptRZ.IxwuiAf5iTuuh0kvbbzXiY09Q659xAq","+3816432123",3,2);

INSERT INTO user (active,email,firstname,lastname,password,phone_number,city_id,role_id) 
VALUES (true, "handtclient1@gmail.com","Jelena","Tucic","$2a$10$1tLtceUPxsH5QcrXxptRZ.IxwuiAf5iTuuh0kvbbzXiY09Q659xAq","+3816432123",3,3);

INSERT INTO owner (id) 
VALUES (2), (3);

INSERT INTO client (id)
VALUES (4);

INSERT INTO offer (city_id, owner_id,name,address,avaliable_from, avaliable_until,description, num_of_people, price, rating, img_folder_path, thumbnail)
VALUES (3,2,"Etno hotel Vrdnička kula", "Potes pod kulom bb", "2022-05-11T16:00", "2022-09-11T12:00","Objekat Etno hotel Vrdnička kula nudi smeštaj sa restoranom, besplatnim privatnim parkingom, barom i vrtom u Vrdniku, 
na manje od 1 km od Banje Vrdnik. Gostima ovog objekta su na raspolaganju porodične sobe i dečije igralište. Ponuda smeštajnog objekta uključuje 
recepciju koja radi non-stop, prevoz od/do aerodroma, poslugu u sobi i besplatan WiFi.",8,6999,4.5,"Etno hotel Vrdnička kula","VrdnickaKula.jpg"),
(2,2,"Drvena Kuca RUŽA","neka ulica 3221","2022-05-11T16:00","2022-09-11T12:00","Set in Kopaonik in the Central Serbia region, Drvena Kuca RUŽA offers accommodation with free WiFi and free private parking, 
as well as access to a Turkish bath. The accommodation features a spa bath and a sauna.", 8, 5999, 3.8, "Drvena Kuca RUŽA","DrvenaKucaRuza.jpg");


INSERT INTO accommodation (room_number, id)
VALUES (2,1), (4,2);


INSERT INTO review (is_accepted, rating,review_text,client_id,offer_id) VALUES (true,4,"Cool",4,1);