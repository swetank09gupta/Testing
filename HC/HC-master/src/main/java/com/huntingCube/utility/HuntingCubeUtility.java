package com.huntingCube.utility;

import com.huntingCube.dataView.model.ResourceBase;
import com.huntingCube.login.model.User;
import com.huntingCube.login.model.UserProfile;
import com.huntingCube.login.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

/**
 * Created by dgup27 on 1/10/2017.
 */
public class HuntingCubeUtility {

    static final Logger logger = LoggerFactory.getLogger(HuntingCubeUtility.class);

    public static void setGlobalModelAttributes(ModelMap model, UserService userService) {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        model.addAttribute("userSSOId", userName);
        User user = userService.findBySSO(userName);
        if (user != null) {
            String displayName = user.getFirstName() + " " + user.getLastName();
            model.addAttribute("userDisplayName", displayName);
            final Set<UserProfile> userProfiles = user.getUserProfiles();
            model.addAttribute("userProfiles", userProfiles);
        }
    }

    public static boolean isNotEmptyOrNull(String objectToCheck) {
        if(objectToCheck != null && !objectToCheck.trim().isEmpty())
            return true;
        else
            return false;
    }

    public static int convertToInt(Object inputString) {
        try {
            if (inputString != null && isNotEmptyOrNull(String.valueOf(inputString)) && !"NA".equals(String.valueOf(inputString))) {
                return Integer.parseInt((String.valueOf(inputString)).replaceAll("[^0-9]", ""));
            } else {
                return 0;
            }
        } catch (NumberFormatException e) {
            logger.error("Error while parsing value in Int : " + inputString);
            return 0;
        }
    }

    public static double convertToDouble(Object inputString) {
        try {
            if (inputString != null && isNotEmptyOrNull(String.valueOf(inputString)) && !"NA".equals(String.valueOf(inputString))) {
                return Double.parseDouble((String.valueOf(inputString)).replaceAll("[^0-9.]", ""));
            } else {
                return 0.0;
            }
        } catch (NumberFormatException e) {
            logger.error("Error while parsing value in Double : " + inputString);
            return 0.0;
        }
    }

    public static java.sql.Date convertToDBDate(String day, String month, String year) {
        try {
            if (isNotEmptyOrNull(day) && isNotEmptyOrNull(month) && isNotEmptyOrNull(year)) {
                String dateString = month + " " + day + ", " + year;
                DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
                Date date = format.parse(dateString);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                return sqlDate;
            } else {
                return null;
            }

        } catch (ParseException e) {
            return new java.sql.Date((new Date()).getTime());
        }
    }

    public static ResourceBase copyResourceData(ResourceBase fromResource, ResourceBase toResource) {

        if (HuntingCubeUtility.isNotEmptyOrNull(fromResource.getName()))
            toResource.setName(fromResource.getName());

        if (HuntingCubeUtility.isNotEmptyOrNull(fromResource.getContactNumber())) {
            String contactNo = toResource.getContactNumber();

            if (!HuntingCubeUtility.isNotEmptyOrNull(contactNo)) {
                contactNo = fromResource.getContactNumber();
            }

            if (HuntingCubeUtility.isNotEmptyOrNull(fromResource.getContactNumber()) && HuntingCubeUtility.isNotEmptyOrNull(toResource.getContactNumber()) && !fromResource.getContactNumber().replaceAll(" ", "").replaceAll(";", "").equals(toResource.getContactNumber().replaceAll(" ", "").replaceAll(";", ""))) {
                contactNo = contactNo + ";" + fromResource.getContactNumber();
            }
            toResource.setContactNumber(contactNo);
        }

        String emailID = toResource.getEmailId();

        if (!HuntingCubeUtility.isNotEmptyOrNull(emailID)) {
            emailID = fromResource.getEmailId();
        }

        if (HuntingCubeUtility.isNotEmptyOrNull(fromResource.getEmailId()) && HuntingCubeUtility.isNotEmptyOrNull(toResource.getEmailId()) && !fromResource.getEmailId().replaceAll(" ", "").replaceAll(";", "").equals(toResource.getEmailId().replaceAll(" ", "").replaceAll(";", ""))) {
            emailID = emailID + ";" + fromResource.getEmailId();
        }

        toResource.setEmailId(emailID);

        toResource.setInstitute(fromResource.getInstitute());

        toResource.setStream(fromResource.getStream());

        toResource.setProgram(fromResource.getProgram());

        toResource.setPassingYear(fromResource.getPassingYear());

        //toResource.setCGPA(fromResource.getCGPA());

        toResource.setAirRank(fromResource.getAirRank());

        toResource.setOtherRank(fromResource.getOtherRank());

        if (HuntingCubeUtility.isNotEmptyOrNull(fromResource.getAreaOfExpertise()))
            toResource.setAreaOfExpertise(fromResource.getAreaOfExpertise());

        if (HuntingCubeUtility.isNotEmptyOrNull(fromResource.getSkills()))
            toResource.setSkills(fromResource.getSkills());

        toResource.setDesignation(fromResource.getDesignation());

        if (HuntingCubeUtility.isNotEmptyOrNull(fromResource.getCompany()))
            toResource.setCompany(fromResource.getCompany());

        toResource.setExperience(fromResource.getExperience());

        toResource.setFixedCTC(fromResource.getFixedCTC());

        toResource.setVariableCTC(fromResource.getVariableCTC());

        toResource.setExpectedCTC(fromResource.getExpectedCTC());

        toResource.setNoticePeriod(fromResource.getNoticePeriod());

        toResource.setCurrentLocation(fromResource.getCurrentLocation());

        toResource.setPreferredLocation(fromResource.getPreferredLocation());

        if (HuntingCubeUtility.isNotEmptyOrNull(fromResource.getLinkedinProfile()))
            toResource.setLinkedinProfile(fromResource.getLinkedinProfile());

        if (HuntingCubeUtility.isNotEmptyOrNull(fromResource.getFacebookProfile()))
            toResource.setFacebookProfile(fromResource.getFacebookProfile());

        toResource.setAddedDate(fromResource.getAddedDate());
        toResource.setAddedBy(fromResource.getAddedBy());
        logger.info("From Resource>>>, {}", fromResource.toString());
        logger.info("To Resource>>>, {}", toResource.toString());
        return toResource;
    }
}
