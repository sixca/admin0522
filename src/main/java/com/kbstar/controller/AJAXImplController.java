package com.kbstar.controller;

import com.kbstar.dto.Chart;
import com.kbstar.service.ChartService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class AJAXImplController {

    @Autowired
    ChartService service;

    @RequestMapping("/chart1")
    public Object chart1() throws Exception {

            List<Chart> list = service.getMonthlyTotal();
            JSONArray fma = new JSONArray();
            JSONArray ma = new JSONArray();
            for (Chart c : list) {
                if (c.getGender().toUpperCase().equals("F")) {
                    fma.add(c.getTotal());
                } else {
                    ma.add(c.getTotal());
                }
            }
            JSONObject fmo = new JSONObject();
            JSONObject mo = new JSONObject();
            fmo.put("name", "Female");
            mo.put("name", "Male");
            fmo.put("data", fma);
            mo.put("data", ma);
            JSONArray data = new JSONArray();
            data.add(fmo);
            data.add(mo);
            return data;
        }
    }