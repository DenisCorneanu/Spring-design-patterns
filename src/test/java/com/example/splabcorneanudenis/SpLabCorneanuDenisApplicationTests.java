package com.example.splabcorneanudenis;

import com.example.splabcorneanudenis.book.Book;
import com.example.splabcorneanudenis.book.Paragraph;
import com.example.splabcorneanudenis.book.Section;
import com.example.splabcorneanudenis.books.AllBooksSubject;
import com.example.splabcorneanudenis.books.BooksObserver;
import com.example.splabcorneanudenis.books.BooksService;
import com.example.splabcorneanudenis.books.CreateBookCommand;
import com.example.splabcorneanudenis.books.GetAllBooksCommand;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DesignPatternsTests {

    @Test
    void testCreareCarte() {
        BooksService service = mock(BooksService.class);
        Book book = new Book("Clean Code");

        when(service.create(book)).thenReturn(book);

        CreateBookCommand command =
                new CreateBookCommand(service, book);

        Book result = command.execute();

        assertSame(book, result);
        verify(service).create(book);
    }

    @Test
    void testObtinereCarti() {
        BooksService service = mock(BooksService.class);

        Book firstBook = new Book("Clean Code");
        Book secondBook = new Book("Design Patterns");

        List<Book> books =
                List.of(firstBook, secondBook);

        when(service.findAll()).thenReturn(books);

        GetAllBooksCommand command =
                new GetAllBooksCommand(service);

        List<Book> result = command.execute();

        assertEquals(2, result.size());
        assertSame(books, result);
        verify(service).findAll();
    }

    @Test
    void testNotificareObserver() {
        AllBooksSubject subject =
                new AllBooksSubject();

        BooksObserver observer =
                mock(BooksObserver.class);

        Book book =
                new Book("Spring in Action");

        subject.attach(observer);
        subject.add(book);

        verify(observer).update(book);
    }

    @Test
    void testStergereObserver() {
        AllBooksSubject subject =
                new AllBooksSubject();

        BooksObserver observer =
                mock(BooksObserver.class);

        Book book =
                new Book("Spring Boot");

        subject.attach(observer);
        subject.detach(observer);
        subject.add(book);

        verify(observer, never()).update(book);
    }

    @Test
    void testAdaugareElementInSectiune() {
        Section section =
                new Section("Introducere");

        Paragraph paragraph =
                new Paragraph("Primul paragraf");

        section.add(paragraph);

        assertEquals(
                1,
                section.getElements().size()
        );

        assertSame(
                paragraph,
                section.getElements().get(0)
        );
    }
}
