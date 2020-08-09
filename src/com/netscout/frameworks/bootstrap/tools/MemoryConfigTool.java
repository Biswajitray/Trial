package com.netscout.frameworks.bootstrap.tools;

import com.netscout.frameworks.bootstrap.config.ProcessConstants;
import com.netscout.frameworks.bootstrap.config.xml.schema.MemoryConfiguration;
import com.netscout.frameworks.bootstrap.config.xml.schema.Process;
import com.netscout.frameworks.bootstrap.config.xml.schema.Processes;
import com.netscout.frameworks.bootstrap.config.xml.schema.SystemMemory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MemoryConfigTool {

    public static final int ADD_NEW_NODE = 1;

    public static void main(String args[])
    {
        System.setProperty("app.root", "C:\\NetScout\\");
        int opcode = 0;
        if(args.length > 0)
            opcode = Integer.parseInt(args[0]);

        MemoryConfiguration memoryConfiguration = (MemoryConfiguration) loadXML(ProcessConstants.memoryConfigurationPackage,
                getProcessConfigFileName(ProcessConstants.memoryConfigurationXML));

        switch (opcode)
        {
            case ADD_NEW_NODE : addNewMemoryNode(memoryConfiguration);
                              break;
        }
    }
    private static Object loadXML(String strPackage, String xmlName)
    {
        Object retVal = null;
        File xmlFile = null;
        FileInputStream fis = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Class.forName(strPackage));
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            xmlFile = new File(xmlName);

            if (xmlFile.exists()) {
                fis = new FileInputStream(xmlFile);
                retVal = jaxbUnmarshaller.unmarshal(fis); // ok
            }
            else {
                System.out.println("XML File:- "+xmlName+" does not exits.");
            }
        } catch (Throwable e) {
            System.out.println("Exception during unmarshal for xml file:- "+xmlName);
            e.printStackTrace();
        }
        finally
        {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return retVal;
    }
    public static String getProcessConfigFileName(String xmlName)
    {
        String xmlPath = ProcessConstants.masterConfigPath + File.separatorChar + xmlName;
        return xmlPath;
    }
    private static void addNewMemoryNode(MemoryConfiguration memoryConfiguration)
    {
        memoryConfiguration.getLicense().stream().flatMap(
                    license -> license.getASI2XMode().stream().flatMap(
                      asi2xMode -> asi2xMode.getServerType().stream()
                    )
                ).forEach(
                        serverType -> {
                            SystemMemory systemMemory = new SystemMemory();
                            systemMemory.setName("384000");

                            serverType.getSystemMemory().add(0,systemMemory);
                            SystemMemory memory_128 =  serverType.getSystemMemory().stream().
                                                        filter( memory -> memory.getName().equalsIgnoreCase("128000")).findFirst().get();

                            memory_128.getProcess().forEach(
                                    process -> {
                                        Process process_384 = new Process();
                                        process_384.setName(process.getName());
                                        process_384.setConnectionName(process.getConnectionName());
                                        process_384.setXmx(String.valueOf(Integer.parseInt(process.getXmx()) * 3));

                                        systemMemory.getProcess().add(process_384);
                                    }
                            );
        });

        saveProcessesXML(memoryConfiguration);
    }

    public static void saveProcessesXML(MemoryConfiguration memoryConfigurationXML)
    {
            try (OutputStream outputStream = new FileOutputStream(getProcessConfigFileName(ProcessConstants.memoryConfigurationXML))){

            JAXBContext jaxbContext = JAXBContext.newInstance(MemoryConfiguration.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(memoryConfigurationXML, outputStream);
            outputStream.flush();

        } catch (Throwable e) {
            System.out.println("Error during save process xml:- " + e.getMessage());
            e.printStackTrace();
        }
    }
}
