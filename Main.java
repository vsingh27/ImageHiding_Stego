import static java.lang.Thread.sleep;

public class Main
{

    //stego [-d/-e] [carrier image] [hidden image]
    public static void main(String[] args) throws InterruptedException
    {
        StegoHelper stegoHelper = new StegoHelper();

        String carrierPath  = "/Users/vishavpreetsingh/Documents/Dev/IntelliJ/ImageHiding/images";
        String messagePath  = "/Users/vishavpreetsingh/Documents/Dev/IntelliJ/ImageHiding/images";
        String carrierName  = "carrier";
        String mesageName   = "message";
        String ext          = "png";
        String dec_path     = "/Users/vishavpreetsingh/Documents/Dev/IntelliJ/ImageHiding/images/stego";
        String password     = "helloWorld12345u";
        byte[] passwordarray = password.getBytes();

        stegoHelper.encode(carrierPath,carrierName,ext,messagePath,mesageName,ext,"stego");
        stegoHelper.decode(dec_path,"stego",ext, dec_path,"new_message",passwordarray);
    }
}