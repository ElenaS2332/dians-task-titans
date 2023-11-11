package firstHomework.winery;

import java.util.List;
import java.util.Map;

import java.io.FileWriter;
import java.io.IOException;

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

        writeListToJsonFile(modifiedList, "firstHomework/winery/wineries.json");
    }

    private static void writeListToJsonFile(List<Winery> modifiedList, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write("[\n");

            for (int i = 0; i < modifiedList.size(); i++) {
                Winery winery = modifiedList.get(i);
                String json = wineryToJson(winery);

                fileWriter.write(json);

                if (i < modifiedList.size() - 1) {
                    fileWriter.write(",\n");
                }
            }

            fileWriter.write("\n]\n");

            System.out.println("JSON file created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String wineryToJson(Winery winery) {
        // Convert Winery object to JSON representation
        // This is a simplified example, and you may need to adjust it based on your Winery class structure
        return String.format("  {\"id\": \"%s\", \"name\": \"%s\", \"latitude\": \"%s\", \"longitude\": \"%s\"}",
                winery.getID(), winery.getName(), winery.getLatitude(), winery.getLongitude());
    }
}
