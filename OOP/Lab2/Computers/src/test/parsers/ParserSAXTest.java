package test.parsers;

import main.java.models.Device;
import main.java.models.Elements;
import main.java.parsers.ParserSAX;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class ParserSAXTest {

    @Test
    public void parseSAX() throws IOException, SAXException, ParserConfigurationException {
        ParserSAX SAXparser = new ParserSAX();
        File xmlFile = new File("src/main/resources/devices.xml");
        List<Device> deviceList = SAXparser.parseSAX(xmlFile);
        Device firstDevice = deviceList.get(0);

        assertEquals(firstDevice.getTypes().getEnergyConsumption(), 6);
        assertEquals(firstDevice.getTypes().getPort(), "USB");
        assertEquals(firstDevice.getTypes().getGroup(), "Multimedia");
        assertTrue(firstDevice.getTypes().isPeripherals());
        assertTrue(firstDevice.getTypes().isCooler());

        assertEquals(Elements.FirstDeviceID, firstDevice.getId());
        assertEquals(Elements.FirstDeviceName, firstDevice.getName());

        assertEquals(firstDevice.getOrigin(), "United States");
        assertEquals(firstDevice.getPrice().toString(), "1000");
        assertTrue(firstDevice.isCritical());

        Device secondDevice = deviceList.get(1);

        assertEquals(secondDevice.getTypes().getEnergyConsumption(), 1);
        assertEquals(secondDevice.getTypes().getPort(), "false");
        assertEquals(secondDevice.getTypes().getGroup(), "Sound");
        assertTrue(secondDevice.getTypes().isPeripherals());
        assertFalse(secondDevice.getTypes().isCooler());

        assertEquals(Elements.SecondDeviceID, secondDevice.getId());
        assertEquals(Elements.SecondDeviceName, secondDevice.getName());

        assertEquals(secondDevice.getOrigin(), "China");
        assertEquals(secondDevice.getPrice().toString(), "50");
        assertTrue(secondDevice.isCritical());
    }
}