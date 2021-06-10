create table hibernate_sequence
(
    next_val bigint null
)
    engine = MyISAM;

INSERT INTO immo.hibernate_sequence (next_val) VALUES (6);

create table File
(
    id   bigint auto_increment
        primary key,
    date datetime     null,
    path varchar(255) null
)
    engine = MyISAM;

create table Address
(
    id      bigint auto_increment
        primary key,
    city    varchar(255) null,
    country varchar(255) null,
    street  varchar(255) null,
    zipCode varchar(255) null
)
    engine = MyISAM;

INSERT INTO immo.Address (id, city, country, street, zipCode) VALUES (1, 'Düsseldorf', 'Deutschland', 'Maxmustermannstraße.1', '40627');
INSERT INTO immo.Address (id, city, country, street, zipCode) VALUES (2, 'Düsseldorf', 'Deutschland', 'Maxmustermannstraße.1', '40627');
INSERT INTO immo.Address (id, city, country, street, zipCode) VALUES (3, '', '', '', '');
INSERT INTO immo.Address (id, city, country, street, zipCode) VALUES (4, '', '', '', '');
INSERT INTO immo.Address (id, city, country, street, zipCode) VALUES (5, 'Düsseldorf', 'Deutschland', 'Maxmustermannstraße.1', '40627');
INSERT INTO immo.Address (id, city, country, street, zipCode) VALUES (6, 'Düsseldorf', 'Deutschland', 'Maxmustermannstraße.1', '40627');
INSERT INTO immo.Address (id, city, country, street, zipCode) VALUES (7, 'Düsseldorf', 'Deutschland', 'Rheinstraße. 666', '40112');
INSERT INTO immo.Address (id, city, country, street, zipCode) VALUES (8, 'Düsseldorf', 'Deutschland', 'Teststraße.1', '46289');
INSERT INTO immo.Address (id, city, country, street, zipCode) VALUES (9, 'Düsseldorf', 'Deutschland', 'Aachener Str.5', '40223');
INSERT INTO immo.Address (id, city, country, street, zipCode) VALUES (10, 'Düsseldorf', 'Deutschland', 'Aachener Str.6', '40223');
INSERT INTO immo.Address (id, city, country, street, zipCode) VALUES (11, 'Düsseldorf', 'Deutschland', 'Aachener Str.5', '40223');
INSERT INTO immo.Address (id, city, country, street, zipCode) VALUES (12, 'Düsseldorf', 'Deutschland', 'Aachener Str.5', '40223');
INSERT INTO immo.Address (id, city, country, street, zipCode) VALUES (13, 'Düsseldorf', 'Deutschland', 'Aachener Str.7', '40223');

create table BankAccount
(
    id                bigint auto_increment
        primary key,
    accountOwner      varchar(255) null,
    bic               varchar(255) null,
    creditInstitution varchar(255) null,
    iban              varchar(255) null
)
    engine = MyISAM;

INSERT INTO immo.BankAccount (id, accountOwner, bic, creditInstitution, iban) VALUES (1, 'Max Mustermann', 'COBADEFFXXX', 'Commerzbank AG', 'DE1234');
INSERT INTO immo.BankAccount (id, accountOwner, bic, creditInstitution, iban) VALUES (2, 'Musterfrau Muster', 'DUSSDEDD', 'Stadtsparkasse Düsseldorf', 'DE4321');
INSERT INTO immo.BankAccount (id, accountOwner, bic, creditInstitution, iban) VALUES (3, 'Gustav Günther', 'GUDEXXX', 'Gustav Bank', 'DE124332');

create table Role
(
    id       bigint auto_increment
        primary key,
    roleType int null
)
    engine = MyISAM;

INSERT INTO immo.Role (id, roleType) VALUES (1, 1);
INSERT INTO immo.Role (id, roleType) VALUES (2, 0);

