package com.arbc.development.mvc.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arbc.development.mvc.models.dto.PeakSeasonDto;
import com.arbc.development.mvc.models.entities.PackagePeakSeason;
import com.arbc.development.mvc.models.entities.UsersPackage;
import com.arbc.development.mvc.repositories.PackagePeakSeasonRepository;
import com.arbc.development.mvc.repositories.TravelPackageRepository;
import com.arbc.development.mvc.repositories.UsersPackageRepository;

@Service
public class PeakSeasonServiceImpl implements PeakSeasonService{

    @Autowired
    private PackagePeakSeasonRepository packagePeakSeasonRepository;

    @Autowired
    private UsersPackageRepository usersPackageRepository;

    @Autowired
    private TravelPackageRepository travelPackageRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PeakSeasonDto> findAll() {
        List<PackagePeakSeason> peakSeasons = (List<PackagePeakSeason>) packagePeakSeasonRepository.findAll();
        List<PeakSeasonDto> dataDeliver = new ArrayList<PeakSeasonDto>();
        for (PackagePeakSeason packagePeakSeason : peakSeasons) {
            List<UsersPackage> uP = usersPackageRepository.findByTraPackage(travelPackageRepository.findById(packagePeakSeason.gettPackage()).get());
            int amount = 0;
            for (UsersPackage uPackage : uP) {
                amount += uPackage.getAmount();
            }
            PeakSeasonDto pP = new PeakSeasonDto(packagePeakSeason.getCreation_date(), amount, travelPackageRepository.findById(packagePeakSeason.gettPackage()).get().getName(), travelPackageRepository.findById(packagePeakSeason.gettPackage()).get().getPrice() * amount);
            dataDeliver.add(pP);
        }
        return dataDeliver;
    }

}
