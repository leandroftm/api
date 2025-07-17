package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.patient.PatientUpdateDTO;
import med.voll.api.dto.patient.PatientListDTO;
import med.voll.api.dto.patient.PatientCreateDTO;
import med.voll.api.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pacientes")
public class PatientController {

    @Autowired
    private PatientService service;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid PatientCreateDTO data){
        Long id = service.create(data);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public Page<PatientListDTO> findAllActive(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        return service.list(pageable);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid PatientUpdateDTO data){
        service.update(data);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