create table Person
(
    id                  bigint       not null
        primary key,
    email               varchar(255) null,
    firstName           varchar(255) null,
    lastName            varchar(255) null,
    phoneNumberLandline varchar(255) null,
    phoneNumberMobile   varchar(255) null,
    title               varchar(255) null,
    address_id          bigint       null
)
    engine = MyISAM;

create index FK6i7nduc8blbwp1dbfwavvnvvx
    on Person (address_id);

create table User
(
    id                  bigint       not null
        primary key,
    email               varchar(255) null,
    firstName           varchar(255) null,
    lastName            varchar(255) null,
    phoneNumberLandline varchar(255) null,
    phoneNumberMobile   varchar(255) null,
    title               varchar(255) null,
    address_id          bigint       null,
    password            varchar(255) null,
    username            varchar(255) null,
    role_id             bigint       null
)
    engine = MyISAM;

create index FK84qlpfci484r1luck11eno6ec
    on User (role_id);

create index FK_25yqck53dyy0k1q261ncjxmw3
    on User (address_id);

INSERT INTO immo.User (id, email, firstName, lastName, phoneNumberLandline, phoneNumberMobile, title, address_id, password, username, role_id) VALUES (2, 'user.user@user.com', 'User', 'user', '+49User', 'User', 'User', null, 'user', 'User', 1);
INSERT INTO immo.User (id, email, firstName, lastName, phoneNumberLandline, phoneNumberMobile, title, address_id, password, username, role_id) VALUES (4, 'mitarbeiter.mitarbeit@immo.de', 'Mitarbeiter', 'Mitarbeit', '+49Immo', '+49Privat', 'Herr', null, 'mitarbeit', 'Mitarbeiter', 1);

create table Document
(
    id       bigint auto_increment
        primary key,
    fileName varchar(255) null
)
    engine = MyISAM;

INSERT INTO immo.Document (id, fileName) VALUES (1, 'Gustav Günther Auszugserklärung');

create table Document_File
(
    Document_id    bigint not null,
    versionList_id bigint not null,
    constraint UK_3x31803b93a17816i2r0pa170
        unique (versionList_id)
)
    engine = MyISAM;

create index FKovhhmkiwom3r89x9x8on1dgnk
    on Document_File (Document_id);

create table Tenant
(
    id                  bigint       not null
        primary key,
    email               varchar(255) null,
    firstName           varchar(255) null,
    lastName            varchar(255) null,
    phoneNumberLandline varchar(255) null,
    phoneNumberMobile   varchar(255) null,
    title               varchar(255) null,
    address_id          bigint       null,
    contactOnly         bit          not null,
    getTenancyEnd       datetime     null,
    tenancyStart        datetime     null,
    bankAccount_id      bigint       null,
    oldAddress_id       bigint       null
)
    engine = MyISAM;

create index FK56mntgb3onaq95mjyf20x2a6a
    on Tenant (oldAddress_id);

create index FK_oyfy3aayoot36euwatubtlmff
    on Tenant (address_id);

create index FKfo8vajj4w70k0wmneywhxbuhu
    on Tenant (bankAccount_id);

INSERT INTO immo.Tenant (id, email, firstName, lastName, phoneNumberLandline, phoneNumberMobile, title, address_id, contactOnly, getTenancyEnd, tenancyStart, bankAccount_id, oldAddress_id) VALUES (1, 'max.mustermann@gmx.de', 'Max', 'Mustermann', '+49123456789', '+49987654321', 'Herr', null, false, '2019-06-08 00:00:00', '2018-06-16 00:00:00', 1, 4);
INSERT INTO immo.Tenant (id, email, firstName, lastName, phoneNumberLandline, phoneNumberMobile, title, address_id, contactOnly, getTenancyEnd, tenancyStart, bankAccount_id, oldAddress_id) VALUES (3, 'musterfrau.muster@gmx.de', 'Musterfrau', 'Muster', '+4900000000', '+4099999999', 'Frau', null, false, '2021-06-20 00:00:00', '2018-06-16 00:00:00', 2, 6);
INSERT INTO immo.Tenant (id, email, firstName, lastName, phoneNumberLandline, phoneNumberMobile, title, address_id, contactOnly, getTenancyEnd, tenancyStart, bankAccount_id, oldAddress_id) VALUES (5, 'gustav.günther@webmail.de', 'Gustav', 'Günther', '+4900000000', '+4999999999', 'Herr', null, false, '2026-06-21 00:00:00', '2008-06-06 00:00:00', 3, null);

