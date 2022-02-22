package uk.net.catchpoler.image_compress.compressimage.compressor;

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

    public File compressImage(File inputImage) throws IOException {

        BufferedImage image = ImageIO.read(inputImage);

        System.out.println(image);

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

        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(0.3f);
        writer.write(null, new IIOImage(image, null, null), param);

        os.close();
        ios.close();
        writer.dispose();

        return compressedImageFile;
    }
}

