package com.project.tracker.service;

import com.project.tracker.entity.User;
import com.project.tracker.repo.UserRepo;
import com.project.tracker.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    // @Autowired
    //private ProgramRepository programRepository;
    @Autowired
    private UserRepo userRepo;

    public List<UserResource> getAllUser() {
        return (List<UserResource>) userRepo.findAll()
                .stream()
                .map(user -> new UserResource(user))
                .collect(Collectors.toList());
    }
    //public List<UserResource> getAllUserByProgram(long programId) {
      //  List<UserResource> users = programRepo.findById(programId)
        //        .get()
          //      .getUsers()
            //    .stream()
              //  .map(user -> new UserResource(user))
                //.collect(Collectors.toList());
       // return users;
    //}

    //public List<ProgramResource> getAllProgramByUser(long uid) {
      //  return userRepo.findProgramById(uid)
        //        .stream()
          //      .map(program -> new ProgramResource(program))
            //    .collect(Collectors.toList());
    //}

    public User getUser(long id) {
        return userRepo.findById(id).get();
    }


}
