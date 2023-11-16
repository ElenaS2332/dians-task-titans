package firstHomework.winery;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import java.io.FileWriter;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) {
        String zipFilePath = "dians-task-titans/data.zip";

        try (ZipInputStream zipInputStream = new CustomZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                String entryName = entry.getName();

                // Process each entry (file) in the zip file
                if (!entry.isDirectory()) {
                    processZipEntry(zipInputStream, entryName);
                }
                // Check if the stream is closed
                if (((CustomZipInputStream) zipInputStream).isClosed()) {
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static class CustomZipInputStream extends ZipInputStream {
        private boolean closed = false;

        public CustomZipInputStream(InputStream in) {
            super(in);
        }

        @Override
        public void close() throws IOException {
            super.close();
            closed = true;
        }

        public boolean isClosed() {
            return closed;
        }
    }
    private static void processZipEntry(ZipInputStream zipInputStream, String entryName) {
        // Your existing code to parse and process Winery data goes here
        OSMParser parser = new OSMParser(zipInputStream);
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
        
        // Write the modifiedList to a JSON file
        writeListToJsonFile(modifiedList, "dians-task-titans/firstHomework/winery/wineries.json");
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