package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;

	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency,
								  MyBean myBean,
								  MyBeanWithDependency myBeanWithDependency,
								  MyBeanWithProperties myBeanWithProperties,
								  UserPojo userPojo,
								  UserRepository userRepository){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// ejemplosAnteriores();
		saveUserInDataBase();
		getInformationJpqlFromUser();
	}

	private void getInformationJpqlFromUser()
	{
		LOGGER.info("Usuario con el metodo findByUserEmail : "+userRepository.findByUserEmail("prueba@prueba.com").
				orElseThrow(() -> new RuntimeException("No se encontro el usuario")));
		userRepository.findAndSort("Prueb", Sort.by("id").descending())
				.forEach(user -> LOGGER.info("Usuario con metodo sort : "+user));

		userRepository.findByName("Prueba")
				.forEach(user -> LOGGER.info("Usuario con query method : "+user));

		LOGGER.info("Usuario con query method findByEmailAndName: "+userRepository.findByEmailAndName("prueba5@prueba.com","Prueba5")
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
	}

	private void saveUserInDataBase(){
		User user1 = new User ("Prueba","prueba@prueba.com", LocalDate.of(2021,8,10));
		User user2 = new User ("Prueba2","prueba2@prueba.com", LocalDate.of(2021,8,10));
		User user3 = new User ("Prueba3","prueba3@prueba.com", LocalDate.of(2021,8,10));
		User user4 = new User ("Prueba4","prueba4@prueba.com", LocalDate.of(2021,8,10));
		User user5 = new User ("Prueba5","prueba5@prueba.com", LocalDate.of(2021,8,10));

		List<User> list = Arrays.asList(user1,user2,user3,user4,user5);
		list.forEach(userRepository::save);
	}

	private void ejemplosAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDepedency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail()+" - "+userPojo.getPassword());
		try{
			//error
			int value = 10/0;
			LOGGER.debug("Mi valor : "+value);
		}catch(Exception e){
			LOGGER.error("Esto es un error : "+e);
		}
	}
}
