INSERT INTO users (id, name, username, password) VALUES (1, 'admin', 'zs1', 'passwd');
INSERT INTO users (id, name, username, password) VALUES (2, 'zs', 'zs2', 'passwd');
INSERT INTO users (id, name, username, password) VALUES (3, 'ls', 'zs3', 'passwd');
INSERT INTO users (id, name, username, password) VALUES (4, 'ww', 'zs4', 'passwd');


INSERT INTO roles (roles.id, roles.name) VALUES (1, '管理员');
INSERT INTO roles (roles.id, roles.name) VALUES (2, '普通员工');
INSERT INTO roles (roles.id, roles.name) VALUES (3, '主管');

INSERT INTO resources (resources.id, resources.name) VALUES (1, '菜单一');
INSERT INTO resources (resources.id, resources.name) VALUES (2, '菜单二');
INSERT INTO resources (resources.id, resources.name) VALUES (3, '菜单三');
INSERT INTO resources (resources.id, resources.name) VALUES (4, '菜单四');

INSERT INTO user_role (user_role.user_id, user_role.role_id) VALUES (1, 1);
INSERT INTO user_role (user_role.user_id, user_role.role_id) VALUES (2, 2);
INSERT INTO user_role (user_role.user_id, user_role.role_id) VALUES (3, 3);
INSERT INTO user_role (user_role.user_id, user_role.role_id) VALUES (4, 1);
INSERT INTO user_role (user_role.user_id, user_role.role_id) VALUES (4, 2);
INSERT INTO user_role (user_role.user_id, user_role.role_id) VALUES (4, 3);

INSERT INTO role_resource (role_resource.role_id, role_resource.resource_id) VALUES (1, 1);
INSERT INTO role_resource (role_resource.role_id, role_resource.resource_id) VALUES (1, 2);
INSERT INTO role_resource (role_resource.role_id, role_resource.resource_id) VALUES (1, 3);
INSERT INTO role_resource (role_resource.role_id, role_resource.resource_id) VALUES (1, 4);
INSERT INTO role_resource (role_resource.role_id, role_resource.resource_id) VALUES (2, 1);
INSERT INTO role_resource (role_resource.role_id, role_resource.resource_id) VALUES (2, 2);
INSERT INTO role_resource (role_resource.role_id, role_resource.resource_id) VALUES (2, 3);
INSERT INTO role_resource (role_resource.role_id, role_resource.resource_id) VALUES (2, 4);
INSERT INTO role_resource (role_resource.role_id, role_resource.resource_id) VALUES (3, 1);
INSERT INTO role_resource (role_resource.role_id, role_resource.resource_id) VALUES (3, 2);
INSERT INTO role_resource (role_resource.role_id, role_resource.resource_id) VALUES (3, 3);
INSERT INTO role_resource (role_resource.role_id, role_resource.resource_id) VALUES (4, 1);
INSERT INTO role_resource (role_resource.role_id, role_resource.resource_id) VALUES (4, 2);
