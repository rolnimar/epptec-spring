package epptec.epptecspring;

import epptec.epptecspring.Controller.OptionController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EpptecSpringApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(EpptecSpringApplication.class, args);
		OptionController optionController = ctx.getBean(OptionController.class);
		optionController.init();
	}

}
