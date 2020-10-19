package com.example.boltjavasample;

import com.slack.api.bolt.App;
import com.slack.api.bolt.servlet.SlackAppServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BoltJavaSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoltJavaSampleApplication.class, args);
    }

    @Bean
    public App initSlackApp() {
        App app = new App();
        app.command("/hello", (req, ctx) -> ctx.ack(":wave: Hello!"));
        return app;
    }

    @Bean
    public ServletRegistrationBean<SlackAppServlet> slackAppServletServlet(App app) {
        SlackAppServlet servlet = new SlackAppServlet(app);
        ServletRegistrationBean<SlackAppServlet> registrationBean =
                new ServletRegistrationBean<>(servlet);
        registrationBean.addUrlMappings("/slack/event");
        return registrationBean;
    }
}
