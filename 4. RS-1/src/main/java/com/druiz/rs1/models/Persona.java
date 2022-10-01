package com.druiz.rs1.models;


import org.springframework.stereotype.Service;
@Service
public class Persona {
        private int id;
        private String name;
        public void setName(String name) {
                this.name = name;
        }
        public void setId(int id) {
                this.id = id;
        }
        public int getId(int id){
                return id;
        }
        public String getName() {
                return name;
        }
}


