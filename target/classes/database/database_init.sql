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

insert into user(login, password, role, is_blocked) values ('admin', md5('admin'), 'ADMIN', false);
insert into user_credential(user_id, name, surname) values (1, 'admin', 'admin');

insert into user(login, password, is_blocked) values ('nikita', md5('nikita'), false);
insert into user_credential(user_id, name, surname) values (2, 'Nikita', 'Torop');

insert into user(login, password, is_blocked) values ('vasya', md5('vasya'), false);
insert into user_credential(user_id, name, surname) values (3, 'Vasily', 'Savin');

insert into user(login, password, is_blocked) values ('petya', md5('petya'), false);
insert into user_credential(user_id, name, surname) values (4, 'Petr', 'Klimuk');

insert into user(login, password, is_blocked) values ('sanya', md5('sanya'), false);
insert into user_credential(user_id, name, surname) values (5, 'Alexander', 'Borisov');

insert into user(login, password, is_blocked) values ('katya', md5('katya'), false);
insert into user_credential(user_id, name, surname) values (6, 'Ekaterina', 'Stefanenko');

insert into user(login, password, is_blocked) values ('ksenia', md5('ksenia'), false);
insert into user_credential(user_id, name, surname) values (7, 'Ksenia', 'Gombalevskaya');

insert into user(login, password, is_blocked) values ('olya', md5('olya'), false);
insert into user_credential(user_id, name, surname) values (8, 'Olga', 'Krakovskaya');

insert into user(login, password, is_blocked) values ('marina', md5('marina'), false);
insert into user_credential(user_id, name, surname) values (9, 'Marina', 'Kravets');

insert into user(login, password, is_blocked) values ('alexandra', md5('alexandra'), false);
insert into user_credential(user_id, name, surname) values (10, 'Alexandra', 'Dubrovskaya');

insert into user(login, password, is_blocked) values ('larisa', md5('larisa'), true);
insert into user_credential(user_id, name, surname) values (11, 'Larisa', 'Solovyeva');

insert into user(login, password, is_blocked) values ('julia', md5('julia'), true);
insert into user_credential(user_id, name, surname) values (12, 'Julia', 'Grakina');