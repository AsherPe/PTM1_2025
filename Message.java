package test;
import java.util.Date;

public final class Message {
    public final byte[] data;
    public final String asText;
    public final double asDouble;
    public final Date date;

    public Message(String asText) { //constructor
        if (asText == null) {
            throw new IllegalArgumentException("String cannot be null");
        }
        this.data = asText.getBytes();
        this.asText = asText;
        this.asDouble = trypars(asText);
        this.date = new Date();
    }

    public Message(byte[] bytes) {
        this(new String(bytes));
    }

    public Message(double d) {
        this(Double.toString(d));
    }

    private static double trypars(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return Double.NaN;
        }
    }
}