create table RentalObject
(
    id                bigint auto_increment
        primary key,
    additionalCosts   int          not null,
    commercial        bit          not null,
    livingSpace       int          not null,
    notes             varchar(255) null,
    objectDescription varchar(255) null,
    objectNumber      varchar(255) null,
    rentalType        int          null,
    squareMeterPrice  int          not null,
    address_id        bigint       null,
    tenant_id         bigint       null
)
    engine = MyISAM;

create index FK5beroxdt5yeqkp28qw433wqp4
    on RentalObject (address_id);

create index FK6s7f9icxuqp2fmcl3to9om793
    on RentalObject (tenant_id);

INSERT INTO immo.RentalObject (id, additionalCosts, commercial, livingSpace, notes, objectDescription, objectNumber, rentalType, squareMeterPrice, address_id, tenant_id) VALUES (1, 300, false, 45, 'Immobilie in der Düsseldorfer Innenstadt', 'Schöne Lage in der Düsseldorfer Stadt', '1', 0, 16, 1, 1);
INSERT INTO immo.RentalObject (id, additionalCosts, commercial, livingSpace, notes, objectDescription, objectNumber, rentalType, squareMeterPrice, address_id, tenant_id) VALUES (2, 5, false, 20, '', 'Parkplatz für Objekt Nummer 1', '2', 2, 20, 5, 1);
INSERT INTO immo.RentalObject (id, additionalCosts, commercial, livingSpace, notes, objectDescription, objectNumber, rentalType, squareMeterPrice, address_id, tenant_id) VALUES (3, 400, false, 86, null, 'Schönes Objekt in der Nähe von der Rheinstraße', '3', 0, 20, 7, 3);
INSERT INTO immo.RentalObject (id, additionalCosts, commercial, livingSpace, notes, objectDescription, objectNumber, rentalType, squareMeterPrice, address_id, tenant_id) VALUES (4, 23, true, 45, 'Immobilie in der Düsseldorfer Innenstadt', 'Kommerzielle Fläche für Großunternehmen', '5', 1, 34, 8, null);
INSERT INTO immo.RentalObject (id, additionalCosts, commercial, livingSpace, notes, objectDescription, objectNumber, rentalType, squareMeterPrice, address_id, tenant_id) VALUES (5, 534, false, 45, 'Immobilie in der Düsseldorfer Innenstadt', 'Erdgeschoss Wohnung in Bilk', '6', 0, 16, 9, 5);
INSERT INTO immo.RentalObject (id, additionalCosts, commercial, livingSpace, notes, objectDescription, objectNumber, rentalType, squareMeterPrice, address_id, tenant_id) VALUES (6, 235, false, 45, 'Immobilie in der Düsseldorfer Innenstadt', 'Schöne Lage in der Düsseldorfer Stadt', '7', 0, 16, 10, 5);
INSERT INTO immo.RentalObject (id, additionalCosts, commercial, livingSpace, notes, objectDescription, objectNumber, rentalType, squareMeterPrice, address_id, tenant_id) VALUES (7, 71, false, 45, 'Immobilie in der Düsseldorfer Innenstadt', 'Schöne Lage in der Düsseldorfer Stadt', '8', 0, 16, 11, 5);
INSERT INTO immo.RentalObject (id, additionalCosts, commercial, livingSpace, notes, objectDescription, objectNumber, rentalType, squareMeterPrice, address_id, tenant_id) VALUES (8, 123, false, 45, 'Immobilie in der Düsseldorfer Innenstadt', 'Wohnung im Dachgeschoss am Rande von Düsseldorf', '9', 0, 16, 12, null);
INSERT INTO immo.RentalObject (id, additionalCosts, commercial, livingSpace, notes, objectDescription, objectNumber, rentalType, squareMeterPrice, address_id, tenant_id) VALUES (9, 22, false, 45, 'Immobilie in der Düsseldorfer Innenstadt', 'Schöne Lage in der Düsseldorfer Stadt', '10', 0, 16, 13, null);

