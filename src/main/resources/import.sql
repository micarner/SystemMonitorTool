INSERT INTO tag (id,name) VALUES (1,'System');
INSERT INTO tag (id,name) VALUES (2,'Server');
INSERT INTO tag (id,name) VALUES (3,'Application');
INSERT INTO tag (id,name) VALUES (4,'Service');
INSERT INTO tag (id,name) VALUES (5,'Windows Service');
INSERT INTO tag (id,name) VALUES (6,'Uluro Service');

-- DFI Servers

insert into sysmontool.system (id,name,description,importance) VALUES (1,'RPAPPSVR-2k16', '', 'CRITICAL');
insert into sysmontool.system (id,name,description,importance) VALUES (2,'RPMAIN-2k16', '', 'CRITICAL');
insert into sysmontool.system (id,name,description,importance) VALUES (3,'PRESORT-2k16', '', 'CRITICAL');
insert into sysmontool.system (id,name,description,importance) VALUES (4,'PRSVR', '', 'HIGH');
insert into sysmontool.system (id,name,description,importance) VALUES (5,'PRSVR2', '', 'HIGH');
insert into sysmontool.system_tags (system_id,tags_id) VALUES (1,2);
insert into sysmontool.system_tags (system_id,tags_id) VALUES (2,2);
insert into sysmontool.system_tags (system_id,tags_id) VALUES (3,2);
insert into sysmontool.system_tags (system_id,tags_id) VALUES (4,2);
insert into sysmontool.system_tags (system_id,tags_id) VALUES (5,2);

insert into sysmontool.system (id,name,description,importance) VALUES (6,'RPpresort', '', 'CRITICAL');
insert into sysmontool.system_tags (system_id,tags_id) VALUES (6,5);
insert into sysmontool.system_tags (system_id,tags_id) VALUES (6,6);

insert into sysmontool.system (id,name,description,importance) VALUES (7,'RPcleansing', '', 'CRITICAL');
insert into sysmontool.system_tags (system_id,tags_id) VALUES (7,5);
insert into sysmontool.system_tags (system_id,tags_id) VALUES (7,6);

insert into sysmontool.system (id,name,description,importance) VALUES (8,'Breakpack', 'UluroBreakPackService', 'CRITICAL');
insert into sysmontool.system_tags (system_id,tags_id) VALUES (8,5);
insert into sysmontool.system_tags (system_id,tags_id) VALUES (8,6);

insert into sysmontool.system (id,name,description,importance) VALUES (9,'Preprocessing', 'UluroPrePService', 'CRITICAL');
insert into sysmontool.system_tags (system_id,tags_id) VALUES (9,5);
insert into sysmontool.system_tags (system_id,tags_id) VALUES (9,6);

insert into sysmontool.system (id,name,description,importance) VALUES (10,'Postprocessing', 'UluroPPService', 'CRITICAL');
insert into sysmontool.system_tags (system_id,tags_id) VALUES (10,5);
insert into sysmontool.system_tags (system_id,tags_id) VALUES (10,6);

insert into sysmontool.system (id,name,description,importance) VALUES (11,'SubFTP Service', 'UluroSubFTPService', 'CRITICAL');
insert into sysmontool.system_tags (system_id,tags_id) VALUES (11,5);
insert into sysmontool.system_tags (system_id,tags_id) VALUES (11,6);

insert into sysmontool.system (id,name,description,importance) VALUES (12,'Submission Processing', '', 'CRITICAL');
insert into sysmontool.system_tags (system_id,tags_id) VALUES (12,1);

insert into sysmontool.system (id,name,description,importance) VALUES (13,'Printing', '', 'CRITICAL');
insert into sysmontool.system_tags (system_id,tags_id) VALUES (13,1);

insert into sysmontool.system (id,name,description,importance) VALUES (14,'PDFPregen', 'Provides return files for customers, but mainly Arvest', 'CRITICAL');
insert into sysmontool.system_tags (system_id,tags_id) VALUES (14,1);
