import java.util.ArrayList;

public class KeyValueCollection {
    private  ArrayList<KeyValueElement> collection = new ArrayList<>(); //тут будемо зберфшати наші елементи

    public int getMaxFrequency() {
        int maxFrequency = 0;
        for (KeyValueElement record : collection) {
            if (record.getValue() > maxFrequency) {
                maxFrequency = record.getValue();
            }
        }
        return maxFrequency;
    }

    public void addElement(StringBuilder field) {
        for (KeyValueElement element : collection) {
            if(Main.equals(element.getKey(), field)) {
                element.incValue();
                return;
            }
        }
        collection.add(new KeyValueElement(field, 1));
    }

    public StringBuilder findKeyByValue(int value) {
        for (KeyValueElement element : collection) {
            if (element.getValue() == value) {
                return element.getKey();
            }
        }
        return null ;
    }

    public void printList() {
        System.out.println("| Field:   | Value: |");
        System.out.println("—————————————————————");
        for (KeyValueElement word : collection) {
            String formattedLine = String.format("| %-8s |    %d   |", word.getKey(), word.getValue());
            System.out.println(formattedLine);
        }
        System.out.println("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
    }


}