package uz.pdp.kichikproekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.kichikproekt.entity.Input;
import uz.pdp.kichikproekt.entity.InputProduct;
import uz.pdp.kichikproekt.entity.Product;
import uz.pdp.kichikproekt.payload.InputDto;
import uz.pdp.kichikproekt.payload.InputProductDto;
import uz.pdp.kichikproekt.payload.Result;
import uz.pdp.kichikproekt.repository.InputProductRepository;
import uz.pdp.kichikproekt.repository.InputRepository;
import uz.pdp.kichikproekt.repository.ProductRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {
    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InputRepository inputRepository;
    public Result addInputProduct(InputProductDto inputProductDto){
        InputProduct inputProduct=new InputProduct();
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent()) {
            return new Result("Bunday id li product topilamdi",false);
        }
        Optional<Input> inputOptional = inputRepository.findById(inputProductDto.getInputId());
        if (!inputOptional.isPresent())
            return new Result("Bunday id li input topilamdi",false);
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setInputId(inputOptional.get());
        inputProductRepository.save(inputProduct);
        return new Result("Bajarildi",true);
    }
    public List<InputProduct> getInputProduct(){
        return inputProductRepository.findAll();
    }
    public Result editInputProduct(int id,InputProductDto inputProductDto){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent()) {
            return new Result("Bunday id li input product topilmadi",false);
        }
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent()) {
            return new Result("Bunday id li product topilamdi",false);
        }
        Optional<Input> inputOptional = inputRepository.findById(inputProductDto.getInputId());
        if (!inputOptional.isPresent())
            return new Result("Bunday id li input topilamdi",false);
        InputProduct inputProduct = optionalInputProduct.get();
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setInputId(inputOptional.get());
        inputProductRepository.save(inputProduct);
        return new Result("Bajarildi",true);
    }
    public Result deleteInputProduct(int id){
        try {
            inputProductRepository.deleteById(id);
            return new Result("Bajarildi",true);
        } catch (Exception e) {
            return new Result("Bajarilmadi",false);
        }
    }
}
