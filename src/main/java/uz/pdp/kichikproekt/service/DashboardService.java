package uz.pdp.kichikproekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.kichikproekt.entity.InputProduct;
import uz.pdp.kichikproekt.entity.OutputProduct;
import uz.pdp.kichikproekt.payload.Result;
import uz.pdp.kichikproekt.repository.InputProductRepository;
import uz.pdp.kichikproekt.repository.InputRepository;
import uz.pdp.kichikproekt.repository.OutputProductRepository;
import uz.pdp.kichikproekt.repository.ProductRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.ListIterator;


@Service
public class DashboardService {
    @Autowired
    InputProductRepository inputProductRepository;
    ProductRepository productRepository;
    InputRepository inputRepository;
    OutputProductRepository outputProductRepository;
    public List<InputProduct> getPriceOrder(Timestamp timestamp){
        return inputProductRepository.findAllByInputId_KunOrderByPrice(timestamp);
    }
    public Result getGeneralPrice(Timestamp timestamp){
        Double generalPrice=0.00;
        List<InputProduct> inputProductList = inputProductRepository.findAllByInputId_KunOrderByPrice(timestamp);
        for (InputProduct inputProduct : inputProductList) {
            double price = inputProduct.getPrice();
            generalPrice += price;
        }
        return new Result("Umumiy summasi",true,generalPrice);
    }
    public List<OutputProduct> getOutputProductDate(Timestamp timestamp){
        return outputProductRepository.findAllByOutput_Kun(timestamp);
    }
//    public List<InputProduct> getExpireDate(Timestamp timestamp){
//        List<InputProduct> inputProductList = inputProductRepository.findAllExpireDate();
//
//    }

}
