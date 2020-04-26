package file.jar;

import com.netscout.frameworks.bootstrap.startup.ProcessStartup;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarFileMain {

    public static final String filterPackage = "com.netscout.frameworks.bootstrap.config.xml.util.filter";
    public static void main(String s[])
    {
        try {
         //   readJar();
            readJarAnotherApproach();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static void readJar() throws IOException, URISyntaxException {
        ProcessStartup pstartup = null;
        ClassLoader classLoader = JarFileMain.class.getClassLoader();
        //InputStreamclassLoader.getResourceAsStream(filterPackage.replace('.', '/'));
        Enumeration<URL> resources = classLoader.getResources(filterPackage.replace('.', '/')); // This should never be replaced with separator char
        while (resources.hasMoreElements())
        {
            URL filterPkgUrl = resources.nextElement();
            System.out.println("Package URL : "+filterPkgUrl);
            File pkgFolder = new File(filterPkgUrl.toURI());
            System.out.println("Package folder : "+pkgFolder);
            File[] filtersClass = pkgFolder.listFiles();

        }
    }

    private static void readJarAnotherApproach() throws IOException, URISyntaxException {
        ProcessStartup pstartup = null;
        ClassLoader classLoader = JarFileMain.class.getClassLoader();
        //InputStreamclassLoader.getResourceAsStream(filterPackage.replace('.', '/'));
        URL filterPkgUrl = classLoader.getResource(filterPackage.replace('.', '/')); // This should never be replaced with separator char
        System.out.println("File "+filterPkgUrl.getFile());
        System.out.println("URI "+filterPkgUrl.toURI().getRawPath());
            System.out.println("Package URL : "+filterPkgUrl);
            JarFile filterJar = new JarFile(filterPkgUrl.getFile().split("!")[0].substring(5));
            filterJar.stream().filter(jarEntry -> jarEntry.getName().indexOf(filterPackage.replace('.', '/')) > -1).forEach(
                    jarEntry -> {
                        System.out.println("JarEntry "+jarEntry.getName().replace('/','.'));

                    }
            );



    }
}
