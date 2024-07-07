insert into book (title, author, category, available_amount, isbn)
    values  ('Java in action', 'Urma R.-G., Fusco M., Mycroft A.', 'Java', 2, '1'),
            ('Algorithms', 'Robert Sedgewick, Kevin Wayne', 'Java', 1, '2'),
            ('Design Patterns', 'Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides', 'Java', 4, '3'),
            ('Sherlock Holmes', 'Arthur Conan Doyle', 'Detective', 3, '4'),
            ('Harry Potter and the Philosopher\'s stone', 'J. K. Rowling', 'Fantasy', 4, '5'),
            ('1984', 'George Orwell', 'Dystopian', 5, '9780451524935'),
            ('To Kill a Mockingbird', 'Harper Lee', 'Fiction', 7, '9780061120084'),
            ('Pride and Prejudice', 'Jane Austen', 'Romance', 3, '9781503290563'),
            ('The Great Gatsby', 'F. Scott Fitzgerald', 'Fiction', 4, '9780743273565'),
            ('Moby Dick', 'Herman Melville', 'Adventure', 2, '9781503280786'),
            ('War and Peace', 'Leo Tolstoy', 'Fiction', 6, '9780199232765'),
            ('The Catcher in the Rye', 'J.D. Salinger', 'Fiction', 5, '9780316769488'),
            ('The Odyssey', 'Homer', 'Epic', 10, '9780140268867'),
            ('The Divine Comedy', 'Dante Alighieri', 'Epic', 4, '9780142437223'),
            ('Jane Eyre', 'Charlotte Brontë', 'Romance', 3, '9781503278196'),
            ('Wuthering Heights', 'Emily Brontë', 'Fiction', 4, '9780141439556'),
            ('Brave New World', 'Aldous Huxley', 'Dystopian', 7, '9780060850524'),
            ('Madame Bovary', 'Gustave Flaubert', 'Fiction', 5, '9780140449129'),
            ('One Hundred Years of Solitude', 'Gabriel Garcia Marquez', 'Magic Realism', 8, '9780060883287'),
            ('In Search of Lost Time', 'Marcel Proust', 'Modernist', 2, '9780143039570'),
            ('Ulysses', 'James Joyce', 'Modernist', 3, '9780199535675'),
            ('Don Quixote', 'Miguel de Cervantes', 'Adventure', 7, '9780060934347'),
            ('The Iliad', 'Homer', 'Epic', 5, '9780140275360'),
            ('The Hobbit', 'J.R.R. Tolkien', 'Fantasy', 9, '9780345339683'),
            ('Fahrenheit 451', 'Ray Bradbury', 'Dystopian', 4, '9781451673319');

insert into book_detail (id, publisher, year, abstract_to_book)
values (1, 'Manning Publications', 2018, 'Comprehensive guide to modern Java programming'),
       (2, 'Addison-Wesley', 2011, 'A comprehensive introduction to algorithms'),
       (3, 'Addison-Wesley', 1994, 'A classic guide to software design patterns');

insert into category (id, name, description)
values (1, 'Java','Learning Java'),
       (2, 'Java','Learning Java'),
       (3, 'Java','Learning Java'),
       (4, 'Java','Learning Java');