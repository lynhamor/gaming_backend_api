package com.api.auth.controller;

import com.api.auth.database.repository.OperatorRepository;
import com.api.auth.dto.OperatorDto;
import com.api.auth.dto.ResponseDto;
import com.api.auth.service.OperatorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Operator Controller")
@RestController
@RequestMapping("/api/operator")
public class OperatorController {

    private final OperatorService operatorService;
    private final OperatorRepository operatorRepository;

    public OperatorController(OperatorService operatorService, OperatorRepository operatorRepository) {
        this.operatorService = operatorService;
        this.operatorRepository = operatorRepository;
    }

//    @GetMapping("/generate-token")
//    public ResponseEntity<ResponseDto> generateToken(
//            @RequestParam Long operatorId
//    ) {
//
//        return operatorService.generateToken(operatorId).getResponseEntity();
//    }

    @GetMapping("/generate-token")
    public ResponseEntity<ResponseDto> generateOperatorToken(){
        return operatorService.generateOperatorToken().getResponseEntity();
    }

    @PostMapping("/register-token")
    public ResponseEntity<ResponseDto> registerOperatorToken(
            @RequestParam Long operatorId,
            @RequestParam String operatorToken
    ){
        return operatorService.registerOperatorToken(operatorId, operatorToken).getResponseEntity();
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> register(
            @RequestBody OperatorDto dto
    ) {
        return operatorService.register(dto).getResponseEntity();
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getOperator(
            @RequestParam(required = false) Long operatorId,
            @RequestParam(required = false) String operatorName
    ) {
        return operatorService.getOperator(operatorId, operatorName).getResponseEntity();
    }
}
