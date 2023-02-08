package com.example.pmb1;

import java.util.List;

public class respon {
    String value, message;
    List<Result> result;

    public String getValue(){
        return value;
    }

    public String getMessage(){
        return message;
    }

    public List<Result> getResult(){
        return result;
    }

    public class Result{
        String nama, nim, jurusan;

        public String getNama(){
            return nama;
        }

        public String getNim(){
            return nim;
        }

        public String getJurusan(){
            return jurusan;
        }
    }
}
