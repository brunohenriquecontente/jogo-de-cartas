package br.com.brunohenrique.desafiocartas.interceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static org.slf4j.LoggerFactory.*;

@Component
public class ControllerRequestInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = getLogger(ControllerRequestInterceptor.class);

    @Override
    @NonNull
    public boolean preHandle(
            @NonNull final HttpServletRequest request,
            @NonNull final HttpServletResponse response,
            @NonNull final Object handler) {

        request.l

        return true;
    }

}
