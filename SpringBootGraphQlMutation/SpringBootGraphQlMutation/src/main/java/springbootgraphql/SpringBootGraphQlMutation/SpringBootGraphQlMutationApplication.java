package springbootgraphql.SpringBootGraphQlMutation;

import mutations.Mutation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import repository.AuthorRepository;
import repository.BookRepository;
import resolver.BookResolver;
import resolver.Query;

@SpringBootApplication
@ComponentScan ("repository")
public class SpringBootGraphQlMutationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootGraphQlMutationApplication.class, args);
    }

    @Bean
    public BookResolver authorResolver(AuthorRepository authorRepository) {
        return new BookResolver(authorRepository);
    }

    @Bean
    public Query query(AuthorRepository authorRepository, BookRepository bookRepository) {
        return new Query(bookRepository, authorRepository);
    }

    @Bean
    public Mutation mutation(AuthorRepository authorRepository, BookRepository bookRepository) {
        return new Mutation(authorRepository, bookRepository);
    }

}
