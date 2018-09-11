package com.huntingCube.dataView.controller;

import com.huntingCube.dataView.model.ClientHistory;
import com.huntingCube.dataView.model.ResourceDetails;
import com.huntingCube.login.model.User;
import com.huntingCube.utility.HuntingCubeUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dgup27 on 5/22/2017.
 */
@Controller
public class ReportsController extends BaseController {

    static final Logger logger = LoggerFactory.getLogger(ReportsController.class);

    @RequestMapping(value = {"/clientStatusReport"}, method = RequestMethod.GET)
    public String clientStatusReport(ModelMap model) {
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        ClientHistory clientHistoryFilter = new ClientHistory();
        model.addAttribute("filterClientHistory", clientHistoryFilter);
        clientStatusReportData(model, clientHistoryFilter);
        return "reports/clientStatusReport";
    }

    @RequestMapping(value = {"/clientStatusReport"}, method = RequestMethod.POST)
    public String clientStatusReportFiltered(ModelMap model, ClientHistory clientHistory) {
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        model.addAttribute("filterClientHistory", clientHistory);
        clientStatusReportData(model, clientHistory);
        return "reports/clientStatusReport";
    }

    private void clientStatusReportData(ModelMap model, ClientHistory clientHistoryFilter) {
        clientHistoryFilter.setAddedBy((String)model.get("userSSOId"));
        List<ClientHistory> clientHistories = clientHistoryService.findByFilter(clientHistoryFilter);
        Map<String, Map<String, String>> allClientHistoryMap = new HashMap<>();
        for (ClientHistory clientHistory : clientHistories) {
            Map<String, String> clientHistoryMap = new HashMap<>();
            clientHistoryMap.put("clientName", clientHistory.getClient().getClientName());
            clientHistoryMap.put("clientStatus", clientHistory.getClientStatus().getClientStatusName());
            clientHistoryMap.put("positionName", clientHistory.getPositionName().getPositionName());
            ResourceDetails resourceServiceById = resourceService.findById(clientHistory.getResourceID());
            clientHistoryMap.put("resourceName", resourceServiceById.getName());
            clientHistoryMap.put("resourceEmail", resourceServiceById.getEmailId());
            clientHistoryMap.put("resourceContact", resourceServiceById.getContactNumber());
            User userServiceBySSO = userService.findBySSO(clientHistory.getAddedBy());
            if (userServiceBySSO != null) {
                clientHistoryMap.put("recruiterName", userServiceBySSO.getFirstName() + " " + userServiceBySSO.getLastName());
            } else {
                clientHistoryMap.put("recruiterName", clientHistory.getAddedBy());
            }

            if (clientHistory.getAddedBy().equals((String)model.get("userSSOId"))) {
                clientHistoryMap.put("action", "<a class=\"updateStatus\"\n" +
                        "                               href=\"/huntingCube/updateStatus-" + clientHistory.getId() + "\">Update Status</a>");
            } else {
                clientHistoryMap.put("action", "");
            }
            allClientHistoryMap.put(clientHistory.getId() + "", clientHistoryMap);
        }
        model.addAttribute("allClientHistoryMap", allClientHistoryMap);
    }
}
