create index IX_E2C2D8C9 on TEST_Task (groupId, completed);
create index IX_9D571B96 on TEST_Task (groupId, title[$COLUMN_LENGTH:75$]);
create index IX_D148801C on TEST_Task (groupId, userId, title[$COLUMN_LENGTH:75$]);