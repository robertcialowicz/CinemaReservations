create sequence hibernate_sequence start with 1 increment by 1
create table Reservation (id bigint not null, desc varchar(1000), seats varchar(255), title varchar(200), primary key (id))
create sequence hibernate_sequence start with 1 increment by 1
create table Reservation (id bigint not null, desc varchar(1000), seats varchar(255), title varchar(200), primary key (id))
create sequence hibernate_sequence start with 1 increment by 1
create table Reservation (id bigint not null, desc varchar(1000), seats varchar(255), title varchar(200), primary key (id))
create sequence hibernate_sequence start with 1 increment by 1
create table Reservation (id bigint not null, desc varchar(1000), seats varchar(255), title varchar(200), primary key (id))
create sequence hibernate_sequence start with 1 increment by 1
create table Reservation (id bigint not null, desc varchar(1000), seats varchar(255), title varchar(200), primary key (id))
create sequence hibernate_sequence start with 1 increment by 1
create table Film (FILM_ID bigint not null, DESCRIPTION varchar(1000), TITLE varchar(1000), primary key (FILM_ID))
create table MovieShow (MOVIESHOW_ID bigint not null, DATE varchar(1005), TIME varchar(1000), FILM_ID bigint, primary key (MOVIESHOW_ID))
create table Reservation (id bigint not null, BOOKEDSEATS varchar(255), NAME varchar(255), SURNAME varchar(255), MOVIESHOW_ID bigint, primary key (id))
create table TheatreHall (id bigint not null, SEATS_RESERVATION_STATUS varchar(1000), WHICH_HALL bigint, movieShow_MOVIESHOW_ID bigint, primary key (id))
alter table MovieShow add constraint FKsek8rxgbbopnf3hquaq1w162i foreign key (FILM_ID) references Film
alter table Reservation add constraint FKo4k3moydtcu44jj39q8kbmguo foreign key (MOVIESHOW_ID) references MovieShow
alter table TheatreHall add constraint FKe0p0cd810i9qn6o9o7n236sqq foreign key (movieShow_MOVIESHOW_ID) references MovieShow
create sequence hibernate_sequence start with 1 increment by 1
create table Film (FILM_ID bigint not null, DESCRIPTION varchar(1000), TITLE varchar(1000), primary key (FILM_ID))
create table MovieShow (MOVIESHOW_ID bigint not null, DATE varchar(1005), TIME varchar(1000), FILM_ID bigint, primary key (MOVIESHOW_ID))
create table Reservation (id bigint not null, BOOKEDSEATS varchar(255), NAME varchar(255), SURNAME varchar(255), MOVIESHOW_ID bigint, primary key (id))
create table TheatreHall (id bigint not null, SEATS_RESERVATION_STATUS varchar(1000), WHICH_HALL bigint, movieShow_MOVIESHOW_ID bigint, primary key (id))
alter table MovieShow add constraint FKsek8rxgbbopnf3hquaq1w162i foreign key (FILM_ID) references Film
alter table Reservation add constraint FKo4k3moydtcu44jj39q8kbmguo foreign key (MOVIESHOW_ID) references MovieShow
alter table TheatreHall add constraint FKe0p0cd810i9qn6o9o7n236sqq foreign key (movieShow_MOVIESHOW_ID) references MovieShow
create sequence hibernate_sequence start with 1 increment by 1
create table Film (FILM_ID bigint not null, DESCRIPTION varchar(1000), TITLE varchar(1000), primary key (FILM_ID))
create table MovieShow (MOVIESHOW_ID bigint not null, DATE varchar(1005), TIME varchar(1000), FILM_ID bigint, primary key (MOVIESHOW_ID))
create table Reservation (id bigint not null, BOOKEDSEATS varchar(255), NAME varchar(255), SURNAME varchar(255), MOVIESHOW_ID bigint, primary key (id))
create table TheatreHall (id bigint not null, SEATS_RESERVATION_STATUS varchar(1000), WHICH_HALL bigint, movieShow_MOVIESHOW_ID bigint, primary key (id))
alter table MovieShow add constraint FKsek8rxgbbopnf3hquaq1w162i foreign key (FILM_ID) references Film
alter table Reservation add constraint FKo4k3moydtcu44jj39q8kbmguo foreign key (MOVIESHOW_ID) references MovieShow
alter table TheatreHall add constraint FKe0p0cd810i9qn6o9o7n236sqq foreign key (movieShow_MOVIESHOW_ID) references MovieShow
create sequence hibernate_sequence start with 1 increment by 1
create table Film (FILM_ID bigint not null, DESCRIPTION varchar(1000), TITLE varchar(1000), primary key (FILM_ID))
create table MovieShow (MOVIESHOW_ID bigint not null, DATE varchar(1005), TIME varchar(1000), FILM_ID bigint, primary key (MOVIESHOW_ID))
create table Reservation (id bigint not null, BOOKEDSEATS varchar(255), NAME varchar(255), SURNAME varchar(255), MOVIESHOW_ID bigint, primary key (id))
create table TheatreHall (id bigint not null, SEATS_RESERVATION_STATUS varchar(1000), WHICH_HALL bigint, movieShow_MOVIESHOW_ID bigint, primary key (id))
alter table MovieShow add constraint FKsek8rxgbbopnf3hquaq1w162i foreign key (FILM_ID) references Film
alter table Reservation add constraint FKo4k3moydtcu44jj39q8kbmguo foreign key (MOVIESHOW_ID) references MovieShow
alter table TheatreHall add constraint FKe0p0cd810i9qn6o9o7n236sqq foreign key (movieShow_MOVIESHOW_ID) references MovieShow
create sequence hibernate_sequence start with 1 increment by 1
create table Film (FILM_ID bigint not null, DESCRIPTION varchar(1000), TITLE varchar(1000), primary key (FILM_ID))
create table MovieShow (MOVIESHOW_ID bigint not null, DATE varchar(1005), TIME varchar(1000), FILM_ID bigint, primary key (MOVIESHOW_ID))
create table Reservation (id bigint not null, BOOKEDSEATS varchar(255), NAME varchar(255), SURNAME varchar(255), MOVIESHOW_ID bigint, primary key (id))
create table TheatreHall (id bigint not null, SEATS_RESERVATION_STATUS varchar(1000), WHICH_HALL bigint, movieShow_MOVIESHOW_ID bigint, primary key (id))
alter table MovieShow add constraint FKsek8rxgbbopnf3hquaq1w162i foreign key (FILM_ID) references Film
alter table Reservation add constraint FKo4k3moydtcu44jj39q8kbmguo foreign key (MOVIESHOW_ID) references MovieShow
alter table TheatreHall add constraint FKe0p0cd810i9qn6o9o7n236sqq foreign key (movieShow_MOVIESHOW_ID) references MovieShow
create sequence hibernate_sequence start with 1 increment by 1
create table Film (FILM_ID bigint not null, DESCRIPTION varchar(1000), TITLE varchar(1000), primary key (FILM_ID))
create table MovieShow (MOVIESHOW_ID bigint not null, DATE varchar(1005), TIME varchar(1000), FILM_ID bigint, primary key (MOVIESHOW_ID))
create table Reservation (id bigint not null, BOOKEDSEATS varchar(255), NAME varchar(255), SURNAME varchar(255), MOVIESHOW_ID bigint, primary key (id))
create table TheatreHall (id bigint not null, SEATS_RESERVATION_STATUS varchar(1000), WHICH_HALL bigint, movieShow_MOVIESHOW_ID bigint, primary key (id))
alter table MovieShow add constraint FKsek8rxgbbopnf3hquaq1w162i foreign key (FILM_ID) references Film
alter table Reservation add constraint FKo4k3moydtcu44jj39q8kbmguo foreign key (MOVIESHOW_ID) references MovieShow
alter table TheatreHall add constraint FKe0p0cd810i9qn6o9o7n236sqq foreign key (movieShow_MOVIESHOW_ID) references MovieShow
create sequence hibernate_sequence start with 1 increment by 1
create table Film (FILM_ID bigint not null, DESCRIPTION varchar(1000), TITLE varchar(1000), primary key (FILM_ID))
create table MovieShow (MOVIESHOW_ID bigint not null, DATE varchar(1005), TIME varchar(1000), FILM_ID bigint, primary key (MOVIESHOW_ID))
create table Reservation (id bigint not null, BOOKEDSEATS varchar(255), NAME varchar(255), SURNAME varchar(255), MOVIESHOW_ID bigint, primary key (id))
create table TheatreHall (id bigint not null, SEATS_RESERVATION_STATUS varchar(1000), WHICH_HALL bigint, movieShow_MOVIESHOW_ID bigint, primary key (id))
alter table MovieShow add constraint FKsek8rxgbbopnf3hquaq1w162i foreign key (FILM_ID) references Film
alter table Reservation add constraint FKo4k3moydtcu44jj39q8kbmguo foreign key (MOVIESHOW_ID) references MovieShow
alter table TheatreHall add constraint FKe0p0cd810i9qn6o9o7n236sqq foreign key (movieShow_MOVIESHOW_ID) references MovieShow
create sequence hibernate_sequence start with 1 increment by 1
create table Film (FILM_ID bigint not null, DESCRIPTION varchar(1000), TITLE varchar(1000), primary key (FILM_ID))
create table MovieShow (MOVIESHOW_ID bigint not null, DATE varchar(1005), TIME varchar(1000), FILM_ID bigint, primary key (MOVIESHOW_ID))
create table Reservation (id bigint not null, BOOKEDSEATS varchar(255), NAME varchar(255), SURNAME varchar(255), MOVIESHOW_ID bigint, primary key (id))
create table TheatreHall (id bigint not null, SEATS_RESERVATION_STATUS varchar(1000), WHICH_HALL bigint, movieShow_MOVIESHOW_ID bigint, primary key (id))
alter table MovieShow add constraint FKsek8rxgbbopnf3hquaq1w162i foreign key (FILM_ID) references Film
alter table Reservation add constraint FKo4k3moydtcu44jj39q8kbmguo foreign key (MOVIESHOW_ID) references MovieShow
alter table TheatreHall add constraint FKe0p0cd810i9qn6o9o7n236sqq foreign key (movieShow_MOVIESHOW_ID) references MovieShow
create sequence hibernate_sequence start with 1 increment by 1
create table Film (FILM_ID bigint not null, DESCRIPTION varchar(1000), TITLE varchar(1000), primary key (FILM_ID))
create table MovieShow (MOVIESHOW_ID bigint not null, DATE varchar(1005), TIME varchar(1000), FILM_ID bigint, primary key (MOVIESHOW_ID))
create table Reservation (id bigint not null, BOOKEDSEATS varchar(255), NAME varchar(255), SURNAME varchar(255), MOVIESHOW_ID bigint, primary key (id))
create table TheatreHall (id bigint not null, SEATS_RESERVATION_STATUS varchar(1000), WHICH_HALL bigint, movieShow_MOVIESHOW_ID bigint, primary key (id))
alter table MovieShow add constraint FKsek8rxgbbopnf3hquaq1w162i foreign key (FILM_ID) references Film
alter table Reservation add constraint FKo4k3moydtcu44jj39q8kbmguo foreign key (MOVIESHOW_ID) references MovieShow
alter table TheatreHall add constraint FKe0p0cd810i9qn6o9o7n236sqq foreign key (movieShow_MOVIESHOW_ID) references MovieShow
create sequence hibernate_sequence start with 1 increment by 1
create table Film (FILM_ID bigint not null, DESCRIPTION varchar(1000), TITLE varchar(1000), primary key (FILM_ID))
create table MovieShow (MOVIESHOW_ID bigint not null, DATE varchar(1005), TIME varchar(1000), FILM_ID bigint, primary key (MOVIESHOW_ID))
create table Reservation (id bigint not null, BOOKEDSEATS varchar(255), NAME varchar(255), SURNAME varchar(255), MOVIESHOW_ID bigint, primary key (id))
create table TheatreHall (id bigint not null, SEATS_RESERVATION_STATUS varchar(1000), WHICH_HALL bigint, movieShow_MOVIESHOW_ID bigint, primary key (id))
alter table MovieShow add constraint FKsek8rxgbbopnf3hquaq1w162i foreign key (FILM_ID) references Film
alter table Reservation add constraint FKo4k3moydtcu44jj39q8kbmguo foreign key (MOVIESHOW_ID) references MovieShow
alter table TheatreHall add constraint FKe0p0cd810i9qn6o9o7n236sqq foreign key (movieShow_MOVIESHOW_ID) references MovieShow
create sequence hibernate_sequence start with 1 increment by 1
create table Film (FILM_ID bigint not null, DESCRIPTION varchar(1000), TITLE varchar(1000), primary key (FILM_ID))
create table MovieShow (MOVIESHOW_ID bigint not null, DATE varchar(1005), TIME varchar(1000), FILM_ID bigint, primary key (MOVIESHOW_ID))
create table Reservation (id bigint not null, BOOKEDSEATS varchar(255), NAME varchar(255), SURNAME varchar(255), MOVIESHOW_ID bigint, primary key (id))
create table TheatreHall (id bigint not null, SEATS_RESERVATION_STATUS varchar(1000), WHICH_HALL bigint, movieShow_MOVIESHOW_ID bigint, primary key (id))
alter table MovieShow add constraint FKsek8rxgbbopnf3hquaq1w162i foreign key (FILM_ID) references Film
alter table Reservation add constraint FKo4k3moydtcu44jj39q8kbmguo foreign key (MOVIESHOW_ID) references MovieShow
alter table TheatreHall add constraint FKe0p0cd810i9qn6o9o7n236sqq foreign key (movieShow_MOVIESHOW_ID) references MovieShow
create sequence hibernate_sequence start with 1 increment by 1
create table Film (FILM_ID bigint not null, DESCRIPTION varchar(1000), TITLE varchar(1000), primary key (FILM_ID))
create table MovieShow (MOVIESHOW_ID bigint not null, DATE varchar(1005), TIME varchar(1000), FILM_ID bigint, primary key (MOVIESHOW_ID))
create table Reservation (id bigint not null, BOOKEDSEATS varchar(255), NAME varchar(255), SURNAME varchar(255), MOVIESHOW_ID bigint, primary key (id))
create table TheatreHall (id bigint not null, SEATS_RESERVATION_STATUS varchar(1000), WHICH_HALL bigint, movieShow_MOVIESHOW_ID bigint, primary key (id))
alter table MovieShow add constraint FKsek8rxgbbopnf3hquaq1w162i foreign key (FILM_ID) references Film
alter table Reservation add constraint FKo4k3moydtcu44jj39q8kbmguo foreign key (MOVIESHOW_ID) references MovieShow
alter table TheatreHall add constraint FKe0p0cd810i9qn6o9o7n236sqq foreign key (movieShow_MOVIESHOW_ID) references MovieShow
