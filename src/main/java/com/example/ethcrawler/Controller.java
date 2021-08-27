package com.example.ethcrawler;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Controller {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${apikey}")
    String ak;

    private static String url = "https://api.etherscan.io/";

    @GetMapping("/transactions")
    public List<Object> getTransactions(){

    }

    @GetMapping("/ethquery")
    public String queryForm(Model model){
        model.addAttribute("ethpojo", new EthPOJO());
        return "index";
    }

    @PostMapping("/ethquery")
    public String querySubmit(@ModelAttribute("ethpojo") EthPOJO ethpojo) throws UnsupportedEncodingException, UnirestException {
        String addr = ethpojo.getAddr();
        String blocknumber = ethpojo.getBlocknumber();
        EthQuery.CheckbyBlock(addr, blocknumber,ak );
        return "result";
    }
}
