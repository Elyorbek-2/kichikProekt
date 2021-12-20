package uz.pdp.kichikproekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.kichikproekt.entity.Supplier;
import uz.pdp.kichikproekt.payload.Result;
import uz.pdp.kichikproekt.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public Result addSuppleirService(Supplier supplier){
        boolean existsByName = supplierRepository.existsByName(supplier.getName());
        if (existsByName)
            return new Result("bunday supplier bor",false);
        supplierRepository.save(supplier);
        return new Result("Bajarildi",true);
    }
    public List<Supplier> getSupplierList(){
        List<Supplier> supplierList = supplierRepository.findAll();
        return supplierList;
    }
    public Result editSupplierService(int id,Supplier supplier){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent())
            return new Result("BUnday id li supplier topilmadi",false);
        supplierRepository.save(supplier);
        return new Result("Bajarildi",true);
    }
    public Result deleteSupplierService(int id){
        try {
            supplierRepository.deleteById(id);
            return new Result("Supplier o'chirildi",true);
        } catch (Exception e) {
            return new Result("Bunday id li malumot topilmadi",false);
        }
    }
}
