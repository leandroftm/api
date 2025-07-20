package med.voll.api.service;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.dto.doctor.DoctorCreateDTO;
import med.voll.api.dto.doctor.DoctorUpdateDTO;
import med.voll.api.entity.Doctor;
import med.voll.api.dto.doctor.DoctorListDTO;
import med.voll.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repository;

    @Transactional
    public Long create(DoctorCreateDTO data){
        if(repository.existsByCrm(data.crm()))
            throw new RuntimeException("CRM já cadastrado.");

        Doctor doctor = new Doctor(data);
        repository.save(doctor);
        return doctor.getId();
    }

    @Transactional(readOnly = true)
    public Page<DoctorListDTO> list(Pageable pageable){
        return repository.findAllByActiveTrue(pageable)
                .map(DoctorListDTO::new);
    }

    @Transactional
    public void update(Long id, DoctorUpdateDTO data){
        if(data.name() == null && data.telephone() == null && data.address() == null && data.active() == null){

            throw new IllegalArgumentException("Pelo menos um campo deve ser informado para atualização.");

        }
            Doctor doctor = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Médico com ID " + id + " não encontrado."));

            doctor.update(data);

    }

    @Transactional
    public void deactivate(Long id){
        Doctor doctor = repository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Médico com ID " + id + " não encontrado."));
        doctor.deactivate();
    }
}
