package com.huntingCube.dataView.model;

import com.huntingCube.utility.HuntingCubeUtility;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dgup27 on 5/21/2017.
 */
@MappedSuperclass
public abstract class ResourceBase extends BaseEntity {

    static final Logger logger = LoggerFactory.getLogger(ResourceBase.class);

    @NotEmpty
    @Column(name = "NAME")
    private String name;

    @Column(name = "CONTACT_NUMBER")
    private String contactNumber;

    @Column(name = "EMAIL")
    private String emailId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "INSTITUTE_ID")
    private Institute institute;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "STREAM_ID")
    private Stream stream;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PROGRAM_ID")
    private Program program;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PASSING_YEAR_ID")
    private PassingYear passingYear;

    @Column(name = "CGPA")
    private double CGPA;

    @Column(name = "AIR_RANK")
    private int airRank;

    @Column(name = "OTHER_RANK")
    private int otherRank;

    @Column(name = "AREA_EXPERTISE")
    private String areaOfExpertise;

    @Column(name = "SKILLS")
    private String skills;

    @Column(name = "DESIGNATION")
    private String designation;

    @Column(name = "COMPANY")
    private String company;

    @Column(name = "EXPERIENCE")
    private double experience;

    @Column(name = "FIXED_CTC")
    private double fixedCTC;

    @Column(name = "VARIABLE_CTC")
    private double variableCTC;

    @Column(name = "EXPECTED_CTC")
    private double expectedCTC;

    @Column(name = "NOTICE_PERIOD")
    private double noticePeriod;

    @OneToOne
    @JoinColumn(name = "CURRENT_LOCATION_ID", referencedColumnName = "LOCATION_ID")
    private Location currentLocation;

    @OneToOne
    @JoinColumn(name = "PREFERRED_LOCATION_ID", referencedColumnName = "LOCATION_ID")
    private Location preferredLocation;

    @Column(name = "LINKDIN_PROFILE")
    private String linkedinProfile;

    @Column(name = "FACEBOOK_PROFILE")
    private String facebookProfile;

    @Column(name = "ADDED_DATE")
    private Date addedDate;

    public String getLinkedinProfile() {
        return linkedinProfile;
    }

    public void setLinkedinProfile(String linkedinProfile) {
        this.linkedinProfile = linkedinProfile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Institute getInstitute() {
        return institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public double getCGPA() {
        return CGPA;
    }

    public void setCGPA(Object CGPA) {
        this.CGPA = HuntingCubeUtility.convertToDouble(CGPA);
    }

    public int getAirRank() {
        return airRank;
    }

    public void setAirRank(Object airRank) {
        this.airRank = HuntingCubeUtility.convertToInt(airRank);
    }

    public String getAreaOfExpertise() {
        return areaOfExpertise;
    }

    public void setAreaOfExpertise(String areaOfExpertise) {
        this.areaOfExpertise = areaOfExpertise;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(Object experience) {
        this.experience = HuntingCubeUtility.convertToDouble(experience);
    }

    public double getFixedCTC() {
        return fixedCTC;
    }

    public void setFixedCTC(Object fixedCTC) {
        this.fixedCTC = HuntingCubeUtility.convertToDouble(fixedCTC);
    }

    public double getVariableCTC() {
        return variableCTC;
    }

    public void setVariableCTC(Object variableCTC) {
        this.variableCTC = HuntingCubeUtility.convertToDouble(variableCTC);
    }

    public double getExpectedCTC() {
        return expectedCTC;
    }

    public void setExpectedCTC(Object expectedCTC) {
        this.expectedCTC = HuntingCubeUtility.convertToDouble(expectedCTC);
    }

    public double getNoticePeriod() {
        return noticePeriod;
    }

    public void setNoticePeriod(Object noticePeriod) {
        this.noticePeriod = HuntingCubeUtility.convertToDouble(noticePeriod);
    }

    public String getFacebookProfile() {
        return facebookProfile;
    }

    public void setFacebookProfile(String facebookProfile) {
        this.facebookProfile = facebookProfile;
    }

    public Stream getStream() {
        return stream;
    }

    public void setStream(Stream stream) {
        this.stream = stream;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public PassingYear getPassingYear() {
        return passingYear;
    }

    public void setPassingYear(PassingYear passingYear) {
        this.passingYear = passingYear;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Location getPreferredLocation() {
        return preferredLocation;
    }

    public void setPreferredLocation(Location preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public int getOtherRank() {
        return otherRank;
    }

    public void setOtherRank(Object otherRank) {
        this.otherRank = HuntingCubeUtility.convertToInt(otherRank);
    }

    @Override
    public String toString() {
        return "ResourceBase{" +
                "name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", emailId='" + emailId + '\'' +
                ", institute=" + institute +
                ", stream=" + stream +
                ", program=" + program +
                ", passingYear=" + passingYear +
                ", CGPA=" + CGPA +
                ", airRank=" + airRank +
                ", otherRank='" + otherRank + '\'' +
                ", areaOfExpertise='" + areaOfExpertise + '\'' +
                ", skills='" + skills + '\'' +
                ", designation='" + designation + '\'' +
                ", company='" + company + '\'' +
                ", experience=" + experience +
                ", fixedCTC='" + fixedCTC + '\'' +
                ", variableCTC='" + variableCTC + '\'' +
                ", noticePeriod=" + noticePeriod +
                ", currentLocation=" + currentLocation +
                ", preferredLocation=" + preferredLocation +
                ", linkedinProfile='" + linkedinProfile + '\'' +
                ", facebookProfile='" + facebookProfile + '\'' +
                ", addedDate=" + addedDate +
                ", addedBy=" + getAddedBy()+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceBase that = (ResourceBase) o;

        return emailId.equals(that.emailId);
    }

    @Override
    public int hashCode() {
        return emailId.hashCode();
    }
}
