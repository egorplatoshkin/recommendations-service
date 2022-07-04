package com.xm.controller.v1;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class CustomErrorController implements ErrorController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/error")
    public void handleError(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        logger.info("Was requested to the broken URI");
        resp.sendRedirect(request.getRequestURL().toString().replace("error", "swagger-ui/"));
    }
}