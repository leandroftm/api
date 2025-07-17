package med.voll.api.dto.doctor;

import med.voll.api.entity.Doctor;
import med.voll.api.enums.MedicalSpeciality;

public record DoctorListDTO(Long id, String name, String email, String crm, MedicalSpeciality speciality) {

    public DoctorListDTO(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpeciality());
    }
}
