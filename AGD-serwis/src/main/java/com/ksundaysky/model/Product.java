package com.ksundaysky.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;


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
        private String name;
        @Column(name = "owner_last_name")
        @NotEmpty(message = "*Uzupełnij nazwisko właściciela")
        private String lastName;
        @Column(name = "product_note")
        private String note;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

}
