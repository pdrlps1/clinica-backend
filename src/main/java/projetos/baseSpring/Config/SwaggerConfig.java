package projetos.baseSpring.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
    public OpenAPI customOpenAPI() { 
        return new OpenAPI() 
                .info(new Info() 
                        .title("API baseSpring Online") 
                        .version("1.0") 
                        .description("Documentação da API de exemplo de baseSpring online")
                        .contact(new Contact()
                            .name("Equipe de TI")
                            .url("https://www.reges.com.br")
                            .email("equipeti@equipeti.com.br"))); 
    } 

}
