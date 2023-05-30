import java.math.BigInteger;
import java.security.SecureRandom;

public class ElGamalAlgorithm {
    private BigInteger p; // велике просте число
    private BigInteger g; // генератор
    private BigInteger x; // приватний ключ
    private BigInteger y; // публічний ключ

    public ElGamalAlgorithm() {
        // Генерація великого простого числа p та генератора g
        SecureRandom random = new SecureRandom();
        p = BigInteger.probablePrime(128, random);
        g = BigInteger.valueOf(2).modPow(BigInteger.probablePrime(64, random), p);

        // Генерація приватного ключа x
        x = new BigInteger(p.bitLength() - 1, random);

        // Генерація публічного ключа y
        y = g.modPow(x, p);
    }

    public BigInteger encrypt(BigInteger message) {
        // Генерація випадкового числа k
        SecureRandom random = new SecureRandom();
        BigInteger k = new BigInteger(p.bitLength() - 1, random);

        // Обчислення компонентів шифротексту
        BigInteger a = g.modPow(k, p);
        BigInteger b = y.modPow(k, p).multiply(message).mod(p);

        // Повернення шифротексту у вигляді пари (a, b)
        return a.add(b.multiply(p));
    }

    public BigInteger decrypt(BigInteger ciphertext) {
        // Видобування компонентів шифротексту
        BigInteger a = ciphertext.mod(p);
        BigInteger b = ciphertext.divide(p);

        // Обчислення розшифрованого тексту
        BigInteger message = a.modPow(x.negate(), p).multiply(b).mod(p);

        return message;
    }

    public static void main(String[] args) {
        ElGamalAlgorithm elGamal = new ElGamalAlgorithm();

        BigInteger plaintext = new BigInteger("12345");
        System.out.println("Відкритий текст: " + plaintext);

        BigInteger ciphertext = elGamal.encrypt(plaintext);
        System.out.println("Шифротекст: " + ciphertext);

        BigInteger decryptedText = elGamal.decrypt(ciphertext);
        System.out.println("Розшифрований текст: " + decryptedText);
    }
}
