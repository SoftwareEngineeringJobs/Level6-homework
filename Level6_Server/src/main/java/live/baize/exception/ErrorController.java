package live.baize.exception;

import live.baize.dto.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RestControllerAdvice
public class ErrorController extends BasicErrorController {

    @Autowired
    public ErrorController(ErrorAttributes errorAttributes, ServerProperties serverProperties, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, serverProperties.getError(), errorViewResolvers);
    }

    // ============================================ Error ============================================ //
    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("/error.html", new HashMap<>(), getStatus(request));
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("code", ResponseEnum.Res_Not_Found.getCode());
        body.put("msg", ResponseEnum.Res_Not_Found.getMsg());
        body.put("date", new Date());
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

}
