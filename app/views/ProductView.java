package views;

public class ProductView {

    public final Long id;
    public final String name;
    public final String model;
    public final String manufacturer;
    public final String starship_class;
    public final String cost_in_credits;
    public final String image;
    public final String passengers;
    public final String crew;
    public final String cargo_capacity;
    public final String hyperdrive_rating;
    public final Boolean is_featured;
    public final Boolean is_favorite;
    public final Boolean is_in_cart;

    private ProductView(Builder builder) {
        is_in_cart = builder.is_in_cart;
        id = builder.id;
        name = builder.name;
        model = builder.model;
        manufacturer = builder.manufacturer;
        starship_class = builder.starship_class;
        cost_in_credits = builder.cost_in_credits;
        image = builder.image;
        passengers = builder.passengers;
        crew = builder.crew;
        cargo_capacity = builder.cargo_capacity;
        hyperdrive_rating = builder.hyperdrive_rating;
        is_featured = builder.is_featured;
        is_favorite = builder.is_favorite;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ProductView copy) {
        Builder builder = new Builder();
        builder.is_in_cart = copy.is_in_cart;
        builder.id = copy.id;
        builder.name = copy.name;
        builder.model = copy.model;
        builder.manufacturer = copy.manufacturer;
        builder.starship_class = copy.starship_class;
        builder.cost_in_credits = copy.cost_in_credits;
        builder.image = copy.image;
        builder.passengers = copy.passengers;
        builder.crew = copy.crew;
        builder.cargo_capacity = copy.cargo_capacity;
        builder.hyperdrive_rating = copy.hyperdrive_rating;
        builder.is_featured = copy.is_featured;
        builder.is_favorite = copy.is_favorite;
        return builder;
    }


    public static final class Builder {
        private Long id;
        private String name;
        private String model;
        private String manufacturer;
        private String starship_class;
        private String cost_in_credits;
        private String image;
        private String passengers;
        private String crew;
        private String cargo_capacity;
        private String hyperdrive_rating;
        private Boolean is_featured = Boolean.FALSE;

        // User context
        private Boolean is_favorite = Boolean.FALSE;
        private Boolean is_in_cart = Boolean.FALSE;

        private Builder() {
        }

        public Builder withIsInCart(Boolean val) {
            is_in_cart = val;
            return this;
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withModel(String val) {
            model = val;
            return this;
        }

        public Builder withManufacturer(String val) {
            manufacturer = val;
            return this;
        }

        public Builder withStarshipClass(String val) {
            starship_class = val;
            return this;
        }

        public Builder withCostInCredits(String val) {
            cost_in_credits = val;
            return this;
        }

        public Builder withImage(String val) {
            image = val;
            return this;
        }

        public Builder withPassengers(String val) {
            passengers = val;
            return this;
        }

        public Builder withCrew(String val) {
            crew = val;
            return this;
        }

        public Builder withCargoCapacity(String val) {
            cargo_capacity = val;
            return this;
        }

        public Builder withHyperdriveRating(String val) {
            hyperdrive_rating = val;
            return this;
        }

        public Builder withIsFeatured(Boolean val) {
            is_featured = val;
            return this;
        }

        public Builder withIsFavorite(Boolean val) {
            is_favorite = val;
            return this;
        }

        public ProductView build() {
            return new ProductView(this);
        }
    }
}
