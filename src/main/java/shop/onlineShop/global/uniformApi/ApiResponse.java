package shop.onlineShop.global.uniformApi;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;

import static shop.onlineShop.global.uniformApi.SuccessStatus._OK;

@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {
    private Boolean isSuccess;
    private String code;
    private String message;
    private T result;

    public static <T> ApiResponse<T> onSuccess(T result){
        return new ApiResponse<>(true, _OK.getCode(), _OK.getMessage(), result);
    }

    public static <T> ApiResponse<T> onFailure(ErrorStatus errorStatus, T result){
        return new ApiResponse<>(false, errorStatus.getCode(), errorStatus.getMessage(), result);
    }

}
