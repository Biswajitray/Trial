package serialization;

import java.io.Serializable;

public class DerivedClass extends SuperClass implements Serializable {
    private int i;
    private String derivedValue;

    public void setDerivedValue(String derivedValue)
    {
        this.derivedValue = derivedValue;
        i = 5;
    }

    public String toString()
    {
        return  super.toString() + "\n"
                 + "DerivedClass => " + derivedValue
                 + " i => " + i;
    }
}
