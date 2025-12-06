package com.api.auth.service;

import com.api.auth.database.entity.OperatorToken;
import com.api.auth.database.repository.OperatorTokenRepository;
import com.api.auth.dto.OperatorDto;
import com.api.auth.dto.ResponseDto;
import com.api.auth.database.entity.Operator;
import com.api.auth.database.repository.OperatorRepository;
import com.api.auth.util.JwtUtil;
import com.api.auth.util.KeyGeneratorUtil;
import com.api.auth.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperatorService {

    private final JwtUtil jwtUtil;
    private final OperatorRepository operatorRepository;
    private final OperatorTokenRepository operatorTokenRepository;

    public ResponseDto generateToken(Long operatorId) throws Exception {

        Operator operator = operatorRepository.findById(operatorId)
                .orElseThrow(() -> new Exception("Operator not found with id: " + operatorId));

        String token = jwtUtil.generateToken(operator);

        return ResponseDto.success("success", token);
    }

    public ResponseDto register(OperatorDto dto) {

        Operator operator = new Operator();
        operator.setOperatorName(dto.operatorName());
        operator.setPassword(PasswordEncoder.encode(dto.password()));
        operator.setIsActive(true);
        operator.setCreatedBy("admin");
        operator.setUpdatedBy("admin");
        operator = operatorRepository.save(operator);

        return ResponseDto.success("success", operator);
    }

    public ResponseDto getOperator(Long operatorId, String operatorName) {

        List<Operator> operatorList = operatorRepository.findOperatorBy(operatorId, operatorName);

        return ResponseDto.success("success", operatorList);
    }

    public ResponseDto generateOperatorToken() {

        String operatorToken = KeyGeneratorUtil.generate(20);
        return ResponseDto.success("success", operatorToken);
    }

    public ResponseDto registerOperatorToken(Long operatorId, String token) {

        OperatorToken operatorToken = new OperatorToken();
        operatorToken.setOperatorId(operatorId);
        operatorToken.setToken(token);
        operatorToken.setCreatedBy("admin");
        operatorToken.setUpdatedBy("admin");

        operatorToken = operatorTokenRepository.save(operatorToken);

        return ResponseDto.success("success", operatorToken);
    }
}
