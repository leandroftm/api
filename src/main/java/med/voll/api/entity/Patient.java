package med.voll.api.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.patient.PatientUpdateDTO;
import med.voll.api.dto.patient.PatientCreateDTO;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
@Table(name= "patients")
@Entity(name= "Patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String telephone;
    @Column(nullable = false, unique = true)
    private String cpf;

    @Embedded
    private Address address;

    private Boolean active;

    public Patient(PatientCreateDTO data){
        this.name = data.name();
        this.email = data.email();
        this.telephone = data.telephone();
        this.cpf = data.cpf();
        this.address = new Address(data.address());
        this.active = data.active();
    }


    public void update(@Valid PatientUpdateDTO data) {
        if(data.name() != null)
            this.name = data.name();
        if(data.telephone() != null)
            this.telephone = data.telephone();
        if(data.address() != null)
            this.address.updateAddress(data.address());
        if(data.active() != null)
            this.active = data.active();
    }

    public void deactivate(){
        this.active = false;
    }
}
