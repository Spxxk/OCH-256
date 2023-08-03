import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class OCH {

    public static void main(String[] args) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            SecretKey secretKey = keyGenerator.generateKey();
            
            byte[] keyBytes = secretKey.getEncoded();
            
            byte[][] roundKeys = new byte[8][4];
            for (int i = 0; i < 8; i++) {
                roundKeys[i] = Arrays.copyOfRange(keyBytes, i*4, (i+1)*4);
            }
            
            for (int i = 0; i < 8; i++) {
                System.out.println("Round key " + i + ": " + toHexString(roundKeys[i]));
            }
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String toHexString(byte[] bytes) {
        return String.format("%032X", new BigInteger(1, bytes));
    }
}
