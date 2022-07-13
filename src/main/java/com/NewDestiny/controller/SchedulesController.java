package com.NewDestiny.controller;

import com.NewDestiny.exceptions.ResourceNotFoundException;
import com.NewDestiny.model.dto.ProductDto;
import com.NewDestiny.model.dto.ScheduleDto;
import com.NewDestiny.service.implementation.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SchedulesController {
    @Autowired
    private ScheduleService schedulesService;

    @PostMapping("/save")
    public ResponseEntity<String> saveSchedule(@RequestBody ScheduleDto scheduleDto) {
        schedulesService.save(scheduleDto);
        return ResponseEntity.ok(" La reserva se agrego con éxito");
    }

    @GetMapping("/list")
    public List<ScheduleDto> listSchedules() {
        return schedulesService.findAll();
    }

    @GetMapping("list/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        ScheduleDto scheduleResponse = schedulesService.findById(id);
        if(scheduleResponse!=null){
            return ResponseEntity.ok(scheduleResponse);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la reserva con el id: " + id + " en la base de datos. Por favor ingrese un id válido");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) throws ResourceNotFoundException {
        schedulesService.deleteById(id);
        return ResponseEntity.ok("Se elimino la reserva correctamente");
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody ScheduleDto scheduleDto) {
        scheduleDto.setId(id);
        schedulesService.update(scheduleDto);
        return ResponseEntity.ok("Se actualizo la reserva correctamente");
    }

    @GetMapping("/filter/user/{userId}")
    public List<ScheduleDto> listSchedulesByUserId(@PathVariable Long userId) {
        return schedulesService.findAllByUserId(userId);
    }

    @GetMapping("/filter/product/{productId}")
    public List<ScheduleDto> listSchedulesByProductId(@PathVariable Long productId) {
        return schedulesService.findAllByProductId(productId);
    }

}
