package com.docp.user_service.dto;


public record UserRequest(
        String username,
        String password,
        String email,
        String firstName,
        String lastName,
        AddressDto address) {
}
