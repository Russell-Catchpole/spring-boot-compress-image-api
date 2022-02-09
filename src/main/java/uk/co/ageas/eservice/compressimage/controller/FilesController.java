package uk.co.ageas.eservice.compressimage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import uk.co.ageas.eservice.compressimage.compressor.Compressor;
import uk.co.ageas.eservice.compressimage.service.FilesStorageService;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@Controller
//@CrossOrigin("http://localhost:3000")
@CrossOrigin("http://server2:8027")  // CATCH#RSVR
public class FilesController {

    @Autowired
    FilesStorageService storageService;

    @PostMapping("/compress")
    public ResponseEntity<Resource> uploadFiles(@RequestParam("files") MultipartFile file)
            throws IOException {
        String message = "";
        AtomicReference<File> compressedImage = new AtomicReference<>(new File(" "));

        try {
            storageService.save(file);
            try {
//                File originalImage = new File("./uploads/" + file.getOriginalFilename());
                File originalImage = new File(file.getOriginalFilename());
                Compressor compressor = new Compressor();
                compressedImage.set(compressor.compressImage(originalImage));
                String fileName = compressedImage.get().getName();

                System.out.println("Image compressed!");

            } catch (IOException e) {
                e.printStackTrace();
            }

            Resource responseFile = storageService.load(compressedImage.get().getName());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + responseFile.getFilename() + "\"").body(responseFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.unprocessableEntity()
                .header(HttpHeaders.CONTENT_DISPOSITION).body(null);
    }
}
