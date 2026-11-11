package com.apnishadi.auth;

import com.apnishadi.profile.Profile;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SignupRequest(
		@Email @NotBlank String email,
		@NotBlank String phone,
		@NotBlank @Size(min = 8) String password,
		@NotNull @Valid Profile profile
) {
}
