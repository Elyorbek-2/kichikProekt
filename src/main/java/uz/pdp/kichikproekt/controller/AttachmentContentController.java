package uz.pdp.kichikproekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.kichikproekt.entity.AttachmentContent;
import uz.pdp.kichikproekt.payload.Result;
import uz.pdp.kichikproekt.service.AttachmentContentService;

import java.util.List;

@RestController
@RequestMapping("/attachmentContent")
public class AttachmentContentController {
//    @Autowired
//    AttachmentContentService attachmentContentService;
//    @PostMapping("/upload")
//    public Result uploadFile(MultipartHttpServletRequest request){
//        return attachmentContentService.uploadFile(request);
//    }
//    @GetMapping
//    public List<AttachmentContent> getAttachmentContent(){
//        return attachmentContentService.getAttachmentContent();
//    }
//    @PutMapping
//    public Result editAttachmentContent(@PathVariable Integer id,MultipartHttpServletRequest request){
//        return attachmentContentService.editAttachmentContent(id,request);
//    }
//    @DeleteMapping
//    public Result deleteAttachmentContent(@PathVariable Integer id){
//        return attachmentContentService.deleteAttachmentContent(id);
//    }
}
