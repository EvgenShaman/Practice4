import java.io.*;
import java.util.regex.*;

class PhoneFormatter {

    public static void main(String[] args) {

    }

    private static String readFile(String filename) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Записывает строку в файл
     */
    private static void writeFile(String filename, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ищет в тексте все телефонные номера и заменяет их на отформатированные
     */
    private static String replacePhoneNumbers(String text) {
        // Регулярное выражение для поиска номеров:
        //   (?:+7|7|8) – код страны (может быть с +)
        //   затем любые разделители (\s, -, ., (, ))
        //   затем 10 цифр, между которыми тоже могут быть разделители
        String regex = "(?:(?:\\+?7|8)[\\s\\-\\(\\)\\.]*)(?:\\d[\\s\\-\\(\\)\\.]*){10}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String phoneRaw = matcher.group();
            String formatted = formatPhoneNumber(phoneRaw);
            matcher.appendReplacement(result, Matcher.quoteReplacement(formatted));
        }
        matcher.appendTail(result);
        return result.toString();
    }

    /**
     * Преобразует сырой номер (например "+7 (999) 000-11-11" или "8-912-345-67-89")
     * в единый формат +1 (XXX) XXX-XX-XX
     */
    private static String formatPhoneNumber(String raw) {
        // Извлекаем все цифры из строки
        String digits = raw.replaceAll("\\D", "");
        if (digits.length() < 10) {
            // Недостаточно цифр – возвращаем как есть (ошибка)
            return raw;
        }

        // Берём последние 10 цифр (локальный номер)
        String local = digits.substring(digits.length() - 10);
        String part1 = local.substring(0, 3);
        String part2 = local.substring(3, 6);
        String part3 = local.substring(6, 8);
        String part4 = local.substring(8, 10);

        return String.format("+1 (%s) %s-%s-%s", part1, part2, part3, part4);
    }
}