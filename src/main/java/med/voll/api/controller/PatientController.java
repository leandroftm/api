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
    public ResponseEntity<Page<PatientListDTO>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        Page<PatientListDTO> page = service.list(pageable);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid PatientUpdateDTO data){
        service.update(id, data);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deactivate(id);
        return ResponseEntity.noContent().build();
    }

}
