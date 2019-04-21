package ru.project.cscm.war;

import javax.validation.constraints.NotNull;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.WebApplicationInitializer;

import ru.project.cscm.dao.config.DaoConfiguration;
import ru.project.cscm.ws.core.config.WebMvcConfiguration;

@SpringBootApplication
@ComponentScan("ru.project.cscm")
@Import({ DaoConfiguration.class, WebMvcConfiguration.class })
public class CscmWarApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return configureApplication(builder);
	}

	public static void main(final String[] args) {
		configureApplication(new SpringApplicationBuilder()).run(args);
	}

	@NotNull
	private static SpringApplicationBuilder configureApplication(@NotNull final SpringApplicationBuilder builder) {
		return builder.sources(CscmWarApplication.class);
	}
}
