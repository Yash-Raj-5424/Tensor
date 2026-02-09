package com.eigen.tensor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TensorApplication {

	public static void main(String[] args) {
//		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(TensorApplication.class, args);
	}

}
