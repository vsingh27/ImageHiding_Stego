/**
 * This is the main entry to the program.
 * Parses Command Line arguments and either
 * Encodes the message image into the carrier
 * image or decodes the message image from the
 * carrier image.
 *
 * DATE:        October 12, 2016
 *
 * REVISIONS:
 *
 * DESIGNER:    Vishav Singh <vishav27@gmail.com>
 *
 * PROGRAMMER:  Vishav Singh <vishav27@gmail.com>
 *
 */
import org.apache.commons.cli.*;

public class ImageHiding
{

    public static void main(String[] args) throws InterruptedException
    {
        StegoHelper stegoHelper = new StegoHelper();


        /*
         *Parse the command line arguments
         */
        Options options = new Options();

        Option path = new Option("pt","path", true, "Image file path");
        path.setRequired(true);
        options.addOption(path);

        Option carrier = new Option("c","carrier", true, "Name of the Carier image without extension");
        carrier.setRequired(true);
        options.addOption(carrier);


        Option stego = new Option("s","stego", true, "Name of the Stego image without extension");
        stego.setRequired(true);
        options.addOption(stego);

        Option extension = new Option("e", "extension", true, "Extension of the files");
        extension.setRequired(true);
        options.addOption(extension);

        Option mode = new Option("m", "mode", true, "Mode");
        mode.setRequired(true);
        options.addOption(mode);

        Option password = new Option("p", "password", true, "Password");
        password.setRequired(true);
        options.addOption(password);

        CommandLineParser   parser      =   new DefaultParser();
        HelpFormatter       formatter   =   new HelpFormatter();
        CommandLine cmd;

        try
        {
            cmd = parser.parse(options, args);
        }catch (ParseException e)
        {
            System.out.println(e.getMessage());
            formatter.printHelp("ImageHiding", options);
            System.exit(1);
            return;
        }

        String carrier_path  =   cmd.getOptionValue("path");
        String message_path  =   cmd.getOptionValue("path");
        String carrier_image    =   cmd.getOptionValue("carrier");
        String message_image    =   cmd.getOptionValue("stego");
        String task             =   cmd.getOptionValue("mode");
        String extension_img        =   cmd.getOptionValue("extension");
        String aes_password     =   cmd.getOptionValue("password");

        if(aes_password.length() != 16)
        {
            System.out.println("Password must be 16 Bytes");
            System.exit(1);
            return;
        }
        byte[] passwordarray = aes_password.getBytes();

        //Decode the Images
        if(task.equals("decode"))
        {
            stegoHelper.decode(carrier_path+"/stego","stego_new",extension_img, carrier_path+"/stego","new_message",passwordarray);
            System.exit(0);
            return;
        }

        //Encode the images
        if(task.equals("encode"))
        {
            stegoHelper.encode(carrier_path,carrier_image,extension_img,message_path,message_image,extension_img,"stego_new",passwordarray);
            System.exit(0);
            return;
        }

    }
}