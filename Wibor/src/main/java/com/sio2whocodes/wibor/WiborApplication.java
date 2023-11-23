package com.sio2whocodes.wibor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class WiborApplication {

	public static void main(String[] args) {
		SpringApplication.run(WiborApplication.class, args);
	}

}
