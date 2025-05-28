package com.virellarent.backend.util;

import java.time.LocalDateTime;

public record RegisterRequest(
    String username,
    String email,
    String password,
    LocalDateTime createIn
) {

}
