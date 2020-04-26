package serialization;

public class SuperClass {
    protected int j;
    protected String value;

    public void setValue(String value)
    {
        this.value = value;
        j =10;
    }

    public String toString()
    {
        return "Value => "+value + " j => "+j;
    }
}
