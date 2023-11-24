

public class KeyValueElement {
    private StringBuilder key;
    private int value;

    KeyValueElement(StringBuilder field, int value) {
        this.key = field;
        this.value = value;
    }

    public void incValue() {
        value++;
    }

    public StringBuilder getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }
}