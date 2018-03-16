package ca.purpleowl.examples.swing;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SpringBootSwingApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringBootSwingApplication.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
