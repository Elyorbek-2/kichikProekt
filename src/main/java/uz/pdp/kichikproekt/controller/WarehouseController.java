package uz.pdp.kichikproekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.kichikproekt.entity.Warehouse;
import uz.pdp.kichikproekt.payload.Result;
import uz.pdp.kichikproekt.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/werehouse")
public class WarehouseController {
    @Autowired
    WarehouseService warehouseService;

    @PostMapping
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    public Result addWarehouse(@RequestBody Warehouse warehouse){
        return warehouseService.addWarehouse(warehouse);
    }

    @GetMapping
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    public List<Warehouse> getWarehouse(){
        List<Warehouse> warehouseService = this.warehouseService.getWarehouseService();
        return warehouseService;
    }

    @PutMapping
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    public Result editWarehouse(@RequestBody Warehouse warehouse,@PathVariable Integer id){
        return warehouseService.editWarehouse(id,warehouse);
    }
    @DeleteMapping
    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    public Result deleteWarehouse(@PathVariable Integer id){
        return warehouseService.deleteWarehouse(id);
    }
}
