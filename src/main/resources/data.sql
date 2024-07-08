insert into book_detail (id, publisher, year, abstract_to_book)
values (1, 'Manning Publications', 2018, 'Comprehensive guide to modern Java programming'),
       (2, 'Addison-Wesley', 2011, 'A comprehensive introduction to algorithms'),
       (3, 'Addison-Wesley', 1994, 'A classic guide to software design patterns');

insert into category (id, name, description)
values (1, 'Java','Learning Java'),
       (2, 'Detective','Detective'),
       (3, 'Dystopian','Dystopian'),
       (4, 'Fiction','Fiction');

insert into book (id, title, author, category_id, available_amount, isbn, book_detail_id)
    values  (1,'Java in action', 'Urma R.-G., Fusco M., Mycroft A.', 1, 2, '1', 1),
            (2,'Algorithms', 'Robert Sedgewick, Kevin Wayne', 1, 1, '2', 2),
            (3,'Design Patterns', 'Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides', 1, 4, '3', 3),
            (4,'Sherlock Holmes', 'Arthur Conan Doyle', 2, 3, '4', null),
            (5,'Harry Potter and the Philosopher\'s stone', 'J. K. Rowling', null, 4, '5', null),
            (6,'1984', 'George Orwell', 3, 5, '9780451524935', null),
            (7,'To Kill a Mockingbird', 'Harper Lee', 4, 7, '9780061120084', null),
            (8,'Pride and Prejudice', 'Jane Austen', null, 3, '9781503290563', null),
            (9,'The Great Gatsby', 'F. Scott Fitzgerald', 4, 4, '9780743273565', null),
            (10,'Moby Dick', 'Herman Melville', null, 2, '9781503280786', null),
            (11,'War and Peace', 'Leo Tolstoy', 4, 6, '9780199232765', null),
            (12,'The Catcher in the Rye', 'J.D. Salinger', 4, 5, '9780316769488', null),
            (13,'The Odyssey', 'Homer', null, 10, '9780140268867', null),
            (14,'The Divine Comedy', 'Dante Alighieri', null, 4, '9780142437223', null),
            (15,'Jane Eyre', 'Charlotte Brontë', null, 3, '9781503278196', null),
            (16,'Wuthering Heights', 'Emily Brontë', 4, 4, '9780141439556', null),
            (17,'Brave New World', 'Aldous Huxley', 3, 7, '9780060850524', null),
            (18,'Madame Bovary', 'Gustave Flaubert', 4, 5, '9780140449129', null),
            (19,'One Hundred Years of Solitude', 'Gabriel Garcia Marquez', null, 8, '9780060883287', null),
            (20,'In Search of Lost Time', 'Marcel Proust', null, 2, '9780143039570', null),
            (21,'Ulysses', 'James Joyce', null, 3, '9780199535675', null),
            (22,'Don Quixote', 'Miguel de Cervantes', null, 7, '9780060934347', null),
            (23,'The Iliad', 'Homer', null, 5, '9780140275360', null),
            (24,'The Hobbit', 'J.R.R. Tolkien', null, 9, '9780345339683', null),
            (25,'Fahrenheit 451', 'Ray Bradbury', 3, 4, '9781451673319', null);

insert into author(id, name, surname, author_info)
values (1, 'William', 'Shakespeare',
        'English playwright, poet, and actor, widely regarded as the greatest writer in the English language.'),
       (2, 'Jane', 'Austen',
        'English novelist known primarily for her six major novels, which interpret, critique and comment upon the British landed gentry at the end of the 18th century.'),
       (3, 'Charles', 'Dickens',
        'English writer and social critic. He created some of the worlds best-known fictional characters and is regarded by many as the greatest novelist of the Victorian era.'),
       (4, 'George', 'Orwell',
        'English novelist, essayist, journalist and critic, whose work is marked by lucid prose, awareness of social injustice, opposition to totalitarianism, and outspoken support of democratic socialism.'),
       (5, 'Virginia', 'Woolf',
        'English writer, considered one of the most important modernist 20th-century authors and also a pioneer in the use of stream of consciousness as a narrative device.'),
       (6, 'Agatha', 'Christie',
        'English writer known for her sixty-six detective novels and fourteen short story collections, particularly those revolving around her fictional detectives Hercule Poirot and Miss Marple.');

insert into visitor ( id, email, password, name, surname, role)
values (12, 'john.doe@example.com', 'password123', 'John', 'Doe', 'guest'),
       (23, 'jane.smith@example.com', 'password123', 'Jane', 'Smith', 'guest'),
       (34, 'alice.jones@example.com', 'password123', 'Alice', 'Jones', 'guest'),
       (45, 'bob.brown@example.com', 'password123', 'Bob', 'Brown', 'guest'),
       (56, 'charlie.black@example.com', 'password123', 'Charlie', 'Black', 'guest'),
       (67, 'david.white@example.com', 'password123', 'David', 'White', 'guest'),
       (78, 'eve.green@example.com', 'password123', 'Eve', 'Green', 'guest'),
       (89, 'frank.red@example.com', 'password123', 'Frank', 'Red', 'guest'),
       (90, 'grace.blue@example.com', 'password123', 'Grace', 'Blue', 'guest'),
       (101, 'hannah.yellow@example.com', 'password123', 'Hannah', 'Yellow', 'guest');

insert into visitor_took_book (visitor_id, book_id)
    values (12, 1),
           (12, 2);