package pl.kzadros.tictactoe.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author kzadros
 */
@Configuration
@ComponentScan(basePackages = "pl.kzadros.tictactoe")
@EnableWebMvc
public class AppConfig {
    
}
