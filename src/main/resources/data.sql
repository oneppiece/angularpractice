INSERT INTO users (id, name, username, password, roleName, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled)
VALUES (1, 'admin', 'admin', '123456', "ROLE_ADMIN,ROLE_USER", TRUE, TRUE, TRUE, TRUE);
INSERT INTO users (id, name, username, password, roleName, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled)
VALUES (2, '张三', 'zs', 'passwd', "ROLE_USER", TRUE, TRUE, TRUE, TRUE);
INSERT INTO users (id, name, username, password, roleName, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled)
VALUES (3, '李四', 'ls', 'passwd', "ROLE_USER", TRUE, TRUE, TRUE, TRUE);
INSERT INTO users (id, name, username, password, roleName, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled)
VALUES (4, '王五', 'ww', 'passwd', "ROLE_USER", TRUE, TRUE, TRUE, TRUE);
INSERT INTO users (id, name, username, password, roleName, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled)
VALUES (5, '赵六', 'zl', 'passwd', "ROLE_USER", TRUE, TRUE, TRUE, TRUE);


INSERT INTO roles (roles.id, roles.name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (roles.id, roles.name) VALUES (2, 'ROLE_USER');
INSERT INTO roles (roles.id, roles.name) VALUES (3, 'ROLE_GUEST');

INSERT INTO resources (resources.id, resources.name, resources.url, resources.pid) VALUES (1, '菜单一', '/menu1', 0);
INSERT INTO resources (resources.id, resources.name, resources.url, resources.pid) VALUES (2, '菜单二', '/menu2', 0);
INSERT INTO resources (resources.id, resources.name, resources.url, resources.pid) VALUES (3, '菜单三', '/menu3', 0);
INSERT INTO resources (resources.id, resources.name, resources.url, resources.pid) VALUES (4, '菜单四', '/menu4', 0);

INSERT INTO user_role (user_role.user_id, user_role.role_id) VALUES (1, 1);
INSERT INTO user_role (user_role.user_id, user_role.role_id) VALUES (1, 2);
INSERT INTO user_role (user_role.user_id, user_role.role_id) VALUES (2, 2);
INSERT INTO user_role (user_role.user_id, user_role.role_id) VALUES (3, 2);
INSERT INTO user_role (user_role.user_id, user_role.role_id) VALUES (4, 2);
INSERT INTO user_role (user_role.user_id, user_role.role_id) VALUES (5, 2);
INSERT INTO user_role (user_role.user_id, user_role.role_id) VALUES (5, 3);

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

INSERT INTO products (product_name) VALUES ('商品1');
INSERT INTO products (product_name) VALUES ('商品2');
INSERT INTO products (product_name) VALUES ('商品3');
INSERT INTO products (product_name) VALUES ('商品4');
INSERT INTO products (product_name) VALUES ('商品5');
INSERT INTO products (product_name) VALUES ('商品6');
INSERT INTO products (product_name) VALUES ('商品7');
INSERT INTO products (product_name) VALUES ('商品8');
INSERT INTO products (product_name) VALUES ('商品9');
INSERT INTO products (product_name) VALUES ('商品10');

