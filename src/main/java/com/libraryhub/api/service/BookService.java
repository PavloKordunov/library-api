package com.libraryhub.api.service;

import com.libraryhub.api.entity.Book;
import com.libraryhub.api.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Book updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id).map(existingBook -> {
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthorLastName(updatedBook.getAuthorLastName());
            existingBook.setAuthorInitials(updatedBook.getAuthorInitials());
            existingBook.setCopiesCount(updatedBook.getCopiesCount());
            existingBook.setPublicationYear(updatedBook.getPublicationYear());

            return bookRepository.save(existingBook);
        }).orElseThrow(() -> new RuntimeException("Книгу з ID " + id + " не знайдено"));
    }

    public List<Book> getAllBooks(String sortBy, String sortDir) {
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? Sort.Direction.DESC : Sort.Direction.ASC;

        if ("author".equalsIgnoreCase(sortBy)) {
            return bookRepository.findAll(Sort.by(direction, "authorLastName", "authorInitials"));
        } else if ("year".equalsIgnoreCase(sortBy)) {
            return bookRepository.findAll(Sort.by(direction, "publicationYear"));
        } else {
            return bookRepository.findAll(Sort.by(direction, "id"));
        }
    }
}