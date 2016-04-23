package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    public Long id;
    public String name;
    public String model;
    public String manufacturer;
    public String startship_class;
    public String cost_in_credits;
    public String image;
    public String passengers;
    public String crew;
    public String cargo_capacity;
    public String hyperdrive_rating;
}