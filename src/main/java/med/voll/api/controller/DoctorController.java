package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.doctor.DoctorUpdateDTO;
import med.voll.api.dto.doctor.DoctorListDTO;
import med.voll.api.dto.doctor.DoctorCreateDTO;
import med.voll.api.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
public class DoctorController {

    @Autowired
    private DoctorService service;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid DoctorCreateDTO data){
        Long id = service.create(data);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public Page<DoctorListDTO> list(@PageableDefault(size =10, sort = {"name"}) Pageable pageable){
        return service.list(pageable);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid DoctorUpdateDTO data){
       service.update(data);
       return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
       service.delete(id);
       return ResponseEntity.ok().build();
    }

}
