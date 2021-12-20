package uz.pdp.kichikproekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.kichikproekt.entity.Supplier;
import uz.pdp.kichikproekt.payload.Result;
import uz.pdp.kichikproekt.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;
    @PostMapping
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    public Result addsupplier(@RequestBody Supplier supplier){
        Result result = supplierService.addSuppleirService(supplier);
        return result;
    }

    @GetMapping
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    public List<Supplier> getSupplierController(){
        return supplierService.getSupplierList();
    }

    @PutMapping("/{id}")
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    public Result editSupplierController(@PathVariable Integer id,@RequestBody Supplier supplier){
        return supplierService.editSupplierService(id,supplier);
    }

    @DeleteMapping
    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    public Result deleteSupplierController(@PathVariable Integer id){
        return supplierService.deleteSupplierService(id);
    }

}
