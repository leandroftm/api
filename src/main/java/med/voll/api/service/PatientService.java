package med.voll.api.service;

import med.voll.api.dto.patient.PatientCreateDTO;
import med.voll.api.dto.patient.PatientListDTO;
import med.voll.api.dto.patient.PatientUpdateDTO;
import med.voll.api.entity.Patient;
import med.voll.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repository;

    @Transactional
    public Long create(PatientCreateDTO data){
        return repository.save(new Patient(data)).getId();
    }

    public Page<PatientListDTO> list(Pageable pageable){
        return repository.findAllByActiveTrue(pageable).
                map(PatientListDTO::new);
    }

    @Transactional
    public void update(PatientUpdateDTO data){
        var doctor = repository.getReferenceById(data.id());
        doctor.update(data);
    }

    @Transactional
    public void delete(Long id){
        var doctor = repository.getReferenceById(id);
        doctor.applyInactive();
    }
}
