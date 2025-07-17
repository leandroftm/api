package med.voll.api.dto.Address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDTO(
        @NotBlank
        String address,
        @NotBlank
        String neighborhood,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,
        @NotBlank
        String city,
        @NotBlank
        String fu,
        String complement,
        String number
    ) {

}
