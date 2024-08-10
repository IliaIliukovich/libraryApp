package com.telran.libraryapp.service;

import com.telran.libraryapp.dto.BookDto;
import com.telran.libraryapp.dto.BookFullInfoDto;
import com.telran.libraryapp.entity.Book;
import com.telran.libraryapp.entity.BookDetail;
import com.telran.libraryapp.entity.Building;
import com.telran.libraryapp.entity.Category;
import com.telran.libraryapp.mapper.BookFullInfoMapper;
import com.telran.libraryapp.mapper.BookMapper;
import com.telran.libraryapp.repository.BookDetailRepository;
import com.telran.libraryapp.repository.BookRepository;
import com.telran.libraryapp.repository.BuildingRepository;
import com.telran.libraryapp.repository.CategoryRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private static final Logger logger = LogManager.getLogger(BookService.class);

    private final BookRepository repository;
    private final BookDetailRepository bookDetailRepository;
    private final CategoryRepository categoryRepository;
    private final BuildingRepository buildingRepository;
    private final BookMapper bookMapper;
    private final BookFullInfoMapper bookFullInfoMapper;

    @Autowired
    public BookService(BookRepository repository, BookDetailRepository bookDetailRepository, CategoryRepository categoryRepository, BuildingRepository buildingRepository, BookMapper bookMapper, BookFullInfoMapper bookFullInfoMapper) {
        this.repository = repository;
        this.bookDetailRepository = bookDetailRepository;
        this.categoryRepository = categoryRepository;
        this.buildingRepository = buildingRepository;
        this.bookMapper = bookMapper;
        this.bookFullInfoMapper = bookFullInfoMapper;
    }

    public List<BookDto> getAll() {
        List<Book> books = repository.findAll();

//        logger.debug("Books retrieved from DB: " + books.stream().map(Book::getTitle).toList());

//        if (logger.isDebugEnabled()) {
//            logger.debug("Books retrieved from DB: {}", books.stream().map(Book::getTitle).toList());
//        }

        logger.debug("Books retrieved from DB: {}", () -> books.stream().map(Book::getTitle).toList());

//        logger.info("info");
//        logger.warn("warn");
//        logger.error("error");
//        logger.fatal("fatal");
        return bookMapper.entityListToDto(books);
    }

    public List<BookDto> getAllByTitle(String title, Integer amount) {
        if (amount == null) {
            amount = 0;
        }
        List<Book> books = repository.findBooksByTitleStartingWithAndAvailableAmountIsGreaterThanEqual(title, amount);
        return bookMapper.entityListToDto(books);
    }

    public BookDto addOrUpdate(BookDto bookDto) {
        Book book = bookMapper.dtoToEntity(bookDto);
        Book createdOrUpdated = repository.save(book);
        return bookMapper.entityToDto(createdOrUpdated);
    }

    @Transactional
    public BookFullInfoDto addBookFullInfo(BookFullInfoDto bookFullInfoDto) {
        Book book = bookFullInfoMapper.dtoToBook(bookFullInfoDto);
        BookDetail bookDetail = bookFullInfoMapper.dtoToBookDetail(bookFullInfoDto);
        bookDetail = bookDetailRepository.save(bookDetail);
        book.setBookDetail(bookDetail);

        if (bookFullInfoDto.getBuildingId() != null) {
            Building building = buildingRepository.getReferenceById(bookFullInfoDto.getBuildingId());
            book.setBuilding(building);
        }

        if (bookFullInfoDto.getCategoryId() != null) {
            Category category = categoryRepository.getReferenceById(bookFullInfoDto.getCategoryId());
            book.setCategory(category);
        }

        book = repository.save(book);
        return bookFullInfoMapper.entityToDto(book, bookDetail);
    }

    public BookDto updateBook(BookDto bookDto) {
        Optional<Book> optional = repository.findById(bookDto.getId());
        if (optional.isPresent()) {
            Book saved = repository.save(bookMapper.dtoToEntity(bookDto));
            return bookMapper.entityToDto(saved);
        } else {
            return null;
        }
    }

    public Optional<BookDto> updateAmountOfBooks(Long id, Integer amount) {
        Optional<Book> optional = repository.findById(id);
        if (optional.isPresent()) {
            Book book = optional.get();
            book.setAvailableAmount(amount);
            Book updatedBook = repository.save(book);
            return Optional.of(bookMapper.entityToDto(updatedBook));
        } else {
            return Optional.empty();
        }
    }

    public void updateAmountOfBooksOptimized(Long id, Integer amount) {
        repository.updateAmountOfBooksOptimized(id, amount);
    }

    public void remove(Long id) {
        repository.deleteById(id);
    }
}
