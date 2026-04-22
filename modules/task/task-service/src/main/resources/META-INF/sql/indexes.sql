create index IX_E2C2D8C9 on TEST_Task (groupId, completed);
create index IX_9D571B96 on TEST_Task (groupId, title[$COLUMN_LENGTH:75$]);
create index IX_2CBA771D on TEST_Task (groupId, userId, active_);
create index IX_3EC28A4F on TEST_Task (groupId, userId, completed);
create index IX_DB275C1D on TEST_Task (groupId, userId, completedDate);
create index IX_976F4E6D on TEST_Task (groupId, userId, title[$COLUMN_LENGTH:75$], active_);
create index IX_5291776D on TEST_Task (groupId, userId, title[$COLUMN_LENGTH:75$], completedDate);