package uz.pdp.kichikproekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.kichikproekt.entity.Attachment;
import uz.pdp.kichikproekt.payload.Result;
import uz.pdp.kichikproekt.service.AttachmentService;

import java.util.List;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;


    @PostMapping("/upload")
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    public Result addAttachmentController(MultipartHttpServletRequest request) {
        return attachmentService.addAttachmentService(request);
    }

    @GetMapping("/getAttachment")
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    public List<Attachment> getAttachment(){
        return attachmentService.getAttachment();
    }


    @PutMapping
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    public Result editAttachment(@PathVariable Integer id,MultipartHttpServletRequest request){
        return attachmentService.editAttachment(id,request);
    }


    @DeleteMapping("/delete/id")
    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    public Result deleteAttachment(@PathVariable Integer id){
        return attachmentService.deleteAttachment(id);
    }


}
