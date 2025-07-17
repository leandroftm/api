package med.voll.api.service;

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
        Long id = new Doctor(data).getId();
        repository.save(new Doctor(data));
        return id;
    }


    public Page<DoctorListDTO> list(Pageable pageable){
        return repository.findAllByActiveTrue(pageable).
                map(DoctorListDTO::new);
    }

    @Transactional
    public void update(DoctorUpdateDTO data){
        var doctor = repository.getReferenceById(data.id());
        doctor.update(data);
    }

    @Transactional
    public void delete(Long id){
        var doctor = repository.getReferenceById(id);
        doctor.applyInactive();
    }
}
