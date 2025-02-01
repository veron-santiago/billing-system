package com.veron_santiago.facturas_api.service.interfaces;

import com.veron_santiago.facturas_api.persistance.entity.AppUser;

import java.util.List;

public interface IAppUserService {

    AppUser createAppUser(AppUser appUser);
    AppUser getAppUserById(Long id);
    List<AppUser> getAllAppUsers();
    AppUser updateAppUser(Long id, AppUser appUser);
    void deleteAppUser(Long id);

}
