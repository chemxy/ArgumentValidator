package argVal.model;

import java.util.Objects;

public class Symbol {

    private String name;
    private Boolean value;

    public Symbol(String name) {
        this.name = name;
        this.value = null;
    }

    public Symbol(Character name) {
        this.name = name.toString();
        this.value = null;
    }

    public Symbol(String name, Boolean value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Boolean getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol symbol = (Symbol) o;
        return Objects.equals(name, symbol.name) && Objects.equals(value, symbol.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    @Override
    public String toString() {
      return getName();
    }
}
