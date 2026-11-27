package com.coderaah.medtrack.doctor.service;

import com.coderaah.medtrack.doctor.domain.Specialty;
import com.coderaah.medtrack.doctor.repository.SpecialtyRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SpecialtyService {
  private final SpecialtyRepository specialtyRepository;


  public SpecialtyService(SpecialtyRepository specialtyRepository) {
      this.specialtyRepository = specialtyRepository;
  }

  // STEP2: check if the specialty code and specialty name exists in the database

// this method checks if the code of speciality exists in the database or not
  public boolean isCodeTaken(String code){
      return specialtyRepository.existsByCode(code);
  }

  // this method checks if the name of speciality exists in the database or not
    public boolean isNameTaken(String name){
      return specialtyRepository.existsByName(name);
    }

    // STEP3: do the validation
    // throw an error if the specialty code already exists in the database
   public void validateSpecialtyCode(String code){
      if(isCodeTaken(code)){
          throw new IllegalArgumentException("This specialty code already exists!");
      }
   }

   // throw an error if the specialty name exists in the database
    public void validateSpecialtyName(String name){
      if(isNameTaken(name)){
          throw new IllegalArgumentException("This specialty name already exists!");
      }
    }
    //-----------------------------------------------------------------------------------------

  // Step 4: if specialty name or code does not exist in the database, then create and save it in the database

  public  Specialty createSpecialty(Specialty specialty){
      // validate specialty code
      validateSpecialtyCode(specialty.getCode());

      // validate specialty name
      validateSpecialtyName(specialty.getName());

      // save specialty
      return specialtyRepository.save(specialty);
  }

  // step 5: get all specialties
    public List<Specialty> getAllSpecialties(){
      return specialtyRepository.findAll();
    }

    // step 6: get specialty by ID
    public Specialty getSpecialtyById(Long id){
      return specialtyRepository.findById(id).
              orElseThrow(() -> new IllegalArgumentException("No Specialty found with id: " + id));
    }
}
