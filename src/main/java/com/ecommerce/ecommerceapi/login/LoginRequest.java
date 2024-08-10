package com.ecommerce.ecommerceapi.login;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LoginRequest {
    private String username;
    private String password;
}
