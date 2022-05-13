import javax.crypto.*;
import java.io.IOException;
import java.security.*;

public class RSA extends Protocol {

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public RSA () throws NoSuchAlgorithmException {
        generateKeyPair( );
    }

    public PrivateKey generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance( "RSA" );
        keyPairGenerator.initialize( 2048 );
        KeyPair keyPair = keyPairGenerator.generateKeyPair( );
        this.privateKey = keyPair.getPrivate( );
        this.publicKey = keyPair.getPublic( );
        return privateKey;
    }



    @Override
    public byte[] encrypt ( byte[] message, PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
        String protocol= "RSA";
        Cipher cipher = Cipher.getInstance( protocol );
        cipher.init( Cipher.ENCRYPT_MODE , publicKey );
        return cipher.doFinal( message );
    }


    @Override
    public byte[] decrypt (byte[] message) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException,IOException {
        Cipher cipher = Cipher.getInstance( "RSA" );
        cipher.init( Cipher.DECRYPT_MODE , this.privateKey );
        return cipher.doFinal( message );
    }
    @Override
    public PublicKey getPublicKey () {
        return publicKey;
    }


}