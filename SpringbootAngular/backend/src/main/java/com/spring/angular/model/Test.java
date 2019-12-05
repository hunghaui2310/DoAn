package com.spring.angular.model;

import ch.qos.logback.classic.util.EnvUtil;

import java.util.HashMap;
import java.util.Map;

public class Test {

    public Map<String, Object> executeTransformation(String ktrPath) {

        Map<String, Object> executionResult = new HashMap<String, Object>();

        try {
            KettleEnvironment.init();
            EnvUtil.environmentInit();

            TransMeta transMeta = new TransMeta(ktrPath);

            List<DatabaseMeta> dbMetaList = transMeta.getDatabases();

            for (DatabaseMeta dbMeta : dbMetaList) {
                if (dbMeta.getName().equals(this.connectionName)) {
                    dbMeta.setHostname(this.dbHostName);
                    dbMeta.setUsername(this.dbUerName);
                    dbMeta.setPassword(this.dbPassword);
                    dbMeta.setDBPort(this.dbPort);
                    dbMeta.setDBName(this.dbName);
                }
            }

            Trans transformation = new Trans(transMeta);

            if (this.parameters != null) {
                for (Map.Entry<String, String> entry : this.parameters.entrySet()) {
                    transformation.setParameterValue((String) entry.getKey(), (String) entry.getValue());
                }
            }

            transformation.execute(null);
            transformation.waitUntilFinished();

            for (StepMetaDataCombi combi : transformation.getSteps()) {

                StepDTO stepDTO = new StepDTO();

                stepDTO.setStepName(combi.step.getStepname());
                stepDTO.setLinesInput(Long.valueOf(combi.step.getLinesInput()));
                stepDTO.setLinesOutput(Long.valueOf(combi.step.getLinesOutput()));
                stepDTO.setLinesRead(Long.valueOf(combi.step.getLinesRead()));
                stepDTO.setLinesRejected(Long.valueOf(combi.step.getLinesRejected()));
                stepDTO.setLinesUpdated(Long.valueOf(combi.step.getLinesUpdated()));

                stepDTO.setStepDestinationNameList(new ArrayList<String>());

                for (RowSet rowSet : combi.step.getOutputRowSets()) {
                    stepDTO.getStepDestinationNameList().add(rowSet.getDestinationStepName());
                }

                this.getStepDTOList().add(stepDTO);
            }

            if (transformation.getErrors() > 0) {
                System.out.println("Erroruting Transformation");

                executionResult.put("transformationExecuted", false);
                return executionResult;
            } else {

                executionResult.put("transformationExecuted", true);
                return executionResult;
            }
        } catch (Exception e) {
            e.printStackTrace();

            executionResult.put("transformationExecuted", false);
            return executionResult;
        }
    }
}
