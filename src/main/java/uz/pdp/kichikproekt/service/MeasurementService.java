package uz.pdp.kichikproekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.kichikproekt.entity.Measurement;
import uz.pdp.kichikproekt.payload.Result;
import uz.pdp.kichikproekt.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;


@Service
public class MeasurementService {
    @Autowired
    MeasurementRepository measurementRepository;


    public Result addMeasurementSerice(Measurement measurement){
        boolean existsByName = measurementRepository.existsByName(measurement.getName());
        if (!existsByName)
            return new Result("Bunday o'lchov birligi mavjud",false);
        if (!measurement.isActive())
            return new Result("O'lchov tizim uchun faol emas",false);
        Measurement measuremenSave = measurementRepository.save(measurement);
        return new Result("Muaffaqiyatli yakunlandi",true);
    }

    public List<Measurement> getMeasurementService(){
        return measurementRepository.findAll();
    }

    public Result editMeasurementService(Integer id,Measurement measurement){
        if (!measurement.isActive())
            return new Result("O'lchov tizim uchun faol emas",false);
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent())
            return new Result("Bunday id li measurement topilmadi",false);
        Measurement measurement1 = optionalMeasurement.get();
        measurement1.setName(measurement.getName());
        measurementRepository.save(measurement1);
        return new Result("Bajarildi",true);
    }
    public Result deleteMeasurementService(Integer id){
        try {
            measurementRepository.deleteById(id);
            return new Result("Bajarildi",true);
        } catch (Exception e) {
            return new Result("Xatolik sababli bunday measurement o'chirilmadi",false);
        }
    }
}
