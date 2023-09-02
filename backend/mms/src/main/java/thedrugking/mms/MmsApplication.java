package thedrugking.mms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MmsApplication.class, args);
	}

}
