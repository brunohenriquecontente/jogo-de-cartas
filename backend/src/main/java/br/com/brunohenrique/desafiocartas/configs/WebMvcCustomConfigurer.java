package br.com.brunohenrique.desafiocartas.configs;

import br.com.brunohenrique.desafiocartas.interceptor.ControllerRequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcCustomConfigurer implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new ControllerRequestInterceptor());
  }
}
