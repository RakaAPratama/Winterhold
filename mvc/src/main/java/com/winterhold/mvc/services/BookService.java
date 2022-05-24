package com.winterhold.mvc.services;

import com.winterhold.mvc.dtos.book.UpdateInsertBookDTO;
import com.winterhold.mvc.dtos.category.UpdateInsertCategoryDTO;
import com.winterhold.mvc.models.Author;
import com.winterhold.mvc.models.Book;
import com.winterhold.mvc.models.Category;
import com.winterhold.mvc.repositories.AuthorRepository;
import com.winterhold.mvc.repositories.BookRepository;
import com.winterhold.mvc.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookService {

    private BookRepository bookRepository;
    private CategoryRepository categoryRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository, CategoryRepository categoryRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
    }

    private final int PAGE_LIMIT = 5;

    public Page<Category> findAllCategories(int page, String category){
        Page<Category> allCategories = categoryRepository.findByIdContaining(
                category, PageRequest.of(page - 1, PAGE_LIMIT, Sort.by("id")));
        return allCategories;
    }

    public UpdateInsertCategoryDTO findCategoryById(String category){
        Category dataCategory = categoryRepository.findById(category)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        return new UpdateInsertCategoryDTO(
                dataCategory.getId(),
                dataCategory.getFloor(),
                dataCategory.getIsle(),
                dataCategory.getBay());
    }

    public void saveCategory(UpdateInsertCategoryDTO upsertCategory){
        Category dataCategory = new Category(
                upsertCategory.getId(),
                upsertCategory.getFloor(),
                upsertCategory.getIsle(),
                upsertCategory.getBay());
        categoryRepository.save(dataCategory);
    }

    public void deleteCategoryById(String categories){
        Category category = categoryRepository.findById(categories)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
            categoryRepository.delete(category);
    }

    public Page<Book> findAllBookByCategory(String category, String title, String author, int page){
        Page<Book> allBooks = bookRepository.findByCategoryName_idAndTitleContainingAndAuthor_fullNameContaining(category, title, author, PageRequest.of(page - 1, PAGE_LIMIT, Sort.by("id")));
        return allBooks;
    }

    public UpdateInsertBookDTO findBookById(String bookId){
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        return new UpdateInsertBookDTO(
                book.getId(),
                book.getTitle(),
                book.getCategoryName().getId(),
                book.getAuthor().getId(),
                book.getReleaseDate(),
                book.getTotalPage(),
                book.getSummary());
    }

    public void saveBook(UpdateInsertBookDTO newBook){
        Author author = authorRepository.findById(newBook.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));
        Category category = categoryRepository.findById(newBook.getCategoryName())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        Book dataBook = new Book(
                newBook.getId(),
                newBook.getTitle(),
                category,
                author,
                newBook.getSummary(),
                newBook.getReleaseDate(),
                newBook.getTotalPage());

        bookRepository.save(dataBook);
    }

    public void deleteBook(String deleteBookId){
        Book book = bookRepository.findById(deleteBookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        bookRepository.delete(book);
    }
}
