package com.hello;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("----------app start----------");
        AmazonSSMParameterStoreService amazonSSMParameterStoreService = new AmazonSSMParameterStoreService();
        String paraValue =
            amazonSSMParameterStoreService.getParaValue("/wonderfl/mp-dev-mirakl-base-url");
        System.out.println("paraValue: " + paraValue);
        System.out.println("----------app end----------");
    }
}
