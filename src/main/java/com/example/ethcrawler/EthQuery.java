package com.example.ethcrawler;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


public class EthQuery {
    public static Integer tsToSec8601(String timestamp){
        if(timestamp == null) return null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            Date dt = sdf.parse(timestamp);
            long epoch = dt.getTime();
            return (int)(epoch/1000);

        } catch(ParseException e) {
            return null;
        }

    }

    public static boolean CheckbyBlock(String addr, String blocknumber, String apikey) throws UnsupportedEncodingException, UnirestException {

        //host url
        String host = "https://api.etherscan.io/api";
        String charset = "UTF-8";
        //params
        String s = "?module=account&action=txlist&address="
                + addr
                + "&startblock="
                + blocknumber
                + "&endblock=99999999&sort=asc&apikey="
                + apikey;

        //format query for preventing encoding problems
        String query = String.format("s=%s",URLEncoder.encode(s,charset));
        HttpResponse <JsonNode> response = Unirest.get(host + query)
                .asJson();

        System.out.println(response.getStatus());
        System.out.println(response.getHeaders().get("Content-Type"));
        return true;

    }
    /*public void BalanceCheck(String addr, String querydate){
        //host url
        String host = "https://api.etherscan.io/api";
        String charset = "UTF-8";
        //params
        String s = "?module=block&action=getblocknobytime&address="
                + addr
                + "&startblock="
                + blocknumber
                + "&endblock=99999999&sort=asc&apikey="
                + apikey;

        //format query for preventing encoding problems
        String query = String.format("s=%s",URLEncoder.encode(s,charset));
        HttpResponse <JsonNode> response = Unirest.get(host + query)
                .asJson();
    }*/
}
