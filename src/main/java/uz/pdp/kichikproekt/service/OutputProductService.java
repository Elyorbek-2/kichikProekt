package uz.pdp.kichikproekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.kichikproekt.entity.Output;
import uz.pdp.kichikproekt.entity.OutputProduct;
import uz.pdp.kichikproekt.entity.Product;
import uz.pdp.kichikproekt.payload.OutputProductDto;
import uz.pdp.kichikproekt.payload.Result;
import uz.pdp.kichikproekt.repository.OutputProductRepository;
import uz.pdp.kichikproekt.repository.OutputRepository;
import uz.pdp.kichikproekt.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {
    @Autowired
    OutputProductRepository outputPoductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OutputRepository outputRepository;
    public Result addOutputProduct(OutputProductDto outputProductDto){
        OutputProduct outputProduct=new OutputProduct();
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("BUnday id li product mavjud emas",false);
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent())
            return new Result("BUnday id li output mavjud emas",false);
        outputProduct.setProduct(outputProduct.getProduct());
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setOutput(optionalOutput.get());
        outputPoductRepository.save(outputProduct);
        return new Result("Bajarildi",true);
    }
    public List<OutputProduct> getOutputProduct(){
        return outputPoductRepository.findAll();
    }
    public Result editOutputProduct(int id,OutputProductDto outputProductDto){
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("BUnday id li product mavjud emas",false);
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent())
            return new Result("BUnday id li output mavjud emas",false);
        Optional<OutputProduct> optionalOutputProduct = outputPoductRepository.findById(id);
        if (!optionalOutputProduct.isPresent())
            return new Result("BUnday id li outputProduct mavjud emas",false);
        OutputProduct outputProduct = optionalOutputProduct.get();
        outputProduct.setProduct(outputProduct.getProduct());
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setOutput(optionalOutput.get());
        outputPoductRepository.save(outputProduct);
        return new Result("Bajarildi",true);
    }
    public Result deleteOutputProduct(int id){
        try {
            outputPoductRepository.deleteById(id);
            return new Result("Bajarildi",true);
        } catch (Exception e) {
            return new Result("Bajarilmadi",false);
        }
    }
}
