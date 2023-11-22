package project.commons.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.entities.Configs;
import project.repositories.ConfigsRepository;

@Service
@RequiredArgsConstructor
public class ConfigDeleteService {
    private final ConfigsRepository repository;

    public void delete(String code) {
        Configs configs = repository.findById(code).orElse(null);
        if (configs == null) {
            return;
        }

        repository.delete(configs);
        repository.flush();
    }
}