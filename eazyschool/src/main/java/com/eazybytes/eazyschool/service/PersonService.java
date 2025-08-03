package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.constants.EazySchoolConstants;
import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.model.Roles;
import com.eazybytes.eazyschool.repository.PersonRepository;
import com.eazybytes.eazyschool.repository.RolesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class PersonService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private RolesRepository rolesRepository;

    public boolean createNewPerson(Person person){
        boolean isSaved=false;

       Roles roles=rolesRepository.findByRoleName(EazySchoolConstants.STUDENT);
        person.setRoles(roles);
        log.info("roles: "+roles.toString());
        person.setPwd(passwordEncoder.encode(person.getPwd()));
        Person createdPerson=personRepository.save(person);

        if (createdPerson.getPersonId()>0){
            isSaved=true;
        }
        return isSaved;
    }
}
