package com.atorres.bootcoinmsf.utils;

import com.atorres.bootcoinmsf.model.CreateRequest;
import com.atorres.bootcoinmsf.model.PetitionRequest;
import com.atorres.bootcoinmsf.model.dao.BootcoinDao;
import com.atorres.bootcoinmsf.model.dao.PetitionDao;
import com.atorres.bootcoinmsf.model.dto.BootcoinDto;
import com.atorres.bootcoinmsf.model.dto.PetitionDto;
import com.atorres.bootcoinmsf.model.dto.SellerDto;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Component
public class BootcoinMapper {
  public BootcoinDto toDto(BootcoinDao bootcoinDao){
    BootcoinDto purse= new BootcoinDto();
    purse.setId(bootcoinDao.getId());
    purse.setNombre(bootcoinDao.getNombre());
    purse.setPhone(bootcoinDao.getPhone());
    purse.setEmail(bootcoinDao.getEmail());
    purse.setBootcoinAmount(bootcoinDao.getBootcoinAmount());
    purse.setPass(bootcoinDao.getPass());
    return purse;
  }

  public BootcoinDao toDao(CreateRequest request){
    BootcoinDao purse = new BootcoinDao();
    purse.setNombre(request.getNombre());
    purse.setPhone(request.getPhone());
    purse.setEmail(request.getEmail());
    purse.setBootcoinAmount(new BigDecimal(("0")));
    purse.setPass(request.getPass());
    purse.setIsSeller(false);
    purse.setPaymentId("");
    return purse;
  }

  public SellerDto toSeller(BootcoinDao bootcoinDao){
    SellerDto sellerDto = new SellerDto();
    sellerDto.setNombre(bootcoinDao.getNombre());
    sellerDto.setPhone(bootcoinDao.getPhone());
    sellerDto.setBootcoinAmount(bootcoinDao.getBootcoinAmount());
    return sellerDto;
  }

  public Mono<PetitionDao> toPetitionDao(BootcoinDao buyer, BootcoinDao seller, PetitionRequest request){
    PetitionDao petitionDao = new PetitionDao();
    petitionDao.setPhone(buyer.getPhone());
    petitionDao.setNameSeller(seller.getNombre());
    petitionDao.setNameBuyer(buyer.getNombre());
    petitionDao.setMethodTransfer("TRANSFERENCIA ENTRE CUENTAS");
    petitionDao.setAccountBuyer(buyer.getPaymentId());
    petitionDao.setNameSeller(seller.getPaymentId());
    petitionDao.setAmountcoin(request.getBootcoinAmout());
    return Mono.just(petitionDao);
  }

  public PetitionDto toPetitionDto(PetitionDao petitionDao){
    PetitionDto petition = new PetitionDto();
    petition.setId(petition.getId());
    petition.setPhone(petitionDao.getPhone());
    petition.setNameSeller(petition.getNameSeller());
    petition.setNameBuyer(petitionDao.getNameBuyer());
    petition.setMethodTransfer(petition.getMethodTransfer());
    petition.setAccountBuyer(petition.getAccountBuyer());
    petition.setNameSeller(petition.getNameSeller());
    petition.setAmountcoin(petition.getAmountcoin());
    return petition;
  }
}
