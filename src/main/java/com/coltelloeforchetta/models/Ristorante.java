package com.coltelloeforchetta.models;


public class Ristorante {
    String Name, Address, Location, Price, Cuisine, Longitude, Latitude, PhoneNumber, Url, WebsiteUrl, Award, GreenStar, FacilitiesAndServices,Description;

    public Ristorante( String name, String address, String location, String price, String cuisine, String longitude, String latitude, String phoneNumber, String url, String websiteUrl, String award, String greenStar, String facilitiesAndServices,String description) {
        Name = name;
        Address = address;
        Location = location;
        Price = price;
        Cuisine = cuisine;
        Longitude = longitude;
        Latitude = latitude;
        PhoneNumber = phoneNumber;
        Url = url;
        WebsiteUrl = websiteUrl;
        Award = award;
        GreenStar = greenStar;
        FacilitiesAndServices = facilitiesAndServices;
        Description=description;
        
    }


    
}
