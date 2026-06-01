package utils;

import dto.RegisterDTO;

public class RegisterValidator {

    public String validate(RegisterDTO registerDTO) {
        if (registerDTO == null) {
            return "Register data is required.";
        }

        if (isBlank(registerDTO.getFullName())) {
            return "Full name is required.";
        }

        if (isBlank(registerDTO.getEmail())) {
            return "Email is required.";
        }

        if (!registerDTO.getEmail().trim().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            return "Email format is invalid.";
        }

        if (isBlank(registerDTO.getPassword())) {
            return "Password is required.";
        }

        if (registerDTO.getPassword().trim().length() < 6) {
            return "Password must be at least 6 characters.";
        }

        if (isBlank(registerDTO.getPhone())) {
            return "Phone is required.";
        }

        if (!registerDTO.getPhone().trim().matches("\\d{9,11}")) {
            return "Phone must contain 9 to 11 digits.";
        }
        return null;
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
