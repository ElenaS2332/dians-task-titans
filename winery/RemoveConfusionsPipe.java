package winery;

import java.util.List;
import java.util.stream.Collectors;

public class RemoveConfusionsPipe implements RemoveStreet, RemoveConfusingNames
{

    @Override
    public List<Winery> removeStreet(List<Winery> list) {
        return list.stream().filter(winery ->
                (!winery.name.contains("Vasil Glavinov")
                        && !winery.name.contains("Васил Главинов")
                        && !winery.name.contains("Резиденција Главинов")
                        && !winery.name.contains("aleksglavinov@gmail.com")))
                .collect(Collectors.toList());
    }

    @Override
    public List<Winery> removeConfusingNames(List<Winery> list) {
        return list.stream().filter(winery ->
                        (!winery.name.contains("Жеравино")
                                && !winery.name.contains("Дарвинова")
                                && !winery.name.contains("aleksglavinov@gmail.com")
                                && !winery.name.contains("Željuvino")
                                && !winery.name.contains("Crkvino")
                                && !winery.name.contains("Divino Kamp")
                                && !winery.name.contains("Прифатен центар \"Винојуг\"")
                                && !winery.name.contains("Градинка \"Виножито\"")
                                && !winery.name.contains("Darvinova")
                                && !winery.name.contains("pizza;coffee_shop;burger;grill;wine;beer")
                                && !winery.name.contains("http://hotelimakedonija.com.mk/en/Restaurant/1073/In-Vino-Restaurant")
                                && winery.ID != 533757101
                        ))
                .collect(Collectors.toList());    }
}
