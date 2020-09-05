package my.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

public class DesCbcUtil {
    private final static Logger logger = LoggerFactory.getLogger(DesCbcUtil.class);
    private final static String encoding = "UTF-8";
    /**
     * 3DES加密
     *
     * @param plainText 普通文本
     * @return 加密后的文本，失败返回null
     */
    public static String encode(String plainText, String secretKey, String iv) {
        String result = null;
        try {
            DESedeKeySpec deSedeKeySpec = new DESedeKeySpec(secretKey.getBytes());
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("desede");
            Key desKey = secretKeyFactory.generateSecret(deSedeKeySpec);
            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, desKey, ips);
            byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
            result = Base64Utils.encodeToString(encryptData);
        } catch (Exception e) {
            logger.error("DesCbcUtil encode error : {}", e);
        }
        return result;
    }

    /**
     * 3DES解密
     *
     * @param encryptText 加密文本
     * @return 解密后明文，失败返回null
     */
    public static String decode(String encryptText, String secretKey, String iv) {
        String result = null;
        try {
            DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("desede");
            Key desKey = secretKeyFactory.generateSecret(spec);
            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, desKey, ips);
            byte[] decryptData = cipher.doFinal(Base64Utils.decodeFromString(encryptText));
            result = new String(decryptData, encoding);
        } catch (Exception e) {
            logger.error("DesCbcUtil decode error : {}", e.getMessage());
        }
        return result;
    }
}
