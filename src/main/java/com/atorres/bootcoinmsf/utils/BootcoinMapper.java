package com.atorres.bootcoinmsf.utils;

import com.atorres.bootcoinmsf.model.dao.PursecoinDao;
import com.atorres.bootcoinmsf.model.dto.PursecoinDto;
import org.springframework.stereotype.Component;

@Component
public class BootcoinMapper {
  public PursecoinDto toDto(PursecoinDao pursecoinDao){
    PursecoinDto purse= new PursecoinDto();
    purse.setId(pursecoinDao.getId());
    purse.setNombre(pursecoinDao.getNombre());
    purse.setPhone(pursecoinDao.getPhone());
    purse.setEmail(pursecoinDao.getEmail());
    purse.setBootcoin(pursecoinDao.getBootcoin());
    purse.setPass(pursecoinDao.getPass());
    return purse;
  }
}
