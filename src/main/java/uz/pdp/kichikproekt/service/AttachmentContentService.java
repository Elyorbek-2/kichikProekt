package uz.pdp.kichikproekt.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.kichikproekt.entity.Attachment;
import uz.pdp.kichikproekt.entity.AttachmentContent;
import uz.pdp.kichikproekt.payload.Result;
import uz.pdp.kichikproekt.repository.AttachmentContentRepository;
import uz.pdp.kichikproekt.repository.AttachmentRepository;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentContentService {
    @Autowired
    AttachmentContentRepository attachmentContentRepository;
    @Autowired
    AttachmentRepository attachmentRepository;

    @SneakyThrows
    public Result uploadFile(MultipartHttpServletRequest request){
        Iterator<String> fileNames = request.getFileNames();
        while (fileNames.hasNext()){
            MultipartFile file = request.getFile(fileNames.next());
            Attachment attachment=new Attachment();
            assert file != null;
            attachment.setFileOriginalName(file.getOriginalFilename());
            attachment.setSize(file.getSize());
            attachment.setContentType(file.getContentType());
            Attachment save = attachmentRepository.save(attachment);
            AttachmentContent attachmentContent=new AttachmentContent();
            attachmentContent.setBytes(file.getBytes());
            attachmentContent.setAttachment(save);
            attachmentContentRepository.save(attachmentContent);
            return new Result("Bajarildi",true,save.getId());
        }
        return new Result("Xatolik",false);
    }


    public List<AttachmentContent> getAttachmentContent(){
        return attachmentContentRepository.findAll();
    }

    @SneakyThrows
    public Result editAttachmentContent(int id, MultipartHttpServletRequest request){
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent())
            return new Result("Bunday id li malumot topilmadi",false);
        Attachment attachment = optionalAttachment.get();
        Optional<AttachmentContent> attachmentContentOptional = attachmentContentRepository.findById(attachment.getId());
        if (!attachmentContentOptional.isPresent())
            return new Result("Bunday id li malumot topilmadi",false);
        Iterator<String> fileNames = request.getFileNames();
        while (fileNames.hasNext()){
            MultipartFile file = request.getFile(fileNames.next());
            attachment.setSize(file.getSize());
            attachment.setContentType(file.getContentType());
            attachment.setFileOriginalName(file.getOriginalFilename());
            attachmentRepository.save(attachment);
            AttachmentContent attachmentContent = attachmentContentOptional.get();
            attachmentContent.setBytes(file.getBytes());
            attachmentContent.setAttachment(attachment);
            attachmentContentRepository.save(attachmentContent);
            return new Result("Bajarildi",true,attachment.getId());
        }
        return new Result("Xatolik yuzaga keldi",false);

    }
    public Result deleteAttachmentContent(int id){
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        Attachment attachment = optionalAttachment.get();
        Integer id1 = attachment.getId();
        try {
            attachmentRepository.deleteById(id);
            attachmentContentRepository.deleteById(id1);
            return new Result("Bajarildi",true);
        } catch (Exception e) {
            return new Result("Xatolik yuz berdi",false);
        }
    }
}
