package mateacademy.amazonreviewsapp.entity.role.service;

import mateacademy.amazonreviewsapp.entity.role.Role;
import mateacademy.amazonreviewsapp.entity.role.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getByName(String name) {
        return roleRepository.getByName(Role.RoleName.valueOf(name));
    }
}
