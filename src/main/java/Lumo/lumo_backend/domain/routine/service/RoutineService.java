package Lumo.lumo_backend.domain.routine.service;

import Lumo.lumo_backend.domain.routine.dto.RoutineRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoutineService {

    public void createRoutine (Long userId, String title){


    }

    public RoutineRequestDTO.GetRoutineReqDTO getRoutine (Long userId){
        return null;
    }


    public void deleteRoutine(Long routineId){
        return;
    }

    public void renameRoutine( Long routineId,  String title) {
        return;
    }

}
