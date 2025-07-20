package med.voll.api.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.doctor.DoctorCreateDTO;
import med.voll.api.dto.doctor.DoctorUpdateDTO;
import med.voll.api.enums.MedicalSpeciality;

@Table(name= "doctors")
@Entity(name= "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Doctor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String telephone;

    @Column(nullable = false, unique = true)
    private String crm;

    @Enumerated(EnumType.STRING)
    private MedicalSpeciality speciality;

    @Embedded
    private Address address;

    private Boolean active;

    public Doctor(DoctorCreateDTO doctor) {
        this.active = true;
        this.name = doctor.name();
        this.email = doctor.email();
        this.crm = doctor.crm();
        this.telephone = doctor.telephone();
        this.speciality = doctor.speciality();
        this.address = new Address(doctor.address());
    }

    public void update(DoctorUpdateDTO doctor) {
       if(doctor.name() != null)
        this.name = doctor.name();
       if(doctor.telephone() != null)
        this.telephone = doctor.telephone();
       if(doctor.address() != null)
        this.address.updateAddress(doctor.address());
       if(doctor.active() != null)
           this.active = doctor.active();
    }

    public void deactivate() {
        this.active = false;
    }
}
