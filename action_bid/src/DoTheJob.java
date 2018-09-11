import sun.reflect.generics.tree.Tree;

import java.util.*;

public class DoTheJob {
    public static void main(String[] args) {
        List<CompanyDetails> details = PopulateData.populateData();

        LinkedHashMap<String, CompanyDetails> map = new LinkedHashMap<>();

        for(CompanyDetails detail : details){

            if(map.containsKey(detail.getName())){
                CompanyDetails temp = map.get(detail.getName());
                if(detail.getCurrentBid() > temp.getHighest()){
                    temp.setHighest((long) detail.getCurrentBid());
                }
                else if(detail.getCurrentBid() < temp.getLowest()){
                    temp.setLowest((long)detail.getCurrentBid());
                }
                map.put(temp.getName(), temp);
            }
            else{
                detail.setHighest((long)detail.getCurrentBid());
                detail.setLowest((long) detail.getCurrentBid());
                map.put(detail.getName(), detail);
            }


        }
        System.out.println(map);
    }
}
