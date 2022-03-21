--Password alex123
INSERT INTO User (id, firstname, lastname, username, password, salary, age) VALUES (1, 'Alex','Knr', 'alex123','$2a$04$4vwa/ugGbBVDvbWaKUVZBuJbjyQyj6tqntjSmG8q.hi97.xSdhj/2', 3456, 33);
INSERT INTO User (id, firstname, lastname, username, password, salary, age)  VALUES (2, 'Tom', 'Asr', 'tom234', '$2a$04$QED4arFwM1AtQWkR3JkQx.hXxeAk/G45NiRd3Q4ElgZdzGYCYKZOW', 7823, 23);
INSERT INTO User (id, firstname, lastname, username, password, salary, age)  VALUES (3, 'Adam', 'Psr', 'adam', '$2a$04$WeT1SvJaGjmvQj34QG8VgO9qdXecKOYKEDZtCPeeIBSTxxEhazNla', 4234, 45);

INSERT INTO ORDER2(id, lastname, totalPrice,canceled,finished) VALUES (-1, 'unfinished',15.85, null, null);
INSERT INTO ORDER2(id, lastname, totalPrice,canceled,finished) VALUES (-2, 'canceled',15.85, '2020-01-01', null);
INSERT INTO ORDER2(id, lastname, totalPrice,canceled,finished) VALUES (-3, 'finished',15.85, null, '2020-01-01');

INSERT INTO PRODUCT(id, name, description, imageName, price, validfrom, validto) values (-1, 'Himmelsklang', 'Das legendäre Himmelsklang-Schwert von der Himmelschmiede geschmiedet', 'assets/Pictures/Sword1.jpg', 119.90, '2020-01-01','2099-01-01');
INSERT INTO PRODUCT(id, name, description, imageName, price, validfrom, validto) values (-2, 'Drachen Dolche', 'Die Drachen Dolche, von der Zwergenschmiede geschmiedet', 'assets/Pictures/Dolch1.jpg', 89.90, '2020-01-01','2099-01-01');
INSERT INTO PRODUCT(id, name, description, imageName,  price, validfrom, validto) values (-3, 'Balguhrs Axt', 'Die Axt von Bahlgur, von Himmelschmiede geschmiedet', 'assets/Pictures/Axt1.jpg', 144.90, '2020-01-01','2099-01-01');
