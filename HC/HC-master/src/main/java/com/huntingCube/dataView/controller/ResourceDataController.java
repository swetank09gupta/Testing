package com.huntingCube.dataView.controller;

import com.huntingCube.dataView.model.*;
import com.huntingCube.utility.HuntingCubeUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by dgup27 on 1/10/2017.
 */
@Controller
public class ResourceDataController extends BaseController {

    static final Logger logger = LoggerFactory.getLogger(ResourceDataController.class);

    /**
     * This method will redirect user having only User role to data view page.
     */
    @RequestMapping(value = {"/", "/dataList"}, method = RequestMethod.GET)
    public String dataList(ModelMap model) {
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        ResourceDetails resourceDetails = new ResourceDetails();
        model.addAttribute("resourceDetails", resourceDetails);
        model.addAttribute("isFilterSet", "false");
        return "dataView/dataList";
    }

    @RequestMapping(value = {"/", "/dataList"}, method = RequestMethod.POST)
    public String fetchDataList(ResourceDetails resourceDetails, ModelMap model) {
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        List<ResourceDetails> resourceDetailsList = resourceService.findResources(20, resourceDetails);
        model.addAttribute("resourceDetails", resourceDetails);
        model.addAttribute("resourceDetailsList", resourceDetailsList);
        return "dataView/dataList";
    }

    @RequestMapping(value = {"/addResourceExcel"}, method = RequestMethod.GET)
    public String uploadExcel(ModelMap model) {
        model.addAttribute("filePath", "");
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/excelUploadUtility";
    }


    @RequestMapping(value = {"/addResource"}, method = RequestMethod.GET)
    public String addResource(ModelMap model) {
        ResourceDetails resourceDetails = new ResourceDetails();
        model.addAttribute("resourceDetails", resourceDetails);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/addResource";
    }

    @RequestMapping(value = {"/addResource"}, method = RequestMethod.POST)
    public String saveResource(@Valid ResourceDetails resourceDetails, BindingResult result,
                               ModelMap model) {
        return saveOrUpdateResource(resourceDetails, result, model);
    }

    @RequestMapping(value = {"/deleteResource-{resourceID}"}, method = RequestMethod.GET)
    public String deleteResource(@PathVariable String resourceID, ModelMap model) {
        resourceService.deleteById(Integer.parseInt(resourceID));
        model.addAttribute("edit", true);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "redirect:/dataList";
    }

    @RequestMapping(value = {"/editResource-{resourceID}"}, method = RequestMethod.GET)
    public String editResource(@PathVariable String resourceID, ModelMap model) {
        ResourceDetails resourceDetails = resourceService.findById(Integer.parseInt(resourceID));
        model.addAttribute("resourceDetails", resourceDetails);
        model.addAttribute("edit", true);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/addResource";
    }

    @RequestMapping(value = {"/editResource-{resourceID}"}, method = RequestMethod.POST)
    public String updateResource(@Valid ResourceDetails resourceDetails, BindingResult result,
                               ModelMap model) {
        return saveOrUpdateResource(resourceDetails, result, model);
    }

    private String saveOrUpdateResource(ResourceDetails resourceDetails, BindingResult result,
                                        ModelMap model) {
        int resourceID = 0;
        try {
            HuntingCubeUtility.setGlobalModelAttributes(model, userService);
            resourceDetails.setAddedBy((String) model.get("userSSOId"));
            resourceDetails.setAddedDate(new Date());
            if (result.hasErrors()) {
                logger.info("Error in result");
            }
            ResourceDetails resourceDetailsByEmail = resourceService.findByEmail(resourceDetails.getEmailId());
            if (resourceDetailsByEmail != null) {
                logger.info("Resource Already exist by this email >> " + resourceDetailsByEmail);
                resourceDetailsByEmail = (ResourceDetails) HuntingCubeUtility.copyResourceData(resourceDetails, resourceDetailsByEmail);
                logger.info("After Setting resource Data >> " + resourceDetailsByEmail);
                resourceService.save(resourceDetailsByEmail);
            } else {
                resourceService.save(resourceDetails);
            }

            resourceID = resourceDetails.getId();
        } catch (Exception e) {
            logger.error("Error while saving resource", e);
        }
        return "redirect:/dataList";
    }

    @RequestMapping(value = {"/resourceDetails-{id}"}, method = RequestMethod.GET)
    public String getResource(@PathVariable String id, ModelMap model) {
        model.addAttribute("resourceDetail", resourceService.findById(Integer.parseInt(id)));
        return "dataView/resourceDetails";
    }

    @RequestMapping(value = {"/addResourceExcel"}, method = RequestMethod.POST)
    public String addDataFromExcel(@Valid String filePath, ModelMap model) {
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        Map<String, String> errorMap = resourceService.saveExcelRecords(filePath);
        StringBuilder message = new StringBuilder();
        if(errorMap != null) {
            if(errorMap.containsKey("noOfRecordsPersisted")) {
                message.append("Total Number of Records saved : ").append(errorMap.get("noOfRecordsPersisted")).append("\n");
                errorMap.remove("noOfRecordsPersisted");
            }
            if(errorMap.containsKey("noOfRecordsPersisted")) {
                message.append("Exception occurred : ").append(errorMap.get("Exception")).append("\n");
                errorMap.remove("Exception");
            }
            for(Map.Entry<String, String> entry : errorMap.entrySet()) {
                message.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
            }
        }

        if(!message.toString().isEmpty()) {
            logger.info("message>>>>>>>>>>."+message.toString());
            model.addAttribute("errorMessage", message.toString());
        }
        return "dataView/excelUploadUtility";
    }

}
