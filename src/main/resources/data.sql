insert into book (id, title, author, category, available_amount, isbn)
    values  (1,'Java in action', 'Urma R.-G., Fusco M., Mycroft A.', 'Java', 2, '1'),
            (2,'Algorithms', 'Robert Sedgewick, Kevin Wayne', 'Java', 1, '2'),
            (3,'Design Patterns', 'Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides', 'Java', 4, '3'),
            (4,'Sherlock Holmes', 'Arthur Conan Doyle', 'Detective', 3, '4'),
            (5,'Harry Potter and the Philosopher\'s stone', 'J. K. Rowling', 'Fantasy', 4, '5'),
            (6,'1984', 'George Orwell', 'Dystopian', 5, '9780451524935'),
            (7,'To Kill a Mockingbird', 'Harper Lee', 'Fiction', 7, '9780061120084'),
            (8,'Pride and Prejudice', 'Jane Austen', 'Romance', 3, '9781503290563'),
            (9,'The Great Gatsby', 'F. Scott Fitzgerald', 'Fiction', 4, '9780743273565'),
            (10,'Moby Dick', 'Herman Melville', 'Adventure', 2, '9781503280786'),
            (11,'War and Peace', 'Leo Tolstoy', 'Fiction', 6, '9780199232765'),
            (12,'The Catcher in the Rye', 'J.D. Salinger', 'Fiction', 5, '9780316769488'),
            (13,'The Odyssey', 'Homer', 'Epic', 10, '9780140268867'),
            (14,'The Divine Comedy', 'Dante Alighieri', 'Epic', 4, '9780142437223'),
            (15,'Jane Eyre', 'Charlotte Brontë', 'Romance', 3, '9781503278196'),
            (16,'Wuthering Heights', 'Emily Brontë', 'Fiction', 4, '9780141439556'),
            (17,'Brave New World', 'Aldous Huxley', 'Dystopian', 7, '9780060850524'),
            (18,'Madame Bovary', 'Gustave Flaubert', 'Fiction', 5, '9780140449129'),
            (19,'One Hundred Years of Solitude', 'Gabriel Garcia Marquez', 'Magic Realism', 8, '9780060883287'),
            (20,'In Search of Lost Time', 'Marcel Proust', 'Modernist', 2, '9780143039570'),
            (21,'Ulysses', 'James Joyce', 'Modernist', 3, '9780199535675'),
            (22,'Don Quixote', 'Miguel de Cervantes', 'Adventure', 7, '9780060934347'),
            (23,'The Iliad', 'Homer', 'Epic', 5, '9780140275360'),
            (24,'The Hobbit', 'J.R.R. Tolkien', 'Fantasy', 9, '9780345339683'),
            (25,'Fahrenheit 451', 'Ray Bradbury', 'Dystopian', 4, '9781451673319');

insert into book_detail (id, publisher, year, abstract_to_book)
values (1, 'Manning Publications', 2018, 'Comprehensive guide to modern Java programming'),
       (2, 'Addison-Wesley', 2011, 'A comprehensive introduction to algorithms'),
       (3, 'Addison-Wesley', 1994, 'A classic guide to software design patterns');

insert into category (id, name, description)
values (1, 'Java','Learning Java'),
       (2, 'Java','Learning Java'),
       (3, 'Java','Learning Java'),
       (4, 'Java','Learning Java');

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