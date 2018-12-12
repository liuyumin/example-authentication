--
--  Oauth sql  -- MYSQL
--
 
Drop table  if exists t_oauth_client_details;
create table t_oauth_client_details (
  client_id VARCHAR(255) not null COMMENT '客户(第三方系统)编号' PRIMARY KEY,
  client_secret VARCHAR(255) not null COMMENT '客户(第三方系统)密码',
  resource_ids VARCHAR(255) COMMENT '资源编号app,pc',
  scope VARCHAR(255) not null COMMENT '资源权限范围',
  authorized_grant_types VARCHAR(255) COMMENT '验证权限类型code,密码等',
  web_server_redirect_uri VARCHAR(255) not null COMMENT '回调地址',
  authorities VARCHAR(255) COMMENT '所有权限信息',
  access_token_validity INTEGER COMMENT '访问token失效时间',
  refresh_token_validity INTEGER COMMENT '刷新token失效时间',
  additional_information TEXT COMMENT '扩展信息',
  autoapprove VARCHAR (255) default 'false' COMMENT '是否自动审核',
  create_time datetime(3)   NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建日期',
  update_time datetime(3)   DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  flag        tinyint(2)    NOT NULL DEFAULT '0' COMMENT '0：未删除，1：逻辑删除'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='第三方业务信息表';
 
 
Drop table  if exists t_oauth_access_token;
create table t_oauth_access_token (
  token_id VARCHAR(255) COMMENT 'TOKEN编号',
  token BLOB COMMENT 'TOKEN内容',
  authentication_id VARCHAR(255) COMMENT '验证编号',
  user_name VARCHAR(255) COMMENT '用户姓名',
  client_id VARCHAR(255) COMMENT '客户编号',
  authentication BLOB COMMENT '权限信息',
  refresh_token VARCHAR(255) COMMENT '刷新TOKEN',
  create_time datetime(3)   NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建日期',
  update_time datetime(3)   DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  flag        tinyint(2)    NOT NULL DEFAULT '0' COMMENT '0：未删除，1：逻辑删除'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='访问TOKEN信息';
 
 
Drop table  if exists t_oauth_refresh_token;
create table t_oauth_refresh_token (
  token_id VARCHAR(255) COMMENT 'TOKEN编号',
  token BLOB COMMENT 'TOKEN值',
  authentication BLOB COMMENT '权限信息'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='刷新TOKEN信息';
 
 
Drop table  if exists t_oauth_code;
create table t_oauth_code (
  code VARCHAR(255) COMMENT 'CODE码',
  authentication BLOB COMMENT '权限信息'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='验证CODE码表';


create table t_oauth_user
(
   id                   int(11)                        NOT NULL AUTO_INCREMENT COMMENT 'id',
   open_id              varchar(64)                    DEFAULT NULL COMMENT '系统用户唯一标识',
   user_name            varchar(32)                    DEFAULT NULL COMMENT '姓名',
   user_nickname        varchar(100)                   DEFAULT NULL COMMENT '昵称',
   account              varchar(20)                    DEFAULT NULL COMMENT '登陆账号',
   password             varchar(128)                   NOT NULL COMMENT '登陆密码',
   mobile_no            varchar(16)                    DEFAULT NULL COMMENT '电话',
   sex                  char(1)                        DEFAULT NULL COMMENT '性别 0-男 1-女',
   photo                varchar(255)                   DEFAULT NULL COMMENT '头像',
   email                varchar(64)                    DEFAULT NULL COMMENT '邮箱',
   is_freeze            tinyint(4)                     NOT NULL DEFAULT '0' COMMENT '是否冻结 0--未冻结 1--冻结',
   last_login_ip        varchar(32)                    DEFAULT NULL COMMENT '最后登录IP',
   last_login_time      datetime(3)                    DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '最后登录时间',
   create_time          datetime(3)                    NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建日期',
   create_by            varchar(32)                    NOT NULL COMMENT '创建人',
   update_time          datetime(3)                    DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
   update_by            varchar(32)                    NOT NULL COMMENT '修改人',
   flag                 tinyint(4)                     NOT NULL DEFAULT '0' COMMENT '0：未删除，1：逻辑删除',
   PRIMARY KEY (`id`),
   UNIQUE KEY `udx_account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='验证用户表';
 
 
 
-- Add indexes
create index token_id_index on t_oauth_access_token (token_id);
create index authentication_id_index on t_oauth_access_token (authentication_id);
create index user_name_index on t_oauth_access_token (user_name);
create index client_id_index on t_oauth_access_token (client_id);
create index refresh_token_index on t_oauth_access_token (refresh_token);
create index token_id_index on t_oauth_refresh_token (token_id);
create index code_index on t_oauth_code (code);
 
-- INSERT DEFAULT DATA
--INSERT INTO `oauth_client_details` VALUES ('dev', '', 'dev', 'app', 'authorization_code','http://localhost:7777/', '', '3600', '3600', '{"country":"CN","country_code":"086"}', 'TAIJI');
--------------------- 
