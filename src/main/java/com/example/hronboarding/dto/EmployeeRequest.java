package com.example.hronboarding.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record EmployeeRequest(
        @NotBlank @Size(min = 3, max = 100) String fullName,
        @NotBlank @Email String email,
        @NotBlank String position,
        @NotNull LocalDate startDate
) {}
