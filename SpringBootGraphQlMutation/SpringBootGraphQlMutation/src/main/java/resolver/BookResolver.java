package resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import models.Author;
import models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import repository.AuthorRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public class BookResolver implements GraphQLResolver<Book> {
    @Autowired
    private AuthorRepository authorRepository;

    public BookResolver(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getAuthor(Book book) {
        Optional<Author> optionalAuthor = authorRepository.findById(book.getAuthor().getId());
        return optionalAuthor.get();
    }
}
