
    create table cliente (
        id_usuario integer not null auto_increment,
        cpf_usuario varchar(255) not null,
        email_usuario varchar(255) not null,
        endereco_usuario varchar(255) not null,
        funcao varchar(255) not null,
        nome_usuario varchar(255) not null,
        senha_usuario varchar(255) not null,
        telefone_usuario varchar(255) not null,
        primary key (id_usuario)
    ) engine=InnoDB;

    create table emprestimo (
        codigo_emprestimo integer not null auto_increment,
        usuario_id_usuario integer,
        livro_id bigint,
        data_de_devolução varchar(255),
        data_de_inicio varchar(255),
        primary key (codigo_emprestimo)
    ) engine=InnoDB;

    create table livro (
        ano_de_publicacao integer,
        numero_de_exemplares integer,
        numero_de_paginas integer,
        id bigint not null auto_increment,
        sinopse varchar(2000),
        autor varchar(255),
        cover_url varchar(255),
        editora varchar(255),
        genero varchar(255),
        idioma varchar(255),
        isbn varchar(255),
        titulo varchar(255),
        primary key (id)
    ) engine=InnoDB;

    alter table cliente 
       add constraint UKn0a54uwu56q00tkwv7hxckbyq unique (cpf_usuario);

    alter table cliente 
       add constraint UKavp72olq9ha5nr6c3mpxlvl3d unique (email_usuario);

    alter table livro 
       add constraint UKk8si93wtslp275pv65gity1gg unique (isbn);

    alter table emprestimo 
       add constraint FKlraqo5mkov260qr5h3es93sgr 
       foreign key (livro_id) 
       references livro (id);

    create table cliente (
        id_usuario integer not null auto_increment,
        cpf_usuario varchar(255) not null,
        email_usuario varchar(255) not null,
        endereco_usuario varchar(255) not null,
        funcao varchar(255) not null,
        nome_usuario varchar(255) not null,
        senha_usuario varchar(255) not null,
        telefone_usuario varchar(255) not null,
        primary key (id_usuario)
    ) engine=InnoDB;

    create table emprestimo (
        codigo_emprestimo integer not null auto_increment,
        usuario_id_usuario integer,
        livro_id bigint,
        data_de_devolução varchar(255),
        data_de_inicio varchar(255),
        primary key (codigo_emprestimo)
    ) engine=InnoDB;

    create table livro (
        ano_de_publicacao integer,
        numero_de_exemplares integer,
        numero_de_paginas integer,
        id bigint not null auto_increment,
        sinopse varchar(2000),
        autor varchar(255),
        cover_url varchar(255),
        editora varchar(255),
        genero varchar(255),
        idioma varchar(255),
        isbn varchar(255),
        titulo varchar(255),
        primary key (id)
    ) engine=InnoDB;

    alter table cliente 
       add constraint UKn0a54uwu56q00tkwv7hxckbyq unique (cpf_usuario);

    alter table cliente 
       add constraint UKavp72olq9ha5nr6c3mpxlvl3d unique (email_usuario);

    alter table livro 
       add constraint UKk8si93wtslp275pv65gity1gg unique (isbn);

    alter table emprestimo 
       add constraint FKlraqo5mkov260qr5h3es93sgr 
       foreign key (livro_id) 
       references livro (id);
