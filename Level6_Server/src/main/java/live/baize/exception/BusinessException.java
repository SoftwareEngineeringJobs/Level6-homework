package live.baize.exception;

import live.baize.dto.ResponseEnum;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final ResponseEnum responseEnum;

    public BusinessException(ResponseEnum responseEnum) {
        super(responseEnum.getMsg());
        this.responseEnum = responseEnum;
    }

    public BusinessException(ResponseEnum responseEnum, Throwable cause) {
        super(responseEnum.getMsg(), cause);
        this.responseEnum = responseEnum;
    }
}
