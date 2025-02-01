package com.veron_santiago.facturas_api.service.implementations;

import com.veron_santiago.facturas_api.persistance.entity.AppUser;
import com.veron_santiago.facturas_api.persistance.repository.IAppUserRepository;
import com.veron_santiago.facturas_api.service.interfaces.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserServiceImpl implements IAppUserService {

    private final IAppUserRepository appUserRepository;

    @Autowired
    public AppUserServiceImpl(IAppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }


    @Override
    public AppUser createAppUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    @Override
    public AppUser getAppUserById(Long id) {
        return appUserRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public List<AppUser> getAllAppUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser updateAppUser(Long id, AppUser appUser) {
        AppUser existingUser = appUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        existingUser.setUsername(appUser.getUsername());
        existingUser.setEmail(appUser.getEmail());
        existingUser.setAlias(appUser.getAlias());
        existingUser.setCuit(appUser.getCuit());

        return appUserRepository.save(existingUser);
    }

    @Override
    public void deleteAppUser(Long id) {
        AppUser appUser = appUserRepository.findById(id)
                        .orElseThrow( () -> new RuntimeException("Usuario no encontrado con ID: " + id));
        appUserRepository.deleteById(id);
    }
}
