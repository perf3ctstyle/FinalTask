insert into faculty(id, name, description, admission_plan) values
(1, 'Faculty of Information Technologies And Control', 'This faculty was opened in 1964. ' ||
'It was originally called the Faculty of Automation and Computer Engineering. ' ||
'Today it is a large educational and scientific center, where more than 1900 students of the first ' ||
'and second stages of higher education, representing 20 countries of the world, study, ' ||
'and training is also conducted in English. ' ||
'The educational process at the faculty is provided by a qualified teaching staff.', 100);

insert into faculty(id, name, description, admission_plan) values
(2, 'Faculty of Computer-Aided Design', 'The Faculty of Computer-Aided Design was opened in 1973. ' ||
'It is one of the most innovative and dynamically developing faculties in BSUIR. ' ||
'Faculty training facilities are based on international educational, scientific and production centres ' ||
'and laboratories, equipped with high-end hardware and software.', 5);

insert into faculty(id, name, description, admission_plan) values
(3, 'Faculty of Computer Systems and Networks', 'The Faculty of Computer Systems and Networks is ' ||
'one of the leading faculties in the Republic of Belarus for the training of IT specialists. ' ||
'To date, about 2000 students study at the faculty in four specialties - ' ||
'"Computing machines, systems and networks", "Informatics and programming technologies", ' ||
'"Information technology software", "Electronic computing means".', 50);

insert into faculty(id, name, description, admission_plan) values
(4, 'Faculty of Radioengineering and Electronics', 'The Faculty of Radioengineering and Electronics ' ||
'has a rich history. It was founded in 1964 as a part of Minsk Radio Engineering Institute. ' ||
'The achievements of the Faculty’s scientists are recognised all over the world. ' ||
'Their research outcomes are used in spacecraft and missiles, radiolocation systems, ' ||
'advanced micro- and nanoelectronics.', 5);

insert into faculty(id, name, description, admission_plan) values
(5, 'Faculty of Engineering and Economics', 'The Faculty of Engineering and Economics is a unique faculty ' ||
'in Belarus: it provides its students with integrated knowledge in the fields of economics ' ||
'and modern information technologies, and this creates a distinguished feature of the Faculty graduates ' ||
'among graduates of economic faculties of other Higher Educational Institutions. ' ||
'The Faculty is proud of one of its graduates, Dmitry Shedko, ' ||
'Deputy Minister of Information of the Republic of Belarus.', 15);

insert into faculty(id, name, description, admission_plan) values
(6, 'Faculty of Infocommunications', 'The Faculty of Infocommunications is a wonderful opportunity ' ||
'for those who are eager to get experience in modern electronics and computer science, ' ||
'to master modern information technologies in telecommunications and become ' ||
'a highly sought-after professional. This Faculty was the 1st in Belarus to introduce ' ||
'English-medium degree programmes in the telecommunications sphere in 2009 and ' ||
'is still keeping the leading position today.', 3);

insert into faculty(id, name, description, admission_plan) values
(7, 'Military Faculty', 'The Military Faculty trains regular officers on demand of state military bodies ' ||
'of Belarus. It also trains military students of foreign countries. Also, the Faculty offers ' ||
'the University students an opportunity to gain a military profession of a Junior Leader ' ||
'or Reserve Officer in addition to their full-time studies at the other University faculties. ' ||
'The teaching process is supported by regular officers with vast experience of service in command ' ||
'and in engineering in the Armed Forces of the Republic of Belarus.', 1);

insert into faculty(id, name, description, admission_plan) values
(8, 'Faculty of Distance Education', 'At the Faculty of Distance Education you can study single ' ||
'disciplines using distant educational technologies. After being certified ' ||
'you get an appropriate certificate.', 5);

insert into user(id, login, password_hash, role, is_blocked) values (1, 'admin', md5('admin'), 'ADMIN', false);
insert into credential(id, user_id, name, surname) values (1, 1, 'admin', 'admin');
update user set credential_id = 1 where id = 1;

insert into user(id, login, password_hash, is_blocked) values (2, 'nikita', md5('nikita'), false);
insert into credential(id, user_id, name, surname) values (2, 2, 'Nikita', 'Torop');
update user set credential_id = 2 where id = 2;

insert into user(id, login, password_hash, is_blocked) values (3, 'vasya', md5('vasya'), false);
insert into credential(id, user_id, name, surname) values (3, 3, 'Vasily', 'Savin');
update user set credential_id = 3 where id = 3;

insert into user(id, login, password_hash, is_blocked) values (4, 'petya', md5('petya'), false);
insert into credential(id, user_id, name, surname) values (4, 4, 'Petr', 'Klimuk');
update user set credential_id = 4 where id = 4;

insert into user(id, login, password_hash, is_blocked) values (5, 'sanya', md5('sanya'), false);
insert into credential(id, user_id, name, surname) values (5, 5, 'Alexander', 'Borisov');
update user set credential_id = 5 where id = 5;

insert into user(id, login, password_hash, is_blocked) values (6, 'katya', md5('katya'), false);
insert into credential(id, user_id, name, surname) values (6, 6, 'Ekaterina', 'Stefanenko');
update user set credential_id = 6 where id = 6;

insert into user(id, login, password_hash, is_blocked) values (7, 'ksenia', md5('ksenia'), false);
insert into credential(id, user_id, name, surname) values (7, 7, 'Ksenia', 'Gombalevskaya');
update user set credential_id = 7 where id = 7;

insert into user(id, login, password_hash, is_blocked) values (8, 'olya', md5('olya'), false);
insert into credential(id, user_id, name, surname) values (8, 8, 'Olga', 'Krakovskaya');
update user set credential_id = 8 where id = 8;

insert into user(id, login, password_hash, is_blocked) values (9, 'marina', md5('marina'), false);
insert into credential(id, user_id, name, surname) values (9, 9, 'Marina', 'Kravets');
update user set credential_id = 9 where id = 9;

insert into user(id, login, password_hash, is_blocked) values (10, 'alexandra', md5('alexandra'), false);
insert into credential(id, user_id, name, surname) values (10, 10, 'Alexandra', 'Dubrovskaya');
update user set credential_id = 10 where id = 10;

insert into user(id, login, password_hash, is_blocked) values (11, 'larisa', md5('larisa'), true);
insert into credential(id, user_id, name, surname) values (11, 11, 'Larisa', 'Solovyeva');
update user set credential_id = 11 where id = 11;

insert into user(id, login, password_hash, is_blocked) values (12, 'julia', md5('julia'), true);
insert into credential(id, user_id, name, surname) values (12, 12, 'Julia', 'Grakina');
update user set credential_id = 12 where id = 12;

insert into subject(id, name) values (1, 'Russian language');

insert into subject(id, name) values (2, 'English language');

insert into subject(id, name) values (3, 'Mathematics');