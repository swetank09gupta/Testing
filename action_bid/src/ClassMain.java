import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ClassMain {
    public static void main(String[] args) {
        List<CompanyDetails> company = PopulateData.populateData();
        LinkedHashMap<String, CompanyDetails> map = new LinkedHashMap<>();

        for (CompanyDetails c : company) {
            if (map.containsKey(c.getName())) {
                CompanyDetails temp = map.get(c.getName());
                if (c.getCurrentBid() > temp.getHighest()) {
                    temp.setHighest((long) c.getCurrentBid());
                } else if (c.getCurrentBid() < temp.getLowest()) {
                    temp.setLowest((long) c.getCurrentBid());
                }
                map.put(temp.getName(), temp);
            } else {
                c.setHighest((long) c.getCurrentBid());
                c.setLowest((long) c.getCurrentBid());
                map.put(c.getName(), c);
            }
        }
        System.out.println(map);
    }
}
