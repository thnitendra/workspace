package app.common;

import app.model.CustomUserBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j(topic = "APP_ACCESS_LOG")
public class RequestFilter implements Filter{

    @Override
    public void destroy() {
        // Do nothing
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        try {
            CustomUserBean loggedInUser = getUser();

            if(loggedInUser != null) {
                request.getSession().setAttribute("user", loggedInUser);
            }
        } catch(RuntimeException e) {
            //Valid case. User has not logged-in yet.
        }

        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // Do nothing
    }

    public static CustomUserBean getUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        log.debug(securityContext.toString());
        Authentication authentication = securityContext.getAuthentication();

        if (authentication == null) {
            throw new RuntimeException("NO_USER_LOGGED_IN");
        }

        CustomUserBean customUserBean = null;

        if (authentication.getPrincipal() instanceof CustomUserBean) {
            customUserBean = (CustomUserBean) authentication.getPrincipal();

            if(customUserBean == null || customUserBean.getUserId().contains("anonymous")) {
                throw new RuntimeException("NO_USER_LOGGED_IN");
            }
        } else {
            throw new RuntimeException("NO_USER_LOGGED_IN");
        }

        return customUserBean;
    }
}