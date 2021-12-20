package uz.pdp.kichikproekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.kichikproekt.entity.Attachment;
import uz.pdp.kichikproekt.entity.Category;
import uz.pdp.kichikproekt.entity.Measurement;
import uz.pdp.kichikproekt.entity.Product;
import uz.pdp.kichikproekt.payload.ProductDto;
import uz.pdp.kichikproekt.payload.Result;
import uz.pdp.kichikproekt.repository.AttachmentRepository;
import uz.pdp.kichikproekt.repository.CategoryRepository;
import uz.pdp.kichikproekt.repository.MeasurementRepository;
import uz.pdp.kichikproekt.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    MeasurementRepository measurementRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    CategoryRepository categoryRepository;
    public Result addProduct(ProductDto productDto){
        boolean nameAndCategory_id = productRepository.existsByNameAndCategory_Id(productDto.getName(), productDto.getCategoryId());
        if (nameAndCategory_id)
            return new Result("Bunday mahsulot ushbu kategoriyada mavjud",false);
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new Result("bunday id li measurement yo'q",false);
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getAttachmentId());
        if (!optionalAttachment.isPresent())
            return new Result("bunday id li attachment yo'q",false);
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("Bunday kategoriya mavjud emas",false);
        Product product=new Product();
        product.setName(productDto.getName());
        product.setCode(product.getId()+1);
        product.setCategory(optionalCategory.get());
        product.setAttachment(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        productRepository.save(product);
        return new Result("Bajarildi",true);
    }
    public List<Product> getProduct(){
        return productRepository.findAll();
    }
    public Result editProduct(int id,ProductDto productDto){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new Result("Bunday id li product topilmadi",false);
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("Bunday id li category topilmadi",false);
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getAttachmentId());
        if (!optionalAttachment.isPresent())
            return new Result("Bunday id li attachment topilmadi",false);
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new Result("Bunday id li measurement topilmadi",false);
        Product product = optionalProduct.get();
        product.setName(productDto.getName());
        product.setAttachment(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setCategory(optionalCategory.get());
        product.setCode(product.getId()+1);
        productRepository.save(product);
        return new Result("Bajarildi",true);
    }
    public Result deleteProduct(int id){
        try {
            productRepository.deleteById(id);
            return new Result("Bajarildi",true);
        } catch (Exception e) {
            return new Result("Bajarildilmadi",false);
        }
    }
}
