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

        List<Winery> modifiedList = removeConfusionsPipe.removeConfusingNames(
                removeConfusionsPipe.removeStreet(list)
        );
        removeConfusionsPipe.changeById(modifiedList);
        removeConfusionsPipe.translateById(modifiedList);

        System.out.println(modifiedList);
        System.out.println(modifiedList.size());
    }
}
