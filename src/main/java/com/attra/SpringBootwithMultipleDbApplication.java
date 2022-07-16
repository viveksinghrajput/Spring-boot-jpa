package com.attra;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attra.book.repository.BookRepository;
import com.attra.model.book.Book;
import com.attra.model.user.User;
import com.attra.user.repository.UserRepository;

@SpringBootApplication
@RestController
public class SpringBootwithMultipleDbApplication {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void addDataDB() {
		userRepository.saveAll(Stream.of(new User(744, "John"), new User(755, "Piter")).collect(Collectors.toList()));
		bookRepository.saveAll(
				Stream.of(new Book(888, "Core Java"), new Book(999, "SpringBoot")).collect(Collectors.toList()));
	}
	
	@GetMapping("/getUsers")
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/getBooks")
	public List<Book> getBooks(){
		return bookRepository.findAll();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootwithMultipleDbApplication.class, args);
	}

}
