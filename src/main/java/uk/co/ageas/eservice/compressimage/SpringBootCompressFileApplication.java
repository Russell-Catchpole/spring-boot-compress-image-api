package uk.co.ageas.eservice.compressimage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import uk.co.ageas.eservice.compressimage.service.FilesStorageService;

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
  public void run(String... arg) throws Exception {
    storageService.deleteAll();
    storageService.init();
  }
}
