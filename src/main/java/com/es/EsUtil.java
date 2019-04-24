package com.es;

import org.elasticsearch.client.Client;

public class EsUtil {
    public static Client getTransportClient() {
        try {
            return ElasticSearchFactory.getPool().borrowObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void closeClientPool(){
        ElasticSearchFactory.getPool().close();
    }

    public static void returnClient(Client client){
        ElasticSearchFactory.getPool().returnObject(client);
    }



}
