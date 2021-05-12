package com.carta.service;

import com.carta.model.EmpTransaction;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CSVService {

    public void save (MultipartFile file);
}
