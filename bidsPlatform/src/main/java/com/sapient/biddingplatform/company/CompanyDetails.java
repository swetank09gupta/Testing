package com.sapient.biddingplatform.company;

import javax.annotation.Resource;

@Resource
public class CompanyDetails {
    private String companyName;
    private double bidAmount;

    public CompanyDetails (String name, double bidAmount) {
        this.bidAmount = bidAmount;
        this.companyName = name;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public String getName() {
        return companyName;
    }

    public void setName (String companyName) {
        companyName = companyName;
    }

    public void setBidAmount(double bidAmount) {
        bidAmount = bidAmount;
    }

}
