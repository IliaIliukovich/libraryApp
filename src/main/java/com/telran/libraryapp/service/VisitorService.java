package com.telran.libraryapp.service;



import com.telran.libraryapp.dto.VisitorDto;
import com.telran.libraryapp.entity.Visitor;
import com.telran.libraryapp.mapper.VisitorMapper;
import com.telran.libraryapp.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VisitorService {

    private final VisitorRepository visitorRepository;
    private final VisitorMapper visitorMapper;

    private final PasswordEncoder encoder;

    @Autowired
    public VisitorService(VisitorRepository visitorRepository, VisitorMapper visitorMapper, PasswordEncoder encoder) {
        this.visitorRepository = visitorRepository;
        this.visitorMapper = visitorMapper;
        this.encoder = encoder;
    }

    public List<VisitorDto> getAll() {
        List<Visitor> visitors = visitorRepository.findAll();
        return visitorMapper.entityListToDto(visitors);
    }

    public Optional<VisitorDto> getVisitorByEmail(String email) {
        Optional<Visitor> visitor = visitorRepository.findById(email);
        return visitor.map(visitorMapper::entityToDto);
    }

    public Optional<Visitor> getByLogin(String login) {
        return visitorRepository.findById(login);
    }

    public VisitorDto addVisitor(VisitorDto visitorDto) {
        Visitor visitor = visitorMapper.dtoToEntity(visitorDto);
        visitor.setPassword(encoder.encode(visitor.getPassword()));
        Visitor savedVisitor = visitorRepository.save(visitor);
        return visitorMapper.entityToDto(savedVisitor);
    }

    public boolean updateVisitor(VisitorDto visitorDto) {
        Optional<Visitor> optional = visitorRepository.findById(visitorDto.getEmail());
        if (optional.isPresent()) {
            visitorRepository.save(visitorMapper.dtoToEntity(visitorDto));
            return true;
        } else {
            return false;
        }
    }

    public void deleteVisitorByEmail(String email) {
        visitorRepository.deleteById(email);
    }

    public List<VisitorDto> getVisitorByName(String name) {
        List<Visitor> visitors = visitorRepository.findVisitorsByName(name);
        return visitorMapper.entityListToDto(visitors);
    }

    public void deleteAllRoles() {
        visitorRepository.deleteAll();
    }
}


