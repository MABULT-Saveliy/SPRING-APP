package com.example.bookapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List&lt;Book&gt; getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity&lt;Book&gt; getBookById(@PathVariable Long id) {
        Optional&lt;Book&gt; book = bookService.getBookById(id);
        return book.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity&lt;Book&gt; createBook(@RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        return new ResponseEntity&lt;&gt;(createdBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity&lt;Book&gt; updateBook(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        Book updatedBook = bookService.updateBook(book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity&lt;Void&gt; deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}