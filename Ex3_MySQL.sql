use demo;
create table category( 
	id INT NOT NULL AUTO_INCREMENT primary key ,
    name varchar(50) NOT NULL ,
    create_date Datetime NOT NULL ,
    fix_date Datetime not null,
    description varchar(255) not null
    
);

create table permission(
	id INT NOT NULL AUTO_INCREMENT primary key,
    name_per varchar(50) NOT NULL
);

create table per_detail(
	id INT NOT NULL AUTO_INCREMENT primary key,
    id_per int not null,
    action_name varchar(50) Not null,
    action_code varchar(10) NOT NULL,
    check_action boolean,
    foreign key (id_per) references permission (id)
);

create table user(
	id INT NOT NULL AUTO_INCREMENT primary key,
    username varchar(50) NOT NULL,
    password varchar(512) not null
);

create table user_per(
id int not null AUTO_INcrement primary key,
id_per int not null,
id_user int not null,
licensed boolean not null,
foreign key (id_user) references user(id),
foreign key (id_per) references permission(id)
);

create table storageProduct( 
	id INT NOT NULL AUTO_INCREMENT primary key,
    name varchar(50) NOT NULL,
    category_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    Foreign Key (category_id) References category(id),
    Foreign Key (product_id) References product(id)
);

create table order_detail(
	id INT NOT NULL AUTO_INCREMENT primary key,
    name varchar(50) NOT NULL,
    category_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    price decimal NOT NULL,
    
    Foreign Key (category_id) References Category(id),
    Foreign Key (product_id) References Product(id)
);

create table product( 
id int not null auto_increment primary key,
    name varchar(50) not null ,
    img_link varchar(50) not null,
    price decimal(18,4),
    quantity int not null,
    sale_number int not null,
    description varchar(255) not null,
    category_id int ,
    FOREIGN KEY (category_id) REFERENCES Category(id)
);


select p.*
from product p inner join category c on p.id=c.id
where (p.name  like N'%Điện thoại%' or p.description like N'%Điện thoại%') and c.name = 'Apple';

select c.id,count(p.id) as total
from product p right JOIN category c on p.category_id=c.id
group by c.id order by total desc;

select p.*
from product p
order by p.sale_number desc
limit 0,10 ;

