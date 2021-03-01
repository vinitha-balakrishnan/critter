package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PetService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PetRepository petRepository;

    public Pet savePet(Pet pet, long ownerId) {
        List<Pet>petList = new ArrayList<>();
        Customer customer = customerRepository.getOne(ownerId);

        pet.setCustomer(customer);
        pet = petRepository.save(pet);
        petList.add(pet);

        customer.setPets(petList);
        customerRepository.save(customer);
       return pet;
    }

    public Pet getPetById(long petId) {
        return petRepository.getOne(petId);
    }

    public List<Pet> getAllPets() {
      return petRepository.findAll();
    }

    public List<Pet> getPetsByCustomerId(long ownerId) {
      return petRepository.findPetByCustomerId(ownerId);
    }
}
