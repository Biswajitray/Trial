package serialization;

import java.io.*;

public class SerializationMain {
    public static void main(String s[])
    {
        File file = new File("trialseriali.ser");
        DerivedClass dc = new DerivedClass();
        dc.setDerivedValue("Derived");
        dc.setValue("value");
        try {
            FileOutputStream foos = new FileOutputStream(file);
            ObjectOutputStream ooos = new ObjectOutputStream(foos);
            ooos.writeObject(dc);
            ooos.close();
            foos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream finps = new FileInputStream(file);
            ObjectInputStream oois = new ObjectInputStream(finps);

            System.out.println(oois.readObject());
            finps.close();
            oois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
