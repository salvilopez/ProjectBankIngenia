package com.ingenia.projectbank;

import com.ingenia.projectbank.model.*;
import com.ingenia.projectbank.service.AccountService;
import com.ingenia.projectbank.service.BankCardService;
import com.ingenia.projectbank.service.MovementService;
import com.ingenia.projectbank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class ProjectBankApplication implements CommandLineRunner {


	 @Autowired
	MovementService movementService;

	 @Autowired
	AccountService accountService;

	@Autowired
	UserService userService;

	@Autowired
	BankCardService bankCardService;

	@Autowired
	PasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(ProjectBankApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


		Account account1 = new Account("es2452645435454",3000.0, 2200.00);
		Account account3 = new Account("es5256475585755",2000.0, 3200.00);
		Account account4 = new Account("es1212122323131",5000.0, 1600.00);
		Account account2 = new Account("es8787878787878",6000.0, 1400.00);

		Movement movement1=new Movement(OperationType.REST, PaymentType.ACCOUNT, Instant.now(),LocalDate.now(),320.0,account1, CategoryType.UTILITIES);
		Movement movement2=new Movement(OperationType.REST, PaymentType.CREDIT,Instant.parse("2021-01-01T18:35:24.00Z"),LocalDate.parse("2021-01-01"),100.0,account1, CategoryType.CLOTHES);
		Movement movement3=new Movement(OperationType.REST, PaymentType.DEBIT,Instant.parse("2021-01-10T18:35:24.00Z"),LocalDate.parse("2021-01-10"),20.0,account1, CategoryType.FUEL);
		Movement movement4=new Movement(OperationType.REST, PaymentType.ACCOUNT,Instant.parse("2021-01-15T18:35:24.00Z"),LocalDate.parse("2021-01-15"),40.0,account1, CategoryType.RESTAURANTS);
		Movement movement5=new Movement(OperationType.REST, PaymentType.DEBIT,Instant.parse("2021-01-20T18:35:24.00Z"),LocalDate.parse("2021-01-20"),400.0,account1, CategoryType.UTILITIES);
		Movement movement6=new Movement(OperationType.SUM, PaymentType.ACCOUNT,Instant.parse("2021-02-01T18:35:24.00Z"),LocalDate.parse("2021-02-01"),1200.0,account1, CategoryType.PAID);
		Movement movement7=new Movement(OperationType.SUM, PaymentType.ACCOUNT,Instant.parse("2021-02-10T18:35:24.00Z"),LocalDate.parse("2021-02-10"),1200.0,account1, CategoryType.PAID);
		Movement movement8=new Movement(OperationType.REST, PaymentType.CREDIT,Instant.parse("2021-02-20T18:35:24.00Z"),LocalDate.parse("2021-02-20"),300.0,account2, CategoryType.CLOTHES);
		Movement movement9=new Movement(OperationType.SUM, PaymentType.ACCOUNT,Instant.parse("2021-02-22T18:35:24.00Z"),LocalDate.parse("2021-02-22"),1200.0,account2, CategoryType.PAID);
		Movement movement10=new Movement(OperationType.SUM, PaymentType.ACCOUNT,Instant.parse("2021-03-01T18:35:24.00Z"),LocalDate.parse("2021-03-01"),200.0,account2, CategoryType.PAID);
		Movement movement11=new Movement(OperationType.SUM, PaymentType.CREDIT,Instant.parse("2021-03-05T18:35:24.00Z"),LocalDate.parse("2021-03-05"),200.0,account2, CategoryType.PAID);
		Movement movement14=new Movement(OperationType.REST, PaymentType.DEBIT,Instant.parse("2021-04-01T18:35:24.00Z"),LocalDate.parse("2021-04-01"),200.0,account2, CategoryType.PAID);
		Movement movement15=new Movement(OperationType.SUM, PaymentType.DEBIT,Instant.parse("2021-04-10T18:35:24.00Z"),LocalDate.parse("2021-04-10"),200.0,account2, CategoryType.PAID);
		Movement movement16=new Movement(OperationType.SUM, PaymentType.DEBIT,Instant.parse("2021-04-15T18:35:24.00Z"),LocalDate.parse("2021-04-15"),200.0,account2, CategoryType.PAID);


		User user1 = new User("Borja", "Díaz", "pruebas@pruebas.com",encoder.encode("pruebas"));
		User user2 = new User("Elena", "Fernández", "elena@fernandez",encoder.encode("1234"));
		User salvi = new User("salvi", "Lopez", "salvilopezpruebas@gmail.com",encoder.encode("salvi"));


		BankCard bankCard1 = new BankCard("456435453435435", "221", Instant.now(),CardType.VISA);
		BankCard bankCard2 = new BankCard("392489234898492", "221", Instant.now(),CardType.MASTERCARD);
		BankCard bankCard3 = new BankCard("657454854254554", "221", Instant.now(),CardType.VISA);
		BankCard bankCard4 = new BankCard("354654846545468", "114",Instant.now(),CardType.MASTERCARD);


		Prestam prestam1 = new Prestam(InterestType.FIJO,600.0,6,account1,account3);
		Prestam prestam2 = new Prestam(InterestType.FIJO,1200.0,12,account1,account3);
		Prestam prestam3 = new Prestam(InterestType.FIJO,300.0,24,account1,account3);
		Prestam prestam4 = new Prestam(InterestType.FIJO,700.0,12,account2,account2);




		account1.getPrestamsPayments().add(prestam1);
		account3.getPrestamsIncomes().add(prestam1);

		account1.getPrestamsPayments().add(prestam2);
		account3.getPrestamsIncomes().add(prestam2);


		account2.getPrestamsPayments().add(prestam4);
		account2.getPrestamsIncomes().add(prestam4);


		account1.addMovimiento(movement1);
		account1.addMovimiento(movement2);
		account1.addMovimiento(movement3);
		account1.addMovimiento(movement4);
		account1.addMovimiento(movement5);
		account1.addMovimiento(movement6);
		account1.addMovimiento(movement7);
		account2.addMovimiento(movement10);
		account2.addMovimiento(movement11);
		account2.addMovimiento(movement14);
		account2.addMovimiento(movement15);
		account2.addMovimiento(movement16);





		movement1.setAccount(account1);
		movement2.setAccount(account1);
		movement3.setAccount(account1);
		movement4.setAccount(account1);
		movement5.setAccount(account1);
		movement6.setAccount(account1);
		movement7.setAccount(account1);
        movement10.setAccount(account2);
        movement11.setAccount(account2);
		movement14.setAccount(account2);
		movement15.setAccount(account2);
		movement16.setAccount(account2);






		user1.getAccounts().add(account1);
		user1.getAccounts().add(account3);
		user1.getAccounts().add(account4);
		user2.getAccounts().add(account2);

		account1.getUsers().add(user1);
		account3.getUsers().add(user1);
		account4.getUsers().add(user1);
		account2.getUsers().add(user2);

		account1.getCards().add(bankCard1);
		account3.getCards().add(bankCard3);
		account4.getCards().add(bankCard4);
		account2.getCards().add(bankCard2);

		bankCard1.setAccount(account1);
		bankCard3.setAccount(account3);
		bankCard4.setAccount(account4);
		bankCard2.setAccount(account2);

		account2.addMovimiento(movement8);
		movement8.setAccount(account2);
		account2.addMovimiento(movement9);
		movement9.setAccount(account2);

		accountService.createAccount(account1);
		accountService.createAccount(account3);
		accountService.createAccount(account4);

		accountService.createAccount(account2);


		userService.createUser(salvi);
		userService.createUser(user1);
		userService.createUser(user2);
		bankCardService.createBankCard(bankCard1);
		bankCardService.createBankCard(bankCard2);
		bankCardService.createBankCard(bankCard3);
		bankCardService.createBankCard(bankCard4);












	}


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST","PUT", "DELETE");
			}
		};
	}
}
