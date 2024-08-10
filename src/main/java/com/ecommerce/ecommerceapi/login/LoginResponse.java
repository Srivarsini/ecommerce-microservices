package com.ecommerce.ecommerceapi.login;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LoginResponse {
    private String token;
    private String userName;
}
