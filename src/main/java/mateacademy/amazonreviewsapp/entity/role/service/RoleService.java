package mateacademy.amazonreviewsapp.entity.role.service;

import mateacademy.amazonreviewsapp.entity.role.Role;

public interface RoleService {
    Role save(Role role);

    Role getByName(String name);
}
