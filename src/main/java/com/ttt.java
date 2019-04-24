package com;

import org.elasticsearch.common.UUIDs;

import java.sql.*;

public class ttt {


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        for(int i = 0;i<10;i++){
            System.out.println(UUIDs.randomBase64UUID());
        }

    }
}
