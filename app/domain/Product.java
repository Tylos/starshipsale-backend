package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    public String id;
    public String name;
    public String model;
    public String manufacturer;
    public String starship_class;
    public String cost_in_credits;
    public String image;
    public String passengers;
    public String crew;
    public String cargo_capacity;
    public String hyperdrive_rating;
    public Boolean is_featured;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Product product = (Product) o;

        return getId().equals(product.getId());

    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    public String getProductNameAsId() {
        return name.toLowerCase().replaceAll(" ","-");
    }

    public String getId(){
        return getProductNameAsId();
    }

}