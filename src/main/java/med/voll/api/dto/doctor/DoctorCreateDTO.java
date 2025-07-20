package med.voll.api.dto.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.dto.Address.AddressDTO;
import med.voll.api.enums.MedicalSpeciality;

public record DoctorCreateDTO(

        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telephone,
        @NotBlank
        @Pattern(regexp ="\\d{4,6}")
        String crm,
        @NotNull
        MedicalSpeciality speciality,
        @NotNull
        @Valid
        AddressDTO address,
        @NotNull
        Boolean active) {
}
