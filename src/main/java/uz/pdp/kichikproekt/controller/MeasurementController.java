package uz.pdp.kichikproekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.kichikproekt.entity.Measurement;
import uz.pdp.kichikproekt.payload.Result;
import uz.pdp.kichikproekt.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    @Autowired
    MeasurementService measurementService;

    @PostMapping
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    public Result addMeasurementController(@RequestBody Measurement measurement){
        return measurementService.addMeasurementSerice(measurement);
    }

    @GetMapping
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    public List<Measurement> getMeasurementController(){
        return this.measurementService.getMeasurementService();
    }

    @PutMapping("/{id}")
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    public Result editMeasurementController(@PathVariable Integer id,Measurement measurement){
        return measurementService.editMeasurementService(id, measurement);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    public Result deleteMeasurementController(@PathVariable Integer id){
        return measurementService.deleteMeasurementService(id);
    }
}
