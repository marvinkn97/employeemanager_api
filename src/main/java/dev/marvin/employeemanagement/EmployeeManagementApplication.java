package dev.marvin.employeemanagement;

import com.github.javafaker.Faker;
import dev.marvin.employeemanagement.model.Employee;
import dev.marvin.employeemanagement.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class EmployeeManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository){
		return args -> {
			Faker faker = new Faker();

			for (int i = 0; i < 15; i++) {

				var name = faker.name().name();
				var email = name.toLowerCase().trim().concat("@example.com");

				Employee newEmployee = Employee.builder()
						.name(name)
						.email(email)
						.phoneNumber(faker.phoneNumber().cellPhone())
						.jobTitle(faker.job().title())
						.imageUrl(faker.avatar().image())
						.employeeCode(UUID.randomUUID().toString().substring(0, 6))
						.build();

				employeeRepository.save(newEmployee);
			}
		};
	}

}
