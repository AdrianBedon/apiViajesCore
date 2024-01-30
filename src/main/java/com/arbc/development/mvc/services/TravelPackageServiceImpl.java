package com.arbc.development.mvc.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arbc.development.mvc.models.dto.TravelPackageDto;
import com.arbc.development.mvc.models.dto.mappers.DtoMapperTravelPackage;
import com.arbc.development.mvc.models.entities.Flight;
import com.arbc.development.mvc.models.entities.Hotel;
import com.arbc.development.mvc.models.entities.TravelPackage;
import com.arbc.development.mvc.models.entities.TravelPackageRequest;
import com.arbc.development.mvc.models.entities.TravelPackageSave;
import com.arbc.development.mvc.models.entities.User;
import com.arbc.development.mvc.models.entities.UsersPackage;
import com.arbc.development.mvc.repositories.FlightRepository;
import com.arbc.development.mvc.repositories.HotelRepository;
import com.arbc.development.mvc.repositories.TravelPackageRepository;
import com.arbc.development.mvc.repositories.UserRepository;
import com.arbc.development.mvc.repositories.UsersPackageRepository;

@Service
public class TravelPackageServiceImpl implements TravelPackageService{

    @Autowired
    private TravelPackageRepository travelPackageRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsersPackageRepository usersPackageRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TravelPackageDto> findAll() {
        List<TravelPackage> tPackages = (List<TravelPackage>) travelPackageRepository.findAll();
        return tPackages
                .stream()
                .map(tp -> DtoMapperTravelPackage.builder().setTPackage(tp).build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TravelPackageDto> findById(Long id) {
        return travelPackageRepository.findById(id).map(tp -> DtoMapperTravelPackage
                    .builder()
                    .setTPackage(tp)
                    .build());
    }

    @Override
    @Transactional
    public Optional<TravelPackageDto> save(TravelPackageSave tPackage) {
        TravelPackage tPackageDb = new TravelPackage();
        TravelPackage tPackageOptional = null;
        tPackageDb.setName(tPackage.getName());
        Optional<Hotel> oH = hotelRepository.findByName(tPackage.getHotel());
        Optional<Flight> oF = flightRepository.findByCode(tPackage.getFlight());
        Optional<User> oU = userRepository.findByUsername(tPackage.getUsername());
        UsersPackage upDb = new UsersPackage();
        User user = null;
        if (oH.isPresent() && oF.isPresent() && oU.isPresent()) {
            tPackageDb.setName(tPackage.getName());
            tPackageDb.setHotel(oH.orElseThrow());
            tPackageDb.setFlight(oF.orElseThrow());
            tPackageDb.setInitDate(tPackage.getInitDate());
            tPackageDb.setEndDate(tPackage.getEndDate());
            tPackageDb.setPrice(tPackage.getPrice());
            tPackageOptional = travelPackageRepository.save(tPackageDb);
            user = userRepository.findByUsername(tPackage.getUsername()).orElseThrow();
            upDb.setTraPackage(tPackageOptional);
            upDb.setPrice(tPackageOptional.getPrice());
            upDb.setUser(user);
            upDb.setAmount(tPackage.getAmount());
            usersPackageRepository.save(upDb);
        }
        return Optional.ofNullable(DtoMapperTravelPackage.builder().setTPackage(tPackageOptional).build());
    }

    @Override
    @Transactional
    public Optional<TravelPackageDto> update(TravelPackageRequest tpRequest)
    {
        Optional<TravelPackage> o = travelPackageRepository.findById(tpRequest.getId());
        TravelPackage tpOptional = null;
        UsersPackage upDb = new UsersPackage();
        if (o.isPresent())
        {
            TravelPackage tpDb = o.orElseThrow();
            User u = userRepository.findByUsername(tpRequest.getUsername()).orElseThrow();
            Optional<UsersPackage> upO = usersPackageRepository.findByUserAndTraPackage(u, tpDb);
            if (upO.isPresent())
            {
                upDb = upO.orElseThrow();
                upDb.setAmount(tpRequest.getAmount());
                usersPackageRepository.save(upDb);
            }
            else
            {
                upDb.setUser(u);
                upDb.setPrice(tpDb.getPrice());
                upDb.setTraPackage(tpDb);
                upDb.setAmount(tpRequest.getAmount());
                usersPackageRepository.save(upDb);
            }
            tpOptional = tpDb;
        }
        return Optional.ofNullable(DtoMapperTravelPackage.builder().setTPackage(tpOptional).build());
    }

    @Override
    @Transactional
    public void remove(Long id) {
        travelPackageRepository.deleteById(id);
    }

    @Override
    public List<TravelPackageDto> findByDate(LocalDate date) {
        List<TravelPackage> tPackages = (List<TravelPackage>) travelPackageRepository.findByInitDate(date);
        return tPackages
                .stream()
                .map(tp -> DtoMapperTravelPackage.builder().setTPackage(tp).build())
                .collect(Collectors.toList());
    }

    @Override
    public List<TravelPackageDto> findByPrice(Double priceInitial, Double priceFinal) {
        List<TravelPackage> tPackages = (List<TravelPackage>) travelPackageRepository.findAll();
        List<TravelPackage> filteredList = new ArrayList<TravelPackage>();
        for (TravelPackage travelPackage : tPackages) {
            if (travelPackage.getPrice() >= priceInitial && travelPackage.getPrice() <= priceFinal) {
                filteredList.add(travelPackage);
            }
        }
        return filteredList
                .stream()
                .map(tp -> DtoMapperTravelPackage.builder().setTPackage(tp).build())
                .collect(Collectors.toList());
    }
}
