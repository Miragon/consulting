package io.miragon.publicplandemocore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class PublicPlanDemoConfiguration {

	private final StorePDFPort storePDFPort;
	private final GeneratePDFPort generatePDFPort;

	@Bean
	public GeneratePDFUseCase generatePDFUseCase(){
		return new GeneratePDFService(this.generatePDFPort);
	}

	@Bean
	public StorePDFUseCase storePDFUseCase() {
		return new StorePDFService(this.storePDFPort);
	}

	@Bean
	public void tenantInterceptor() {}

}
