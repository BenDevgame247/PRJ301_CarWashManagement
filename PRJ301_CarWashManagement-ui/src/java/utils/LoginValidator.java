
package utils;

import dto.LoginDTO;

public class LoginValidator {
    
    public String validate(LoginDTO loginDTO) {
        if (loginDTO == null) {
            return "Login data is required.";
        }
        
        if (isBlank(loginDTO.getEmail())) {
            return "Email is required.";
        }
        
        if (!loginDTO.getEmail().trim().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            return "Email format is invalid.";
        }
        
        if (isBlank(loginDTO.getPassword())) {
            return "Password is required.";
        }
        
        return null;
    }
    
    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
