package enumT;

import static enumT.EnumTrial.UpgradeType.*;
import static enumT.EnumTrial.UpgradeType.PROP2XML;
import static enumT.EnumTrial.UpgradeType.XML2XML_DEFAULT;

public class EnumTrial {
    public static void main(String s[])
    {
        UpgradeType type  = valueOf("PROP2XML");
        //String type = "p2x";
        switch (type)
        {
            case XML2XML: System.out.println("Type is XML 2 XML");
                break;

            case XML2XML_DEFAULT: System.out.println("Type is XML 2 XML DFAULT");
                break;

            case PROP2XML: System.out.println("Type is PROPERTIES 2 XML");
                break;
        }
    }

    public static enum UpgradeType
    {
        PROP2XML("p2x"),  // Upgrade from properties to XML
        XML2XML("x2x"), // Upgrade from xml 2 xml
        XML2XML_DEFAULT("x2x_d"); // Upgrade from xml 2 xml with only default memory node

        private String type;
        private UpgradeType(String type)
        {
            this.type = type;
        }

        public String getType()
        {
            return type;
        }
    }
}
