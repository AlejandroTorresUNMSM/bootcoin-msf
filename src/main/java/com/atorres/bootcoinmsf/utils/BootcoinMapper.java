package com.atorres.bootcoinmsf.utils;

import com.atorres.bootcoinmsf.model.PursecoinRequest;
import com.atorres.bootcoinmsf.model.dao.PursecoinDao;
import com.atorres.bootcoinmsf.model.dto.PursecoinDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

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

  public PursecoinDao toDao(PursecoinRequest request){
    PursecoinDao purse = new PursecoinDao();
    purse.setNombre(request.getNombre());
    purse.setPhone(request.getPhone());
    purse.setEmail(request.getEmail());
    purse.setBootcoin(new BigDecimal(("0")));
    purse.setPass(request.getPass());
    purse.setIsSeller(false);
    return purse;
  }
}
