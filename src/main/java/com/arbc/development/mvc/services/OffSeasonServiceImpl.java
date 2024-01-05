package com.arbc.development.mvc.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arbc.development.mvc.models.dto.OffSeasonDto;
import com.arbc.development.mvc.models.entities.PackageOffSeason;
import com.arbc.development.mvc.models.entities.UsersPackage;
import com.arbc.development.mvc.repositories.PackageOffSeasonRepository;
import com.arbc.development.mvc.repositories.TravelPackageRepository;
import com.arbc.development.mvc.repositories.UsersPackageRepository;

@Service
public class OffSeasonServiceImpl implements OffSeasonService{

    @Autowired
    private PackageOffSeasonRepository packageOffSeasonRepository;

    @Autowired
    private UsersPackageRepository usersPackageRepository;

    @Autowired
    private TravelPackageRepository travelPackageRepository;

    @Override
    @Transactional(readOnly = true)
    public List<OffSeasonDto> findAll() {
        List<PackageOffSeason> peakSeasons = (List<PackageOffSeason>) packageOffSeasonRepository.findAll();
        List<OffSeasonDto> dataDeliver = new ArrayList<OffSeasonDto>();
        for (PackageOffSeason packageOffSeason : peakSeasons) {
            List<UsersPackage> uP = usersPackageRepository.findByTraPackage(travelPackageRepository.findById(packageOffSeason.gettPackage()).get());
            int amount = 0;
            for (UsersPackage uPackage : uP) {
                amount += uPackage.getAmount();
            }
            OffSeasonDto pP = new OffSeasonDto(packageOffSeason.getCreation_date(), amount, travelPackageRepository.findById(packageOffSeason.gettPackage()).get().getName(), travelPackageRepository.findById(packageOffSeason.gettPackage()).get().getPrice() * amount);
            dataDeliver.add(pP);
        }
        return dataDeliver;
    }

}
