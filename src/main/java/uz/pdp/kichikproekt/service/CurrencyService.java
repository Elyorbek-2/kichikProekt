package uz.pdp.kichikproekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.kichikproekt.entity.Currency;
import uz.pdp.kichikproekt.payload.Result;
import uz.pdp.kichikproekt.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;


@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    public Result addCurrency(Currency currency){
        if (!currency.isActive())
            return new Result("valyuta turi tizim uchun faol emas",false);
        boolean existsByName = currencyRepository.existsByName(currency.getName());
        if (!existsByName)
            return new Result("Bunday nomli currency bor",false);
        currencyRepository.save(currency);
        return new Result("Bajarildi",true);
    }
    public List<Currency> getCurrencyList(){
        List<Currency> currencyList = currencyRepository.findAll();
        return currencyList;
    }
    public Result editCurrency(int id,Currency currency){
        if (!currency.isActive())
            return new Result("valyuta turi tizim uchun faol emas",false);
        Optional<Currency> currencyOptional = currencyRepository.findById(id);
        boolean existsByName = currencyRepository.existsByName(currency.getName());
        if (!currencyOptional.isPresent())
            return new Result("Bunday id li curency topilmadi",false);
        if (!existsByName)
            return new Result("Bunday nomli currency bor",false);
        currencyRepository.save(currency);
        return new Result("Bajarildi",true);
    }
    public Result deleteCurrency(int id){
        try {
            currencyRepository.deleteById(id);
            return new Result("Bajarildi",true);
        } catch (Exception e) {
            return new Result("Bajarilmadi",false);
        }
    }
}
