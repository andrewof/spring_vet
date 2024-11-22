package com.andres.veterinaria.models.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AdminRequest {

    @Email(message = "Introduce un email válido")
    private String email;

    @Size(min = 8, message = "La contraseña debe contener 8 carácteres mínimo")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$",
            message = "La contraseña debe contener al menos una letra mayúscula, una letra minúscula, un número y un carácter especial.")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
