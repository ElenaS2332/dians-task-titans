package winery;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String filePath = "data.osm";
        OSMParser parser = new OSMParser(filePath);
        parser.parseOSM();
        Map<String, Winery> map = OSMParser.getMap();

        DuplicatePipe duplicatePipe = new DuplicatePipe();
        List<Winery> list = duplicatePipe.removeDuplicates(map);

        RemoveConfusionsPipe removeConfusionsPipe = new RemoveConfusionsPipe();
        List<Winery> newList = removeConfusionsPipe.removeStreet(list);
        List<Winery> newerNewList = removeConfusionsPipe.removeConfusingNames(newList);
        System.out.println(newerNewList);
        System.out.println(newerNewList.size());
    }
}
