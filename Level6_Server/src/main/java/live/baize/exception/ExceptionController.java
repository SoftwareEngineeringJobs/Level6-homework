package live.baize.exception;

import live.baize.dto.Response;
import live.baize.dto.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.ServletException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MultipartException.class)
    public Response handleMultipartException(MultipartException multipartException) {
        return new Response(ResponseEnum.Not_IsMultipart);
    }

    // 参数异常
    @ExceptionHandler(ValidationException.class)
    public Response handleValidationException(ValidationException validationException) {
        List<String> errorList = new ArrayList<>();
        if (validationException instanceof ConstraintViolationException) {
            ConstraintViolationException exs = (ConstraintViolationException) validationException;
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                errorList.add(item.getMessage());
            }
        } else {
            errorList.add(validationException.getMessage());
        }
        return new Response(ResponseEnum.Param_Check, errorList);
    }

    @ExceptionHandler(ServletException.class)
    public Response handleServletException(ServletException servletException) {
        log.info(servletException.getMessage(), servletException);
        // 请求方法不支持
        if (servletException instanceof HttpRequestMethodNotSupportedException) {
            return new Response(ResponseEnum.Method_Not_Supported);
        }

        // 参数校验缺失
        if (servletException instanceof MissingRequestValueException) {
            return new Response(ResponseEnum.Param_Missing, servletException.getMessage());
        }

        // 请求体参数缺失
        if (servletException instanceof MissingServletRequestPartException) {
            return new Response(ResponseEnum.Request_Part_Missing, servletException.getMessage());
        }

        return new Response(ResponseEnum.SYSTEM_UNKNOWN, servletException.getMessage());
    }

    @ExceptionHandler(IOException.class)
    public Response handleIOException(IOException ioException) {
        // 记录日志
        log.error(ioException.getMessage(), ioException);
        return new Response(ResponseEnum.SYSTEM_UNKNOWN);
    }

    @ExceptionHandler(BusinessException.class)
    public Response handleBusinessException(BusinessException businessException) {
        // 记录日志
        log.warn(businessException.getMessage(), businessException);
        // 直接返回即可
        return new Response(businessException.getResponseEnum());
    }

    @ExceptionHandler(SystemException.class)
    public Response handleSystemException(SystemException systemException) {
        // 记录日志
        log.error(systemException.getMessage(), systemException);
        // 返回前端信息
        return new Response(systemException.getResponseEnum());
    }

    @ExceptionHandler(Exception.class)
    public Response handleException(Exception exception) {
        // 记录日志
        log.error(exception.getMessage(), exception);
        // 返回前端信息
        return new Response(ResponseEnum.SYSTEM_UNKNOWN);
    }

}
