package scanner.token;

import scanner.type.Type;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Token {
    private Type type;
    private String value;

    public Token(Type type, String value) {
        this.type = type;
        this.value = value;
    }
    // set getter for fields and make them self encapsulate
    public Type getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("(%s,%s)", getType().name(), getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Token) {
            Token temp = (Token) o;
            if (temp.getType() == this.getType()) {
                return this.getType() != Type.KEYWORDS || this.getValue().equals(temp.getValue());
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = getType().hashCode();
        if (getType() == Type.KEYWORDS)
            result = prime * result + (getValue() == null ? 0 : getValue().hashCode());
        return result;
    }

    public static Type getTyepFormString(String s) {
        Pattern pattern;
        Matcher matcher;
        for (Type t : Type.values()) {
            if (t.toString().equals(s))
                return t;
        }
        for (Type t : Type.values()) {
            pattern = Pattern.compile(t.pattern);
            matcher = pattern.matcher(s);
            if (matcher.matches())
                return t;
        }

        throw new IllegalArgumentException();
    }
}
