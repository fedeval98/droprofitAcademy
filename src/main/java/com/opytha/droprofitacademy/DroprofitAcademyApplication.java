package com.opytha.droprofitacademy;

import com.opytha.droprofitacademy.models.Client;
import com.opytha.droprofitacademy.models.Courses;
import com.opytha.droprofitacademy.models.Videos;
import com.opytha.droprofitacademy.models.enums.Roles;
import com.opytha.droprofitacademy.repositories.ClientsRepository;
import com.opytha.droprofitacademy.repositories.CoursesRepository;
import com.opytha.droprofitacademy.repositories.VideosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
@PropertySource("classpath:.env")
public class DroprofitAcademyApplication {

	public static void main(String[] args) {
		SpringApplication.run(DroprofitAcademyApplication.class, args);
	}

	@Autowired
	public PasswordEncoder passwordEncoder;

/*	@Bean
	public CommandLineRunner initData(ClientsRepository clientsRepository,
									  CoursesRepository coursesRepository,
									  VideosRepository videosRepository){
		return args -> {

			Client fede = new Client("Fede","Val","fede@gmail.com",passwordEncoder.encode("fedeval1"));
			fede.setRol(Roles.ADMIN);

			clientsRepository.save(fede);


			Client user = new Client("User","Val","user@gmail.com",passwordEncoder.encode("userval1"));
			clientsRepository.save(user);

			Courses java = new Courses("Java");

			Videos introJava = new Videos("Introduccion a Java", "https://www.youtube.com/watch?v=STVXkRO4LZY", LocalDate.now());

			Videos advancedJava = new Videos("Java Avanzado","https://www.youtube.com/watch?v=50y7KYHeSHc", LocalDate.now());

			java.addVideos(introJava);
			java.addVideos(advancedJava);

			coursesRepository.save(java);
			videosRepository.save(introJava);
			videosRepository.save(advancedJava);

			user.addCourses(java);
		};
	}
*/

}
