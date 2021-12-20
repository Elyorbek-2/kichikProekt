package uz.pdp.kichikproekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.kichikproekt.entity.Users;
import uz.pdp.kichikproekt.payload.Result;
import uz.pdp.kichikproekt.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public Result addUser(Users users){
        if (!users.isActive())
            return new Result("Ishchi activ emas",false);
        Users users1=new Users();
        users1.setFirstName(users.getFirstName());
        users1.setLastName(users1.getLastName());
        users1.setPassword(users.getPassword());
        users1.setPhoneNumber(users.getPhoneNumber());
        users1.setCode(users1.getId()+1);
        users1.setWarehouse(users.getWarehouse());
        userRepository.save(users1);
        return new Result("Bajarildi",true);
    }
    public Set<Users> getUsersService(){
        List<Users> usersList = userRepository.findAll();
        Set<Users> setUsers=new HashSet<>();
        setUsers.addAll(usersList);
        return setUsers;
    }
    public Result editUsers(int id,Users users){
        if (!users.isActive())
            return new Result("Ishchi activ emas",false);
        Optional<Users> optionalUsers = userRepository.findById(id);
        if (!optionalUsers.isPresent()){
            return new Result("Bunday id li malumot topilmadi",false);
        }
        Users users1 = optionalUsers.get();
        users1.setFirstName(users.getFirstName());
        users1.setLastName(users1.getLastName());
        users1.setPassword(users.getPassword());
        users1.setPhoneNumber(users.getPhoneNumber());
        users1.setCode(users1.getId()+1);
        users1.setWarehouse(users.getWarehouse());
        userRepository.save(users1);
        return new Result("Bajarildi",true);
    }
    public Result deleteUsers(int id){
        try {
            userRepository.deleteById(id);
            return new Result("Bajarildi",true);
        } catch (Exception e) {
            return new Result("Bunday id li ma'lumot topilmadi",false);
        }
    }

}
