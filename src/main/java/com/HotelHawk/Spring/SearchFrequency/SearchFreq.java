package com.HotelHawk.Spring.SearchFrequency;

import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchFreq {
    public static HashMap<String,Integer> map=new HashMap<String, Integer>();
    public static String get_data() throws IOException {
        File file=new File(System.getProperty("user.dir")+"\\searchfreq");
        BufferedReader br= new BufferedReader(new FileReader(file));
        JSONObject json= new JSONObject();
        String s;
        while((s=br.readLine())!=null){
            json.put(s.substring(0,s.lastIndexOf(':')),s.substring(s.lastIndexOf(':')+1,s.length()));
        }
        return json.toString();

    }
    public static void update(String cityname) throws IOException {
        File file=new File(System.getProperty("user.dir")+"\\searchfreq");
        BufferedReader br= new BufferedReader(new FileReader(file));
        ArrayList<String>temp= new ArrayList<String>();
        String s;
        while((s=br.readLine())!=null){
            String city=((s.substring(0,s.lastIndexOf(':'))).toUpperCase());
            String counting=(s.substring(s.lastIndexOf(':')+1,s.length()));

            if(city.matches(cityname.toUpperCase())){
                int count= Integer.parseInt(counting)+1;
                System.out.println("hhola");
                String fin= s.substring(0,s.lastIndexOf(':')+1).concat(Integer.toString(count));
                System.out.println(fin);
                temp.add(fin);
            }
            else{
                temp.add(s);
            }
        }
        BufferedWriter outputWriter = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"\\searchfreq"));
        for (int i = 0; i < temp.size(); i++) {
            outputWriter.write(temp.get(i));
            //System.out.println(temp.get(i));
            outputWriter.newLine();
        }
        outputWriter.flush();
        outputWriter.close();


    }

    public static void initialize(String cityname){

    }
    public static void first_time() throws FileNotFoundException {
        PrintWriter pw= new PrintWriter("searchfreq");
        pw.println("Toronto:1");
        pw.println("Calgary:1");
        pw.close();

    }
    public static void main(String[] args) throws FileNotFoundException {
        first_time();

    }
}
