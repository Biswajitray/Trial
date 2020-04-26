package others;

import com.sun.istack.internal.NotNull;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.net.Inet6Address;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OthersMain {
    private static List<String> specialHandlingProperties = new ArrayList<>();
    public static void main(String []s)
    {
      //  checkIpv6();
      //  checkIPv6Address();
     /*   // stealth.database.location=C:\\NetScout\\rtm\\database
        // PAHOME=C:\\NetScout\\rtm\\pa
       //  String location="C:\\NetScout\\rtm\\pa\\filters";
        //FileSystem fs = FileSystems.getDefault();
        //System.out.println(location);
         //   System.out.println("Location" + fs.getPath(location));
        //specialHandlingProperties.add("PAHOME");
        //specialHandlingProperties.add("pa.custom.filter.location");
        //specialHandlingProperties.add("stealth.database.location");
        try {
            //changeFilePath();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    // textSplit();
        String datapoint = "adfasdf";
        checkNotNull(datapoint);
        datapoint = null;
        checkNotNull(datapoint);
    }


    public static void checkNotNull(@NotNull String dataPoint)
    {
        System.out.println("Datasend : "+dataPoint);
    }
    private static void textSplit()
    {
        String s1 = "afdfsd1";
        String s2[] = s1.split("-");
        for (int i = 0 ; i < s2.length ; i++)
        {
            System.out.println(s2[i]);
        }
    }
    private static void textManipulation()
    {
        String temp = "[1231231kjksdfkjsoidfjoi988234]";
        System.out.println(temp.substring(1,temp.length()-1));
    }
    private static void changeFilePath() throws IOException {
        String newPropertyFilePath = "C:\\nG1\\Test\\serverprivate.properties";
        Properties prop = new Properties();
        prop.load(new FileReader(newPropertyFilePath));
        List<String> fileContent =  new ArrayList<>();
        try( BufferedReader br = new BufferedReader(new FileReader(newPropertyFilePath)))
        {
            String line = null;
            while( ( line = br.readLine() ) != null)
            {
                if( !line.startsWith("#")   && (line.indexOf("=") > -1))
                {
                    String key= line.split("=")[0];
                    String value = prop.getProperty(key);
                    value = modifyValue(key,value);
                    line = key + "=" + value;
                }
                fileContent.add("\n" +line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeToFile(fileContent,"C:\\nG1\\Test\\copy_of_serverprivate.properties");
    }


    private static String modifyValue(String key,String value)
    {
        if(specialHandlingProperties.contains(key)) {
            StringBuilder modifiedValue = new StringBuilder();
            String[] fileNameParts = value.split("\\\\");
            boolean addSeparator = false;
            for (String dirName : fileNameParts) {
                if (addSeparator)
                    modifiedValue.append(File.separator + File.separator);
                modifiedValue.append(dirName);
                addSeparator = true;
            }
            return modifiedValue.toString();
        }
        return value;
    }

    private static void writeToFile(List<String> fileContentList , String fileName)
    {
        if(fileContentList.size() <= 0)
            return;

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName)))
        {
            fileContentList.forEach(
                    propertyLine -> {
                        try {
                            bw.write(propertyLine);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void checkBoolean()
    {
        Map<String,Boolean> stringBooleanMap = new HashMap<>();
        stringBooleanMap.put("ABC",true);

        Boolean boolVal = stringBooleanMap.get("asdas");
        System.out.println("Value " + boolVal);
    }
    // Anything starts with "::" and never ends with ":"
    // e.g. ::150, ::150:138
    private static final String ipv6Pattern0 = "(::)([0-9a-f]{1,4}[:]??){1,}[^:]$";

    // Any address with "::" in between different parts of address
    private static final String ipv6Pattern1 = "(([0-9a-f]{1,4}[:]??){0,})(:)([0-9a-f]{1,4}[:]??){1,}[^:]$";

    // Ideal ipv6 with all eight fields
    private static final String ipv6Pattern2 = "([0-9a-f]{1,4}:){1,7}([0-9a-f]){1,4}";

    private static final String ipv6Pattern = ipv6Pattern0 + "|" + ipv6Pattern1 + "|" + ipv6Pattern2 ;
    private static Pattern VALID_IPV6_PATTERN = null;
    private static void checkIpv6()
    {
        String ipAddress = "::150";
        String ipAddress1 = "fd49:b785:0906:1001:4313::0150";
        String ipAddress2 = "fd49:b785:0906:1001:4313:0000:0000:0150";
        String ipAddress3 = "fd49:b785:906:1001:4313::150";
        VALID_IPV6_PATTERN = Pattern.compile(ipv6Pattern, Pattern.CASE_INSENSITIVE);
        Matcher m = VALID_IPV6_PATTERN.matcher(ipAddress);
        System.out.println("ipAddress :: " + m.matches());

        VALID_IPV6_PATTERN = Pattern.compile(ipv6Pattern, Pattern.CASE_INSENSITIVE);
        Matcher m1 = VALID_IPV6_PATTERN.matcher(ipAddress1);
        System.out.println("ipAddress1 :: " + m1.matches());

        VALID_IPV6_PATTERN = Pattern.compile(ipv6Pattern, Pattern.CASE_INSENSITIVE);
        Matcher m2 = VALID_IPV6_PATTERN.matcher(ipAddress2);
        System.out.println("ipAddress2 :: " + m2.matches());

        VALID_IPV6_PATTERN = Pattern.compile(ipv6Pattern, Pattern.CASE_INSENSITIVE);
        Matcher m3 = VALID_IPV6_PATTERN.matcher(ipAddress3);
        System.out.println("ipAddress3 :: " + m3.matches());
    }

    private static void checkIPv6Address()
    {
        String ipAddress1 = "fd49:b785:0906:1001:4313::0150";
        String ipAddress2 = "fd49:b785:0906:1001:4313:0000:0000:0150";
        String ipAddress3 = "fd49:b785:906:1001:4313::150";

        String ipaAddress = "";
        if(ipaAddress.indexOf("::") > -1)
        {

        }
       // Inet6Address inet6Address = Inet6Address.getByAddress()
    }
}
