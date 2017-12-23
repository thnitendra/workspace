
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class Crypto {
    final static int ITERATIONS = 65536;
    final static int KEY_LENGTH = 256;

    public static String cypher(String pass, String salt) {
        byte[] secret = null;
        try {
            KeySpec pbeKeySpec = new PBEKeySpec(pass.toCharArray(), salt.getBytes(), ITERATIONS, KEY_LENGTH);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            secret = secretKeyFactory.generateSecret(pbeKeySpec).getEncoded();
        } catch(NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        System.out.println(secret);
        Base64.Encoder encoder = Base64.getEncoder();
        return secret == null ? null : encoder.encodeToString(secret);
    }

    public static void main(String[] args) {
        System.out.println(cypher(args[0], args[1]));
    }
}
