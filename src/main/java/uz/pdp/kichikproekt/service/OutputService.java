package uz.pdp.kichikproekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.kichikproekt.entity.Client;
import uz.pdp.kichikproekt.entity.Currency;
import uz.pdp.kichikproekt.entity.Output;
import uz.pdp.kichikproekt.entity.Warehouse;
import uz.pdp.kichikproekt.payload.OutputDto;
import uz.pdp.kichikproekt.payload.Result;
import uz.pdp.kichikproekt.repository.ClientRepository;
import uz.pdp.kichikproekt.repository.CurrencyRepository;
import uz.pdp.kichikproekt.repository.OutputRepository;
import uz.pdp.kichikproekt.repository.WarehouseRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class OutputService {
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    public Result addOutput(OutputDto outputDto){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Bunday id li warehouse topilmadi",false);
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent())
            return new Result("Bunday id li client topilmadi",false);
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Bunday id li currency topilmadi",false);
        Output output=new Output();
        Timestamp timestamp=new Timestamp(new Date().getTime());
        output.setKun(timestamp);
        output.setCurrency(optionalCurrency.get());
        output.setClient(optionalClient.get());
        output.setWarehouse(optionalWarehouse.get());
        output.setCode(output.getId());
        output.setFactureNumber(outputDto.getFactureNumber());
        outputRepository.save(output);
        return new Result("Bajarildi",true);
    }
    public List<Output> getOutput(){
        return outputRepository.findAll();
    }
    public Result editOutput(int id,OutputDto outputDto){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Bunday id li warehouse topilmadi",false);
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent())
            return new Result("Bunday id li client topilmadi",false);
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Bunday id li currency topilmadi",false);
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent())
            return new Result("BUnday id li output mavjud emas",false);
        Output output = optionalOutput.get();
        Timestamp timestamp=new Timestamp(new Date().getTime());
        output.setKun(timestamp);
        output.setCurrency(optionalCurrency.get());
        output.setClient(optionalClient.get());
        output.setWarehouse(optionalWarehouse.get());
        output.setCode(output.getId());
        output.setFactureNumber(outputDto.getFactureNumber());
        outputRepository.save(output);
        return new Result("Bajarildi",true);
    }
    public Result deleteOutput(int id){
        try {
            outputRepository.deleteById(id);
            return new Result("bajarildi",true);
        } catch (Exception e) {
            return new Result("Bajarilmadi",false);
        }
    }
}
