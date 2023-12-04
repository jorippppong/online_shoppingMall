package shop.onlineShop.global.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shop.onlineShop.global.uniformApi.ApiResponse;
import shop.onlineShop.global.uniformApi.ErrorStatus;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ApiResponse<String> handleMemberException(CustomException ex){
        return ApiResponse.onFailure(ex.getErrorStatus(), null);
    }

    @ExceptionHandler(Exception.class)
    protected ApiResponse<String> handleServerException(){
        return ApiResponse.onFailure(ErrorStatus._INTERNAL_SERVER_ERROR, null);
    }

}