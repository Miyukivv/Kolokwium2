package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Kolokwium2Application {

	public static void main(String[] args) {
		ImageRGB imageRGB = ImageRGB.getInstance();
		imageRGB.setImageBasedOnPixels();

		SpringApplication.run(Kolokwium2Application.class, args);
	}

}
