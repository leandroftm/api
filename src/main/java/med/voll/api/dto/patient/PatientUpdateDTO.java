package med.voll.api.dto.patient;

import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.Address.AddressDTO;

public record PatientUpdateDTO(
        String name,
        String telephone,
        AddressDTO address,
        Boolean active

) {

}
