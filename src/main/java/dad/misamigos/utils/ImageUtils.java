package dad.misamigos.utils;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ImageUtils {

    private static byte [] imageToByteArray(Image image) throws IOException {
        BufferedImage bi = SwingFXUtils.fromFXImage(image, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, "png", baos);
        byte [] content  = baos.toByteArray();
        baos.close();
        return content;
    }

    private static Image byteArrayToImage(byte [] content) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(content);
        BufferedImage bi = ImageIO.read(bais);
        Image image = SwingFXUtils.toFXImage(bi, null);
        bais.close();
        return image;
    }

    public static String encodeImage(Image image) {
        if (image == null) {
            return null;
        }
        try {
            return new String(Base64.getEncoder().encode(imageToByteArray(image)));
        } catch (IOException e) {
            return null;
        }
    }

    public static Image decodeImage(String code) {
        if (code == null) {
            return null;
        }
        try {
            return byteArrayToImage(Base64.getDecoder().decode(code));
        } catch (IOException e) {
            return null;
        }
    }

}