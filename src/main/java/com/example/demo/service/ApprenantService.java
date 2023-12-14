package com.example.demo.service;

import com.example.demo.entity.Apprenant;

import java.util.List;

public interface ApprenantService {
    public List<Apprenant> getAllApprenant();
    public Apprenant findApprenantById (Long id);
    public Apprenant createApprenant( Apprenant apprenant);
    public Apprenant updateApprenant( Apprenant apprenant);
    public void deleteApprenant( long id);

}
