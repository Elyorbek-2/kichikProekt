package uz.pdp.kichikproekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.kichikproekt.entity.Currency;
import uz.pdp.kichikproekt.entity.Input;
import uz.pdp.kichikproekt.entity.Supplier;
import uz.pdp.kichikproekt.entity.Warehouse;
import uz.pdp.kichikproekt.payload.InputDto;
import uz.pdp.kichikproekt.payload.Result;
import uz.pdp.kichikproekt.repository.CurrencyRepository;
import uz.pdp.kichikproekt.repository.InputRepository;
import uz.pdp.kichikproekt.repository.SupplierRepository;
import uz.pdp.kichikproekt.repository.WarehouseRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InputService {
    @Autowired
    InputRepository inputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    public Result addInput(InputDto inputDto){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent()){
            return new Result("bunday id li warehouse topilmadi",false);
        }
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent())
            return new Result("bunday id li supplier topilmadi",false);
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("bunday id li currency topilmadi",false);
        Input input=new Input();
        input.setKun(inputDto.getTimestamp());
        input.setCurrency(optionalCurrency.get());
        input.setSupplier(optionalSupplier.get());
        input.setWarehouse(optionalWarehouse.get());
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setCode(input.getId());
        inputRepository.save(input);
        return new Result("Bajarildi",true);
    }
    public List<Input> getInputs(){
        return inputRepository.findAll();
    }
    public Result editInput(int id,InputDto inputDto){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent())
            return new Result("Bunday id li ma'lumot yo'q",false);
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("bunday id li warehouse topilmadi",false);

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent())
            return new Result("bunday id li supplier topilmadi",false);
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("bunday id li currency topilmadi",false);
        Input input = optionalInput.get();
        input.setKun(inputDto.getTimestamp());
        input.setCurrency(optionalCurrency.get());
        input.setSupplier(optionalSupplier.get());
        input.setWarehouse(optionalWarehouse.get());
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setCode(input.getId());
        inputRepository.save(input);
        return new Result("Bajarildi",true);
    }
    public Result deleteInput(int id){
        try {
            inputRepository.deleteById(id);
            return new Result("Bajarildi",true);
        } catch (Exception e) {
            return new Result("Barilmadi",false);
        }
    }
}
