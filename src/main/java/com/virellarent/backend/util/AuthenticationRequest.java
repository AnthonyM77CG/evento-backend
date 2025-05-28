package com.virellarent.backend.util;

public record AuthenticationRequest(
    String email,
    String password
) {

}
