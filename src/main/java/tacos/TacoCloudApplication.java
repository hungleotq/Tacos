package tacos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Admin
 *
 *Main class -> entry of spring boot app, able to work like Main method of class
 */
@Slf4j
@SpringBootApplication
public class TacoCloudApplication {
	
	public static void main(String[] args) {
		log.info("start Taco app (-_-) (v_v)");
		log.info("init App context");
		ConfigurableApplicationContext appContext = SpringApplication.run(TacoCloudApplication.class, args);
		log.info("finish init Taco app");
	}

}
