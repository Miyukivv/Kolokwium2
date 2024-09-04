package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Kolokwium2Application {

	public static void main(String[] args) {
		ImageRGB imageRGB = ImageRGB.getInstance();
		imageRGB.setImageBasedOnPixels();

		Server server = new Server(5000);
		server.start();

		SpringApplication.run(Kolokwium2Application.class, args);
	}

}
