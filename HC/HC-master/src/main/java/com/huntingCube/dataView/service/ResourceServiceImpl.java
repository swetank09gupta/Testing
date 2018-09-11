package com.huntingCube.dataView.service;

import com.huntingCube.dataView.dao.*;
import com.huntingCube.dataView.model.*;
import com.huntingCube.utility.HuntingCubeUtility;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by dgup27 on 1/10/2017.
 */
@Service("resourceService")
@Transactional
public class ResourceServiceImpl implements ResourceService {

    static final Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);

    @Autowired
    ResourceDao resourceDao;

    @Autowired
    InstituteDao instituteDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    PassingYearDao passingYearDao;

    @Autowired
    ProgramDao programDao;

    @Autowired
    StreamDao streamDao;

    @Autowired
    ClientDao clientDao;

    @Autowired
    ClientHistoryDao clientHistoryDao;

    @Autowired
    ClientStatusDao clientStatusDao;

    @Autowired
    PositionDao positionDao;

    @Autowired
    ResourceHistoryDao resourceHistoryDao;

    @Override
    public List<ResourceDetails> findResources(int maxRecords, ResourceDetails resourceDetails) {
        return resourceDao.findResources(maxRecords, resourceDetails);
    }

    @Override
    public ResourceDetails findById(int id) {
        return resourceDao.findById(id);
    }

    @Override
    public ResourceDetails findByEmail(String emailID) {
        return resourceDao.findByEmail(emailID);
    }

    @Override
    public void save(ResourceDetails resourceDetails) {
        resourceDao.save(resourceDetails);
    }

    @Override
    public void update(ResourceDetails resourceDetails) {
        ResourceDetails entity = resourceDao.findById(resourceDetails.getId());
        if(entity != null) {
            entity = resourceDetails;
        }
    }

    @Override
    public Map<String, String> saveExcelRecords(String filePath) {
        Map<String, String> errorMap = null;
        try {
            logger.info("Excel File Path {}", filePath);
            errorMap = readExcelAndSaveRecords(filePath);
        } catch (IOException io) {
            logger.error("Error fetching excel file", io);
        }
        return errorMap;
    }

    @Override
    public void deleteById(int id) {
        resourceDao.deleteById(id);
    }

    private Map<String, String> readExcelAndSaveRecords(String excelPath) throws IOException {
        int noOfRecordsPersisted = 0;
        Map<String, String> errorMap = new HashMap<>();
        try {
            FileInputStream file = new FileInputStream(new File(excelPath));
            org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(file);
            int noOfSheets = workbook.getNumberOfSheets();
            DataFormatter formatter = new DataFormatter();
            for (int i = 0; i < noOfSheets; i++) {
                org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(i);
                boolean isFirstRow = true;
                LinkedList<String> columnNamesList = new LinkedList<>();
                for (int rowCount = 0; rowCount < sheet.getPhysicalNumberOfRows(); rowCount++) {
                    Row row = sheet.getRow(rowCount);
                if (row == null || row.getCell(8) == null || formatter.formatCellValue(row.getCell(8)) == null || formatter.formatCellValue(row.getCell(8)).isEmpty()) {
                    rowCount++;
                    continue;
                }

                    ResourceDetails resourceDetails = new ResourceDetails();
                    ResourceHistoryDetails resourceHistoryDetails = null;
                    ClientHistory clientHistory = null;
                    Map<String, String> rowMap = new HashMap<>();
                    for (int index = 0; index <= 28; index++) {
                        Cell cell = row.getCell(index);
                        if (cell != null) {
                            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                            }
                            if (rowCount == 0) {
                                columnNamesList.add(formatter.formatCellValue(cell).trim());
                            } else {

                                rowMap.put(columnNamesList.get(index),
                                        cell == null || formatter.formatCellValue(cell).isEmpty() ? "NA" : formatter.formatCellValue(cell));
                            }
                        } else if (rowCount != 0) {
                            rowMap.put(columnNamesList.get(index), "NA");
                        }
                    }
                    if (rowCount == 0) {
                        //Do nothing
                    } else {
                        errorMap.put(rowMap.get(columnNamesList.get(8)), rowMap.get(columnNamesList.get(6)));
                        Institute institute = null;
                        Stream stream = null;
                        Program program = null;
                        PassingYear passingYear = null;
                        Location location = null;
                        Position position = null;
                        ClientStatus clientStatus = null;
                        Client client = null;

                        Date addedDate = HuntingCubeUtility.convertToDBDate(
                                rowMap.get(columnNamesList.get(0)), rowMap.get(columnNamesList.get(1)), rowMap.get(columnNamesList.get(2)));

                        String clientStatusName = rowMap.get(columnNamesList.get(3));
                        String clientPosition = rowMap.get(columnNamesList.get(4));
                        String clientName = rowMap.get(columnNamesList.get(5));
                        if (addedDate != null && HuntingCubeUtility.isNotEmptyOrNull(clientName) && HuntingCubeUtility.isNotEmptyOrNull(clientPosition)) {
                            clientHistory = new ClientHistory();
                            resourceHistoryDetails = new ResourceHistoryDetails();
                            if (clientDao.findByName(clientName) != null) {
                                clientHistory.setClient(clientDao.findByName(clientName));
                            } else {
                                client = new Client();
                                client.setClientName(clientName);
                                client.setAddedBy("Excel Upload");
                                clientDao.save(client);
                                clientHistory.setClient(client);
                            }
                            if (clientStatusDao.findByStatusName(clientStatusName) != null) {
                                clientHistory.setClientStatus(clientStatusDao.findByStatusName(clientStatusName));
                            } else {
                                clientStatus = new ClientStatus();
                                clientStatus.setClientStatusName(clientStatusName);
                                clientStatus.setAddedBy("Excel Upload");
                                clientStatusDao.save(clientStatus);
                                clientHistory.setClientStatus(clientStatus);
                            }
                            if (positionDao.findByName(clientPosition) != null) {
                                clientHistory.setPositionName(positionDao.findByName(clientPosition));
                            } else {
                                position = new Position();
                                position.setPositionName(clientPosition);
                                position.setAddedBy("Excel Upload");
                                positionDao.save(position);
                                clientHistory.setPositionName(position);
                            }
                            clientHistory.setAddedDate(addedDate);
                            clientHistory.setAddedBy(rowMap.get(columnNamesList.get(28)));
                            clientHistory.setRemarks(rowMap.get(columnNamesList.get(25)));
                        }
                        resourceDetails.setName(rowMap.get(columnNamesList.get(6)));
                        resourceDetails.setContactNumber(rowMap.get(columnNamesList.get(7)));
                        if ("NA".equals(rowMap.get(columnNamesList.get(8)))) {
                            continue;
                        }
                        resourceDetails.setEmailId("NA".equals(rowMap.get(columnNamesList.get(8))) ? "" : rowMap.get(columnNamesList.get(8)));
                        if (instituteDao.findByName(rowMap.get(columnNamesList.get(9))) != null) {
                            resourceDetails.setInstitute(instituteDao.findByName(rowMap.get(columnNamesList.get(9))));
                        } else {
                            institute = new Institute();
                            institute.setInstituteName(rowMap.get(columnNamesList.get(9)).toUpperCase());
                            institute.setAddedBy("Excel Upload");
                            instituteDao.save(institute);
                            resourceDetails.setInstitute(institute);
                        }
                        if (streamDao.findByName(rowMap.get(columnNamesList.get(10))) != null) {
                            resourceDetails.setStream(streamDao.findByName(rowMap.get(columnNamesList.get(10))));
                        } else {
                            stream = new Stream();
                            stream.setStreamName(rowMap.get(columnNamesList.get(10)).toUpperCase());
                            stream.setAddedBy("Excel Upload");
                            streamDao.save(stream);
                            resourceDetails.setStream(stream);
                        }
                        if (programDao.findByName(rowMap.get(columnNamesList.get(11))) != null) {
                            resourceDetails.setProgram(programDao.findByName(rowMap.get(columnNamesList.get(11))));
                        } else {
                            program = new Program();
                            program.setProgramName(rowMap.get(columnNamesList.get(11)).toUpperCase());
                            program.setAddedBy("Excel Upload");
                            programDao.save(program);
                            resourceDetails.setProgram(program);
                        }
                        if (passingYearDao.findByName(rowMap.get(columnNamesList.get(12))) != null) {
                            resourceDetails.setPassingYear(passingYearDao.findByName(rowMap.get(columnNamesList.get(12))));
                        } else {
                            passingYear = new PassingYear();
                            passingYear.setPassingYear(rowMap.get(columnNamesList.get(12)).toUpperCase());
                            passingYear.setAddedBy("Excel Upload");
                            passingYearDao.save(passingYear);
                            resourceDetails.setPassingYear(passingYear);
                        }
                        resourceDetails.setCGPA(rowMap.get(columnNamesList.get(13)));

                        resourceDetails.setAirRank(rowMap.get(columnNamesList.get(14)));

                        resourceDetails.setAreaOfExpertise(rowMap.get(columnNamesList.get(15)));
                        resourceDetails.setSkills(rowMap.get(columnNamesList.get(16)));
                        resourceDetails.setDesignation(rowMap.get(columnNamesList.get(17)));
                        resourceDetails.setCompany(rowMap.get(columnNamesList.get(18)));
                        resourceDetails.setExperience(rowMap.get(columnNamesList.get(19)));
                        resourceDetails.setFixedCTC(rowMap.get(columnNamesList.get(20)));
                        resourceDetails.setVariableCTC(rowMap.get(columnNamesList.get(21)));
                        resourceDetails.setNoticePeriod(rowMap.get(columnNamesList.get(22)));

                        if (locationDao.findByName(rowMap.get(columnNamesList.get(23))) != null) {
                            resourceDetails.setCurrentLocation(locationDao.findByName(rowMap.get(columnNamesList.get(23))));
                        } else {
                            location = new Location();
                            location.setLocationName(rowMap.get(columnNamesList.get(23)).toUpperCase());
                            location.setAddedBy("Excel Upload");
                            locationDao.save(location);
                            resourceDetails.setCurrentLocation(location);
                        }

                        if (locationDao.findByName(rowMap.get(columnNamesList.get(24))) != null) {
                            resourceDetails.setPreferredLocation(locationDao.findByName(rowMap.get(columnNamesList.get(24))));
                        } else {
                            location = new Location();
                            location.setLocationName(rowMap.get(columnNamesList.get(24)).toUpperCase());
                            location.setAddedBy("Excel Upload");
                            locationDao.save(location);
                            resourceDetails.setPreferredLocation(location);
                        }

                        resourceDetails.setExpectedCTC(rowMap.get(columnNamesList.get(26)));

                        resourceDetails.setLinkedinProfile(rowMap.get(columnNamesList.get(27)));
                        resourceDetails.setAddedBy("NA".equals(rowMap.get(columnNamesList.get(28))) ? "Excel Upload" : rowMap.get(columnNamesList.get(28)));
                        if (addedDate != null)
                            resourceDetails.setAddedDate(addedDate);
                        else
                            resourceDetails.setAddedDate(new Date());
                        ResourceDetails resourceDetailsByEmail = resourceDao.findByEmail(resourceDetails.getEmailId());
                        int resourceID = 0;
                        int resourceHistoryID = 0;
                        if (resourceDetailsByEmail != null) {
                            if (addedDate != null && !(new Date()).equals(resourceDetails.getAddedDate()) && addedDate.before(resourceDetails.getAddedDate())) {
                                //Do not add resource to resource detail as this is copy of existing resource but sent earlier
                                logger.info("Doing nothing");
                            } else {
                                resourceDetailsByEmail = (ResourceDetails) HuntingCubeUtility.copyResourceData(resourceDetails, resourceDetailsByEmail);
                                resourceDao.save(resourceDetailsByEmail);
                            }
                            resourceID = resourceDetailsByEmail.getId();
                        } else {
                            resourceDao.save(resourceDetails);
                            resourceID = resourceDetails.getId();
                        }

                        if (resourceHistoryDetails != null) {
                            resourceHistoryDetails = (ResourceHistoryDetails) HuntingCubeUtility.copyResourceData(resourceDetails, resourceHistoryDetails);
                            resourceHistoryDetails.setId(0);
                            resourceHistoryDao.save(resourceHistoryDetails);
                            resourceHistoryID = resourceHistoryDetails.getId();
                        }

                        if (clientHistory != null) {
                            clientHistory.setResourceID(resourceID);
                            clientHistory.setResourceHistoryID(resourceHistoryID);
                            clientHistoryDao.save(clientHistory);
                        }
                        errorMap.remove(rowMap.get(columnNamesList.get(8)));
                        noOfRecordsPersisted++;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error in excel upload>>>>>>>>>>>>>>>>", e);
            errorMap.put("Exception", e.getMessage());
        }
        errorMap.put("noOfRecordsPersisted", noOfRecordsPersisted+"");
        logger.info("ErrorMap>>>>>>>>>>>{}", errorMap.toString());
        return errorMap;
    }
}
