package com.example.byte_bank.form;

import lombok.*;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@Getter
@Setter
public class UserRegistrationForm {
    @NonNull
    String userLogin;

    @NonNull
    String userPassword;

    @Nullable
    int bytesBalance;
}
