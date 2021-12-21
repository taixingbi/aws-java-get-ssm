package com.hello;

import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
//import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.*;

public class AmazonSSMParameterStoreService {
    private final String SSM_AWS_REGION = "us-east-1";
    private SsmClient ssmClient;

    AmazonSSMParameterStoreService(){
        this.ssmClient = getSsmClient();
    }

    private SsmClient getSsmClient() {
      if (ssmClient == null) {
        ssmClient = SsmClient.builder()
//            .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
  //              .httpClientBuilder(UrlConnectionHttpClient.builder())
            .region(Region.of(SSM_AWS_REGION))
            .build();
      }
      return ssmClient;
    }

    public String getParaValue(String paraName) {
        String paraValue = "";
        try {
          GetParameterRequest parameterRequest = GetParameterRequest.builder()
              .name(paraName)
              .build();
          GetParameterResponse parameterResponse = ssmClient.getParameter(parameterRequest);
          paraValue =  parameterResponse.parameter().value();
        } catch (SsmException e) {
          System.err.println(e.getMessage());
          System.exit(1);
        }
        return paraValue;
    }

    public void close() {
        if (ssmClient != null) {
          ssmClient.close();
          ssmClient = null;
        }
    }
}
