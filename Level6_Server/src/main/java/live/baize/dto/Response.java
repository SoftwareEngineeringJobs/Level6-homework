package live.baize.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Response {
    private Integer code;
    private Object data;
    private String msg;
    private Date date;

    public Response(ResponseEnum responseEnum) {
        this.msg = responseEnum.getMsg();
        this.code = responseEnum.getCode();
        this.data = null;
        this.date = new Date();
    }

    public Response(ResponseEnum responseEnum, Object data) {
        this.msg = responseEnum.getMsg();
        this.code = responseEnum.getCode();
        this.data = data;
        this.date = new Date();
    }

}
