package shop.onlineShop.global.uniformApi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum SuccessStatus {
    _OK(HttpStatus.OK, "COMMON200", "success");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
