package uz.pdp.kichikproekt.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.kichikproekt.entity.Warehouse;
import uz.pdp.kichikproekt.payload.Result;
import uz.pdp.kichikproekt.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;

    public List<Warehouse> getWarehouseService(){
        return warehouseRepository.findAll();
    }

    public Result addWarehouse(Warehouse warehouse){
        if (!warehouse.isActive())
            return new Result("Ombor activ emas",false);
        warehouseRepository.save(warehouse);
        return new Result("Muaffaqiyatli bajarildi",true);
    }
    public Result editWarehouse(int id,Warehouse warehouse){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent()){
            return new Result("Bunday id li malumot mavjud emas",false);
        }
        Warehouse warehouse1 = optionalWarehouse.get();
        warehouse1.setName(warehouse.getName());
        warehouseRepository.save(warehouse1);
        return new Result("Bajarildi",true);
    }
    public Result deleteWarehouse(int id){
        try {
            warehouseRepository.deleteById(id);
            return new Result("Bajarildi",true);
        } catch (Exception e) {
            return new Result("Bunday id li ma'lumot topilmadi",false);
        }
    }
}
