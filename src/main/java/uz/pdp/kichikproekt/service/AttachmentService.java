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
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @SneakyThrows
    public Result addAttachmentService(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        Attachment attachment=new Attachment();
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        attachment.setFileOriginalName(file.getOriginalFilename());
        attachmentRepository.save(attachment);
        AttachmentContent attachmentContent=new AttachmentContent();
        attachmentContent.setAttachment(attachment);
        attachmentContent.setBytes(file.getBytes());
        attachmentContentRepository.save(attachmentContent);
        return new Result("Bajarildi",true,attachment.getId());
    }

    public List<Attachment> getAttachment() {
        return attachmentRepository.findAll();
    }

    public Result editAttachment(int id, MultipartHttpServletRequest request) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent())
            return new Result("Bunday id li malumot topilmadi", false);
        Iterator<String> fileNames = request.getFileNames();
        while (fileNames.hasNext()) {
            MultipartFile file = request.getFile(fileNames.next());
            if (file == null)
                return new Result("Fayl yuklanmadi", false);
            Attachment attachment = optionalAttachment.get();
            attachment.setFileOriginalName(file.getOriginalFilename());
            attachment.setContentType(file.getContentType());
            attachment.setSize(file.getSize());
            attachmentRepository.save(attachment);
        }
        return new Result("Bajarildi", true);
    }
    public Result deleteAttachment(int id){
        try {
            attachmentRepository.deleteById(id);
            return new Result("Bajarildi",true);
        } catch (Exception e) {
            return new Result("Bajarilmadi",false);
        }
    }
}
