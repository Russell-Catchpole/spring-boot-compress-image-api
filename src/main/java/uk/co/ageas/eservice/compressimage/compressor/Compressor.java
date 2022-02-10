package uk.co.ageas.eservice.compressimage.compressor;
// Dummy change to test commit & push to GOGs
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.awt.image.*;

import javax.imageio.*;
import javax.imageio.stream.ImageOutputStream;

public class Compressor {

//    private final Path root = Paths.get("uploads");
    private final Path root = Paths.get("/");

    public File compressImage(File inputImage, String quality) throws IOException {

        //File input = new File("digital_image_processing.jpg");
        System.out.println("inputImage" + inputImage.toString() );
        BufferedImage image = ImageIO.read(inputImage);

        System.out.println(this.root.resolve(inputImage.toString()));

        //File compressedImageFile = new File("cmp_" + inputImage.toString());
        String newFileName = null;
        int dotIndex = inputImage.toString().lastIndexOf('.');
        if (dotIndex == -1) {
            newFileName = inputImage.toString() + "_cmp";
        } else {
            newFileName = inputImage.toString().substring(0, dotIndex) + "_cmp" +
                    inputImage.toString().substring(dotIndex);
        }

//      File compressedImageFile = new File( inputImage.toString() + "cmp.jpg");
        File compressedImageFile = new File( newFileName);

        OutputStream os = new FileOutputStream(compressedImageFile);

        Iterator<ImageWriter>writers =  ImageIO.getImageWritersByFormatName("jpg");
        ImageWriter writer = (ImageWriter) writers.next();

        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
        writer.setOutput(ios);

        ImageWriteParam param = writer.getDefaultWriteParam();
        // Convert quality parameter with value 1 - 100 to compresisonQuality value 0 - 1f.
        float compressionQuality = Float.parseFloat(quality) / 100;
        System.out.println("CompressionQuality =" + compressionQuality);
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(compressionQuality);
        writer.write(null, new IIOImage(image, null, null), param);

        os.close();
        ios.close();
        writer.dispose();

        return compressedImageFile;
    }
}

