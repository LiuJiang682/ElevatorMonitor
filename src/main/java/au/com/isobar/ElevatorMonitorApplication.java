package au.com.isobar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class ElevatorMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElevatorMonitorApplication.class, args);
	}
}
