package com.elitsoft.servicampo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 *
 */
@ComponentScan(basePackages = {"com.elitsoft.servicampo"})
@SpringBootApplication
@EnableAutoConfiguration // Add this line
public class ServicampoApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ServicampoApplication.class, args);
	}

}