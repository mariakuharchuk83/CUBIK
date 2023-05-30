
import java.util.ArrayList;
import java.util.List;

public class AlgorithmRabin {
    private static final int PRIME = 101; // Просте число для хешування

    public static List<Integer> search(String text, String pattern) { //повертає список цілих чисел, які представляють початкові позиції всіх збігів шаблону у тексті
        List<Integer> occurrences = new ArrayList<>();

        int textLength = text.length();
        int patternLength = pattern.length();

        long patternHash = createHash(pattern, patternLength);
        long textHash = createHash(text, patternLength);

        // Проходимо по тексту
        for (int i = 0; i <= textLength - patternLength; i++) {
            // Перевіряємо, чи збігаються значення хешів
            if (patternHash == textHash && checkEqual(text, i, i + patternLength - 1, pattern)) {
                occurrences.add(i);
            }

            // Оновлюємо значення хешу для наступної ітерації
            if (i < textLength - patternLength) {
                textHash = recalculateHash(text, i, i + patternLength, textHash, patternLength);
            }
        }

        return occurrences;
    }

    private static long createHash(String str, int end) { //обчислює значення хешу для заданого рядка
        long hash = 0;
        for (int i = 0; i < end; i++) {
            hash += str.charAt(i) * Math.pow(PRIME, i);
        }
        return hash;
    }

    private static long recalculateHash(String str, int oldIndex, int newIndex, long oldHash, int patternLength) { //оновлює значення хешу при зміщенні вікна
        long newHash = oldHash - str.charAt(oldIndex);
        newHash /= PRIME;
        newHash += str.charAt(newIndex) * Math.pow(PRIME, patternLength - 1);
        return newHash;
    }

    private static boolean checkEqual(String str1, int start1, int end1, String str2) { //перевіряє, чи співпадають символи у вікні з шаблоном. Алгоритм ітерується по тексту, порівнюючи значення хешів та символи, та додає початкові позиції співпадаючих шаблонів до списку occurrences
        int len = end1 - start1 + 1;
        for (int i = 0; i < len; i++) {
            if (str1.charAt(start1 + i) != str2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) { //шукаємо шаблон
        String text = "AABAACAADAABAABA";
        String pattern = "AABA";
        List<Integer> occurrences = search(text, pattern);

        if (occurrences.isEmpty()) {
            System.out.println("Шаблон не знайдено в тексті.");
        } else {
            System.out.println("Шаблон знайдено на позиціях: " + occurrences);
        }
    }
}

