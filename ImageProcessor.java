import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by vishavpreetsingh on 2016-10-10.
 */
public class ImageProcessor
{
    /**
     * Default Constructor
     */
    public ImageProcessor()
    {

    }

    /**
     * Gets the Image from Disk and Returns a BufferedImage
     *
     * @param file The complete path of the file name
     * @return A BufferedImage of the provided path name
     */
    public BufferedImage getImage(String file)
    {
        BufferedImage image = null;
        File image_file = new File(file);
        try
        {
            image   = ImageIO.read(image_file);
        } catch (Exception ex)
        {
            System.out.println("Error Reading Image!!!");
        }
        return image;
    }


    /**
     * Writes the BufferImage  to file
     *
     * @param image The  BufferedImage to save
     * @param file  File to save the image to
     * @param ext   Extension of the file to be saved
     * @return True if successful
     */
    public boolean setImage(BufferedImage image, File file, String ext)
    {
        try
        {
            file.delete();
            ImageIO.write(image, ext, file);
            return true;
        } catch (Exception ex)
        {
            System.out.println("File cannot be saved!!!");
            return false;
        }
    }

    /**
     * To get the image in the memory for editing and saving images
     *
     * @param image The image to put into the user_space
     * @return The memory version of the image
     */
    public BufferedImage copy_image(BufferedImage image)
    {
        BufferedImage new_image = new BufferedImage(image.getWidth(), image.getHeight(),image.getType());
        Graphics2D graphics     = new_image.createGraphics();

        graphics.drawRenderedImage(image, null);
        graphics.dispose();
        return new_image;
    }

    /**
     * Gets the bytes from the image
     *
     * @param image The bufferedImage to get the bytes from
     * @return The byte array of the image supplied
     */
    public byte[] get_bytes(BufferedImage image)
    {
        WritableRaster raster = image.getRaster();
        DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();
        return buffer.getData();
    }

}
