package com.huntingCube.dataView.controller;

import com.huntingCube.dataView.model.ClientHistory;
import com.huntingCube.dataView.model.ResourceDetails;
import com.huntingCube.dataView.model.ResourceHistoryDetails;
import com.huntingCube.login.model.User;
import com.huntingCube.utility.HuntingCubeUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by dgup27 on 5/20/2017.
 */
@Controller
public class ClientHistoryController extends BaseController {

    static final Logger logger = LoggerFactory.getLogger(ClientHistoryController.class);

    @RequestMapping(value = {"/clientHistory-{id}"}, method = RequestMethod.GET)
    public String getClientHistory(@PathVariable String id, ModelMap model) {
        model.addAttribute("resourceDetail", resourceService.findById(Integer.parseInt(id)));
        return "dataView/resourceDetails";
    }

    @RequestMapping(value = {"/editAndSendToClient-{resourceID}"}, method = RequestMethod.GET)
    public String editAndSendToClient(@PathVariable int resourceID, ModelMap model) {
        model.addAttribute("fromAddClientHistory", true);
        ResourceDetails resourceDetails = resourceService.findById(resourceID);
        model.addAttribute("resourceDetails", resourceDetails);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/addResource";
    }

    @RequestMapping(value = {"/editAndSendToClient-{resourceID}"}, method = RequestMethod.POST)
    public String saveResourceHistory(@Valid ResourceDetails resourceDetails, @PathVariable int resourceID, BindingResult result,
                                      ModelMap model) {
        int savedResourceID = 0;
        try {
            logger.info("Coming for adding client History");
            HuntingCubeUtility.setGlobalModelAttributes(model, userService);
            ResourceHistoryDetails resourceHistoryDetails = new ResourceHistoryDetails();
            resourceHistoryDetails = (ResourceHistoryDetails) HuntingCubeUtility.copyResourceData(resourceDetails, resourceHistoryDetails);
            if (result.hasErrors()) {
                logger.info("Error in result");
            }
            resourceService.save(resourceDetails);
            resourceHistoryDetails.setId(0);
            resourceHistoryService.save(resourceHistoryDetails);
            logger.info("originalResourceID>>>>>>>>>>>>>>>."+resourceID);
            savedResourceID =  resourceHistoryDetails.getId();
        } catch (Exception e) {
            logger.error("Error while saving Client History", e);
        }
        logger.info("addClientHistory-savedResourceID>>>>>>>>"+resourceID+","+savedResourceID);
        return "redirect:/addClientHistory-"+resourceID+","+savedResourceID;
    }

    @RequestMapping(value = {"/addClientHistory-{resourceIDs}"}, method = RequestMethod.GET)
    public String addClientHistory(@PathVariable String resourceIDs, ModelMap model) {
        int resourceID = Integer.parseInt(resourceIDs.split(",")[0]);
        int resourceHistoryID = Integer.parseInt(resourceIDs.split(",")[1]);
        ClientHistory historyDetails = new ClientHistory();
        historyDetails.setResourceID(resourceID);
        historyDetails.setResourceHistoryID(resourceHistoryID);
        model.addAttribute("historyDetails", historyDetails);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/addClientHistory";
    }

    @RequestMapping(value = {"/addClientHistory-{resourceID}"}, method = RequestMethod.POST)
    public String saveClientHistory(@Valid ClientHistory historyDetails, BindingResult result,
                                    ModelMap model) {
        try {
            HuntingCubeUtility.setGlobalModelAttributes(model, userService);
            historyDetails.setAddedBy((String) model.get("userSSOId"));
            historyDetails.setAddedDate(new Date());
            logger.info("historyDetails"+historyDetails.toString());
            if (result.hasErrors()) {
                logger.error("Error in result"+ result.toString());
            }
            clientHistoryService.save(historyDetails);
        } catch (Exception e) {
            logger.error("Error while saving Client History", e);
        }
        return "redirect:/dataList";
    }

    @RequestMapping(value = {"/getClientHistory-{resourceID}"}, method = RequestMethod.GET)
    public String getResourceClientHistory(@PathVariable int resourceID, ModelMap model) {
        List<ClientHistory> clientHistoryList = clientHistoryService.findByResource(resourceID);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        if(clientHistoryList != null) {
            ListIterator<ClientHistory> clientHistoryListIterator = clientHistoryList.listIterator();
            while(clientHistoryListIterator.hasNext()) {
                ClientHistory clientHistory = clientHistoryListIterator.next();
                User user = userService.findBySSO(clientHistory.getAddedBy());
                if (user != null) {
                    clientHistory.setAddedBy(user.getFirstName() + " " + user.getLastName());
                } else {
                    clientHistory.setAddedBy(clientHistory.getAddedBy());
                }
            }
        }
        model.addAttribute("clientHistoryList", clientHistoryList);
        return "dataView/clientHistoryList";
    }

    @RequestMapping(value = {"/resourceHistoryDetails-{id}"}, method = RequestMethod.GET)
    public String getResource(@PathVariable String id, ModelMap model) {
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        model.addAttribute("resourceDetail", resourceHistoryService.findById(Integer.parseInt(id)));
        return "dataView/resourceDetails";
    }

    /*@RequestMapping(value = {"/updateStatus-{id}"}, method = RequestMethod.GET)
    public String updateStatus(@PathVariable int id, ModelMap model) {
        ClientHistory clientHistoryServiceById = clientHistoryService.findById(id);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        model.addAttribute("clientHistory", clientHistory)
    }*/
}
