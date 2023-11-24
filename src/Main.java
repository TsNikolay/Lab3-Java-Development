import java.util.ArrayList;
import java.util.Scanner;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
    public static void main(String[] args) {
        int studentBookNumber = 1122;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Номер заліковки: " + studentBookNumber);
        System.out.println("С3 = " + studentBookNumber + " mod 3 = " + studentBookNumber % 3 + " => Тип даних StringBuilder");
        System.out.println("С13 = " + studentBookNumber + " mod 17 = " + studentBookNumber % 17 + " => Треба знайти найбільшу кількість речень заданого тексту, в яких є однакові слова.");
        System.out.println("Будь ласка, введіть текст: ");

        StringBuilder text;
        do {
            text = new StringBuilder(scanner.nextLine());
        } while (!isTextValid(text));
        countSentencesWithSameWords(text);
        scanner.close();
    }
    
    private static boolean isTextValid(StringBuilder text) {
            if (text.length() == 0) {
                System.out.println("Текст відсутній");
                return false;
            }
            return true;
    }

    private static void countSentencesWithSameWords(StringBuilder text) {
        ArrayList<StringBuilder> sentences = splitTextIntoSentences(text);
        KeyValueCollection wordCount = new KeyValueCollection();

        for (StringBuilder sentence : sentences) {
            ArrayList<StringBuilder> words = splitSentenceIntoWords(sentence);
            ArrayList <StringBuilder> allWords = new ArrayList<>();
            for (StringBuilder word : words)
                if (!isWordCounted(allWords, word)) {
                    allWords.add(word);
                    wordCount.addElement(word);
                }

        }

        int maxFrequency = wordCount.getMaxFrequency();
        wordCount.printList();
        System.out.println("Слово яке зустрічається в найбільшій кількості речень: " + wordCount.findKeyByValue(maxFrequency));
        System.out.println("Кількість речень: " + wordCount.getMaxFrequency());

    }

    private static ArrayList<StringBuilder> splitTextIntoSentences(StringBuilder text) {
        ArrayList<StringBuilder> sentences = new ArrayList<>();

        int sentenceStart = 0;

        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);

            if (symbol == '.' || symbol == '!' || symbol == '?') {
                if (i + 1 < text.length() && text.charAt(i + 1) == ' ') {
                    StringBuilder sentence = new StringBuilder();
                    sentence.append(text, sentenceStart, i + 2); // з початку речення до початку наступного
                    sentences.add(sentence);
                    sentenceStart = i + 2;
                }
            }
        }

        // Додаємо останнє речення
        StringBuilder lastSentence = new StringBuilder(text.substring(sentenceStart));
        sentences.add(lastSentence);

        return sentences;
    }



    private static ArrayList<StringBuilder> splitSentenceIntoWords(StringBuilder sentence) {
        Pattern wordPattern = Pattern.compile("\\b\\w+\\b");
        ArrayList<StringBuilder> words = new ArrayList<>();
        Matcher matcher = wordPattern.matcher(sentence);

        while (matcher.find()) {
            StringBuilder word = new StringBuilder(matcher.group());
            toLowerCase(word);
            words.add(word);
        }

        return words;
    }

    private static void toLowerCase(StringBuilder word) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isUpperCase(c)) {
                word.setCharAt(i, Character.toLowerCase(c));
            }
        }
    }


    private static boolean isWordCounted(ArrayList<StringBuilder> allWords, StringBuilder word) {
        for (StringBuilder element : allWords) {
            if (equals(element, word)) {
                return true;
            }
        }
        return false;
    }

    public static boolean equals(StringBuilder text1, StringBuilder text2) {
        if (text1.length() != text2.length()) {
            return false;
        }

        for (int i = 0; i < text1.length(); i++) {
            if (text1.charAt(i) != text2.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}

/*
This sentence contains the word word!! Another sentence does not contain this word... The third sentence contains a word, another word, and a word? Hello. What about sentence? Oh, here it is!
 */