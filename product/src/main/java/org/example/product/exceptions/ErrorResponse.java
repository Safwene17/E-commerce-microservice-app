package org.example.product.exceptions;
import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
