insert into book (title, author, category, available_amount, isbn)
    values ('Java in Action1', 'Author', 'Java', 3, '1'),
           ('Java in Action2', 'Author', 'Java', 3, '2'),
           ('Java in Action3', 'Author', 'Java', 3, '3');

insert into book_detail (id, publisher, year, abstract_to_book)
    values (1, 'Manning Publications', 2018, 'Comprehensive guide to modern Java programming'),
           (2, 'Addison-Wesley', 2011, 'A comprehensive introduction to algorithms'),
           (3, 'Addison-Wesley', 1994, 'A classic guide to software design patterns');