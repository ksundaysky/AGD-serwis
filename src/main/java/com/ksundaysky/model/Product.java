package com.ksundaysky.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Digits;


@Entity
@Table(name = "product")
public class Product {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "product_id")
        private int id;
        @Column(name = "product_name")
        @NotEmpty(message = "*Uzupełnij nazwę produktu")
        private String product_name;
        @Column(name = "product_brand")
        @NotEmpty(message = "*Uzupełnij markę produktu")
        private String brand;
        @Column(name = "product_model")
        @NotEmpty(message = "*Uzupełnij model produktu")
        private String model;
        @Column(name = "owner_name")
        @NotEmpty(message = "*Uzupełnij imię właściciela")
        private String note;
        @Column(name = "unique_number")
        @Digits(integer=10, fraction=0, message = "provide valid serial number")
        private String serial;
        //id wlasciciela produktu
        @Column(name = "client_id")
        private int client_id;

        public Product(){

        }
        public Product(int id, String product_name, String brand, String model, String note, String serial, int client_id)
        {
            this.id=id;
            this.product_name=product_name;
            this.brand=brand;
            this.model=model;
            this.note=note;
            this.serial=serial;
            this.client_id=client_id;
        }
    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }


    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getClient_id() {
        return client_id;
    }
}
