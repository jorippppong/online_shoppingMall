package shop.onlineShop.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import shop.onlineShop.global.uniformApi.ErrorStatus;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {
    private final ErrorStatus errorStatus;
}
