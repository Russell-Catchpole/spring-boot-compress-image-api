package uk.net.catchpoler.image_compress.compressimage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import uk.net.catchpoler.image_compress.compressimage.service.FilesStorageService;

import javax.annotation.Resource;

@SpringBootApplication
//public class SpringBootCompressFileApplication implements CommandLineRunner {
  public class SpringBootCompressFileApplication extends SpringBootServletInitializer {
  @Resource
  FilesStorageService storageService;

  public static void main(String[] args) {
    SpringApplication.run(SpringBootCompressFileApplication.class, args);
  }

//  @Override
//  public void run(String... arg) throws Exception {
//    storageService.deleteAll();
//    storageService.init();
//  }
}
