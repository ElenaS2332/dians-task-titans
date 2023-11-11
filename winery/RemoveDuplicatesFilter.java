package winery;

import java.util.List;
import java.util.Map;

public interface RemoveDuplicatesFilter {
    List<Winery> removeDuplicates(Map<String, Winery> map);
}
