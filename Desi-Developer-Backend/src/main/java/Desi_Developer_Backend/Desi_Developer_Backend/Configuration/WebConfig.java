package Desi_Developer_Backend.Desi_Developer_Backend.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry
                .addResourceHandler("/music/**")
                .addResourceLocations("file:uploads/music/");

        registry
                .addResourceHandler("/covers/**")
                .addResourceLocations("file:uploads/covers/");
    }
}
