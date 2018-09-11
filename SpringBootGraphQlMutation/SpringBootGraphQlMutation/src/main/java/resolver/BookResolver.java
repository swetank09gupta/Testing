package resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import models.Author;
import models.Book;
import repository.AuthorRepository;

@AllArgsConstructor
public class BookResolver implements GraphQLResolver<Book> {
    private AuthorRepository authorRepository;

    public Author getAuthor(Book book) {
        return authorRepository.findOne(book.getAuthor().getId());
    }
}
