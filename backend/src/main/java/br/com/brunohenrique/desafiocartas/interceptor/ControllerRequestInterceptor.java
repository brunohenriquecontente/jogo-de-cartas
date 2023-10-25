package br.com.brunohenrique.desafiocartas.interceptor;

import static org.slf4j.LoggerFactory.*;

import br.com.brunohenrique.desafiocartas.utils.LogConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ControllerRequestInterceptor implements HandlerInterceptor {

  private static final Logger LOGGER = getLogger(ControllerRequestInterceptor.class);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    LOGGER.info(
        LogConstants.INFO_REQUEST_RECEIVED,
        request.getMethod(),
        request.getRequestURI(),
        request.getRemoteAddr());
    return true;
  }

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
    LOGGER.info(
        LogConstants.INFO_REQUEST_PROCESSED,
        request.getMethod(),
        request.getRequestURI(),
        response.getStatus(),
        ex);
  }
}
