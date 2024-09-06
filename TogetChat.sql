drop table chatMember purge;
drop table message purge;
drop table chat purge;
drop table reply purge;
drop table boardImage purge;
drop table board purge;
drop table review purge;
drop table programLike purge;
drop table program purge;
drop table category purge;
drop table member purge;


create table member (
    idx         number          GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    userId      varchar2(500)   unique not null,
    userPw      varchar2(500)   not null,
    email       varchar2(500)   not null,
    nickName    varchar2(500)   unique not null,
    profile     varchar2(4000)  ,
    role        number          default '3' check(role between 1 and 3) not null
    -- role 1: 전체관리자, 2: 중간관리자, 3: 일반회원
);

create table category (
    idx         number          GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name        varchar2(500)   not null check(name in ('드라마','영화','애니매이션','예능'))
);

create table program (
    idx             number          GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    categoryIdx    number          not null,
    title           varchar2(1000)  not null,
    content         varchar2(4000)  ,
    image           varchar2(4000)  ,
    
    constraint program_category_fk
    foreign key (categoryIdx)
    references category(idx) on delete cascade
);

create table programLike (
    programIdx      number          not null,
    nickName        varchar2(500)   not null,
    
    constraint programLike_program_fk
    foreign key (programIdx)
    references program(idx) on delete cascade,
    
    constraint programLike_member_fk
    foreign key (nickName)
    references member(nickName) on delete cascade
);

create table review (
    programIdx     number          not null,
    nickName       varchar2(500)   not null,
    score          number          check(score between 1 and 5),
    
    constraint review_program_fk
    foreign key (programIdx)
    references program(idx) on delete cascade,
    
    constraint review_member_fk
    foreign key (nickName)
    references member(nickName) on delete cascade
);

create table board (
    idx             number          GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    programIdx      number          not null,
    writer          varchar2(500)   not null,
    title           varchar2(1000)  not null,
    content         varchar2(4000)  not null,
    writeDate       date            default sysdate,
    viewCount       number          default 0,
    
    constraint board_program_fk
    foreign key (programIdx)
    references program(idx) on delete cascade,
    
    constraint board_member_fk
    foreign key (writer)
    references member(nickName) on delete cascade
);

create table boardImage (
    boardIdx    number          not null,
    image       varchar2(4000)  not null,
    
    constraint boardImage_board_fk
    foreign key (boardIdx)
    references board(idx) on delete cascade
);

create table reply (
    idx             number          GENERATED ALWAYS AS IDENTITY PRIMARY KEY,   
    boardIdx        number          not null,
    writer          varchar2(500)   not null,
    content         varchar2(2000)  not null,
    writeDate       date            default sysdate,
    
    constraint reply_board_fk
    foreign key (boardIdx)
    references board(idx) on delete cascade,
    
    constraint reply_member_fk
    foreign key (writer)
    references member(nickName) on delete cascade
);

create table chat (
    idx             number          GENERATED ALWAYS AS IDENTITY PRIMARY KEY,   
    programIdx      number          not null,
    chatAdmin       varchar2(500)   not null,
    
    constraint chat_program_fk
    foreign key (programIdx)
    references program(idx) on delete cascade,
    
    constraint chat_member_fk
    foreign key (chatAdmin)
    references member(nickName) on delete cascade
);

create table message (
    idx             number          GENERATED ALWAYS AS IDENTITY PRIMARY KEY,   
    chatIdx         number           not null,
    nickName        varchar2(500)   not null,
    writeDate       date            default sysdate,
    content         varchar2(2000)  not null,
    
    constraint message_chat_fk
    foreign key (chatIdx)
    references chat(idx) on delete cascade,
    
    constraint message_member_fk
    foreign key (nickName)
    references member(nickName) on delete cascade
);

create table chatMember (
    chatIdx         number           not null,
    nickName        varchar2(500)    not null,
    
    constraint chatMember_chat_fk
    foreign key (chatIdx)
    references chat(idx) on delete cascade,
    
    constraint chatMember_member_fk
    foreign key (nickName)
    references member(nickName) on delete cascade
);


select * from tab;
