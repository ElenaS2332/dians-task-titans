package winery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DuplicatePipe implements RemoveDuplicates{
    public DuplicatePipe() {
    }

    @Override
    public List<Winery> removeDuplicates(Map<String, Winery> map) {
        List<Winery> list = new ArrayList<>(map.values());
        List<Winery> newList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            boolean isDuplicate = false;

            for (int j = 0; j < newList.size(); j++) {
                if (list.get(i).name.equals(newList.get(j).name)) {
                    isDuplicate = true;
                    break;
                }
            }

            if (!isDuplicate) {
                newList.add(list.get(i));
            }
        }

        return newList;
    }
}