create table Document_RentalObject
(
    Document_id             bigint not null,
    relatedRentalObjects_id bigint not null
)
    engine = MyISAM;

create index FK3haew52wx6ervvi695p8ygknh
    on Document_RentalObject (relatedRentalObjects_id);

create index FKiehapewit51a19liwfw2h3wkx
    on Document_RentalObject (Document_id);

INSERT INTO immo.Document_RentalObject (Document_id, relatedRentalObjects_id) VALUES (1, 5);
INSERT INTO immo.Document_RentalObject (Document_id, relatedRentalObjects_id) VALUES (1, 6);
INSERT INTO immo.Document_RentalObject (Document_id, relatedRentalObjects_id) VALUES (1, 7);

create table Document_Tenant
(
    Document_id       bigint not null,
    relatedTenants_id bigint not null
)
    engine = MyISAM;

create index FK35js249p80691osob7so26x87
    on Document_Tenant (relatedTenants_id);

create index FKcl36bmr7iq9r56q3dsgm0i0j5
    on Document_Tenant (Document_id);

INSERT INTO immo.Document_Tenant (Document_id, relatedTenants_id) VALUES (1, 5);

create table RentalObject_Document
(
    RentalObject_id bigint not null,
    documents_id    bigint not null
)
    engine = MyISAM;

create index FK7602q9xom9pw8jy413f7vkmx0
    on RentalObject_Document (RentalObject_id);

create index FKq4pj33j5i7d1wwa22ywix2bnj
    on RentalObject_Document (documents_id);

INSERT INTO immo.RentalObject_Document (RentalObject_id, documents_id) VALUES (6, 1);
INSERT INTO immo.RentalObject_Document (RentalObject_id, documents_id) VALUES (5, 1);
INSERT INTO immo.RentalObject_Document (RentalObject_id, documents_id) VALUES (7, 1);

create table RentalObject_RentalObject
(
    RentalObject_id bigint not null,
    subObjects_id   bigint not null,
    constraint UK_p4kox6fyrelv47paxmb1lt7in
        unique (subObjects_id)
)
    engine = MyISAM;

create table Tenant_Document
(
    Tenant_id    bigint not null,
    documents_id bigint not null
)
    engine = MyISAM;

create index FKgrw3x1idmty8ytvpsak7rjhv4
    on Tenant_Document (documents_id);

create index FKjomdsa05t9os3oc9kk6jd1ge1
    on Tenant_Document (Tenant_id);

INSERT INTO immo.Tenant_Document (Tenant_id, documents_id) VALUES (5, 1);

create index FKg7jpniuw1py8b23rd4sejr15g
    on RentalObject_RentalObject (RentalObject_id);

INSERT INTO immo.RentalObject_RentalObject (RentalObject_id, subObjects_id) VALUES (1, 2);

create table RentalObject_Tenant
(
    RentalObject_id bigint not null,
    contacts_id     bigint not null
)
    engine = MyISAM;

create index FKc52elncdqobu9jmtw20k2m6kb
    on RentalObject_Tenant (contacts_id);

create index FKt0vn6xypfqcym0a8apoq12e02
    on RentalObject_Tenant (RentalObject_id);



create table Invoice
(
    id           bigint auto_increment
        primary key,
    date         datetime null,
    settled      bit      not null,
    recipient_id bigint   null
)
    engine = MyISAM;

