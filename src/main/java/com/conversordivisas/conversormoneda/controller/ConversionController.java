package com.conversordivisas.conversormoneda.controller;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.conversordivisas.conversormoneda.model.Conversion;

@Controller
public class ConversionController {

    @Value("${fixer.api.key}")
    private String apiKey;

    @GetMapping("/ConvertD")
    public String showForm(Model model) throws IOException {
        Conversion conversion = new Conversion();
        model.addAttribute("conversion", conversion);
        Map<String, String> currencies = getCurrencyList();
        model.addAttribute("currencies", currencies);
        return "index";
    }

    @GetMapping("/symbols")
    public ResponseEntity<String> getSymbols() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url("https://api.apilayer.com/fixer/symbols")
                .addHeader("apikey", apiKey)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        return ResponseEntity.ok(response.body().string());
    }

    @PostMapping("/ConvertD")
    public String convertCurrency(@ModelAttribute Conversion conversion, Model model) throws IOException {
        String from = conversion.getFrom();
        String to = conversion.getTo();
        double amount = conversion.getAmount();
        try {
            double result = convert(from, to, amount);
            conversion.setResult(result);
            model.addAttribute("conversion", conversion);
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error de conversión de moneda");
        }
        Map<String, String> currencies = getCurrencyList();
        model.addAttribute("currencies", currencies);
        return "index";
    }

    private Map<String, String> getCurrencyList() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url("https://api.apilayer.com/fixer/symbols")
                .addHeader("apikey", apiKey)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        Map<String, String> currencies = new HashMap<>();
        try {
            JSONObject jsonObject = new JSONObject(responseBody);
            for (String key : jsonObject.getJSONObject("symbols").keySet()) {
                currencies.put(key, jsonObject.getJSONObject("symbols").getString(key));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IOException("Error parsing currency list");
        }
        return currencies;
    }

    private double convert(String from, String to, double amount) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url("https://api.apilayer.com/fixer/convert?to=" + to + "&from=" + from + "&amount=" + amount)
                .addHeader("apikey", apiKey)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        JSONObject jsonObject = new JSONObject(responseBody);
        return jsonObject.getDouble("result");
    }

}