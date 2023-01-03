import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String s = "Регулярные выражения — тема, которую программисты, даже опытные, зачастую откладывают на потом. " +
                "Однако большинству Java-разработчиков рано или поздно придётся столкнуться с обработкой текстовой информации. " +
                "Чаще всего — с операциями поиска в тексте и редактированием. " +
                "Без регулярных выражений продуктивный и компактный программный код, связанный с обработкой текстов, попросту немыслим. " +
                "Так что хватит откладывать, разберёмся с «регулярками» прямо сейчас. Это не такая уж и сложная задача.";

        StringBuilder str = new StringBuilder();
        // запис у змінну словника прийменників
        try (FileReader fr = new FileReader("Vocabulary.txt")) {
            int temp;
            for (; ; ) {
                temp = fr.read();
                if (temp == -1) break;
                str.append((char) temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // розділення по ,
        String[] vocabulary = str.toString().split(",");
        // видалення зайвих пробілів та додавання пробілів на початку та на кінці
        for (int i = 0; i < vocabulary.length; i++) {
            vocabulary[i] = vocabulary[i].trim();
            vocabulary[i] = " " + vocabulary[i] + " ";
        }
        s = " " + s;
        boolean flag;
        ArrayList<String> al = new ArrayList<>();
        // пошук прийменників у тексті та заміна на Java
        for (String value : vocabulary) {
            Pattern p = Pattern.compile(value, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
            Matcher m = p.matcher(s);
            flag = m.find();
            s = m.replaceAll(" Java ");
            if (flag) al.add(value);
        }
        s = s.trim();
        System.out.println(s);
        System.out.println();
        System.out.println("Поміняли такі слова:");
        for (String temp : al) System.out.println(temp);

    }
}
