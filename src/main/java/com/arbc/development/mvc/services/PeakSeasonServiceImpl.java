package com.arbc.development.mvc.services;

import java.time.LocalDate;
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

    @Override
    @Transactional(readOnly = true)
    public Double getReport(LocalDate starDate, LocalDate endDate) {
        List<PackagePeakSeason> peakSeasons = (List<PackagePeakSeason>) packagePeakSeasonRepository.findAll();
        Double revenue = 0.0;
        for (PackagePeakSeason packagePeakSeason : peakSeasons) {
            int amount =0;
            if (packagePeakSeason.getCreation_date().isAfter(starDate) && packagePeakSeason.getCreation_date().isBefore(endDate)) {
                List<UsersPackage> uP = usersPackageRepository.findByTraPackage(travelPackageRepository.findById(packagePeakSeason.gettPackage()).get());
                for (UsersPackage uPackage : uP) {
                    amount += uPackage.getAmount();
                }
                Double price = travelPackageRepository.findById(packagePeakSeason.gettPackage()).get().getPrice();
                Double priceOff = price / 1.15;
                priceOff = priceOff - (priceOff * 0.15);
                Double salePeak = price * amount;
                Double saleOff = priceOff * amount;
                Double saleDifference = salePeak - saleOff;
                revenue += saleDifference;
            }
        }
        return revenue;
    }

}
