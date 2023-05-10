package live.baize.exception;

import live.baize.dto.ResponseEnum;
import lombok.Getter;

@Getter
public class SystemException extends RuntimeException {
    private final ResponseEnum responseEnum;

    public SystemException(ResponseEnum responseEnum) {
        super(responseEnum.getMsg());
        this.responseEnum = responseEnum;
    }

    public SystemException(ResponseEnum responseEnum, Throwable cause) {
        super(responseEnum.getMsg(), cause);
        this.responseEnum = responseEnum;
    }
}
