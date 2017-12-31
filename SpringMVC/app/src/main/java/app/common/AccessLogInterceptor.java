package app.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static net.logstash.logback.argument.StructuredArguments.entries;

@Slf4j(topic = "APP_ACCESS_LOG")
public class AccessLogInterceptor extends HandlerInterceptorAdapter {

    private void logRequest(HttpServletRequest request, Map<String, String> map) {
        try {
            //skip wwwcheck
            String URL = request.getRequestURL().toString();
            if (URL.contains("wwwcheck") || URL.contains("resources")) {
                map.put("skip", "true");
                return;
            }

            map.put("method", request.getMethod());
            map.put("URL", request.getRequestURL().toString());
            map.put("contentType", request.getContentType());
        } catch (Exception e) {
            log.warn("Request logging error", e);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Map<String, String> map = new HashMap<>();
        try {
            map.put("method", request.getMethod());
            map.put("URL", request.getRequestURL().toString());
            map.put("contentType", request.getContentType());
            map.put("status", String.valueOf(response.getStatus()));
            if (response.getStatus() == HttpStatus.OK.value() || response.getStatus() == HttpStatus.FOUND.value()) {
                log.debug("Access Log {}", entries(map));
            } else {
                log.info("Access Log {}", entries(map));
            }
        } catch (Exception e) {
            log.warn("Request logging error", e);
        }
        super.afterCompletion(request, response, handler, ex);
    }

}
