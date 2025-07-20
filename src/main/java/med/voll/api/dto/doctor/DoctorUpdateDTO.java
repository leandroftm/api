package med.voll.api.dto.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.Address.AddressDTO;

public record DoctorUpdateDTO(
        String name,
        String telephone,
        AddressDTO address,
        Boolean active) {
}
