package com.lesliemu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
		System.out.println("helloworld");
	    SpringApplication.run(Application.class, args);
    }

}
