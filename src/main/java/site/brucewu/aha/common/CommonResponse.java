package site.brucewu.aha.common;

import lombok.Data;

@Data
public class CommonResponse<T> {

    private String code;

    private String message;

    private T data;

    public CommonResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public CommonResponse() {
    }

    public static <T> CommonResponse<T> ok() {
        return new CommonResponse<>("200", "准了", null);
    }
}
