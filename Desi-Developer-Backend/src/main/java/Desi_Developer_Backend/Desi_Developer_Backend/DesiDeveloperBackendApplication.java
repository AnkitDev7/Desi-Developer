package Desi_Developer_Backend.Desi_Developer_Backend;

import Desi_Developer_Backend.Desi_Developer_Backend.Entity.Admin;
import Desi_Developer_Backend.Desi_Developer_Backend.Repositry.AdminRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DesiDeveloperBackendApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(DesiDeveloperBackendApplication.class, args);

		AdminRepository adminRepo = run.getBean(AdminRepository.class);

		PasswordEncoder bean1 = run.getBean(PasswordEncoder.class);

		if(adminRepo.findByEmail("ankit@gmail.com").isEmpty()){

			Admin admin = new Admin();
			admin.setEmail("ankit@gmail.com");
			admin.setName("ADMIN");
			admin.setPassword(bean1.encode("admin123"));
			adminRepo.save(admin);

			System.out.println("ADMIN INSERTED");
		}else {
			System.out.println("Admin Already Exist");
		}

	}

}
