package uz.pdp.kichikproekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.kichikproekt.entity.Client;
import uz.pdp.kichikproekt.payload.Result;
import uz.pdp.kichikproekt.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;
    public Result addClient(Client client){
        boolean existsByPhoneNumber = clientRepository.existsByPhoneNumber(client.getPhoneNumber());
        if (existsByPhoneNumber)
            return new Result("Bunday raqamli client mavjud",false);
        clientRepository.save(client);
        return new Result("Bajarildi",true);
    }
    public List<Client> getClient(){
        return clientRepository.findAll();
    }
    public Result editClient(int id,Client client){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent()){
            return new Result("bunday id li malumot topilmadi",false);
        }
        boolean existsByPhoneNumber = clientRepository.existsByPhoneNumber(client.getPhoneNumber());
        if (existsByPhoneNumber)
            return new Result("Bunday raqamli client mavjud",false);
        Client client1 = optionalClient.get();
        client1.setName(client.getName());
        client1.setPhoneNumber(client.getPhoneNumber());
        clientRepository.save(client1);
        return new Result("Bajarildi",true);
    }
    public Result deleteClient(int id){
        try {
            clientRepository.deleteById(id);
            return new Result("Bajarildi",true);
        } catch (Exception e) {
            return new Result("Bunday id li malumot topilmadi",false);
        }
    }
}
