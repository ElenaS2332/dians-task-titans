package firstHomework.winery;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.File;
import java.util.HashMap;

public class OSMParser {

    private String filePath;
    private static HashMap<String, Winery> map = new HashMap<>();
    private static boolean isWine = false;
    private static String nameValue = "";

    private static String previousId = "";
    private static String previousLat = "";
    private static String previousLon = "";

    public OSMParser(String filePath) {
        this.filePath = filePath;
    }


    public static HashMap<String, Winery> getMap() {
        return map;
    }

    public void parseOSM()
    {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if(isWine)
                    {
                        Double currentLat = 0.0;
                        Double currentLon = 0.0;
                        if(previousLat!= null && previousLon!=null)
                        {
                            currentLat = Double.parseDouble(previousLat);
                            currentLon = Double.parseDouble(previousLon);
                        }
                        Winery winery = new Winery(Long.parseLong(previousId), nameValue, currentLat, currentLon);
                        map.putIfAbsent(previousId, winery);
                    }

                    isWine = false;
                    nameValue = attributes.getValue("v");

                    if((qName.equalsIgnoreCase("way")
                            || qName.equalsIgnoreCase("node")
                            || qName.equalsIgnoreCase("relation")))
                    {
                        previousId = attributes.getValue("id");
                        previousLat = attributes.getValue("lat");
                        previousLon = attributes.getValue("lon");
                    }
                    if(nameValue!=null && (nameValue.toLowerCase().contains("vinarija")
                            || nameValue.toLowerCase().contains("wine")
                            || nameValue.toLowerCase().contains("vino")
                            || nameValue.toLowerCase().contains("винарија")
                            || nameValue.toLowerCase().contains("вино"))) {
                        isWine = true;
                    }
                }
            };
            saxParser.parse(new File(filePath), handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
