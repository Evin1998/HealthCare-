package com.inti.student.healthcareordering.Model;

import java.util.ArrayList;

public class Product {
    private String id;
    private String code;
    private String name;
    private String description;
    private double price;

    public Product(String id, String code, String name, String description, double price) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static ArrayList<Product> createAllProductList() {
        ArrayList<Product> products = new ArrayList<Product>();

        products.add(new Product(
                "",
                "B001",
                "Abbott Pediasure Oht Complete Choco ",
                "Formulated with Sure3 System, PediaSure Oht Complete Choco 600g is a formula " +
                        "that provides complete nutrition for children age 1-10 years old.",
                65.40));

        products.add(new Product(
                "",
                "B002",
                "Actal",
                "For the relief of stomach discomfort such as indigestion, heartburn due to gastric " +
                        "hyperacidity, peptic ulcer and heartburn of pregnancy",
                3.80));

        products.add(new Product(
                "",
                "B003",
                "Acu-life Nasal Aspirator",
                "Effective at clearing mucus from baby’s nose to enable easier breathing",
                8.70));

        products.add(new Product(
                "",
                "S001",
                "Ascentia Nutri-C Vege Cap",
                "For antioxidant, immune booster (effective against common cold & help to speed " +
                        " up recovery) and tissue repair (wound healing)",
                69.00));

        products.add(new Product(
                "",
                "S002",
                "Flexigel Neepain 400mg Cap",
                "contains active ingredients that are rich in tetrahydrocurcuminoids & Bioactive Boswellin PS" +
                        " to offer fast and natural pain relief and support joint and muscular health.",
                69.00));

        products.add(new Product(
                "",
                "S003",
                "Simepar Cap",
                "Silymarin extract from Silybum marianum (milk thistle), thiamine hydrochloride, pyridoxine hydrochloride, " +
                        "riboflavin, nicotinamide, calcium pantothemate , cyanicobalamin",
                36.00));

        products.add(new Product(
                "",
                "M001",
                "Aspro Clear",
                "Each tablet is totally soluble for rapid absorption to give " +
                        "fast relief from headache.",
                30.50));

        products.add(new Product(
                "",
                "M002",
                "Paracetemol",
                "Purpose: Arthritic pain, Headache, Migraine headache,Muscular ache, " +
                        "Neuralgic conditions, etc.",
                8.90));

        products.add(new Product(
                "",
                "M003",
                "Nurofen",
                "Nurofen tablets help to reduce inflammation and provide relief from pain " +
                        "for up to 8 hours.",
                24.70));

        return products;
    }

    public static ArrayList<Product> createBabyCareProductList() {
        ArrayList<Product> products = new ArrayList<Product>();

        products.add(new Product(
                "",
                "B001",
                "Abbott Pediasure Oht Complete Choco ",
                "Formulated with Sure3 System, PediaSure Oht Complete Choco 600g is a formula " +
                        "that provides complete nutrition for children age 1-10 years old.",
                65.40));

        products.add(new Product(
                "",
                "B002",
                "Actal",
                "For the relief of stomach discomfort such as indigestion, heartburn due to gastric " +
                        "hyperacidity, peptic ulcer and heartburn of pregnancy",
                3.80));

        products.add(new Product(
                "",
                "B003",
                "Acu-life Nasal Aspirator",
                "Effective at clearing mucus from baby’s nose to enable easier breathing",
                8.70));

        return products;
    }

    public static ArrayList<Product> createSupplementProductList() {
        ArrayList<Product> products = new ArrayList<Product>();

        products.add(new Product(
                "",
                "S001",
                "Ascentia Nutri-C Vege Cap",
                "For antioxidant, immune booster (effective against common cold & help to speed " +
                        " up recovery) and tissue repair (wound healing)",
                69.00));

        products.add(new Product(
                "",
                "S002",
                "Flexigel Neepain 400mg Cap",
                "contains active ingredients that are rich in tetrahydrocurcuminoids & Bioactive Boswellin PS" +
                        " to offer fast and natural pain relief and support joint and muscular health.",
                69.00));

        products.add(new Product(
                "",
                "S003",
                "Simepar Cap",
                "Silymarin extract from Silybum marianum (milk thistle), thiamine hydrochloride, pyridoxine hydrochloride, " +
                        "riboflavin, nicotinamide, calcium pantothemate , cyanicobalamin",
                36.00));


        return products;
    }

    public static ArrayList<Product> createMedicineProductList() {
        ArrayList<Product> products = new ArrayList<Product>();

        products.add(new Product(
                "",
                "M001",
                "Aspro Clear",
                "Each tablet is totally soluble for rapid absorption to give " +
                        "fast relief from headache.",
                30.50));

        products.add(new Product(
                "",
                "M002",
                "Paracetemol",
                "Purpose: Arthritic pain, Headache, Migraine headache,Muscular ache, " +
                        "Neuralgic conditions, etc.",
                8.90));

        products.add(new Product(
                "",
                "M003",
                "Nurofen",
                "Nurofen tablets help to reduce inflammation and provide relief from pain " +
                        "for up to 8 hours.",
                24.70));

        return products;
    }
}
