import java.util.*;

public class PopulateData {
    public static List<CompanyDetails> populateData(){
        List<CompanyDetails> list = new ArrayList<CompanyDetails>();

        CompanyDetails c1 = new CompanyDetails("google", 500, 1234);
        CompanyDetails c2 = new CompanyDetails("google", 600, 1234);
        CompanyDetails c3 = new CompanyDetails("google", 300, 1234);

        CompanyDetails c4 = new CompanyDetails("rbs", 400, 1234);
        CompanyDetails c5 = new CompanyDetails("sapient", 10, 1234);
        CompanyDetails c6 = new CompanyDetails("sapient", 40, 1234);
        CompanyDetails c7 = new CompanyDetails("rbs", 100, 1234);
        CompanyDetails c8 = new CompanyDetails("polaris", 500, 1234);

        list.add(c1);
        list.add(c2);
        list.add(c3);
        list.add(c4);
        list.add(c5);
        list.add(c6);
        list.add(c7);
        list.add(c8);

        return list;
    }
}