INSERT INTO immo.Invoice (id, date, settled, recipient_id) VALUES (1, '2021-06-11 00:00:00', false, 1);
INSERT INTO immo.Invoice (id, date, settled, recipient_id) VALUES (2, '2021-06-11 00:00:00', false, 3);
INSERT INTO immo.Invoice (id, date, settled, recipient_id) VALUES (3, '2021-06-11 00:00:00', false, 5);

create table RentalObject_Invoice
(
    RentalObject_id bigint not null,
    invoices_id     bigint not null,
    constraint UK_idhn1g6t7rhqwmfmu1l8loq40
        unique (invoices_id)
)
    engine = MyISAM;

create index FKeqs46cvgsv74a1x5g0rfg1e0f
    on RentalObject_Invoice (RentalObject_id);

create table Item
(
    id      bigint auto_increment
        primary key,
    comment varchar(255) null,
    value   int          not null
)
    engine = MyISAM;

INSERT INTO immo.Item (id, comment, value) VALUES (1, 'Miete', 1020);
INSERT INTO immo.Item (id, comment, value) VALUES (2, 'Miete Wohnung', 1020);
INSERT INTO immo.Item (id, comment, value) VALUES (3, 'Miete Wohnung', 1020);
INSERT INTO immo.Item (id, comment, value) VALUES (4, 'Miete Parkplatz', 405);
INSERT INTO immo.Item (id, comment, value) VALUES (5, 'Miete Wohnung', 1200);
INSERT INTO immo.Item (id, comment, value) VALUES (6, 'Miete Wohnung', 2120);
INSERT INTO immo.Item (id, comment, value) VALUES (7, 'Miete Wohnung 1', 1429);
INSERT INTO immo.Item (id, comment, value) VALUES (8, 'Miete Wohnung 2', 2153);
INSERT INTO immo.Item (id, comment, value) VALUES (9, 'Miete Wohnung 3', 2233);

create table Invoice_Item
(
    Invoice_id  bigint not null,
    itemList_id bigint not null,
    constraint UK_px5ld0gfcdxwcwrthjv33on20
        unique (itemList_id)
)
    engine = MyISAM;

create index FKnb9y5vjcd7hnmqmawg7y04g8
    on Invoice_Item (Invoice_id);

INSERT INTO immo.Invoice_Item (Invoice_id, itemList_id) VALUES (1, 4);
INSERT INTO immo.Invoice_Item (Invoice_id, itemList_id) VALUES (1, 2);
INSERT INTO immo.Invoice_Item (Invoice_id, itemList_id) VALUES (2, 6);
INSERT INTO immo.Invoice_Item (Invoice_id, itemList_id) VALUES (3, 8);
INSERT INTO immo.Invoice_Item (Invoice_id, itemList_id) VALUES (3, 7);
INSERT INTO immo.Invoice_Item (Invoice_id, itemList_id) VALUES (3, 9);

create table PaymentReceived
(
    id              bigint auto_increment
        primary key,
    amount          int      not null,
    date            datetime null,
    rentalObject_id bigint   null,
    tenant_id       bigint   null
)
    engine = MyISAM;

create index FKbgqv49nfmphcg5lmxp79ofgme
    on PaymentReceived (tenant_id);

create index FKjttigodpwtyj0uaf9aqgax7q2
    on PaymentReceived (rentalObject_id);

INSERT INTO immo.PaymentReceived (id, amount, date, rentalObject_id, tenant_id) VALUES (1, 1020, '2021-05-11 00:00:00', 1, 1);
INSERT INTO immo.PaymentReceived (id, amount, date, rentalObject_id, tenant_id) VALUES (2, 405, '2021-05-11 00:00:00', 2, 1);
INSERT INTO immo.PaymentReceived (id, amount, date, rentalObject_id, tenant_id) VALUES (3, 5000, '2021-04-11 00:00:00', 3, 3);

