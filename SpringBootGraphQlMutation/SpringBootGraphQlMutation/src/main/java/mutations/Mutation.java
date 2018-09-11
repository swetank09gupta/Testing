package mutations;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import models.Author;
import models.Book;
import repository.AuthorRepository;
import repository.BookRepository;

import java.util.Optional;


public class Mutation implements GraphQLMutationResolver {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public Mutation(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Author newAuthor(String firstName, String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);

        AuthorRepository authorRepository = null;
        authorRepository.save(author);
        return author;
    }

    public Book newBook(String title, String isbn, Integer pageCount, Long authorId) {
        Book book = new Book();
        book.setAuthor(new Author(authorId));
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setPageCount(pageCount != null ? pageCount : 0);

        BookRepository bookRepository = null;
        bookRepository.save(book);

        return book;
    }

    public boolean deleteBook (Long id) {
        bookRepository.delete(id);
        return true;
    }


    public Book updateBookPageCount (Integer pageCount, Long id) {

        Optional<Book> optionalBook = bookRepository.findById(id);
        Book book = optionalBook.get();
        book.setPageCount(pageCount);
        bookRepository.save(book);
        return book;
    }
}
