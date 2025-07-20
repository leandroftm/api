package med.voll.api.service;

import jakarta.persistence.EntityNotFoundException;
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
        if(repository.existsByCpf(data.cpf()))
            throw new RuntimeException("CPF já cadastrado.");

        Patient patient = new Patient(data);
        repository.save(patient);
        return patient.getId();
    }

    @Transactional(readOnly = true)
    public Page<PatientListDTO> list(Pageable pageable){
        return repository.findAllByActiveTrue(pageable).
                map(PatientListDTO::new);
    }

    @Transactional
    public void update(Long id, PatientUpdateDTO data){
        if(data.name() == null && data.telephone() == null && data.address() == null && data.active() == null)
            throw new IllegalArgumentException("Pelo menos um dos capos deve ser informado para atualização.");


        Patient patient = repository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Paciente com id " + id + "não encontrado."));
        patient.update(data);
    }

    @Transactional
    public void deactivate(Long id){
        Patient patient = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente com id " + id + "não encontrado"));
        patient.deactivate();
    }
}
