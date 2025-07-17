package med.voll.api.dto.patient;

import med.voll.api.entity.Patient;

public record PatientListDTO(Long id, String name, String email, String cpf) {

    public PatientListDTO(Patient patient){
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
